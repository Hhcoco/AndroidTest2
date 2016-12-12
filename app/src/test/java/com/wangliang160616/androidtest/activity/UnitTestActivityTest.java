package com.wangliang160616.androidtest.activity;

import android.util.Log;
import android.widget.TextView;

import com.wangliang160616.androidtest.BuildConfig;
import com.wangliang160616.androidtest.R;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import static org.junit.Assert.*;

/**
 * Created by wangliang on 2016/8/15.
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class UnitTestActivityTest {

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void testClick() throws Exception {
        UnitTestActivity unitTestActivity = Robolectric.setupActivity(UnitTestActivity.class);
        TextView mTv =(TextView) unitTestActivity.findViewById(R.id.activity_unit_test_tv_id);
        assertTrue(mTv.isShown());
        mTv.performClick();
        assertTrue(mTv.isEnabled());
        assertEquals("单测试" , mTv.getText().toString());
        /*assertTrue(mTv.isShown());
        assertTrue(mTv.isEnabled());
        assertEquals("单元测试" , mTv.getText().toString());*/
    }
}