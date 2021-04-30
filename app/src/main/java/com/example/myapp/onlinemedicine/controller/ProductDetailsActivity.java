package com.example.myapp.onlinemedicine.controller;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.example.myapp.onlinemedicine.R;
import com.example.myapp.onlinemedicine.model.Product;
import com.example.myapp.onlinemedicine.prevalent.Prevalent;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class ProductDetailsActivity extends AppCompatActivity {

    private Button addToCard;
    private ImageView productImage,rr;
    private ElegantNumberButton numberButton;
    private TextView productPrice, productDescription, productName;
    private String productID = "", states = "normal";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);
        productID = getIntent().getStringExtra("pid");

        addToCard = (Button) findViewById(R.id.product_add_to_card_btn);
        numberButton = (ElegantNumberButton) findViewById(R.id.number_btn);
        productImage = (ImageView) findViewById(R.id.product_image_details);
        productPrice = (TextView) findViewById(R.id.product_price_details);
        productName = (TextView) findViewById(R.id.product_name_details);
        productDescription = (TextView) findViewById(R.id.product_description_details);
        rr = (ImageView) findViewById(R.id.rr);

        rr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProductDetailsActivity.this, HomeActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                finish();
            }
        });

        getProductDetails(productID);

        addToCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (states == "delivered" || states == "Undelivered") {

                    Toast.makeText(ProductDetailsActivity.this, "Please wait for the confirmation of your previous order", Toast.LENGTH_LONG).show();
                    
                }
                else {
                    addingToCard();
                }
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();

        customerOrder();
    }

    private void addingToCard() {

        String saveCurrentDate, saveCurrentTIme;
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat currentDate = new SimpleDateFormat("MMM dd,yyyy");
        saveCurrentDate = currentDate.format(calendar.getTime());
        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
        saveCurrentTIme = currentTime.format(calendar.getTime());

        final DatabaseReference cardListRef = FirebaseDatabase.getInstance().getReference().child("Cart List");

        final HashMap<String, Object> cardMap = new HashMap<>();
        cardMap.put("pid", productID);
        cardMap.put("pname", productName.getText().toString());
        cardMap.put("price", productPrice.getText().toString());
        cardMap.put("date", saveCurrentDate);
        cardMap.put("time", saveCurrentTIme);
        cardMap.put("quantity", numberButton.getNumber());
        cardMap.put("discount", "");

        cardListRef.child("User View").child(Prevalent.currentOnlineUser.getPhone()).child("Products").child(productID).
                updateChildren(cardMap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    cardListRef.child("Admin View").child(Prevalent.currentOnlineUser.getPhone()).child("Products").child(productID).
                            updateChildren(cardMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(ProductDetailsActivity.this, "Product added to shopping cart", Toast.LENGTH_SHORT).show();

                                Intent intent = new Intent(ProductDetailsActivity.this, HomeActivity.class);
                                startActivity(intent);
                            }
                        }
                    });
                }
            }
        });
    }

    private void getProductDetails(String productID) {

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("Products");

        databaseReference.child(productID).addValueEventListener(new ValueEventListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    Product product = dataSnapshot.getValue(Product.class);
                    productName.setText(product.getPname());
                    productDescription.setText(product.getDescription());
                    productPrice.setText(product.getPrice());
                    Picasso.get().load(product.getImage()).into(productImage);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void customerOrder() {
        DatabaseReference reference;
        reference = FirebaseDatabase.getInstance().getReference().child("Waiting Orders").child(Prevalent.currentOnlineUser.getPhone());
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {

                    String state = dataSnapshot.child("state").getValue().toString();

                    if (state.equals("delivered")) {

                        states = "delivered";

                    } else if (state.equals("Undelivered")) {

                        states = "Undelivered";

                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
