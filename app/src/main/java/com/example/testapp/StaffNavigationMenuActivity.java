package com.example.testapp;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.drawerlayout.widget.DrawerLayout;

public class StaffNavigationMenuActivity extends AppCompatActivity {
    DrawerLayout drawerLayout;
    ImageButton ibOpenMenu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff_navigation_menu);
        setControl();
        setEvent();
    }

    private void setEvent() {
        ibOpenMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.open();
            }
        });
    }

    private void setControl() {
        drawerLayout = findViewById(R.id.drawerLayout);
        ibOpenMenu = findViewById(R.id.ib_drawerMenu);
    }
}