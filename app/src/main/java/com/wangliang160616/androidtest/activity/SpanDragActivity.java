package com.wangliang160616.androidtest.activity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Scroller;
import android.widget.TextView;

import com.wangliang160616.androidtest.R;
import com.wangliang160616.androidtest.view.NewLinearLayout;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class SpanDragActivity extends AppCompatActivity {

    @BindView(R.id.activity_span_ll)
    NewLinearLayout activitySpanLl;
    @BindView(R.id.activity_span_rv_left)
    RecyclerView activitySpanRvLeft;
    @BindView(R.id.activity_span_rv_right)
    RecyclerView activitySpanRvRight;
    @BindView(R.id.activity_span_ll_left)
    LinearLayout activitySpanLlLeft;
    @BindView(R.id.activity_span_ll_right)
    LinearLayout activitySpanLlRight;
    @BindView(R.id.activity_span_img)
    ImageView activitySpanImg;

    /*设置宽度和移动位置，创建图像都只需要执行一次,第一次的坐标为起点坐标*/
    private boolean isFirst = true, isFirstScroll = true , isFirstCreateBitmap = true , isFirstPoint = true , isFirstLeftScroll = true;
    /*拖拽辅助类*/
    private ItemTouchHelper itemTouchHelper;
    /*拖动的View和图像*/
    private View TempDragView;
    private Bitmap bitmap;
    private BitmapDrawable bitmapDrawable;
    private int x = 5 , y = 5;
    /*手势监听长按事件*/
    private GestureDetectorCompat mDetector;
    /*这是长按的那个itemView*/
    private View TempItemView;
    /*起始位置*/
    private float startX = 0, startY = 0 , firstX = 0 , firstY = 0;
    /*Scroller弹性滑动*/
    private Scroller scroller;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_span_drag);
        Unbinder unbinder = ButterKnife.bind(this);
        unbinder.unbind();

        scroller = new Scroller(this);
        scroller.startScroll(0 , 0 , 1080 , 0 , 1000);

        mDetector = new GestureDetectorCompat(this , new GestureDetector.SimpleOnGestureListener(){
            @Override
            public void onLongPress(MotionEvent e) {
                Log.v("out" , "长按");
                if(TempItemView == null) return;
                        bitmap = view2bitmap(TempItemView);
                        //bitmapDrawable = new BitmapDrawable(getResources() , bitmap);
                        activitySpanImg.setImageBitmap(bitmap);
                        isFirstCreateBitmap = false;
            }
        });
        TempDragView = new View(this);
        /*设置接口获取手指位置*/
        activitySpanLl.set(new NewLinearLayout.IGetX() {
            @Override
            public void point(float dx , float dy) {
                Log.v("out", "手指位置：" + dx);
                if(isFirstPoint){
                    firstX = dx;
                    firstY = dy;
                    isFirstPoint = false;
                }
                if(bitmap != null) {
                    activitySpanImg.setTranslationX(dx-firstX+startX);
                    activitySpanImg.setTranslationY(dy-firstY+startY);
                }
                if(dx >=800){
                }
                if (isFirstScroll)
                    if (dx >= 1000) {
                        activitySpanLl.beginScroll(1080);
                        isFirstScroll = false;
                        /*向右边滑动了，所以现在可以向左滑动了*/
                        isFirstLeftScroll = true;
                        int a = activitySpanLl.getScrollX();
                        float b = activitySpanLl.getX();
                    }
                if(isFirstLeftScroll)
                    if(dx <= 80){
                        activitySpanLl.beginScroll(-1080);
                        isFirstLeftScroll = false;
                        /*向左边滑动了，所以现在可以向右滑动了*/
                        isFirstScroll = true;
                        int a = activitySpanLl.getScrollX();
                        float b = activitySpanLl.getX();
                    }
            }
        });
        itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.Callback() {
            @Override
            public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
                final int dragFlag = ItemTouchHelper.DOWN | ItemTouchHelper.UP | ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT ;
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

        /*左边列表*/
        activitySpanRvLeft.setLayoutManager(new LinearLayoutManager(this));
        Recycler1Adapter adapter = new Recycler1Adapter();
        ArrayList<String> mData = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            mData.add("你好，我是页面1的" + i + "号");
        }
        adapter.setData(mData);
        activitySpanRvLeft.setAdapter(adapter);
        itemTouchHelper.attachToRecyclerView(activitySpanRvLeft);

        /*右边列表*/
        activitySpanRvRight.setLayoutManager(new LinearLayoutManager(this));
        Recycler1Adapter adapterRight = new Recycler1Adapter();
        ArrayList<String> mData2 = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            mData2.add("你好，我是页面2的" + i + "号");
        }
        adapterRight.setData(mData2);
        activitySpanRvRight.setAdapter(adapterRight);
        //itemTouchHelper.attachToRecyclerView(activitySpanRvRight);
    }

    /*将itemview转换为图片，实现拖拽的视觉效果*/
    public Bitmap view2bitmap(View view) {
        int width = view.getMeasuredWidth();
        int height = view.getMeasuredHeight();
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        view.draw(new Canvas(bitmap));
        return bitmap;
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
            View itemView = LayoutInflater.from(SpanDragActivity.this).inflate(R.layout.item_launch_title, null);
            final Recycler1ViewHolder viewHolder = new Recycler1ViewHolder(itemView);
            viewHolder.itemView.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    mDetector.onTouchEvent(event);
                    if(event.getAction() == MotionEvent.ACTION_DOWN){
                        bitmap = null;
                        TempItemView = viewHolder.itemView;
                        startX = TempItemView.getLeft();
                        startY = TempItemView.getTop();
                    }
                    /*恢复计点*/
                    if(event.getAction() == MotionEvent.ACTION_UP){
                        isFirstPoint = true;
                    }

                    return false;
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

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        if (isFirst) {
            WindowManager windowManager = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
            DisplayMetrics dm = new DisplayMetrics();
            windowManager.getDefaultDisplay().getMetrics(dm);
            activitySpanLlRight.getLayoutParams().width = dm.widthPixels;
            isFirst = false;
        }
    }
}
