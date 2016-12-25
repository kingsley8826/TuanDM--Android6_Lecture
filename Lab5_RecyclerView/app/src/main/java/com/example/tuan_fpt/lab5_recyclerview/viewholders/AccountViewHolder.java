package com.example.tuan_fpt.lab5_recyclerview.viewholders;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.tuan_fpt.lab5_recyclerview.R;
import com.example.tuan_fpt.lab5_recyclerview.models.Note;

import butterknife.BindColor;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Tuan-FPT on 18/12/2016.
 */
public class AccountViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_content)
    TextView tvContent;
    @BindView(R.id.ll_note)
    LinearLayout llNote;
    public AccountViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
    public void bind(Note note){
        tvTitle.setText(note.getTitle());
        tvContent.setText(note.getContent());
        if (!note.getColor().equalsIgnoreCase("")) {
            llNote.setBackgroundColor(Color.parseColor(note.getColor()));
        }
    }
}
