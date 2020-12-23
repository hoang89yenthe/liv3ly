/*
 * Copyright (C) 2020 HoangDH
 */

package com.liv3ly.demo.ui.squares;
import com.liv3ly.demo.di.PerActivity;
import com.liv3ly.demo.ui.base.MvpPresenter;

/**
 * Created by HoangDH 23/12/2020.
 */

@PerActivity
public interface SquaresMvpPresenter<V extends SquaresMvpView> extends MvpPresenter<V> {
    void getImageUrl();
}
