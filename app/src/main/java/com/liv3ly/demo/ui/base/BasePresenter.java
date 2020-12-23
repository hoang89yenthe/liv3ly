/*
 * Copyright (C) 2020 HoangDH
 */

package com.liv3ly.demo.ui.base;

import com.androidnetworking.common.ANConstants;
import com.androidnetworking.error.ANError;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.liv3ly.demo.R;
import com.liv3ly.demo.data.DataManager;
import com.liv3ly.demo.data.network.model.ApiError;
import com.liv3ly.demo.data.network.model.BaseErrorResponse;
import com.liv3ly.demo.utils.AppConstants;
import com.liv3ly.demo.utils.AppLogger;
import com.liv3ly.demo.utils.rx.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Base class that implements the Presenter interface and provides a base implementation for
 * onAttach() and onDetach(). It also handles keeping a reference to the mvpView that
 * can be accessed from the children classes by calling getMvpView().
 */
public class BasePresenter<V extends MvpView> implements MvpPresenter<V> {

    private final DataManager mDataManager;
    private final SchedulerProvider mSchedulerProvider;
    private CompositeDisposable mCompositeDisposable;

    private V mMvpView;

    @Inject
    public BasePresenter(DataManager dataManager,
                         SchedulerProvider schedulerProvider,
                         CompositeDisposable compositeDisposable) {
        this.mDataManager = dataManager;
        this.mSchedulerProvider = schedulerProvider;
        //this.mCompositeDisposable = compositeDisposable;
    }

    @Override
    public void onAttach(V mvpView) {
        mMvpView = mvpView;
        this.mCompositeDisposable = new CompositeDisposable();
    }

    @Override
    public void onDetach() {
        mCompositeDisposable.dispose();
        mMvpView = null;
    }

    public boolean isViewAttached() {
        return mMvpView != null;
    }

    public V getMvpView() {
        return mMvpView;
    }

    public void checkViewAttached() {
        if (!isViewAttached()) throw new MvpViewNotAttachedException();
    }

    public DataManager getDataManager() {
        return mDataManager;
    }

    public SchedulerProvider getSchedulerProvider() {
        return mSchedulerProvider;
    }

    public CompositeDisposable getCompositeDisposable() {
        return mCompositeDisposable;
    }

    @Override
    public void handleApiError(ANError error) {

    }

    @Override
    public void setUserAsLoggedOut() {
//        getDataManager().setAccessToken(null);
    }

    public static class MvpViewNotAttachedException extends RuntimeException {
        public MvpViewNotAttachedException() {
            super("Please call Presenter.onAttach(MvpView) before" +
                    " requesting data to the Presenter");
        }
    }

}
