package com.example.tu4nfpt.listviewintro.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.tu4nfpt.listviewintro.R;
import com.example.tu4nfpt.listviewintro.fragments.DetailFragment;
import com.example.tu4nfpt.listviewintro.fragments.OnStudentUpdateListener;
import com.example.tu4nfpt.listviewintro.models.Student;

public class DetailActivity extends BaseActivity implements OnStudentUpdateListener {

    public static final String STUDENT_KEY = "position";
    public static final String OPERATION_KEY = "operation";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Intent intent = getIntent();
        int operation = intent.getIntExtra(OPERATION_KEY, -1);
        Student student;
        DetailFragment detailFragment;
        if(operation == Student.OP_UPDATE) {
            student = (Student) intent.getSerializableExtra(STUDENT_KEY);
            for (int i = 0; i < Student.list.size(); i++) {
                if (Student.list.get(i).toString().equals(student.toString())) {
                    student = Student.list.get(i);
                    break;
                }
            }
            detailFragment = DetailFragment.create(student, operation);
        }else{
            detailFragment = DetailFragment.create(operation);
        }
        detailFragment.setOnStudentUpdateListener(this);
        changeFragment(R.id.fl_detail, detailFragment);

    }

    @Override
    public void onUpdate() {
        finish();
    }
}
