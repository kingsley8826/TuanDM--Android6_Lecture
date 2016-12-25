package com.example.tuan_fpt.lab5_recyclerview.RetrofitService;

import com.example.tuan_fpt.lab5_recyclerview.models.Account;
import com.example.tuan_fpt.lab5_recyclerview.models.Status;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by Tuan-FPT on 18/12/2016.
 */

public interface LoginService {
    @POST("login")
    Call<Status> callLogin(@Body RequestBody requestBody);
}
