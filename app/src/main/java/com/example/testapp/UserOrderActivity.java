package com.example.testapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.content.ContextCompat;

import android.app.Activity;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class UserOrderActivity extends AppCompatActivity {
    private Button btnBuy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_order);

        setControl();
        setEvent();
    }

    private void setEvent() {
        btnBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openProcessActivity();
            }
        });
    }

    private void openProcessActivity() {
        Intent intent = new Intent(this, UserDeliveryProcessActivity.class);
        startActivity(intent);
    }

    private void setControl() {
        btnBuy = findViewById(R.id.btn_buyNow);
    }
}