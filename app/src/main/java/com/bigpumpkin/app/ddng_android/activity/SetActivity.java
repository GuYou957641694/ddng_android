package com.bigpumpkin.app.ddng_android.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.bigpumpkin.app.ddng_android.R;
import com.bigpumpkin.app.ddng_android.base.BaseActivity;
import com.bigpumpkin.app.ddng_android.bean.ChangeBean;
import com.bigpumpkin.app.ddng_android.net.Contacts;
import com.bigpumpkin.app.ddng_android.persenter.MyPresenterImpl;
import com.bigpumpkin.app.ddng_android.utils.CleanMessageUtil;
import com.bigpumpkin.app.ddng_android.utils.DataUtils;
import com.bigpumpkin.app.ddng_android.utils.EncryptUtils;
import com.bigpumpkin.app.ddng_android.utils.GlideUtils;
import com.bigpumpkin.app.ddng_android.utils.ImageUtil;
import com.bigpumpkin.app.ddng_android.utils.IntentUtils;
import com.bigpumpkin.app.ddng_android.utils.RegexUtil;
import com.bigpumpkin.app.ddng_android.utils.SpzUtilUser;
import com.bigpumpkin.app.ddng_android.utils.SpzUtils;
import com.bigpumpkin.app.ddng_android.view.MyView;
import com.bigpumpkin.app.ddng_android.weight.DialogUtil;
import com.hjq.toast.ToastUtils;

import java.io.File;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import pub.devrel.easypermissions.EasyPermissions;

public class SetActivity extends BaseActivity implements MyView, EasyPermissions.PermissionCallbacks {

    @BindView(R.id.shipping_address)
    RelativeLayout shippingAddress;
    @BindView(R.id.security)
    RelativeLayout security;
    @BindView(R.id.remove)
    RelativeLayout remove;
    @BindView(R.id.version)
    RelativeLayout version;
    @BindView(R.id.exit)
    RelativeLayout exit;
    CircleImageView myCourseRnImg;
    EditText tvName;
    TextView tvData;
    TextView setSex;
    ImageView ivData;
    TextView tvSave;
    @BindView(R.id.return_iv)
    ImageView returnIv;
    private View popupView, popup;
    private PopupWindow pw;
    TimePickerView pvTime;
    private Uri uri;
    //相册请求码
    private static final int ALBUM_REQUEST_CODE = 1;
    //相机请求码
    private static final int CAMERA_REQUEST_CODE = 2;
    //剪裁请求码
    private static final int CROP_REQUEST_CODE = 3;
    //调用照相机返回图片文件
    private File tempFile;
    private String[] permissions = {Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE};
    private Bitmap image;
    private String appid;
    private String appsecret;
    private Map<String, RequestBody> map;
    private MyPresenterImpl presenter;
    private RelativeLayout relativeLayout;
    private String sha1;
    private String time;
    private String tvname;
    private String tvsex;
    private String tvbirthday;
    private boolean type;

    @Override
    public int intiLayout() {
        return R.layout.activity_set;
    }

