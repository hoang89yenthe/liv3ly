package com.liv3ly.demo.navigation;

import android.os.Bundle;

import androidx.annotation.AnimRes;
import androidx.annotation.AnimatorRes;
import androidx.annotation.IdRes;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.liv3ly.demo.ui.base.BaseFragment;

import java.util.Map;
import java.util.Objects;
import java.util.Stack;

public abstract class CommonNavigationManager {

    protected FragmentManager fragmentManager;
    protected Map<Integer, Stack<BaseFragment>> mStacks;
    protected int mCurrentTab;
    protected @IdRes
    int containerId;

    // useless constructor for subclass singleton
    CommonNavigationManager() {
    }

    void init(FragmentManager fragmentManager, @IdRes int containerId, int defaultTab) {
        this.fragmentManager = fragmentManager;
        this.containerId = containerId;
        this.mCurrentTab = defaultTab;
    }

    private void hideAllTopFragments() {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        for (Map.Entry<Integer, Stack<BaseFragment>> entry : mStacks.entrySet()) {
            Stack<BaseFragment> stack = entry.getValue();
            if (!stack.isEmpty()) {
                fragmentTransaction.hide(stack.peek());
            }
        }
        fragmentTransaction.commit();
    }

    /**
     * add fragment to view and respective tab's back stack
     *
     * @param fragment {@link Fragment} Fragment to add
     * @param bundle   {@link Bundle} Bundle to pass to new Fragment
     */
    public void addFragment(int tabName, BaseFragment fragment, @Nullable Bundle bundle,
                            @AnimatorRes @AnimRes int enter, @AnimatorRes @AnimRes int exit) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(enter, exit);
        fragment.hideKeyboard();
        if (bundle != null) {
            fragment.setArguments(bundle);
        }

        if (!fragment.isAdded()) {
            hideAllTopFragments();
            fragmentTransaction.add(containerId, fragment, fragment.getFragmentTag());
        } else {
            fragmentTransaction.show(fragment);
        }
        Objects.requireNonNull(mStacks.get(tabName)).push(fragment);

        fragmentTransaction.commit();
    }

    public void addFragment(int tabName, BaseFragment fragment, @Nullable Bundle bundle) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragment.hideKeyboard();
        if (bundle != null) {
            fragment.setArguments(bundle);
        }

        if (!fragment.isAdded()) {
            hideAllTopFragments();
            fragmentTransaction.add(containerId, fragment, fragment.getFragmentTag());
        } else {
            fragmentTransaction.show(fragment);
        }

        Objects.requireNonNull(mStacks.get(tabName)).push(fragment);

        fragmentTransaction.commit();
    }

    private boolean isTopLevelFragment(int tabName) {
        return mStacks.get(tabName).size() <= 1;
    }

    /**
     * pop fragment on top of current tab's back stack
     *
     * @return boolean true if {@link Fragment} Fragment was popped,
     * false if Fragment is top level of the tab
     */
    public boolean popFragment() {
        if (isTopLevelFragment(mCurrentTab)) return false;

        Stack<BaseFragment> currentTabStack = mStacks.get(mCurrentTab);
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.remove(currentTabStack.pop());

        Fragment fragmentToShow = currentTabStack.peek();
        if (fragmentToShow.isAdded()) {
            fragmentTransaction.show(fragmentToShow);
        } else {
            fragmentTransaction.add(containerId, fragmentToShow);
        }
        fragmentTransaction.commit();

        return true;
    }

    public abstract void selectTab(int tabName);

    protected void showLastFragment(int tabName) {
        BaseFragment fragment = mStacks.get(tabName).peek();
        if (fragment.isHidden()) {
            hideAllTopFragments();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.show(fragment);
            fragmentTransaction.commit();
        } else {
            addFragment(tabName, fragment, null);
        }
    }
}
