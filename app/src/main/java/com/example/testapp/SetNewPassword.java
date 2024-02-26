package com.example.testapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class SetNewPassword extends AppCompatActivity {
    TextView linkSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_new_password);
        setControl();
        setEvent();

    }
    private void setControl() {
        linkSignUp = findViewById(R.id.tvLinkSignup);

    }
    private void setEvent() {
        linkSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivitySignup();
            }

        });

    }
    private void openActivitySignup() {
        Intent intent = new Intent(this, RegistrationActivity.class);
        startActivity(intent);
    }


}