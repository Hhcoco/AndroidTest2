package com.wangliang160616.androidtest.utils.ZeusRecyclerView;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.wangliang160616.androidtest.R;

import java.util.ArrayList;

/**
 * Created by wangliang on 2016/10/8.
 */
public class ZeusAdapter extends RecyclerView.Adapter {

    private ArrayList<ZeusRecyclerView.FixedViewInfo> mHeaderViewInfos , mFooterViewInfos;
    /*原始Item*/
    private RecyclerView.Adapter adapter;
    /*头部标志为0 ， 中间部分为1 ， 尾部标志为2*/
    private static final int FLAG_HEADER_VIEW = 0 , FLAG_VIEW = 1 , FLAG_FOOTER_VIEW = 2 ;
    /*上拉刷新标志为0x001*/
    private static final int PULL_UP_VIEW_TYPE = 0x001;
    /*如果要显示，该值为1，itemcount则加1，不显示为0 ,默认不显示*/
    private int isShowPullUpView = 0;
    /*是否正在loading中 , 默认不显示*/
    private boolean isLoadingPullUpView = false;
    /*上拉加载更多的View*/
    private View pullupView;
    /*监听器*/
    private View.OnClickListener onClickListener;
    private View.OnLongClickListener onLongClickListener;

    public ZeusAdapter() {
    }

    public ZeusAdapter(ArrayList<ZeusRecyclerView.FixedViewInfo> mHeaderViewInfos ,
                       ArrayList<ZeusRecyclerView.FixedViewInfo> mFooterViewInfos ,
                       RecyclerView.Adapter mActualAdapter ,
                       Context context) {
        if(mHeaderViewInfos != null)
            this.mHeaderViewInfos = mHeaderViewInfos;
        else this.mHeaderViewInfos = new ArrayList<>();
        if(mFooterViewInfos != null)
            this.mFooterViewInfos = mFooterViewInfos;
        else this.mFooterViewInfos = new ArrayList<>();
        if(mActualAdapter != null){
            /*获取到真正item的adapter*/
            adapter = mActualAdapter;
            /*添加上拉加载更多的View,后面需要优化为代码添加View，不引用Context以免造成内存泄漏*/
            pullupView = LayoutInflater.from(context).inflate(R.layout.default_load_more, null);
        }
    }

    /*两个View拼接方法*/
    public static View add2View(View originalView , View slideView){
        /**/
        return originalView;
    }

    /*设置监听器*/
    public void setOnClickListener(View.OnClickListener listener){
        this.onClickListener = listener;
    }
    public void setOnLongClickListener(View.OnLongClickListener listener){
        this.onLongClickListener = listener;
    }

    /*对外接口暴露头尾部信息*/
    public int getHeaderCount(){
        if(mHeaderViewInfos != null)
            return mHeaderViewInfos.size();
        else return 0;
    }
    public int getFooterCount(){
        if(mFooterViewInfos != null)
            return mFooterViewInfos.size();
        else return 0;
    }

    /*添加上拉加载更多的View*/
    public void setPullUpView(View pullUpView){

    }
    /*设置显示加载View*/
    public void setIsLoading(boolean isLoading){
        this.isLoadingPullUpView = isLoading;
        if(isLoading)
            isShowPullUpView = 1;
        else isShowPullUpView = 0;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        /*因为头尾部的一般很少，所以为了效率先判断这两个*/
        for(ZeusRecyclerView.FixedViewInfo fixedViewInfo:mHeaderViewInfos){
            if(fixedViewInfo.itemType == viewType)
                //return adapter.onCreateViewHolder(parent , viewType);
                return new RecyclerView.ViewHolder(fixedViewInfo.view) {};
        }
        /*尾部*/
        for(ZeusRecyclerView.FixedViewInfo fixedViewInfo:mFooterViewInfos){
            if(fixedViewInfo.itemType == viewType)
                return new RecyclerView.ViewHolder(fixedViewInfo.view) {};
        }
        /*上拉加载更多的VH*/
        if(viewType == PULL_UP_VIEW_TYPE)
            if(pullupView != null)
                return new RecyclerView.ViewHolder(pullupView) {};
        /*中间的item*/
        {
            ZeusViewHolder viewHolder = (ZeusViewHolder) adapter.onCreateViewHolder(parent , viewType);
            View view1 = viewHolder.itemView;

            if(viewHolder.getItemView() != null){
                viewHolder.getItemView().setFocusable(false);
                if(onClickListener != null) {
                    viewHolder.getItemView().setOnClickListener(onClickListener);
                }
            }
            return viewHolder;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(position < mHeaderViewInfos.size() + adapter.getItemCount() && position >mHeaderViewInfos.size()-1){
            adapter.onBindViewHolder(holder , position-mHeaderViewInfos.size());
        }
    }

    @Override
    public int getItemCount() {
        /*总数为头部数量+实际item数量+尾部数量+上拉加载更多时显示的数量*/
        return adapter.getItemCount() + mHeaderViewInfos.size() + mFooterViewInfos.size()+isShowPullUpView;
    }
    @Override
    public int getItemViewType(int position){
        /*如果position小于头部数量，则是在头部 , 其他同理，因为可能有多个头部，所以不能这样写*/
        if(position < mHeaderViewInfos.size()){
            int aa = mHeaderViewInfos.get(position).itemType;
            return aa;
        }
        /*中间*/
        else if(position < mHeaderViewInfos.size() + adapter.getItemCount())
            return adapter.getItemViewType(position-mHeaderViewInfos.size());
        /*上拉加载更多*/
        else if(position >= mHeaderViewInfos.size()+adapter.getItemCount()+mFooterViewInfos.size()){
            /**/
            return PULL_UP_VIEW_TYPE;
        }
        /*尾部*/
        else
        {
            int j = position-mHeaderViewInfos.size()-adapter.getItemCount();
            int a = mFooterViewInfos.get(j).itemType;
            return  a;
        }
    }
}
