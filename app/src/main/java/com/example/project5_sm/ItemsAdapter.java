package com.example.project5_sm;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
/**
 * Adapter class for managing the display of items in a RecyclerView.
 * It binds the data to the layout and handles item click events.
 * @author Fraidoon Pourooshasb, Samman Pandey
 */
public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ItemsHolder>{
    private final Context context;
    private final ArrayList<Item> items;
    private final MainActivity mainMenuController;


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
        return items.size();
    }

    /**
     * Represents a ViewHolder for items in the RecyclerView.
     * It holds references to the views in the row layout.
     */
    public class ItemsHolder extends RecyclerView.ViewHolder {
        private final ImageView imageView;
        private final TextView pizzaName;
        private final TextView toppings;
        private final TextView sauce;

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
            RelativeLayout parentLayout = itemView.findViewById(R.id.rowView);


            parentLayout.setOnClickListener(view -> {

                Intent intent = new Intent(itemView.getContext(), selectedItemActivity.class);


                intent.putExtra("mainMenuController", mainMenuController);
                int position = getAdapterPosition();
                intent.putExtra("Sauce", items.get(position).getSauce());
                intent.putExtra("PizzaName", items.get(position).getPizzaName());
                intent.putExtra("Toppings", items.get(position).getToppings());
                intent.putExtra("Image", items.get(position).getImage());

                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            });
        }
    }

}