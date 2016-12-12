package com.wangliang160616.androidtest.activity;
import android.support.multidex.MultiDexApplication;

import com.squareup.leakcanary.LeakCanary;
import com.wangliang160616.androidtest.utils.CrashHandler;

import butterknife.BuildConfig;
import butterknife.ButterKnife;

/**
 * Created by wangliang on 2016/7/10.
 */
public class WLApplication extends MultiDexApplication {

    @Override
    public void onCreate(){
        super.onCreate();
        int pid = android.os.Process.myPid();
        System.out.println("系统进程："+pid);
        if(LeakCanary.isInAnalyzerProcess(this)){
            return;
        }
        LeakCanary.install(this);
        ButterKnife.setDebug(BuildConfig.DEBUG);
        
        /*捕获全局异常*/
        CrashHandler crashHandler = CrashHandler.getInstance();
        crashHandler.init(this);
    }

}
