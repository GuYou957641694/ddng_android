package com.bigpumpkin.app.ddng_android.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.bigpumpkin.app.ddng_android.R;
import com.bigpumpkin.app.ddng_android.base.BaseActivity;
import com.bigpumpkin.app.ddng_android.bean.CeSshi;
import com.bigpumpkin.app.ddng_android.bean.CustomBean;
import com.bigpumpkin.app.ddng_android.bean.EvaluationBean;
import com.bigpumpkin.app.ddng_android.bean.EvaluationDetailsBean;
import com.bigpumpkin.app.ddng_android.config.Urls;
import com.bigpumpkin.app.ddng_android.net.Contacts;
import com.bigpumpkin.app.ddng_android.persenter.MyPresenterImpl;
import com.bigpumpkin.app.ddng_android.utils.EncryptUtils;
import com.bigpumpkin.app.ddng_android.utils.GlideUtils;
import com.bigpumpkin.app.ddng_android.utils.SpzUtils;
import com.bigpumpkin.app.ddng_android.view.MyView;
import com.bigpumpkin.app.ddng_android.weight.EvaluationChoiceImageView;
import com.donkingliang.labels.LabelsView;
import com.google.gson.Gson;
import com.hjq.toast.ToastUtils;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import qiu.niorgai.StatusBarCompat;

//订单评价
public class EvaluationDetailsActivity extends BaseActivity implements MyView {
    public static final int REQUEST_CODE_CHOOSE = 1;
    private String appid;
    private String appsecret;
    private HashMap<String, Object> map;
    private HashMap<String, Object> headmap;
    private MyPresenterImpl presenter;
    private String sha1;
    private String time;
    private RecyclerView recyclerview;
    private List<LocalMedia> selectList = new ArrayList<>();
    private List<EvaluationDetailsBean.DataBean> data1;
    private PopupWindow pop;
    private EvaluationAdapter evaluationAdapter;
    private int mTempPosition;//存放选择的商品position
    private List<EvaluationBean> evaluationBeans;
    private List<CustomBean.DataBean> dataBeans;
    private EvaluationChoiceImageView mTempEvaluationChoiceImageView;//存放临时的EvaluationChoiceImageView
    private File fileByUri;
    private TextView tv_submit;
    private CustomBean.DataBean dataBean;

    @Override
    public int intiLayout() {
        return R.layout.activity_evaluation_details;
    }

    @Override
    public void initView() {
        StatusBarCompat.translucentStatusBar(this);
        String id = getIntent().getStringExtra("id");
        presenter = new MyPresenterImpl(this);
        time = String.valueOf(System.currentTimeMillis());
        appid = SpzUtils.getString("appid");
        appsecret = SpzUtils.getString("appsecret");
        String sha = "appid=" + appid + "&" + "appsecret=" + appsecret + "&" + "timestamp=" + time;
        sha1 = EncryptUtils.getSHA(sha);
        headmap = new HashMap<>();
        map = new HashMap<>();
        map.put("appid", appid);
        map.put("appsecret", appsecret);
        map.put("timestamp", time);
        map.put("sign", sha1);
        map.put("order_id", id);
        recyclerview = findViewById(R.id.recyclerview);
        tv_submit = findViewById(R.id.tv_submit);
        evaluationBeans = new ArrayList<>();
        dataBeans = new ArrayList<>();

    }

    @Override
    public void initData() {
        presenter.getpost(Contacts.evaluations, headmap, map, EvaluationDetailsBean.class);
    }

