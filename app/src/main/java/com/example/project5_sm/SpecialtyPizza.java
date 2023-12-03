package com.example.project5_sm;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.widget.Toast;

import java.util.ArrayList;

public class SpecialtyPizza extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ItemsAdapter pizzaAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.specialtypizza);

        // Find the RecyclerView in your layout
        recyclerView = findViewById(R.id.recyclerView);

        // Initialize and set the adapter
        pizzaAdapter = new ItemsAdapter(this, initializeListOfPizzas(), new ItemsAdapter.ItemClickListener() {
            @Override
            public void onItemCLick(Item items) {
            showToast(items.getPizzaName() +"clicked");
                //openRecycleViewer();
            }
        });
        recyclerView.setAdapter(pizzaAdapter);

        // Set the layout manager (could be LinearLayoutManager, GridLayoutManager, etc.)
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Optionally, you can add item decorations or set other properties for the RecyclerView
    }

    public void openRecycleViewer(){
        Intent intent = new Intent(this, selectedItemActivity.class);
        startActivity(intent);

    }
    private void showToast(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    // This method initializes your list of pizzas with data
    private ArrayList<Item> initializeListOfPizzas() {
        ArrayList<Item> listOfPizzas = new ArrayList<>();

        // Add TypeofPizza objects for the specialty pizzas
        listOfPizzas.add(new Item("Deluxe", R.drawable.deluxe, "Toppings 1", "Sauce 1"));
        listOfPizzas.add(new Item("Meatzza", R.drawable.meatzza, "Toppings 2", "Sauce 2"));
        listOfPizzas.add(new Item("Pepperoni", R.drawable.pepperoni, "Toppings 3", "Sauce 3"));
        listOfPizzas.add(new Item("Seafood", R.drawable.seafood, "Toppings 4", "Sauce 4"));
        listOfPizzas.add(new Item("Supreme", R.drawable.supreme, "Toppings 5", "Sauce 5"));

        // Add more pizzas as needed

        return listOfPizzas;
    }
}
