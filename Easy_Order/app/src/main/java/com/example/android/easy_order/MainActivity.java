package com.example.android.easy_order;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView quantityDisplay;
    TextView priceDisplay;

    Button addButton;
    Button minusButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        priceDisplay = (TextView) findViewById(R.id.price_display);
        quantityDisplay = (TextView) findViewById(R.id.quantity_display);

        addButton = (Button) findViewById(R.id.plus_button);
        minusButton = (Button) findViewById(R.id.minus_button);

        quantityDisplay.setText("" + 0);
        priceDisplay.setText("$" + 0);

        addButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                String stringQuantity = quantityDisplay.getText().toString();
                int quantity = Integer.parseInt(stringQuantity);

                quantity++;
                quantityDisplay.setText("" + quantity);

                priceCalculator(quantity);


            }
        });

        minusButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                String stringQuantity = quantityDisplay.getText().toString();
                int quantity = Integer.parseInt(stringQuantity);

                if(quantity > 0) {
                    quantity--;
                    quantityDisplay.setText("" + quantity);

                    priceCalculator(quantity);
                }

            }
        });

    }

    public void priceCalculator(int quantity) {
        double price = 2.5 * quantity;
        priceDisplay.setText("$" + price);
    }



}
