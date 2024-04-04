package com.example.testapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.testapp.adapter.OrderListAdapter;
import com.example.testapp.adapter.OrderStatusAdapter;
import com.example.testapp.api.ApiService;
import com.example.testapp.model.Order;
import com.example.testapp.model.OrderStatus;
import com.example.testapp.response.ListEntityStatusResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StaffOderListActivity extends AppCompatActivity {
    List<Order> orderList = new ArrayList<>();              // list chứa data danh sách đơn hàng
    List<Order> latestOrderList = new ArrayList<>();
    List<OrderStatus> filterList = new ArrayList<>();       // list chứa danh sách các trạng thái để lọc nằm trong spinner
    OrderListAdapter OrderAdapter;
    OrderStatusAdapter orderStatusAdapter;
    ListView lvOrderList;
    TextView txtStatus;
    Spinner spinnerList;
    ProgressBar proBarShowList;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff_oder_list);
        setControl();
        setEvent();
    }

    private void setControl() {
        lvOrderList = findViewById(R.id.lvCustomerOrderList);
        spinnerList = (Spinner) findViewById(R.id.spnOrderFilter);
        txtStatus = findViewById(R.id.txtOrderStatus);

        proBarShowList = findViewById(R.id.proBar_showList);
    }


    private void setEvent() {
        SharedPreferences sharedPreferences = getSharedPreferences("MyPerfs", Context.MODE_PRIVATE);
        String token = "Bearer " + sharedPreferences.getString("token", null);

        proBarShowList.setVisibility(View.VISIBLE);
        getAllOrder(token);

        lvOrderList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });


    }
    private void getAllOrder(String token){
        ApiService.apiservice.getAllOrder(token).enqueue(new Callback<ListEntityStatusResponse<Order>>() {
            @Override
            public void onResponse(Call<ListEntityStatusResponse<Order>> call, Response<ListEntityStatusResponse<Order>> response) {
                if(response.isSuccessful()){
                    ListEntityStatusResponse<Order> resultResponse = response.body();
                    if(resultResponse != null){
                    List<Order> orderList = resultResponse.getData();
                        OrderAdapter = new OrderListAdapter(StaffOderListActivity.this, R.layout.layout_item_order, orderList);
                        lvOrderList.setAdapter(OrderAdapter);
                        proBarShowList.setVisibility(View.GONE);
                        Log.i("get all order: ", resultResponse.getMessage());
                    }
                }
            }
            @Override
            public void onFailure(Call<ListEntityStatusResponse<Order>> call, Throwable t) {
                Log.i("error: ", t.getMessage());
            }
        });
    }
}