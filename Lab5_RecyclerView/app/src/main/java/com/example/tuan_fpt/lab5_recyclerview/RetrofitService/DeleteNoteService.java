package com.example.tuan_fpt.lab5_recyclerview.RetrofitService;

import com.example.tuan_fpt.lab5_recyclerview.models.Status;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Header;
import retrofit2.http.Path;

/**
 * Created by Tuan-FPT on 25/12/2016.
 */

public interface DeleteNoteService {
    @DELETE("todos/{id}")
    Call<Status> callDeleteNote(@Path("id") String id, @Header("token") String token);
}
