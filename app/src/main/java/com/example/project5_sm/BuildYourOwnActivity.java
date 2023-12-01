package com.example.project5_sm;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class BuildYourOwnActivity extends AppCompatActivity {

    private MainActivity mainMenuController;
    private Spinner sizeDropdown;
    private ListView additionalToppingsListView;
    private ListView selectedToppingsListView;
    private CheckBox extraSauceCheckbox;
    private CheckBox extraCheeseCheckbox;
    private EditText amountTextField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.buildyourown);

        Intent intent = getIntent();
        mainMenuController = (MainActivity) intent.getSerializableExtra("mainMenuController");

        sizeDropdown = findViewById(R.id.sizeDropdown);
        extraSauceCheckbox = findViewById(R.id.extraSauceCheckbox);
        extraCheeseCheckbox = findViewById(R.id.extraCheeseCheckbox);
        additionalToppingsListView = findViewById(R.id.additionalToppingsListView);
        selectedToppingsListView = findViewById(R.id.selectedToppingsListView);
        amountTextField = findViewById(R.id.amountTextField);


        Button addToOrderButton = findViewById(R.id.addToOrderButton);
        addToOrderButton.setOnClickListener(this::handlePlaceOrder);

        initialize();

        sizeDropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                updatePizzaPrice();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Do nothing here
            }
        });
        additionalToppingsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                handleTransferTopping(additionalToppingsListView, selectedToppingsListView, position);
            }
        });

        selectedToppingsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                handleTransferTopping(selectedToppingsListView, additionalToppingsListView, position);
            }
        });

        extraSauceCheckbox.setOnCheckedChangeListener((buttonView, isChecked) -> updatePizzaPrice());

        extraCheeseCheckbox.setOnCheckedChangeListener((buttonView, isChecked) -> updatePizzaPrice());

    }

    private void initialize() {

        List<String> sizeOptions = new ArrayList<>();
        sizeOptions.add("Small");
        sizeOptions.add("Medium");
        sizeOptions.add("Large");

        ArrayAdapter<String> sizeAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, sizeOptions);
        sizeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sizeDropdown.setAdapter(sizeAdapter);
        sizeDropdown.setSelection(sizeAdapter.getPosition("Small"));


        List<String> additionalToppings = new ArrayList<>();


        additionalToppings.add("Chicken");
        additionalToppings.add("Beef");
        additionalToppings.add("Ham");
        additionalToppings.add("Pepperoni");
        additionalToppings.add("Shrimp");
        additionalToppings.add("Squid");
        additionalToppings.add("CrabMeats");
        additionalToppings.add("GreenPepper");
        additionalToppings.add("Onion");
        additionalToppings.add("Mushroom");
        additionalToppings.add("Pineapple");
        additionalToppings.add("BlackOlives");

        ArrayAdapter<String> toppingsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, additionalToppings);
        additionalToppingsListView.setAdapter(toppingsAdapter);

        ArrayAdapter<String> selectedToppingsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, new ArrayList<>());
        selectedToppingsListView.setAdapter(selectedToppingsAdapter);
    }


    private void updatePizzaPrice() {
        ArrayAdapter<String> selectedToppingsAdapter = (ArrayAdapter<String>) selectedToppingsListView.getAdapter();

        // Check if the adapter is not null
        if (selectedToppingsAdapter != null) {
            int selectedToppingsCount = selectedToppingsAdapter.getCount();

            double additionalToppingsCost = Math.max(selectedToppingsCount - 3, 0) * 1.49;
            double sizeCost = calculateSizeCost();
            double sauceCost = (extraSauceCheckbox.isChecked()) ? 1.0 : 0.0;
            double cheeseCost = (extraCheeseCheckbox.isChecked()) ? 1.0 : 0.0;
            double totalPrice = additionalToppingsCost + sizeCost + sauceCost + cheeseCost;

            amountTextField.setText(String.format("%.2f", totalPrice));
        }
    }

    private void handleTransferTopping(ListView sourceListView, ListView destinationListView, int position) {
        ArrayAdapter<String> sourceAdapter = (ArrayAdapter<String>) sourceListView.getAdapter();
        ArrayAdapter<String> destinationAdapter = (ArrayAdapter<String>) destinationListView.getAdapter();
        String selectedTopping = sourceAdapter.getItem(position);
        destinationAdapter.add(selectedTopping);
        sourceAdapter.remove(selectedTopping);
        updatePizzaPrice();
    }
    private double calculateSizeCost() {
        if (sizeDropdown.getSelectedItem() != null) {
            String selectedSize = sizeDropdown.getSelectedItem().toString();
            switch (selectedSize) {
                case "Medium":
                    return 10.99;
                case "Large":
                    return 12.99;
                default:
                    return 8.99;
            }
        }
        return 0;
    }

    private void reset() {
        sizeDropdown.setSelection(0);
        extraCheeseCheckbox.setChecked(false);
        extraSauceCheckbox.setChecked(false);
        amountTextField.setText(null);

        // Clear toppings lists
        clearListView(selectedToppingsListView);
        clearListView(additionalToppingsListView);

        // Set default additional toppings
        List<String> defaultToppings = new ArrayList<>();
        defaultToppings.add("Sausage");
        defaultToppings.add("Chicken");
        defaultToppings.add("Beef");
        defaultToppings.add("Ham");
        defaultToppings.add("Pepperoni");
        defaultToppings.add("Shrimp");
        defaultToppings.add("Squid");
        defaultToppings.add("CrabMeats");
        defaultToppings.add("GreenPepper");
        defaultToppings.add("Onion");
        defaultToppings.add("Mushroom");
        defaultToppings.add("Pineapple");
        defaultToppings.add("BlackOlives");

        ArrayAdapter<String> toppingsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, defaultToppings);
        additionalToppingsListView.setAdapter(toppingsAdapter);
    }
    private void clearListView(ListView listView) {
        ArrayAdapter adapter = (ArrayAdapter) listView.getAdapter();
        if (adapter != null) {
            adapter.clear();
            adapter.notifyDataSetChanged();
        }
    }
    public void handlePlaceOrder(View view) {

        if (mainMenuController == null) {
            Log.e("BuildYourOwnActivity", "mainMenuController is null");
            return;
        }

        ArrayAdapter<String> adapter = (ArrayAdapter<String>) selectedToppingsListView.getAdapter();

        if (adapter.getCount() > 2) {

        Pizza pizza = PizzaMaker.createPizza("Build Your Own");
        pizza.size = Size.valueOf(sizeDropdown.getSelectedItem().toString());
        pizza.extraCheese = (extraCheeseCheckbox.isChecked());
        pizza.extraSauce = (extraSauceCheckbox.isChecked());

        List<String> selectedToppings = new ArrayList<>();
        for (int i = 0; i < adapter.getCount(); i++) {
            selectedToppings.add(adapter.getItem(i));
        }
        pizza.toppings.addAll(selectedToppings);

        StoreOrders orders = mainMenuController.getStores();
        int currentOrderNumber = orders.nextAvailableNumber();

        Order currentOrder = orders.find(currentOrderNumber);
        currentOrder.addPizza(pizza);

        Toast.makeText(this, "Order Placed!", Toast.LENGTH_SHORT).show();

        reset();
    } else {
            Toast.makeText(this, "Not Enough Toppings!", Toast.LENGTH_SHORT).show();
        }
    }
}
