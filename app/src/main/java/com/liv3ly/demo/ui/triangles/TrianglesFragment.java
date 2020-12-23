/*
 * Copyright (C) 2020 HoangDH
 */

package com.liv3ly.demo.ui.triangles;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
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
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.liv3ly.demo.R;
import com.liv3ly.demo.databinding.FragmentSquaresBinding;
import com.liv3ly.demo.databinding.FragmentTrianglesBinding;
import com.liv3ly.demo.di.component.ActivityComponent;
import com.liv3ly.demo.ui.base.BaseFragment;
import com.liv3ly.demo.utils.ScreenUtils;

import org.greenrobot.eventbus.EventBus;

import java.util.Random;

import javax.inject.Inject;


/**
 * Created by HoangDH 23/12/2020.
 */

public class TrianglesFragment extends BaseFragment
        implements TrianglesMvpView {

    @Inject
    TrianglesMvpPresenter<TrianglesMvpView> mPresenter;

    @Inject
    LinearLayoutManager mLayoutManager;

    FragmentTrianglesBinding binding;
    MotionEvent currentMotionEvent;

    int widthHeightValue;

    public static TrianglesFragment newInstance() {
        Bundle args = new Bundle();
        TrianglesFragment fragment = new TrianglesFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentTrianglesBinding.inflate(getLayoutInflater());
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
        Drawable drawable = ContextCompat.getDrawable(requireContext(), R.drawable.triangle);
        imageView.setImageDrawable(drawable);
        imageView.setColorFilter(Color.parseColor("#" + color));
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
        Drawable drawable = ContextCompat.getDrawable(requireContext(), R.drawable.triangle);
        Random rnd = new Random();
        int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
        imageView.setImageDrawable(drawable);
        imageView.setColorFilter(color);
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
