package com.example.testapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.example.testapp.R;
import com.example.testapp.model.OrderDetail;

import java.util.ArrayList;
import java.util.List;

public class ProductDetailOrderAdapter extends ArrayAdapter {
    private Context context;
    private int resource;
    private List<OrderDetail> data;

    private List<OrderDetail> listProduct = new ArrayList<>();



    public ProductDetailOrderAdapter(Context context, int resource, List <OrderDetail> data) {
        super(context, resource, data);
        this.context = context;
        this.resource = resource;
        this.data = data;
    }
    static class ViewHolder {
         TextView tvProductName, tvProductQuantity, tvProductSize;
         ImageView ivProductImg;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @Nullable ViewGroup parent){
        ViewHolder holder;
        if(convertView == null) {

            convertView = LayoutInflater.from(context).inflate(resource, parent, false);
            holder = new ViewHolder();
            holder.tvProductName = convertView.findViewById(R.id.tv_productName);
            holder.tvProductQuantity = convertView.findViewById(R.id.tv_productQuantity);
            holder.tvProductSize = convertView.findViewById(R.id.tv_productSize);
            holder.ivProductImg = convertView.findViewById(R.id.img_product);

            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
            // set data
            OrderDetail product = data.get(position);
            holder.tvProductName.setText(product.getProduct().getProduct_name());
            holder.tvProductQuantity.setText(String.valueOf(product.getQuantity()));
            holder.tvProductSize.setText("Size: " + String.valueOf(product.getSize()));
            Glide.with(convertView)
                    .load(product.getProduct().getImage())
                     .into(holder.ivProductImg);

        return convertView;
    }
}
