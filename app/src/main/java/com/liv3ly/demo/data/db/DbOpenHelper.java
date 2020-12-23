/*
 * Copyright (C) 2020 HoangDH
 */

package com.liv3ly.demo.data.db;

import android.content.Context;

import com.liv3ly.demo.data.db.model.DaoMaster;
import com.liv3ly.demo.di.ApplicationContext;
import com.liv3ly.demo.di.DatabaseInfo;
import com.liv3ly.demo.utils.AppLogger;

import org.greenrobot.greendao.database.Database;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by HoangDH  on 08/12/16.
 */

@Singleton
public class DbOpenHelper extends DaoMaster.OpenHelper {

    @Inject
    public DbOpenHelper(@ApplicationContext Context context, @DatabaseInfo String name) {
        super(context, name);
    }

    @Override
    public void onUpgrade(Database db, int oldVersion, int newVersion) {
        super.onUpgrade(db, oldVersion, newVersion);
        AppLogger.d("DEBUG", "DB_OLD_VERSION : " + oldVersion + ", DB_NEW_VERSION : " + newVersion);
        switch (oldVersion) {
            case 1:
            case 2:
                //db.execSQL("ALTER TABLE " + UserDao.TABLENAME + " ADD COLUMN "
                // + UserDao.Properties.Name.columnName + " TEXT DEFAULT 'DEFAULT_VAL'");
        }
    }
}
