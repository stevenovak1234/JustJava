package com.example.android.justjava;



import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;


/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    int quantity = 0;



    @Override


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }





    /**
     * This method is called when the increment button is clicked.
     */
    public void increment(View view) {

            quantity = quantity + 1;
        if (quantity <= 10) {
            quantity = quantity;
        } else {quantity = 10;}
        myQuantityDisplay(quantity);
        if (quantity == 10) {Toast.makeText(this, "Max Order", Toast.LENGTH_SHORT).show();}
        }


        /**
     * This method is called when the decrement button is clicked.
     */
    public void decrement(View view) {

            quantity = quantity - 1;
        if (quantity >= 0) {
            quantity = quantity;
        } else {quantity = 0;}
            myQuantityDisplay(quantity);
        if (quantity == 1) {Toast.makeText(this, "Min Order", Toast.LENGTH_SHORT).show();}
    }
    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {

        CheckBox whippedCreamCheckbox = (CheckBox) findViewById(R.id.whipped_cream_checkbox);
        boolean hasWhippedCream = whippedCreamCheckbox.isChecked();
        Log.v("MainActivity", "Has whipped cream: " + hasWhippedCream);
        TextView myTrueFalseWC = (TextView) findViewById(R.id.true_false);
        myTrueFalseWC.setText("" + hasWhippedCream);
        int whippedCreamIsServed = Boolean.valueOf(hasWhippedCream).compareTo(false);
        Log.v("MainActivity", "Whipped Cream: " + whippedCreamIsServed);

        CheckBox chocolateCheckbox = (CheckBox) findViewById(R.id.chocolate_checkbox);
        boolean hasChocolate = chocolateCheckbox.isChecked();
        Log.v("MainActivity", "Has chocolate: " + hasChocolate);
        TextView myTrueFalseChoc = (TextView) findViewById(R.id.true_false_choc);
        myTrueFalseChoc.setText("" + hasChocolate);
        int chocIsServed = Boolean.valueOf(hasChocolate).compareTo(false);
        Log.v("MainActivity", "Chocolate: " + chocIsServed);

        int myPrice = (quantity * 5 + (chocIsServed * quantity *2) + (whippedCreamIsServed * quantity *1) );

        TextView myQuantity = (TextView) findViewById(R.id.lower_quantity);
        myQuantity.setText("" + quantity);

        TextView myPriceTotal = (TextView) findViewById(R.id.price_text_view);
        myPriceTotal.setText("$" + myPrice);

        TextView myThankyou = (TextView) findViewById(R.id.thankyou);
        myThankyou.setText("Thank you!");

        EditText myNameEnter = (EditText) findViewById(R.id.editText);
        TextView myNameDisplay = (TextView) findViewById(R.id.name);
        myNameDisplay.setText("" + myNameEnter.getText().toString());

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/html");
        intent.putExtra(Intent.EXTRA_EMAIL, "spec-auto@shaw.ca");
        intent.putExtra(Intent.EXTRA_SUBJECT, "Coffee Order Summary");
        intent.putExtra(Intent.EXTRA_TEXT, "Hello! " + myNameEnter.getText().toString() + "\nEnjoy your coffee!" + "\nAdd whipped cream: "
        + hasWhippedCream + "\nAdd Chocolate: " + hasChocolate + "\nQuantity: " + quantity + "\nTotal: $" + myPrice + "\nThankyou!");

        startActivity(Intent.createChooser(intent, "Send Email"));

    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void myQuantityDisplay(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    public void showMap (View view){
        Uri gmmIntentUri = Uri.parse("geo:49.874426, -119.352773");
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        if (mapIntent.resolveActivity(getPackageManager()) != null)
            startActivity(mapIntent);
    }


    }












