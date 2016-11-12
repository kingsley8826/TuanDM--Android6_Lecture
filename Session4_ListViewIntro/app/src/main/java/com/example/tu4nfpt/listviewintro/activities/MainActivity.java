package com.example.tu4nfpt.listviewintro.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ListView;

import com.example.tu4nfpt.listviewintro.R;
import com.example.tu4nfpt.listviewintro.fragments.DetailFragment;
import com.example.tu4nfpt.listviewintro.fragments.OnStudentUpdateListener;
import com.example.tu4nfpt.listviewintro.models.Student;

import java.util.ArrayList;

public class MainActivity extends BaseActivity implements OnStudentUpdateListener{

    public static final String TAG = MainActivity.class.toString();

    public static ArrayList<Student> students = Student.list;
    ArrayAdapter<Student> arrAdapter;

    private ListView lvStudents;
    private Button btnAdd;
    private FrameLayout emptyLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getPreferences();
        Log.d(TAG,"create");
        setupUI();
    }

    @Override
    protected void onRestart() {
        arrAdapter.notifyDataSetChanged();
        super.onRestart();
    }

    private void setupUI() {
        // Create adapter
        arrAdapter = new ArrayAdapter<Student>(this, android.R.layout.simple_list_item_1, students);
        lvStudents.setAdapter(arrAdapter);
        lvStudents.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Student student = students.get(i);
                if(findViewById(R.id.fl_detail) == null) {
                    Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                    intent.putExtra(DetailActivity.STUDENT_KEY, student);
                    intent.putExtra(DetailActivity.OPERATION_KEY, Student.OP_UPDATE);
                    startActivity(intent);
                }else {
                    DetailFragment detailFragment = DetailFragment.create(student, Student.OP_UPDATE);
                    detailFragment.setOnStudentUpdateListener(MainActivity.this);
                    changeFragment(R.id.fl_detail, detailFragment);
                }

            }
        });
        lvStudents.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                students.remove(i);
                arrAdapter.notifyDataSetChanged();
                return true; // false: onClick still will call
            }
        });
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(findViewById(R.id.fl_detail) == null) {
                    Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                    intent.putExtra(DetailActivity.OPERATION_KEY, Student.OP_ADD);
                    startActivity(intent);
                }else {
                    DetailFragment detailFragment = DetailFragment.create(Student.OP_ADD);
                    detailFragment.setOnStudentUpdateListener(MainActivity.this);
                    changeFragment(R.id.fl_detail, detailFragment);
                }
            }
        });
    }

    private void getPreferences(){
        lvStudents = (ListView) findViewById(R.id.lv_students);
        btnAdd = (Button) findViewById(R.id.btn_add);
        emptyLayout = (FrameLayout) findViewById(R.id.fl_detail);
    }

    @Override
    public void onUpdate() {
        arrAdapter.notifyDataSetChanged();
        emptyLayout.removeAllViews();
    }
}
