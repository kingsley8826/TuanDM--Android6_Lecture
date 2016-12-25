package com.example.tuan_fpt.lab5_recyclerview.fragments;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tuan_fpt.lab5_recyclerview.DbContext;
import com.example.tuan_fpt.lab5_recyclerview.R;
import com.example.tuan_fpt.lab5_recyclerview.models.Account;
import com.example.tuan_fpt.lab5_recyclerview.models.Status;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginAndRegisterFragment extends Fragment {

    @BindView(R.id.et_userName)
    EditText etUserName;
    @BindView(R.id.et_passWord)
    EditText etPassWord;
    @BindView(R.id.btnLogin)
    Button btnLogin;
    @BindView(R.id.btnRegister)
    Button btnRegister;
    public LoginAndRegisterFragment() {
        EventBus.getDefault().register(this);
        // Required empty public constructor
    }
    @OnClick(R.id.btnRegister)
    public void registerClick(View view){
        Account account = new Account(etUserName.getText().toString(), etPassWord.getText().toString());
        DbContext.register(account);
    }
    @OnClick(R.id.btnLogin)
    public void loginClick(View view){
        Account account = new Account(etUserName.getText().toString(), etPassWord.getText().toString());
        DbContext.login(account);
    }

    private void saveTokenSharedPreferences(String token){
        SharedPreferences sharedPref = getActivity().getSharedPreferences(Status.TOKEN_DATA,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(Status.TOKEN_KEY, token);
        editor.apply();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login_and_register_screen, container, false);
        ButterKnife.bind(this, view);
        return view;
    }
    @Subscribe
    public void onEvent(Status status){
        Toast.makeText(getContext(), status.getMessage(), Toast.LENGTH_SHORT).show();
        if(status.getMessage().equalsIgnoreCase("Logged in")){
            saveTokenSharedPreferences(status.getToken());
        }
    }
}
