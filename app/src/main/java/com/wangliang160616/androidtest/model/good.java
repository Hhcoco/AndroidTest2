package com.wangliang160616.androidtest.model;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;

/**
 * Created by wangliang on 2016/6/16.
 */
public class good implements Serializable {
    @JSONField(name="id")
    public String id;
    @JSONField(name="name")
    public String name;

}
