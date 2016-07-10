package com.wangliang160616.androidtest.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.wangliang160616.androidtest.R;

import java.util.HashMap;

/**
 * Created by wangliang on 2016/7/10.
 */
public class SimpleAdapter extends BaseAdapter {

    private String [] data;
    private int layoutxml;
    private Context mContext;

    public SimpleAdapter(){
    }

    public SimpleAdapter(Context context,String [] data,int layoutxml){
        this.mContext = context;
        this.data = data;
        this.layoutxml = layoutxml;
    }

    static final class SimpleAdapterViewHolder{
        TextView mTv;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        SimpleAdapterViewHolder vh = null;
        if(convertView==null){
            convertView = (View) LayoutInflater.from(mContext).inflate(layoutxml,null);
            vh = new SimpleAdapterViewHolder();
            vh.mTv = (TextView) convertView.findViewById(R.id.item_launch_title_tv_title);
            convertView.setTag(vh);
        }else vh = (SimpleAdapterViewHolder) convertView.getTag();
         vh.mTv.setText(data[position]);
        return convertView;
    }

    @Override
    public int getCount() {
        return data.length;
    }

    @Override
    public Object getItem(int position) {
        return data[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


}
