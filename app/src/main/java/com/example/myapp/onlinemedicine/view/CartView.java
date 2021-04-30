package com.example.myapp.onlinemedicine.view;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.myapp.onlinemedicine.R;
import com.example.myapp.onlinemedicine.interfaces.ItemClickListner;

public class CartView extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView txtProductName , txtProductPrice ,txtProductQuantity;
    private ItemClickListner itemClickListner;

    public CartView(@NonNull View itemView) {
        super(itemView);
        txtProductName = itemView.findViewById(R.id.card_product_name);
        txtProductPrice = itemView.findViewById(R.id.card_product_price);
        txtProductQuantity = itemView.findViewById(R.id.card_product_quantity);
    }

    @Override
    public void onClick(View v) {
        itemClickListner.onClick(v, getAdapterPosition() ,false);
    }

    public void setItemClickListner(ItemClickListner itemClickListner) {
        this.itemClickListner = itemClickListner;
    }
}