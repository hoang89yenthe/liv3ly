/*
 * Copyright (C) 2020 HoangDH
 */

package com.liv3ly.demo.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;

import com.liv3ly.demo.data.DataManager;
import com.liv3ly.demo.utils.AppLogger;

import javax.inject.Inject;

/**
 * Created by HoangDH  on 01/02/17.
 */

public class SyncService extends Service {

    @Inject
    DataManager mDataManager;

    public static Intent getStartIntent(Context context) {
        return new Intent(context, SyncService.class);
    }

    public static void start(Context context) {
        Intent starter = new Intent(context, SyncService.class);
        context.startService(starter);
    }

    public static void stop(Context context) {
        context.stopService(new Intent(context, SyncService.class));
    }

    @Override
    public void onCreate() {
        super.onCreate();

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        AppLogger.d("SyncService started");
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        AppLogger.d("SyncService stopped");
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