    @Override
    public void initView() {
        SharedPreferences sharedPreferences = getSharedPreferences("user",
                Context.MODE_PRIVATE);
        getPermission();
        String pic = sharedPreferences.getString("pic", "");
        String name = sharedPreferences.getString("name", "");
        String birth = sharedPreferences.getString("birth", "");
        String sex = sharedPreferences.getString("sex", "");
        myCourseRnImg = findViewById(R.id.my_course_rn_img);
        tvName = findViewById(R.id.tv_name);
        setSex = findViewById(R.id.set_sex);
        tvData = findViewById(R.id.tv_data);
        ivData = findViewById(R.id.iv_data);
        tvSave = findViewById(R.id.tv_save);
        GlideUtils.loadImage(SetActivity.this, pic, myCourseRnImg);
        tvName.setText(name);
        if (sex != null && sex.length() > 0) {
            //1 男 2 女 0未设置
            if (sex.equals("1")) {
                setSex.setText("男");
            } else if (sex.equals("2")) {
                setSex.setText("女");
            } else {
                setSex.setText("未设置");
            }
        }
        if (birth.equals("0000-00-00")) {
            tvData.setText("请选择");
        } else {
            tvData.setText(birth);
        }
        presenter = new MyPresenterImpl(this);

        tvSave.setOnClickListener(new View.OnClickListener() {

            private File file;

            @Override
            public void onClick(View v) {
                tvsex = setSex.getText().toString();
                tvbirthday = tvData.getText().toString();
                time = String.valueOf(System.currentTimeMillis());
                appid = SpzUtils.getString("appid");
                appsecret = SpzUtils.getString("appsecret");
                String sha = "appid=" + appid + "&" + "appsecret=" + appsecret + "&" + "timestamp=" + time;
                sha1 = EncryptUtils.getSHA(sha);
                map = new HashMap<>();
                if (type ==true){
                    String path = ImageUtil.getPath(SetActivity.this, uri);
                    file = new File(path);
                    if (!file.exists() || file.length() == 0) {
                    } else {
                        map.put("file\";filename=\"" + file.getName(), RequestBody.create(MediaType.parse("image/jpg"), file));
                    }
                }

                tvname = tvName.getText().toString();
                boolean type = RegexUtil.checkUserName(tvname);
               /* if (type == false) {
                    ToastUtils.show("用户名不规范");
                    return;
                }*/
                map.put("appid", toRequestBody(appid));
                map.put("appsecret", toRequestBody(appsecret));
                map.put("timestamp", toRequestBody(time));
                map.put("sign", toRequestBody(sha1));
                map.put("name", toRequestBody(tvname));
                map.put("birthday", toRequestBody(tvbirthday));
                map.put("sex", toRequestBody(tvsex));
                presenter.image(Contacts.My_change, map, ChangeBean.class);
            }
        });
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

    @OnClick({R.id.shipping_address, R.id.security, R.id.return_iv, R.id.remove, R.id.version, R.id.exit, R.id.my_course_rn_img, R.id.tv_name, R.id.set_sex, R.id.tv_data, R.id.iv_data})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.shipping_address:
                IntentUtils.getIntents().Intent(this, Management_addressActivity.class, null);
                break;
            case R.id.security:
                IntentUtils.getIntents().Intent(this, SecurityActivity.class, null);
                break;
            case R.id.remove:
                try {
                    String totalCacheSize = CleanMessageUtil.getTotalCacheSize(this);
                    Log.d(TAG, "onViewClicked: " + totalCacheSize);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case R.id.version:
                IntentUtils.getIntents().Intent(this, AboutActivity.class, null);
                break;
            case R.id.exit:
                //退出
                DialogUtil.showAlertDialog(this, "退出提示", "你确定要退出吗？",
                        "取消", "确定", true, new DialogUtil.AlertDialogBtnClickListener() {
                            @Override
                            public void clickPositive() {

                            }

                            @Override
                            public void clickNegative() {
                                //negative
                                SpzUtilUser.clear();
                                SpzUtils.clear();
                                IntentUtils.getIntents().Intent(SetActivity.this, AllActivity.class, null);
                            }
                        });

                break;
            case R.id.my_course_rn_img:
                initHead();
                break;
            case R.id.tv_name:
                break;
            case R.id.set_sex:
                initSex();
                break;
            case R.id.tv_data:
            case R.id.iv_data:
                pvTime = new TimePickerBuilder(SetActivity.this,
                        new OnTimeSelectListener() {
                            private String format;

                            @Override
                            public void onTimeSelect(Date date, View v) {
                                try {
                                    format = DataUtils.dataToStrings(date);
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                                tvData.setText(format);
                            }
                        }).build();
                pvTime.show();
                break;
            case R.id.return_iv:
                finish();
                break;
        }
    }

    private void initSex() {
        View view = getLayoutInflater().inflate(R.layout.personal_gender, null);
        PopupWindow mPopupWindowthere = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        TextView tvmale = view.findViewById(R.id.tv_male);
        TextView tvFemale = view.findViewById(R.id.tv_female);
        tvmale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //男
                setSex.setText("男");
                mPopupWindowthere.dismiss();
            }
        });
        tvFemale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //女
                setSex.setText("女");
                mPopupWindowthere.dismiss();
            }
        });

        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = 0.4f;
        getWindow().setAttributes(lp);
        mPopupWindowthere.setFocusable(true);// 取得焦点
        mPopupWindowthere.setBackgroundDrawable(new BitmapDrawable());
        mPopupWindowthere.setOutsideTouchable(true);
        mPopupWindowthere.setTouchable(true);
        mPopupWindowthere.setAnimationStyle(R.style.mypopwindow_anim_style);

        mPopupWindowthere.setOnDismissListener(new PopupWindow.OnDismissListener() {
            public void onDismiss() {
                WindowManager.LayoutParams lp = getWindow().getAttributes();
                lp.alpha = 1f;
                getWindow().setAttributes(lp);
            }
        });
        mPopupWindowthere.showAtLocation(view, Gravity.BOTTOM, 0, 0);
    }

    private void initHead() {
        View view = getLayoutInflater().inflate(R.layout.personal_photo, null);
        PopupWindow mPopupWindowthere = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        TextView personal_phone_phone = view.findViewById(R.id.personal_phone_phone);
        TextView personal_phone_camera = view.findViewById(R.id.personal_phone_camera);
        TextView personal_phone_finish = view.findViewById(R.id.personal_phone_finish);
        personal_phone_phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //相机
                // getPicFromCamera();
                goCamera();
                mPopupWindowthere.dismiss();
            }
        });
        personal_phone_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //相册
                getPhoto();
                mPopupWindowthere.dismiss();
            }
        });
        personal_phone_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPopupWindowthere.dismiss();
            }
        });
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = 0.4f;
        getWindow().setAttributes(lp);
        mPopupWindowthere.setFocusable(true);// 取得焦点
        mPopupWindowthere.setBackgroundDrawable(new BitmapDrawable());
        mPopupWindowthere.setOutsideTouchable(true);
        mPopupWindowthere.setTouchable(true);
        mPopupWindowthere.setAnimationStyle(R.style.mypopwindow_anim_style);

        mPopupWindowthere.setOnDismissListener(new PopupWindow.OnDismissListener() {
            public void onDismiss() {
                WindowManager.LayoutParams lp = getWindow().getAttributes();
                lp.alpha = 1f;
                getWindow().setAttributes(lp);
            }
        });
        mPopupWindowthere.showAtLocation(view, Gravity.BOTTOM, 0, 0);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case CAMERA_REQUEST_CODE:   //调用相机后返回
                //用相机返回的照片去调用剪裁也需要对Uri进行处理
                if (data == null) {
                    return;
                } else {
                    if (data.getParcelableExtra("data") == null) {
                        return;
                    } else {
                        type =true;
                        Bitmap data1 = data.getParcelableExtra("data");
                        uri = Uri.parse(MediaStore.Images.Media.insertImage(SetActivity.this.getContentResolver(), data1, null, null));
                        // uri = FileProvider.getUriForFile(SetActivity.this, "com.bigpumpkin.app.ddng_android", cameraSavePath);
                        myCourseRnImg.setImageBitmap(data1);
                    }
                }
                break;
            case ALBUM_REQUEST_CODE:    //调用相册后返回
                if (resultCode == RESULT_OK) {
                    uri = data.getData();
                    cropPhoto(uri);
                }
                break;
            case CROP_REQUEST_CODE:     //调用剪裁后返回
                Bundle bundle = data.getExtras();
                image = bundle.getParcelable("data");
                myCourseRnImg.setImageBitmap(image);
                type =true;
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

    //激活相机操作
    private void goCamera() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.addCategory("android.intent.category.DEFAULT");
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        SetActivity.this.startActivityForResult(intent, CAMERA_REQUEST_CODE);
    }


    /**
     * 从相册获取图片
     */
    private void getPhoto() {
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, ALBUM_REQUEST_CODE);
    }

    //获取权限
    private void getPermission() {
        if (EasyPermissions.hasPermissions(this, permissions)) {
        } else {
            //没有打开相关权限、申请权限
            EasyPermissions.requestPermissions(this, "需要获取您的相册、照相使用权限", 1, permissions);
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        //框架要求必须这么写
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {
        //框架要求必须这么写
        ToastUtils.show("相关权限获取成功");
    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {
        ToastUtils.show("请同意相关权限，否则功能无法使用");
    }

    @Override
    public void success(Object data) {
        if (data instanceof ChangeBean) {
            ChangeBean changeBean = (ChangeBean) data;
            if (changeBean.getCode().equals("200")) {
                ToastUtils.show("修改成功");
                SharedPreferences.Editor editor = getSharedPreferences("user",
                        Context.MODE_PRIVATE).edit();
                if (changeBean.getData() != null) {
                    editor.putString("pic", changeBean.getData());
                }
                editor.putString("name", tvname);
                editor.putString("birth", tvbirthday);
                if (tvsex.equals("男")) {
                    editor.putString("sex", "1");
                } else if (tvsex.equals("女")) {
                    editor.putString("sex", "2");
                } else {
                    editor.putString("sex", "0");
                }
                editor.apply();
                IntentUtils.getIntents().Intent(SetActivity.this, AllActivity.class, null);
            }
        }
    }

    @Override
    public void error(String error) {

    }

    public RequestBody toRequestBody(String value) {
        RequestBody requestBody = RequestBody.create(MediaType.parse("text/plain"), value);
        return requestBody;
    }

    private String getPath() {
        String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/luban/";
        File file = new File(path);
        if (file.mkdir()) {
            return path;
        }
        return path;
    }


}
