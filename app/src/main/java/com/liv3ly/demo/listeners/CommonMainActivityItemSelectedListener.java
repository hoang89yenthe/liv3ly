package com.liv3ly.demo.listeners;

import android.view.View;

import androidx.annotation.IdRes;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.liv3ly.demo.databinding.ActivityMainBinding;
import com.liv3ly.demo.navigation.NavigationManager;

public abstract class CommonMainActivityItemSelectedListener
        implements BottomNavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    protected ActivityMainBinding viewBinding;
    protected NavigationManager navigationManager;

    CommonMainActivityItemSelectedListener(ActivityMainBinding viewBinding,
                                           FragmentManager fragmentManager,
                                           @IdRes int containerId, int defaultTab) {
        this.viewBinding = viewBinding;
        navigationManager = NavigationManager.getInstance();
        navigationManager.init(fragmentManager, containerId, defaultTab);
    }

    @Override
    public void onClick(View v) {
    }

    protected abstract boolean onBackPressed();
}
