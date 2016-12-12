package com.wangliang160616.androidtest.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.wangliang160616.androidtest.R;
import com.wangliang160616.androidtest.utils.PostPosition;
import com.wangliang160616.androidtest.view.ZeusViewGroup;

public class ViewGroupActivity extends AppCompatActivity {

    private ZeusViewGroup zeusViewGroup;
    private int [] drables = {R.drawable.home_ok , R.drawable.icon_tab_apply_ok , R.drawable.icon_tab_me_ok , R.drawable.icon_tab_service_ok};
    private String [] txts = {"主页选中" , "主页未选中","主页选中" , "主页未选中"};
    private int [] olddrables = {R.drawable.home_no , R.drawable.icon_tab_apply_no , R.drawable.icon_tab_me , R.drawable.icon_tab_service};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_group);
        zeusViewGroup = (ZeusViewGroup) findViewById(R.id.zesuviewgroup);
        zeusViewGroup.setData(drables , olddrables, txts);
        zeusViewGroup.setTextSize(14);
        zeusViewGroup.setPadding(5);
        zeusViewGroup.setTxtColor(R.color.color_AEAEAE , R.color.colorAccent);
        zeusViewGroup.setPostPosition(new PostPosition() {
            @Override
            public void position(int position) {
                Log.v("out" , "点击的位置为"+position);
            }
        });
        zeusViewGroup.myaddView();
        zeusViewGroup.setSelected(0);
    }
}
