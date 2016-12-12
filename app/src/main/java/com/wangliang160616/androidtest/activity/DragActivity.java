package com.wangliang160616.androidtest.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wangliang160616.androidtest.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DragActivity extends AppCompatActivity {

    @BindView(R.id.activity_drag_tv_show)
    TextView activityDragTvShow;
    @BindView(R.id.activity_drag_rl)
    RelativeLayout activityDragRl;

    /*点击落点的位置*/
    private float startX = 0 , startY = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drag);
        ButterKnife.bind(this);
        init();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        super.onTouchEvent(event);
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                startX = event.getX();
                startY = event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                if(Math.abs(event.getX()-startX)<Math.abs(event.getY()-startY)){
                    ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) activityDragRl.getLayoutParams();
                    marginLayoutParams.topMargin += Math.abs(event.getY()-startY);
                    activityDragRl.requestLayout();
                }else{
                    activityDragTvShow.setText("左右滑动");
                }
        }
        return true;
    }

    /**/
    public void init(){
        activityDragRl.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                boolean isConsume = false;

                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        startX = event.getX();
                        startY = event.getY();
                        return false;  //将事件交由activity的onTouchEvent来处理
                    case MotionEvent.ACTION_MOVE:
                        if(Math.abs(event.getX()-startX)<Math.abs(event.getY()-startY)){
                            isConsume = true;
                            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) activityDragRl.getLayoutParams();
                            marginLayoutParams.topMargin += Math.abs(event.getY()-startY);
                            activityDragRl.requestLayout();
                        }
                }
                return true;
            }
        });
    }
}
