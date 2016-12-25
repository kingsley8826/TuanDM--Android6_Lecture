package com.example.tuan_fpt.lab5_recyclerview.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tuan_fpt.lab5_recyclerview.R;
import com.example.tuan_fpt.lab5_recyclerview.adapters.AccountAdapter;
import com.example.tuan_fpt.lab5_recyclerview.eventbus.OnUpdateRecyclerView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class CompleteNoteFragment extends Fragment {

    @BindView(R.id.rv_complete_note)
    RecyclerView rvCompleteNote;
    AccountAdapter accountAdapter;
    public CompleteNoteFragment() {
        // Required empty public constructor
        EventBus.getDefault().register(this);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_complete_note, container, false);
        ButterKnife.bind(this, view);
        setupUI();
        return view;
    }

    private void setupUI() {
        accountAdapter = new AccountAdapter();
        accountAdapter.setCompleted(true);
        rvCompleteNote.setLayoutManager(new StaggeredGridLayoutManager(2, 1));
        rvCompleteNote.setAdapter(accountAdapter);
    }
    @Subscribe
    public void onEvent(OnUpdateRecyclerView onUpdateRecyclerView){
        if(onUpdateRecyclerView.isCompletedView()) {
            setupUI();
        }
    }
}
