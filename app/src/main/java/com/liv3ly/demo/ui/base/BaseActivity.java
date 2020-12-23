/*
 * Copyright (C) 2020 HoangDH
 */

package com.liv3ly.demo.ui.base;

import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.google.android.material.snackbar.Snackbar;
import com.liv3ly.demo.R;
import com.liv3ly.demo.Liv3lyApp;
import com.liv3ly.demo.di.component.ActivityComponent;
import com.liv3ly.demo.di.component.DaggerActivityComponent;
import com.liv3ly.demo.di.module.ActivityModule;
import com.liv3ly.demo.ui.custom.AppErrorDialog;
import com.liv3ly.demo.ui.main.MainActivity;
import com.liv3ly.demo.utils.CommonUtils;
import com.liv3ly.demo.utils.NetworkUtils;

import io.github.inflationx.viewpump.ViewPumpContextWrapper;

/**
 * Created by HoangDH 23/12/2020.
 */

public abstract class BaseActivity extends AppCompatActivity
        implements MvpView, BaseFragment.Callback {

    private ProgressDialog mProgressDialog;

    private ActivityComponent mActivityComponent;

//    private Unbinder mUnBinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityComponent = DaggerActivityComponent.builder()
                .activityModule(new ActivityModule(this))
                .applicationComponent(((Liv3lyApp) getApplication()).getComponent())
                .build();

    }

    public ActivityComponent getActivityComponent() {
        return mActivityComponent;
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase));
    }

    @TargetApi(Build.VERSION_CODES.M)
    public void requestPermissionsSafely(String[] permissions, int requestCode) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(permissions, requestCode);
        }
    }

    @TargetApi(Build.VERSION_CODES.M)
    public boolean hasPermission(String permission) {
        return Build.VERSION.SDK_INT < Build.VERSION_CODES.M ||
                checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED;
    }

    @Override
    public void showLoading() {
        hideLoading();
        mProgressDialog = CommonUtils.showLoadingDialog(this);
    }

    @Override
    public void hideLoading() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.cancel();
        }
    }

    private void showSnackBar(String message) {
        Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content),
                message, Snackbar.LENGTH_SHORT);
        View sbView = snackbar.getView();
        TextView textView = (TextView) sbView
                .findViewById(com.google.android.material.R.id.snackbar_text);
        textView.setTextColor(ContextCompat.getColor(this, R.color.white));
        snackbar.show();
    }

    private void showDialogError(String message) {
        AppErrorDialog.getInstance(this).show(message);
    }

    @Override
    public void onError(String message) {
        if (message != null) {
            showDialogError(message);
        } else {
            showDialogError(getString(R.string.some_error));
        }
    }

    @Override
    public void onError(@StringRes int resId) {
        onError(getString(resId));
    }

    @Override
    public void showMessage(String message) {
        if (message != null) {
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, getString(R.string.some_error), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void showMessage(@StringRes int resId) {
        showMessage(getString(resId));
    }

    @Override
    public boolean isNetworkConnected() {
        return NetworkUtils.isNetworkConnected(getApplicationContext());
    }

    @Override
    public void onFragmentAttached() {

    }

    @Override
    public void onFragmentDetached(String tag) {

    }

    public void hideKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)
                    getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    @Override
    public void openActivityOnTokenExpire() {
        startActivity(MainActivity.getStartIntent(this));
        finish();
    }

//    public void setUnBinder(Unbinder unBinder) {
//        mUnBinder = unBinder;
//    }

    @Override
    protected void onDestroy() {

//        if (mUnBinder != null) {
//            mUnBinder.unbind();
//        }
        AppErrorDialog.getInstance(this).onDestroy();
        super.onDestroy();
    }

    protected abstract void setUp();
}
