package com.example.restaurantmanagementsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
EditText etUserName,etPassword;
Button btnLogin,btnSignUp;
String password;
int x=0;

    DatabaseReference firebaseDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
       btnSignUp.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent = new Intent(MainActivity.this,SignUp.class);
               startActivity(intent);
           }
       });

       btnLogin.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

               if(etUserName.getText().toString().trim().isEmpty()){
                   etUserName.setError("Field Empty");}
               if(etPassword.getText().toString().trim().isEmpty()){
                   etPassword.setError("Field Empty");}
               if(etUserName.getText().toString().trim().equals("admin")&&etPassword.getText().toString().trim().equals("admin")){
                   Intent intent = new Intent(MainActivity.this,AdminClass.class);
                   startActivity(intent);

               }
 else{
                   firebaseDatabase.child(etUserName.getText().toString().trim()).addListenerForSingleValueEvent(new ValueEventListener() {
                       @Override
                       public void onDataChange(@NonNull DataSnapshot snapshot) {
                           if(snapshot.exists()){
                               if(etPassword.getText().toString().trim().equals(snapshot.child("password").getValue())){
                                   MyApplication.Uname = etUserName.getText().toString().trim();;
                                   Intent intent = new Intent(MainActivity.this,MenuList.class);
                                   startActivity(intent);
                               }
                               else {
                                   etPassword.setError("Wrong Password");
                                   etPassword.setText("");
                               }
                           }
                           else{
                               etUserName.setError("Wrong Username");
                               etPassword.setError("");
                               etUserName.setText("");
                               etPassword.setText("");
                           }
                       }

                       @Override
                       public void onCancelled(@NonNull DatabaseError error) {

                       }
                   });
               }
           }


       });


    }
    protected void init(){
        etUserName=findViewById(R.id.etUserName);
        etPassword=findViewById(R.id.etPassword);
        btnLogin=findViewById(R.id.btnLogin);
        btnSignUp=findViewById(R.id.btnSignUp);
        firebaseDatabase=FirebaseDatabase.getInstance().getReference().child("Users");
    }

}