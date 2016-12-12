package com.wangliang160616.androidtest.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.wangliang160616.androidtest.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ClickAndTouchTestActivity extends AppCompatActivity {

    @BindView(R.id.activity_click_and_touch_test_rl)
    RelativeLayout activityClickAndTouchTestRl;
    @BindView(R.id.activity_click_and_touch_test_rl_parent)
    RelativeLayout activityClickAndTouchTestRlParent;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_click_and_touch_test);
        mContext = this;
        ButterKnife.bind(this);
        activityClickAndTouchTestRl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "点击事件", Toast.LENGTH_SHORT).show();
            }
        });
        activityClickAndTouchTestRlParent.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.v("out", "触摸事件");
                return false;
            }
        });
        /*activityClickAndTouchTestRl.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.v("out", "触摸事件");
                return true;
            }
        });*/
    }
}
