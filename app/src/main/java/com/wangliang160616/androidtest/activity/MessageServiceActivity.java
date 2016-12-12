package com.wangliang160616.androidtest.activity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.wangliang160616.androidtest.R;
import com.wangliang160616.androidtest.service.MessageService;

public class MessageServiceActivity extends AppCompatActivity {

    private ServiceConnection conn;
    private Messenger mMessengerService,mMessengerClient;
    private Message msgToService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_service);

        mMessengerClient = new Messenger(new Handler(){
            @Override
            public void handleMessage(Message msgfromservice){
                Log.v("out","收到了来自服务器端的数据:"+msgfromservice.what);
            }
        });

        /*为了能让服务器也给客户端发送数据，我们需要将客户端的信使传递给服务器端，服务器端将信使传递给
        * 客户端的方式是通过Binder，客户端将信使传递给服务器端的方式是将信使封装到Message对象中*/
        msgToService = Message.obtain(null,123321);
        msgToService.replyTo = mMessengerClient;

        /*首先创建ServiceConnection，并从中获取到服务器端的Messenger*/
        conn = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                /*得到的是服务端的信使,我们通过这个信使将数据发给服务端*/
                mMessengerService = new Messenger(service);
                try {
                    mMessengerService.send(msgToService);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {

            }
        };
        bindService(new Intent(MessageServiceActivity.this, MessageService.class),conn,this.BIND_AUTO_CREATE);

    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        unbindService(conn);
    }
}
