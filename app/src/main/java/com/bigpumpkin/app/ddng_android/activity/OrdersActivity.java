package com.bigpumpkin.app.ddng_android.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.bigpumpkin.app.ddng_android.R;
import com.bigpumpkin.app.ddng_android.base.BaseActivity;
import com.bigpumpkin.app.ddng_android.orders.All_OrdersFragment;
import com.bigpumpkin.app.ddng_android.orders.For_CollectionFragment;
import com.bigpumpkin.app.ddng_android.orders.For_EvaluateFragment;
import com.bigpumpkin.app.ddng_android.orders.For_GoodsFragment;
import com.bigpumpkin.app.ddng_android.orders.For_SalesFragment;
import com.bigpumpkin.app.ddng_android.weight.TitleXML;

import java.util.ArrayList;
import java.util.List;

public class OrdersActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener, ViewPager.OnPageChangeListener, View.OnClickListener {

    private List<String> list_Title;
    private List<Fragment> fragmentList;
    private ViewPager vp;
    private TabLayout tablayout;
    private int index;
    private RadioGroup rg;


    @Override
    public int intiLayout() {
        return R.layout.activity_orders;
    }

    @Override
    public void initView() {
        new TitleXML(OrdersActivity.this, "我的订单", true).init().setListener(new TitleXML.TitleXMLClick() {
            @Override
            public void onImage() {
                finish();
            }
        });
        vp = findViewById(R.id.viewpager);
        rg = findViewById(R.id.wallpaper_rg);

       // tablayout = findViewById(R.id.tablayout);

        index = getIntent().getIntExtra("key", 0);

       // list_Title = new ArrayList<>();
        fragmentList = new ArrayList<>();
        fragmentList.add(new All_OrdersFragment());//全部订单
        fragmentList.add(new For_CollectionFragment());//代付款
        fragmentList.add(new For_GoodsFragment());//待收货
        fragmentList.add(new For_EvaluateFragment());//待评价
        fragmentList.add(new For_SalesFragment());//退款
//        list_Title.add("全部订单");
//        list_Title.add("待付款");
//        list_Title.add("待收货");
//        list_Title.add("待评价");
//        list_Title.add("退款/售后");
        //MyPagerAdapter myPagerAdapter = new MyPagerAdapter(getSupportFragmentManager(), OrdersActivity.this, fragmentList, list_Title);
        //viewpager.setAdapter(myPagerAdapter);
       /* tablayout.setupWithViewPager(viewpager);//此方法就是让tablayout和ViewPager联动


        viewpager.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragmentList.get(position);
            }

            @Override
            public int getCount() {
                return fragmentList.size();
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                super.destroyItem(container, position, object);
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return list_Title.get(position);
            }
        });
*/

       /* //跳到指定页面
        tablayout.getTabAt(index).select();*/
        rg.setOnCheckedChangeListener(this);
        vp.addOnPageChangeListener(this);
        //通过适配器把Fragment添加到主界面上
        //vp.setOffscreenPageLimit(3);
        vp.setAdapter(new Myadapter(getSupportFragmentManager(), fragmentList));
    }


    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
        //声明一个下标,并赋值
        int index = -1;
        switch (checkedId) {
            case R.id.rb_all:
                index = 0;
                break;
            case R.id.rb_payment:
                index = 1;
                break;
            case R.id.rb_cargo:
                index = 2;
                break;
            case R.id.rb_evaluation:
                index = 3;
                break;
            case R.id.rb_refund:
                index = 4;
                break;
        }
        //设置vp选项，与ViewButton关联起来
        vp.setCurrentItem(index);

    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        RadioButton radioButton = (RadioButton) rg.getChildAt(position);
        radioButton.setChecked(true);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onClick(View v) {

    }


    class Myadapter extends FragmentPagerAdapter {
        List<Fragment> fragmentList;

        public Myadapter(FragmentManager fm, List<Fragment> fragmentList) {
            super(fm);
            this.fragmentList = fragmentList;
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return (fragmentList == null) ? 0 : fragmentList.size();
        }

    }

    @Override
    public void initData() {

    }
}
