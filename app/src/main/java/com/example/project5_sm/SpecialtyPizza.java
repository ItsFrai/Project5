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
        Dessert dessert = new Dessert();
        items.add(new Item(dessert.pizzaType(), R.drawable.dessert, dessert.toppings.toString(), dessert.sauce.toString()));
        Breakfast breakfast = new Breakfast();
        items.add(new Item(breakfast.pizzaType(), R.drawable.breakfast, breakfast.toppings.toString(), breakfast.sauce.toString()));
        Hawaiian hawaiian = new Hawaiian();
        items.add(new Item(hawaiian.pizzaType(), R.drawable.hawaiian, hawaiian.toppings.toString(), hawaiian.sauce.toString()));
        Veggie veggie = new Veggie();
        items.add(new Item(veggie.pizzaType(), R.drawable.dessert, veggie.toppings.toString(), veggie.sauce.toString()));
        BaconCheeseBurger baconCheeseBurger = new BaconCheeseBurger();
        items.add(new Item(baconCheeseBurger.pizzaType(), R.drawable.baconcheeseburger, baconCheeseBurger.toppings.toString(), baconCheeseBurger.sauce.toString()));

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new ItemsAdapter(getApplicationContext(), items));
    }
}

