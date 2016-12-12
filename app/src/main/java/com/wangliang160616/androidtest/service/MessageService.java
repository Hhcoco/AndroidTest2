package com.wangliang160616.androidtest.service;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;

public class MessageService extends Service {

    private Handler mHandler;
    private Messenger mMessenger;


    public MessageService() {

        /*创建一个Handler处理来自客户端的Message*/
        mHandler = new Handler(){
            @Override
            public void handleMessage(Message msgfromclient){
                Log.v("out","当前线程:"+android.os.Process.myPid()+"收到客户端的数据为："+msgfromclient.what);
                /*得到来自客户端的信使*/
                Messenger messengerfromclient = msgfromclient.replyTo;
                Message messagetoclient = Message.obtain(null,123456789);
                try {
                    messengerfromclient.send(messagetoclient);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        };
        /*根据上面的Handler生成一个Messenger对象,Messenger:信使，指向一个Handler，可以通过
        * 这个Messenger来将Message发给其指向的Handler处理*/
        mMessenger = new Messenger(mHandler);

    }

    @Override
    public IBinder onBind(Intent intent) {
        /*通过这一步操作将Messenger返给客户端*/
        return mMessenger.getBinder();
    }
}
