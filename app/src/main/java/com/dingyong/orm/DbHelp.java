package com.dingyong.orm;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;
import java.util.HashMap;

/**
 * Created by Administrator on 2016/9/13.
 */
public class DbHelp extends OrmLiteSqliteOpenHelper {
    private static final String DB_NAME = Environment.getExternalStorageDirectory().getAbsolutePath()+"/sqlite-test.db";
    private static final int DB_VERSION = 2;

    private HashMap<String,Dao> mDaos = new HashMap<>();
    public DbHelp(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }
    private static DbHelp mInstance;
    public synchronized static DbHelp getmInstace(Context context){
        if (mInstance == null){
            synchronized (DbHelp.class){
                if (mInstance == null){
                    mInstance = new DbHelp(context);
                }
            }
        }
        return mInstance;
    }

    public synchronized Dao getDao(Class clz) throws SQLException {
        Dao dao = null;
        String className = clz.getSimpleName();
        if (mDaos.containsKey(className)){
            dao = mDaos.get(className);
        }
        if (dao == null){
            dao = super.getDao(clz);
            mDaos.put(className,dao);
        }
        return dao;
    }

    /**
     * 释放资源
     */
    @Override
    public void close() {
        super.close();
        for (String key : mDaos.keySet()) {
            Dao dao = mDaos.get(key);
            dao = null;
        }
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource,User.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource, int i, int i1) {
        try {
            TableUtils.dropTable(connectionSource, User.class, true);
            onCreate(sqLiteDatabase, connectionSource);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
