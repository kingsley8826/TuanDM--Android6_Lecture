package com.example.tuan_fpt.lab5_recyclerview.eventbus;

import android.support.v4.app.Fragment;

/**
 * Created by Tuan-FPT on 19/12/2016.
 */

public class OnChangeFragment {

    private Fragment fragment;
    private String token;

    public OnChangeFragment(Fragment fragment, String token) {
        this.fragment = fragment;
        this.token = token;
    }

    public Fragment getFragment() {
        return fragment;
    }

    public String getToken() {
        return token;
    }
}
