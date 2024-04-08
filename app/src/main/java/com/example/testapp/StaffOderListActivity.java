package com.example.testapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.util.Pair;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.testapp.adapter.OrderListAdapter;
import com.example.testapp.adapter.OrderStatusAdapter;
import com.example.testapp.api.ApiService;
import com.example.testapp.model.Order;
import com.example.testapp.model.OrderStatus;
import com.example.testapp.response.ListEntityStatusResponse;
import com.google.android.gms.common.api.Api;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StaffOderListActivity extends AppCompatActivity {
    private List<OrderStatus> listStatus = new ArrayList<>();
    OrderListAdapter orderAdapter;
    OrderStatusAdapter statusAdapter;
    ListView lvOrderList;
    TextView txtStatus, tvSelectStarDate, tvSelectEndDate, tvLine;
    Spinner spinnerList;
    ProgressBar proBarShowList;
    CardView btnShow;

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
        tvSelectStarDate = findViewById(R.id.tv_selectStarDate);
        tvSelectEndDate = findViewById(R.id.tv_selectEndDate);
        tvLine = findViewById(R.id.tv_line);

        proBarShowList = findViewById(R.id.proBar_showList);

        btnShow = findViewById(R.id.btn_showDate);
    }

    private void setEvent() {
        SetData();
        SharedPreferences sharedPreferences = getSharedPreferences("MyPerfs", Context.MODE_PRIVATE);
        String token = "Bearer " + sharedPreferences.getString("token", null);

        proBarShowList.setVisibility(View.VISIBLE);
        getAllOrder(token);

        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
        public void onClick(View v) {
            openDatePicker(token);
        }
        });

        statusAdapter = new OrderStatusAdapter(this, R.layout.layout_item_order_status, listStatus);
        spinnerList.setAdapter(statusAdapter);

        spinnerList.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedStatus = String.valueOf(listStatus.get(position).getName());
//                Toast.makeText(StaffOderListActivity.this, selectedStatus, Toast.LENGTH_SHORT).show();
                int statusId = listStatus.get(position).getId();
                if(statusId != -1)
                    getOrderById(token, statusId);
                else
                    getAllOrder(token);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    private void SetData() {
        listStatus.add(new OrderStatus(-1,"Tất cả"));
        listStatus.add(new OrderStatus(0,"Chờ xác nhận"));
        listStatus.add(new OrderStatus(1,"Đã xác nhận"));
        listStatus.add(new OrderStatus(2,"Đang thực hiện"));
        listStatus.add(new OrderStatus(3,"Đang giao"));
        listStatus.add(new OrderStatus(4,"Đã hoàn thành"));
    }

    private void getAllOrder(String token){
        ApiService.apiservice.getAllOrder(token).enqueue(new Callback<ListEntityStatusResponse<Order>>() {
            @Override
            public void onResponse(Call<ListEntityStatusResponse<Order>> call, Response<ListEntityStatusResponse<Order>> response) {
                if(response.isSuccessful()){
                    ListEntityStatusResponse<Order> resultResponse = response.body();
                    if(resultResponse != null){
                    List<Order> orderList = resultResponse.getData();
                        orderAdapter = new OrderListAdapter(StaffOderListActivity.this, R.layout.layout_item_order, orderList);
                        lvOrderList.setAdapter(orderAdapter);
                        proBarShowList.setVisibility(View.GONE);
                        Log.i("get all order: ", resultResponse.getMessage());
                    }
                }
            }
            @Override
            public void onFailure(Call<ListEntityStatusResponse<Order>> call, Throwable t) {
                Log.i("error get all order: ", t.getMessage());
            }
        });
    }

    private  void getOrderById(String token, Integer statusId){
        ApiService.apiservice.getOrderByStatus(token, statusId).enqueue(new Callback<ListEntityStatusResponse<Order>>() {
            @Override
            public void onResponse(Call<ListEntityStatusResponse<Order>> call, Response<ListEntityStatusResponse<Order>> response) {
                if(response.isSuccessful()){
                    ListEntityStatusResponse<Order> resultResponse = response.body();
                    if(resultResponse != null){
                        List<Order> listOrder = resultResponse.getData();
                        orderAdapter = new OrderListAdapter(StaffOderListActivity.this, R.layout.layout_item_order, listOrder);
                        lvOrderList.setAdapter(orderAdapter);
                        Log.i("get order by status:", resultResponse.getMessage());
                    }
                }
            }

            @Override
            public void onFailure(Call<ListEntityStatusResponse<Order>> call, Throwable t) {
                Log.i("error", t.getMessage());
            }
        });
    }

    private void openDatePicker(String token) {
        MaterialDatePicker<Pair<Long, Long>> materialDatePicker = MaterialDatePicker.Builder.dateRangePicker().setSelection(new Pair<>(
                MaterialDatePicker.thisMonthInUtcMilliseconds(),
                MaterialDatePicker.todayInUtcMilliseconds()
        )).build();
        materialDatePicker. addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener<Pair<Long, Long>>() {
            @Override
            public void onPositiveButtonClick(Pair<Long, Long> selection) {
                String dateStart = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date(selection.first));
                String dateEnd = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date(selection.second));

                String showStartDate =  new SimpleDateFormat("MM-dd", Locale.getDefault()).format(new Date(selection.first));
                String showEndDate =  new SimpleDateFormat("MM-dd", Locale.getDefault()).format(new Date(selection.second));

                tvSelectStarDate.setText(showStartDate);
                tvSelectEndDate.setText(showEndDate);
                tvLine.setVisibility(View.VISIBLE);
                tvSelectEndDate.setVisibility(View.VISIBLE);
                getOrderByDate(token, dateStart, dateEnd);
            }
        });
        materialDatePicker.show(getSupportFragmentManager(), "tag");
    }

    private void getOrderByDate(String token, String startDate, String endDate){
        ApiService.apiservice.getOrderByDate(token, startDate, endDate).enqueue(new Callback<ListEntityStatusResponse<Order>>() {
            @Override
            public void onResponse(Call<ListEntityStatusResponse<Order>> call, Response<ListEntityStatusResponse<Order>> response) {
                if(response.isSuccessful()){
                    ListEntityStatusResponse<Order> resultResponse = response.body();
                    if(resultResponse != null){
                        List<Order> listOrder = resultResponse.getData();
                        orderAdapter = new OrderListAdapter(StaffOderListActivity.this, R.layout.layout_item_order, listOrder);
                        lvOrderList.setAdapter(orderAdapter);
                        Log.i("get order by status:", resultResponse.getMessage());
                    }
                }
            }

            @Override
            public void onFailure(Call<ListEntityStatusResponse<Order>> call, Throwable t) {
                Log.i("erro api get order by date ", t.getMessage());
            }
        });
    }
}