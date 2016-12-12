package com.wangliang160616.androidtest.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wangliang160616.androidtest.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by wangliang on 2016/6/16.
 */
public class Fragment_recycler2 extends Fragment {

    @BindView(R.id.fragment_recyclerview)
    RecyclerView fragmentRecyclerview;

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup container, Bundle savedInstanceState) {
        View view = layoutInflater.inflate(R.layout.fragment_recyclerview, null);
        ButterKnife.bind(this, view);
        fragmentRecyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        Recycler2Adapter adapter = new Recycler2Adapter();
        ArrayList<String> mData = new ArrayList<>();
        for(int i=0;i<5;i++){
            mData.add("你好，我是页面2的"+i+"号");
        }
        adapter.setData(mData);
        fragmentRecyclerview.setAdapter(adapter);
        return view;
    }

    /*create adapter*/
    private class Recycler2Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

        private ArrayList<String> mData;

        public void setData(ArrayList<String> mData){
            this.mData = mData;
        }
        public void addData(ArrayList<String> mData){
            this.mData.addAll(mData);
            notifyDataSetChanged();
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(getContext()).inflate(R.layout.item_launch_title , null);
            Recycler2ViewHolder viewHolder = new Recycler2ViewHolder(itemView);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            ((Recycler2ViewHolder)holder).mTv.setText(mData.get(position));
        }

        @Override
        public int getItemCount() {
            return mData.size();
        }
        /*create viewholder*/
        class Recycler2ViewHolder extends RecyclerView.ViewHolder{

            private TextView mTv;
            public Recycler2ViewHolder(View itemView) {
                super(itemView);
                mTv = (TextView) itemView.findViewById(R.id.item_launch_title_tv_title);
            }
        }
    }
}
