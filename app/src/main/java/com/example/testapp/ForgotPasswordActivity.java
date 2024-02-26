package com.example.testapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ForgotPasswordActivity extends AppCompatActivity {
    Button btn_accept;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        setControl();
        setEvent();
    }
    private void setControl() {
        btn_accept = findViewById(R.id.btnAccept);
    }
    private void setEvent() {
        btn_accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivitySetPass();
            }
        });
    }

    private void openActivitySetPass() {
        Intent intent = new Intent(this, SetNewPassword.class);
        startActivity(intent);
    }


}

