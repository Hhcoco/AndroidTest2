package com.wangliang160616.androidtest.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by wangliang on 2016/7/11.
 */
public class student implements Parcelable {

    private int stuId;
    private String stuName;
    private String stuNo;
    private boolean stuSex;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.stuId);
        dest.writeString(this.stuName);
        dest.writeString(this.stuNo);
        dest.writeByte(this.stuSex ? (byte) 1 : (byte) 0);
    }

    public student() {
    }

    protected student(Parcel in) {
        this.stuId = in.readInt();
        this.stuName = in.readString();
        this.stuNo = in.readString();
        this.stuSex = in.readByte() != 0;
    }

    public static final Creator<student> CREATOR = new Creator<student>() {
        @Override
        public student createFromParcel(Parcel source) {
            return new student(source);
        }

        @Override
        public student[] newArray(int size) {
            return new student[size];
        }
    };

    public int getStuId() {
        return stuId;
    }

    public void setStuId(int stuId) {
        this.stuId = stuId;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public String getStuNo() {
        return stuNo;
    }

    public void setStuNo(String stuNo) {
        this.stuNo = stuNo;
    }

    public boolean isStuSex() {
        return stuSex;
    }

    public void setStuSex(boolean stuSex) {
        this.stuSex = stuSex;
    }
}
