package com.example.project5_sm;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ItemsHolder>{
    private Context context; //need the context to inflate the layout
    private ArrayList<Item> items; //need the data binding to each row of RecyclerView
    private ItemClickListener mItemListner;

    public ItemsAdapter(Context context, ArrayList<Item> items, ItemClickListener itemClickListener) {
        this.context = context;
        this.items = items;
        this.mItemListner= itemClickListener;
    }




    /**
     * This method will inflate the row layout for the items in the RecyclerView
     * @param parent
     * @param viewType
     * @return
     */
    @NonNull
    @Override
    public ItemsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //inflate the row layout for the items
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recycleviewer, parent, false);

        return new ItemsHolder(view);
    }

    /**
     * Assign data values for each row according to their "position" (index) when the item becomes
     * visible on the screen.
     * @param holder the instance of ItemsHolder
     * @param position the index of the item in the list of items
     */
    @Override
    public void onBindViewHolder(@NonNull ItemsHolder holder, int position) {
        //assign values for each row
        holder.pizzaName.setText(items.get(position).getPizzaName());
        holder.toppings.setText(items.get(position).getToppings());
        holder.sauce.setText(items.get(position).getSauce());
        holder.imageView.setImageResource(items.get(position).getImage());

        holder.itemView.setOnClickListener(view -> {
            mItemListner.onItemCLick(items.get(position));
        });


    }

    /**
     * Get the number of items in the ArrayList.
     * @return the number of items in the list.
     */
    @Override
    public int getItemCount() {
        return items.size(); //number of MenuItem in the array list.
    }
    public interface ItemClickListener{
        void onItemCLick(Item items);
    }

    /**
     * Get the views from the row layout file, similar to the onCreate() method.
     */
    public class ItemsHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView pizzaName, toppings, sauce;
        //private RelativeLayout parentLayout; //this is the row layout

        public ItemsHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            pizzaName = itemView.findViewById(R.id.pizzaName);
            toppings = itemView.findViewById(R.id.toppings);
            sauce = itemView.findViewById(R.id.sauce);
            //parentLayout = itemView.findViewById(R.id.rowView);
        }
    }


}