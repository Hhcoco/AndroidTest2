package com.wangliang160616.androidtest.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.wangliang160616.androidtest.R;

public class UnitTestActivity extends AppCompatActivity {

    private TextView mTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unit_test);
        mTv = (TextView) findViewById(R.id.activity_unit_test_tv_id);
        mTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UnitTestActivity.this , SecondActivity.class));
            }
        });
    }
}
