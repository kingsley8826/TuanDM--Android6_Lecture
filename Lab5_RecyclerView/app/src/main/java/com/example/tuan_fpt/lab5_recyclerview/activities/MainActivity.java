package com.example.tuan_fpt.lab5_recyclerview.activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.tuan_fpt.lab5_recyclerview.DbContext;
import com.example.tuan_fpt.lab5_recyclerview.eventbus.OnChangeFragment;
import com.example.tuan_fpt.lab5_recyclerview.R;
import com.example.tuan_fpt.lab5_recyclerview.fragments.AddNoteFragment;
import com.example.tuan_fpt.lab5_recyclerview.fragments.ListNoteFragment;
import com.example.tuan_fpt.lab5_recyclerview.fragments.LoginAndRegisterFragment;
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

        changeFragment(R.id.fl_container, new LoginAndRegisterFragment(), false);

        SharedPreferences sharedPref = this.getSharedPreferences(Status.TOKEN_DATA, Context.MODE_PRIVATE);
        String token = sharedPref.getString(Status.TOKEN_KEY, "");
        if(!token.equalsIgnoreCase("")){
            DbContext.getNotes(token);
            changeFragment(R.id.fl_container, new ListNoteFragment(), false);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_account, menu);
        menu.findItem(R.id.it_logout).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() { // cach 1
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                changeFragment(R.id.fl_container, new LoginAndRegisterFragment(), false);
                SharedPreferences sharedPref = MainActivity.this.getSharedPreferences(Status.TOKEN_DATA,Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putString(Status.TOKEN_KEY, "");
                editor.apply();
                return false;
            }
        });
        return true;
    }

    @Subscribe // this is a default method of EventBus
    public void onEvent(OnChangeFragment onChangeFragment){ // receive message and execute message
        changeFragment(R.id.fl_container,onChangeFragment.getFragment(), onChangeFragment.isAddToBackStack());
    }
}
