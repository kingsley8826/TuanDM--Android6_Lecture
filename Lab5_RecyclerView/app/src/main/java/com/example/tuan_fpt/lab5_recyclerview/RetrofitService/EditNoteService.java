package com.example.tuan_fpt.lab5_recyclerview.RetrofitService;

import com.example.tuan_fpt.lab5_recyclerview.models.Note;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * Created by Tuan-FPT on 23/12/2016.
 */

public interface EditNoteService {
    @PUT("todos/{id}")
    Call<Note> callEditNote(@Path("id") String id, @Header("token") String token, @Body RequestBody requestBody);
}
