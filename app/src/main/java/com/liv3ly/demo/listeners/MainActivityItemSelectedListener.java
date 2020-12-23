package com.liv3ly.demo.listeners;

import android.view.MenuItem;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.liv3ly.demo.R;

import com.liv3ly.demo.databinding.ActivityMainBinding;
import com.liv3ly.demo.ui.all.AllFragment;

public class MainActivityItemSelectedListener extends CommonMainActivityItemSelectedListener {

    public static final int TAB_SQUARES = 1;
    public static final int TAB_CIRCLE = 2;
    public static final int TAB_TRIANGLES = 3;
    public static final int TAB_ALL = 4;

    public MainActivityItemSelectedListener(ActivityMainBinding viewBinding, FragmentManager fragmentManager, @IdRes int containerId) {
        super(viewBinding, fragmentManager, containerId, TAB_SQUARES);
    }

    /**
     * pop fragment on top of current tab's back stack
     *
     * @return boolean true if {@link Fragment} Fragment was popped,
     * false if Fragment is top level of the tab
     */
    @Override
    public boolean onBackPressed() {
        return navigationManager.popFragment();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // TODO setup toolbar properly
        switch (item.getItemId()) {
            case R.id.nav_squares:
                navigationManager.selectTab(TAB_SQUARES);
                return true;
            case R.id.nav_circles:
                navigationManager.selectTab(TAB_CIRCLE);
                return true;
            case R.id.nav_triangles:
                navigationManager.selectTab(TAB_TRIANGLES);
                return true;
            case R.id.nav_all:
                navigationManager.selectTab(TAB_ALL);
                return true;
        }
        return false;
    }

    // TODO remove this
    public void toBlankFragment() {
        navigationManager.addFragment(TAB_SQUARES, new AllFragment(), null);
    }
}
