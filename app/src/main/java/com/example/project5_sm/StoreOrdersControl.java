package com.example.project5_sm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class StoreOrdersControl extends AppCompatActivity{
    private MainActivity mainMenuController;

    private TextView orderNumberTextView;
    private ListView StoreOrdersListView;
    private TextView orderTotalTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.storeorder);
        orderNumberTextView = findViewById(R.id.orderNumberTextField);
        StoreOrdersListView = findViewById(R.id.StoreOrdersListView);
        orderTotalTextView = findViewById(R.id.orderTotalTextField);

        Intent intent = getIntent();
        mainMenuController = (MainActivity) intent.getSerializableExtra("mainMenuController");

        // Set up UI
        setOrderNumber();
        initializePizza();
        initializePrice();
    }

    private void setOrderNumber() {
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


            double tax = subtotalDouble * 0.06625;
            String taxString = new DecimalFormat("#,##0.00").format(tax);


            double total = subtotalDouble + tax;
            String totalString = new DecimalFormat("#,##0.00").format(total);
            StoreorderTotalTextView.setText(totalString);
        }

        private void initializePizza() {
            int currentOrderNum = mainMenuController.getStores().nextAvailableNumber();
            StoreOrders orders = mainMenuController.getStores();
            Order currentOrder = orders.find(currentOrderNum);

            ArrayList<String> pizzas = currentOrder.getPizzaStrings();
            ArrayAdapter<String> pizzaAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, pizzas);
            StoreOrdersListView.setAdapter(pizzaAdapter);
        }
        public void handleRemovePizza(View view, int selectedIndex) {
            int currentOrderNum = mainMenuController.getStores().nextAvailableNumber();
            StoreOrders orders = mainMenuController.getStores();
            Order currentOrder = orders.find(currentOrderNum);
            try {
                currentOrder.removePizza(selectedIndex);
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
}
