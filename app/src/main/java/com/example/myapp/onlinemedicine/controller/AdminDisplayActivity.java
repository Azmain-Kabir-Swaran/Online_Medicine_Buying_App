package com.example.myapp.onlinemedicine.controller;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.myapp.onlinemedicine.R;
import com.example.myapp.onlinemedicine.model.Cart;
import com.example.myapp.onlinemedicine.view.CartView;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AdminDisplayActivity extends AppCompatActivity {

    private RecyclerView productsList;
    RecyclerView.LayoutManager layoutManager;
    private DatabaseReference productRef;
    private String pid = "";
    private ImageView fer_disp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_display);

        pid = getIntent().getStringExtra("pid");
        productsList = findViewById(R.id.product_list);
        productsList.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        productsList.setLayoutManager(layoutManager);
        fer_disp = (ImageView) findViewById(R.id.fer_disp);

        fer_disp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminDisplayActivity.this, AdminOrderListActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });

        productRef = FirebaseDatabase.getInstance().getReference().child("Cart List")
                .child("Admin View").child(pid).child("Products");
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseRecyclerOptions<Cart> options = new FirebaseRecyclerOptions.Builder<Cart>()
                .setQuery(productRef, Cart.class).build();

        FirebaseRecyclerAdapter<Cart, CartView> adapter = new FirebaseRecyclerAdapter<Cart, CartView>(options) {
            @Override
            protected void onBindViewHolder(@NonNull CartView holder, int position, @NonNull Cart model) {
                holder.txtProductQuantity.setText(model.getQuantity());
                holder.txtProductPrice.setText(model.getPrice());
                holder.txtProductName.setText(model.getPname());
            }

            @NonNull
            @Override
            public CartView onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
                View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cart_items_layout, viewGroup ,false);
                CartView cartView = new CartView(view);
                return cartView;
            }
        };

        productsList.setAdapter(adapter);
        adapter.startListening();

    }
}
