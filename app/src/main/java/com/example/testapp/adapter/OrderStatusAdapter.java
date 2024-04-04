// adapter của spinner lọc đơn hàng theo trạng thái

package com.example.testapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


import com.example.testapp.R;
import com.example.testapp.model.OrderStatus;

import java.util.List;

public class OrderStatusAdapter extends BaseAdapter {

    Context context;
    int resource;
    List <OrderStatus> data;
    TextView txtStatus;

    @Override
    public int getCount() {
        return data != null ? data.size() : 0;
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(resource, parent, false);


        txtStatus = convertView.findViewById(R.id.txtOrderStatus);      // txtOrderStatus của item nằm trong Spinner

        OrderStatus status = data.get(position);
        txtStatus.setText(status.getName());

        return convertView;
    }


    public OrderStatusAdapter(@NonNull Context context, int resource, List <OrderStatus> data) {
        super();
        this.context = context;
        this.resource = resource;
        this.data = data;
    }


}
