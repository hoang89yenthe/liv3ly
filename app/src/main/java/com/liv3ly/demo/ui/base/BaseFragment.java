/*
 * Copyright (C) 2020 HoangDH
 */

package com.liv3ly.demo.ui.base;

import android.app.ProgressDialog;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;

import com.liv3ly.demo.di.component.ActivityComponent;
import com.liv3ly.demo.utils.CommonUtils;
import com.liv3ly.demo.utils.ShakeUtil;

/**
 * Created by HoangDH 23/12/2020.
 */

public abstract class BaseFragment extends Fragment implements MvpView {

    private BaseActivity mActivity;
    //    private Unbinder mUnBinder;
    private ProgressDialog mProgressDialog;

    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    private ShakeUtil mShakeDetector;
    private int xDelta, yDelta;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // ShakeDetector initialization
        mSensorManager = (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);
        mAccelerometer = mSensorManager
                .getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mShakeDetector = new ShakeUtil();
        mShakeDetector.setOnShakeListener(new ShakeUtil.OnShakeListener() {

            @Override
            public void onShake(int count) {
                onDeviceShake(count);
            }
        });
        setUp(view);
    }

    protected abstract void onDeviceShake(int count);

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof BaseActivity) {
            BaseActivity activity = (BaseActivity) context;
            this.mActivity = activity;
            activity.onFragmentAttached();
        }
    }

    @Override
    public void showLoading() {
        hideLoading();
        mProgressDialog = CommonUtils.showLoadingDialog(this.getContext());
    }

    @Override
    public void hideLoading() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.cancel();
        }
    }

    @Override
    public void onError(String message) {
        if (mActivity != null) {
            mActivity.onError(message);
        }
    }

    @Override
    public void onError(@StringRes int resId) {
        if (mActivity != null) {
            mActivity.onError(resId);
        }
    }

    @Override
    public void showMessage(String message) {
        if (mActivity != null) {
            mActivity.showMessage(message);
        }
    }

    @Override
    public void showMessage(@StringRes int resId) {
        if (mActivity != null) {
            mActivity.showMessage(resId);
        }
    }

    @Override
    public boolean isNetworkConnected() {
        if (mActivity != null) {
            return mActivity.isNetworkConnected();
        }
        return false;
    }

    @Override
    public void onDetach() {
        mActivity = null;
        super.onDetach();
    }

    @Override
    public void hideKeyboard() {
        if (mActivity != null) {
            mActivity.hideKeyboard();
        }
    }

    @Override
    public void openActivityOnTokenExpire() {
        if (mActivity != null) {
            mActivity.openActivityOnTokenExpire();
        }
    }

    public ActivityComponent getActivityComponent() {
        if (mActivity != null) {
            return mActivity.getActivityComponent();
        }
        return null;
    }

    public BaseActivity getBaseActivity() {
        return mActivity;
    }

    //    public void setUnBinder(Unbinder unBinder) {
//        mUnBinder = unBinder;
//    }

    protected abstract void setUp(View view);

    public String getFragmentTag() {
        return this.getClass().getSimpleName();
    }

//    public abstract void handleReceivedData(Bundle bundle);

    @Override
    public void onDestroy() {
//        if (mUnBinder != null) {
//            mUnBinder.unbind();
//        }
        mSensorManager.unregisterListener(mShakeDetector);
        super.onDestroy();
    }

    @Override
    public void onStart() {
        super.onStart();
        mSensorManager.registerListener(mShakeDetector, mAccelerometer, SensorManager.SENSOR_DELAY_UI);

    }

    public interface Callback {

        void onFragmentAttached();

        void onFragmentDetached(String tag);
    }

    public View.OnTouchListener getTouchListener(View rootView) {
        View.OnTouchListener touchListener = (view, event) -> {
            final int x = (int) event.getRawX();
            final int y = (int) event.getRawY();

            switch (event.getAction() & MotionEvent.ACTION_MASK) {
                case MotionEvent.ACTION_DOWN: {
                    FrameLayout.LayoutParams lParams = (FrameLayout.LayoutParams) view.getLayoutParams();
                    xDelta = x - lParams.leftMargin;
                    yDelta = y - lParams.topMargin;
                    break;
                }

                case MotionEvent.ACTION_MOVE: {
                    if (x - xDelta + view.getWidth() <= rootView.getWidth()
                            && y - yDelta + view.getHeight() <= rootView.getHeight()
                            && x - xDelta >= 0
                            && y - yDelta >= 0) {
                        FrameLayout.LayoutParams layoutParams =
                                (FrameLayout.LayoutParams) view.getLayoutParams();
                        layoutParams.leftMargin = x - xDelta;
                        layoutParams.topMargin = y - yDelta;
                        layoutParams.rightMargin = 0;
                        layoutParams.bottomMargin = 0;
                        view.setLayoutParams(layoutParams);
                    }
                    break;
                }
            }
            rootView.invalidate();
            return true;
        };
        return touchListener;
    }

}
