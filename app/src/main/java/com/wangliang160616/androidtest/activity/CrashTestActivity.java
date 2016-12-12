package com.wangliang160616.androidtest.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.wangliang160616.androidtest.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CrashTestActivity extends AppCompatActivity {

    @BindView(R.id.activity_crash_test_tv)
    TextView activityCrashTestTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crash_test);
        ButterKnife.bind(this);
        Log.v("out" , "change test111");
    }

    @OnClick(R.id.activity_crash_test_tv)
    public void onClick() {
        throw new RuntimeException("异常测试");
    }
}
