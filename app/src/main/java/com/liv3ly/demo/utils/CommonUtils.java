/*
 * Copyright (C) 2020 HoangDH
 */

package com.liv3ly.demo.utils;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.provider.Settings;

import com.liv3ly.demo.R;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by HoangDH 23/12/2020.
 */

public final class CommonUtils {

    public static final long CLICK_TIME_INTERVAL = 400;
    public static long lastClickTime = System.currentTimeMillis();


    private CommonUtils() {
        // This utility class is not publicly instantiable
    }

    public static ProgressDialog showLoadingDialog(Context context) {
        ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.show();
        if (progressDialog.getWindow() != null) {
            progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
        progressDialog.setContentView(R.layout.progress_dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        return progressDialog;
    }

    @SuppressLint("all")
    public static String getDeviceId(Context context) {
        return Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
    }

    public static String loadJSONFromAsset(Context context, String jsonFileName)
            throws IOException {

        AssetManager manager = context.getAssets();
        InputStream is = manager.open(jsonFileName);

        int size = is.available();
        byte[] buffer = new byte[size];
        is.read(buffer);
        is.close();

        return new String(buffer, "UTF-8");
    }


    public static boolean isDuplicateClick() {
        long now = System.currentTimeMillis();
        if (now - lastClickTime < CLICK_TIME_INTERVAL) {
            lastClickTime = now;
            return true;
        } else {
            lastClickTime = now;
            return false;
        }
    }

}
