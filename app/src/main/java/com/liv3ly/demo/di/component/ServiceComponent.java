/*
 * Copyright (C) 2020 HoangDH
 */

package com.liv3ly.demo.di.component;

import com.liv3ly.demo.di.PerService;
import com.liv3ly.demo.di.module.ServiceModule;
import com.liv3ly.demo.service.SyncService;

import dagger.Component;

/**
 * Created by HoangDH  on 01/02/17.
 */

@PerService
@Component(dependencies = ApplicationComponent.class, modules = ServiceModule.class)
public interface ServiceComponent {

    void inject(SyncService service);

}
