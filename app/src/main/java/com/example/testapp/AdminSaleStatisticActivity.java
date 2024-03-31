package com.example.testapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.util.Pair;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.testapp.api.ApiService;
import com.example.testapp.model.StatisticRequest;
import com.example.testapp.response.ListEntityStatusResponse;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;

import org.eazegraph.lib.charts.BarChart;
import org.eazegraph.lib.models.BarModel;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdminSaleStatisticActivity extends AppCompatActivity {
    private BarChart barChart;
//    private TextView tvSelecStarttDate, tvSelecEndtDate;
    private TextView tvSelectDate;
    private CardView btnShow;
    private Button btnShowProductStatistic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_sale_statistic);

        setControl();
        setEvent();
    }
    private void setControl() {
        barChart = findViewById(R.id.barChart);
        tvSelectDate = findViewById(R.id.tv_selectDate);
        btnShow = findViewById(R.id.btn_showDate);
        btnShowProductStatistic = findViewById(R.id.btn_showProductStatistic);
    }
    private void setEvent() {
        getDataStatisticYear();

        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDatePicker();
            }
        });
        btnShowProductStatistic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openProductStatistic();
            }
        });

    }

    private void openProductStatistic() {
        Intent intent = new Intent(this, AdminProductStatisticsActivity.class);
        startActivity(intent);
    }

    private void openDatePicker() {
        MaterialDatePicker<Pair<Long, Long>> materialDatePicker = MaterialDatePicker.Builder.dateRangePicker().setSelection(new Pair<>(
                MaterialDatePicker.thisMonthInUtcMilliseconds(),
                MaterialDatePicker.todayInUtcMilliseconds()
        )).build();
        materialDatePicker. addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener<Pair<Long, Long>>() {
            @Override
            public void onPositiveButtonClick(Pair<Long, Long> selection) {
                String dateStart = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(new Date(selection.first));
                String dateEnd = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(new Date(selection.second));

                tvSelectDate.setText(dateStart + " - " + dateEnd);
            }
        });
        materialDatePicker.show(getSupportFragmentManager(), "tag");
    }

    private void getDataStatisticYear(){
        List<BarModel> revenueList = new ArrayList<>();
        String token = "Bearer " + "eyJhbGciOiJIUzM4NCJ9.eyJpYXQiOjE3MTE2ODY4NDQsImV4cCI6MTcxMTk0NjA0NCwidXNlcm5hbWUiOiIrODQ5NzkzNDUxOTAifQ.lpoUx6fvgL2UDE9L0ViUzgWMRkv4UmCD5_cWEfR2MF8BsvgwXLqllHBQy72xzj4i";
        ApiService.apiservice.getStatisticYear(token,"2024").enqueue(new Callback<ListEntityStatusResponse<StatisticRequest>>() {
            @Override
            public void onResponse(Call<ListEntityStatusResponse<StatisticRequest>> call, Response<ListEntityStatusResponse<StatisticRequest>> response) {
                if(response.isSuccessful()){
                    ListEntityStatusResponse<StatisticRequest> dataStatisticResponse = response.body();
                    if(dataStatisticResponse != null){
                        List<StatisticRequest> SaleStatisticOfYear = dataStatisticResponse.getData();
                        for ( StatisticRequest item:SaleStatisticOfYear) {
                            revenueList.add(new BarModel("Tháng " + item.getMoth(), item.getTotal_price(),getColor(R.color.top2Color)));
                        }
                        Toast.makeText(AdminSaleStatisticActivity.this, "Thành công", Toast.LENGTH_SHORT).show();
                        barChart.getLayoutParams().width =revenueList.size()*200;
                        Log.i("width", String.valueOf(revenueList.size()*200));
                        barChart.addBarList(revenueList);
                        barChart.startAnimation();
                    }
                }else{
                    try {
                        Log.i("erro",response.errorBody().string());
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }

            }
            @Override
            public void onFailure(Call<ListEntityStatusResponse<StatisticRequest>> call, Throwable t) {
                Toast.makeText(AdminSaleStatisticActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}