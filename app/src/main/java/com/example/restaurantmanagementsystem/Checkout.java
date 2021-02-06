package com.example.restaurantmanagementsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class Checkout extends AppCompatActivity implements  CartItemAdaptor.ItemSelect{
TextView tvTotal;
EditText etAddress;
Button btnPlaceOrder;
RecyclerView recyclerView1;
RecyclerView.Adapter myadaptor1;
RecyclerView.LayoutManager layoutManager1;
DatabaseReference firebaseDatabase;
protected String Fnum,Fname;
Integer Tprice=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);
        init();

recyclerView1.setHasFixedSize(true);
layoutManager1 = new LinearLayoutManager(Checkout.this);
recyclerView1.setLayoutManager(layoutManager1);
myadaptor1=new CartItemAdaptor(Checkout.this,MyApplication.carts);
recyclerView1.setAdapter(myadaptor1);
for(int i=0;i<MyApplication.carts.size();i++){

    Tprice=Tprice+Integer.valueOf(MyApplication.carts.get(i).getCprice());



}
new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(recyclerView1);



tvTotal.setText("Total Price: "+String.valueOf(Tprice)+" Rs");
btnPlaceOrder.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {


         if(etAddress.getText().toString().trim().isEmpty()) {
            etAddress.setError("Field Empty");


        }
       else if(MyApplication.carts.isEmpty()){
            Toast.makeText(Checkout.this, "No Item In Cart", Toast.LENGTH_SHORT).show();

        }
        else {
            firebaseDatabase.child("Users").child(MyApplication.Uname).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    Fname = snapshot.child("name").getValue().toString();
                    Fnum = snapshot.child("phoneNo").getValue().toString();
                    String Sdata = "";
                    for (int x = 0; x < MyApplication.carts.size(); x++) {
                        Sdata = Sdata + MyApplication.carts.get(x).getCname() + " x" + MyApplication.carts.get(x).getCquantity() + ",";


                    }
                    HashMap<String, String> data = new HashMap<>();
                    data.put("Uname", MyApplication.Uname);
                    data.put("name", Fname);
                    data.put("phoneNo", Fnum);
                    data.put("Orderdetails", Sdata);
                    data.put("Bill",tvTotal.getText().toString());
                    data.put("address", etAddress.getText().toString());
                    firebaseDatabase.child("orders").child(MyApplication.Uname).setValue(data);
                    Intent intent = new Intent(Checkout.this,AfterOrder.class);
                    intent.putExtra("name",Fname);
                    startActivity(intent);

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
        tvTotal=findViewById(R.id.tvTotal);
        etAddress=findViewById(R.id.etAddress);
        btnPlaceOrder=findViewById(R.id.btnPlaceOrder);
        recyclerView1=findViewById(R.id.Rlist);
        firebaseDatabase=FirebaseDatabase.getInstance().getReference();

    }


    @Override
    public void onItemClicked(int index) {

    }
    ItemTouchHelper.SimpleCallback itemTouchHelperCallback = new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.RIGHT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            Tprice=Tprice-Integer.valueOf(MyApplication.carts.get(viewHolder.getAdapterPosition()).getCprice());
            MyApplication.carts.remove(viewHolder.getAdapterPosition());
myadaptor1.notifyDataSetChanged();

            tvTotal.setText("Total Price: "+String.valueOf(Tprice)+" Rs");







        }
    };

}