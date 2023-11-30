package com.example.project5_sm;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class BuildYourOwnActivity extends AppCompatActivity {

    private Spinner sizeDropdown;
    private ListView additionalToppingsListView;
    private ListView selectedToppingsListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.buildyourown);

        sizeDropdown = findViewById(R.id.sizeDropdown);
        ImageView pizzaImage = findViewById(R.id.pizzaImage);
        RadioGroup twoSauceRadioGroup = findViewById(R.id.twoSauceRadioGroup);
        CheckBox extraSauceCheckbox = findViewById(R.id.extraSauceCheckbox);
        CheckBox extraCheeseCheckbox = findViewById(R.id.extraCheeseCheckbox);
        additionalToppingsListView = findViewById(R.id.additionalToppingsListView);
        Button addToppingButton = findViewById(R.id.addToppingButton);
        Button removeToppingButton = findViewById(R.id.removeToppingButton);
        selectedToppingsListView = findViewById(R.id.selectedToppingsListView);
        EditText amountTextField = findViewById(R.id.amountTextField);

        // Set default values and load pizza image
        initialize();

        // Set event listeners
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

        twoSauceRadioGroup.setOnCheckedChangeListener((group, checkedId) -> updatePizzaPrice());

        extraSauceCheckbox.setOnCheckedChangeListener((buttonView, isChecked) -> updatePizzaPrice());

        extraCheeseCheckbox.setOnCheckedChangeListener((buttonView, isChecked) -> updatePizzaPrice());

        // Add listeners for the topping buttons
        addToppingButton.setOnClickListener(this::handleAddTopping);

        removeToppingButton.setOnClickListener(this::handleRemoveTopping);
    }

    public void BackButton(View view) {
        // Create an Intent to navigate back to the main menu or another activity
        Intent intent = new Intent(this, MainActivity.class); // Replace MainActivity with the desired activity

        // Start the activity
        startActivity(intent);
    }

    private void initialize() {
        // Set default size value
        List<String> sizeOptions = new ArrayList<>();
        sizeOptions.add("Small");
        sizeOptions.add("Medium");
        sizeOptions.add("Large");

        ArrayAdapter<String> sizeAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, sizeOptions);
        sizeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sizeDropdown.setAdapter(sizeAdapter);

        // Set default pizza image

        // Set additional toppings
        List<String> additionalToppings = new ArrayList<>();
        additionalToppings.add("Sausage");
        additionalToppings.add("Chicken");
        // Add other toppings...

        ArrayAdapter<String> toppingsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, additionalToppings);
        additionalToppingsListView.setAdapter(toppingsAdapter);
    }

    private void updatePizzaPrice() {
        // Implement the logic to update the pizza price based on the selected options
        // ...

        // Update the amountTextField
        // amountTextField.setText(String.format("%.2f", totalPrice));
    }

    public void handleAddTopping(View view) {
        String selectedTopping = (String) additionalToppingsListView.getSelectedItem();

        // Check if a topping is selected and the limit is not exceeded
        if (selectedTopping != null && selectedToppingsListView.getCount() <= 6) {
            // Add the selected topping to the selectedToppingsListView
            ListAdapter selectedToppingsListAdapter = selectedToppingsListView.getAdapter();
            if (selectedToppingsListAdapter instanceof ArrayAdapter) {
                ArrayAdapter<String> selectedToppingsAdapter = (ArrayAdapter<String>) selectedToppingsListAdapter;
                selectedToppingsAdapter.add(selectedTopping);
            }
            // Remove the selected topping from the additionalToppingsListView
            ListAdapter additionalToppingsListAdapter = additionalToppingsListView.getAdapter();
            if (additionalToppingsListAdapter instanceof ArrayAdapter) {
                ArrayAdapter<String> additionalToppingsAdapter = (ArrayAdapter<String>) additionalToppingsListAdapter;
                additionalToppingsAdapter.remove(selectedTopping);
            }

            // Update the pizza price
            updatePizzaPrice();
        } else {
            Toast.makeText(this, "Maximum 7 toppings allowed.", Toast.LENGTH_SHORT).show();
        }
    }


    public void handleRemoveTopping(View view) {
        // Get the selected topping from the selectedToppingsListView
        String selectedTopping = (String) selectedToppingsListView.getSelectedItem();

        // Check if a topping is selected
        if (selectedTopping != null) {
            // Add the selected topping back to the additionalToppingsListView
            ArrayAdapter<String> additionalToppingsAdapter = (ArrayAdapter<String>) additionalToppingsListView.getAdapter();
            additionalToppingsAdapter.add(selectedTopping);

            // Remove the selected topping from the selectedToppingsListView
            ArrayAdapter<String> selectedToppingsAdapter = (ArrayAdapter<String>) selectedToppingsListView.getAdapter();
            selectedToppingsAdapter.remove(selectedTopping);

            // Update the pizza price
            updatePizzaPrice();
        } else {
            // Show a Toast message if no topping is selected
            Toast.makeText(this, "Minimum 3 toppings", Toast.LENGTH_SHORT).show();
        }
    }
    public void handlePlaceOrder(View view) {
        // Implement the logic to handle placing the order
        // For example, you can show a confirmation message or navigate to a new activity
        Toast.makeText(this, "Order Placed!", Toast.LENGTH_SHORT).show();
    }
}
