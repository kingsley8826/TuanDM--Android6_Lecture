package com.example.tuan_fpt.session91_login_nwc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tuan_fpt.session91_login_nwc.jsonmodels.Acount;
import com.example.tuan_fpt.session91_login_nwc.jsonmodels.LoginStatus;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {


    private static final String TAG = MainActivity.class.toString();
    private EditText edUserName;
    private EditText edPassWord;
    private Button btnLogin;
    String url = "https://a5-tumblelog.herokuapp.com/api/login";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getReferences();
        addListener();
    }
    private void getReferences(){
        edUserName = (EditText) findViewById(R.id.ed_userName);
        edPassWord = (EditText) findViewById(R.id.ed_passWord);
        btnLogin = (Button) findViewById(R.id.btnLogin);
    }
    private void addListener(){
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendPost();
            }
        });
    }
    private void sendPost(){
        // create client
        OkHttpClient client = new OkHttpClient();

        // create post body
        // 1. create Media Type
        // 2. crate data
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        String userName = edUserName.getText().toString();
        String passWord = edPassWord.getText().toString();
        Acount acount = new Acount(userName, passWord);
        Gson gson = new Gson();
        String data = gson.toJson(acount);
        Log.d(TAG, "data: " + data);
        RequestBody requestBody = RequestBody.create(JSON, data);

        // create request
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d(TAG, "fail");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String body = response.body().string();
                Log.d(TAG, body);
                Gson gson = new Gson();
                LoginStatus loginStatus = gson.fromJson(body, LoginStatus.class);
                final String status;
                if(loginStatus.getCode() == 0){
                    status = "Login fail! Ahihi";
                } else {
                    status = "Login successfully!";
                }
                MainActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(MainActivity.this, status, Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });
    }
}
