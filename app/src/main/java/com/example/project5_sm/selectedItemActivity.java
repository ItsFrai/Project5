package com.example.project5_sm;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.text.DecimalFormat;
import java.util.ArrayList;


/**
 * Represents the activity for viewing and customizing a selected pizza item.
 * @ Fraidoon Pourooshasb, Samman Pandey
 */
public class selectedItemActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private MainActivity mainMenuController;
    private TextView pizzaName, sauce, toppings, price;
    private ImageView image;
    private Spinner spinner;
    private ArrayAdapter<String> adapter;
    private CheckBox cheeseCheckBox, sauceCheckBox;
    private Pizza pizza;

    /**
     *  Initialize UI components and creates the activity
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.selected_item);
        pizzaName = findViewById(R.id.pizzaNameSelected);;
        toppings = findViewById(R.id.toppingsSelected);
        sauce = findViewById(R.id.sauceSelected);
        image = findViewById(R.id.imageViewSelected);
        Intent intent = getIntent();
        mainMenuController = (MainActivity) intent.getSerializableExtra("mainMenuController");
        pizzaName.setText(intent.getStringExtra("PizzaName"));
        toppings.setText(intent.getStringExtra("Toppings"));
        sauce.setText(intent.getStringExtra("Sauce"));
        image.setImageResource(intent.getIntExtra("Image", 0));

        // Set size spinner
        spinner = findViewById(R.id.specialtySize);
        String[] size = {"Small", "Medium", "Large"}; //could be a list from the backend
        adapter = new ArrayAdapter<>(
                this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, size);


        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this); //add the listener

        // Set initial price
        String pizzaType = intent.getStringExtra("PizzaName");
        pizza = PizzaMaker.createPizza(pizzaType);
        CharSequence priceSeq = getPrice();
        price.setText(priceSeq);
    }


    /**
     * Formats a double value to a string with two decimal places.
     *
     * @param price the price to format.
     * @return the formatted price string.
     */
    private String formatDouble(Double price) {
        DecimalFormat format = new DecimalFormat("#.##");
        price = Double.parseDouble(format.format(price));
        return String.valueOf(price);
    }


    /**
     * Retrieves the formatted price string based on the selected options.
     *
     * @return the formatted price string.
     */
    private String getPrice() {
        price = findViewById(R.id.price);
        cheeseCheckBox = findViewById(R.id.cheeseCheckBox);
        sauceCheckBox = findViewById(R.id.sauceCheckBox);

        Double price = pizza.price();
        String priceString = formatDouble(price);

        return "Price: $" + priceString;
    }

    /**
     *  Get the selected item from the spinner
     *  Display a toast with the selected item
     *  Update the displayed price
     *
     */
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String size = spinner.getSelectedItem().toString(); //get the selected item
        Toast.makeText(this, size, Toast.LENGTH_SHORT).show(); //do something about the selected item

        if (size.equals("Small")) {
            pizza.size = Size.Small;
        }
        else if (size.equals("Medium")) {
            pizza.size = Size.Medium;
        }
        else {
            pizza.size = Size.Large;
        }
        price.setText(getPrice());

    }
    /**
     *  If nothing is selected, then nothing will happen
     *
     */
    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }


    /**
     * Handles the click event for the extra sauce checkbox.
     *
     * @param view the view that was clicked.
     */
    public void extraSauceClick(View view) {
        if (sauceCheckBox.isChecked()) {
            pizza.extraSauce = true;
        }
        else {
            pizza.extraSauce = false;
        }

        price.setText(getPrice());
    }

    /**
     * Handles the click event for the extra cheese checkbox.
     *
     * @param view the view that was clicked.
     */
    public void extraCheeseClick(View view) {
        if (cheeseCheckBox.isChecked()) {
            pizza.extraCheese = true;
        }
        else {
            pizza.extraCheese = false;
        }

        price.setText(getPrice());
    }


    /**
     * Handles the click event for the "Order" button.
     *
     * Displays a toast indicating that the pizza has been added to the order.
     * Updates the current order with the selected pizza.
     * Resets spinner and checkboxes to their initial state.
     * Creates a new pizza for customization.
     * Updates the displayed price.
     *
     * @param view the view that was clicked.
     */
    public void specialtyPizzaButtonOrder(View view) {

        String message = "Pizza Added to Order!";
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();

        Intent intent = getIntent();
        mainMenuController = (MainActivity) intent.getSerializableExtra("mainMenuController");

        StoreOrders storeOrders = mainMenuController.getStores();
        int currentOrderNumber = storeOrders.nextAvailableNumber();
        Order currentOrder = storeOrders.find(currentOrderNumber);
        currentOrder.addPizza(pizza);


        spinner.setSelection(0);
        cheeseCheckBox.setChecked(false);
        sauceCheckBox.setChecked(false);

        String pizzaType = intent.getStringExtra("PizzaName");
        pizza = PizzaMaker.createPizza(pizzaType);
        CharSequence priceSeq = getPrice();
        price.setText(priceSeq);

    }
}
