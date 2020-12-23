/*
 * Copyright (C) 2020 HoangDH
 */

package com.liv3ly.demo.di.component;

import com.liv3ly.demo.di.PerActivity;
import com.liv3ly.demo.di.module.ActivityModule;
import com.liv3ly.demo.ui.all.AllFragment;
import com.liv3ly.demo.ui.circles.CirclesFragment;
import com.liv3ly.demo.ui.squares.SquaresFragment;
import com.liv3ly.demo.ui.triangles.TrianglesFragment;

import dagger.Component;

/**
 * Created by HoangDH 23/12/2020.
 */

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent extends CommonActivityComponent {
    void inject(SquaresFragment fragment);

    void inject(CirclesFragment fragment);

    void inject(TrianglesFragment fragment);

    void inject(AllFragment fragment);
}
