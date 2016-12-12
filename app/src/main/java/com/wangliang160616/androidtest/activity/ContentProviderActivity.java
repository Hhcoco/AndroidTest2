package com.wangliang160616.androidtest.activity;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.wangliang160616.androidtest.R;

public class ContentProviderActivity extends AppCompatActivity {

    /*定义常量*/
    public static final Uri CONTENT_URI = Uri.parse("content://com.wangliang160616.androidtest.NoteContentProvider/notes");
    public int a;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_provider);
        handleBusiness();
    }

    /*handle business*/
    public void handleBusiness(){
        ContentValues values = new ContentValues();
        values.put("title" , "hello");
        values.put("content" , "i am content");
        Uri uri = getContentResolver().insert(CONTENT_URI , values);
        Log.v("out" , uri.toString());

    }

}
