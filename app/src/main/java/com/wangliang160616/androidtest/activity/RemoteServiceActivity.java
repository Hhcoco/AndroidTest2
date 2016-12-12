package com.wangliang160616.androidtest.activity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.wangliang160616.androidtest.R;
import com.wangliang160616.androidtest.model.IMyAidlInterface;
import com.wangliang160616.androidtest.model.student;
import com.wangliang160616.androidtest.service.RemoteService;

public class RemoteServiceActivity extends AppCompatActivity {

    private IMyAidlInterface mService;
    private ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mService = IMyAidlInterface.Stub.asInterface(service);

            student data  = new student();
            data.setStuId(001);
            data.setStuName("wangliang");
            data.setStuNo("2022");
            data.setStuSex(true);
            try {
                mService.setStuInfo(data);
                mService.setAge(002);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            try {
                Thread.sleep(2000);
                unbindService(conn);
                Log.v("out","正在断开服务");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            try {
                Thread.sleep(20000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            try {
                mService.setStuInfo(data);
                mService.setAge(003);
            } catch (RemoteException e) {
                e.printStackTrace();
            }

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mService = null;
            Log.v("out","服务断开了");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remote_service);
        Log.v("out","当前Activity pid:"+android.os.Process.myPid());
        bindService(new Intent(this,RemoteService.class),conn,this.BIND_AUTO_CREATE);

    }
    @Override
    public void onDestroy(){
        unbindService(conn);
        super.onDestroy();
    }
}
