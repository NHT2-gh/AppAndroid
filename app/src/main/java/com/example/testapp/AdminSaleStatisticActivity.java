package com.example.testapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.util.Pair;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.HorizontalScrollView;
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
    private TextView tvSelectDate, tvNoData;
    private CardView btnShow;
    private Button btnShowProductStatistic;
    private HorizontalScrollView scrollView;

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

        scrollView = findViewById(R.id.scroll_view);
        tvNoData = findViewById(R.id.tv_noData1);

    }
    private void setEvent() {
        SharedPreferences sharedPreferences = getSharedPreferences("MyPerfs", Context.MODE_PRIVATE);
        String token = "Bearer " + sharedPreferences.getString("token", null);

        String year = new SimpleDateFormat("yyyy", Locale.getDefault()).format(new Date().getTime());
        tvSelectDate.setText(year);

        getDataStatisticYear(token, year);

//        btnShow.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                openDatePicker();
//            }
//        });
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

//    private void openDatePicker() {
//        MaterialDatePicker<Pair<Long, Long>> materialDatePicker = MaterialDatePicker.Builder.dateRangePicker().setSelection(new Pair<>(
//                MaterialDatePicker.thisMonthInUtcMilliseconds(),
//                MaterialDatePicker.todayInUtcMilliseconds()
//        )).build();
//        materialDatePicker. addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener<Pair<Long, Long>>() {
//            @Override
//            public void onPositiveButtonClick(Pair<Long, Long> selection) {
//                String dateStart = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(new Date(selection.first));
//                String dateEnd = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(new Date(selection.second));
//
//                tvSelectDate.setText(dateStart + " - " + dateEnd);
//            }
//        });
//        materialDatePicker.show(getSupportFragmentManager(), "tag");
//    }

    private void getDataStatisticYear(String token, String year){

        // reset
        tvNoData.setVisibility(View.GONE);
        scrollView.setVisibility(View.VISIBLE);

        List<BarModel> revenueList = new ArrayList<>();
        ApiService.apiservice.getStatisticYear(token,year).enqueue(new Callback<ListEntityStatusResponse<StatisticRequest>>() {
            @Override
            public void onResponse(Call<ListEntityStatusResponse<StatisticRequest>> call, Response<ListEntityStatusResponse<StatisticRequest>> response) {
                if(response.isSuccessful()){
                    ListEntityStatusResponse<StatisticRequest> dataStatisticResponse = response.body();
                    if(dataStatisticResponse != null){
                        List<StatisticRequest> SaleStatisticOfYear = dataStatisticResponse.getData();
                        if(SaleStatisticOfYear.isEmpty()){
                            // show text view no data and hidden chart
                            tvNoData.setVisibility(View.VISIBLE);
                            scrollView.setVisibility(View.GONE);
                        }else{
                            for ( StatisticRequest item:SaleStatisticOfYear) {
                                revenueList.add(new BarModel("Tháng " + item.getMoth(), item.getTotal_price(),getColor(R.color.top2Color)));
                            }
                            barChart.getLayoutParams().width =revenueList.size()*200;
                            Log.i("width", String.valueOf(revenueList.size()*200));
                            barChart.addBarList(revenueList);
                            barChart.startAnimation();
                        }
                        Toast.makeText(AdminSaleStatisticActivity.this, "Thành công", Toast.LENGTH_SHORT).show();
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