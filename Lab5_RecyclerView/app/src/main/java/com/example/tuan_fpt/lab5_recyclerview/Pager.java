package com.example.tuan_fpt.lab5_recyclerview;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.tuan_fpt.lab5_recyclerview.fragments.CompleteNoteFragment;
import com.example.tuan_fpt.lab5_recyclerview.fragments.IncompleteNoteFragment;

/**
 * Created by Tuan-FPT on 23/12/2016.
 */

public class Pager extends FragmentStatePagerAdapter {
    int tabCount;

    //Constructor to the class
    public Pager(FragmentManager fm, int tabCount) {
        super(fm);
        //Initializing tab count
        this.tabCount= tabCount;
    }
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                IncompleteNoteFragment incompleteNoteFragment = new IncompleteNoteFragment();
                return incompleteNoteFragment;
            case 1:
                CompleteNoteFragment completeNoteFragment = new CompleteNoteFragment();
                return completeNoteFragment;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}
