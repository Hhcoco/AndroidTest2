package com.wangliang160616.androidtest.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.ViewConfiguration;

/**
 * Created by wangliang on 2016/9/20.
 */
public class Cell {

    /*绘制圆形的画笔和文本的画笔*/
    private Paint mCirclePaint , mTextPaint;
    /*单元格的值和位置*/
    private int day, i , j;
    private State state;
    /*视图宽高和单元格间距*/
    private int mViewWidth , mViewHeight , mCellSpace;

    public Cell(int day , int i , int j ,State state){
        this.state = state;
        this.day = day;
        this.i = i;
        this.j = j;
        init();
    }

    /*初始化画笔等*/
    public void init(){
        mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG); //抗锯齿标识
        mCirclePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mCirclePaint.setStyle(Paint.Style.FILL);
        mCirclePaint.setColor(Color.parseColor("#F24949"));
    }
    /*每个单元格的状态，上一个月，当前月，下一个月*/
    enum State {
        TODAY , CURRENT_MONTH , PRE_MONTH , NEXT_MONTH;
    }
    /*绘制*/
    public void drawIt(Canvas canvas){
        switch (state){
            case TODAY:
                mTextPaint.setColor(Color.parseColor("#F24949"));
                break;
            case CURRENT_MONTH:
                mTextPaint.setColor(Color.BLUE);
                break;
            case PRE_MONTH:
                mTextPaint.setColor(Color.GRAY);
                break;
            case NEXT_MONTH:
                mTextPaint.setColor(Color.GRAY);
                break;
            default:
                mTextPaint.setColor(Color.RED);
                break;
        }
        mTextPaint.setTextSize(80);
        canvas.drawText(day+"" , (j+1)*120 , (i+1)*120+200 , mTextPaint);
    }
}
