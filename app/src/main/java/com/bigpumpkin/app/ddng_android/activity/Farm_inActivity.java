package com.bigpumpkin.app.ddng_android.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.bigpumpkin.app.ddng_android.R;
import com.bigpumpkin.app.ddng_android.adapter.FarmType_Adapter;
import com.bigpumpkin.app.ddng_android.adapter.FullyGridLayoutManager;
import com.bigpumpkin.app.ddng_android.adapter.GridImageAdapter;
import com.bigpumpkin.app.ddng_android.base.BaseActivity;
import com.bigpumpkin.app.ddng_android.bean.CeSshi;
import com.bigpumpkin.app.ddng_android.bean.Farm_TypeBean;
import com.bigpumpkin.app.ddng_android.net.Contacts;
import com.bigpumpkin.app.ddng_android.persenter.MyPresenterImpl;
import com.bigpumpkin.app.ddng_android.utils.NotificationUtil;
import com.bigpumpkin.app.ddng_android.view.MyView;
import com.bigpumpkin.app.ddng_android.weight.AddressPickerView;
import com.bigpumpkin.app.ddng_android.weight.RecyclerViewItemDecoration;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.permissions.Permission;
import com.luck.picture.lib.permissions.RxPermissions;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.functions.Consumer;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public class Farm_inActivity extends BaseActivity implements View.OnClickListener, MyView {

    private MyPresenterImpl presenter;
    private RecyclerView rv_picture;
    private RecyclerView rv_type;
    private GridImageAdapter adapter;
    private PopupWindow pop;
    private List<LocalMedia> selectList = new ArrayList<>();
    private List<String> img = new ArrayList<>();
    private List<Farm_TypeBean> list = new ArrayList<>();
    private List<String> lists = new ArrayList<>();
    private int maxSelectNum = 6;
    private TextView etFarmSex;
    private TextView etFarmRegion;
    private Map<String, RequestBody> map;

    @Override
    public int intiLayout() {
        return R.layout.activity_farm_in;
    }

    @Override
    public void initView() {
        NotificationUtil.setStatusBarTransparent(this);
        EditText etFarmName = findViewById(R.id.et_farm_name);
        etFarmSex = findViewById(R.id.et_farm_sex);
        EditText etFarmPhone = findViewById(R.id.et_farm_phone);
        etFarmRegion = findViewById(R.id.et_farm_region);
        EditText etDarmAddress = findViewById(R.id.et_farm_address);
        EditText etIntroduce = findViewById(R.id.et_introduce);
        rv_type = findViewById(R.id.rv_type);
        rv_picture = findViewById(R.id.rv_picture);
        Button bt_apply = findViewById(R.id.bt_apply);
        etFarmSex.setOnClickListener(this);
        etFarmRegion.setOnClickListener(this);
        bt_apply.setOnClickListener(this);

        initType();
        initWidget();
        presenter = new MyPresenterImpl(this);
        map = new HashMap<>();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getColor(R.color.FFF8F8F8));
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
    }

    private void initType() {
        lists.add("种植业");
        lists.add("养殖业");
        lists.add("畜物业");
        lists.add("农业深加工");
        lists.add("水产");
        lists.add("药材种植");
        lists.add("药食工厂");
        lists.add("其他");
        for (int i = 0; i < lists.size(); i++) {
            Farm_TypeBean farm_typeBean = new Farm_TypeBean(lists.get(i), false);
            list.add(farm_typeBean);
        }
        FarmType_Adapter farmType_adapter = new FarmType_Adapter(this, list);
        rv_type.setAdapter(farmType_adapter);
        rv_type.setLayoutManager(new StaggeredGridLayoutManager(3, OrientationHelper.VERTICAL));
        RecyclerViewItemDecoration decoration = new RecyclerViewItemDecoration(32);
        rv_type.addItemDecoration(decoration);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.et_farm_sex:
                initSex();
                break;
            case R.id.et_farm_region:
                adddress();
                break;
            case R.id.bt_apply:
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).isRype() == true) {
                        Log.d(TAG, "onClick: " + list.get(i).getName());
                    }
                }
                for (int i = 0; i < img.size(); i++) {
                    File file = new File(img.get(i));
                    map.put("pic[]\";filename=\"" + file.getName(), RequestBody.create(MediaType.parse("image/jpg"), file));
                }
                presenter.image(Contacts.ces, map, CeSshi.class);

                break;
        }
    }

    @Override
    public void initData() {

    }

    private void initWidget() {
        FullyGridLayoutManager manager = new FullyGridLayoutManager(this, 3, GridLayoutManager.VERTICAL, false);
        rv_picture.setLayoutManager(manager);
        adapter = new GridImageAdapter(this, onAddPicClickListener);
        adapter.setList(selectList);
        adapter.setSelectMax(maxSelectNum);
        rv_picture.setAdapter(adapter);
        adapter.setOnItemClickListener(new GridImageAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                if (selectList.size() > 0) {
                    LocalMedia media = selectList.get(position);
                    String pictureType = media.getPictureType();
                    int mediaType = PictureMimeType.pictureToVideo(pictureType);
                    switch (mediaType) {
                        case 1:
                            // 预览图片 可自定长按保存路径
                            //PictureSelector.create(MainActivity.this).externalPicturePreview(position, "/custom_file", selectList);
                            PictureSelector.create(Farm_inActivity.this).externalPicturePreview(position, selectList);
                            break;
                        case 2:
                            // 预览视频
                            PictureSelector.create(Farm_inActivity.this).externalPictureVideo(media.getPath());
                            break;
                        case 3:
                            // 预览音频
                            PictureSelector.create(Farm_inActivity.this).externalPictureAudio(media.getPath());
                            break;
                    }
                }
            }
        });
    }

    private void adddress() {
        View view = getLayoutInflater().inflate(R.layout.pop_addresss, null);
        PopupWindow mPopupWindowtwo = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        AddressPickerView addressPickerView = view.findViewById(R.id.apvAddress);
        addressPickerView.setOnAddressPickerSure(new AddressPickerView.OnAddressPickerSureListener() {
            @Override
            public void onSureClick(String sheng, String shi, String qu, String provinceCode, String cityCode, String districtCode) {
                etFarmRegion.setText(sheng + "-" + shi + "-" + qu);
                mPopupWindowtwo.dismiss();
            }
        });
        //产生背景变暗效果
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = 0.4f;
        getWindow().setAttributes(lp);
        mPopupWindowtwo.setFocusable(true);// 取得焦点
        //注意  要是点击外部空白处弹框消息  那么必须给弹框设置一个背景色  不然是不起作用的
        mPopupWindowtwo.setBackgroundDrawable(new BitmapDrawable());
        //点击外部消失
        mPopupWindowtwo.setOutsideTouchable(true);
        //设置可以点击
        mPopupWindowtwo.setTouchable(true);
        //mPopupWindow，指定刚才定义的style
        mPopupWindowtwo.setAnimationStyle(R.style.mypopwindow_anim_style);

        //设置popupWindow消失时的监听
        mPopupWindowtwo.setOnDismissListener(new PopupWindow.OnDismissListener() {
            //在dismiss中恢复透明度
            public void onDismiss() {
                WindowManager.LayoutParams lp = getWindow().getAttributes();
                lp.alpha = 1f;
                getWindow().setAttributes(lp);
            }
        });
        mPopupWindowtwo.showAtLocation(view, Gravity.BOTTOM, 0, 0);
    }

    private GridImageAdapter.onAddPicClickListener onAddPicClickListener = new GridImageAdapter.onAddPicClickListener() {

        @SuppressLint("CheckResult")
        @Override
        public void onAddPicClick() {
            //获取写的权限
            RxPermissions rxPermission = new RxPermissions(Farm_inActivity.this);
            rxPermission.requestEach(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    .subscribe(new Consumer<Permission>() {
                        @Override
                        public void accept(Permission permission) {
                            if (permission.granted) {// 用户已经同意该权限
                                //第一种方式，弹出选择和拍照的dialog
                                showPop();
                                //第二种方式，直接进入相册，但是 是有拍照得按钮的
//                                showAlbum();
                            } else {
                                Toast.makeText(Farm_inActivity.this, "拒绝", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
    };

    private void showPop() {
        View bottomView = View.inflate(Farm_inActivity.this, R.layout.personal_photo, null);
        TextView mAlbum = bottomView.findViewById(R.id.personal_phone_phone);
        TextView mCamera = bottomView.findViewById(R.id.personal_phone_camera);
        TextView mCancel = bottomView.findViewById(R.id.personal_phone_finish);

        pop = new PopupWindow(bottomView, -1, -2);
        pop.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        pop.setOutsideTouchable(true);
        pop.setFocusable(true);
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = 0.5f;
        getWindow().setAttributes(lp);
        pop.setOnDismissListener(new PopupWindow.OnDismissListener() {

            @Override
            public void onDismiss() {
                WindowManager.LayoutParams lp = getWindow().getAttributes();
                lp.alpha = 1f;
                getWindow().setAttributes(lp);
            }
        });
        pop.setAnimationStyle(R.style.main_menu_photo_anim);
        pop.showAtLocation(getWindow().getDecorView(), Gravity.BOTTOM, 0, 0);

        View.OnClickListener clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.personal_phone_camera:
                        //相册
                        PictureSelector.create(Farm_inActivity.this)
                                .openGallery(PictureMimeType.ofImage())
                                .maxSelectNum(maxSelectNum)
                                .minSelectNum(1)
                                .imageSpanCount(4)
                                .selectionMode(PictureConfig.MULTIPLE)
                                .forResult(PictureConfig.CHOOSE_REQUEST);
                        break;
                    case R.id.personal_phone_phone:
                        //拍照
                        PictureSelector.create(Farm_inActivity.this)
                                .openCamera(PictureMimeType.ofImage())
                                .forResult(PictureConfig.CHOOSE_REQUEST);
                        break;
                    case R.id.personal_phone_finish:
                        //取消
                        //closePopupWindow();
                        break;
                }
                closePopupWindow();
            }
        };

        mAlbum.setOnClickListener(clickListener);
        mCamera.setOnClickListener(clickListener);
        mCancel.setOnClickListener(clickListener);
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
                etFarmSex.setText("男");
                mPopupWindowthere.dismiss();
            }
        });
        tvFemale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //女
                etFarmSex.setText("女");
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

    public void closePopupWindow() {
        if (pop != null && pop.isShowing()) {
            pop.dismiss();
            pop = null;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        List<LocalMedia> images;
        if (resultCode == RESULT_OK) {
            if (requestCode == PictureConfig.CHOOSE_REQUEST) {// 图片选择结果回调
                images = PictureSelector.obtainMultipleResult(data);
                selectList.addAll(images);
                //selectList = PictureSelector.obtainMultipleResult(data);

                // 例如 LocalMedia 里面返回三种path
                // 1.media.getPath(); 为原图path
                // 2.media.getCutPath();为裁剪后path，需判断media.isCut();是否为true
                // 3.media.getCompressPath();为压缩后path，需判断media.isCompressed();是否为true
                // 如果裁剪并压缩了，以取压缩路径为准，因为是先裁剪后压缩的
                adapter.setList(selectList);
                adapter.notifyDataSetChanged();
                for (int i = 0; i < images.size(); i++) {
                    img.add(images.get(i).getPath());
                }
            }
        }
    }

    @Override
    public void success(Object data) {
        if (data instanceof CeSshi) {
            CeSshi ceSshi = (CeSshi) data;

        }
    }

    @Override
    public void error(String error) {

    }
}
