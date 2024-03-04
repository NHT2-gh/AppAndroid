package com.example.testapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import com.example.testapp.adapter.ProductAdapter;
import com.example.testapp.model.Products;
import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;

    private List<Products> data = new ArrayList<>();

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
        data.add(new Products("1", "PHINCHOCO", 45000, R.drawable.img_product));
        data.add(new Products("2", "Choco2", 45000, R.drawable.img_product));
        data.add(new Products("3", "PHINCHOCO", 45000, R.drawable.img_product));
        data.add(new Products("4", "PHINCHOCO", 45000, R.drawable.img_product));
    }

    private void setControl() {
        recyclerView = findViewById(R.id.recycler_listProduct);
    }
}