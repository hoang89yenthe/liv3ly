/*
 * Copyright (C) 2020 HoangDH
 */

package com.liv3ly.demo.ui.squares;


import com.liv3ly.demo.ui.base.MvpView;

/**
 * Created by HoangDH 23/12/2020.
 */

public interface SquaresMvpView extends MvpView {
    void onGetImageUrlSuccess(String url);

    void onGetImageUrlFalse();
}
