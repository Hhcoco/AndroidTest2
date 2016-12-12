package com.wangliang160616.androidtest.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wangliang160616.androidtest.R;

public class ScrollToOrByActivity extends AppCompatActivity  {
    private LinearLayout container;
    private int currentX;
    private int currentY;
    private TextView xyValue;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_scroll_to_or_by);

        container = (LinearLayout) findViewById(R.id.container);
        xyValue = (TextView) findViewById(R.id.xyValue);

    }

    @Override
    public void onWindowFocusChanged (boolean hasFocus){
        super.onWindowFocusChanged(hasFocus);
        //container.scrollTo(50,50);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        switch (event.getAction())
        {
            case MotionEvent.ACTION_DOWN:
            {
                currentX = (int) event.getRawX();
                currentY = (int) event.getRawY();
                Log.v("out" ,currentX+"/"+currentY+"/"+event.getX()+"/"+event.getY());
                break;
            }
            case MotionEvent.ACTION_MOVE:
            {

                break;
            }
            case MotionEvent.ACTION_UP:
            {
                int x2 = (int) event.getRawX();
                int y2 = (int) event.getRawY();
                container.scrollBy(currentX - x2 , currentY - y2);
                Log.v("out" ,(currentX - x2) +"/" +(currentY - y2));
                Log.v("out" ,container.getScrollX() +"/" +container.getScrollY());
                break;
            }
        }
        return true;
    }
}