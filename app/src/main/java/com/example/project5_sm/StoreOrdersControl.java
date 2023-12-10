package com.example.project5_sm;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Represents the activity for managing store orders.
 * @author Fraidoon Pourooshasb, Samman Pandey
 */
public class StoreOrdersControl extends AppCompatActivity {

    private StoreOrders sOrder;
    private MainActivity mainController;

    private ListView orderListView;
    private EditText totalEditText;
    private Spinner orderNumberSpinner;


    /**
     *  Initialize UI components and creates the activity
     */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.storeorder);

        orderListView = findViewById(R.id.StoreOrdersListView);
        totalEditText = findViewById(R.id.orderTotalTextField);
        orderNumberSpinner = findViewById(R.id.choiceBox);

        mainController = new MainActivity().get_control();
        sOrder = mainController.getStores();

        if (sOrder.numOrders() - 1 == 0) {
            showAlert("No Pizza", "Nothing available");
            return;
        }

        ArrayList<Integer> currentNumbers = sOrder.getOrderNumbers();
        ArrayAdapter<Integer> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, currentNumbers);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        orderNumberSpinner.setAdapter(adapter);

        orderNumberSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                showPizza();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                totalEditText.setText("");
                orderListView.setAdapter(null);
            }
        });
    }

    /**
     * Calculates and sets the total price based on the selected order.
     */
    private void setPrice() {
        int orderNum = (int) orderNumberSpinner.getSelectedItem();
        double taxes = 0.06625;
        double subtotal = sOrder.find(orderNum).totalCost();
        double total = (subtotal * taxes) + subtotal;
        String totalString = new DecimalFormat("#,##0.00").format(total);
        totalEditText.setText(totalString);
    }

    /**
     * Displays the list of pizzas for the selected order and updates the total price.
     */
    private void showPizza() {
        if (orderNumberSpinner.getSelectedItem() == null) {
            totalEditText.setText("");
            orderListView.setAdapter(null);
            return;
        }

        Order selectedOrder = sOrder.find((int) orderNumberSpinner.getSelectedItem());
        ArrayList<String> pizzas = selectedOrder.getPizzaStrings();
        ArrayAdapter<String> pizzaAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, pizzas);
        orderListView.setAdapter(pizzaAdapter);

        setPrice();
    }

    /**
     * Called when the activity is resumed. Invoked after the activity has been paused.
     */
    @Override
    protected void onResume() {
        super.onResume();
        setMainMenuController(mainController);
    }

    /**
     * Sets the main controller
     */
    private void setMainMenuController(MainActivity controller) {
        mainController = controller;
    }

    /**
     * Shows an alert dialog with the specified title and content.
     *
     * @param title   the title of the alert.
     * @param content the content of the alert.
     */
    private void showAlert(String title, String content) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title)
                .setMessage(content)
                .setPositiveButton("OK", null)
                .show();
    }

    /**
     * Handles the click event to cancel an order.
     *
     * @param view the view that was clicked.
     */
    public void cancelOrder(View view) {
        if (orderNumberSpinner.getSelectedItem() == null) {
            showAlert("Null", "Nothing selected");
            return;
        }

        ArrayList<Integer> ordersPlaced = mainController.get_control().get_placed();
        int number = (int) orderNumberSpinner.getSelectedItem();

        if (!contains(ordersPlaced, number)) {
            showAlert("Cancel", "Nothing placed");
            return;
        }

        sOrder = mainController.getStores();
        ArrayList<String> pizzaList = sOrder.find(number).getPizzaStrings();
        if (pizzaList.isEmpty()) {
            showAlert("Order", "Nothing in order");
            return;
        }

        removeOrder(number);
        ArrayAdapter<Integer> adapter = (ArrayAdapter<Integer>) orderNumberSpinner.getAdapter();
        adapter.remove(number);
        showAlert("Cancel Success!", "Cancelled!");

        if (!adapter.isEmpty()) {
            orderNumberSpinner.setSelection(0);
        } else {
            totalEditText.getText().clear();
            orderListView.setAdapter(null);
        }
    }

    /**
     * Checks if a given list contains a specific order number.
     *
     * @param list        the list to check.
     * @param orderNumber the order number to look for.
     * @return true if the list contains the order number, false otherwise.
     */
    private boolean contains(ArrayList<Integer> list, int orderNumber) {
        for (Integer integer : list) {
            if (Objects.equals(integer, orderNumber)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Removes a specific order number from the list of placed orders.
     *
     * @param orderNumber the order number to remove.
     */
    private void removeOrder(int orderNumber) {
        ArrayList<Integer> ordersPlaced = mainController.get_control().get_placed();
        ordersPlaced.removeIf(i -> i.equals(orderNumber));
    }
}
