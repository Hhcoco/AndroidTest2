package com.wangliang160616.androidtest.activity;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wangliang160616.androidtest.R;
import com.wangliang160616.androidtest.view.WeekView;

public class CustomCalendarActivity extends AppCompatActivity implements View.OnClickListener {

    private WeekView mWeekView;
    private TextView mTvPre,mTvNext;
    private int month = 9;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_calendar);
        mWeekView = (WeekView) findViewById(R.id.weekview);
        mTvPre = (TextView) findViewById(R.id.pre);
        mTvNext = (TextView) findViewById(R.id.next);
        mTvNext.setOnClickListener(this);
        mTvPre.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.pre:
                mWeekView.setMonth(--month);
                break;
            case R.id.next:
                mWeekView.setMonth(++month);
                break;
        }
    }

    class CustomPagerAdapter<T extends View> extends PagerAdapter{

        private T [] views;

        public CustomPagerAdapter(T[] views){
            this.views = views;
        }

        @Override
        public Object instantiateItem(ViewGroup container , int position){
            ((ViewPager)container).addView(views[position% views.length] , 0);
            return views[position% views.length];
        }

        @Override
        public int getCount() {
            return Integer.MAX_VALUE;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == (View)object;
        }
        @Override
        public void destroyItem(ViewGroup container , int position , Object object){
            ((ViewPager)container).removeView((View)container);
        }
    }

}
