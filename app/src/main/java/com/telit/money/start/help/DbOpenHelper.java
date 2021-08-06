package com.telit.money.start.help;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.greendao.dao.AdviceBeanDao;
import com.greendao.dao.DaoMaster;


/**
 * author: qzx
 * Date: 2020/4/2 10:03
 */
public class DbOpenHelper extends DaoMaster.DevOpenHelper {
    public DbOpenHelper(Context context, String name) {
        super(context, name);
    }

    public DbOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
        super(context, name, factory);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //切记不要调用super.onUpgrade(db,oldVersion,newVersion)
        if (oldVersion < newVersion) {
            MigrationHelper.migrate(db, AdviceBeanDao.class);
        }
    }
}
