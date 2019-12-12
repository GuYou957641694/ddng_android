package com.bigpumpkin.app.ddng_android.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.bigpumpkin.app.ddng_android.R;
import com.bigpumpkin.app.ddng_android.base.BaseActivity;
import com.bigpumpkin.app.ddng_android.fragment.Goods_Fragment;
import com.bigpumpkin.app.ddng_android.fragment.Message_Fragment;
import com.bigpumpkin.app.ddng_android.fragment.My_Farm_Fragment;
import com.bigpumpkin.app.ddng_android.fragment.My_Fragment;
import com.bigpumpkin.app.ddng_android.fragment.Shopping_Fragment;
import com.bigpumpkin.app.ddng_android.utils.IntentUtils;
import com.bigpumpkin.app.ddng_android.utils.NetWatchdog;
import com.hjq.toast.ToastUtils;

import java.util.ArrayList;
import java.util.List;

public class AllActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener {
    //定义一个变量，来标识是否退出
    private static boolean isExit = false;
    private static final String TAG = "AllActivity";
    private static final String FRAGMENT_ALL_HOME = "My_Farm_Fragment";
    private static final String FRAGMENT_ALL_BOOK_READER = "Goods_Fragment";
    private static final String FRAGMENT_ALL_ACTIVITY = "Message_Fragment";
    private static final String FRAGMENT_ALL_MY = "Shopping_Fragment";
    private static final String My_Fragment = "My_Fragment";
    private List<Fragment> fragmentList = new ArrayList<>();
    private BottomNavigationView bnvAllActivity;
    private FragmentManager mFragmentManager;
    private FragmentTransaction mFragmentTransaction;
    private Fragment mCurrentFragment;
    private My_Farm_Fragment my_farm_fragment;
    private Message_Fragment message_fragment;
    private Shopping_Fragment shopping_fragment;
    private Goods_Fragment goods_fragment;
    private My_Fragment my_fragment;
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            isExit = false;
        }
    };
    //网络监听
    private NetWatchdog netWatchdog;
    private RadioGroup radioGroup;

    @Override
    public int intiLayout() {
        return R.layout.activity_all;
    }

    @Override
    public void initView() {
        //监听网络
        // netWatchdog = new NetWatchdog(this);
        //  bnvAllActivity = findViewById(R.id.bnv_all_activity);
        my_farm_fragment = new My_Farm_Fragment();
        message_fragment = new Message_Fragment();
        my_fragment = new My_Fragment();
        shopping_fragment = new Shopping_Fragment();
        goods_fragment = new Goods_Fragment();
        mFragmentManager = getSupportFragmentManager();
        mFragmentTransaction = mFragmentManager.beginTransaction();
        mFragmentTransaction.add(R.id.frame, goods_fragment);
        radioGroup = findViewById(R.id.radioGroup);
        radioGroup.setOnCheckedChangeListener(this);//对radiogroup进行监听
        mFragmentTransaction.commit();
        mCurrentFragment = goods_fragment;
        //initBottomNavView();
       /* netWatchdog.setNetChangeListener(new NetWatchdog.NetChangeListener() {
            @Override
            public void onWifiTo4G() {

            }

            @Override
            public void on4GToWifi() {

            }

            @Override
            public void onNetDisconnected() {
                ToastUtil.showShort(AllActivity.this, "网络错误");
            }
        });
        netWatchdog.startWatch();*/
        ImageView imageView = findViewById(R.id.rbAdd);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentUtils.getIntents().Intent(AllActivity.this, My_FarmActivity.class, null);
            }
        });
    }

  /*  private void initBottomNavView() {
        bnvAllActivity.setItemIconTintList(null);
        bnvAllActivity.setSelectedItemId(R.id.item_bottom_nv_index);
        bnvAllActivity.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.item_bottom_nv_index:
                        try {
                            switchFragment(FRAGMENT_ALL_HOME).commit();
                        } catch (Exception e) {
                            Log.w(TAG, "onNavigationItemSelected: ", e);
                        }
                        break;
                    case R.id.item_bottom_nv_read:
                        try {
                            switchFragment(FRAGMENT_ALL_BOOK_READER).commit();
                        } catch (Exception e) {
                            Log.w(TAG, "onNavigationItemSelected: ", e);
                        }
                        break;
                    case R.id.item_bottom_nv_circle:
                        try {
                            switchFragment(FRAGMENT_ALL_ACTIVITY).commit();
                        } catch (Exception e) {
                            Log.w(TAG, "onNavigationItemSelected: ", e);
                        }
                        break;
                    case R.id.shopp:
                        try {
                            switchFragment(FRAGMENT_ALL_MY).commit();
                        } catch (Exception e) {
                            Log.w(TAG, "onNavigationItemSelected: ", e);
                        }
                        break;
                    case R.id.item_bottom_nv_my:
                        try {
                            switchFragment(My_Fragment).commit();
                        } catch (Exception e) {
                            Log.w(TAG, "onNavigationItemSelected: ", e);
                        }
                        break;
                    default:
                        return true;
                }
                return true;
            }
        });
    }*/


    public FragmentTransaction switchFragment(String tag) {
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        Fragment targetFragment = mFragmentManager.findFragmentByTag(tag);
        if (targetFragment == null) {
            switch (tag) {
             /*   case FRAGMENT_ALL_HOME:
                    targetFragment = my_farm_fragment;
                    break;*/
                case FRAGMENT_ALL_BOOK_READER:
                    targetFragment = goods_fragment;
                    break;
                case FRAGMENT_ALL_ACTIVITY:
                    targetFragment = message_fragment;
                    break;
                case FRAGMENT_ALL_MY:
                    targetFragment = shopping_fragment;
                    break;
                case My_Fragment:
                    targetFragment = my_fragment;
                    break;
                default:
                    mCurrentFragment.onPause();
                    break;
            }
        }

        if (!targetFragment.isAdded() && mFragmentManager.findFragmentByTag(tag) == null) {
            if (mCurrentFragment != null) {
                fragmentTransaction.hide(mCurrentFragment);
            }
            fragmentTransaction.add(R.id.frame, targetFragment, tag);
        } else {
            fragmentTransaction.hide(mCurrentFragment).show(targetFragment);
        }

        mCurrentFragment = targetFragment;
        return fragmentTransaction;
    }

    @Override
    public void initData() {
        Intent intent = getIntent();
        if (intent != null) {
            String action = intent.getAction();
            if (Intent.ACTION_VIEW.equals(action)) {
                Uri data = intent.getData();
                String sharetype = data.getQueryParameter("sharetype");
                ToastUtils.show(sharetype);
            }
        }

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exit();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void exit() {
        if (!isExit) {
            isExit = true;
            Toast.makeText(getApplicationContext(), "再按一次退出程序", Toast.LENGTH_SHORT).show();
            //利用handler延迟发送更改状态信息
            handler.sendEmptyMessageDelayed(0, 2000);
        } else {
            finish();
            System.exit(0);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        netWatchdog.stopWatch();
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.rb_home:
                try {
                    switchFragment(FRAGMENT_ALL_BOOK_READER).commit();
                } catch (Exception e) {
                }
                break;
            case R.id.rb_pond:
                try {
                    switchFragment(FRAGMENT_ALL_ACTIVITY).commit();
                } catch (Exception e) {
                }
                break;
            case R.id.rb_message:
                try {
                    switchFragment(FRAGMENT_ALL_MY).commit();
                } catch (Exception e) {
                }
                break;
            case R.id.rb_me:
                try {
                    switchFragment(My_Fragment).commit();
                } catch (Exception e) {
                }
                break;
        }
    }
}
