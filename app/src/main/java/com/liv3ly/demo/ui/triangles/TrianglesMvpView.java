/*
 * Copyright (C) 2020 HoangDH
 */

package com.liv3ly.demo.ui.triangles;


import com.liv3ly.demo.ui.base.MvpView;

/**
 * Created by HoangDH 23/12/2020.
 */

public interface TrianglesMvpView extends MvpView {
    void onGetImageColorSuccess(String color);

    void onGetImageColorFalse();
}
