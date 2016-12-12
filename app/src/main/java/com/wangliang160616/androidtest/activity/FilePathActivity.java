package com.wangliang160616.androidtest.activity;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.wangliang160616.androidtest.R;

import java.io.IOException;

public class FilePathActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_path);
        String mAbsolutePath = Environment.getExternalStorageDirectory().getAbsolutePath();
        String mESD = Environment.getExternalStorageState();
        String mCachePath = Environment.getDataDirectory().getPath();
        String SDFilePath = this.getExternalFilesDir("").getPath();
        String SDCachePath = this.getExternalCacheDir().getPath();
        try {
            String mInnerCardPath = getFilesDir().getCanonicalPath();
            String mInnerCachePath = getCacheDir().getAbsolutePath();
            Log.v("out" , mAbsolutePath+"@"+mESD+"@"+mCachePath+"@"+mInnerCardPath+"@"+mInnerCachePath
            +"@"+SDFilePath+"@"+SDCachePath);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
