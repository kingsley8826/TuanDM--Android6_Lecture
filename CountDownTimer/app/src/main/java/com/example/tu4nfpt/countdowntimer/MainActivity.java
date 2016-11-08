package com.example.tu4nfpt.countdowntimer;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.toString();
    private static final String MIN_KEY = "min_key";
    private static final String SEC_KEY = "sec_key";
    private Button btnStart;
    private Button btnStop;
    private EditText edMin;
    private EditText edSec;
    private TextView tvClock;
    private int sec;
    private int min;
    private CountDownTimer timer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getPrefereces();
        if(savedInstanceState != null){
            min = savedInstanceState.getInt(MIN_KEY, 0);
            sec = savedInstanceState.getInt(SEC_KEY, 0);
            runClock();
        }
        addListener();
    }

    private void getPrefereces(){
        btnStart = (Button) findViewById(R.id.btn_Start);
        btnStop = (Button) findViewById(R.id.btn_Stop);
        edMin = (EditText) findViewById(R.id.ed_Min);
        edSec = (EditText) findViewById(R.id.ed_Sec);
        tvClock = (TextView) findViewById(R.id.tv_Clock);
    }
    private void addListener(){
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    min = Integer.parseInt(edMin.getText().toString());
                    sec = Integer.parseInt(edSec.getText().toString());
                }catch (Exception ex){
                    Toast.makeText(
                            MainActivity.this,
                            "Please input the integer number!",
                            Toast.LENGTH_SHORT
                    ).show();
                    if(timer != null){
                        timer.cancel();
                    }
                    return;
                }
                if(min < 0 || sec < 0 || sec >= 60){
                    Toast.makeText(
                            MainActivity.this,
                            "Please input the number in range!",
                            Toast.LENGTH_SHORT
                    ).show();
                    if(timer != null){
                        timer.cancel();
                    }
                    return;
                }
               runClock();
            }
        });
        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timer.cancel();
            }
        });
    }

    private void runClock(){
        int time = min * 60 + sec;
        if(timer != null){
            timer.cancel();
            timer = null;
        }
        timer = new CountDownTimer((time + 2) * 1000, 1000) {
            @Override
            public void onTick(long l) {
                tvClock.setText(String.format(" %02d : %02d", min, sec));
                sec--;
                if(sec < 0){
                    min--;
                    sec = 59;
                }
            }
            @Override
            public void onFinish() {
                Log.d(TAG,"finish");
            }
        }.start();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(MIN_KEY, min);
        outState.putInt(SEC_KEY, sec);
    }
}
