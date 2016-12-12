package com.wangliang160616.androidtest.utils;

import android.content.Context;
import android.os.Process;
import android.util.Log;

/**
 * Created by wangliang on 2016/9/22.
 */
public class CrashHandler implements Thread.UncaughtExceptionHandler {

    private Thread.UncaughtExceptionHandler mDefaultCrashHandler;
    private Context mContext;

    /*私有构造函数*/
    private CrashHandler(){
    }
    private static class Inner{
        private final static CrashHandler Instance = new CrashHandler();
    }
    public final static CrashHandler getInstance(){
        return Inner.Instance;
    }

    @Override
    public void uncaughtException(Thread thread, Throwable ex) {

        Log.v("out" , "CrashData:"+ex.toString());
        if(mDefaultCrashHandler != null){
            Log.v("out" , "要你管，我要自己处理异常。");
            mDefaultCrashHandler.uncaughtException(thread , ex);
        }else {
            Log.v("out" , "自行处理异常");
            Process.killProcess(Process.myPid());
        }

    }

    /*initialize*/
    public void init(Context context){
        mContext = context.getApplicationContext();
        mDefaultCrashHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
    }


}
