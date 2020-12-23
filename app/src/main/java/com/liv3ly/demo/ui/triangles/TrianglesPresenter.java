/*
 * Copyright (C) 2020 HoangDH
 */

package com.liv3ly.demo.ui.triangles;

import com.liv3ly.demo.data.DataManager;
import com.liv3ly.demo.ui.base.BasePresenter;
import com.liv3ly.demo.utils.rx.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by HoangDH 23/12/2020.
 */

public class TrianglesPresenter<V extends TrianglesMvpView> extends BasePresenter<V>
        implements TrianglesMvpPresenter<V> {

    String currentDay = "";

    @Inject
    public TrianglesPresenter(DataManager dataManager,
                              SchedulerProvider schedulerProvider,
                              CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }

    @Override
    public void getImageColor() {
        getMvpView().showLoading();
        getCompositeDisposable().add(getDataManager()
                .getImageURL()
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(response -> {
                    if (response != null) {
                        getMvpView().onGetImageColorSuccess(response.get(0).getHex());
                    }
                    getMvpView().hideLoading();
                }, throwable -> {
                    if (!isViewAttached()) {
                        return;
                    }
                    getMvpView().onGetImageColorFalse();
                    getMvpView().hideLoading();
                }));
    }
}
