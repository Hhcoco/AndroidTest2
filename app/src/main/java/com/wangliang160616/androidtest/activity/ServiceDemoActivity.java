package com.wangliang160616.androidtest.activity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;

import com.wangliang160616.androidtest.R;
import com.wangliang160616.androidtest.service.CountSumService;

public class ServiceDemoActivity extends AppCompatActivity {

    private ServiceConnection conn;
    private CountSumService countSumService;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setContentView(R.layout.activity_service_demo);
        conn = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                Log.v("out","hhhhhhhhhhhhhhhhhhhhhh"+name.toString());
                IBinder temp = service;
                if(service!=null) {
                    countSumService = ((CountSumService.ServiceBinder) service).getService();
                    countSumService.test();
                }
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                countSumService = null;
                Log.v("out","服务已经断开");
            }
        };
        //mContext.bindService(new Intent(ServiceDemoActivity.this,CountSumService.class) ,conn,BIND_AUTO_CREATE);
        Intent intent = new Intent(ServiceDemoActivity.this,CountSumService.class);
        intent.setPackage("com.wangliang160616.androidtest");
        startService(intent);
    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
        //unbindService(conn);
        Intent intent = new Intent(ServiceDemoActivity.this,CountSumService.class);
        intent.setPackage("com.wangliang160616.androidtest");
        stopService(intent);
        Log.v("out","Activity onDestroy()");
    }
    @Override
    public boolean onKeyDown(int code ,KeyEvent event){
        if(code==KeyEvent.KEYCODE_BACK&&event.getRepeatCount()==2){
            finish();
            return true;
        }else return super.onKeyDown(code,event);
    }


}
