package com.example.project5_sm;

import android.content.Intent;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class selectedItemActivity extends AppCompatActivity {
    private TextView pizzaName, sauce, toppings;
    private ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.selected_item);
        pizzaName = findViewById(R.id.pizzaName);
        toppings = findViewById(R.id.toppings);
        sauce = findViewById(R.id.sauce);
        image = findViewById(R.id.imageView);
        Intent intent = getIntent();
        pizzaName.setText(intent.getStringExtra("PizzaName"));
        toppings.setText(intent.getStringExtra("Toppings"));
        sauce.setText(intent.getStringExtra("Sauce"));
        image.setImageResource(intent.getIntExtra("Image", 0));
    }

}
