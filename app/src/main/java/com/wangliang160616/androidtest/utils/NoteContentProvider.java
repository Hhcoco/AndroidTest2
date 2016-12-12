package com.wangliang160616.androidtest.utils;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.support.annotation.Nullable;

/**
 * Created by wangliang on 2016/8/26.
 */
public class NoteContentProvider extends ContentProvider {

    private static UriMatcher uriMatcher;
    private static SQLiteDatabase db;

    @Override
    public boolean onCreate() {
        return false;
    }

    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(NoteProviderMetaData.AUTHORITY , "notes" , 1);
        uriMatcher.addURI(NoteProviderMetaData.AUTHORITY , "notes/#" , 2);
    }

    public static class DataBaseHelper extends SQLiteOpenHelper{

        public DataBaseHelper(Context context) {
            super(context, NoteProviderMetaData.DATABASE_NAME, null, NoteProviderMetaData.DATABASE_VERSION);
            db = getReadableDatabase();
        }

        @Override
        public void onCreate(SQLiteDatabase db){
            db.beginTransaction();
            db.execSQL(NoteProviderMetaData.NoteTableMetaData.SQL_CREATE_TABLE);
            db.endTransaction();
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            //
        }
    }


    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {

        Cursor cursor = null;
        switch (uriMatcher.match(uri)){
            case 1:
                String query_sql = "select* from notes";
                cursor = db.rawQuery(query_sql , null);
                break;
        }
        return cursor;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        switch (uriMatcher.match(uri)){
            case 2:
                String title = values.getAsString("title");
                String content = values.getAsString("content");
                db.beginTransaction();
                String insert_sql = "insert into notes values ('"+title+"','"+content+"')";
                db.execSQL(insert_sql);
                db.endTransaction();
                break;
        }
        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }
}
