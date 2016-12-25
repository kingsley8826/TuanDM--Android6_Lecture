package com.example.tuan_fpt.lab5_recyclerview.adapters;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.tuan_fpt.lab5_recyclerview.DbContext;
import com.example.tuan_fpt.lab5_recyclerview.R;
import com.example.tuan_fpt.lab5_recyclerview.eventbus.OnChangeFragment;
import com.example.tuan_fpt.lab5_recyclerview.fragments.AddNoteFragment;
import com.example.tuan_fpt.lab5_recyclerview.fragments.EditNoteFragment;
import com.example.tuan_fpt.lab5_recyclerview.fragments.ListNoteFragment;
import com.example.tuan_fpt.lab5_recyclerview.models.Note;
import com.example.tuan_fpt.lab5_recyclerview.models.Status;
import com.example.tuan_fpt.lab5_recyclerview.viewholders.AccountViewHolder;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tuan-FPT on 18/12/2016.
 */

public class AccountAdapter extends RecyclerView.Adapter<AccountViewHolder>{

    private int countComplete = 0;
    private int countIncomplete = 0;
    private boolean completed;
    @Override
    public AccountViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.list_item_note, parent, false);
        if(completed){
            Note note = Note.completeNoteList.get(countComplete);
            addListener(view, note);
            countComplete++;
        }else {
            Note note = Note.incompleteNoteList.get(countIncomplete);
            addListener(view, note);
            countIncomplete++;
        }
        AccountViewHolder accountViewHolder = new AccountViewHolder(view);
        return accountViewHolder;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    private void addListener(final View view, final Note note) {
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditNoteFragment editNoteFragment = new EditNoteFragment();
                editNoteFragment.setNote(note);
                Log.d("oc",String.valueOf(ListNoteFragment.changeInFirstTime)+ "changeClick");
                if(ListNoteFragment.changeInFirstTime) { // only addToBackStack in first time edit note
                    EventBus.getDefault().post(new OnChangeFragment(editNoteFragment, true));
                    ListNoteFragment.changeInFirstTime = false;
                }else{
                    EventBus.getDefault().post(new OnChangeFragment(editNoteFragment, false));
                }

            }
        });
        view.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                SharedPreferences sharedPreferences = view.getContext().getSharedPreferences(Status.TOKEN_DATA, Context.MODE_PRIVATE);
                String token = sharedPreferences.getString(Status.TOKEN_KEY, "");
                DbContext.deleteNote(note.getId().toString(), token);
                return false;
            }
        });
    }

    @Override
    public void onBindViewHolder(AccountViewHolder holder, int position) {
        Note note;
        if(completed){
            note = Note.completeNoteList.get(position);
        }else {
            note = Note.incompleteNoteList.get(position);
        }
        holder.bind(note);
    }

    @Override
    public int getItemCount() {
        int size;
        if(completed){
            size = Note.completeNoteList.size();
        }else{
            size = Note.incompleteNoteList.size();
        }
        return size;
    }
}
