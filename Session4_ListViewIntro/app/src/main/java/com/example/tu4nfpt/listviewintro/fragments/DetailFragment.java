package com.example.tu4nfpt.listviewintro.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tu4nfpt.listviewintro.R;
import com.example.tu4nfpt.listviewintro.activities.DetailActivity;
import com.example.tu4nfpt.listviewintro.activities.MainActivity;
import com.example.tu4nfpt.listviewintro.models.Student;

import static com.example.tu4nfpt.listviewintro.activities.MainActivity.TAG;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailFragment extends Fragment {

    private int operation;
    private Student student;

    private EditText edName;
    private EditText edGender;
    private EditText edAge;
    private Button btnSave;
    private OnStudentUpdateListener onStudentUpdateListener;

    public DetailFragment() {
        // Required empty public constructor
    }


    public void setOperation(int operation) {
        this.operation = operation;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public void setOnStudentUpdateListener(OnStudentUpdateListener onStudentUpdateListener) {
        this.onStudentUpdateListener = onStudentUpdateListener;
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
                student.setName(edName.getText().toString());
                student.setGender(edGender.getText().toString());
                student.setAge(Integer.parseInt(edAge.getText().toString()));
                if(operation == Student.OP_ADD) {
                    Student.list.add(student);
                }
                onStudentUpdateListener.onUpdate();
            }
        });
    }

    private void setupUI() {
        if(operation == Student.OP_UPDATE) {
            edName.setText(student.getName());
            edGender.setText(student.getGender());
            edAge.setText(String.valueOf(student.getAge()));
        }
    }

    private void getPrefereces(View view){
        edName = (EditText) view.findViewById(R.id.tv_name);
        edGender = (EditText) view.findViewById(R.id.tv_gender);
        edAge = (EditText) view.findViewById(R.id.tv_age);
        btnSave = (Button) view.findViewById(R.id.btnSave);
    }
    public static DetailFragment create(Student student, int operation){
        DetailFragment detailFragment = new DetailFragment();
        detailFragment.setOperation(operation);
        detailFragment.setStudent(student);
        return  detailFragment;
    }
    public static DetailFragment create(int operation){
        DetailFragment detailFragment = new DetailFragment();
        detailFragment.setOperation(operation);
        detailFragment.setStudent(new Student());
        return  detailFragment;
    }

}
