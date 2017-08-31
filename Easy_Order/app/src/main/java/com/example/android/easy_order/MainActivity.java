package com.example.android.easy_order;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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

        quantityDisplay.setText("" + 2);
        priceDisplay.setText("$" + 5);

        addButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                String stringQuantity = quantityDisplay.getText().toString();
                int quantity = Integer.parseInt(stringQuantity);

                orderSummaryDisplay.setText("price");

                if (quantity < 100) {
                    quantity++;

                    displayQuantity(quantity);
                } else {
                    Toast.makeText(MainActivity.this, "You cannot order more than 100 cups at a time", Toast.LENGTH_SHORT).show();
                }

            }
        });

        minusButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                orderSummaryDisplay.setText("price");

                String stringQuantity = quantityDisplay.getText().toString();
                int quantity = Integer.parseInt(stringQuantity);

                if(quantity > 1) {
                    quantity--;

                    displayQuantity(quantity);

                } else {
                    Toast.makeText(MainActivity.this, "You cannot order less than 1 cup", Toast.LENGTH_SHORT).show();
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

        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("message/rfc822");
        i.putExtra(Intent.EXTRA_EMAIL , new String[]{"coffeeshop@gmail.com"});
        i.putExtra(Intent.EXTRA_SUBJECT, "Coffee Order for " + customerName);
        i.putExtra(Intent.EXTRA_TEXT   , "Name: " + customerName + "\n" +
                "Quantity: " + quantityDisplay.getText().toString() + "\n" +
                "Whipped Cream: " + bwhippedCream + "\n" +
                "Chocolate: " + bchocolate + "\n" +
                "Total: $" + price + "\n" +
                "Thank you!");
        try {
            startActivity(Intent.createChooser(i, "Send mail..."));
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(MainActivity.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
        }

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
