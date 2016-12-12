package com.wangliang160616.androidtest.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.wangliang160616.androidtest.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EncryptActivity extends AppCompatActivity {

    @BindView(R.id.encrypt_tv)
    TextView encryptTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encrypt);
        ButterKnife.bind(this);
        handleBusiness();
    }

    /*handle business*/
    public void handleBusiness() {
        encryptTv.setText(getEncryptString1());
    }

    /*声明本地代码库*/
    static {
        System.loadLibrary("encryptLib");
    }

    /*声明本地方法，获得加密后字符串*/
    public native String getEncryptString1();

    public native String getEncryptString2();
}
