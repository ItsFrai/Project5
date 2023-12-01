package com.example.project5_sm;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SpecialtyPizza extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.specialtypizza);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        ArrayList<Item> items = new ArrayList<>();

        Deluxe deluxe = new Deluxe();
        items.add(new Item(deluxe.pizzaType(), R.drawable.deluxe, deluxe.toppings.toString(), deluxe.sauce.toString()));
        Meatzza meatzza = new Meatzza();
        items.add(new Item(meatzza.pizzaType(), R.drawable.meatzza, meatzza.toppings.toString(), meatzza.sauce.toString()));
        Pepperoni pepperoni = new Pepperoni();
        items.add(new Item(pepperoni.pizzaType(), R.drawable.pepperoni, pepperoni.toppings.toString(), pepperoni.sauce.toString()));
        Seafood seafood = new Seafood();
        items.add(new Item(seafood.pizzaType(), R.drawable.seafood, seafood.toppings.toString(), seafood.sauce.toString()));
        Supreme supreme = new Supreme();
        items.add(new Item(supreme.pizzaType(), R.drawable.supreme, supreme.toppings.toString(), supreme.sauce.toString()));

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new ItemsAdapter(getApplicationContext(), items));
    }
}
