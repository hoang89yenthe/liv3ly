/*
 * Copyright (C) 2020 HoangDH
 */

package com.liv3ly.demo.di.component;

import android.app.Application;
import android.content.Context;

import com.liv3ly.demo.Liv3lyApp;
import com.liv3ly.demo.data.DataManager;
import com.liv3ly.demo.di.ApplicationContext;
import com.liv3ly.demo.di.module.ApplicationModule;
import com.liv3ly.demo.service.SyncService;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by HoangDH 23/12/2020.
 */

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    void inject(Liv3lyApp app);

    void inject(SyncService service);

    @ApplicationContext
    Context context();

    Application application();

    DataManager getDataManager();
}