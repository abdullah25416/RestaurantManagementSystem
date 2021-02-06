package com.example.restaurantmanagementsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class SignUp extends AppCompatActivity {
EditText etSName,etSPhone,etSUserName,etSPassword;
Button btnDetailSignUp;
String y;
int x;
DatabaseReference firebaseDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        init();
        btnDetailSignUp.setOnClickListener(new View.OnClickListener() {
            int x;

            @Override
            public void onClick(View v) {
                if(etSName.getText().toString().trim().isEmpty()){
                    etSName.setError("Field Empty");

                }
                 if(etSPhone.getText().toString().trim().isEmpty()){
                    etSPhone.setError("Field Empty");

                }
                 if(etSUserName.getText().toString().trim().isEmpty()){
                    etSUserName.setError("Field Empty");

                }
                 if(etSPassword.getText().toString().trim().isEmpty()){
                    etSPassword.setError("Field Empty");

                }
                else {


firebaseDatabase.child(etSUserName.getText().toString().trim()).addListenerForSingleValueEvent(new ValueEventListener() {
    @Override
    public void onDataChange(@NonNull DataSnapshot snapshot) {
        String userName;
        if(snapshot.exists()){
            etSUserName.setError("UserName Already Exists");
            Toast.makeText(SignUp.this, "Username Already Exists", Toast.LENGTH_SHORT).show();
            etSUserName.setText("");
        }
        else {
           HashMap<String,String> data = new HashMap<>();
           data.put("name",etSName.getText().toString().trim());
            data.put("phoneNo",etSPhone.getText().toString().trim());
            data.put("username",etSUserName.getText().toString().trim());
            data.put("password",etSPassword.getText().toString().trim());
            firebaseDatabase
                    .child(etSUserName.getText().toString().trim())
                    .setValue(data)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            Toast.makeText(SignUp.this, "Data Inserted successfully", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(SignUp.this,MainActivity.class);
                            startActivity(intent);
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(SignUp.this, "Data Insertion Failed", Toast.LENGTH_SHORT).show();

                }
            });

        }

    }

    @Override
    public void onCancelled(@NonNull DatabaseError error) {

        etSName.setText("On Cancelled");


    }

});





                }
            }
        });


    }

    protected void init(){
        etSName=findViewById(R.id.etSName);
        etSPhone=findViewById(R.id.etSPhone);
        etSUserName=findViewById(R.id.etSUserName);
        etSPassword=findViewById(R.id.etSPassword);
        btnDetailSignUp=findViewById(R.id.btnDetailSignUp);
        firebaseDatabase=FirebaseDatabase.getInstance().getReference().child("Users");
    }
}