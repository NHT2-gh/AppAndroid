package com.example.testapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.testapp.api.ApiService;
import com.example.testapp.model.User;
import com.example.testapp.response.ApiResponse;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SetNewPassword extends AppCompatActivity {
    EditText et_setNewPass, et_rePass;
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_new_password);
        setControl();
        setEvent();

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
    private void setControl() {
        btnLogin = findViewById(R.id.btnLogin);
        et_setNewPass = findViewById(R.id.et_setNewPassword);
        et_rePass = findViewById(R.id.et_rePassword);

    }

    private void putUser(){
        Intent intent = getIntent();
        User user = new User();
        user.setPassword(String.valueOf(et_setNewPass.getText()));
        if (intent != null) {
            ApiService.apiservice.changePassword(String.format(intent.getStringExtra("username")),user).enqueue(new Callback<ApiResponse>() {
                int code = 200;
                @Override
                public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                    if (response.isSuccessful()) {
                        ApiResponse changePassResult = response.body();
                        if (changePassResult != null){
                            Toast.makeText(SetNewPassword.this, changePassResult.getMessage(), Toast.LENGTH_SHORT).show();
                            openActivityHome();
                        }
                    }else {
                        code = response.code();
                        Toast.makeText(SetNewPassword.this, "Thiết lập mật khẩu mới thất bại", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<ApiResponse> call, Throwable t) {
                    Toast.makeText(SetNewPassword.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private void setEvent() {

        btnLogin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                List<EditText> listEditText = Arrays.asList(et_setNewPass, et_rePass);
                if(setRequired(listEditText, "Vui lòng điền đầy đủ thông tin")) {
                        if (et_setNewPass.getText().toString().equals(et_rePass.getText().toString())) {
                        } else {
                            et_rePass.setError("Xác nhận mật khẩu không khớp");
                        }
                        putUser();
                }
            }

        });
    }
    private void openActivityHome() {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }


}