package com.example.testapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ForgotPasswordActivity extends AppCompatActivity {
    private TextView tvPhoneUser;
    private EditText etOtpCode1, etOtpCode2, etOtpCode3, etOtpCode4, etOtpCode5, etOtpCode6;

    private Button btnAccept;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        setControl();
        setupInputs();
        setEvent();

    }

    private void setupInputs() {
        etOtpCode1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(!charSequence.toString().isEmpty()){
                    etOtpCode2.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        etOtpCode2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(!charSequence.toString().isEmpty()){
                    etOtpCode3.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        etOtpCode3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(!charSequence.toString().isEmpty()){
                    etOtpCode4.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        etOtpCode4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(!charSequence.toString().isEmpty()){
                    etOtpCode5.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        etOtpCode5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(!charSequence.toString().isEmpty()){
                    etOtpCode6.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private void setControl() {
        tvPhoneUser = findViewById(R.id.tv_PhoneNumber);

        etOtpCode1 = findViewById(R.id.et_otpCode1);
        etOtpCode2 = findViewById(R.id.et_otpCode2);
        etOtpCode3 = findViewById(R.id.et_otpCode3);
        etOtpCode4 = findViewById(R.id.et_otpCode4);
        etOtpCode5 = findViewById(R.id.et_otpCode5);
        etOtpCode6 = findViewById(R.id.et_otpCode6);

        setupInputs();

        btnAccept = findViewById(R.id.btnAccept);
    }
    private void setEvent() {
        //take info from loginActivity
        Intent intent = getIntent();
        if (intent != null) {
            tvPhoneUser.setText(String.format(intent.getStringExtra("phoneUser")
            ));
        }

        btnAccept.setOnClickListener(new View.OnClickListener() {
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