    @Override
    public void success(Object data) {
        if (data instanceof EvaluationDetailsBean) {
            EvaluationDetailsBean evaluationDetailsBean = (EvaluationDetailsBean) data;
            data1 = evaluationDetailsBean.getData();
            initAdapter();
            for (int i = 0; i < data1.size(); i++) {
                EvaluationBean evaluationBean = new EvaluationBean();
                evaluationBean.setId(data1.get(i).getCp_id());
                evaluationBeans.add(evaluationBean);

                dataBean = new CustomBean.DataBean();
                dataBean.setId(data1.get(i).getCp_id());
                dataBeans.add(dataBean);
            }

            tv_submit.setOnClickListener(new View.OnClickListener() {

                private Map<String, RequestBody> maps;

                @Override
                public void onClick(View v) {
                    maps = new HashMap<>();
                    Gson gson = new Gson();
                    String obj = gson.toJson(dataBeans);
                    maps.put("appid", toRequestBody(appid));
                    maps.put("appsecret", toRequestBody(appsecret));
                    maps.put("timestamp", toRequestBody(time));
                    maps.put("sign", toRequestBody(sha1));
                  //  maps.put("Parameters", RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), obj));
                    maps.put("file\";filename=\"" + fileByUri.getName(), RequestBody.create(MediaType.parse("image/jpg"), fileByUri));
                    maps.put("file\";filename=\"" + fileByUri.getName(), RequestBody.create(MediaType.parse("image/jpg"), fileByUri));
                    presenter.image(Contacts.insert_evaluations, maps, CeSshi.class);
                }

            });
        } else if (data instanceof CeSshi) {
            CeSshi ceSshi = (CeSshi) data;
            String code = ceSshi.getCode();
            ToastUtils.show(ceSshi.getData());
        }
    }

    private void initAdapter() {
        evaluationAdapter = new EvaluationAdapter(data1, this);
        recyclerview.setAdapter(evaluationAdapter);
        recyclerview.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
    }

    @Override
    public void error(String error) {

    }

    public class EvaluationAdapter extends RecyclerView.Adapter<EvaluationAdapter.ViewHolder> {

        List<EvaluationDetailsBean.DataBean> data1;
        Context context;
        private ArrayList<String> label = new ArrayList<>();

