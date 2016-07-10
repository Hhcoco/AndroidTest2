package com.wangliang160616.androidtest.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wangliang160616.androidtest.R;

/**
 * Created by wangliang on 2016/6/16.
 */
public class Fragment_test extends Fragment {
    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup container, Bundle savedInstanceState){
        View view = layoutInflater.inflate(R.layout.fragment_test,null);
        return view;
    }
}
