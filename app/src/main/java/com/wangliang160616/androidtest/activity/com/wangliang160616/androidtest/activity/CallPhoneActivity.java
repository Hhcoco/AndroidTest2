package com.wangliang160616.androidtest.activity.com.wangliang160616.androidtest.activity;

import android.content.Intent;
import android.hardware.Camera;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.wangliang160616.androidtest.R;
import com.wangliang160616.androidtest.view.MySurfaceView;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CallPhoneActivity extends AppCompatActivity {

    /*@BindView(R.id.call_phone_tv)
    TextView callPhoneTv;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(new MySurfaceView(this));
        setContentView(R.layout.activity_call_phone);
        //ButterKnife.bind(this);
    }



//    @OnClick(R.id.call_phone_tv)
//    public void onClick() {
//    }
}
