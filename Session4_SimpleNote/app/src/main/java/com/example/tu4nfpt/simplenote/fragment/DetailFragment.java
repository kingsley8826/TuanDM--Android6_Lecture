package com.example.tu4nfpt.simplenote.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;

import com.example.tu4nfpt.simplenote.R;
import com.example.tu4nfpt.simplenote.model.Note;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailFragment extends Fragment {


    private int operation;
    private Note note;

    private EditText edTittle;
    private EditText edContent;
    private Button btnSave;
    private OnUpdateListenter onUpdateListenter;

    public DetailFragment() {
        // Required empty public constructor
    }

    public void setOperation(int operation) {
        this.operation = operation;
    }

    public void setNote(Note note) {
        this.note = note;
    }

    public void setOnUpdateListenter(OnUpdateListenter onUpdateListenter) {
        this.onUpdateListenter = onUpdateListenter;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_detail, container, false);
        getPrefereces(view);
        setupUI();
        addListener();
        return  view;
    }

    private void addListener() {
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                note.setTitle(edTittle.getText().toString());
                note.setContent(edContent.getText().toString());
                if(operation == Note.OP_ADD) {
                    Note.list.add(note);
                }
                onUpdateListenter.onUpdate();
            }
        });
    }

    private void setupUI() {
        if(operation == Note.OP_UPDATE) {
            edTittle.setText(note.getTitle());
            edContent.setText(note.getContent());
        }
    }

    private void getPrefereces(View view){
        edTittle = (EditText) view.findViewById(R.id.ed_title);
        edContent = (EditText) view.findViewById(R.id.ed_content);
        btnSave = (Button) view.findViewById(R.id.btn_save);
    }
    public static DetailFragment create( Note note, int operation){
        DetailFragment detailFragment = new DetailFragment();
        detailFragment.setOperation(operation);
        detailFragment.setNote(note);
        return  detailFragment;
    }
    public static DetailFragment create(int operation){
        DetailFragment detailFragment = new DetailFragment();
        detailFragment.setOperation(operation);
        detailFragment.setNote(new Note());
        return  detailFragment;
    }

}
