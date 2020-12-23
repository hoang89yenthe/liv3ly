/*
 * Copyright (C) 2020 HoangDH
 */

package com.liv3ly.demo.ui.main;


import com.liv3ly.demo.di.PerActivity;
import com.liv3ly.demo.ui.base.MvpPresenter;

/**
 * Created by HoangDH 23/12/2020.
 */

@PerActivity
public interface MainMvpPresenter<V extends MainMvpView> extends MvpPresenter<V> {

}
