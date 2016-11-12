package com.example.tu4nfpt.simplenote.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ListView;

import com.example.tu4nfpt.simplenote.R;
import com.example.tu4nfpt.simplenote.fragment.DetailFragment;
import com.example.tu4nfpt.simplenote.fragment.OnUpdateListenter;
import com.example.tu4nfpt.simplenote.model.Note;

import java.util.ArrayList;

public class MainActivity extends BaseActivity implements OnUpdateListenter{

    private ListView lvNote;
    private Button btnAdd;

    ArrayList<Note> notes = Note.list;

    ArrayAdapter<Note> arrayAdapter;
    FrameLayout emptyLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getReferences();
        setupUI();

    }

    @Override
    protected void onRestart() {
        arrayAdapter.notifyDataSetChanged();
        super.onRestart();
    }

    private void getReferences(){
        lvNote = (ListView) findViewById(R.id.lv_note);
        btnAdd = (Button) findViewById(R.id.btn_add);
        emptyLayout = (FrameLayout) findViewById(R.id.fl_detail);
    }

    private void setupUI(){
        arrayAdapter = new ArrayAdapter<Note>(this, android.R.layout.simple_list_item_1, notes);
        lvNote.setAdapter(arrayAdapter);
        addListener();
    }

    private void addListener(){
        lvNote.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Note note = notes.get(i);
                if(findViewById(R.id.fl_detail) == null){
                    Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                    intent.putExtra(DetailActivity.NOTE_KEY, note);
                    intent.putExtra(DetailActivity.OPERATION_KEY, Note.OP_UPDATE);
                    startActivity(intent);
                }else {
                    DetailFragment detailFragment = DetailFragment.create(note, Note.OP_UPDATE);
                    detailFragment.setOnUpdateListenter(MainActivity.this);
                    changeFragment(R.id.fl_detail, detailFragment);
                }
            }
        });

        lvNote.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                notes.remove(i);
                arrayAdapter.notifyDataSetInvalidated();
                return true;
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(findViewById(R.id.fl_detail) == null){
                    Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                    intent.putExtra(DetailActivity.OPERATION_KEY, Note.OP_ADD);
                    startActivity(intent);
                }else{
                    DetailFragment detailFragment = DetailFragment.create(Note.OP_ADD);
                    detailFragment.setOnUpdateListenter(MainActivity.this);
                    changeFragment(R.id.fl_detail, detailFragment);
                }
            }
        });
    }

    @Override
    public void onUpdate() {
        arrayAdapter.notifyDataSetChanged();
        emptyLayout.removeAllViews();
    }
}
