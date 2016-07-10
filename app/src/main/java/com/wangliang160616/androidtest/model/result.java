package com.wangliang160616.androidtest.model;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

/**
 * Created by wangliang on 2016/6/16.
 */
public class result {

    @JSONField(name="s")
    public String s;
    @JSONField(name="r")
    public String r;
    @JSONField(name="t")
    public List<good> t;

}
