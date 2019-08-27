package com.bigpumpkin.app.ddng_android.activity;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.bigpumpkin.app.ddng_android.R;
import com.bigpumpkin.app.ddng_android.base.BaseActivity;
import com.bigpumpkin.app.ddng_android.bean.Change_Bean;
import com.bigpumpkin.app.ddng_android.config.Urls;
import com.bigpumpkin.app.ddng_android.net.Contacts;
import com.bigpumpkin.app.ddng_android.persenter.MyPresenterImpl;
import com.bigpumpkin.app.ddng_android.utils.DataUtils;
import com.bigpumpkin.app.ddng_android.utils.EncryptUtils;
import com.bigpumpkin.app.ddng_android.utils.PermissionsUtils;
import com.bigpumpkin.app.ddng_android.utils.SpzUtilUser;
import com.bigpumpkin.app.ddng_android.utils.SpzUtils;
import com.bigpumpkin.app.ddng_android.utils.ToastUtil;
import com.bigpumpkin.app.ddng_android.view.MyView;
import com.bigpumpkin.app.ddng_android.weight.TitleXML;
import com.bumptech.glide.Glide;

import java.io.File;
import java.text.ParseException;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import pub.devrel.easypermissions.EasyPermissions;

public class SettingActivity extends BaseActivity implements MyView {
    //相册请求码
    private static final int ALBUM_REQUEST_CODE = 1;
    //相机请求码
    private static final int CAMERA_REQUEST_CODE = 2;
    //剪裁请求码
    private static final int CROP_REQUEST_CODE = 3;
    CircleImageView my_course_rn_img;

