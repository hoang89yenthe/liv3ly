/*
 * Copyright (C) 2020 HoangDH
 */

package com.liv3ly.demo;

import androidx.multidex.MultiDexApplication;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.interceptors.HttpLoggingInterceptor.Level;
import com.liv3ly.demo.data.DataManager;
import com.liv3ly.demo.di.component.ApplicationComponent;
import com.liv3ly.demo.di.component.DaggerApplicationComponent;
import com.liv3ly.demo.di.module.ApplicationModule;
import com.liv3ly.demo.utils.AppLogger;
import com.liv3ly.demo.BuildConfig;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import okhttp3.OkHttpClient;


/**
 * Created by HoangDH 23/12/2020.
 */

public class Liv3lyApp extends MultiDexApplication {

    @Inject
    DataManager mDataManager;

    private ApplicationComponent mApplicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        mApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this)).build();

        mApplicationComponent.inject(this);

        AppLogger.init();

        OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .build();
        AndroidNetworking.initialize(getApplicationContext(), okHttpClient);
        if (BuildConfig.DEBUG) {
            AndroidNetworking.enableLogging(Level.HEADERS);
        }
    }

    public ApplicationComponent getComponent() {
        return mApplicationComponent;
    }


    // Needed to replace the component with a test specific one
    public void setComponent(ApplicationComponent applicationComponent) {
        mApplicationComponent = applicationComponent;
    }
}
