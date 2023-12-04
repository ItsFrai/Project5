package com.example.project5_sm;

import android.content.Intent;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.text.DecimalFormat;

public class selectedItemActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private TextView pizzaName, sauce, toppings, price;
    private ImageView image;
    private Spinner spinner;
    private ArrayAdapter<String> adapter;
    private CheckBox cheeseCheckBox, sauceCheckBox;
    private Pizza pizza;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.selected_item);
        pizzaName = findViewById(R.id.pizzaNameSelected);;
        toppings = findViewById(R.id.toppingsSelected);
        sauce = findViewById(R.id.sauceSelected);
        image = findViewById(R.id.imageViewSelected);
        Intent intent = getIntent();
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

    private String formatDouble(Double price) {
        DecimalFormat format = new DecimalFormat("#.##");
        price = Double.parseDouble(format.format(price));
        return String.valueOf(price);
    }

    private String getPrice() {
        price = findViewById(R.id.price);
        cheeseCheckBox = findViewById(R.id.cheeseCheckBox);
        sauceCheckBox = findViewById(R.id.sauceCheckBox);

        Double price = pizza.price();
        String priceString = formatDouble(price);

        return "Price: $" + priceString;
    }

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
    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }

    public void extraSauceClick(View view) {
        if (sauceCheckBox.isChecked()) {
            pizza.extraSauce = true;
        }
        else {
            pizza.extraSauce = false;
        }

        price.setText(getPrice());
    }

    public void extraCheeseClick(View view) {
        if (cheeseCheckBox.isChecked()) {
            pizza.extraCheese = true;
        }
        else {
            pizza.extraCheese = false;
        }

        price.setText(getPrice());
    }
}
