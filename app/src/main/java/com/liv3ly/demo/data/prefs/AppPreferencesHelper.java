/*
 * Copyright (C) 2020 HoangDH
 */

package com.liv3ly.demo.data.prefs;

import android.content.Context;
import android.content.SharedPreferences;

import com.liv3ly.demo.data.DataManager;
import com.liv3ly.demo.di.ApplicationContext;
import com.liv3ly.demo.di.PreferenceInfo;
import com.liv3ly.demo.utils.AppConstants;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by HoangDH 23/12/2020.
 */

@Singleton
public class AppPreferencesHelper implements PreferencesHelper {

    private final SharedPreferences mPrefs;

    @Inject
    public AppPreferencesHelper(@ApplicationContext Context context,
                                @PreferenceInfo String prefFileName) {
        mPrefs = context.getSharedPreferences(prefFileName, Context.MODE_PRIVATE);
    }

}
