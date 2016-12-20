package com.example.tuan_fpt.lab5_recyclerview.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tuan_fpt.lab5_recyclerview.R;
import com.example.tuan_fpt.lab5_recyclerview.models.Note;
import com.example.tuan_fpt.lab5_recyclerview.viewholders.AccountViewHolder;

import java.util.List;

/**
 * Created by Tuan-FPT on 18/12/2016.
 */

public class AccountAdapter extends RecyclerView.Adapter<AccountViewHolder>{
    @Override
    public AccountViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.list_item_note, parent, false);
        AccountViewHolder accountViewHolder = new AccountViewHolder(view);
        return accountViewHolder;
    }

    @Override
    public void onBindViewHolder(AccountViewHolder holder, int position) {
        Note note = Note.noteList.get(position);
        holder.bind(note);
    }

    @Override
    public int getItemCount() {
        return Note.noteList.size();
    }
}
