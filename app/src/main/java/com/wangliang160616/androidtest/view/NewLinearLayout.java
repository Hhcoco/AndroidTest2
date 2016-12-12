package com.wangliang160616.androidtest.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.LinearLayout;
import android.widget.Scroller;

/**
 * Created by wangliang on 2016/9/27.
 */
public class NewLinearLayout extends LinearLayout {

    /*将触摸点坐标传递出去*/
    private IGetX iGetX;
    /*弹性滑动*/
    private Scroller mScroller;

    public NewLinearLayout(Context context) {
        super(context);
        init(context);
    }

    public NewLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public NewLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    /*initial*/
    public void init(Context context){
        mScroller = new Scroller(context);
    }

    @Override
    public void computeScroll(){
        super.computeScroll();
        /*判断是否完成滑动*/
        if(mScroller.computeScrollOffset()){
            scrollTo(mScroller.getCurrX() , 0);
            /*循环调用该方法*/
            postInvalidate();
        }
    }

    /*开启弹性滑动*/
    public void beginScroll(int dx ){
        mScroller.startScroll(mScroller.getCurrX() , 0 , dx , 0 , 1000);
        /*调用computeScroll()方法*/
        invalidate();
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event){
        float dx = event.getX();
        float dy = event.getY();
        if(event.getAction() == MotionEvent.ACTION_MOVE)
            iGetX.point(dx , dy);
        return super.dispatchTouchEvent(event);
    }

    public void set(IGetX iGetX){
        this.iGetX = iGetX;
    }

    public interface IGetX{
        void point(float dx , float dy);
    }
}
