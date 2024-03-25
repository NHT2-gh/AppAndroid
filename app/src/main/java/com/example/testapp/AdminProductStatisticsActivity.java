package com.example.testapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.testapp.api.ApiService;
import com.example.testapp.model.ProductSaleRequest;
import com.example.testapp.response.ListEntityStatusResponse;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;

import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdminProductStatisticsActivity extends AppCompatActivity {
    private TextView tvTop1, tvTop2, tvTop3, tvTop4, tvTop5, tvProductNameTop1,tvProductNameTop2,
            tvProductNameTop3, tvProductNameTop4, tvProductNameTop5, tvCommentTop1, tvCommentTop2, tvCommentTop3,
            tvCommentTop4, tvCommentTop5;
    private PieChart pieChart;
    private LinearLayout layoutCommment1, layoutCommment2, layoutCommment3, layoutCommment4, layoutCommment5;
    private RelativeLayout rlt_top1, rlt_top2, rlt_top3, rlt_top4, rlt_top5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_product_statistics);
        setControl();
        setEvent();
    }

    private void setEvent() {
        getData();
    }

    private void getData() {
        List<TextView> listProductQuantity = Arrays.asList(tvTop1, tvTop2, tvTop3, tvTop4, tvTop5);
        List<TextView> listProductName = Arrays.asList(tvProductNameTop1, tvProductNameTop2, tvProductNameTop3, tvProductNameTop4, tvProductNameTop5);
        List<TextView> listProductNameComment = Arrays.asList(tvCommentTop1, tvCommentTop2, tvCommentTop3, tvCommentTop4, tvCommentTop5);
        List<Integer> listColor = Arrays.asList(R.color.top1Color, R.color.top2Color, R.color.top3Color, R.color.top4Color, R.color.top5Color);
        List<LinearLayout> listLinnerLayout = Arrays.asList(layoutCommment1,layoutCommment2, layoutCommment3, layoutCommment4, layoutCommment5);
        List<RelativeLayout> listRelativeLayout = Arrays.asList(rlt_top1, rlt_top2, rlt_top3, rlt_top4, rlt_top5);
        ApiService.apiservice.getStatisticProduct().enqueue(new Callback<ListEntityStatusResponse<ProductSaleRequest>>() {
            @Override
            public void onResponse(Call<ListEntityStatusResponse<ProductSaleRequest>> call, Response<ListEntityStatusResponse<ProductSaleRequest>> response) {
                if (response.isSuccessful()) {
                    ListEntityStatusResponse<ProductSaleRequest> productSaleRequest = response.body();
                    if (productSaleRequest != null) {
                        List<ProductSaleRequest> productSaleRequests = productSaleRequest.getData();
                        for (int i = 0; i < productSaleRequests.size(); i++) {
                            listProductName.get(i).setText(productSaleRequests.get(i).getProduct_name());
                            listProductQuantity.get(i).setText(String.valueOf((int) productSaleRequests.get(i).getTotal_quantity()));
                            listProductNameComment.get(i).setText(productSaleRequests.get(i).getProduct_name());
                            pieChart.addPieSlice(
                                    new PieModel(
                                            productSaleRequests.get(i).getProduct_name(),
                                            Integer.parseInt(listProductQuantity.get(i).getText().toString()), getColor(listColor.get(i))));
                            listLinnerLayout.get(i).setVisibility(View.VISIBLE);
                            listRelativeLayout.get(i).setVisibility(View.VISIBLE);
                        }
                        Toast.makeText(AdminProductStatisticsActivity.this, "thanh cong", Toast.LENGTH_SHORT).show();
                        pieChart.startAnimation();
                    }
                }else {
                    Toast.makeText(AdminProductStatisticsActivity.this, "thai bai", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<ListEntityStatusResponse<ProductSaleRequest>> call, Throwable t) {
                Toast.makeText(AdminProductStatisticsActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();

            }

        });
    }

//        tvTop1.setText(Integer.toString(40));
//        tvTop2.setText(Integer.toString(30));
//        tvTop3.setText(Integer.toString(25));
//        tvTop4.setText(Integer.toString(20));
//        tvTop5.setText(Integer.toString(15));


        // Set the data and color to the pie chart


    private void setControl() {
        tvTop1 = findViewById(R.id.tv_top1);
        tvTop2 = findViewById(R.id.tv_top2);
        tvTop3 = findViewById(R.id.tv_top3);
        tvTop4 = findViewById(R.id.tv_top4);
        tvTop5 = findViewById(R.id.tv_top5);

        tvCommentTop1 = findViewById(R.id.tv_commentTop1);
        tvCommentTop2 = findViewById(R.id.tv_commentTop2);
        tvCommentTop3 = findViewById(R.id.tv_commentTop3);
        tvCommentTop4 = findViewById(R.id.tv_commentTop4);
        tvCommentTop5 = findViewById(R.id.tv_commentTop5);

        tvProductNameTop1 = findViewById(R.id.tv_productNameTop1);
        tvProductNameTop2 = findViewById(R.id.tv_productNameTop2);
        tvProductNameTop3 = findViewById(R.id.tv_productNameTop3);
        tvProductNameTop4 = findViewById(R.id.tv_productNameTop4);
        tvProductNameTop5 = findViewById(R.id.tv_productNameTop5);

        layoutCommment1 = findViewById(R.id.layout_comment1);
        layoutCommment2 = findViewById(R.id.layout_comment2);
        layoutCommment3 = findViewById(R.id.layout_comment3);
        layoutCommment4 = findViewById(R.id.layout_comment4);
        layoutCommment5 = findViewById(R.id.layout_comment5);

        rlt_top1 = findViewById(R.id.rlt_top1);
        rlt_top2 = findViewById(R.id.rlt_top2);
        rlt_top3 = findViewById(R.id.rlt_top3);
        rlt_top4 = findViewById(R.id.rlt_top4);
        rlt_top5 = findViewById(R.id.rlt_top5);

        pieChart = findViewById(R.id.piechart);

    }
}