package com.example.tuan_fpt.lab5_recyclerview.RetrofitService;

import com.example.tuan_fpt.lab5_recyclerview.models.Note;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by Tuan-FPT on 18/12/2016.
 */

public interface ListNoteService {
    @GET("todos")
    Call<List<Note>> callNoteList(@Header("token") String token);
}
