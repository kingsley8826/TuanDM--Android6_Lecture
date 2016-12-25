package com.example.tuan_fpt.lab5_recyclerview.fragments;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;

import com.example.tuan_fpt.lab5_recyclerview.DbContext;
import com.example.tuan_fpt.lab5_recyclerview.Pager;
import com.example.tuan_fpt.lab5_recyclerview.R;
import com.example.tuan_fpt.lab5_recyclerview.activities.MainActivity;
import com.example.tuan_fpt.lab5_recyclerview.adapters.AccountAdapter;
import com.example.tuan_fpt.lab5_recyclerview.eventbus.OnChangeFragment;
import com.example.tuan_fpt.lab5_recyclerview.eventbus.OnUpdateRecyclerView;
import com.example.tuan_fpt.lab5_recyclerview.models.Status;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListNoteFragment extends Fragment implements TabLayout.OnTabSelectedListener{

    public static boolean changeInFirstTime = true; // only addToBackStack in first time press button add
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.pager)
    ViewPager viewPager;

    public ListNoteFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_todos, container, false);
        ButterKnife.bind(this, view);
        sendGET();
        setupTab();
        return view;
    }
    public void setupTab(){
        //Adding the tabs using addTab() method
        tabLayout.addTab(tabLayout.newTab().setText("InCompleted Note"));
        tabLayout.addTab(tabLayout.newTab().setText("Completed Note"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        //Creating our pager adapter
        Pager pagerAdapter = new Pager(getActivity().getSupportFragmentManager(), tabLayout.getTabCount());

        //Adding adapter to pager
        viewPager.setAdapter(pagerAdapter);

        //Adding onTabSelectedListener to swipe views
        tabLayout.setOnTabSelectedListener(this);
    }

    public void sendGET(){
        SharedPreferences sharedPref = getActivity().getSharedPreferences(Status.TOKEN_DATA, Context.MODE_PRIVATE);
        String token = sharedPref.getString(Status.TOKEN_KEY, "");
        DbContext.getNotes(token);
    }

    @OnClick(R.id.img_add)
    public void onAddButtonClick(View view){
        Log.d("oc",String.valueOf(changeInFirstTime)+ "changeAdd");
        if(changeInFirstTime) { // only addToBackStack in first time press button add
            EventBus.getDefault().post(new OnChangeFragment(new AddNoteFragment(), true));
            changeInFirstTime = false;
        }else{
            EventBus.getDefault().post(new OnChangeFragment(new AddNoteFragment(), false));
        }

    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        viewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
}
