/*
 * Copyright (C) 2020 HoangDH
 */

package com.liv3ly.demo.ui.squares;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.liv3ly.demo.R;
import com.liv3ly.demo.databinding.FragmentSquaresBinding;
import com.liv3ly.demo.di.component.ActivityComponent;
import com.liv3ly.demo.ui.base.BaseFragment;
import com.liv3ly.demo.utils.CommonUtils;
import com.liv3ly.demo.utils.ScreenUtils;

import java.util.Random;

import javax.inject.Inject;


/**
 * Created by HoangDH 23/12/2020.
 */

public class SquaresFragment extends BaseFragment
        implements SquaresMvpView {

    @Inject
    SquaresMvpPresenter<SquaresMvpView> mPresenter;

    @Inject
    LinearLayoutManager mLayoutManager;

    FragmentSquaresBinding binding;
    MotionEvent currentMotionEvent;

    int widthHeightValue;

    public static SquaresFragment newInstance() {
        Bundle args = new Bundle();
        SquaresFragment fragment = new SquaresFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentSquaresBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();

        ActivityComponent component = getActivityComponent();
        if (component != null) {
            component.inject(this);
            mPresenter.onAttach(this);
        }
        return view;
    }

    @Override
    protected void setUp(View view) {
        binding.llrootView.setOnTouchListener((v, event) -> {
            switch (event.getAction() & MotionEvent.ACTION_MASK) {
                case MotionEvent.ACTION_DOWN: {
                    currentMotionEvent = event;
                    mPresenter.getImageUrl();
                    break;
                }
            }
            binding.llrootView.invalidate();
            return false;
        });
    }

    @Override
    protected void onDeviceShake(int count) {
        binding.llrootView.removeAllViewsInLayout();
    }

    @Override
    public void onDestroyView() {
        mPresenter.onDetach();
        super.onDestroyView();
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

        widthHeightValue =  ScreenUtils.getRandomWidthValue(requireContext());
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
        imageView.setOnClickListener(v -> {
            if (CommonUtils.isDuplicateClick()) {
                imageView.setBackgroundColor(color);
            }
        });

        widthHeightValue =  ScreenUtils.getRandomWidthValue(requireContext());
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                widthHeightValue,
                widthHeightValue);
        params.leftMargin = x - 100;
        params.topMargin = y - 100;
        binding.llrootView.addView(imageView, params);
    }
}