        public EvaluationAdapter(List<EvaluationDetailsBean.DataBean> data1, Context context) {
            this.data1 = data1;
            this.context = context;
        }


        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View view = LayoutInflater.from(context).inflate(R.layout.evaluationdetails_item, viewGroup, false);
            final ViewHolder holder = new ViewHolder(view);
            return holder;
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder myViewHolder, int i) {
            GlideUtils.loadRoundCircleImage(context, Urls.BASEURL + data1.get(i).getPic(), myViewHolder.pic);
            myViewHolder.farm_name.setText(data1.get(i).getTitle());
            myViewHolder.gg_title.setText(data1.get(i).getPz_title() + data1.get(i).getGg_title());
            List<EvaluationDetailsBean.DataBean.EvaluationDimensionBean> evaluation_dimension = data1.get(i).getEvaluation_dimension();
            for (int j = 0; j < evaluation_dimension.size(); j++) {
                label.add(evaluation_dimension.get(j).getTitle());
                myViewHolder.labeled.setLabels(label);
            }
            //标签的点击监听
            myViewHolder.labeled.setOnLabelClickListener(new LabelsView.OnLabelClickListener() {
                @Override
                public void onLabelClick(TextView label, Object data, int position) {
                    //label是被点击的标签，data是标签所对应的数据，position是标签的位置。
                    Log.d(TAG, "onLabelClick: " + i + position);
                    String id = evaluation_dimension.get(position).getId();
                    evaluationBeans.get(i).setEvaluatinType(id + ",");

                    dataBeans.get(i).setEvaluation_dimension(id + ",");
                 /*   StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append(id+",");
                    stringBuilder.toString();*/
                    //dataBeans.get(i).setEvaluation_dimension(id);
                }
            });
            //评价内容
            myViewHolder.evaluation.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    evaluationBeans.get(i).setEvaluationContent(s.toString() + ",");
                    dataBeans.get(i).setTitle(s.toString() + "");
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });

            myViewHolder.itemRegularevaluationEvaluationchoiceimageview.setOnClickAddImageListener(new EvaluationChoiceImageView.OnClickAddImageListener() {
                @Override
                public void onClickAddImage() {
                    Toast.makeText(context, "" + i, Toast.LENGTH_SHORT).show();
                    //这里将EvaluationChoiceImageView存到临时变量中好对不同的EvaluationChoiceImageView添加图片
                    mTempEvaluationChoiceImageView = myViewHolder.itemRegularevaluationEvaluationchoiceimageview;
                    mTempPosition = i;
                    choiceImage();
                }
            });
            myViewHolder.itemRegularevaluationEvaluationchoiceimageview.setOnClickDeleteImageListener(new EvaluationChoiceImageView.OnClickDeleteImageListener() {
                @Override
                public void onClickDeleteImage(int position) {
                    evaluationBeans.get(i).getEvaluationImages().remove(position);
                }
            });

            myViewHolder.itemRegularevaluationEvaluationchoiceimageview.setOnClickImageListener(new EvaluationChoiceImageView.OnClickImageListener() {
                @Override
                public void onClickImage(int position) {
                    //Intent intent=new Intent(context,SeePhotoActivity.class);
                    //intent.putExtra("photoPath",evaluationBeans.get(itemposition).getEvaluationImages().get(position).getAbsolutePath());
                    //startActivity(intent);
                    //overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
                }
            });
        }

        @Override
        public int getItemCount() {
            return data1.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            ImageView pic;
            TextView farm_name;
            TextView gg_title;
            EditText evaluation;
            LabelsView labeled;
            EvaluationChoiceImageView itemRegularevaluationEvaluationchoiceimageview;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                pic = itemView.findViewById(R.id.pic);
                farm_name = itemView.findViewById(R.id.farm_name);
                gg_title = itemView.findViewById(R.id.gg_title);
                labeled = itemView.findViewById(R.id.labeled);
                evaluation = itemView.findViewById(R.id.evaluation);
                itemRegularevaluationEvaluationchoiceimageview = itemView.findViewById(R.id.item_regularevaluation_evaluationchoiceimageview);

            }
        }
    }

    @SuppressLint("CheckResult")
    private void choiceImage() {
        PictureSelector.create(EvaluationDetailsActivity.this)
                .openGallery(PictureMimeType.ofImage())
                .maxSelectNum(2)
                .minSelectNum(1)
                .imageSpanCount(4)
                .selectionMode(PictureConfig.MULTIPLE)
                .forResult(PictureConfig.CHOOSE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == PictureConfig.CHOOSE_REQUEST) {// 图片选择结果回调
                List<LocalMedia> localMedia = PictureSelector.obtainMultipleResult(data);
                // pic.clear();
                for (int i = 0; i < localMedia.size(); i++) {
                    fileByUri = new File(localMedia.get(i).getPath());
                    evaluationBeans.get(mTempPosition).getEvaluationImages().add(i, fileByUri);

                    dataBeans.get(mTempPosition).getPic().add(i, fileByUri);
                    mTempEvaluationChoiceImageView.addImage(fileByUri.getAbsolutePath());

                }

//            压缩文件
            /*   Luban.with(this)
                        .load(fileByUri)
                        .ignoreBy(100)
                        .filter(new CompressionPredicate() {
                            @Override
                            public boolean apply(String path) {
                                return !(TextUtils.isEmpty(path) || path.toLowerCase().endsWith(".gif"));
                            }
                        })
                        .setCompressListener(new OnCompressListener() {
                            @Override
                            public void onStart() {
                            }

                            @Override
                            public void onSuccess(File file) {

                            }

                            @Override
                            public void onError(Throwable e) {

                            }
                        }).launch();*/
            }
        }
    }

    public RequestBody toRequestBody(String value) {
        RequestBody requestBody = RequestBody.create(MediaType.parse("text/plain"), value);
        return requestBody;
    }
}