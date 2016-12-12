package com.wangliang160616.androidtest.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.wangliang160616.androidtest.R;
import com.wangliang160616.androidtest.fragments.Fragment_recycler1;
import com.wangliang160616.androidtest.fragments.Fragment_recycler2;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DragTestActivity extends AppCompatActivity {

    @BindView(R.id.activity_drag_test_viewpager)
    public ViewPager activityDragTestViewpager;
    private ArrayList<Fragment> mFragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drag_test);
        ButterKnife.bind(this);
        if(mFragments == null)
            mFragments = new ArrayList<>();
        mFragments.add(new Fragment_recycler1());
        mFragments.add(new Fragment_recycler2());
        activityDragTestViewpager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mFragments.get(position);
            }

            @Override
            public int getCount() {
                return 2;
            }
        });
    }
}
