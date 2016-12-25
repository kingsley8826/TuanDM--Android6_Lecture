package com.example.tuan_fpt.lab5_recyclerview.eventbus;

import android.support.v4.app.Fragment;

/**
 * Created by Tuan-FPT on 19/12/2016.
 */

public class OnChangeFragment {

    private Fragment fragment;
    private boolean isAddToBackStack;

    public OnChangeFragment(Fragment fragment, boolean isAddToBackStack) {
        this.fragment = fragment;
        this.isAddToBackStack = isAddToBackStack;
    }

    public Fragment getFragment() {
        return fragment;
    }

    public void setFragment(Fragment fragment) {
        this.fragment = fragment;
    }

    public boolean isAddToBackStack() {
        return isAddToBackStack;
    }

    public void setAddToBackStack(boolean addToBackStack) {
        isAddToBackStack = addToBackStack;
    }
}
