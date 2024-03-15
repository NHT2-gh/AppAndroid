package com.example.testapp.api;

import com.example.testapp.model.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiService {
    //base link:http://localhost:9999/
    Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();

    ApiService apiservice = new Retrofit.Builder().baseUrl("http://192.168.0.192:9999/").addConverterFactory(GsonConverterFactory.create(gson))
            .build().create(ApiService.class);

    @POST("auth/signup")
    Call<User> sendUser(@Body User user);

    @POST("auth/signin")
    Call<User> loginUser(@Body User user);
}
