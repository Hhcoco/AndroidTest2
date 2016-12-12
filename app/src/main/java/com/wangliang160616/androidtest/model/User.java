package com.wangliang160616.androidtest.model;

import android.os.Parcel;
import android.os.Parcelable;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;

/**
 * Created by wangliang on 2016/8/30.
 */
@Entity
public class User implements Parcelable{

    /**/
    @Id
    private Long id;
    /*新增加的属性*/
    @Property(nameInDb = "test")
    private String test;
    /*新增加的属性*/
    @Property(nameInDb = "password")
    private String password;
    /**/
    @Property(nameInDb = "userName")
    private String userName;
    /**/
    @Property(nameInDb = "userSex")
    private String userSex;
    /**/
    @Property(nameInDb = "userAge")
    private Integer userAge;
    public Integer getUserAge() {
        return this.userAge;
    }
    public void setUserAge(Integer userAge) {
        this.userAge = userAge;
    }
    public String getUserSex() {
        return this.userSex;
    }
    public void setUserSex(String userSex) {
        this.userSex = userSex;
    }
    public String getUserName() {
        return this.userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    @Generated(hash = 446084445)
    public User(Long id, String test, String password, String userName,
            String userSex, Integer userAge) {
        this.id = id;
        this.test = test;
        this.password = password;
        this.userName = userName;
        this.userSex = userSex;
        this.userAge = userAge;
    }
    @Generated(hash = 586692638)
    public User() {
    }

    public String getPassword() {
        return this.password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.id);
        dest.writeString(this.test);
        dest.writeString(this.password);
        dest.writeString(this.userName);
        dest.writeString(this.userSex);
        dest.writeValue(this.userAge);
    }
    public String getTest() {
        return this.test;
    }
    public void setTest(String test) {
        this.test = test;
    }

    protected User(Parcel in) {
        this.id = (Long) in.readValue(Long.class.getClassLoader());
        this.test = in.readString();
        this.password = in.readString();
        this.userName = in.readString();
        this.userSex = in.readString();
        this.userAge = (Integer) in.readValue(Integer.class.getClassLoader());
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel source) {
            return new User(source);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };
}
