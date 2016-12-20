package com.example.tuan_fpt.lab5_recyclerview.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tuan_fpt.lab5_recyclerview.R;
import com.example.tuan_fpt.lab5_recyclerview.adapters.AccountAdapter;
import com.example.tuan_fpt.lab5_recyclerview.eventbus.OnUpdateRecyclerView;
import com.example.tuan_fpt.lab5_recyclerview.models.Note;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListTodosFragment extends Fragment {


    @BindView(R.id.rv_note)
    RecyclerView rvNote;
    AccountAdapter accountAdapter;
    public ListTodosFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_todos, container, false);
        ButterKnife.bind(this, view);
        EventBus.getDefault().register(this);
        // Prepare Adapter (view holder)
        accountAdapter = new AccountAdapter();
        rvNote.setLayoutManager(new GridLayoutManager(this.getContext(), 2));

        // Config recycler view
        rvNote.setAdapter(accountAdapter);
        return view;
    }
    @Subscribe
    public void onEvent(OnUpdateRecyclerView onUpdateRecyclerView){
        accountAdapter = new AccountAdapter();
        rvNote.setAdapter(accountAdapter);
        for(int i = 0; i < Note.noteList.size(); i++){
            Log.d("ocdo2", Note.noteList.get(i).toString());
        }
    }
}
