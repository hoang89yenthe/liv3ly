/*
 * Copyright (C) 2020 HoangDH
 */

package com.liv3ly.demo.data.network;

import com.liv3ly.demo.data.network.model.AddGuardiansRequest;
import com.liv3ly.demo.data.network.model.ImageURLResponse;
import com.liv3ly.demo.data.network.model.VerifyOtpRequest;

import org.json.JSONObject;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;

/**
 * Created by HoangDH 23/12/2020.
 */

public interface ApiHelper {

    ApiHeader getApiHeader();

    Observable<List<ImageURLResponse>> getImageURL();
}
