package com.wangliang160616.androidtest.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewConfiguration;

import com.wangliang160616.androidtest.utils.DateUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by wangliang on 2016/9/20.
 */
public class WeekView extends View {

    /*6行7列*/
    private static final int TOTAL_COL = 7 , TOTAL_ROW=6;
    /*绘制圆形的画笔和文本的画笔*/
    private Paint mCirclePaint , mTextPaint;
    /*视图宽高和单元格间距*/
    private int mViewWidth , mViewHeight , mCellSpace;
    /*触摸相关，最小滑动距离，滑动位置*/
    private int touchSlop;
    private float mDownX , mDownY;
    /*每个单元格的日期，从1开始*/
    private int day = 1;
    /*前一个月和后一个月显示在这个月的天数*/
    private int preDay , nextDay;
    /*月份*/
    private int month = 9;
    /*是否已经画过了*/
    private boolean isHaveDraw = true;



        public WeekView(Context context) {
        super(context);
        init(context);
    }

    public WeekView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public WeekView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public void setMonth(int month){
        this.month = month;
        invalidate();
    }

    @Override
    public void onDraw(Canvas canvas){
        super.onDraw(canvas);
        drawWeek(canvas);
        fillDate(canvas);
    }

    /*画上面的星期*/
    public void drawWeek(Canvas canvas){
        if(isHaveDraw){
            String [] weekString = {"日" , "一" , "二" , "三", "四" , "五" , "六"};
            Paint mLinePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
            mLinePaint.setStyle(Paint.Style.STROKE);
            mLinePaint.setColor(Color.parseColor("#CCE4F2"));
            mLinePaint.setStrokeWidth(4);
            canvas.drawLine(0,0,getWidth(),0,mLinePaint);
            canvas.drawLine(0,200,getWidth(),200,mLinePaint);
            mLinePaint.setTextSize(100);
            for(int i=0; i<weekString.length; i++){
                int startX = (i+1)*120;
                int startY = 50;
                if(weekString[i].contains("日")||weekString[i].contains("六")){
                    mLinePaint.setColor(Color.RED);
                }else mLinePaint.setColor(Color.BLUE);
                canvas.drawText(weekString[i] , startX , startY , mLinePaint);
            }
            isHaveDraw = false;
        }
    }

    /*初始化画笔等*/
    public void init(Context context){
        int m=0;
        while(++m<10){
            Log.v("out" , "++m的值："+m);
        }
        int n = 0;
        while(n++<10){
            Log.v("out" , "m++的值："+n);
        }

        mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG); //抗锯齿标识
        mCirclePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mCirclePaint.setStyle(Paint.Style.FILL);
        mCirclePaint.setColor(Color.parseColor("#F24949"));
        /*最小滑动距离*/
        touchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
    }
    /*填充数据*/
    public void fillDate(Canvas canvas){
        /*获取今天*/
        int monthDay = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
        /*获取当月月份*/
        int currentMonth = Calendar.getInstance().get(Calendar.MONTH);

        /*获取上一个月的天数*/
        int lastMonthDays = DateUtil.getMonthDay(2016 , month-1);
        /*获取当前月的天数*/
        int currentMonthDays = DateUtil.getMonthDay(2016 , month);
        /*获取每个月第一天是星期几*/

        int weekIndex = 0;
        try {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new SimpleDateFormat("yyyy-MM-dd").parse("2016-09-01"));
            weekIndex = calendar.get(Calendar.DAY_OF_WEEK)<0?0:calendar.get(Calendar.DAY_OF_WEEK)-1;
        } catch (ParseException e) {
            e.printStackTrace();
        }

        day = 1;
        preDay = lastMonthDays-weekIndex;
        nextDay = 0;

        Log.v("out" , "当前月份："+Calendar.getInstance().MONTH+"&"+Calendar.MONTH);

        for(int i=0;i<TOTAL_ROW;i++){

            for(int j=0;j<TOTAL_COL;j++){
                /*第一行，可能有上一个月的日期*/
                if(i==0){
                    if(j<weekIndex)
                        new Cell(++preDay , i , j ,Cell.State.PRE_MONTH).drawIt(canvas);
                    else {
                        if(day == monthDay && Calendar.getInstance().get(Calendar.MONTH)+1 == month)
                            new Cell(day , i ,j , Cell.State.TODAY).drawIt(canvas);
                        else new Cell(day , i ,j , Cell.State.CURRENT_MONTH).drawIt(canvas);
                        if(day+1<currentMonthDays)
                            day++;
                    }
                }else {
                    /*由于是先判断后自加，所以减1*/
                    if(day-1<currentMonthDays){
                        if(day == monthDay && Calendar.getInstance().get(Calendar.MONTH)+1 == month)
                            new Cell(day++ , i ,j , Cell.State.TODAY).drawIt(canvas);
                        else new Cell(day++ , i ,j , Cell.State.CURRENT_MONTH).drawIt(canvas);
                    }else new Cell(++nextDay , i ,j , Cell.State.NEXT_MONTH).drawIt(canvas);
                }
            }

        }
    }
}
