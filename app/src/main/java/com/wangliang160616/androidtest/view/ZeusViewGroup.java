package com.wangliang160616.androidtest.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wangliang160616.androidtest.R;
import com.wangliang160616.androidtest.utils.PostPosition;

/**
 * Created by wangliang on 2016/8/7.
 */
public class ZeusViewGroup extends LinearLayout {

    private int[] mDrawables , mOldDrawables;
    private String [] mTxts ;
    private float marginL,marginT,marginR,marginB , density;
    private int color , checkedColor , textSize = 14, mDrawableToTxtPadding = 5 , mWidth , mChildWidth ;
    private TextView[] mViews;
    private int prePosition = 0;
    private PostPosition postPosition;

    public ZeusViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ZeusViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }
    public ZeusViewGroup(Context context) {
        super(context);
        init();
    }

    public void init(){
        this.setOrientation(LinearLayout.HORIZONTAL);
        WindowManager windowManager = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics metrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(metrics);
        density = metrics.density;
    }

    /*设置数据源，分别为图片数组，文字数组*/
    public void setData(int [] drawables , int [] olddrawables , String [] txts ){
        if(drawables.length > 0 && txts.length >0){
            this.mDrawables = drawables;
            this.mTxts = txts;
            this.mOldDrawables = olddrawables;
            if(drawables.length == txts.length)
                this.setWeightSum(drawables.length);
            mViews = new TextView[drawables.length];
        }
    }
    public void setPostPosition(PostPosition postPosition){
        this.postPosition = postPosition;
    }
    public void setTxtColor(int color , int checkedColor){
        this.color = color;
        this.checkedColor = checkedColor;
    }
    /*图片和文字的间距*/
    public void setPadding(int padding){
        this.mDrawableToTxtPadding = padding;
    }
    public void setTextSize(int size){
        this.textSize = size;
    }

    public void myaddView(){
        if(mViews == null || mViews.length == 0) return;
        for(int i=0;i<mViews.length;i++) {
            mViews[i] = new TextView(getContext());
            mViews[i].setText(mTxts[i]);
            Drawable drawable = getResources().getDrawable(mOldDrawables[i]);
            drawable.setBounds(0 , 0 , drawable.getMinimumWidth() , drawable.getMinimumHeight());
            mViews[i].setCompoundDrawables(null , drawable , null , null);
            mViews[i].setCompoundDrawablePadding(mDrawableToTxtPadding);
            mViews[i].setTextColor(getResources().getColor(color));
            mViews[i].setTextSize(textSize);
            mViews[i].setTag(i);
            mViews[i].setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    setSelected((int) v.getTag());
                    postPosition.position((int)v.getTag());
                }
            });
            addView(mViews[i]);
        }
       // invalidate();
    }

    public void setSelected(int position){
        Drawable drawable;
        /*上一次选中的变灰*/
        if(prePosition == position) return;
        drawable = getResources().getDrawable(mOldDrawables[prePosition]);
        drawable.setBounds(0 , 0 , drawable.getMinimumWidth() , drawable.getMinimumHeight());
        mViews[prePosition].setCompoundDrawables(null , drawable , null , null);
        mViews[prePosition].setTextColor(getResources().getColor(color));

        /*这一次的变红*/
        drawable = getResources().getDrawable(mDrawables[position]);
        drawable.setBounds(0 , 0 , drawable.getMinimumWidth() , drawable.getMinimumHeight());
        mViews[position].setCompoundDrawables(null , drawable , null , null);
        mViews[position].setTextColor(getResources().getColor(checkedColor));

        prePosition = position;

        invalidate();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec , int heightMeasureSpec){
        super.onMeasure(widthMeasureSpec , heightMeasureSpec);
        mWidth = MeasureSpec.getSize(widthMeasureSpec);
        measureChildren(widthMeasureSpec , heightMeasureSpec);
        //mWidth =(int) (mWidth/density);
        this.mChildWidth = mWidth/mDrawables.length;
    }
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed , l , t, r , b);
        for(int i=0 ; i<getChildCount();i++){
            View view = getChildAt(i);
            int cWidth = view.getMeasuredWidth();
            //view.layout(i*mChildWidth , 0 , (i+1)*mChildWidth , getHeight());
            view.layout(i*mChildWidth+(mChildWidth - cWidth)/2 , 0 , (i+1)*mChildWidth - (mChildWidth - cWidth)/2 , getHeight());
        }
    }
}
