package com.example.restaurantmanagementsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AfterOrder extends AppCompatActivity {
TextView tvFinal;
protected String string;
Button btnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_order);
    tvFinal=findViewById(R.id.tvFinal);
    btnBack=findViewById(R.id.btnBackToMenu);
        Intent intent = getIntent();
        string=intent.getStringExtra("name");
    tvFinal.setText(string+" Your Order has Been Recieved");
    btnBack.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            MyApplication.carts.clear();
            Intent intent = new Intent(AfterOrder.this,MenuList.class);
            startActivity(intent);
        }
    });
    }
}