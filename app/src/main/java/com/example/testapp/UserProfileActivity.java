package com.example.testapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.example.testapp.api.ApiService;
import com.example.testapp.model.Customer;
import com.example.testapp.model.User;
import com.example.testapp.response.EntityStatusResponse;
import com.example.testapp.response.ListEntityStatusResponse;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserProfileActivity extends AppCompatActivity {
    private EditText etFullName, etPhoneUser, etAddressUser, etEmail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        setControl();
        setEvent();
    }

    private void setEvent() {
        // Lấy token từ nơi lưu trữ token (ví dụ: SharedPreferences)
        String token = "Bearer " + "eyJhbGciOiJIUzM4NCJ9.eyJpYXQiOjE3MTEyODE1OTMsImV4cCI6MTcxMTU0MDc5MywidXNlcm5hbWUiOiIrODQ5NzkzNDUxOTAifQ.oOQwkfbQelOuvPePeDmGIFk_YPtDsXMKgRYuZekvHym5aIeyKprxS1KbG0zWMApO";
        ApiService.apiservice.getUserProfile(token).enqueue(new Callback<EntityStatusResponse<Customer>>() {
            @Override
            public void onResponse(Call<EntityStatusResponse<Customer>> call, Response<EntityStatusResponse<Customer>> response) {
                if (response.isSuccessful()) {
                    EntityStatusResponse<Customer> profileUserResult = response.body();
                    if (profileUserResult != null){
//                        String jsonString = response.body().toString();
//                        JsonObject jsonObject = new Gson().fromJson(jsonString, JsonObject.class);
//                        JsonObject dataObject = jsonObject.getAsJsonObject("data");
//                        Log.i("message", "ok");
//
//                        etFullName.setText(dataObject.get("lastname").getAsString());
//                        etEmail.setText(dataObject.get("email").getAsString());
//                        etPhoneUser.setText(dataObject.get("phone").getAsString());
//                        etAddressUser.setText(dataObject.get("address").getAsString());

                        Customer customer = profileUserResult.getData();
                        etFullName.setText(customer.getFirstname());
                        etEmail.setText(customer.getEmail());
                        etPhoneUser.setText(customer.getPhone());
                        etAddressUser.setText(customer.getAddress());
                        Toast.makeText(UserProfileActivity.this, "Thành công", Toast.LENGTH_SHORT).show();

                    } else {
                        Toast.makeText(UserProfileActivity.this, "Thất bại", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<EntityStatusResponse<Customer>> call, Throwable t) {
                Log.i("erro",t.getMessage().toString());
            }
        });
    }
    private void getInfoUser(){

    }

    private void setControl() {
        etAddressUser = findViewById(R.id.et_addressUser);
        etFullName = findViewById(R.id.et_fullName);
        etPhoneUser = findViewById(R.id.et_phoneUser);
        etEmail = findViewById(R.id.et_emailUser);

    }
}