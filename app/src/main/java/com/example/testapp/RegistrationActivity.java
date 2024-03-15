package com.example.testapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.testapp.api.ApiService;
import com.example.testapp.api.ApiResponse;

import com.example.testapp.model.User;

import java.util.Arrays;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegistrationActivity extends AppCompatActivity {
    TextView tvLinkLogin;
    EditText etFullName, etPhone, etPassword, etRePassword;
    TextView result;
    Button btnRegister;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        setControl();
        setEvent();
    }

    private void setControl() {
        tvLinkLogin = findViewById(R.id.tvLinkLogin);
        btnRegister = findViewById(R.id.btnRegister);
        etFullName = findViewById(R.id.et_fullName);
        etPhone = findViewById(R.id.et_phone);
        etPassword = findViewById(R.id.et_password);
        etRePassword = findViewById(R.id.et_rePassword);
        result = findViewById(R.id.result);

    }
    private void setEvent() {
        tvLinkLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivityLogin();
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<EditText> listEditText = Arrays.asList(etFullName, etPhone, etPassword, etRePassword);
                if(setRequired(listEditText, "Vui lòng điền đầy đủ thông tin")) {
                    if (isValidPhoneNumber(etPhone.getText().toString())) {
                        if (etPassword.getText().toString().equals(etRePassword.getText().toString())) {
                            sendUser();
                        } else {
                            etRePassword.setError("Xác nhận mật khẩu không khớp");
                        }
                    } else {
                        etPhone.setError("SĐT bắt đầu bằng 0, đủ 10 số mới hợp lệ");
                    }
                }
            }
        });
    }
    private void openActivityLogin() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    private void sendUser() {
        // get data
        User user = new User(null, 0, etPhone.getText().toString(), etPassword.getText().toString(), "customer", null, null, null, true);
        ApiService.apiservice.sendUser(user).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                int code = 0;
                if (response.isSuccessful()) {
                    User useResponse = response.body();
                    if (useResponse != null) {
                        code = response.code();
                        Toast.makeText(RegistrationActivity.this, String.valueOf(code), Toast.LENGTH_SHORT).show();
                        openActivityLogin();
                        if(code == HttpsURLConnection.HTTP_CREATED){
                            Toast.makeText(RegistrationActivity.this,"Đăng kí thành công", Toast.LENGTH_SHORT).show();
                        }
                    }
                } else{
                    if(code == HttpsURLConnection.HTTP_CONFLICT)
                        Toast.makeText(RegistrationActivity.this, "Số điện thoại đã được sử dụng", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(RegistrationActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    public boolean setRequired(List<EditText> listEt, String message) {
        boolean check = true;
        for (int i = 0; i < listEt.size(); i++) {
            if (TextUtils.isEmpty(listEt.get(i).getText())) {
                listEt.get(i).setError(message);
                check = false;
            }
        }
        return check;
    }

        private boolean isValidPhoneNumber(String phoneNumber) {
        if (!phoneNumber.startsWith("0")) {
            return false;
        } else if (phoneNumber.length() != 10) {
            return false;
        }
//        for (char c : phoneNumber.toCharArray()) {
//            if (!Character.isDigit(c)) {
//                return false;
//            }
//        }
        return true;
    }
}
