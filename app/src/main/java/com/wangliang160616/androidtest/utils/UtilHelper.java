package com.wangliang160616.androidtest.utils;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

/**
 * Created by wangliang on 2016/7/30.
 */
public class UtilHelper {

    public static int pxTodp(Context context , int px){
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(dm);
        int dp = (int)(px/dm.density);
        return dp;
    }

}
