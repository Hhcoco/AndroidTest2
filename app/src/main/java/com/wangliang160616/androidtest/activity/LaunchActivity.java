package com.wangliang160616.androidtest.activity;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.PrintData;
import com.wangliang160616.androidtest.R;
import com.wangliang160616.androidtest.activity.com.wangliang160616.androidtest.activity.CallPhoneActivity;
import com.wangliang160616.androidtest.adapter.SimpleAdapter;
import com.wangliang160616.androidtest.service.RemoteService;


import java.util.HashMap;

import butterknife.BindView;

public class LaunchActivity extends AppCompatActivity {


    private ListView mListView;
    public int a;
    @PrintData
    public String b;

    String [] mData = {"ListView上滑隐藏，下滑显示","Service Demo","Remote Service","Message Service"
    ,"RecyclerView点击失效测试","scrollto/scrollby","侧滑删除" , "RecyclerView侧滑删除" , "自定义ViewGroup"
    ,"android下几个常用路径的比较" , "单元测试" , "ContentProvider" , "GreenDao测试"
    ,"自定义日历" , "拖拽测试" , "异常测试" , "拖拽测试" , "跨界面拖拽" , "RecyclerView测试" ,
    "点击和触摸冲突测试" , "Jni加密测试" , "ContentProvider测试" ,"新权限测试"};

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
                    case 2:
                        intent.setClass(LaunchActivity.this,RemoteServiceActivity.class);
                        break;
                    case 3:
                        intent.setClass(LaunchActivity.this,MessageServiceActivity.class);
                        break;
                    case 4:
                        intent.setClass(LaunchActivity.this,RecyclerViewClickTestActivity.class);
                        break;
                    case 5:
                        intent.setClass(LaunchActivity.this,ScrollToOrByActivity.class);
                        break;
                    case 6:
                        intent.setClass(LaunchActivity.this,SwipeDeleteActivity.class);
                        break;
                    case 7:
                        intent.setClass(LaunchActivity.this,RVDeleteActivity.class);
                        break;
                    case 8:
                        intent.setClass(LaunchActivity.this,ViewGroupActivity.class);
                        break;
                    case 9:
                        intent.setClass(LaunchActivity.this,FilePathActivity.class);
                        break;
                    case 10:
                        intent.setClass(LaunchActivity.this,UnitTestActivity.class);
                        intent.putExtra("id" , 123);
                        break;
                    case 11:
                        intent.setClass(LaunchActivity.this, ContentProviderActivity.class);
                        break;
                    case 12:
                        intent.setClass(LaunchActivity.this, GreenDaoActivity.class);
                        break;
                    case 13:
                        intent.setClass(LaunchActivity.this, CustomCalendarActivity.class);
                        break;
                    case 14:
                        intent.setClass(LaunchActivity.this, DragActivity.class);
                        break;
                    case 15:
                        intent.setClass(LaunchActivity.this, CrashTestActivity.class);
                        break;
                    case 16:
                        intent.setClass(LaunchActivity.this, DragTestActivity.class);
                        break;
                    case 17:
                        intent.setClass(LaunchActivity.this, SpanDragActivity.class);
                        break;
                    case 18:
                        intent.setClass(LaunchActivity.this, RecyclerViewTestActivity.class);
                        break;
                    case 19:
                        intent.setClass(LaunchActivity.this, ClickAndTouchTestActivity.class);
                        break;
                    case 20:
                        intent.setClass(LaunchActivity.this, EncryptActivity.class);
                        break;
                    case 21:
                        intent.setClass(LaunchActivity.this, ContentProviderTestActivity.class);
                        break;
                    case 22:
                        intent.setClass(LaunchActivity.this, CallPhoneActivity.class);
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
