package com.example.testapp.adapter;

import android.content.Context;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.adapter.FragmentViewHolder;

import com.bumptech.glide.Glide;
import com.example.testapp.R;
import com.example.testapp.model.Topping;
import com.example.testapp.viewModel.ToppingViewModel;

import java.util.ArrayList;
import java.util.List;

public class ToppingAdapter  extends ArrayAdapter {
    private Context context;
    private int resource;
    private List<Topping> data;
    private TextView tvToppingName, tvToppingPrice;
    private ImageView ivToppingImg;

    private List<Topping> selectedToppings = new ArrayList<>();



    public ToppingAdapter (Context context, int resource, List <Topping> data) {
        super(context, resource, data);
        this.context = context;
        this.resource = resource;
        this.data = data;
    }
    static class ViewHolder {
         TextView tvToppingName, tvToppingPrice;
         ImageView ivToppingImg;
         CheckBox checkBox;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @Nullable ViewGroup parent){
        ViewHolder holder;
        if(convertView == null) {

            convertView = LayoutInflater.from(context).inflate(resource, parent, false);
            holder = new ViewHolder();
            holder.tvToppingName = convertView.findViewById(R.id.tv_toppingName);
            holder.tvToppingPrice = convertView.findViewById(R.id.tv_toppingPrice);
            holder.ivToppingImg = convertView.findViewById(R.id.img_topping);
            holder.checkBox = convertView.findViewById(R.id.checkbox);

            holder.checkBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    CheckBox checkBox = (CheckBox) view;
                    Topping topping = (Topping) checkBox.getTag();
                    if (checkBox.isChecked()) {
                        selectedToppings.add(topping);
                        Toast.makeText(context, "true:"+ topping.getToppingName()+topping.getToppingPrice(), Toast.LENGTH_SHORT).show();
                    } else {
                        selectedToppings.remove(topping);
                        Toast.makeText(context, "false:"+ topping.getToppingName()+topping.getToppingPrice(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
            // set data
            Topping topping = data.get(position);
            holder.tvToppingName.setText(topping.getToppingName());
            holder.tvToppingPrice.setText(String.valueOf(topping.getToppingPrice()));
            Glide.with(convertView)
                    .load(topping.getToppingImg())
                     .into(holder.ivToppingImg);
            holder.checkBox.setTag(topping);

        return convertView;
    }
    public List<Topping> getSelectedToppings() {
        return selectedToppings;
    }
}
