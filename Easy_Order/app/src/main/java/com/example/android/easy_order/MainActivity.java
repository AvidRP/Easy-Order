package com.example.android.easy_order;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView quantityDisplay;
    TextView priceDisplay;
    TextView orderSummaryDisplay;

    Button addButton;
    Button minusButton;
    Button submitOrderButton;

    CheckBox whippedCream;
    CheckBox chocolate;

    EditText nameInput;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        priceDisplay = (TextView) findViewById(R.id.price_display);
        quantityDisplay = (TextView) findViewById(R.id.quantity_display);
        orderSummaryDisplay = (TextView) findViewById(R.id.price_or_order_summary);

        addButton = (Button) findViewById(R.id.plus_button);
        minusButton = (Button) findViewById(R.id.minus_button);
        submitOrderButton = (Button) findViewById(R.id.submit_button);

        whippedCream = (CheckBox) findViewById(R.id.whipped_cream);
        chocolate = (CheckBox) findViewById(R.id.chocolate);

        nameInput = (EditText) findViewById(R.id.name_edit_text);

        quantityDisplay.setText("" + 0);
        priceDisplay.setText("$" + 0);

        addButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                String stringQuantity = quantityDisplay.getText().toString();
                int quantity = Integer.parseInt(stringQuantity);

                orderSummaryDisplay.setText("price");

                quantity++;

                displayQuantity(quantity);

            }
        });

        minusButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                orderSummaryDisplay.setText("price");

                String stringQuantity = quantityDisplay.getText().toString();
                int quantity = Integer.parseInt(stringQuantity);

                if(quantity > 0) {
                    quantity--;

                    displayQuantity(quantity);

                }

            }
        });

        submitOrderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String stringQuantity = quantityDisplay.getText().toString();
                int quantity = Integer.parseInt(stringQuantity);

                double price = priceCalculator(quantity);
                submitOrder(price);

            }
        });


    }

    private double priceCalculator(int quantity) {
        double price = 2.5 * quantity;

        if(whippedCream.isChecked()) {
            price = price + quantity * 0.5;
            priceDisplay.setText("$" + price);
        } else if (chocolate.isChecked()) {
            price = price + quantity * 0.5;
            priceDisplay.setText("$" + price);
        } else if (chocolate.isChecked() && whippedCream.isChecked()) {
            price = price + quantity * 0.5;
            priceDisplay.setText("$" + price);
        }
        else {
            priceDisplay.setText("$" + price);
        }


        return price;
    }

    private void displayQuantity (int quantity) {
        quantityDisplay.setText("" + quantity);

        priceCalculator(quantity);
    }

    private void submitOrder (double price) {

        orderSummaryDisplay.setText("Order summary");

        boolean bwhippedCream = checkWhippedCream();
        boolean bchocolate = checkChocolate();

        String customerName = getName();

        priceDisplay.setText("Name: " + customerName + "\n" +
                "Quantity: " + quantityDisplay.getText().toString() + "\n" +
                "Whipped Cream: " + bwhippedCream + "\n" +
                "Chocolate: " + bchocolate + "\n" +
                "Total: $" + price + "\n" +
                "Thank you!");

    }

    private boolean checkWhippedCream () {

        if(whippedCream.isChecked()) {
            return true;
        } else {
            return false;
        }
    }

    private boolean checkChocolate () {
        if(chocolate.isChecked()) {
            return true;
        } else {
            return false;
        }
    }

    private String getName() {
        return nameInput.getText().toString();
    }



}
