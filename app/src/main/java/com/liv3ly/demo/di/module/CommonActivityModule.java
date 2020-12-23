/*
 * Copyright (C) 2020 HoangDH
 */

package com.liv3ly.demo.di.module;

import android.content.Context;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.liv3ly.demo.di.ActivityContext;
import com.liv3ly.demo.di.PerActivity;
import com.liv3ly.demo.ui.main.MainMvpPresenter;
import com.liv3ly.demo.ui.main.MainMvpView;
import com.liv3ly.demo.ui.main.MainPresenter;
import com.liv3ly.demo.utils.rx.AppSchedulerProvider;
import com.liv3ly.demo.utils.rx.SchedulerProvider;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;


/**
 * Created by HoangDH 23/12/2020.
 */

@Module
public class CommonActivityModule {

    protected AppCompatActivity mActivity;

    CommonActivityModule(AppCompatActivity activity) {
        this.mActivity = activity;
    }

    @Provides
    @ActivityContext
    Context provideContext() {
        return mActivity;
    }

    @Provides
    AppCompatActivity provideActivity() {
        return mActivity;
    }

    @Provides
    CompositeDisposable provideCompositeDisposable() {
        return new CompositeDisposable();
    }

    @Provides
    SchedulerProvider provideSchedulerProvider() {
        return new AppSchedulerProvider();
    }

    @Provides
    @PerActivity
    MainMvpPresenter<MainMvpView> provideMainPresenter(
            MainPresenter<MainMvpView> presenter) {
        return presenter;
    }

    @Provides
    LinearLayoutManager provideLinearLayoutManager(AppCompatActivity activity) {
        return new LinearLayoutManager(activity);
    }

}
