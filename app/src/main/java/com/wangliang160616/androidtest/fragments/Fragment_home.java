package com.wangliang160616.androidtest.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.handmark.pulltorefresh.library.ILoadingLayout;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.squareup.picasso.Picasso;
import com.wangliang160616.androidtest.R;
import com.wangliang160616.androidtest.ad.ADInfo;
import com.wangliang160616.androidtest.ad.ImageCycleView;
import com.wangliang160616.androidtest.model.good;
import com.wangliang160616.androidtest.model.result;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by wangliang on 2016/6/16.
 */
public class Fragment_home extends Fragment implements View.OnClickListener {


    private TextView mTextHeader1,mTextHeader2,mTextHeader3,mTextHeader4,mTextHeader5,mTextHeader6,mTextHeader7,
            mText1,mText2,mText3,mText4,mText5,mText6,mText7;
    private PullToRefreshListView mListView;
    private ImageCycleView mImageCycleView;
    private ArrayList<String> mData;
    private int j = 0;
    private int flag = 1;
    private LinearLayout ll;
    private RequestQueue mQueue ;
    private result ss;
    private int k=180;
    private Timer timer;
    private View view;
    private ListViewAdapter adapter;
    private ArrayList<ADInfo> infos = new ArrayList<ADInfo>();
    private String[] imageUrls = {"file:///android_asset/p01.png","file:///android_asset/p02.png"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        view = inflater.inflate(R.layout.fragment_home,null);
        initView();
        mQueue = Volley.newRequestQueue(getContext());


        mListView = (PullToRefreshListView) view.findViewById(R.id.listview);
        mListView.setMode(PullToRefreshBase.Mode.BOTH);
        /*属性设置*/
        ILoadingLayout startLoading = mListView.getLoadingLayoutProxy(
                true, false);
        startLoading.setPullLabel("下拉刷新");// 刚下拉时显示的提示
        startLoading.setRefreshingLabel("正在刷新中...");// 刷新时显示的提示
        startLoading.setReleaseLabel("释放即可刷新");// 下拉达到一定距离时显示的提示

        ILoadingLayout endLoading = mListView.getLoadingLayoutProxy(false,
                true);
        endLoading.setPullLabel("上拉加载更多");// 刚上拉时显示的提示
        endLoading.setRefreshingLabel("拼命加载中...");// 加载时的提示
        endLoading.setReleaseLabel("释放即可加载更多");// 上拉达到一定距离时显示的提示
        mListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                startHttp("http://www.sqydao.com/phone/new_api.php?m=testItem&getType=1&page=1&page_size=20&cid=1");
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                Log.v("out","上拉");
                startHttp("http://www.sqydao.com/phone/new_api.php?m=testItem&getType=1&page=1&page_size=20&cid=1");
            }
        });
        mListView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if(firstVisibleItem>1){
                    ll.setVisibility(View.VISIBLE);
                }else {
                    ll.setVisibility(View.GONE);
                }
            }
        });
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getContext(),"点击的id是:"+mData.get(position-3).split("/")[2],Toast.LENGTH_SHORT).show();
            }
        });
        ll = (LinearLayout) view.findViewById(R.id.show_ll);
        View headerview = inflater.inflate(R.layout.view_header,null);
        View headerview2 = inflater.inflate(R.layout.view_header2,null);
        mTextHeader1 = (TextView) headerview2.findViewById(R.id.header2_text1);
        mTextHeader2 = (TextView) headerview2.findViewById(R.id.header2_text2);
        mTextHeader3 = (TextView) headerview2.findViewById(R.id.header2_text3);
        mTextHeader4 = (TextView) headerview2.findViewById(R.id.header2_text4);
        mTextHeader5 = (TextView) headerview2.findViewById(R.id.header2_text5);
        mTextHeader6 = (TextView) headerview2.findViewById(R.id.header2_text6);
        mTextHeader7 = (TextView) headerview2.findViewById(R.id.header2_text7);
        mTextHeader1.setOnClickListener(this);
        mTextHeader2.setOnClickListener(this);
        mTextHeader3.setOnClickListener(this);
        mTextHeader4.setOnClickListener(this);
        mTextHeader5.setOnClickListener(this);
        mTextHeader6.setOnClickListener(this);
        mTextHeader7.setOnClickListener(this);

        mImageCycleView = (ImageCycleView) headerview.findViewById(R.id.imgCycleView);

        AbsListView.LayoutParams layoutParams = new AbsListView.LayoutParams(AbsListView.LayoutParams.MATCH_PARENT, AbsListView.LayoutParams.WRAP_CONTENT);
        headerview.setLayoutParams(layoutParams);
        imgRecycle();
        mListView.getRefreshableView().addHeaderView(headerview);
        mListView.getRefreshableView().addHeaderView(headerview2);

        mData = new ArrayList<String>();

        adapter = new ListViewAdapter(mData);
        mListView.setAdapter(adapter);

        startHttp("http://www.sqydao.com/phone/new_api.php?m=testItem&getType=1&page=1&page_size=20&cid=1");

        mQueue.start();

        return view;
    }

    public void initView(){

        mText1 = (TextView) view.findViewById(R.id.text1);
        mText2 = (TextView) view.findViewById(R.id.text2);
        mText3 = (TextView) view.findViewById(R.id.text3);
        mText4 = (TextView) view.findViewById(R.id.text4);
        mText5 = (TextView) view.findViewById(R.id.text5);
        mText6 = (TextView) view.findViewById(R.id.text6);
        mText7 = (TextView) view.findViewById(R.id.text7);
        mText1.setOnClickListener(this);
        mText2.setOnClickListener(this);
        mText3.setOnClickListener(this);
        mText4.setOnClickListener(this);
        mText5.setOnClickListener(this);
        mText6.setOnClickListener(this);
        mText7.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if(id==R.id.header2_text1||id==R.id.text1){
            flag = 1;
            update(flag);
        }else if(id==R.id.header2_text2||id==R.id.text2){
            flag = 2;
            update(flag);
        }else if(id==R.id.header2_text3||id==R.id.text3){
            flag = 3;
            update(flag);
        }
        else if(id==R.id.header2_text4||id==R.id.text4){
            flag = 4;
            update(flag);
        }
        else if(id==R.id.header2_text5||id==R.id.text5){
            flag = 5;
            update(flag);
        }
        else if(id==R.id.header2_text6||id==R.id.text6){
            flag = 6;
            update(flag);
        }
        else if(id==R.id.header2_text7||id==R.id.text7){
            flag = 7;
            update(flag);
        }
    }

    public void update(int flag){
        switch (flag){
            case 1:
                grayView();
                mTextHeader1.setTextColor(getResources().getColor(R.color.color_F9AED1));
                mText1.setTextColor(getResources().getColor(R.color.color_F9AED1));
                break;
            case 2:
                grayView();
                mTextHeader2.setTextColor(getResources().getColor(R.color.color_F9AED1));
                mText2.setTextColor(getResources().getColor(R.color.color_F9AED1));
                break;
            case 3:
                grayView();
                mTextHeader3.setTextColor(getResources().getColor(R.color.color_F9AED1));
                mText3.setTextColor(getResources().getColor(R.color.color_F9AED1));
                break;
            case 4:
                grayView();
                mTextHeader4.setTextColor(getResources().getColor(R.color.color_F9AED1));
                mText4.setTextColor(getResources().getColor(R.color.color_F9AED1));
                break;
            case 5:
                grayView();
                mTextHeader5.setTextColor(getResources().getColor(R.color.color_F9AED1));
                mText5.setTextColor(getResources().getColor(R.color.color_F9AED1));
                break;
            case 6:
                grayView();
                mTextHeader6.setTextColor(getResources().getColor(R.color.color_F9AED1));
                mText6.setTextColor(getResources().getColor(R.color.color_F9AED1));
                break;
            case 7:
                grayView();
                mTextHeader7.setTextColor(getResources().getColor(R.color.color_F9AED1));
                mText7.setTextColor(getResources().getColor(R.color.color_F9AED1));
                break;
        }
    }

    public void grayView(){
        mText1.setTextColor(getResources().getColor(R.color.color_AEAEAE));
        mText2.setTextColor(getResources().getColor(R.color.color_AEAEAE));
        mText3.setTextColor(getResources().getColor(R.color.color_AEAEAE));
        mText4.setTextColor(getResources().getColor(R.color.color_AEAEAE));
        mText5.setTextColor(getResources().getColor(R.color.color_AEAEAE));
        mText6.setTextColor(getResources().getColor(R.color.color_AEAEAE));
        mText7.setTextColor(getResources().getColor(R.color.color_AEAEAE));
        mTextHeader1.setTextColor(getResources().getColor(R.color.color_AEAEAE));
        mTextHeader2.setTextColor(getResources().getColor(R.color.color_AEAEAE));
        mTextHeader3.setTextColor(getResources().getColor(R.color.color_AEAEAE));
        mTextHeader4.setTextColor(getResources().getColor(R.color.color_AEAEAE));
        mTextHeader5.setTextColor(getResources().getColor(R.color.color_AEAEAE));
        mTextHeader6.setTextColor(getResources().getColor(R.color.color_AEAEAE));
        mTextHeader7.setTextColor(getResources().getColor(R.color.color_AEAEAE));
    }


    public void startHttp(String url){
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        Log.v("out","结果:"+s);
                        mListView.onRefreshComplete();
                        if(timer!=null){
                            timer.cancel();
                        }
                        k = 180;
                        try {
                            ss = JSON.parseObject(s,result.class);
                        }catch (Exception e){
                            Log.v("out","解析数据出错");
                        }

                        for(good m:ss.t){
                            mData.add(m.name+"/"+180+"/"+m.id);
                        }

                        for(int i=0;i<mData.size();i++){
                            String s1 = mData.get(i).split("/")[0];
                            String s2 = mData.get(i).split("/")[2];
                            mData.set(i,s1+"/"+180+"/"+s2);
                        }

                        adapter.notifyDataSetChanged();

                        final Handler mHandler = new Handler() {
                            public void handleMessage(android.os.Message msg) {
                                if (msg.what == 1) {
                                    k--;
                                    for(int i=0;i<mData.size();i++){
                                        String s1 = mData.get(i).split("/")[0];
                                        String s2 = mData.get(i).split("/")[2];
                                        mData.set(i,s1+"/"+k+"/"+s2);

                                    }
                                    adapter.notifyDataSetChanged();
                                }
                            }
                        };

                        TimerTask task = new TimerTask(){
                                  public void run() {
                                       Message message = new Message();
                                      message.what = 1;
                                      mHandler.sendMessage(message);
                                  }
                        };
                        timer = new Timer(true);
                         timer.schedule(task,1000, 1000); //延时1000ms后执行，1000ms执行一次
                        //timer.cancel(); //退出计时器


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        });

        mQueue.add(stringRequest);
    }



    /*create listview adapter*/
    class ListViewAdapter extends BaseAdapter{

        private ArrayList<String> mData;
        private ViewHolder vh;
        private Handler mHandler;
        public ListViewAdapter(ArrayList<String> list){
            this.mData = list;

        }
        @Override
        public int getCount() {
            return mData.size();
        }

        @Override
        public Object getItem(int position) {
            return mData.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            vh = null;
            if(convertView==null){
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.item,null);
                vh = new ViewHolder();
                vh.mTextName = (TextView) convertView.findViewById(R.id.item_name);
                vh.mTextTime = (TextView) convertView.findViewById(R.id.item_time);
                vh.mTextGo = (ImageView) convertView.findViewById(R.id.item_go);
                convertView.setTag(vh);
            }else {
                vh = (ViewHolder) convertView.getTag();
            }
            vh.mTextName.setText(mData.get(position).split("/")[0]);
            vh.mTextTime.setText("剩余时间："+mData.get(position).split("/")[1]);



            vh.mTextGo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getContext(),"去购买",Toast.LENGTH_LONG).show();
                }
            });

            return convertView;
        }
        final class ViewHolder{
            TextView mTextName,mTextTime;
            ImageView mTextGo;
        }
    }

    /*广告轮播*/
    public void imgRecycle(){
        final ImageCycleView.ImageCycleViewListener mAdCycleViewListener = new ImageCycleView.ImageCycleViewListener() {
            @Override
            public void onImageClick(ADInfo info, int position, View imageView) {
                //Toast.makeText(getActivity(), "content->" + info.getContent(), Toast.LENGTH_SHORT).show();
                Intent i = null;
                String url = infos.get(position).getContent();
            }
            @Override
            public void displayImage(String imageURL, ImageView imageView) {
                //ImageLoader.getInstance().displayImage(imageURL, imageView);// 使用ImageLoader对图片进行加装！
                Picasso.with(getContext()).load(imageURL).into(imageView);
            }
        };

        infos.clear();
        for(int i=0;i < imageUrls.length; i ++){
            ADInfo info = new ADInfo();
            info.setUrl(imageUrls[i]);
            info.setContent("picture" + i);
            infos.add(info);
        }

        mImageCycleView.setImageResources(infos,mAdCycleViewListener);
    }

}
