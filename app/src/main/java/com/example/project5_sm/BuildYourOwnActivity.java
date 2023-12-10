package com.example.project5_sm;

import android.content.Intent;
import android.os.Bundle;
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

/**
 * This activity allows users to build their own pizza with customizable options.
 * It includes features like selecting size, toppings, and additional choices.
 * @author Fraidoon Pourooshasb, Samman Pandey
 */
public class BuildYourOwnActivity extends AppCompatActivity {

    /**
     * Controller for the main menu
     */
    private MainActivity mainMenuController;
    private Spinner sizeDropdown;
    private ListView additionalToppingsListView;
    private ListView selectedToppingsListView;
    private CheckBox extraSauceCheckbox;
    private CheckBox extraCheeseCheckbox;
    private EditText amountTextField;

    /**
     * Initializes the UI elements and sets up listeners for user interactions.
     */
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
            /**
             * Listener for item selection in the sizeDropdown.
             *
             * @param parentView       The AdapterView where the selection happened.
             * @param selectedItemView The view within the AdapterView that was clicked.
             * @param position         The position of the view in the adapter.
             * @param id               The row id of the item that is selected.
             */
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                updatePizzaPrice();
            }


            /**
             * Callback method to be invoked when the selection disappears from this view.
             *
             * @param parentView The AdapterView that now contains no selected item.
             */
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });
        additionalToppingsListView.setOnItemClickListener((parent, view, position, id) -> handleTransferTopping(additionalToppingsListView, selectedToppingsListView, position));

        selectedToppingsListView.setOnItemClickListener((parent, view, position, id) -> handleTransferTopping(selectedToppingsListView, additionalToppingsListView, position));

        extraSauceCheckbox.setOnCheckedChangeListener((buttonView, isChecked) -> updatePizzaPrice());

        extraCheeseCheckbox.setOnCheckedChangeListener((buttonView, isChecked) -> updatePizzaPrice());

    }


    /**
     * Initializes dropdowns and list views with default values.
     */
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


    /**
     * Updates the displayed pizza price based on selected options.
     */
    private void updatePizzaPrice() {
        ArrayAdapter<String> selectedToppingsAdapter = (ArrayAdapter<String>) selectedToppingsListView.getAdapter();

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

    /**
     * Handles the transfer of toppings between list views.
     *
     * @param sourceListView      The source ListView.
     * @param destinationListView The destination ListView.
     * @param position            The position of the selected item.
     */
    private void handleTransferTopping(ListView sourceListView, ListView destinationListView, int position) {
        ArrayAdapter<String> sourceAdapter = (ArrayAdapter<String>) sourceListView.getAdapter();
        ArrayAdapter<String> destinationAdapter = (ArrayAdapter<String>) destinationListView.getAdapter();
        String selectedTopping = sourceAdapter.getItem(position);
        destinationAdapter.add(selectedTopping);
        sourceAdapter.remove(selectedTopping);
        updatePizzaPrice();
    }

    /**
     * Calculates the cost of the selected pizza size.
     *
     * @return The calculated size cost.
     */
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

    /**
     * Resets the UI components to their default state.
     */
    private void reset() {
        sizeDropdown.setSelection(0);
        extraCheeseCheckbox.setChecked(false);
        extraSauceCheckbox.setChecked(false);
        amountTextField.setText(null);

        clearListView(selectedToppingsListView);
        clearListView(additionalToppingsListView);

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

    /**
     * Clears the items from a ListView.
     *
     * @param listView The ListView to be cleared.
     */
    private void clearListView(ListView listView) {
        ArrayAdapter adapter = (ArrayAdapter) listView.getAdapter();
        if (adapter != null) {
            adapter.clear();
            adapter.notifyDataSetChanged();
        }
    }

    /**
     * Handles the placement of the customized pizza order.
     *
     * @param view The View object triggering the method (button click).
     */
    public void handlePlaceOrder(View view) {

        ArrayAdapter<String> adapter = (ArrayAdapter<String>) selectedToppingsListView.getAdapter();

        if (adapter.getCount() > 2 && adapter.getCount() < 8) {

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
    } else if (adapter.getCount() < 3){
            Toast.makeText(this, "Not Enough Toppings!", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(this, "Too many toppings!", Toast.LENGTH_SHORT).show();
        }
    }
}
