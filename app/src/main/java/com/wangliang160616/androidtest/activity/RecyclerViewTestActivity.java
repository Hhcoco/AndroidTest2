package com.wangliang160616.androidtest.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.wangliang160616.androidtest.R;
import com.wangliang160616.androidtest.utils.ZeusRecyclerView.ZeusAdapter;
import com.wangliang160616.androidtest.utils.ZeusRecyclerView.ZeusRecyclerView;
import com.wangliang160616.androidtest.utils.ZeusRecyclerView.ZeusViewHolder;

import java.util.ArrayList;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecyclerViewTestActivity extends AppCompatActivity {

    private Context mContext;
    @BindView(R.id.activity_recycler_view_test_rv)
    ZeusRecyclerView activityRecyclerViewTestRv;
    /*侧滑出来的View*/
    private View mSlideView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_test);
        mContext = this;
        ButterKnife.bind(this);
        activityRecyclerViewTestRv.setLayoutManager(new LinearLayoutManager(mContext));
        Recycler1Adapter adapter = new Recycler1Adapter();
        ArrayList<String> mData = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            mData.add("你好，我是页面1的" + i + "号");
        }
        adapter.setData(mData);

        activityRecyclerViewTestRv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext , "点击了" , Toast.LENGTH_SHORT).show();
            }
        });
        activityRecyclerViewTestRv.setAdapter(adapter , mContext);

        View headerView2 = LayoutInflater.from(mContext).inflate(R.layout.item_launch_title, null);
        activityRecyclerViewTestRv.addHeaderView(headerView2 , 0x124);
        View headerView3 = LayoutInflater.from(mContext).inflate(R.layout.item_launch_title, null);
        activityRecyclerViewTestRv.addHeaderView(headerView3 , 0x125);
        View headerView4 = LayoutInflater.from(mContext).inflate(R.layout.item_launch_title, null);
        activityRecyclerViewTestRv.addFooterView(headerView4 , 0x126);
        new Random().nextInt();

    }

    /*add slideView*/
    private View addSlideView(View originalView , View slideView){
        /*首先创建一个LinearLayout*/
        LinearLayout mRootLinearLayout = new LinearLayout(mContext);
        //mRootLinearLayout.setLayoutParams(new);
        return null;
    }

    /*create adapter*/
    private class Recycler1Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        private ArrayList<String> mData;

        public void setData(ArrayList<String> mData) {
            this.mData = mData;
        }

        public void addData(ArrayList<String> mData) {
            this.mData.addAll(mData);
            notifyDataSetChanged();
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(mContext).inflate(R.layout.item_launch_title, null);
            itemView.getRootView();
            /*如果侧滑View不为空，那么添加*/
            if(mSlideView != null){
                return new ZeusViewHolder(ZeusAdapter.add2View(itemView , mSlideView));
            }
            ZeusViewHolder viewHolder = new ZeusViewHolder(itemView);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            //((Recycler1ViewHolder) holder).mTv.setText(mData.get(position));
            ((ZeusViewHolder) holder).setText(R.id.item_launch_title_tv_title , mData.get(position));
        }

        @Override
        public int getItemCount() {
             return mData.size();
        }

        /*create viewholder*/
        /*class Recycler1ViewHolder extends RecyclerView.ViewHolder {

            private TextView mTv;

            public Recycler1ViewHolder(View itemView) {
                super(itemView);
                mTv = (TextView) itemView.findViewById(R.id.item_launch_title_tv_title);
            }
        }*/
    }
}
