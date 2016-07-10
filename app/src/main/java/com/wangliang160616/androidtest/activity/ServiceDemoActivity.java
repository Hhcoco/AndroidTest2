package com.wangliang160616.androidtest.activity;

import android.content.ComponentName;
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
    private CountSumService.ServiceBinder countSumService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        conn = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                Log.v("out",name.toString());
                countSumService = (CountSumService.ServiceBinder) service;
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                countSumService = null;
            }
        };
        setContentView(R.layout.activity_service_demo);
        this.bindService(new Intent(this,CountSumService.class),conn,BIND_AUTO_CREATE);
    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
        unbindService(conn);
    }
    @Override
    public boolean onKeyDown(int code ,KeyEvent event){
        if(code==KeyEvent.KEYCODE_BACK&&event.getRepeatCount()==2){
            finish();
            return true;
        }else return super.onKeyDown(code,event);
    }


}
