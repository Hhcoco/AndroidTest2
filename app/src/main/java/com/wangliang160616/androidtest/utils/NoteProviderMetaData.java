package com.wangliang160616.androidtest.utils;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by wangliang on 2016/8/26.
 */
public class NoteProviderMetaData {

    public static final String SCHEME = "content://";
    public static final String AUTHORITY = "com.wangliang160616.androidtest.NoteProvider";
    public static final String DATABASE_NAME = "note.db";
    public static final int DATABASE_VERSION = 1;

    /*建表相关,BaseColumns提供了唯一id,可以直接使用*/
    public static final class NoteTableMetaData implements BaseColumns{

        public static final String TABLE_NAME = "notes";
        public static final Uri CONTENT_URI = Uri.parse(SCHEME+AUTHORITY+"/"+TABLE_NAME);
        public static final String SQL_CREATE_TABLE = "create table if not exists notes ("+_ID+" INTEGER PRIMARY KEY,"+"title varchar(50),content text)";

    }

}
