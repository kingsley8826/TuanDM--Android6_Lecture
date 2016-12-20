package com.example.tuan_fpt.lab5_recyclerview;

import android.util.Log;

import com.example.tuan_fpt.lab5_recyclerview.RetrofitService.LoginService;
import com.example.tuan_fpt.lab5_recyclerview.RetrofitService.RegisterService;
import com.example.tuan_fpt.lab5_recyclerview.RetrofitService.TodosServices;
import com.example.tuan_fpt.lab5_recyclerview.eventbus.OnUpdateRecyclerView;
import com.example.tuan_fpt.lab5_recyclerview.models.Account;
import com.example.tuan_fpt.lab5_recyclerview.models.Note;
import com.example.tuan_fpt.lab5_recyclerview.models.Status;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.GsonConverterFactory;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by Tuan-FPT on 18/12/2016.
 */

public class DbContext {
    private static final Retrofit ACCOUNT_RETROFIT = new Retrofit.Builder()
            .baseUrl("http://a-server.herokuapp.com/api/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public static Call<Status> getRegisterStatus(Account account){
        RequestBody requestBody = RequestBody.create(
                MediaType.parse("application/json"),
                (new Gson()).toJson(account));
        return ACCOUNT_RETROFIT.create(RegisterService.class).register(requestBody);
    }
    public static Call<Status> getLoginStatus(Account account){
        RequestBody requestBody = RequestBody.create(
                MediaType.parse("application/json"),
                (new Gson()).toJson(account));
        return ACCOUNT_RETROFIT.create(LoginService.class).login(requestBody);
    }
    private static Call<List<Note>> getCallNotes(String token){
        return ACCOUNT_RETROFIT.create(TodosServices.class).getNotes(token);
    }
    public static void getNotes(String token){
        getCallNotes(token).enqueue(new Callback<List<Note>>() {
            @Override
            public void onResponse(Call<List<Note>> call, Response<List<Note>> response) {
                Note.noteList = response.body();
                EventBus.getDefault().post(new OnUpdateRecyclerView());
            }

            @Override
            public void onFailure(Call<List<Note>> call, Throwable t) {

            }
        });
    }
}
