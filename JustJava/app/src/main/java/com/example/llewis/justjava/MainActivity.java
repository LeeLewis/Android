package com.example.llewis.justjava;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends ActionBarActivity {

    int quantity = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        // Get user's name
        EditText nameField = (EditText) findViewById(R.id.name_field);
        String name = nameField.getText().toString();

        // Does user want whipped cream?
        CheckBox whippedCreamCheckbox = (CheckBox) findViewById(R.id.whipped_cream_checkbox);
        boolean hasWhippedCream = whippedCreamCheckbox.isChecked();

        // Does user want chocolate sprinkles?
        CheckBox chocolateCheckbox = (CheckBox) findViewById(R.id.chocolate_checkbox);
        boolean hasChocolate = chocolateCheckbox.isChecked();

        // Calculate the price
        int price = calculatePrice(hasWhippedCream, hasChocolate);
        String priceMessage = createOrderSummary(name, price, hasWhippedCream, hasChocolate);

         /*  make the new intent */
        Intent intent = new Intent(Intent.ACTION_SENDTO);

        intent.setData(Uri.parse("mailto:"));  // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, "JustJava order for " + name);
        intent.putExtra(Intent.EXTRA_TEXT, priceMessage);

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
        // displayMessage(priceMessage);
    }

    /**
     * Calculates the price of the order.
     *
     */
    private int calculatePrice(boolean addWhippedCream, boolean addChocolate) {

        int basePrice = 5;

        if (addWhippedCream) {
            basePrice += 1;
        }

        if (addChocolate) {
            basePrice += 2;
        }

        return quantity * basePrice;
    }

    /**
     * Create a summary message for the order
     *
     * @param priceOfOrder
     * @param addWhippedCream
     * @return text summary
     */
    private String createOrderSummary(String custName, int priceOfOrder, boolean addWhippedCream, boolean addChocolate) {

        String summaryMessage = "Name: " + custName;
        summaryMessage += "\nAdd whipped cream? " + addWhippedCream;
        summaryMessage += "\nAdd chocolate? " + addChocolate;
        summaryMessage += "\nQuantity: " + quantity;
        summaryMessage += "\nTotal: $" + priceOfOrder;
        summaryMessage += "\n" + getString(R.string.thank_you);
        return summaryMessage;
    }

    /**
     * This method is called when the + button is clicked.
     */
    public void increment(View view) {
        if (quantity < 100) {
            quantity = quantity + 1;
        } else {
            String toastText = "You cannot order more than 100 cups of coffee";
            Toast toast = Toast.makeText(this, toastText, Toast.LENGTH_LONG);
            toast.show();
        }
        displayQuantity(quantity);
    }

    /**
     * This method is called when the - button is clicked.
     */
    public void decrement(View view) {
        if (quantity > 1) {
            quantity = quantity - 1;
        } else {
            String toastText = "You must order at least 1 cup of coffee";
            Toast toast = Toast.makeText(this, toastText, Toast.LENGTH_LONG);
            toast.show();
        }
        displayQuantity(quantity);
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }

}