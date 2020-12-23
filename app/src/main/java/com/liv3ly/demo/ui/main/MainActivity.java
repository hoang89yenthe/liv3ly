/*
 * Copyright (C) 2020 HoangDH
 */

package com.liv3ly.demo.ui.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.liv3ly.demo.R;
import com.liv3ly.demo.databinding.ActivityMainBinding;
import com.liv3ly.demo.listeners.MainActivityItemSelectedListener;
import com.liv3ly.demo.ui.base.BaseActivity;


import javax.inject.Inject;

/**
 * Created by HoangDH 23/12/2020.
 */

public class MainActivity extends BaseActivity implements MainMvpView {

    @Inject
    MainMvpPresenter<MainMvpView> mPresenter;

    private ActivityMainBinding viewBinding;

    private MainActivityItemSelectedListener mListener;

    public static Intent getStartIntent(Context context) {
        return new Intent(context, MainActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getActivityComponent().inject(this);

        viewBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(viewBinding.getRoot());

        mPresenter.onAttach(this);

        setUp();
    }

    @Override
    public void onBackPressed() {
//        FragmentManager fragmentManager = getSupportFragmentManager();
//        Fragment fragment = fragmentManager.findFragmentByTag(AboutFragment.TAG);
//        if (fragment == null) {
//            super.onBackPressed();
//        } else {
//            onFragmentDetached(AboutFragment.TAG);
//        }

        if (!mListener.onBackPressed()) {
            super.onBackPressed();
        }
    }


    @Override
    protected void onDestroy() {
        mPresenter.onDetach();
        super.onDestroy();
    }

    @Override
    public void onFragmentAttached() {
    }

    @Override
    public void onFragmentDetached(String tag) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentByTag(tag);
        if (fragment != null) {
            fragmentManager
                    .beginTransaction()
                    .disallowAddToBackStack()
                    .setCustomAnimations(R.anim.slide_left, R.anim.slide_right)
                    .remove(fragment)
                    .commitNow();
        }
    }

    @Override
    protected void setUp() {
        // setup bottom navigation bar
        BottomNavigationView bottomNav = viewBinding.bottomNavigation.container;
        mListener = new MainActivityItemSelectedListener(viewBinding, getSupportFragmentManager(), R.id.contentFragment);
        bottomNav.setOnNavigationItemSelectedListener(mListener);
        bottomNav.setSelectedItemId(R.id.nav_squares);

//        mPresenter.onNavMenuCreated();
//        setupCardContainerView();

        // TODO setup loading data after view is initialized
//        mPresenter.onViewInitialized();
    }

    //    private void setupCardContainerView() {
//
//        int screenWidth = ScreenUtils.getScreenWidth(this);
//        int screenHeight = ScreenUtils.getScreenHeight(this);
//
//        mCardsContainerView.getBuilder()
//                .setDisplayViewCount(3)
//                .setHeightSwipeDistFactor(10)
//                .setWidthSwipeDistFactor(5)
//                .setSwipeDecor(new SwipeDecor()
//                        .setViewWidth((int) (0.90 * screenWidth))
//                        .setViewHeight((int) (0.75 * screenHeight))
//                        .setPaddingTop(20)
//                        .setSwipeRotationAngle(10)
//                        .setRelativeScale(0.01f));
//
//        mCardsContainerView.addItemRemoveListener(count -> {
//            if (count == 0) {
//                // reload the contents again after 1 sec delay
//                new Handler(getMainLooper()).postDelayed(() -> mPresenter.onCardExhausted(), 800);
//            }
//        });
//    }

}
