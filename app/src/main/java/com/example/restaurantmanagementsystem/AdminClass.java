package com.example.restaurantmanagementsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.inspector.StaticInspectionCompanionProvider;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.sql.Array;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class AdminClass extends AppCompatActivity implements ListFrag.Itemselected{
TextView tvAOrder,tvAName,tvAtotal;
ImageView ivPhone,ivLocation;
    String Ocall,Oloc;
    DatabaseReference firebaseDatabase;


    String abc="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_class);
    init();
if(findViewById(R.id.layout_portrait)!=null){
    FragmentManager manager =this.getSupportFragmentManager();
    manager.beginTransaction()
            .show(manager.findFragmentById(R.id.Detail_Frag))
            .show(manager.findFragmentById(R.id.List_Frag))
            .commit();


}




    }


    protected void init(){
        tvAName=findViewById(R.id.tvAName);
        tvAOrder=findViewById(R.id.tvAOrder);
        tvAtotal=findViewById(R.id.tvATotal);
        ivPhone=findViewById(R.id.ivPhone);
        ivLocation=findViewById(R.id.ivLocation);
        firebaseDatabase= FirebaseDatabase.getInstance().getReference().child("orders");

    }


    @Override
    public void onItemselected(int index) {
        if(findViewById(R.id.layout_portrait)!=null){
            FragmentManager manager = this.getSupportFragmentManager();
            manager.beginTransaction()
                    .show(manager.findFragmentById(R.id.Detail_Frag))
                    .hide(manager.findFragmentById(R.id.List_Frag))
                    .addToBackStack(null)
                    .commit();}
       abc= ListFrag.ref.get(index);
       firebaseDatabase.child(abc).addListenerForSingleValueEvent(new ValueEventListener() {
           @Override
           public void onDataChange(@NonNull DataSnapshot snapshot) {
               if(snapshot.exists()){

                   tvAOrder.setText(snapshot.child("Orderdetails").getValue().toString());
                   tvAName.setText(snapshot.child("name").getValue().toString());
                   tvAtotal.setText(snapshot.child("Bill").getValue().toString());
                   Ocall=snapshot.child("phoneNo").getValue().toString();
                   Oloc=snapshot.child("address").getValue().toString();

                   ivLocation.setOnClickListener(new View.OnClickListener() {
                       @Override
                       public void onClick(View v) {
                           Intent intent = new Intent(Intent.ACTION_VIEW,Uri.parse("geo:0,0?q="+Oloc));
                           startActivity(intent);
                       }
                   });
                   ivPhone.setOnClickListener(new View.OnClickListener() {
                       @Override
                       public void onClick(View v) {
                           Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+ Ocall));
                           startActivity(intent);
                       }
                   });
               }
               else{
                   Toast.makeText(AdminClass.this, "dont", Toast.LENGTH_SHORT).show();
               }


           }

           @Override
           public void onCancelled(@NonNull DatabaseError error) {

           }
       });

    }


}