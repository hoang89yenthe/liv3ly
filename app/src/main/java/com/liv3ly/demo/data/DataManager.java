/*
 * Copyright (C) 2020 HoangDH
 */

package com.liv3ly.demo.data;


import com.liv3ly.demo.data.db.DbHelper;
import com.liv3ly.demo.data.network.ApiHelper;
import com.liv3ly.demo.data.prefs.PreferencesHelper;

import io.reactivex.Observable;

/**
 * Created by HoangDH 23/12/2020.
 */

public interface DataManager extends DbHelper, PreferencesHelper, ApiHelper {

    void updateApiHeader(Long userId, String accessToken);
}
