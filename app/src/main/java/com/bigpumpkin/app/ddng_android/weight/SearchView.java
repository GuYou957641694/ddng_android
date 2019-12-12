package com.bigpumpkin.app.ddng_android.weight;

import android.app.AlertDialog;
import android.content.Context;
import android.content.res.TypedArray;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bigpumpkin.app.ddng_android.R;
import com.bigpumpkin.app.ddng_android.activity.DetailsSearchActivity;
import com.bigpumpkin.app.ddng_android.adapter.HotSearchAdapter;
import com.bigpumpkin.app.ddng_android.adapter.SearchResultAdapter;
import com.bigpumpkin.app.ddng_android.bean.HotSearchsBean;
import com.bigpumpkin.app.ddng_android.net.Contacts;
import com.bigpumpkin.app.ddng_android.persenter.MyPresenterImpl;
import com.bigpumpkin.app.ddng_android.sqlite.RecordSQLiteOpenHelper;
import com.bigpumpkin.app.ddng_android.utils.IntentUtils;
import com.bigpumpkin.app.ddng_android.view.MyView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SearchView extends LinearLayout implements MyView {
    /**
     * 初始化成员变量
     */
    private Context context;
    // 搜索框组件
    private EditText et_search; // 搜索按键
    private ImageView tv_clear;  // 删除搜索记录按键
    private String keyword;
    // 数据库变量
    // 用于存放历史搜索记录
    private RecordSQLiteOpenHelper helper;
    private SQLiteDatabase db;
    private List<String> list;
    // 自定义属性设置
    // 1. 搜索字体属性设置：大小、颜色 & 默认提示
    private Float textSizeSearch;
    private int textColorSearch;
    private String textHintSearch;
    private int searchBlockHeight;
    private RecyclerView listView;
    private RecyclerView home_search_rv;
    private RelativeLayout rl;
    private TextView searchBack;
    private bCallBack bCallBack; // 返回按键回调接口
    private HashMap<String, Object> map;
    private HashMap<String, Object> headmap;
    private MyPresenterImpl presenter;
    private List<String> lists;
    private HotSearchAdapter hotSearchAdapter;

    public SearchView(Context context) {
        super(context);
        this.context = context;
        init();
    }

    public SearchView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        initAttrs(context, attrs);
        init();
    }


    public SearchView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        initAttrs(context, attrs);
        init();
    }

    private void init() {
        // 1. 初始化UI组件
        initView();
        // 2. 实例化数据库SQLiteOpenHelper子类对象
        helper = new RecordSQLiteOpenHelper(context);
        // 3. 第1次进入时查询所有的历史搜索记录
        queryData("");


        /**
         * "清空搜索历史"按钮
         */
        tv_clear.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext(), R.style.custom_dialog);
                LayoutInflater inflater = LayoutInflater.from(getContext());
                View view = inflater.inflate(R.layout.dialog_search_delete_layout, null);
                final AlertDialog dialog = builder.create();
                dialog.show();
                dialog.getWindow().setContentView(view);
                dialog.getWindow().setGravity(Gravity.CENTER);
                TextView ok = view.findViewById(R.id.dialog_delete_ok);//确认
                TextView no = view.findViewById(R.id.dialog_delete_no);//取消
                ok.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //  清空数据库->>关注2
                        deleteData();
                        // 模糊搜索空字符 = 显示所有的搜索历史（此时是没有搜索记录的）
                        queryData("");
                        dialog.dismiss();
                    }
                });
                no.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });

            }
        });

        et_search.setOnKeyListener(new OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

               /* boolean hasData = hasData(et_search.getText().toString().trim());
                // 3. 若存在，则不保存；若不存在，则将该搜索字段保存（插入）到数据库，并作为历史搜索记录
                if (!hasData) {
                    insertData(et_search.getText().toString().trim());
                    queryData("");
                }


                keyword = et_search.getText().toString().trim();
                //网络
                //searchResultPresenter.getSearchWordsPresenter(keyword);
                //searchResult.setVisibility(View.VISIBLE);
                //searchLi.setVisibility(View.GONE);
                if (keyword.equals("")) {
                    // searchLi.setVisibility(View.VISIBLE);
                    //searchResult.setVisibility(View.GONE);
                }*/
                return false;
            }
        });

        /**
         * 搜索框的文本变化实时监听
         */
        et_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                boolean hasData = hasData(et_search.getText().toString().trim());
                // 3. 若存在，则不保存；若不存在，则将该搜索字段保存（插入）到数据库，并作为历史搜索记录
                /*if (!hasData) {
                    insertData(et_search.getText().toString().trim());
                    queryData("");
                }                keyword = et_search.getText().toString().trim();
                // searchResultPresenter.getSearchWordsPresenter(keyword);
                //searchResult.setVisibility(View.VISIBLE);
                //searchLi.setVisibility(View.GONE);
                if (keyword.equals("")) {
                    // searchLi.setVisibility(View.VISIBLE);
                    // searchResult.setVisibility(View.GONE);
                }*/

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        et_search.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    String trim = et_search.getText().toString().trim();
                    if (trim.length() > 0) {
                        insertData(trim);
                        queryData("");
                        Bundle bundle = new Bundle();
                        bundle.putString("trim", trim);
                        IntentUtils.getIntents().Intent(context, DetailsSearchActivity.class, bundle);
                    }
                    return true;
                }
                return false;
            }
        });
    }

    private void queryData(String tempName) {
        list = new ArrayList<String>();
        // 1. 模糊搜索
        Cursor cursor = helper.getReadableDatabase().rawQuery(
                "select id as _id,name from records where name like '%" + tempName + "%' order by id desc ", null);

        while (cursor.moveToNext()) {
            String name = cursor.getString(cursor.getColumnIndex("name"));
            if (!list.contains(name)) {
                list.add(name);
            }
        }
        SearchResultAdapter searchResultAdapter = new SearchResultAdapter(getContext(), list);
        FlowLayoutManager flowLayoutManagers = new FlowLayoutManager();
        listView.addItemDecoration(new SpacesItemDecoration(dp2px(10)));
        listView.setLayoutManager(flowLayoutManagers);
        // 3. 设置适配器
        listView.setAdapter(searchResultAdapter);
        searchResultAdapter.notifyDataSetChanged();
        searchResultAdapter.setListener(new SearchResultAdapter.onListener() {
            @Override
            public void OnListener(int i) {
                String s = list.get(i);
                Bundle bundle = new Bundle();
                bundle.putString("trim", s);
                IntentUtils.getIntents().Intent(context, DetailsSearchActivity.class, bundle);
            }
        });
        System.out.println(cursor.getCount());
        // 当输入框为空 & 数据库中有搜索记录时，显示 "删除搜索记录"按钮
        if (tempName.equals("") && cursor.getCount() != 0) {
            tv_clear.setVisibility(VISIBLE);
        } else {
            tv_clear.setVisibility(INVISIBLE);
        }

        if (cursor.getCount() != 0) {
            rl.setVisibility(VISIBLE);
        } else {
            rl.setVisibility(GONE);
        }
        /**
         * 点击返回按键后的事件
         */
        searchBack.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // 注：由于返回需求会根据自身情况不同而不同，所以具体逻辑由开发者自己实现，此处仅留出接口
                if (!(bCallBack == null)) {
                    bCallBack.BackAciton();
                }

                //根据输入的内容模糊查询商品，并跳转到另一个界面，这个根据需求实现
                //Toast.makeText(context, "返回到上一页", Toast.LENGTH_SHORT).show();
            }
        });

        /**
         * 搜索记录列表（ListView）监听
         * 即当用户点击搜索历史里的字段后,会直接将结果当作搜索字段进行搜索
         */
     /*   listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                // 获取用户点击列表里的文字,并自动填充到搜索框内  并查询结果
                String s = list.get(position);
                et_search.setText(s);
                //searchResultPresenter.getSearchWordsPresenter(s);
            }
        });*/


    }

    private void initView() {
        // 1. 绑定R.layout.search_layout作为搜索框的xml文件
        LayoutInflater.from(context).inflate(R.layout.search_layout, this);
        et_search = (EditText) findViewById(R.id.et_search);
        et_search.setTextSize(textSizeSearch);
        et_search.setTextColor(textColorSearch);
        et_search.setHint(textHintSearch);
        rl = findViewById(R.id.rl);
        searchBack = (TextView) findViewById(R.id.search_back);
        listView = findViewById(R.id.listView);
        // 5. 删除历史搜索记录 按钮
        tv_clear = (ImageView) findViewById(R.id.tv_clear);
        home_search_rv = findViewById(R.id.home_search_rv);
        FlowLayoutManager flowLayoutManager = new FlowLayoutManager();
        home_search_rv.addItemDecoration(new SpacesItemDecoration(dp2px(10)));
        home_search_rv.setLayoutManager(flowLayoutManager);
        presenter = new MyPresenterImpl(this);
        headmap = new HashMap<>();
        map = new HashMap<>();
        presenter.get(Contacts.HotSearchs, headmap, map, HotSearchsBean.class);

    }

    private void initAttrs(Context context, AttributeSet attrs) {
        // 控件资源名称
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.Search_View);

        // 搜索框字体大小（dp）
        textSizeSearch = typedArray.getDimension(R.styleable.Search_View_textSizeSearch, 12);

        // 搜索框字体颜色（使用十六进制代码，如#333、#8e8e8e）
        int defaultColor = context.getResources().getColor(R.color.gray_cc); // 默认颜色 = 灰色
        textColorSearch = typedArray.getColor(R.styleable.Search_View_textColorSearch, defaultColor);

        // 搜索框提示内容（String）
        textHintSearch = typedArray.getString(R.styleable.Search_View_textHintSearch);
        // 搜索框高度
        searchBlockHeight = typedArray.getInteger(R.styleable.Search_View_searchBlockHeight, 150);

        // 搜索框颜色
