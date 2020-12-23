/*
 * Copyright (C) 2020 HoangDH
 */

package com.liv3ly.demo.ui.triangles;
import com.liv3ly.demo.di.PerActivity;
import com.liv3ly.demo.ui.base.MvpPresenter;

/**
 * Created by HoangDH 23/12/2020.
 */

@PerActivity
public interface TrianglesMvpPresenter<V extends TrianglesMvpView> extends MvpPresenter<V> {
    void getImageColor();
}
