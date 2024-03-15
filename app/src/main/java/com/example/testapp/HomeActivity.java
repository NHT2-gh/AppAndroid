package com.example.testapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import com.example.testapp.adapter.ProductAdapter;
import com.example.testapp.model.Product;
import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;

    private List<Product> data = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        setControl();
        setEvent();
    }

    private void setEvent() {
        Khoitao();
        recyclerView.setHasFixedSize(true);
        // Thiết lập LayoutManager GridLayoutManager với 2 cột
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        // Khởi tạo adapter và gán vào RecyclerView
        adapter = new ProductAdapter(this, data);
        recyclerView.setAdapter(adapter);
    }

    private void Khoitao() {
        data.add(new Product("1", "PHINCHOCO", 45000, "https://firebasestorage.googleapis.com/v0/b/image-highland-6ae35.appspot.com/o/6f6eeda1-8ba1-4632-988f-4fa1b20c98bdjpg?alt=media"));
        data.add(new Product("2", "Choco2", 45000, "https://www.highlandscoffee.com.vn/vnt_upload/product/06_2023/HLC_New_logo_5.1_Products__FREEZE_TRA_XANH.jpg"));
        data.add(new Product("3", "PHINCHOCO", 45000, "https://www.highlandscoffee.com.vn/vnt_upload/product/06_2023/HLC_New_logo_5.1_Products__FREEZE_TRA_XANH.jpg"));
        data.add(new Product("4", "PHINCHOCO", 45000, "https://www.highlandscoffee.com.vn/vnt_upload/product/06_2023/HLC_New_logo_5.1_Products__FREEZE_TRA_XANH.jpg"));
    }

    private void setControl() {
        recyclerView = findViewById(R.id.recycler_listProduct);
    }
}