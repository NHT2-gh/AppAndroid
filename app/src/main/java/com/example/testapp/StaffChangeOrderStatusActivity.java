package com.example.testapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.testapp.adapter.ProductDetailOrderAdapter;
import com.example.testapp.api.ApiService;
import com.example.testapp.model.Customer;
import com.example.testapp.model.Order;
import com.example.testapp.model.OrderDetail;
import com.example.testapp.response.EntityStatusResponse;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StaffChangeOrderStatusActivity extends AppCompatActivity {
    private ListView lvListProduct;
    private Button btnChangeStatus;
    private List<OrderDetail> data = new ArrayList<>();
    private Toolbar tb_app_bar;
    private ProductDetailOrderAdapter adapter_productDetail;

    private TextView tvOrderId, tvProductPrice, tvTotalPrice, tvFreightCost, tvOrderAddress;

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

        tvOrderId = findViewById(R.id.tv_orderId);
        tvProductPrice = findViewById(R.id.tv_productPrice);
        tvTotalPrice = findViewById(R.id.tv_totalPrice);
        tvFreightCost = findViewById(R.id.tv_freightCost);
        tvOrderAddress = findViewById(R.id.tv_orderAddress);

        tb_app_bar = findViewById(R.id.app_bar);
    }

    private void setEvent() {
        SharedPreferences sharedPreferences = getSharedPreferences("MyPerfs", Context.MODE_PRIVATE);
        String token = "Bearer " + sharedPreferences.getString("token", null);

        getOrderById(token, 1L);

        btnChangeStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateStatusOrder(token);
            }
        });
    }

    private void getOrderById(String token, Long orderId) {
        ApiService.apiservice.getOrderById(token, orderId).enqueue(new Callback<EntityStatusResponse<Order>>() {
            @Override
            public void onResponse(Call<EntityStatusResponse<Order>> call, Response<EntityStatusResponse<Order>> response) {
                if (response.isSuccessful()) {
                    EntityStatusResponse<Order> resultResponse = response.body();
                    if (resultResponse != null) {
                        Order orderResponse = resultResponse.getData();
                        tvOrderId.setText(orderResponse.getOrder_id().toString());
                        tvProductPrice.setText(formatToVND(orderResponse.getTotal_price()));
                        Integer totalPrice = orderResponse.getTotal_price() + Integer.parseInt((String) tvFreightCost.getText());
                        tvFreightCost.setText(formatToVND(Integer.parseInt((String) tvFreightCost.getText())));
                        tvTotalPrice.setText(formatToVND(totalPrice));
                        tb_app_bar.setTitle(orderResponse.getUpdate_at().toString());

                        Customer customerOfOrder = orderResponse.getCustomer();
                        tvOrderAddress.setText(customerOfOrder.getAddress());


                        List<OrderDetail> listOrderDetail = orderResponse.getOrderDetailList();
                        adapter_productDetail = new ProductDetailOrderAdapter(StaffChangeOrderStatusActivity.this, R.layout.layout_item_product_order, listOrderDetail);
                        lvListProduct.setAdapter(adapter_productDetail);

                        lvListProduct.getLayoutParams().height = listOrderDetail.size() * 210;


                        Log.i("status", orderResponse.getStatus().toString());
                        Log.i("message", "onResponse: " + resultResponse.getMessage());
                    }
                }
//                handler.postDelayed(() -> getOrderById(token), 30000); // 30 giây
            }

            @Override
            public void onFailure(Call<EntityStatusResponse<Order>> call, Throwable t) {
                Log.i("error", t.getMessage());
            }
        });
    }

    private void updateStatusOrder(String token) {
        Order order = new Order();
        order.setStatus(1);
        ApiService.apiservice.updateStatusOrder(token, 1L, order).enqueue(new Callback<EntityStatusResponse<Order>>() {
            @Override
            public void onResponse(Call<EntityStatusResponse<Order>> call, Response<EntityStatusResponse<Order>> response) {
                if (response.isSuccessful()) {
                    EntityStatusResponse<Order> resultResponse = response.body();
                    if (resultResponse != null) {
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
    public static String formatToVND(int amount){
        NumberFormat vnCurrencyFormat = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
        return vnCurrencyFormat.format(amount);
    }
}