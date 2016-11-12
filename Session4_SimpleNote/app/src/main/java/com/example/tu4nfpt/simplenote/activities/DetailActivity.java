package com.example.tu4nfpt.simplenote.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.tu4nfpt.simplenote.R;
import com.example.tu4nfpt.simplenote.fragment.DetailFragment;
import com.example.tu4nfpt.simplenote.fragment.OnUpdateListenter;
import com.example.tu4nfpt.simplenote.model.Note;

public class DetailActivity extends BaseActivity implements OnUpdateListenter{

    public static final String NOTE_KEY = "position";
    public static final String OPERATION_KEY = "operation";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Intent intent = getIntent();
        int operation = intent.getIntExtra(OPERATION_KEY, -1);
        Note note;
        DetailFragment detailFragment;
        if(operation == Note.OP_UPDATE){
            note = (Note) intent.getSerializableExtra(NOTE_KEY);
            for (int i = 0; i < Note.list.size(); i++) {
                if (Note.list.get(i).toString().equals(note.toString())) {
                    note = Note.list.get(i);
                    break;
                }
            }
            detailFragment = DetailFragment.create(note, operation);
        }else {
            detailFragment = DetailFragment.create(operation);
        }
        detailFragment.setOnUpdateListenter(this);
        changeFragment(R.id.fl_detail, detailFragment);
    }
    @Override
    public void onUpdate() {
        finish();
    }
}
