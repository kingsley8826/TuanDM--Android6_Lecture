package com.example.tuan_fpt.lab5_recyclerview;

import android.util.Log;
import android.widget.Toast;

import com.example.tuan_fpt.lab5_recyclerview.RetrofitService.AddNoteService;
import com.example.tuan_fpt.lab5_recyclerview.RetrofitService.DeleteNoteService;
import com.example.tuan_fpt.lab5_recyclerview.RetrofitService.EditNoteService;
import com.example.tuan_fpt.lab5_recyclerview.RetrofitService.LoginService;
import com.example.tuan_fpt.lab5_recyclerview.RetrofitService.RegisterService;
import com.example.tuan_fpt.lab5_recyclerview.RetrofitService.ListNoteService;
import com.example.tuan_fpt.lab5_recyclerview.activities.MainActivity;
import com.example.tuan_fpt.lab5_recyclerview.eventbus.OnChangeFragment;
import com.example.tuan_fpt.lab5_recyclerview.eventbus.OnUpdateRecyclerView;
import com.example.tuan_fpt.lab5_recyclerview.fragments.ListNoteFragment;
import com.example.tuan_fpt.lab5_recyclerview.models.Account;
import com.example.tuan_fpt.lab5_recyclerview.models.Note;
import com.example.tuan_fpt.lab5_recyclerview.models.Status;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
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

    private static Call<Status> getRegisterStatus(Account account){
        RequestBody requestBody = RequestBody.create(
                MediaType.parse("application/json"),
                (new Gson()).toJson(account));
        return ACCOUNT_RETROFIT.create(RegisterService.class).callRegister(requestBody);
    }
    public static void register(Account account){
        DbContext.getRegisterStatus(account).enqueue(new Callback<Status>() {
            @Override
            public void onResponse(Call<Status> call, Response<Status> response) {
                Log.d("oc",response.code()+"code");
                if(response.code() == 201){
                    Status status = response.body();
                    EventBus.getDefault().post(status);
                }else{
                    EventBus.getDefault().post(new Status("", 0, "User already exists"));
                }
            }
            @Override
            public void onFailure(Call<Status> call, Throwable t) {
                EventBus.getDefault().post(new Status("", 0, "Internet error"));
            }
        });
    }
    private static Call<Status> getLoginStatus(Account account){
        RequestBody requestBody = RequestBody.create(
                MediaType.parse("application/json"),
                (new Gson()).toJson(account));
        return ACCOUNT_RETROFIT.create(LoginService.class).callLogin(requestBody);
    }
    public static void login(final Account account){
        getLoginStatus(account).enqueue(new Callback<Status>() {
            @Override
            public void onResponse(Call<Status> call, Response<Status> response) {
                if(response.code() == 201){
                    Status status = response.body();
                    OnChangeFragment onChangeFragment = new OnChangeFragment(new ListNoteFragment(), false);
                    EventBus.getDefault().post(status);
                    EventBus.getDefault().post(onChangeFragment);
                }else{
                    EventBus.getDefault().post(new Status("", 0, "User don't exit"));
                }
            }
            @Override
            public void onFailure(Call<Status> call, Throwable t) {
                EventBus.getDefault().post(new Status("", 0, "Internet error"));
            }
        });
    }

    private static Call<List<Note>> getCallNotes(String token){
        return ACCOUNT_RETROFIT.create(ListNoteService.class).callNoteList(token);
    }
    public static void getNotes(String token){
        getCallNotes(token).enqueue(new Callback<List<Note>>() {
            @Override
            public void onResponse(Call<List<Note>> call, Response<List<Note>> response) {
                List<Note>  noteList = response.body();
                Note.completeNoteList = new ArrayList<Note>();
                Note.incompleteNoteList = new ArrayList<Note>();
                for(Note note: noteList){
                    if(note.isCompleted()){
                        Note.completeNoteList.add(note);
                        EventBus.getDefault().post(new OnUpdateRecyclerView(true));
                    }else{
                        Note.incompleteNoteList.add(note);
                        EventBus.getDefault().post(new OnUpdateRecyclerView(false));
                    }
                }

            }
            @Override
            public void onFailure(Call<List<Note>> call, Throwable t) {

            }
        });
    }

    private static Call<List<Note>> getCallNote(Note note, String token){
        RequestBody requestBody = RequestBody.create(
                MediaType.parse("application/json"),
                (new Gson()).toJson(note));
        return ACCOUNT_RETROFIT.create(AddNoteService.class).callNote(token, requestBody);
    }
    public static void createNote(Note note, final String token){
        getCallNote(note, token).enqueue(new Callback<List<Note>>() {
            @Override
            public void onResponse(Call<List<Note>> call, Response<List<Note>> response) {
                OnChangeFragment onChangeFragment = new OnChangeFragment(new ListNoteFragment(), false);
                EventBus.getDefault().post(onChangeFragment);
                getNotes(token);
            }
            @Override
            public void onFailure(Call<List<Note>> call, Throwable t) {

            }
        });
    }
    private static Call<Note> getCallEditNote(String id, Note newNote, String token){
        RequestBody requestBody = RequestBody.create(
                MediaType.parse("application/json"),
                (new Gson()).toJson(newNote));
        return ACCOUNT_RETROFIT.create(EditNoteService.class).callEditNote(id, token, requestBody);
    }
    public static void editNote(String id, Note note, final String token){
        getCallEditNote(id, note, token).enqueue(new Callback<Note>() {
            @Override
            public void onResponse(Call<Note> call, Response<Note> response) {
                OnChangeFragment onChangeFragment = new OnChangeFragment(new ListNoteFragment(), false);
                EventBus.getDefault().post(onChangeFragment);
                getNotes(token);
            }

            @Override
            public void onFailure(Call<Note> call, Throwable t) {

            }
        });
    }
    private static Call<Status> getCallDeleteNote(String id, String token){
        return ACCOUNT_RETROFIT.create(DeleteNoteService.class).callDeleteNote(id, token);
    }
    public static void deleteNote(String id, final String token){
        getCallDeleteNote(id, token).enqueue(new Callback<Status>() {
            @Override
            public void onResponse(Call<Status> call, Response<Status> response) {
                getNotes(token);
            }

            @Override
            public void onFailure(Call<Status> call, Throwable t) {

            }
        });
    }
}
