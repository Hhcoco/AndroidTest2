package com.wangliang160616.androidtest.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
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
public class Fragment_recycler1 extends Fragment {

    @BindView(R.id.fragment_recyclerview)
    RecyclerView fragmentRecyclerview;
    @BindView(R.id.fragment_recyclerview_right)
    RecyclerView fragmentRecyclerviewRight;
    private ItemTouchHelper itemTouchHelper;

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup container, Bundle savedInstanceState) {
        View view = layoutInflater.inflate(R.layout.fragment_recyclerview, null);
        ButterKnife.bind(this, view);

/*        itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(ItemTouchHelper.DOWN , 0) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                int fromPosition = viewHolder.getAdapterPosition();
                int toPosition = target.getAdapterPosition();
                Log.v("out" , "From:"+fromPosition+"To:"+toPosition);
                return true;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {

            }
        });*/

        itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.Callback() {
            @Override
            public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
                final int dragFlag = ItemTouchHelper.DOWN | ItemTouchHelper.UP | ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
                final int swipeFlag = 0;
                return makeMovementFlags(dragFlag, swipeFlag);
            }

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                int fromPosition = viewHolder.getAdapterPosition();
                int toPosition = target.getAdapterPosition();
                Log.v("out", "From:" + fromPosition + "To:" + toPosition);
                return true;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {

            }
        });

        fragmentRecyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        Recycler1Adapter adapter = new Recycler1Adapter();
        ArrayList<String> mData = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            mData.add("你好，我是页面1的" + i + "号");
        }
        adapter.setData(mData);
        fragmentRecyclerview.setAdapter(adapter);
        fragmentRecyclerview.bringToFront();
        //itemTouchHelper.attachToRecyclerView(fragmentRecyclerview);

        /*右边列表*/
        fragmentRecyclerviewRight.setLayoutManager(new LinearLayoutManager(getContext()));
        Recycler1Adapter adapterRight = new Recycler1Adapter();
        adapterRight.setData(mData);
        fragmentRecyclerviewRight.setAdapter(adapterRight);
        //itemTouchHelper.attachToRecyclerView(fragmentRecyclerviewRight);

        return view;
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
            View itemView = LayoutInflater.from(getContext()).inflate(R.layout.item_launch_title, null);
            Recycler1ViewHolder viewHolder = new Recycler1ViewHolder(itemView);
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    v.setTranslationX(200);
                }
            });
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            ((Recycler1ViewHolder) holder).mTv.setText(mData.get(position));
        }

        @Override
        public int getItemCount() {
            return mData.size();
        }

        /*create viewholder*/
        class Recycler1ViewHolder extends RecyclerView.ViewHolder {

            private TextView mTv;

            public Recycler1ViewHolder(View itemView) {
                super(itemView);
                mTv = (TextView) itemView.findViewById(R.id.item_launch_title_tv_title);
            }
        }
    }
}
