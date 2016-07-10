package com.wangliang160616.androidtest.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.wangliang160616.androidtest.R;
import com.wangliang160616.androidtest.adapter.SimpleAdapter;

import butterknife.BindView;

public class LaunchActivity extends AppCompatActivity {

    private ListView mListView;

    String [] mData = {"ListView上滑隐藏，下滑显示","Service Demo"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);
        initView();
        SimpleAdapter simpleAdapter = new SimpleAdapter(this,mData,R.layout.item_launch_title);
        mListView.setAdapter(simpleAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                switch (position){
                    case 0:
                        intent.setClass(LaunchActivity.this,MainActivity.class);
                        break;
                    case 1:
                        intent.setClass(LaunchActivity.this,ServiceDemoActivity.class);
                        break;
                }
                startActivity(intent);
            }
        });
    }
    /*载入视图*/
    public void initView(){
        mListView = (ListView) findViewById(R.id.launch_listview);
    }
}
