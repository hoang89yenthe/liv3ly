/*
 * Copyright (C) 2020 HoangDH
 */

package com.liv3ly.demo.ui.squares;

import android.util.Log;

import com.androidnetworking.error.ANError;
import com.liv3ly.demo.data.DataManager;
import com.liv3ly.demo.ui.base.BasePresenter;
import com.liv3ly.demo.ui.base.MvpView;
import com.liv3ly.demo.utils.rx.SchedulerProvider;


import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by HoangDH 23/12/2020.
 */

public class SquaresPresenter<V extends SquaresMvpView> extends BasePresenter<V>
        implements SquaresMvpPresenter<V> {

    @Inject
    public SquaresPresenter(DataManager dataManager,
                            SchedulerProvider schedulerProvider,
                            CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }

    @Override
    public void getImageUrl() {
        getMvpView().showLoading();
        getCompositeDisposable().add(getDataManager()
                .getImageURL()
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(response -> {
                    if (response != null) {
                        getMvpView().onGetImageUrlSuccess(response.get(0).getImageUrl());
                    }
                    getMvpView().hideLoading();
                }, throwable -> {
                    if (!isViewAttached()) {
                        return;
                    }
                    getMvpView().onGetImageUrlFalse();
                    getMvpView().hideLoading();
                }));
    }
}
