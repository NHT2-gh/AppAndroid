package com.example.testapp.api;

import com.example.testapp.model.Coupon;
import com.example.testapp.model.Customer;
import com.example.testapp.model.Order;
import com.example.testapp.model.ProductSaleRequest;
import com.example.testapp.model.Profile;
import com.example.testapp.model.StatisticRequest;
import com.example.testapp.model.User;
import com.example.testapp.response.ApiResponse;
import com.example.testapp.response.EntityStatusResponse;
import com.example.testapp.response.ListEntityStatusResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.net.InetAddress;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {
    //base link:http://....:9999/
    Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();

//    WifiManager wm = (WifiManager) getSystemService(WIFI_SERVICE);
//    String ip = Formatter.formatIpAddress(wm.getConnectionInfo().getIpAddress());


    ApiService apiservice = new Retrofit.Builder().baseUrl("http://192.168.0.195:9999/").addConverterFactory(GsonConverterFactory.create(gson))
            .build().create(ApiService.class);

    @POST("auth/signup")
    Call<ApiResponse> sendUser(@Body User user);

    @POST("auth/signin")
    Call<User> loginUser(@Body User user);

    @PUT("auth/change/{username}")
    Call<ApiResponse> changePassword(@Path("username") String username, @Body User user);

    @GET("api/users/find")
    Call<EntityStatusResponse<Customer>> getUserProfile(@Header("Authorization") String token);

    //statistic
    @GET("api/statistic/product")
    Call<ListEntityStatusResponse<ProductSaleRequest>> getStatisticProduct(@Header("Authorization") String token);

    @GET("api/statistic/year")
    Call<ListEntityStatusResponse<StatisticRequest>> getStatisticYear(@Header("Authorization") String token, @Query("year") String year);

    @GET("api/statistic/date")
    Call<ListEntityStatusResponse<ProductSaleRequest>> getStatisticProductByDate(@Header("Authorization") String token, @Query("start") String start, @Query("end") String end);

    //order
    @GET("api/admin/order/{orderId}/find")
    Call<EntityStatusResponse<Order>> getOrderById(@Header("Authorization") String token, @Path("orderId") Long orderId);

    @PUT("api/admin/order/{orderId}/status")
    Call<EntityStatusResponse<Order>> updateStatusOrder(@Header("Authorization") String token, @Path("orderId") Long orderId, @Body Order order);

    @GET("api/admin/order/all")
    Call<ListEntityStatusResponse<Order>> getAllOrder(@Header("Authorization") String token);
    @GET ("api/admin/order/status")
    Call<ListEntityStatusResponse<Order>> getOrderByStatus(@Header("Authorization") String token, @Query("status") Integer status );
    @GET ("api/admin/order/date")
    Call<ListEntityStatusResponse<Order>> getOrderByDate(@Header("Authorization")String token, @Query("start") String start, @Query("end") String end);
    @GET("api/coupon/all")
    Call<ListEntityStatusResponse<Coupon>> getAllCoupon(@Header("Authorization") String token);
}
