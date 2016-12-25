package com.example.tuan_fpt.lab5_recyclerview.fragments;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.tuan_fpt.lab5_recyclerview.DbContext;
import com.example.tuan_fpt.lab5_recyclerview.R;
import com.example.tuan_fpt.lab5_recyclerview.models.Color;
import com.example.tuan_fpt.lab5_recyclerview.models.Note;
import com.example.tuan_fpt.lab5_recyclerview.models.Status;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddNoteFragment extends Fragment {

    @BindView(R.id.et_title)
    EditText etTitle;
    @BindView(R.id.et_content)
    EditText etContent;
    @BindView(R.id.sp_list_color)
    Spinner spListColor;

    ArrayList<Color> colorList;
    ArrayAdapter<Color> colorAdapter;
    public AddNoteFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_note, container, false);
        ButterKnife.bind(this, view);
        setupColorSpinner();
        return view;
    }

    private void setupColorSpinner(){
        colorList = new ArrayList<>();
        colorList.add(new Color("White", "#FFFFFF"));
        colorList.add(new Color("Red", "#F44336"));
        colorList.add(new Color("Pink", "#E91E63"));
        colorList.add(new Color("Purple", "#8E24AA"));
        colorList.add(new Color("Blue", "#1565C0"));
        colorList.add(new Color("Cyan", "#26C6DA"));
        colorList.add(new Color("Teal", "#00695C"));
        colorList.add(new Color("Green", "#4CAF50"));
        colorList.add(new Color("Yellow", "#FFEB3B"));
        colorList.add(new Color("Orange", "#E65100"));
        colorList.add(new Color("Brown", "#3E2723"));
        colorList.add(new Color("Grey", "#616161"));
        colorAdapter = new ArrayAdapter<Color>(getContext(),android.R.layout.simple_spinner_dropdown_item, colorList);
        spListColor.setAdapter(colorAdapter);
        spListColor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Log.d("oc", spListColor.getSelectedItem() + "ec");
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

    }
    @OnClick(R.id.img_add_done)
    public void onDoneButtonClick(View view){
        String title = etTitle.getText().toString();
        String content = etContent.getText().toString();
        String color = ((Color) spListColor.getSelectedItem()).getKey();
        Note note = new Note(title,content,color, false); // when create new note then it must be not completed
        SharedPreferences sharedPref = getActivity().getSharedPreferences(Status.TOKEN_DATA, Context.MODE_PRIVATE);
        String token = sharedPref.getString(Status.TOKEN_KEY, "");
        DbContext.createNote(note, token );
    }

}
