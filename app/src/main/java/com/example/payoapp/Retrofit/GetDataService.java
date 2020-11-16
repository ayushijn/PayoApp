package com.example.payoapp.Retrofit;

import com.example.payoapp.Models.ApiResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GetDataService {
    @GET("users")
    Call<ApiResponse> getDataList(@Query("page") String page);
}

