package com.example.testapp.api;

import com.example.testapp.model.Customer;
import com.example.testapp.model.ProductSaleRequest;
import com.example.testapp.model.User;
import com.example.testapp.response.ApiResponse;
import com.example.testapp.response.EntityStatusResponse;
import com.example.testapp.response.ListEntityStatusResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ApiService {
    //base link:http://....:9999/
    Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();

    ApiService apiservice = new Retrofit.Builder().baseUrl("http://192.168.99.188:9999/").addConverterFactory(GsonConverterFactory.create(gson))
            .build().create(ApiService.class);

    @POST("auth/signup")
    Call<ApiResponse> sendUser(@Body User user);

    @POST("auth/signin")
    Call<User> loginUser(@Body User user);

    @PUT("auth/change/{username}")
    Call<ApiResponse> changePassword(@Path("username") String username, @Body User user);

    @GET("api/users/profile")
    Call<EntityStatusResponse<Customer>> getUserProfile(@Header("Authorization") String token);

    @GET("get")
    Call<ListEntityStatusResponse<ProductSaleRequest>> getStatisticProduct();


}
