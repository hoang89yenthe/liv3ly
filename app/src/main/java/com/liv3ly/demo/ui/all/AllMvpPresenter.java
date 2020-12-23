/*
 * Copyright (C) 2020 HoangDH
 */

package com.liv3ly.demo.ui.all;

import com.liv3ly.demo.di.PerActivity;
import com.liv3ly.demo.ui.base.MvpPresenter;

/**
 * Created by HoangDH 23/12/2020.
 */

@PerActivity
public interface AllMvpPresenter<V extends AllMvpView> extends MvpPresenter<V> {
    void getImageColor();

    void getImageUrl();
}
