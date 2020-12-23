/*
 * Copyright (C) 2020 HoangDH
 */

package com.liv3ly.demo.ui.main;

import com.liv3ly.demo.data.DataManager;
import com.liv3ly.demo.ui.base.BasePresenter;
import com.liv3ly.demo.utils.rx.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import timber.log.Timber;


/**
 * Created by HoangDH 23/12/2020.
 */

public class MainPresenter<V extends MainMvpView> extends BasePresenter<V>
        implements MainMvpPresenter<V> {

    @Inject
    MainPresenter(DataManager dataManager,
                  SchedulerProvider schedulerProvider,
                  CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }
}
