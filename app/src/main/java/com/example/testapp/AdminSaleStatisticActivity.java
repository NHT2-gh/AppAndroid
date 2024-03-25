package com.example.testapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import org.eazegraph.lib.charts.BarChart;
import org.eazegraph.lib.models.BarModel;

import java.util.ArrayList;
import java.util.List;

public class AdminSaleStatisticActivity extends AppCompatActivity {
    private BarChart barChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_sale_statistic);

        setControl();
        setEvent();
    }

    private void setEvent() {
//        List<BarModel> revenueList = new ArrayList<>();
//        revenueList.add("",1000f, "");
//        revenueList.add(1500f);
//        revenueList.add(2000f);
//        revenueList.add(1800f);
//        revenueList.add(2500f);
//        revenueList.add(2200f);
//        revenueList.add(3000f);
//        revenueList.add(2800f);
//        revenueList.add(3500f);
//        revenueList.add(3200f);
//        revenueList.add(4000f);
//        revenueList.add(3800f);
//
//        barChart.addBarList(revenueList);

    }

    private void setControl() {
    barChart = findViewById(R.id.barChart);
    }
}