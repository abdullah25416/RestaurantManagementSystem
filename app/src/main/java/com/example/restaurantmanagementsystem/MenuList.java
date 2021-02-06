package com.example.restaurantmanagementsystem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MenuList extends AppCompatActivity implements ItemAdaptor.ItemSelected {
RecyclerView recyclerView;
RecyclerView.Adapter myAdaptor;
TextView textview3;
RecyclerView.LayoutManager layoutManager;
Button btnMyCart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_list);




        recyclerView = findViewById(R.id.list);



        recyclerView.setHasFixedSize(true);
        layoutManager=new LinearLayoutManager(MenuList.this);
        recyclerView.setLayoutManager(layoutManager);
        myAdaptor =new ItemAdaptor(MenuList.this,MyApplication.items);
        recyclerView.setAdapter(myAdaptor);




        btnMyCart=findViewById(R.id.btnMyCart);
        textview3=findViewById(R.id.textView3);
btnMyCart.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        if(MyApplication.carts.isEmpty()){
            Toast.makeText(MenuList.this, "Cart is Empty", Toast.LENGTH_SHORT).show();
        }

        else{
        Intent intent = new Intent(MenuList.this,Checkout.class);
        startActivity(intent);}
    }
});



    }

    @Override
    public void onItemClicked(int index) {
        Intent intent = new Intent(MenuList.this,AddToCart.class);
        intent.putExtra("index",String.valueOf(index));
        startActivity(intent);

    }
}