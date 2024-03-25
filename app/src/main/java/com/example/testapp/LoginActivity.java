package com.example.testapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.TimeUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.testapp.api.ApiService;
import com.example.testapp.model.User;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    private TextView tvForgotPass, tvLinkSignup;
    private Button btnLogin;
    private EditText etPhone, etPassword;
    private ProgressBar pbLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setControl();
        setUp();
        setEvent();
    }

    private void setUp() {
        String defaultText = "+84";
        etPhone.setText(defaultText);
        etPhone.setSelection(defaultText.length());
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
        etPhone = findViewById(R.id.etPhone);


        etPassword = findViewById(R.id.etPassword);
        tvForgotPass = findViewById(R.id.forgetPass);
        tvLinkSignup = findViewById(R.id.tvLinkSignup);
        btnLogin = findViewById(R.id.btnLogin);
        pbLogin = findViewById(R.id.proBar_login);

        tvForgotPass.setPaintFlags(tvForgotPass.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        tvLinkSignup.setPaintFlags(tvLinkSignup.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
    }

    private void setEvent() {
        tvForgotPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivityForgotPassword();
            }
        });

        tvLinkSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivitySignup();
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                postUser();
            }
        });
    }
    //open Home
    private void openActivityHome() {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }
    //open Sign Up
    private void openActivitySignup() {
        Intent intent = new Intent(this, RegistrationActivity.class);
        startActivity(intent);
    }
    //open Forgot Password
    private void openActivityForgotPassword() {
        List<EditText> listEditText = Arrays.asList(etPhone);

        final ProgressBar proBarForgotPass;
        proBarForgotPass = findViewById((R.id.proBar_forgotPass));

        if(setRequired(listEditText, "Vui lòng nhập số điện thoại để thiết lập mật khẩu mới")){

            proBarForgotPass.setVisibility(View.VISIBLE);
            tvForgotPass.setVisibility(View.INVISIBLE);
            //Send OTP by firebase
            PhoneAuthProvider.verifyPhoneNumber(
                    PhoneAuthOptions.newBuilder(FirebaseAuth.getInstance())
                            .setPhoneNumber(etPhone.getText().toString())
                            .setTimeout(60L, TimeUnit.SECONDS)
                            .setActivity(LoginActivity.this)
                            .setCallbacks(new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                                @Override
                                public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                                    // Xử lý khi xác minh hoàn thành
                                    proBarForgotPass.setVisibility(View.GONE);
                                    tvForgotPass.setVisibility(View.VISIBLE);
                                }

                                @Override
                                public void onVerificationFailed(@NonNull FirebaseException e) {
                                    // Xử lý khi xác minh thất bại
                                    proBarForgotPass.setVisibility(View.GONE);
                                    tvForgotPass.setVisibility(View.VISIBLE);
                                    Toast.makeText(LoginActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                }

                                @Override
                                public void onCodeSent(@NonNull String verificationId, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                    super.onCodeSent(verificationId, forceResendingToken);
                                    //Send phone user to forgot password activity
                                    proBarForgotPass.setVisibility(View.GONE);
                                    tvForgotPass.setVisibility(View.VISIBLE);
                                    Intent intent = new Intent(LoginActivity.this, ForgotPasswordActivity.class);
                                    intent.putExtra("phoneUser", etPhone.getText().toString());
                                    intent.putExtra("verificationId", verificationId);
                                    startActivity(intent);

                                }
                            })
                            .build());

        }
    }


    //Call API
    private void postUser() {
        List<EditText> listRequired = Arrays.asList(etPhone, etPassword);
        if(setRequired(listRequired, "Vui lòng nhập đầy đủ thông tin")){
            User user = new User(null,0,etPhone.getText().toString(),etPassword.getText().toString(),"customer",null, null, null, true);
            pbLogin.setVisibility(View.VISIBLE);
            btnLogin.setVisibility(View.INVISIBLE);
            //call API method POST to login
            ApiService.apiservice.loginUser(user).enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    if (response.isSuccessful()) {
                        User userResult = response.body();
                        if (userResult != null){
                            String token = userResult.getToken();
                            Log.i("Token:", token);

                            Toast.makeText(LoginActivity.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                            pbLogin.setVisibility(View.GONE);
                            btnLogin.setVisibility(View.VISIBLE);
                            openActivityHome();
                        } else {
                            Toast.makeText(LoginActivity.this, "Đăng nhập thất bại", Toast.LENGTH_SHORT).show();
                            pbLogin.setVisibility(View.GONE);
                            btnLogin.setVisibility(View.VISIBLE);
                        }
                    }
                }
                @Override
                public void onFailure(Call<User> call, Throwable t) {
                    Toast.makeText(LoginActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                    pbLogin.setVisibility(View.GONE);
                    btnLogin.setVisibility(View.VISIBLE);
                }
            });
        }else{
            pbLogin.setVisibility(View.GONE);
            btnLogin.setVisibility(View.VISIBLE);
        }

    }
   // End call API
}