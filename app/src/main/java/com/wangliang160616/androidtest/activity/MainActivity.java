package com.wangliang160616.androidtest.activity;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.wangliang160616.androidtest.R;
import com.wangliang160616.androidtest.fragments.Fragment_home;
import com.wangliang160616.androidtest.fragments.Fragment_test;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private ViewPager mViewpager;
    private TextView mTextHome,mTextTest;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
        mTextHome = (TextView) findViewById(R.id.btn_1);
        mTextTest = (TextView) findViewById(R.id.btn_2);
        mTextTest.setOnClickListener(this);
        mTextHome.setOnClickListener(this);
        mViewpager = (ViewPager) findViewById(R.id.viewpager);
        Fragment_home fragmentHome = new Fragment_home();
        Fragment_test fragmentTest = new Fragment_test();
        List<Fragment> mFragments = new ArrayList<Fragment>();
        mFragments.add(fragmentHome);
        mFragments.add(fragmentTest);
        mViewpager.setAdapter(new FragmentAdapter(getSupportFragmentManager(),mFragments));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_1:
                grayAllBtn();
                mTextHome.setTextColor(getResources().getColor(R.color.color_F9AED1));
                Drawable drawableMe = ContextCompat.getDrawable(context,R.drawable.home_ok);
                drawableMe.setBounds(0, 0, drawableMe.getMinimumWidth(), drawableMe.getMinimumHeight());
                mTextHome.setCompoundDrawables(null,drawableMe,null,null);
                mViewpager.setCurrentItem(0,false);
                break;
            case R.id.btn_2:
                grayAllBtn();
                mTextTest.setTextColor(getResources().getColor(R.color.color_F9AED1));
                Drawable drawable = ContextCompat.getDrawable(context,R.drawable.home_ok);
                drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                mTextTest.setCompoundDrawables(null,drawable,null,null);
                mViewpager.setCurrentItem(1,false);
                break;
        }
    }

    public void grayAllBtn(){
        mTextHome.setTextColor(getResources().getColor(R.color.color_AEAEAE));
        mTextTest.setTextColor(getResources().getColor(R.color.color_AEAEAE));
        Drawable drawableMe = ContextCompat.getDrawable(context,R.drawable.home_no);
        drawableMe.setBounds(0, 0, drawableMe.getMinimumWidth(), drawableMe.getMinimumHeight());
        mTextHome.setCompoundDrawables(null,drawableMe,null,null);
        mTextTest.setCompoundDrawables(null,drawableMe,null,null);
    }

    /*create viewpager adapter*/
    public class FragmentAdapter extends FragmentPagerAdapter {

        private List<Fragment> mFragments;

        public FragmentAdapter(FragmentManager fm, List<Fragment> fragments) {
            super(fm);
            this.mFragments = fragments;
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }
    }
}
