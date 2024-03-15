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
import com.example.testapp.model.Topping;

import java.util.List;

public class ToppingAdapter  extends ArrayAdapter {
    Context context;
    int resource;
    List<Topping> data;
    TextView tvToppingName, tvToppingPrice;
    ImageView ivToppingImg;

    public ToppingAdapter (Context context, int resource, List <Topping> data) {
        super(context, resource, data);
        this.context = context;
        this.resource = resource;
        this.data = data;
}
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @Nullable ViewGroup parent){
        convertView = LayoutInflater.from(context).inflate(resource, parent, false);

        tvToppingName = convertView.findViewById(R.id.tv_toppingName);
        tvToppingPrice = convertView.findViewById(R.id.tv_toppingPrice);
        ivToppingImg = convertView.findViewById(R.id.img_topping);

        Topping topping = data.get(position);
        tvToppingName.setText(topping.getToppingName());
        tvToppingPrice.setText(String.valueOf(topping.getToppingPrice()));
        Glide.with(convertView)
                .load(topping.getToppingImg())
                .into(ivToppingImg);

        return convertView;
    }
}
