package com.wangliang160616.androidtest.utils.ZeusRecyclerView;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtConstructor;
import javassist.CtMethod;
import javassist.NotFoundException;

/**
 * Created by wangliang on 2016/10/12.
 */
public class ZeusViewHolder extends RecyclerView.ViewHolder {

    private SparseArray<View> mViews;//集合类，layout里包含的View,以view的id作为key，value是view对象
    /*侧滑后显示出来的View*/
    private View mSlideView;

    public ZeusViewHolder(View itemView) {
        super(itemView);
        mViews = new SparseArray<View>();

        byte [] a = {'a','b' ,'c'};
        String str = new String(a);
        Log.v("out" , "数据为："+str);
    }

    /*尝试运行时注入代码，而不是编译时，用来跳过编译检查，但是失败了...*/
    public static ZeusViewHolder test(View view){
        ClassPool pool = ClassPool.getDefault();
        CtClass cc = null;
        try {
            cc = pool.get("com.wangliang160616.androidtest.utils.ZeusRecyclerView.ZeusViewHolder");
            CtConstructor [] ctConstructors =  cc.getConstructors();
            CtMethod m = cc.getDeclaredMethod("move");
            m.insertBefore("{ System.out.println('hello,javassist!'); }");
            cc.writeFile();
            return new ZeusViewHolder(view);
        } catch (NotFoundException e) {
            e.printStackTrace();
        } catch (CannotCompileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /*设置侧滑出来的View , 要在setAdapter之前调用*/
    public void setSlideView(View view){
        mSlideView = view;
    }

    public View getItemView(){
        return itemView;
    }

    private <T extends View> T findViewById(int viewId) {
        View view = mViews.get(viewId);

        if (view == null) {
            view = itemView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (T) view;
    }

    public View getView(int viewId) {
        return findViewById(viewId);
    }

    public TextView getTextView(int viewId) {
        return (TextView) getView(viewId);
    }

    public Button getButton(int viewId) {
        return (Button) getView(viewId);
    }

    public ImageView getImageView(int viewId) {
        return (ImageView) getView(viewId);
    }

    public ImageButton getImageButton(int viewId) {
        return (ImageButton) getView(viewId);
    }

    public EditText getEditText(int viewId) {
        return (EditText) getView(viewId);
    }

    public ZeusViewHolder setText(int viewId, String value) {
        TextView view = findViewById(viewId);
        view.setText(value);
        return this;
    }

    public ZeusViewHolder setBackground(int viewId, int resId) {
        View view = findViewById(viewId);
        view.setBackgroundResource(resId);
        return this;
    }

    public ZeusViewHolder setClickListener(int viewId, View.OnClickListener listener) {
        View view = findViewById(viewId);
        view.setOnClickListener(listener);
        return this;
    }
}