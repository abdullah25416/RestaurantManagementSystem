package com.example.restaurantmanagementsystem;

import android.app.Application;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

public class MyApplication extends Application {
    public static ArrayList<Item> items;
    public static ArrayList<CartItems> carts;
    public static String Uname;
    public static String hello;
    @Override
    public void onCreate() {
        super.onCreate();
        items=new ArrayList<>();
        carts=new ArrayList<>();
        Uname="Empty";
hello="yo";

        items.add(new Item("Drum Sticks","499","4 Pieces ","1"));
        items.add(new Item("Chicken Pop Corns","299","12 Pieces ","1"));
        items.add(new Item("Chicken Chowmein","299","Single Serving ","1"));
        items.add(new Item("Chicken Manchurian","499","With Egg Fried Rice ","1"));
        items.add(new Item("Cashew Nut Chicken","599","With Egg Fried Rice ","1"));
        items.add(new Item("Beef Chilli Dry","599","With Egg Fried Rice ","1"));
        items.add(new Item("Egg Tarts","299","2 Pieces ","1"));
        items.add(new Item("Chocolate Brownie","149","Single Serving ","1"));
        items.add(new Item("Mint Margarita","119","Fresh Drink ","1"));
        items.add(new Item("Fresh Lime","119","Fresh Drink ","1"));

    }
}
