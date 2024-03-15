package com.example.testapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.example.testapp.adapter.ToppingAdapter;
import com.example.testapp.model.Topping;

import java.util.ArrayList;
import java.util.List;

public class ProductDetailActivity extends AppCompatActivity {
    private ListView lvTopping;
    private List<Topping> data_topping = new ArrayList<>();
    private ToppingAdapter adapter_topping;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);
        setCotrol();
        setEvent();
    }

    private void setCotrol() {
        lvTopping = findViewById(R.id.lv_toppingList);
    }

    private void setEvent() {
        Khoitao();
        adapter_topping = new ToppingAdapter(this, R.layout.layout_item_topping, data_topping);
        lvTopping.setAdapter(adapter_topping);
    }

    private void Khoitao() {
        data_topping.add(new Topping("1", "1","1", "Phô mai tươi",1, 1, "https://www.crane-tea.com/wp-content/uploads/2021/07/a4034263b1c3459d1cd2.jpg"));
        data_topping.add(new Topping("1", "1","1", "Phô mai tươi",1,  1, "https://www.crane-tea.com/wp-content/uploads/2021/07/a4034263b1c3459d1cd2.jpg"));
        data_topping.add(new Topping("1", "1","1", "Phô mai tươi",1,  1, "https://www.crane-tea.com/wp-content/uploads/2021/07/a4034263b1c3459d1cd2.jpg"));

    }
}