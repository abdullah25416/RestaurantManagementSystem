package com.example.restaurantmanagementsystem;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.ListFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashSet;

public class ListFrag extends ListFragment {
static ArrayList<String> ref=new ArrayList<>();
    static Itemselected parentactivity;
    DatabaseReference firebaseDatabase= FirebaseDatabase.getInstance().getReference().child("orders");
    public interface Itemselected{
        void onItemselected(int index) ;




    }

    public ListFrag() {


    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        parentactivity=(Itemselected) context;


    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


firebaseDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
    @Override
    public void onDataChange(@NonNull DataSnapshot snapshot) {
        if(ref.isEmpty()){
        for (DataSnapshot postSnapshot: snapshot.getChildren()) {

            if (!ref.contains(snapshot.getKey())) {
                ref.add(postSnapshot.getKey());
            }
        }

        }
        firebase();
getListView().setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        builder.setTitle("Order Delivered");
        builder.setMessage("Are you sure?");

        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {

                firebaseDatabase.child(ref.get(position)).removeValue();
                ref.remove(position);
                dialog.dismiss();
            }
        });

        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.dismiss();
            }
        });

        AlertDialog alert = builder.create();
        alert.show();
        return true;
    }
});

    }

    @Override
    public void onCancelled(@NonNull DatabaseError error) {

    }
});


    }




public void firebase(){


    setListAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1,ref));
}
    @Override
    public void onListItemClick(@NonNull ListView l, @NonNull View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        parentactivity.onItemselected(position);
    }

}