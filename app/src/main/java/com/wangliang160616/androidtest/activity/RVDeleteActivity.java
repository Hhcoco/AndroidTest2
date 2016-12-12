package com.wangliang160616.androidtest.activity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wangliang160616.androidtest.R;
import com.wangliang160616.androidtest.view.ZeusRecyclerView;

public class RVDeleteActivity extends AppCompatActivity {

    private ZeusRecyclerView recyclerView;
    private Context context;
    private int count = 20;
    private float itemViewHeight;
    private boolean isRefresh = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rvdelete);
        context = this;
        recyclerView = (ZeusRecyclerView) findViewById(R.id.rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new RecyclerView.Adapter() {
            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                if(1 == viewType) {
                    View itemView = LayoutInflater.from(context).inflate(R.layout.activity_swipe_delete, null);
                    itemViewHeight = itemView.getHeight();
                    MyViewHolder viewHolder = new MyViewHolder(itemView);
                    return viewHolder;
                }else if(0 == viewType){
                    View itemView = LayoutInflater.from(context).inflate(R.layout.footer, null);
                    MyViewHolder viewHolder = new MyViewHolder(itemView);
                    if(!isRefresh){
                        itemView.setVisibility(View.GONE);
                    }else itemView.setVisibility(View.VISIBLE);
                    return viewHolder;
                }
                return  null;
            }

            @Override
            public int getItemViewType(int position){
                if(position+1 == getItemCount()){
                    return 0;
                }else return 1;
            }

            @Override
            public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
                if(position<count-1) {
                    ((MyViewHolder) holder).mTv1.setText(position + "");
                    ((MyViewHolder) holder).mTv2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            count--;
                            notifyDataSetChanged();
                        }
                    });
                }
            }

            @Override
            public int getItemCount() {
                return count;
            }

    });
        recyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView , int dx ,int dy){
                super.onScrolled(recyclerView , dx , dy);
                int mLastView =((LinearLayoutManager) recyclerView.getLayoutManager()).findLastCompletelyVisibleItemPosition();
                int itemCount = recyclerView.getAdapter().getItemCount();
                int sum = ((LinearLayoutManager) recyclerView.getLayoutManager()).findLastVisibleItemPosition();

                if(mLastView+1 == itemCount && dy>0 ){
                    Log.v("out" , "到底部了");
                    isRefresh = true;
                    recyclerView.getAdapter().notifyDataSetChanged();
                    /*count+=10;
                    recyclerView.getAdapter().notifyDataSetChanged();*/
                }
            }
        });
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView mTv1,mTv2;
        public LinearLayout linearLayout;
        public MyViewHolder(View itemView) {
            super(itemView);
            mTv1 = (TextView) itemView.findViewById(R.id.rv_tv_1);
            mTv2 = (TextView) itemView.findViewById(R.id.tv);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.ll);
        }
    }
}
