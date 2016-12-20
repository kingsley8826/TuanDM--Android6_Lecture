package com.example.tuan_fpt.lab5_recyclerview.fragments;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tuan_fpt.lab5_recyclerview.DbContext;
import com.example.tuan_fpt.lab5_recyclerview.eventbus.OnChangeFragment;
import com.example.tuan_fpt.lab5_recyclerview.R;
import com.example.tuan_fpt.lab5_recyclerview.models.Account;
import com.example.tuan_fpt.lab5_recyclerview.models.Status;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginAndRegisterScreen extends Fragment {


    @BindView(R.id.et_userName)
    EditText etUserName;
    @BindView(R.id.et_passWord)
    EditText etPassWord;
    @BindView(R.id.btnLogin)
    Button btnLogin;
    @BindView(R.id.btnRegister)
    Button btnRegister;
    public LoginAndRegisterScreen() {
        // Required empty public constructor

    }

    @OnClick(R.id.btnRegister)
    public void registerClick(View view){
        Account account = new Account();
        account.setUserName(etUserName.getText().toString());
        account.setPassWord(etPassWord.getText().toString());
        DbContext.getRegisterStatus(account).enqueue(new Callback<Status>() {
            @Override
            public void onResponse(Call<Status> call, Response<Status> response) {
                Log.d("oc","register ngon");
                if(response.code() == 201){
                    Status status = response.body();
                    Toast.makeText(getContext(),status.getMessage(),Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getContext(),"User already exists",Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<Status> call, Throwable t) {

            }
        });
    }
    @OnClick(R.id.btnLogin)
    public void loginClick(View view){
        Account account = new Account();
        account.setUserName(etUserName.getText().toString());
        account.setPassWord(etPassWord.getText().toString());
        DbContext.getLoginStatus(account).enqueue(new Callback<Status>() {
            @Override
            public void onResponse(Call<Status> call, Response<Status> response) {
                Log.d("oc","login ngon");
                if(response.code() == 201){
                    Status status = response.body();
                    Toast.makeText(getContext(),status.getMessage(),Toast.LENGTH_SHORT).show();
                    saveTokenSharedPreferences(status.getToken());
                    OnChangeFragment onChangeFragment = new OnChangeFragment(new ListTodosFragment(),status.getToken());
                    EventBus.getDefault().post(onChangeFragment);
                }else{
                    Toast.makeText(getContext(),"User doesn't exist",Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<Status> call, Throwable t) {

            }
        });
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

}
