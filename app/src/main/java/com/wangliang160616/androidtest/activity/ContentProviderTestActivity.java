package com.wangliang160616.androidtest.activity;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.wangliang160616.androidtest.R;

import java.lang.annotation.ElementType;


public class ContentProviderTestActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int pid = android.os.Process.myPid();
        Log.v("out" , "新Activity的进程:"+pid);
        setContentView(R.layout.testlayout);
        //创建ContentResolver对象

        ContentResolver contentResolver = getContentResolver();
        Uri uri = Uri.parse("content://"+"com.wangliang160616.androidtest.hello/a/b/10");
        ContentValues contentValues = new ContentValues();
        //注册监听
        contentResolver.registerContentObserver(uri, true, new ContentObserver(null) {
            @Override
            public void onChange(boolean selfChange) {
                super.onChange(selfChange);
                Log.v("out" , "我知道你变了");
            }
        });
        Uri returnUri = contentResolver.insert(uri , contentValues);
        Log.v("out" , "返回的uri:"+returnUri);
    }
}
