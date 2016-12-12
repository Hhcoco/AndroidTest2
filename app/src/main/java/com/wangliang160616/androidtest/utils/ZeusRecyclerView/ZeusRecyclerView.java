package com.wangliang160616.androidtest.utils.ZeusRecyclerView;

import android.content.ContentValues;
import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by wangliang on 2016/10/8.
 */
public class ZeusRecyclerView extends RecyclerView {

    private Adapter mRealAdapter , mFinallyAdapter;
    /*触摸点坐标*/
    float startX = 0, startY = 0;
    /*因为设置了点击事件后，down事件不会进来，所以在这里重新定义*/
    private boolean isFirst = true;

    public ZeusRecyclerView(Context context) {
        super(context);
    }

    public ZeusRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ZeusRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    /**
     * A class that represents a fixed view in a list, for example a header at the top
     * or a footer at the bottom.
     */
    public class FixedViewInfo {
        /** The view to add to the list */
        public View view;
        /** The data backing the view. This is returned from {@link ListAdapter#getItem(int)}. */
        public Object data;
        /** <code>true</code> if the fixed view should be selectable in the list */
        public int itemType;
    }

    /*存储头尾部信息*/
    private ArrayList<FixedViewInfo> mHeaderViewInfos = new ArrayList<>();
    private ArrayList<FixedViewInfo> mFooterViewInfos = new ArrayList<>();
    /*点击事件和长按事件*/
    private OnClickListener onClickListener;
    private OnLongClickListener onLongClickListener;

    /*添加头部方法*/
    public void addHeaderView(View headerview , int itemType){

        FixedViewInfo fixedViewInfo = new FixedViewInfo();
        fixedViewInfo.view = headerview;
        fixedViewInfo.itemType = itemType;
        mHeaderViewInfos.add(fixedViewInfo);
        if(mFinallyAdapter != null)
            mFinallyAdapter.notifyDataSetChanged();

    }

    /*复写父类的该方法，将Adapter包装*/
    public void setAdapter(Adapter adapter , Context context){
        mFinallyAdapter = new ZeusAdapter(mHeaderViewInfos , mFooterViewInfos , adapter , context);
        if(onClickListener != null)
            ((ZeusAdapter)mFinallyAdapter).setOnClickListener(onClickListener);
        if(onLongClickListener != null)
            ((ZeusAdapter)mFinallyAdapter).setOnLongClickListener(onLongClickListener);
        super.setAdapter(mFinallyAdapter);
    }

    /*设置点击事件*/
    public void setOnClickListener(View.OnClickListener listener){
        this.onClickListener = listener;
    }
    /*设置长按事件*/
    public void setOnLongClickListener(View.OnLongClickListener listener){
        this.onLongClickListener = listener;
    }

    /*添加尾部方法*/
    public void addFooterView(View footerview , int itemType){

        final FixedViewInfo fixedViewInfo = new FixedViewInfo();
        fixedViewInfo.view = footerview;
        fixedViewInfo.itemType = itemType;
        mFooterViewInfos.add(fixedViewInfo);

        mRealAdapter = getAdapter();
        if(mRealAdapter != null){
            /*通知数据变化*/
            mRealAdapter.notifyDataSetChanged();
        }
    }

    /*判断当前一屏可以占多少个item*/
    public boolean canLoadMore(RecyclerView recyclerView){
        /*获取RecyclerView的高度,因为最多一屏，不会有太多效率上的损失，而且由于可能有多个itemType，所以循环遍历*/
        int height = recyclerView.getHeight();
        int totalHeight = 0;
        /*计算item的高度*/
        LinearLayoutManager linearLayoutManager1 = (LinearLayoutManager)getLayoutManager();
        for(int i=linearLayoutManager1.findFirstVisibleItemPosition(); i<=linearLayoutManager1.findLastVisibleItemPosition();i++) {
            if(recyclerView != null) {
                /*这个方法，当某个item完全不可见时返回的VH为空*/
                ViewHolder viewHolder = recyclerView.findViewHolderForAdapterPosition(i);
                if(viewHolder == null)
                    continue;
                View view = viewHolder.itemView;
                if(view != null)
                    totalHeight += view.getHeight();
            }
            //view.measure(0 , 0);
        }
        /*计算分割线的高度*/
        int eachDividerHeight = 0;
        if(recyclerView.getChildCount() >= 2){
            LinearLayoutManager linearLayoutManager = (LinearLayoutManager)getLayoutManager();
            int firstVisiblePosition = linearLayoutManager.findFirstVisibleItemPosition();
            eachDividerHeight = recyclerView.findViewHolderForAdapterPosition(firstVisiblePosition+1).itemView.getTop()-recyclerView.findViewHolderForAdapterPosition(firstVisiblePosition).itemView.getBottom();
        }

        /*计算总高度*/
        totalHeight += eachDividerHeight*(recyclerView.getChildCount()-1);
        /*如果当前所有item的高度加起来都小于屏幕高度，那说明不足一屏，不可用上拉加载更多*/
        if(totalHeight<height)
            return false;
        else return true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent e){

        boolean consume = false;

        switch (e.getAction()){
            case MotionEvent.ACTION_DOWN:
                startX = e.getX();
                startY = e.getY();
                consume = true;
                /*如果down方法进来了，那么将标志设置为false*/
                isFirst = false;
                Log.v("out" , "滑动前的条数："+mRealAdapter.getItemCount());
                break;
            case MotionEvent.ACTION_MOVE:

                /*因为设置了点击事件后，down事件不会进来，所以在这里重新定义*/
                if(isFirst){
                    startX = e.getX();
                    startY = e.getY();
                    /*这个方法在一个事件序列中只执行一次*/
                    isFirst = false;
                }

                /*移动距离超过默认设置*/
                if(Math.abs(e.getX() - startX)>ViewConfiguration.getTouchSlop() || Math.abs(e.getY() - startY)>ViewConfiguration.getTouchSlop()){
                    /*Y轴移动距离大于X轴距离*/
                    if(Math.abs(e.getX() - startX) < Math.abs(e.getY() - startY)){
                        /*是在向上滑动*/
                        if(e.getY()-startY<0){
                            if(mRealAdapter instanceof ZeusAdapter){
                                LinearLayoutManager linearLayoutManager = (LinearLayoutManager)getLayoutManager();
                                if(linearLayoutManager!=null) {
                                    if(canLoadMore(this)) {
                                        int visiblePosition = linearLayoutManager.findLastCompletelyVisibleItemPosition();
                                        if (visiblePosition == mRealAdapter.getItemCount() - 1) {
                                            ((ZeusAdapter) mRealAdapter).setIsLoading(true);
                                            Log.v("out", "滑动后的条数：" + mRealAdapter.getItemCount());
                                        }
                                    }else {
                                        Log.v("out" , "不可用上拉加载更多");
                                    }
                                }
                            }
                        }
                    }
                }
                consume = false;
                break;
            case MotionEvent.ACTION_UP:
                /*恢复标志位*/
                isFirst = true;
                break;
        }
        super.onTouchEvent(e);
        return consume;
    }

    /*设置自定义的上拉加载更多的view视图*/
    public void setPullUpView(View view){

    }


}
