package com.example.testapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class UserDeliveryProcessActivity extends AppCompatActivity {
    private static final int REQUEST_CALL_PHONE_PERMISSION = 1;
    private ImageView tvStatus;
    private ImageButton ibCallShipper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery_process);
        setControl();
        setEvent();
    }

    private void setEvent() {
//        Glide.with(this)
//                .load("https://media.giphy.com/media/98uBZTzlXMhkk/giphy.gif")
//                .into(tvStatus);

        /*from raw folder*/
        //load gif
        Glide.with(this)
                .load(R.drawable.gif_step1)
                .into(tvStatus);

        ibCallShipper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                autoCallShipper();
            }
        });
    }

    private void autoCallShipper() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
            startActivity(new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "+84845002405")));
        } else {
            // Quyền chưa được cấp, bạn có thể yêu cầu người dùng cấp quyền bằng cách hiển thị một hộp thoại yêu cầu quyền
            ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.CALL_PHONE}, REQUEST_CALL_PHONE_PERMISSION);
        }

    }

    private void setControl() {
        tvStatus = findViewById(R.id.iv_status);
        ibCallShipper = findViewById(R.id.ib_autoCallShipper);
    }
}