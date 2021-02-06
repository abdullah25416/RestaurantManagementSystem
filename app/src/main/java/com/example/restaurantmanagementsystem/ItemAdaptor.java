package com.example.restaurantmanagementsystem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ItemAdaptor extends RecyclerView.Adapter<ItemAdaptor.ViewHolder> {
ArrayList<Item> items;
ItemSelected activity;


public interface ItemSelected{
void onItemClicked(int index);

}



    public ItemAdaptor(Context context, ArrayList<Item> list){
activity=(ItemSelected) context;
        items=list;

    }



public class  ViewHolder extends  RecyclerView.ViewHolder {
    TextView tvTitel,tvDetails,tvPrice;
    ImageView ivItem;
    public ViewHolder(@NonNull View itemView) {
        super(itemView);
        tvTitel=itemView.findViewById(R.id.tvTitle);
        tvDetails=itemView.findViewById(R.id.tvDetails);
        tvPrice=itemView.findViewById(R.id.tvPrice);
        ivItem=itemView.findViewById(R.id.ivItem);


itemView.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        activity.onItemClicked(items.indexOf((Item) itemView.getTag()));

    }
});

    }
}


    @NonNull
    @Override
    public ItemAdaptor.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_menu_list,parent,false);
        return new ViewHolder(v);
    }


    @Override
    public void onBindViewHolder(@NonNull ItemAdaptor.ViewHolder holder, int position) {

        holder.itemView.setTag(items.get(position));
        holder.tvTitel.setText(items.get(position).getTitle());
        holder.tvDetails.setText(items.get(position).getDescription());
        holder.tvPrice.setText(items.get(position).getPrice()+" Rs");
        if(items.get(position).getTitle().equals("Drum Sticks")){
        holder.ivItem.setImageResource(R.drawable.drumstick1);}
        else if(items.get(position).getTitle().equals("Chicken Pop Corns")){
            holder.ivItem.setImageResource(R.drawable.popcorn);
        }
        else if(items.get(position).getTitle().equals("Chicken Chowmein")){
            holder.ivItem.setImageResource(R.drawable.chowmein);
        }
        else if(items.get(position).getTitle().equals("Chicken Manchurian")){
            holder.ivItem.setImageResource(R.drawable.machchurian);
        }
        else if(items.get(position).getTitle().equals("Cashew Nut Chicken")){
            holder.ivItem.setImageResource(R.drawable.cashew);
        }
        else if(items.get(position).getTitle().equals("Beef Chilli Dry")){
            holder.ivItem.setImageResource(R.drawable.beef);
        }
        else if(items.get(position).getTitle().equals("Egg Tarts")){
            holder.ivItem.setImageResource(R.drawable.eggtart);
        }
        else if(items.get(position).getTitle().equals("Chocolate Brownie")){
            holder.ivItem.setImageResource(R.drawable.brownie);
        }
        else if(items.get(position).getTitle().equals("Mint Margarita")){
            holder.ivItem.setImageResource(R.drawable.mint);
        }
        else{
            holder.ivItem.setImageResource(R.drawable.lime);

        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
