package com.example.tu4nfpt.simplenote.activities;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by tu4nFPT on 12/11/2016.
 */

public class BaseActivity extends AppCompatActivity {

    public void changeFragment(int resId, Fragment detailFragment){
        // get fragmentManager
        FragmentManager fragmentManager = getSupportFragmentManager();

        // Start replacing

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        //
        fragmentTransaction.replace(resId, detailFragment);

        fragmentTransaction.commit();
    }
}
