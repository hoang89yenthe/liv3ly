/*
 * Copyright (C) 2020 HoangDH
 */

package com.liv3ly.demo.utils;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.Random;

/**
 * Created by HoangDH 23/12/2020.
 */

public class ScreenUtils {

    public static int MAX_WIDTH_VALUE = 45;
    public static int MAX_HEIGHT_VALUE = 45;
    public static int MIN_WIDTH_VALUE = 10;
    public static int MIN_HEIGHT_VALUE = 10;

    private ScreenUtils() {
        // This utility class is not publicly instantiable
    }

    public static int getScreenWidth(Context context) {
        WindowManager windowManager = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(dm);
        return dm.widthPixels;
    }

    public static int getScreenHeight(Context context) {
        WindowManager windowManager = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(dm);
        return dm.heightPixels;
    }

    public static int getRandomWidthValue(Context context) {
        int maxWidth = getScreenWidth(context) * MAX_WIDTH_VALUE / 100;
        int minWidth = getScreenWidth(context) * MIN_WIDTH_VALUE / 100;
        return new Random().nextInt((maxWidth - minWidth) + 1) + minWidth;
    }

    public static int getRandomHeightValue(Context context) {
        int maxWidth = getScreenHeight(context) * MAX_HEIGHT_VALUE / 100;
        int minWidth = getScreenHeight(context) * MIN_HEIGHT_VALUE / 100;
        return new Random().nextInt((maxWidth - minWidth) + 1) + minWidth;
    }
}
