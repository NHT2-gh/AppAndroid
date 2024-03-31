package com.example.testapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.testapp.adapter.ProductDetailOrderAdapter;
import com.example.testapp.api.ApiService;
import com.example.testapp.model.Order;
import com.example.testapp.model.OrderDetail;
import com.example.testapp.response.EntityStatusResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StaffChangeOrderStatusActivity extends AppCompatActivity {
    private ListView lvListProduct;
    private Button btnChangeStatus;
    private List<OrderDetail> data = new ArrayList<>();
    private ProductDetailOrderAdapter adapter_productDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff_change_order_status);
        setControl();
        setEvent();
    }
    private void setControl() {
        lvListProduct = findViewById(R.id.lv_listProduct);

        btnChangeStatus = findViewById(R.id.btn_changeStatus);
    }
    private void setEvent() {
        Khoitao();
        adapter_productDetail = new ProductDetailOrderAdapter(this, R.layout.layout_item_product_order, data);
        lvListProduct.setAdapter(adapter_productDetail);

        lvListProduct.getLayoutParams().height =data.size()*200;

        SharedPreferences sharedPreferences = getSharedPreferences("MyPerfs", Context.MODE_PRIVATE);
        String token = "Bearer " + sharedPreferences.getString("token", null);

        btnChangeStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateStatusOrder(token);
            }
        });
    }
    private void updateStatusOrder(String token) {
        Order order = new Order();
        order.setStatus(1);
        ApiService.apiservice.updateStatusOrder(token,1L, order).enqueue(new Callback<EntityStatusResponse<Order>>() {
            @Override
            public void onResponse(Call<EntityStatusResponse<Order>> call, Response<EntityStatusResponse<Order>> response) {
                if(response.isSuccessful()){
                    EntityStatusResponse<Order> resultResponse = response.body();
                    if(resultResponse != null){
                        Order orderResponse = resultResponse.getData();
                        Log.i("message", resultResponse.getMessage());
                    }
                }
            }
            @Override
            public void onFailure(Call<EntityStatusResponse<Order>> call, Throwable t) {
                Log.i("error", t.getMessage());
            }
        });

    }

    private void Khoitao() {
        data.add(new OrderDetail("1", "M","Cà phê","https://www.crane-tea.com/wp-content/uploads/2021/07/a4034263b1c3459d1cd2.jpg", 70000000L,1 ));
        data.add(new OrderDetail("2", "M","Cà phê","https://www.crane-tea.com/wp-content/uploads/2021/07/a4034263b1c3459d1cd2.jpg", 70000000L,1 ));

    }
}