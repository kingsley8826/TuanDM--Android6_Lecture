package com.example.tuan_fpt.lab5_recyclerview.activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import com.example.tuan_fpt.lab5_recyclerview.DbContext;
import com.example.tuan_fpt.lab5_recyclerview.eventbus.OnChangeFragment;
import com.example.tuan_fpt.lab5_recyclerview.R;
import com.example.tuan_fpt.lab5_recyclerview.fragments.ListTodosFragment;
import com.example.tuan_fpt.lab5_recyclerview.fragments.LoginAndRegisterScreen;
import com.example.tuan_fpt.lab5_recyclerview.models.Note;
import com.example.tuan_fpt.lab5_recyclerview.models.Status;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class MainActivity extends BaseActivity {

    private static final String TAG = MainActivity.class.toString();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EventBus.getDefault().register(this);
        SharedPreferences sharedPref = this.getSharedPreferences(Status.TOKEN_DATA, Context.MODE_PRIVATE);
        String token = sharedPref.getString(Status.TOKEN_KEY, "");
        if(!token.equalsIgnoreCase("")){
            DbContext.getNotes(token);
            changeFragment(R.id.fl_container, new ListTodosFragment());
        }else{
            LoginAndRegisterScreen loginAndRegisterScreen = new LoginAndRegisterScreen();
            changeFragment(R.id.fl_container, loginAndRegisterScreen);
        }
    }
    @Subscribe // this is a default method of EventBus
    public void onEvent(OnChangeFragment onChangeFragment){ // receive message and excuse message
        DbContext.getNotes(onChangeFragment.getToken());
        changeFragment(R.id.fl_container,onChangeFragment.getFragment());
    }
}
