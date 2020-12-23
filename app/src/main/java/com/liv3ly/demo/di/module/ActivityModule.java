/*
 * Copyright (C) 2020 HoangDH
 */

package com.liv3ly.demo.di.module;

import androidx.appcompat.app.AppCompatActivity;

import com.liv3ly.demo.ui.all.AllMvpPresenter;
import com.liv3ly.demo.ui.all.AllMvpView;
import com.liv3ly.demo.ui.all.AllPresenter;
import com.liv3ly.demo.ui.circles.CirclesMvpPresenter;
import com.liv3ly.demo.ui.circles.CirclesMvpView;
import com.liv3ly.demo.ui.circles.CirclesPresenter;
import com.liv3ly.demo.ui.squares.SquaresMvpPresenter;
import com.liv3ly.demo.ui.squares.SquaresMvpView;
import com.liv3ly.demo.ui.squares.SquaresPresenter;
import com.liv3ly.demo.ui.triangles.TrianglesMvpPresenter;
import com.liv3ly.demo.ui.triangles.TrianglesMvpView;
import com.liv3ly.demo.ui.triangles.TrianglesPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by HoangDH 23/12/2020.
 */

@Module
public class ActivityModule extends CommonActivityModule {

    public ActivityModule(AppCompatActivity activity) {
        super(activity);
    }

    @Provides
    SquaresMvpPresenter<SquaresMvpView> provideSquares(
            SquaresPresenter<SquaresMvpView> presenter) {
        return presenter;
    }

    @Provides
    TrianglesMvpPresenter<TrianglesMvpView> provideTriangles(
            TrianglesPresenter<TrianglesMvpView> presenter) {
        return presenter;
    }

    @Provides
    CirclesMvpPresenter<CirclesMvpView> provideCircles(
            CirclesPresenter<CirclesMvpView> presenter) {
        return presenter;
    }

    @Provides
    AllMvpPresenter<AllMvpView> provideAll(
            AllPresenter<AllMvpView> presenter) {
        return presenter;
    }

}
