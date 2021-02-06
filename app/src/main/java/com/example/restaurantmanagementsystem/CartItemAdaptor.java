package com.example.restaurantmanagementsystem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CartItemAdaptor extends RecyclerView.Adapter<CartItemAdaptor.ViewHolder> {
ArrayList<CartItems> carts;
ItemSelect activity1;
public interface  ItemSelect{
    void  onItemClicked(int index);
}


    public CartItemAdaptor(Context context, ArrayList<CartItems> list){
     carts= list;
     activity1=(ItemSelect) context;

}



public class ViewHolder extends RecyclerView.ViewHolder {
    TextView tvName,tvPrice,tvQuan;
        public ViewHolder(@NonNull View itemView) {
        super(itemView);
        tvName=itemView.findViewById(R.id.tvCName);
            tvPrice=itemView.findViewById(R.id.tvCprice);
            tvQuan=itemView.findViewById(R.id.tvCQuantity);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    activity1.onItemClicked(carts.indexOf((CartItems) itemView.getTag()));
                }
            });
    }
}

    @NonNull
    @Override
    public CartItemAdaptor.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cartitem,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CartItemAdaptor.ViewHolder holder, int position) {
holder.itemView.setTag(carts.get(position));
        holder.tvName.setText(carts.get(position).getCname());
        holder.tvQuan.setText(carts.get(position).getCquantity());
        holder.tvPrice.setText(carts.get(position).getCprice());
    }

    @Override
    public int getItemCount() {
        return carts.size();
    }
}
