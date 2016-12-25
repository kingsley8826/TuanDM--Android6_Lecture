package com.example.tuan_fpt.lab5_recyclerview.RetrofitService;

import com.example.tuan_fpt.lab5_recyclerview.models.Note;

import java.util.List;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

/**
 * Created by Tuan-FPT on 23/12/2016.
 */

public interface AddNoteService {
    @POST("todos")
    Call<List<Note>> callNote(@Header("token") String token, @Body RequestBody requestBody);
}
