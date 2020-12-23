/*
 * Copyright (C) 2020 HoangDH
 */

package com.liv3ly.demo.ui.all;

import android.annotation.SuppressLint;
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

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.liv3ly.demo.R;
import com.liv3ly.demo.databinding.FragmentAllBinding;
import com.liv3ly.demo.di.component.ActivityComponent;
import com.liv3ly.demo.ui.base.BaseFragment;
import com.liv3ly.demo.utils.ScreenUtils;

import java.util.Random;

import javax.inject.Inject;


/**
 * Created by HoangDH 23/12/2020.
 */

public class AllFragment extends BaseFragment
        implements AllMvpView {

    public static final String NEED_TO_RELOAD = "AllFragment";

    @Inject
    AllMvpPresenter<AllMvpView> mPresenter;

    @Inject
    LinearLayoutManager mLayoutManager;

    FragmentAllBinding binding;
    MotionEvent currentMotionEvent;

    int widthHeightValue;
    int modeDraw; // 1 - Squares; 2 - Circles; 3 - Triangles

    public static AllFragment newInstance() {
        Bundle args = new Bundle();
        AllFragment fragment = new AllFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentAllBinding.inflate(getLayoutInflater());
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

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void setUp(View view) {

        binding.llrootView.setOnTouchListener((v, event) -> {
            switch (event.getAction() & MotionEvent.ACTION_MASK) {
                case MotionEvent.ACTION_DOWN: {
                    currentMotionEvent = event;
                    modeDraw = getRandomDrawMode();
                    switch (modeDraw) {
                        case 1:
                            mPresenter.getImageUrl();
                            break;
                        case 2:
                        case 3:
                            mPresenter.getImageColor();
                            break;
                    }
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
        imageView.setOnTouchListener(getTouchListener(binding.llrootView));
        if (modeDraw == 2) {
            GradientDrawable gradientDrawable = new GradientDrawable();
            gradientDrawable.setShape(GradientDrawable.OVAL);
            gradientDrawable.setColor(Color.parseColor("#" + color));
            imageView.setImageDrawable(gradientDrawable);
        } else {
            Drawable drawable = ContextCompat.getDrawable(requireContext(), R.drawable.triangle);
            imageView.setImageDrawable(drawable);
            imageView.setColorFilter(Color.parseColor("#" + color));
        }

        widthHeightValue = ScreenUtils.getRandomWidthValue(requireContext());
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
        imageView.setOnTouchListener(getTouchListener(binding.llrootView));

        if (modeDraw == 2) {
            GradientDrawable gradientDrawable = new GradientDrawable();
            gradientDrawable.setShape(GradientDrawable.OVAL);
            Random rnd = new Random();
            int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
            gradientDrawable.setColor(color);
            imageView.setImageDrawable(gradientDrawable);
        } else {
            Drawable drawable = ContextCompat.getDrawable(requireContext(), R.drawable.triangle);
            Random rnd = new Random();
            int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
            imageView.setImageDrawable(drawable);
            imageView.setColorFilter(color);
        }

        widthHeightValue = ScreenUtils.getRandomWidthValue(requireContext());
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                widthHeightValue,
                widthHeightValue);
        params.leftMargin = x - 100;
        params.topMargin = y - 100;
        binding.llrootView.addView(imageView, params);
    }

    @Override
    public void onGetImageUrlSuccess(String url) {
        final int x = (int) currentMotionEvent.getRawX();
        final int y = (int) currentMotionEvent.getRawY();
        ImageView imageView = new ImageView(requireContext());
        Glide.with(requireContext())
                .load(url)
                .centerCrop()
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(imageView);
        imageView.setOnTouchListener(getTouchListener(binding.llrootView));

        widthHeightValue = ScreenUtils.getRandomWidthValue(requireContext());
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                widthHeightValue,
                widthHeightValue);
        params.leftMargin = x - 100;
        params.topMargin = y - 100;
        binding.llrootView.addView(imageView, params);
    }

    @Override
    public void onGetImageUrlFalse() {
        final int x = (int) currentMotionEvent.getRawX();
        final int y = (int) currentMotionEvent.getRawY();
        ImageView imageView = new ImageView(requireContext());
        Random rnd = new Random();
        int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
        imageView.setBackgroundColor(color);
        imageView.setOnTouchListener(getTouchListener(binding.llrootView));

        widthHeightValue = ScreenUtils.getRandomWidthValue(requireContext());
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                widthHeightValue,
                widthHeightValue);
        params.leftMargin = x - 100;
        params.topMargin = y - 100;
        binding.llrootView.addView(imageView, params);
    }

    private int getRandomDrawMode() {
        // 1 - Squares; 2 - Circles; 3 - Triangles
        return new Random().nextInt(3) + 1;
    }
}