//        int defaultColor2 = context.getResources().getColor(R.color.color_f5f5f5); // 默认颜色 = 灰色
//        searchBlockColor = typedArray.getColor(R.styleable.Search_View_searchBlockColor, defaultColor2);

        // 释放资源
        typedArray.recycle();
    }

    @Override
    public void success(Object data) {
        if (data instanceof HotSearchsBean) {
            HotSearchsBean hotSearchsBean = (HotSearchsBean) data;
            lists = hotSearchsBean.getData();
            hotSearchAdapter = new HotSearchAdapter(getContext(), lists);
            home_search_rv.setAdapter(hotSearchAdapter);
            //条目点击
            hotSearchAdapter.setOnSearchWordsListener(new HotSearchAdapter.onSearchWordsListener() {
                @Override
                public void onSearchWordsSuccess(int posiion) {
                    // 获取用户点击列表里的文字,并自动填充到搜索框内
                    String hot_word = lists.get(posiion);
                    Bundle bundle = new Bundle();
                    bundle.putString("trim", hot_word);
                    IntentUtils.getIntents().Intent(context, DetailsSearchActivity.class, bundle);
                }
            });
        }
    }

    @Override
    public void error(String error) {

    }

    /**
     * 关注2：清空数据库
     */
    private void deleteData() {
        db = helper.getWritableDatabase();
        db.execSQL("delete from records");
        db.close();
        tv_clear.setVisibility(View.GONE);
    }

    /**
     * 关注3
     * 检查数据库中是否已经有该搜索记录
     */
    private boolean hasData(String tempName) {
        // 从数据库中Record表里找到name=tempName的id
        Cursor cursor = helper.getReadableDatabase().rawQuery(
                "select id as _id,name from records where name =?", new String[]{tempName});
        //  判断是否有下一个
        return cursor.moveToNext();
    }

    /**
     * 关注4
     * 插入数据到数据库，即写入搜索字段到历史搜索记录
     */
    private void insertData(String tempName) {
        if (tempName != null) {
            db = helper.getWritableDatabase();
            db.execSQL("insert into records(name) values('" + tempName + "')");
            db.close();
        }
    }

    private int dp2px(float value) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, value, getResources().getDisplayMetrics());
    }

    /**
     * 点击返回后的操作，用于接口回调
     */
    public void setOnClickBack(bCallBack bCallBack) {
        this.bCallBack = bCallBack;
    }
}
