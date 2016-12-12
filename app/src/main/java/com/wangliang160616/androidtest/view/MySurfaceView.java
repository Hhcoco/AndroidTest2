package com.wangliang160616.androidtest.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.hardware.Camera;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.WindowManager;

import java.io.IOException;
import java.util.List;

/**
 * Created by wangliang on 2016/12/8.
 */
public class MySurfaceView extends SurfaceView implements SurfaceHolder.Callback {

    private boolean mFlag;
    private SurfaceHolder surfaceHolder;
    private Canvas canvas;
    private int counter = 0;
    private Camera mCamera;
    private int mScreenWidth , mScreenHeight;
    private Context mContext;

    private Thread mThread = new Thread() {
        @Override
        public void run() {
            while (mFlag) {
            /*锁定画布，得到Canvas对象*/
                canvas = surfaceHolder.lockCanvas();
                canvas.drawColor(Color.GREEN);

                // 创建画笔
                Paint p = new Paint();
                // 设置画笔颜色
                p.setColor(Color.RED);
                // 设置文字大小
                p.setTextSize(40);

                // 创建一个Rect对象rect
                // public Rect (int left, int top, int right, int bottom)
                Rect rect = new Rect(100, 50, 400, 350);
                // 在canvas上绘制rect
                canvas.drawRect(rect, p);
                // 在canvas上显示时间
                // public void drawText (String text, float x, float y, Paint
                // paint)
                canvas.drawText("时间 = " + (counter++) + " 秒", 500, 200, p);

                if (canvas != null) {
                    // 解除锁定，并提交修改内容，更新屏幕
                    surfaceHolder.unlockCanvasAndPost(canvas);
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    };

    public void initView(){
        surfaceHolder = this.getHolder();
        surfaceHolder.addCallback(this);
        mFlag = true;
    }

    public MySurfaceView(Context context) {
        super(context);
        this.mContext = context;
        initView();
    }

    public MySurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        initView();
    }

    //设置相机参数
    public void setParams(Camera camera){
        Camera.Parameters parameters = camera.getParameters();
        //设置图片尺寸
        List<Camera.Size> pictureSizeList = parameters.getSupportedPictureSizes();
        /*选择和屏幕分辨率最合适的size*/
        Camera.Size suitableSize = getProperSize(pictureSizeList , getScreenWH());
        //重新计算宽高
        int width = suitableSize.width;
        int height = suitableSize.height;
        parameters.setPictureSize(width , height);
        //设置预览尺寸
        List<Camera.Size> previewSizeList = parameters.getSupportedPreviewSizes();
        Camera.Size suitablePreviewSize = getProperSize(previewSizeList , getScreenWH());
        int previewWidth = suitablePreviewSize.width;
        int previewHeight = suitablePreviewSize.height;
        parameters.setPreviewSize(previewWidth , previewHeight);
        //设置照片质量
        parameters.setJpegQuality(100);
        if (parameters.getSupportedFocusModes().contains(android.hardware.Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE)) {
            parameters.setFocusMode(android.hardware.Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE);// 连续对焦模式
        }
        mCamera.cancelAutoFocus();
        mCamera.setDisplayOrientation(90);
        mCamera.setParameters(parameters);


    }

    //获取屏幕分辨率比例
    public float getScreenWH(){
        WindowManager windowManager = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        int widthPix = displayMetrics.widthPixels;
        int heightPix = displayMetrics.heightPixels;
        return heightPix/widthPix;
    }

    private Camera.Size getProperSize(List<Camera.Size> pictureSizeList, float screenRatio) {
        Log.i("out", "screenRatio=" + screenRatio);
        Camera.Size result = null;
        for (Camera.Size size : pictureSizeList) {
            float currentRatio = ((float) size.width) / size.height;
            if (currentRatio - screenRatio == 0) {
                result = size; break; } } if (null == result) {
            for (Camera.Size size : pictureSizeList) {
                float curRatio = ((float) size.width) / size.height;
                if (curRatio == 4f / 3) {
                    // 默认w:h = 4:3
                     result = size;
                     break;
                     }
            }
        } return result;
    }


    /*surface尺寸发生变化的时候，比如横竖屏切换*/
                @Override
                public void surfaceCreated(SurfaceHolder holder) {
                    if(mCamera == null){
                        mCamera = Camera.open(1);
                    }
                    try {
                        setParams(mCamera);
                        mCamera.setPreviewDisplay(surfaceHolder);
                    } catch (IOException e) {
                        Log.e("out" , "MySurfaceView:"+e.toString());
                    }
                }

                @Override
                public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
                    mCamera.startPreview();
                }

                @Override
                public void surfaceDestroyed(SurfaceHolder holder) {
                    mCamera.stopPreview();
                    mCamera.release();
                    mCamera = null;
                    surfaceHolder = null;
                }
            }