    //调用照相机返回图片文件
    private File tempFile;
    private String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA};
    private File filepath;//返回的文件地址
    RelativeLayout head;
    @BindView(R.id.sex)
    RelativeLayout sex;
    @BindView(R.id.birthday)
    RelativeLayout birthday;
    private TimePickerView pvTime;
    private View popupView, popup;
    private PopupWindow pw;
    private Uri contentUri;
    private Uri outputFile;
    private String appid;
    private String appsecret;
    private HashMap<String, Object> map;
    private HashMap<String, Object> headmap;
    private MyPresenterImpl presenter;
    private String sexs;
    private String birth;
    private EditText name;
    private Uri uri;
    private TextView birtha;
    private TextView sex1;

    @Override
    public int intiLayout() {
        return R.layout.activity_setting;
    }

    @Override
    public void initView() {
        my_course_rn_img = findViewById(R.id.my_course_rn_img);
        name = findViewById(R.id.ming);
        head = findViewById(R.id.head);
        birtha = findViewById(R.id.data);
        sex1 = findViewById(R.id.set_sex);
        String pic = SpzUtilUser.getString("pic");
        Glide.with(this).load(Urls.BASEURL + pic).into(my_course_rn_img);
        String names = SpzUtilUser.getString("name");
        if (name != null) {
            name.setText(names);
        }
        String birthS = SpzUtilUser.getString("birth");
        String sett = SpzUtilUser.getString("set");
        String sex = SpzUtilUser.getString("sex");
        if (birthS != null) {
            birtha.setText(birthS);
        }
        if (sett != null) {
            sex1.setText(sett);
        }
        if (sex != null) {
            sex1.setText(sex);
        } else {
            sex1.setText("请选择");
        }
        presenter = new MyPresenterImpl(this);
        new TitleXML(SettingActivity.this, "个人信息", true, "保存").init().setListener(new TitleXML.TitleXMLClick() {
            @Override
            public void onImage() {
                finish();
            }
        }, new TitleXML.OnRightTagClickListener() {
            @Override
            public void onClick() {
                long time = System.currentTimeMillis();
                appid = SpzUtils.getString("appid");
                appsecret = SpzUtils.getString("appsecret");
                String sha = "appid=" + appid + "&" + "appsecret=" + appsecret + "&" + "timestamp=" + time;
                String sha1 = EncryptUtils.getSHA(sha);
                birth = birtha.getText().toString();
                sexs = sex1.getText().toString();
                headmap = new HashMap<>();
                map = new HashMap<>();
                map.put("appid", appid);
                map.put("appsecret", appsecret);
                map.put("timestamp", time);
                map.put("sign", sha1);
                if (name.getText().toString() != null) {
                    map.put("name", name.getText().toString());
                }
                if (birtha.getText().toString() != null) {
                    map.put("date_birth", birtha.getText().toString());
                }
                if (sex1.getText().toString() != null) {
                    map.put("sex", sex1.getText().toString());
                }
                if (uri != null) {
                    map.put("pic", uri);
                }
                presenter.getpost(Contacts.My_change, headmap, map, Change_Bean.class);
            }
        });
        if (!EasyPermissions.hasPermissions(SettingActivity.this, permissions)) {
            EasyPermissions.requestPermissions(SettingActivity.this,
                    "需要下列权限以保证运行:存储空间、相机，不会以任何形式存储您的敏感信息。", 110, permissions);
        }

    }

    @Override
    public void initData() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.head, R.id.name, R.id.sex, R.id.birthday})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.head:
                //头像
                popupView =
                        LayoutInflater.from(SettingActivity.this).inflate(R.layout.personal_photo, null);
                pw = new PopupWindow(popupView, RelativeLayout.LayoutParams.MATCH_PARENT,
                        RelativeLayout.LayoutParams.WRAP_CONTENT);
                pw.setAnimationStyle(R.style.popwin_anim_style);
                WindowManager.LayoutParams lp = getWindow().getAttributes();
                lp.alpha = 0.7f;
                getWindow().setAttributes(lp);
                pw.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#3C000000")));
                //支持点击Back虚拟键退出
                pw.setFocusable(true);
                pw.showAtLocation(birthday, Gravity.BOTTOM, 0, 0);
                pw.setOnDismissListener(new PopupWindow.OnDismissListener() {
                    @Override
                    public void onDismiss() {
                        pw.dismiss();
                        infig();
                    }
                });
                popupView.findViewById(R.id.personal_phone_phone).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (!EasyPermissions.hasPermissions(SettingActivity.this, permissions)) {
                            PermissionsUtils.SettingPermissions(SettingActivity.this,
                                    Arrays.asList(permissions));
                            ToastUtil.showShort(SettingActivity.this, "权限获取失败");
                            return;
                        }
                        getPicFromCamera();
                        infig();
                    }
                });
                popupView.findViewById(R.id.personal_phone_camera).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        getPhoto();
                        infig();
                    }
                });
                popupView.findViewById(R.id.personal_phone_finish).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        pw.dismiss();
                        infig();
                    }
                });
                break;
            case R.id.name:
                //昵称
                //IntentUtils.getIntents().Intent(this, NameActivity.class, null);
                break;
            case R.id.sex:
                //性别
                gender();
                break;
            case R.id.birthday:
                //生日
                birthday();
                break;
        }
    }

    private void birthday() {

        //时间选择器
        pvTime = new TimePickerBuilder(SettingActivity.this,
                new OnTimeSelectListener() {
                    private String format;

                    @Override
                    public void onTimeSelect(Date date, View v) {
                        try {
                            format = DataUtils.dataToString(date);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        birtha.setText(format);
                    }
                }).build();
        pvTime.show();
    }

    //性别
    private void gender() {
        popup =
                LayoutInflater.from(SettingActivity.this).inflate(R.layout.personal_gender, null);
        pw = new PopupWindow(popup, RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = 0.7f;
        getWindow().setAttributes(lp);
        pw.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#3C000000")));
        //支持点击Back虚拟键退出
        pw.setFocusable(true);
        pw.showAtLocation(birthday, Gravity.BOTTOM, 0, 0);
        pw.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                pw.dismiss();
                WindowManager.LayoutParams lp = getWindow().getAttributes();
                lp.alpha = 1f;
                getWindow().setAttributes(lp);
            }
        });
        popup.findViewById(R.id.personal_male).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sex1.setText("男");
                pw.dismiss();
                WindowManager.LayoutParams lp = getWindow().getAttributes();
                lp.alpha = 1f;
                getWindow().setAttributes(lp);
            }
        });
        popup.findViewById(R.id.personal_female).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sex1.setText("女");
                pw.dismiss();
                WindowManager.LayoutParams lp = getWindow().getAttributes();
                lp.alpha = 1f;
                getWindow().setAttributes(lp);
            }
        });

        popup.findViewById(R.id.personal_phone_finish).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pw.dismiss();
                WindowManager.LayoutParams lp = getWindow().getAttributes();
                lp.alpha = 1f;
                getWindow().setAttributes(lp);
            }
        });
    }

    /**
     * 从相册获取图片
     */
    private void getPhoto() {
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, ALBUM_REQUEST_CODE);
    }


    /**
     * 从相机获取图片
     */
    private void getPicFromCamera() {
        //用于保存调用相机拍照后所生成的文件
        tempFile = new File(Environment.getExternalStorageDirectory().getPath(), System.currentTimeMillis() + ".jpg");
        //跳转到调用系统相机
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        //判断版本
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {   //如果在Android7.0以上,使用FileProvider获取Uri
            intent.setFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
            Uri contentUri = FileProvider.getUriForFile(SettingActivity.this, "com.hansion.chosehead", tempFile);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, contentUri);
            Log.e("dasd", contentUri.toString());
        } else {    //否则使用Uri.fromFile(file)方法获取Uri
            intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(tempFile));
        }
        startActivityForResult(intent, CAMERA_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        switch (requestCode) {
            case CAMERA_REQUEST_CODE:   //调用相机后返回
                if (resultCode == RESULT_OK) {
                    //用相机返回的照片去调用剪裁也需要对Uri进行处理
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        Uri contentUri = FileProvider.getUriForFile(SettingActivity.this, "com.hansion.chosehead", tempFile);
                        cropPhoto(contentUri);
                    } else {
                        cropPhoto(Uri.fromFile(tempFile));
                    }
                }
                break;
            case ALBUM_REQUEST_CODE:    //调用相册后返回
                if (resultCode == RESULT_OK) {
                    uri = intent.getData();
                    cropPhoto(uri);
                }
                break;
            case CROP_REQUEST_CODE:     //调用剪裁后返回
                Bundle bundle = intent.getExtras();
                if (bundle != null) {
                    //在这里获得了剪裁后的Bitmap对象，可以用于上传
                    Bitmap image = bundle.getParcelable("data");
                    //设置到ImageView上
                    my_course_rn_img.setImageBitmap(image);
                }
                break;
        }
    }

    /**
     * 裁剪图片
     */
    private void cropPhoto(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);

        intent.putExtra("outputX", 300);
        intent.putExtra("outputY", 300);
        intent.putExtra("return-data", true);

        startActivityForResult(intent, CROP_REQUEST_CODE);
    }


    private void infig() {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = 1f;
        getWindow().setAttributes(lp);
    }

    @Override
    public void success(Object data) {
        if (data instanceof Change_Bean) {
            Change_Bean change_bean = (Change_Bean) data;
            String code = change_bean.getCode();
            if (code.equals("200")) {
                ToastUtil.showShort(this, "修改成功");
                SpzUtilUser.putString("name", name.getText().toString());
                SpzUtilUser.putString("birth", birth);
                SpzUtilUser.putString("sex", sexs);
                SpzUtilUser.putString("pic", String.valueOf(uri));
                finish();
            } else {
                ToastUtil.showShort(this, "修改失败");
                finish();
            }
        }

    }

    @Override
    protected void onPause() {
        super.onPause();

    }

    @Override
    public void error(String error) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
