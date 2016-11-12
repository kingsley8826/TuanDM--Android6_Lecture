package com.example.tu4nfpt.listviewintro.activities;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.example.tu4nfpt.listviewintro.R;

/**
 * Created by tu4nFPT on 08/11/2016.
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
