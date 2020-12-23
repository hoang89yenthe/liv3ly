/*
 * Copyright (C) 2020 HoangDH
 */

package com.liv3ly.demo.di.module;

import android.app.Service;

import dagger.Module;

/**
 * Created by HoangDH  on 01/02/17.
 */

@Module
public class ServiceModule {

    private final Service mService;

    public ServiceModule(Service service) {
        mService = service;
    }
}
