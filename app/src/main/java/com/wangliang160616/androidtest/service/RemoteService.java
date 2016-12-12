package com.wangliang160616.androidtest.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import com.wangliang160616.androidtest.model.IMyAidlInterface;
import com.wangliang160616.androidtest.model.student;


public class RemoteService extends Service {

    private student data;
    private int i=0;

    private IMyAidlInterface.Stub mBinder = new IMyAidlInterface.Stub(){

        @Override
        public void setStuInfo(student stu) throws RemoteException {
            if(stu!=null){
                data = stu;
            }
            Log.v("out","当前Service pid:"+android.os.Process.myPid());
            Log.v("out","姓名："+data.getStuName()+"/id："+data.getStuId());
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

        @Override
        public void setAge(int age) throws RemoteException {
            data.setStuId(age);
            Log.v("out","姓名："+data.getStuName()+"/id："+data.getStuId());
        }
    };

    public RemoteService() {
    }

    @Override
    public IBinder onBind(Intent intent) throws UnsupportedOperationException{
        return  mBinder;
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        Log.v("out","onDestroy()");
        stopSelf();
    }
}
