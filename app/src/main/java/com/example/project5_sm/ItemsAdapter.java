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
/**
 * Adapter class for managing the display of items in a RecyclerView.
 * It binds the data to the layout and handles item click events.
 * @author Fraidoon Pourooshasb, Samman Pandey
 */
public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ItemsHolder>{
    private Context context; //need the context to inflate the layout
    private ArrayList<Item> items; //need the data binding to each row of RecyclerView
    private MainActivity mainMenuController; // Add this field


    /**
     * Constructs an ItemsAdapter.
     *
     * @param context            The context to inflate the layout.
     * @param mainMenuController The main menu controller.
     * @param items              The list of items to display.
     */
    public ItemsAdapter(Context context,MainActivity mainMenuController,ArrayList<Item> items) {
        this.context = context;
        this.mainMenuController = mainMenuController;
        this.items = items;
    }

    /**
     * Inflates the row layout for the items in the RecyclerView.
     *
     * @param parent   The ViewGroup into which the new View will be added.
     * @param viewType The type of the new View.
     * @return A new ItemsHolder instance.
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
     * Assigns data values for each row according to their position when the item becomes visible on the screen.
     *
     * @param holder   The instance of ItemsHolder.
     * @param position The index of the item in the list of items.
     */
    @Override
    public void onBindViewHolder(@NonNull ItemsHolder holder, int position) {
        //assign values for each row
        holder.pizzaName.setText(items.get(position).getPizzaName());
        holder.toppings.setText(items.get(position).getToppings());
        holder.sauce.setText(items.get(position).getSauce());
        holder.imageView.setImageResource(items.get(position).getImage());
    }

    /**
     * Get the number of items in the ArrayList.
     * @return the number of items in the list.
     */
    @Override
    public int getItemCount() {
        return items.size(); //number of MenuItem in the array list.
    }

    /**
     * Represents a ViewHolder for items in the RecyclerView.
     * It holds references to the views in the row layout.
     */
    public class ItemsHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView pizzaName, toppings, sauce;
        private RelativeLayout parentLayout; //this is the row layout

        /**
         * Constructor for ItemsHolder.
         *
         * @param itemView The root view of the item layout.
         */
        public ItemsHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            pizzaName = itemView.findViewById(R.id.pizzaName);
            toppings = itemView.findViewById(R.id.toppings);
            sauce = itemView.findViewById(R.id.sauce);
            parentLayout = itemView.findViewById(R.id.rowView);

            // Set up click listener for the item
            parentLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // Create an intent to navigate to the selectedItemActivity
                    Intent intent = new Intent(itemView.getContext(), selectedItemActivity.class);

                    // Pass necessary data to the intent
                    intent.putExtra("mainMenuController", mainMenuController);
                    int position = getAdapterPosition();
                    intent.putExtra("Sauce", items.get(position).getSauce());
                    intent.putExtra("PizzaName", items.get(position).getPizzaName());
                    intent.putExtra("Toppings", items.get(position).getToppings());
                    intent.putExtra("Image", items.get(position).getImage());

                    // Add flag to start a new activity from outside an activity context
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                    // Start the activity
                    context.startActivity(intent);
                }
            });
        }
    }

}