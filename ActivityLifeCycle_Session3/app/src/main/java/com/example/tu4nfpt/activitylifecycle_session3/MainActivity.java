package com.example.tu4nfpt.activitylifecycle_session3;

import android.content.Intent;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.toString();
    private TextView txtOnCreate;
    private TextView txtOnStart;
    private TextView txtOnRestart;
    private TextView txtOnResume;
    private TextView txtOnPause;
    private TextView txtOnStop;
    private TextView txtOnDestroy;
    private Button btnGotoActiB;

    private int onCreate;
    private int onStart;
    private int onRestart;
    private int onResume;
    private int onPause;
    private int onStop;
    private int onDestroy;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getPrefereces();
        addListener();
        onCreate++;
        Log.d(TAG,String.format("A Create: %s", onCreate));
        txtOnCreate.setText(String.format("onCreate: %s", onCreate));
    }

    private void getPrefereces(){
        txtOnCreate = (TextView) findViewById(R.id.txt_onCreate);
        txtOnStart = (TextView) findViewById(R.id.txt_onStart);
        txtOnRestart = (TextView) findViewById(R.id.txt_onRestart);
        txtOnResume = (TextView) findViewById(R.id.txt_onResume);
        txtOnPause = (TextView) findViewById(R.id.txt_onPause);
        txtOnStop = (TextView) findViewById(R.id.txt_onStop);
        txtOnDestroy = (TextView) findViewById(R.id.txt_onDestroy);
        btnGotoActiB = (Button) findViewById(R.id.btn_GoToActiB);
    }
    private void addListener(){
        btnGotoActiB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // create an intent
                Intent intent = new Intent(MainActivity.this, ActivityB.class);

                // change activity
                startActivity(intent);
            }
        });
    }
    @Override
    protected void onStart() {
        onStart++;
        Log.d(TAG,String.format("A Start: %s", onStart));
        txtOnStart.setText(String.format("onStart: %s", onStart));
        super.onStart();
    }

    @Override
    protected void onResume() {
        onResume++;
        Log.d(TAG,String.format("A Resume: %s", onResume));
        txtOnResume.setText(String.format("onResume: %s", onResume));
        super.onResume();
    }

    @Override
    protected void onRestart() {
        onRestart++;
        Log.d(TAG,String.format("A Restart: %s", onRestart));
        txtOnRestart.setText(String.format("onRestart: %s", onRestart));
        super.onRestart();
    }

    @Override
    protected void onPause() {
        onPause++;
        Log.d(TAG,String.format("A Pause: %s", onPause));
        txtOnPause.setText(String.format("onPause: %s", onPause));
        super.onPause();
    }

    @Override
    protected void onStop() {
        onStop++;
        Log.d(TAG,String.format("A Stop: %s", onStop));
        txtOnStop.setText(String.format("onStop: %s", onStop));
        super.onStop();
    }
    @Override
    protected void onDestroy() {
        onDestroy++;
        Log.d(TAG,String.format("A Destroy: %s", onDestroy));
        txtOnDestroy.setText(String.format("onDestroy: %s", onDestroy));
        super.onDestroy();
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {

        super.onSaveInstanceState(outState, outPersistentState);
    }
}
