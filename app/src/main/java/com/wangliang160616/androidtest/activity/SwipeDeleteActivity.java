package com.wangliang160616.androidtest.activity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.wangliang160616.androidtest.R;

public class SwipeDeleteActivity extends AppCompatActivity {

    private RelativeLayout relativeLayout;
    private LinearLayout linearLayout;
    private TextView tv;
    private float firstPoint = 0f;
    private float desentity , mScrollX = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe_delete);
        linearLayout = (LinearLayout) findViewById(R.id.ll);
        tv = (TextView) findViewById(R.id.tv);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus){
        WindowManager windowManager = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(dm);
        desentity = dm.density;
    }


    @Override
    public boolean onTouchEvent(MotionEvent event){

        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:

                    firstPoint = event.getX();


                break;
            case MotionEvent.ACTION_MOVE:
                if(Math.abs(event.getX() - firstPoint)>5) {
                    int newScrollX = (int) (firstPoint - event.getX());
                    if (newScrollX < 0 && mScrollX <= 0) {
                        newScrollX = 0;
                    } else if (newScrollX > 0 && mScrollX > 100 * desentity) {
                        newScrollX = 0;
                    }
                Log.v("out" ,newScrollX+"/"+mScrollX);
                mScrollX = linearLayout.getScrollX();
                if(mScrollX + newScrollX <= 305 && mScrollX+newScrollX >=-5)
                    linearLayout.scrollBy(newScrollX, 0);
                    firstPoint = event.getX();
                }

                break;
            case MotionEvent.ACTION_UP:
                if(mScrollX >= 150){
                    linearLayout.scrollTo(300 , 0);
                }else if(mScrollX <150){
                    linearLayout.scrollTo(0,0);
                }
                break;
        }

        return  super.onTouchEvent(event);
    }
}
