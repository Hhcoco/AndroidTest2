package com.wangliang160616.androidtest.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.wangliang160616.androidtest.R;
import com.wangliang160616.androidtest.model.DaoMaster;
import com.wangliang160616.androidtest.model.DaoSession;
import com.wangliang160616.androidtest.model.User;
import com.wangliang160616.androidtest.model.UserDao;

import java.util.List;


/**
 * @author wangliang
 * @version 1.1
 * @deprecated
 * @param
 * @return
 * */

public class GreenDaoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_green_dao);

        initView();
    }

    /**/
    public void initView(){
        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(getApplicationContext() , "androidTest.db" ,null);
        DaoMaster daoMaster = new DaoMaster(devOpenHelper.getReadableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        UserDao userDao = daoSession.getUserDao();
        User user = new User(null ,"test","123", "test" , "man" , 22);
        userDao.insert(user);
        List<User> userList = userDao.queryBuilder().build().list();
        if(userList != null && userList.size() > 0){
            Log.v("out" , "数据库条数"+userList.size());
            for(User user1 : userList)
            Log.v("out" , user1.getId() +"/"+user1.getUserName());
        }
    }
}
