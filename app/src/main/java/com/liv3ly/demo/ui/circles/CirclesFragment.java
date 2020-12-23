/*
 * Copyright (C) 2020 HoangDH
 */

package com.liv3ly.demo.ui.circles;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.liv3ly.demo.databinding.FragmentCirclesBinding;
import com.liv3ly.demo.di.component.ActivityComponent;
import com.liv3ly.demo.ui.base.BaseFragment;
import com.liv3ly.demo.utils.ScreenUtils;

import java.util.Random;

import javax.inject.Inject;


/**
 * Created by HoangDH 23/12/2020.
 */

public class CirclesFragment extends BaseFragment
        implements CirclesMvpView {

    @Inject
    CirclesMvpPresenter<CirclesMvpView> mPresenter;

    @Inject
    LinearLayoutManager mLayoutManager;

    FragmentCirclesBinding binding;
    MotionEvent currentMotionEvent;

    int widthHeightValue;

    public static CirclesFragment newInstance() {
        Bundle args = new Bundle();
        CirclesFragment fragment = new CirclesFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentCirclesBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();

        ActivityComponent component = getActivityComponent();
        if (component != null) {
            component.inject(this);
            mPresenter.onAttach(this);
        }
        return view;
    }

    @Override
    protected void onDeviceShake(int count) {
        binding.llrootView.removeAllViewsInLayout();
    }

    @Override
    protected void setUp(View view) {
        binding.llrootView.setOnTouchListener((v, event) -> {
            switch (event.getAction() & MotionEvent.ACTION_MASK) {
                case MotionEvent.ACTION_DOWN: {
                    currentMotionEvent = event;
                    mPresenter.getImageColor();
                    break;
                }
            }
            binding.llrootView.invalidate();
            return false;
        });
    }

    @Override
    public void onDestroyView() {
        mPresenter.onDetach();
        super.onDestroyView();
    }


    @Override
    public void onGetImageColorSuccess(String color) {
        final int x = (int) currentMotionEvent.getRawX();
        final int y = (int) currentMotionEvent.getRawY();
        ImageView imageView = new ImageView(requireContext());
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setShape(GradientDrawable.OVAL);
        gradientDrawable.setColor(Color.parseColor("#" + color));
        imageView.setImageDrawable(gradientDrawable);
        imageView.setOnTouchListener(getTouchListener(binding.llrootView));

        widthHeightValue =  ScreenUtils.getRandomWidthValue(requireContext());
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                widthHeightValue,
                widthHeightValue);
        params.leftMargin = x - 100;
        params.topMargin = y - 100;
        binding.llrootView.addView(imageView, params);

    }

    @Override
    public void onGetImageColorFalse() {
        final int x = (int) currentMotionEvent.getRawX();
        final int y = (int) currentMotionEvent.getRawY();
        ImageView imageView = new ImageView(requireContext());
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setShape(GradientDrawable.OVAL);
        Random rnd = new Random();
        int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
        gradientDrawable.setColor(color);
        imageView.setImageDrawable(gradientDrawable);
        imageView.setOnTouchListener(getTouchListener(binding.llrootView));
        widthHeightValue =  ScreenUtils.getRandomWidthValue(requireContext());
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                widthHeightValue,
                widthHeightValue);
        params.leftMargin = x - 100;
        params.topMargin = y - 100;
        binding.llrootView.addView(imageView, params);
    }
}
