package com.example.restaurantmanagementsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class AddToCart extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
protected ImageView ivCartItem;
protected TextView tvCartDescription,tvCartPrice,tvCtitle;
protected Button btnAddToCart;
protected Spinner tvSpinner;
protected String title, price;
protected int ivalue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_to_cart);
        init();
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.Quantity, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        tvSpinner.setAdapter(adapter);
        tvSpinner.setOnItemSelectedListener(this);
        Intent intent = getIntent();
        ivalue= Integer.valueOf(intent.getStringExtra("index"));
        tvCartDescription.setText(MyApplication.items.get(ivalue).getDescription());
        tvCtitle.setText(MyApplication.items.get(ivalue).getTitle());
        price=MyApplication.items.get(ivalue).getPrice();
        tvCartPrice.setText(MyApplication.items.get(ivalue).getPrice()+" Rs");
        title=MyApplication.items.get(ivalue).getTitle();
        if(title.equals("Drum Sticks")){
            ivCartItem.setImageResource(R.drawable.drumstick1);}
        else if(title.equals("Chicken Pop Corns")){
            ivCartItem.setImageResource(R.drawable.popcorn);
        }
        else if(title.equals("Chicken Chowmein")){
            ivCartItem.setImageResource(R.drawable.chowmein);
        }
        else if(title.equals("Chicken Manchurian")){
            ivCartItem.setImageResource(R.drawable.machchurian);
        }
        else if(title.equals("Cashew Nut Chicken")){
            ivCartItem.setImageResource(R.drawable.cashew);
        }
        else if(title.equals("Beef Chilli Dry")){
            ivCartItem.setImageResource(R.drawable.beef);
        }
        else if(title.equals("Egg Tarts")){
            ivCartItem.setImageResource(R.drawable.eggtart);
        }
        else if(title.equals("Chocolate Brownie")){
            ivCartItem.setImageResource(R.drawable.brownie);
        }
        else if(title.equals("Mint Margarita")){
            ivCartItem.setImageResource(R.drawable.mint);
        }
        else{
            ivCartItem.setImageResource(R.drawable.lime);

        }
btnAddToCart.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Integer x;
        x=Integer.valueOf(MyApplication.items.get(ivalue).getPrice())*Integer.valueOf(tvSpinner.getSelectedItem().toString());
        MyApplication.carts.add(new CartItems(MyApplication.items.get(ivalue).getTitle(),String.valueOf(x),tvSpinner.getSelectedItem().toString()));
        Toast.makeText(AddToCart.this, "Item Added", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(AddToCart.this,MenuList.class);
        startActivity(intent);
    }
});



    }
    protected void init(){
        ivCartItem=findViewById(R.id.ivCartItem);
        tvCartDescription=findViewById(R.id.tvCartDescription);
        tvCartPrice=findViewById(R.id.tvCartPrice);
        btnAddToCart=findViewById(R.id.btnAddToCart);
         tvSpinner = findViewById(R.id.tvSpinner);
        tvCtitle=findViewById(R.id.tvCTitle);


    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
        Integer Tprice=Integer.valueOf(price);
        Tprice=Tprice*Integer.valueOf(text);
        tvCartPrice.setText(Tprice+" Rs");
        Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {


    }
}