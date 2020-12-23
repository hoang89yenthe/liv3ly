/*
 * Copyright (C) 2020 HoangDH
 */

package com.liv3ly.demo.di.module;

import android.app.Application;
import android.content.Context;

import com.liv3ly.demo.BuildConfig;

import com.liv3ly.demo.data.AppDataManager;
import com.liv3ly.demo.data.DataManager;
import com.liv3ly.demo.data.db.AppDbHelper;
import com.liv3ly.demo.data.db.DbHelper;
import com.liv3ly.demo.data.network.ApiHeader;
import com.liv3ly.demo.data.network.ApiHelper;
import com.liv3ly.demo.data.network.AppApiHelper;
import com.liv3ly.demo.data.prefs.AppPreferencesHelper;
import com.liv3ly.demo.data.prefs.PreferencesHelper;
import com.liv3ly.demo.di.ApiInfo;
import com.liv3ly.demo.di.ApplicationContext;
import com.liv3ly.demo.di.DatabaseInfo;
import com.liv3ly.demo.di.PreferenceInfo;
import com.liv3ly.demo.utils.AppConstants;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by HoangDH 23/12/2020.
 */

@Module
public class ApplicationModule {

    private final Application mApplication;

    public ApplicationModule(Application application) {
        mApplication = application;
    }

    @Provides
    @ApplicationContext
    Context provideContext() {
        return mApplication;
    }

    @Provides
    Application provideApplication() {
        return mApplication;
    }

    @Provides
    @DatabaseInfo
    String provideDatabaseName() {
        return AppConstants.DB_NAME;
    }

    @Provides
    @ApiInfo
    String provideApiKey() {
        return BuildConfig.API_KEY;
    }

    @Provides
    @PreferenceInfo
    String providePreferenceName() {
        return AppConstants.PREF_NAME;
    }

    @Provides
    @Singleton
    DataManager provideDataManager(AppDataManager appDataManager) {
        return appDataManager;
    }

    @Provides
    @Singleton
    DbHelper provideDbHelper(AppDbHelper appDbHelper) {
        return appDbHelper;
    }

    @Provides
    @Singleton
    PreferencesHelper providePreferencesHelper(AppPreferencesHelper appPreferencesHelper) {
        return appPreferencesHelper;
    }

    @Provides
    @Singleton
    ApiHelper provideApiHelper(AppApiHelper appApiHelper) {
        return appApiHelper;
    }

    @Provides
    @Singleton
    ApiHeader.ProtectedApiHeader provideProtectedApiHeader(@ApiInfo String apiKey,
                                                           PreferencesHelper preferencesHelper) {
        return new ApiHeader.ProtectedApiHeader(
                apiKey,
                0L,
                "");
    }
}
