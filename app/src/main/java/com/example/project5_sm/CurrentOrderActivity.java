package com.example.project5_sm;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Objects;

public class CurrentOrderActivity extends AppCompatActivity {

    private MainActivity mainMenuController;

    private TextView orderNumberTextView;
    private ListView currentOrdersListView;
    private TextView subtotalTextView;
    private TextView salesTaxTextView;
    private TextView orderTotalTextView;

    private int selectedPizzaIndex = -1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.currentorder);

        // Initialize UI components
        orderNumberTextView = findViewById(R.id.orderNumberTextField);
        currentOrdersListView = findViewById(R.id.currentOrdersListView);
        subtotalTextView = findViewById(R.id.subtotalTextField);
        salesTaxTextView = findViewById(R.id.salesTaxTextField);
        orderTotalTextView = findViewById(R.id.orderTotalTextField);

        Intent intent = getIntent();
        mainMenuController = (MainActivity) intent.getSerializableExtra("mainMenuController");

        // Set up UI
        setOrderNumber();
        initializePizza();
        initializePrice();
    }


    private void setOrderNumber() {
        String orderNumber = String.valueOf(mainMenuController.getStores().nextAvailableNumber());
        orderNumberTextView.setText(orderNumber);
    }

    private void initializePrice() {
        int currentOrderNum = mainMenuController.getStores().nextAvailableNumber();
        StoreOrders orders = mainMenuController.getStores();
        Order currentOrder = orders.find(currentOrderNum);

        double subtotalDouble = currentOrder.totalCost();
        String subtotalString = new DecimalFormat("#,##0.00").format(subtotalDouble);
        subtotalTextView.setText(subtotalString);

        double tax = subtotalDouble * 0.06625;
        String taxString = new DecimalFormat("#,##0.00").format(tax);
        salesTaxTextView.setText(taxString);

        double total = subtotalDouble + tax;
        String totalString = new DecimalFormat("#,##0.00").format(total);
        orderTotalTextView.setText(totalString);
    }

    private void initializePizza() {
        int currentOrderNum = mainMenuController.getStores().nextAvailableNumber();
        StoreOrders orders = mainMenuController.getStores();
        Order currentOrder = orders.find(currentOrderNum);

        ArrayList<String> pizzas = currentOrder.getPizzaStrings();
        ArrayAdapter<String> pizzaAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, pizzas);
        currentOrdersListView.setAdapter(pizzaAdapter);
        currentOrdersListView.setOnItemClickListener((adapterView, view, i, l) -> selectedPizzaIndex = i);
    }
    public void handleRemovePizza(View view) {
        int currentOrderNum = mainMenuController.getStores().nextAvailableNumber();
        StoreOrders orders = mainMenuController.getStores();
        Order currentOrder = orders.find(currentOrderNum);
        try {
            if (selectedPizzaIndex != -1) {
                currentOrder.removePizza(selectedPizzaIndex);
                selectedPizzaIndex = -1;
            } else {
                Toast.makeText(this, "No pizza selected to remove", Toast.LENGTH_SHORT).show();
            }
        } catch (IndexOutOfBoundsException e) {
            Toast.makeText(this, "No pizza to remove", Toast.LENGTH_SHORT).show();
            return;
        }
        initializePizza();
        initializePrice();
    }
    public void handlePlaceOrder(View view) {
        int currentOrderNum = mainMenuController.getStores().nextAvailableNumber();
        StoreOrders orders = mainMenuController.getStores();

        ArrayList<String> pizzaList = orders.find(currentOrderNum).getPizzaStrings();
        if (pizzaList.isEmpty()) {
            Toast.makeText(this, "No  pizzas to place", Toast.LENGTH_SHORT).show();
            return;
        }

        orders.addOrder(orders.find(currentOrderNum));
        mainMenuController.get_placed().add(currentOrderNum);

        initializePizza();
        initializePrice();
        setOrderNumber();
        Toast.makeText(this, "Order Placed", Toast.LENGTH_SHORT).show();
    }
}

