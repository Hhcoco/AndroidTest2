package com.wangliang160616.androidtest.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;

public class CountSumService extends Service {

    private int i=0;
    private ServiceBinder serviceBinder = new ServiceBinder();

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
        Message msg = Message.obtain();
        Handler handler = new Handler();
        handler.handleMessage(msg);
        handler.sendMessage(msg);

        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.v("out","onBind()");
        return serviceBinder;
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        Log.v("out","onDestroy()");
        stopSelf();
    }

    public void test(){
        Log.v("out","service test demo");
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Log.v("out","I："+i);
                    i++;
                }
            }
        }).start();
    }

    public class ServiceBinder extends Binder{
        public CountSumService getService(){
            return CountSumService.this;
        }
    }
}
