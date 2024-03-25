package com.example.testapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.testapp.adapter.ToppingAdapter;
import com.example.testapp.model.Topping;
import com.example.testapp.viewModel.ToppingViewModel;

import java.util.ArrayList;
import java.util.List;

public class ProductDetailActivity extends AppCompatActivity {
    private ListView lvTopping;
    private Button btnBuyNow;
    private TextView tvProductName;
    private List<Topping> data_topping = new ArrayList<>();
    private ToppingAdapter adapter_topping;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);
        setControl();
        setEvent();
    }
    private void setControl() {
        lvTopping = findViewById(R.id.lv_toppingList);
        tvProductName = findViewById(R.id.tv_productName);
        btnBuyNow = findViewById(R.id.btn_buyNow);

    }

    private void setEvent() {
        Khoitao();
        adapter_topping = new ToppingAdapter(this, R.layout.layout_item_topping, data_topping);
        lvTopping.setAdapter(adapter_topping);

        btnBuyNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                processigBuyNow();
            }
        });
    }

    private void processigBuyNow() {
        List<Topping> selectedToppings  = adapter_topping.getSelectedToppings();
        StringBuilder stringBuilder = new StringBuilder();
        for (Topping topping : selectedToppings) {
            stringBuilder.append(topping.getToppingName()).append("\n");
        }
        String selectedToppingsString = stringBuilder.toString();
        Toast.makeText(this, selectedToppingsString, Toast.LENGTH_SHORT).show();

    }


    private void Khoitao() {
        data_topping.add(new Topping("1", "1","1", "Phô mai tươi",70000000, 1, "https://www.crane-tea.com/wp-content/uploads/2021/07/a4034263b1c3459d1cd2.jpg"));
        data_topping.add(new Topping("1", "1","1", "Trân châu",1,  1, "https://www.crane-tea.com/wp-content/uploads/2021/07/a4034263b1c3459d1cd2.jpg"));
        data_topping.add(new Topping("1", "1","1", "Plan",1,  1, "https://www.crane-tea.com/wp-content/uploads/2021/07/a4034263b1c3459d1cd2.jpg"));

    }
}