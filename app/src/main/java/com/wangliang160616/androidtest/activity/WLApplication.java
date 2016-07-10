package com.wangliang160616.androidtest.activity;

import android.app.Application;

import butterknife.BuildConfig;
import butterknife.ButterKnife;

/**
 * Created by wangliang on 2016/7/10.
 */
public class WLApplication extends Application {

    @Override
    public void onCreate(){
        super.onCreate();
        ButterKnife.setDebug(BuildConfig.DEBUG);
    }

}
