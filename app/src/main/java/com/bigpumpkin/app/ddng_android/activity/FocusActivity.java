package com.bigpumpkin.app.ddng_android.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bigpumpkin.app.ddng_android.R;
import com.bigpumpkin.app.ddng_android.adapter.FocusApapter;
import com.bigpumpkin.app.ddng_android.base.BaseActivity;
import com.bigpumpkin.app.ddng_android.bean.Cancel_Colledctions_Bean;
import com.bigpumpkin.app.ddng_android.bean.Focus_Bean;
import com.bigpumpkin.app.ddng_android.config.Urls;
import com.bigpumpkin.app.ddng_android.net.Contacts;
import com.bigpumpkin.app.ddng_android.persenter.MyPresenterImpl;
import com.bigpumpkin.app.ddng_android.utils.EmptyUtils;
import com.bigpumpkin.app.ddng_android.utils.EncryptUtils;
import com.bigpumpkin.app.ddng_android.utils.SpzUtils;
import com.bigpumpkin.app.ddng_android.utils.ToastUtil;
import com.bigpumpkin.app.ddng_android.view.MyView;
import com.bigpumpkin.app.ddng_android.weight.LoadingDialog;
import com.bumptech.glide.Glide;
import com.wkp.editlistview_library.view.EditListView;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class FocusActivity extends BaseActivity implements MyView {

    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    private String s;
    private HashMap<String, Object> map;
    private HashMap<String, Object> headmap;
    private MyPresenterImpl presenter;
    private FocusApapter focusApapter;
    private long curTimeLong;
    private List<Focus_Bean.DataBean> datas;
    private int i;
    private String appid;
    private String appsecret;
    private String sha1;
    private RelativeLayout relativeLayout;
    private EditListView mListView;
    private TextView tv_management,tv_complete;
    private LoadingDialog dialog;
    private ImageView return_iv;
    @Override
    public int intiLayout() {
        return R.layout.activity_focus;
    }

    @Override
    public void initView() {
        dialog = new LoadingDialog(this,"加载中...");
        mListView = findViewById(R.id.lv);
        tv_management = findViewById(R.id.tv_management);
        tv_complete = findViewById(R.id.tv_complete);
        return_iv = findViewById(R.id.return_iv);
        //设置编辑/退出编辑动画时长
        relativeLayout = findViewById(R.id.no_focus);
        presenter = new MyPresenterImpl(this);
        curTimeLong = getCurTimeLong();
        appid = SpzUtils.getString("appid");
        appsecret = SpzUtils.getString("appsecret");
        String sha = "appid=" + appid + "&" + "appsecret=" + appsecret + "&" + "timestamp=" + curTimeLong;
        sha1 = EncryptUtils.getSHA(sha);
        headmap = new HashMap<>();
        map = new HashMap<>();
        map.put("appid", appid);
        map.put("appsecret", appsecret);
        map.put("timestamp", curTimeLong);
        map.put("sign", sha1);
        dialog.show();
        presenter.getpost(Contacts.My_Focuss, headmap, map, Focus_Bean.class);
        tv_management.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //开启编辑状态
                mListView.setEditState(true);
                tv_complete.setVisibility(View.VISIBLE);
                tv_management.setVisibility(View.GONE);
            }
        });
        tv_complete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //关闭编辑状态
                mListView.setEditState(false);
                tv_complete.setVisibility(View.GONE);
                tv_management.setVisibility(View.VISIBLE);
            }
        });
        return_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void initData() {

    }

    public long getCurTimeLong() {
        long time = System.currentTimeMillis();
        return time;
    }


    @Override
    public void success(Object data) {
        if (data instanceof Focus_Bean) {
            dialog.close();
            Focus_Bean focus_bean = (Focus_Bean) data;
            datas = focus_bean.getData();
            if (datas.size() > 0) {
              /*  focusApapter = new FocusApapter(datas, this);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
                recyclerview.setLayoutManager(linearLayoutManager);
                recyclerview.setAdapter(focusApapter);
                recyclerview.setVisibility(View.VISIBLE);
             */
                //1 new一个适配器，并且将数据项的布局文件为R.layout.book_item
                //条目长按监听
                mListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                        mListView.setEditState(true);
                        mListView.setItemChecked(position,true);
                        return true;
                    }
                });
                relativeLayout.setVisibility(View.GONE);
                BookListAdapter bookAdapter = new BookListAdapter(this, R.layout.focus_item,datas);
                mListView.setAdapter(bookAdapter);
            } else {
                recyclerview.setVisibility(View.GONE);
                relativeLayout.setVisibility(View.VISIBLE);
            }
        } else if (data instanceof Cancel_Colledctions_Bean) {
            Cancel_Colledctions_Bean cancel_colledctions_bean = (Cancel_Colledctions_Bean) data;
            String code = cancel_colledctions_bean.getCode();
            if (EmptyUtils.isNotEmpty(code)) {
                if (code.equals("200")) {
                    datas.remove(i);
                    focusApapter.notifyItemRemoved(i);
                } else {
                    ToastUtil.showShort(FocusActivity.this, "取消失败");
                }
            }
        }
    }

    @Override
    public void error(String error) {
        Log.d(TAG, "error: " + error);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    //自定义适配器
    public class BookListAdapter extends BaseAdapter
    {
        private List<Focus_Bean.DataBean> bookList;
        private Context context;
        private int resource;

        public BookListAdapter(Context context, int resource, List<Focus_Bean.DataBean> bookList) {
            this.context = context;
            this.resource = resource;
            this.bookList = bookList;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            if (convertView == null)
            {
                //设置数据项的布局样式
                convertView = LayoutInflater.from(context).inflate(resource, parent, false);
            }

            //获取数据项布局里面的ImageView,TextView句柄
            CircleImageView picture = convertView.findViewById(R.id.picture);
            TextView address = (TextView) convertView.findViewById(R.id.address);
            TextView name = (TextView) convertView.findViewById(R.id.name);

            //获取点击的数据项信息
            Focus_Bean.DataBean book = bookList.get(position);

            //将数据项信息填充到imageView,TextView
            name.setText(book.getTitle());
            address.setText(book.getTitle());
            Glide.with(context).load(Urls.BASEURL + book.getPic3()).into(picture);

            return convertView;
        }

        @Override
        public int getCount() {
            return bookList.size();
        }

        @Override
        public Object getItem(int index) {
            return bookList.get(index);
        }

        @Override
        public long getItemId(int index) {
            return index;
        }
    }

}
