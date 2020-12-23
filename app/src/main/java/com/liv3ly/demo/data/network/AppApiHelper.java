/*
 * Copyright (C) 2020 HoangDH
 */

package com.liv3ly.demo.data.network;

import com.google.gson.reflect.TypeToken;
import com.liv3ly.demo.data.network.model.ImageURLResponse;
import com.rx2androidnetworking.Rx2AndroidNetworking;
import com.liv3ly.demo.data.network.model.AddGuardiansRequest;
import com.liv3ly.demo.data.network.model.VerifyOtpRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;

/**
 * Created by HoangDH 23/12/2020.
 */

@Singleton
public class AppApiHelper implements ApiHelper {

    private ApiHeader mApiHeader;

    @Inject
    public AppApiHelper(ApiHeader apiHeader) {
        mApiHeader = apiHeader;
    }

    @Override
    public ApiHeader getApiHeader() {
        return mApiHeader;
    }

    @Override
    public Observable<List<ImageURLResponse>> getImageURL() {
        return Rx2AndroidNetworking.get(ApiEndPoint.GET_IMAGE_URL)
                .addHeaders(mApiHeader.getProtectedApiHeader())
                .build()
                .getParseObservable(new TypeToken<List<ImageURLResponse>>() {
                });
    }
}

