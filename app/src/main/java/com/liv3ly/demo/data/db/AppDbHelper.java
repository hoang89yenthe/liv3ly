/*
 * Copyright (C) 2020 HoangDH
 */

package com.liv3ly.demo.data.db;

import com.liv3ly.demo.data.db.model.DaoMaster;
import com.liv3ly.demo.data.db.model.DaoSession;
import com.liv3ly.demo.data.db.model.Option;
import com.liv3ly.demo.data.db.model.Question;
import com.liv3ly.demo.data.db.model.User;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;


/**
 * Created by HoangDH  on 08/12/16.
 */

@Singleton
public class AppDbHelper implements DbHelper {

    private final DaoSession mDaoSession;

    @Inject
    AppDbHelper(DbOpenHelper dbOpenHelper) {
        mDaoSession = new DaoMaster(dbOpenHelper.getWritableDb()).newSession();
    }
}
