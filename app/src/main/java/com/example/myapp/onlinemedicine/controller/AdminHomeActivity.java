package com.example.myapp.onlinemedicine.controller;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.myapp.onlinemedicine.R;

public class AdminHomeActivity extends AppCompatActivity {

    private Button btn_logout, btn_viewCommand , btn_add_category;
    private AlertDialog.Builder alertDialogBuilder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);

        btn_logout = (Button) findViewById(R.id.btn_logout_admin);
        btn_viewCommand = (Button) findViewById(R.id.btn_command);
        btn_add_category = (Button) findViewById(R.id.btn_add_product);

        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminHomeActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
            }
        });

        btn_viewCommand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminHomeActivity.this, AdminOrderListActivity.class);
                startActivity(intent);
            }
        });

        btn_add_category.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminHomeActivity.this,AdminCategoryActivity.class);
                startActivity(intent);
            }
        });

    }
    @Override
    public void onBackPressed() {
        alertDialogBuilder = new AlertDialog.Builder(AdminHomeActivity.this);
        //for setting title;
        alertDialogBuilder.setTitle("Exit?");

        //for setting message;
        alertDialogBuilder.setMessage("Do you want to exit?");

        //for setting icon;
        alertDialogBuilder.setIcon(R.drawable.exit);


        //for setting positive button;
        alertDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //for exiting the app;
                finish();
            }
        });

        //for setting negative button;
        alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //for exiting the app;
                dialogInterface.cancel();
            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
}
