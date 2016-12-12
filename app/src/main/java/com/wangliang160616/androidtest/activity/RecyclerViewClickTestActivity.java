package com.wangliang160616.androidtest.activity;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wangliang160616.androidtest.R;

import java.util.ArrayList;


public class RecyclerViewClickTestActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<String> mData = new ArrayList<String>();
    private Context context;
    private TextView mTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_click_test);
        context = this;

        mTv = (TextView) findViewById(R.id.clickme);
        final Handler handler = new Handler(){
            @Override
            public void handleMessage(Message msg){
                if(msg.what==1){
                    mTv.setText("wo  草");
                }
            }
        };
        mTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v("out","666");
                        handler.sendEmptyMessage(1);

                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        mData.add("A");
        mData.add("B");
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new RecyclerView.Adapter() {
            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View itemView = LayoutInflater.from(context).inflate(R.layout.item_recyclerview,null);
                ViewHolder VH = new ViewHolder(itemView);
                return VH;
            }

            @Override
            public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {


                ((ViewHolder)holder).mTv.setText(mData.get(position));

                ((ViewHolder)holder).mTv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ((ViewHolder)holder).mTv.setText("我操");
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    Thread.sleep(2000);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                        }).start();

                    }
                });
            }

            @Override
            public int getItemCount() {
                return mData.size();
            }
            class ViewHolder extends RecyclerView.ViewHolder{

                private TextView mTv;

                public ViewHolder(View itemView) {
                    super(itemView);
                    mTv = (TextView) itemView.findViewById(R.id.item_recyclerview);
                }
            }
        });
    }
}
