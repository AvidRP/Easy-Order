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

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String stringQuantity = quantityDisplay.getText().toString();
                int numberQuantity = Integer.parseInt(stringQuantity);
//                int newQuantity = numberQuantity + 1;
                quantityDisplay.setText(numberQuantity);
            }
        });

    }



}
