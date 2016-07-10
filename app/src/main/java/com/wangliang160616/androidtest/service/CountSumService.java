package com.wangliang160616.androidtest.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class CountSumService extends Service {
    public CountSumService() {
    }

    @Override
    public void onCreate(){
        super.onCreate();
        Log.v("out","onCreate()");
    }

    public int onStartCommand(Intent intent,int flag,int startId){
        if(intent==null){
            Log.v("out","onStartCommand()"+"intent is null");
        }
        Log.v("out","onStartCommand()");
        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        Log.v("out","onBind()");
        return new ServiceBinder();
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        Log.v("out","onDestroy()");
    }

    public class ServiceBinder extends Binder{

    }
}
