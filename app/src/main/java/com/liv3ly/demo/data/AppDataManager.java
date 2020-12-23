/*
 * Copyright (C) 2020 HoangDH
 */

package com.liv3ly.demo.data;


import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.internal.$Gson$Types;
import com.google.gson.reflect.TypeToken;
import com.liv3ly.demo.data.db.DbHelper;
import com.liv3ly.demo.data.db.model.Option;
import com.liv3ly.demo.data.db.model.Question;
import com.liv3ly.demo.data.db.model.User;
import com.liv3ly.demo.data.network.ApiHeader;
import com.liv3ly.demo.data.network.ApiHelper;
import com.liv3ly.demo.data.network.model.AddGuardiansRequest;
import com.liv3ly.demo.data.network.model.ImageURLResponse;
import com.liv3ly.demo.data.network.model.VerifyOtpRequest;
import com.liv3ly.demo.data.prefs.PreferencesHelper;
import com.liv3ly.demo.di.ApplicationContext;
import com.liv3ly.demo.utils.AppConstants;
import com.liv3ly.demo.utils.CommonUtils;

import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;

/**
 * Created by HoangDH 23/12/2020.
 */

@Singleton
public class AppDataManager implements DataManager {

    private final Context mContext;
    private final DbHelper mDbHelper;
    private final PreferencesHelper mPreferencesHelper;
    private final ApiHelper mApiHelper;

    @Inject
    public AppDataManager(@ApplicationContext Context context,
                          DbHelper dbHelper,
                          PreferencesHelper preferencesHelper,
                          ApiHelper apiHelper) {
        mContext = context;
        mDbHelper = dbHelper;
        mPreferencesHelper = preferencesHelper;
        mApiHelper = apiHelper;
    }

    @Override
    public ApiHeader getApiHeader() {
        return mApiHelper.getApiHeader();
    }

    @Override
    public Observable<List<ImageURLResponse>> getImageURL() {
        return mApiHelper.getImageURL();
    }


    @Override
    public void updateApiHeader(Long userId, String accessToken) {
        mApiHelper.getApiHeader().getProtectedApiHeader().setUserId(userId);
        mApiHelper.getApiHeader().getProtectedApiHeader().setAccessToken(accessToken);
    }
}
