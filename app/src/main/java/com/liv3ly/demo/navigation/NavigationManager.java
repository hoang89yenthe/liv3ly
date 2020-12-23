package com.liv3ly.demo.navigation;

import androidx.annotation.IdRes;
import androidx.fragment.app.FragmentManager;

import com.liv3ly.demo.ui.all.AllFragment;
import com.liv3ly.demo.ui.circles.CirclesFragment;
import com.liv3ly.demo.ui.squares.SquaresFragment;
import com.liv3ly.demo.ui.triangles.TrianglesFragment;

import java.util.HashMap;
import java.util.Objects;
import java.util.Stack;

import static com.liv3ly.demo.listeners.MainActivityItemSelectedListener.TAB_ALL;
import static com.liv3ly.demo.listeners.MainActivityItemSelectedListener.TAB_CIRCLE;
import static com.liv3ly.demo.listeners.MainActivityItemSelectedListener.TAB_SQUARES;
import static com.liv3ly.demo.listeners.MainActivityItemSelectedListener.TAB_TRIANGLES;

public class NavigationManager extends CommonNavigationManager {

    private static NavigationManager INSTANCE;

    // useless constructor
    private NavigationManager() {
    }

    public static NavigationManager getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new NavigationManager();
        }

        return INSTANCE;
    }

    public void init(FragmentManager fragmentManager, @IdRes int containerId, int defaultTab) {
        super.init(fragmentManager, containerId, defaultTab);

        mStacks = new HashMap<>();
        mStacks.put(TAB_SQUARES, new Stack<>());
        mStacks.put(TAB_CIRCLE, new Stack<>());
        mStacks.put(TAB_TRIANGLES, new Stack<>());
        mStacks.put(TAB_ALL, new Stack<>());
    }

    @Override
    public void selectTab(int tabName) {
        mCurrentTab = tabName;

        // First time this tab is selected. So add first fragment of that tab.
        if (Objects.requireNonNull(mStacks.get(tabName)).isEmpty()) {
            switch (tabName) {
                case TAB_SQUARES:
                    addFragment(tabName, new SquaresFragment(), null);
                    break;
                case TAB_CIRCLE:
                    addFragment(tabName, new CirclesFragment(), null);
                    break;
                case TAB_TRIANGLES:
                    addFragment(tabName, new TrianglesFragment(), null);
                    break;
                case TAB_ALL:
                    addFragment(tabName, new AllFragment(), null);
                    break;
            }
        } else {
            showLastFragment(tabName);
        }
    }
}
