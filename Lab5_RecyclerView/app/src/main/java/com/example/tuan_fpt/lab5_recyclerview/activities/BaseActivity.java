package com.example.tuan_fpt.lab5_recyclerview.activities;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class BaseActivity extends AppCompatActivity {

    public void changeFragment(int resId, Fragment detailFragment){ // Fragment v4.app
        // get fragmentManager
        FragmentManager fragmentManager = getSupportFragmentManager();
        // Start replacing
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        //
        fragmentTransaction.replace(resId, detailFragment);

        fragmentTransaction.commit();
    }
}
