package com.example.myapp.onlinemedicine.controller;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.myapp.onlinemedicine.R;
import com.example.myapp.onlinemedicine.model.Order;
import com.example.myapp.onlinemedicine.view.OrderView;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AdminOrderListActivity extends AppCompatActivity {

    private RecyclerView commandList;
    private DatabaseReference commandRef;
    private ImageView fer_com;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_order_list);

        commandRef = FirebaseDatabase.getInstance().getReference().child("Waiting Orders");
        commandList = findViewById(R.id.command_list);
        commandList.setLayoutManager(new LinearLayoutManager(this));
        fer_com = (ImageView) findViewById(R.id.fer_com);
        fer_com.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminOrderListActivity.this, AdminHomeActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerOptions<Order> options = new FirebaseRecyclerOptions.Builder<Order>().setQuery(commandRef, Order.class)
                .build();

        FirebaseRecyclerAdapter<Order, OrderView> adapter = new FirebaseRecyclerAdapter<Order, OrderView>(options) {
            @Override
            protected void onBindViewHolder(@NonNull OrderView holder, @SuppressLint("RecyclerView") final int position, @NonNull final Order model) {
                holder.userName.setText(model.getName());
                holder.userPhone.setText(model.getPhone());
                holder.userCity.setText(model.getAddress() + " " + model.getCity());
                holder.userDateTime.setText(model.getDate() + " " + model.getTime());
                holder.userPrice.setText(model.getTotalPrice() + " BDT");


                holder.btn_all_products.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String pid = getRef(position).getKey();
                        Intent intent = new Intent(AdminOrderListActivity.this, AdminDisplayActivity.class);
                        intent.putExtra("pid", pid);
                        startActivity(intent);
                    }
                });

                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        CharSequence sequence[] = new CharSequence[]{
                                "Yes", "No"
                        };
                        AlertDialog.Builder builder = new AlertDialog.Builder(AdminOrderListActivity.this);
                        builder.setTitle("Checking Delivery:");
                        builder.setItems(sequence, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (which == 0) {
                                    String pid = getRef(position).getKey();
                                    removeCommand(pid);
                                } else {
                                    finish();
                                }
                            }
                        });
                        builder.show();
                    }
                });
            }

            @NonNull
            @Override
            public OrderView onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
                View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.order_layout, viewGroup, false);
                return new OrderView(view);
            }
        };

        commandList.setAdapter(adapter);
        adapter.startListening();
    }

    private void removeCommand(String pid) {
        commandRef.child(pid).removeValue();
    }
}


