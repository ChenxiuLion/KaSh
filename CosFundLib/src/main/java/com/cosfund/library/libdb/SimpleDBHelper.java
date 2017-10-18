package com.cosfund.library.libdb;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

/**
 * 作者 by Gavin on 2015/12/29 0029.
 * 描述：
 * ormlite-android 案例数据库操作类
 */
public class SimpleDBHelper extends OrmLiteSqliteOpenHelper {

    /**
     * 访问SimpleData表
     */
    private Dao<SimpleData, Integer> simpleDao = null;

    public SimpleDBHelper(Context context, String dbName, int dbVersion) {
        super(context, dbName, null, dbVersion);
    }

    /**
     * 创建以及存储数据库
     */
    @Override
    public void onCreate(SQLiteDatabase db, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, SimpleData.class);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 调整各种数据匹配新的版本号。
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            TableUtils.dropTable(connectionSource, SimpleData.class, true);
            onCreate(db, connectionSource);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 返回数据库访问对象(DAO)SimpleData类。它将创建或给缓存的值。
     */
    public Dao<SimpleData, Integer> getDao() throws SQLException {
        if (simpleDao == null) {
            simpleDao = getDao(SimpleData.class);
        }
        return simpleDao;
    }

    /**
     * 关闭数据库连接和明确的任何缓存dao。
     */
    @Override
    public void close() {
        super.close();
        simpleDao = null;
    }
}
