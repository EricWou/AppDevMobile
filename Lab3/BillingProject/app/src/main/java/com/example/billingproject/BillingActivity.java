package com.example.billingproject;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.billingproject.model.Billing;

public class BillingActivity extends AppCompatActivity {

    private EditText idUpdateEditText;
    private EditText clientNameUpdateEditText;
    private EditText productNameUpdateEditText;
    private EditText priceUpdateEditText;
    private EditText quantityUpdateEditText;
    private Button billingUpdateButton;
    private int billingIDRetrieve;
    private String billingClientNameRetrieve;
    private String billingProductNameRetrieve;
    private double billingPriceRetrieve;
    private int billingQuantityRetrieve;
    private static String EXTRA_BILLING_ID="com.example.billingproject.client_ID";
    private static String EXTRA_BILLING_CLIENT_NAME="com.example.billingproject.client_Name";
    private static String EXTRA_BILLING_PRODUCT_NAME="com.example.billingproject.product_Name";
    private static String EXTRA_BILLING_PRICE="com.example.billingproject.prd_Price";
    private static String EXTRA_BILLING_QUANTITY="com.example.billingproject.prd_Qty";

    public static Intent newIntent(Context packageContext, int clientID,
                                                            String clientName,
                                                            String productName,
                                                            double productPrice,
                                                            int productQuantity) {
        Intent intent = new Intent(packageContext, BillingActivity.class);
        intent.putExtra(EXTRA_BILLING_ID, clientID);
        intent.putExtra(EXTRA_BILLING_CLIENT_NAME, clientName);
        intent.putExtra(EXTRA_BILLING_PRODUCT_NAME, productName);
        intent.putExtra(EXTRA_BILLING_PRICE, productPrice);
        intent.putExtra(EXTRA_BILLING_QUANTITY, productQuantity);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_billing);

        billingIDRetrieve = getIntent().getIntExtra(EXTRA_BILLING_ID, 0);
        billingClientNameRetrieve = getIntent().getStringExtra(EXTRA_BILLING_CLIENT_NAME);
        billingProductNameRetrieve = getIntent().getStringExtra(EXTRA_BILLING_PRODUCT_NAME);
        billingPriceRetrieve = getIntent().getDoubleExtra(EXTRA_BILLING_PRICE,0.0);
        billingQuantityRetrieve = getIntent().getIntExtra(EXTRA_BILLING_QUANTITY, 0);

        idUpdateEditText = findViewById(R.id.id_update_edit_text);
        idUpdateEditText.setText(billingIDRetrieve+"");

        clientNameUpdateEditText = findViewById(R.id.client_name_update_edit_text);
        clientNameUpdateEditText.setText(billingClientNameRetrieve);

        productNameUpdateEditText = findViewById(R.id.product_name_update_edit_text);
        productNameUpdateEditText.setText(billingProductNameRetrieve);

        priceUpdateEditText = findViewById(R.id.price_update_edit_text);
        priceUpdateEditText.setText(billingPriceRetrieve+"");

        quantityUpdateEditText = findViewById(R.id.quantity_update_edit_text);
        quantityUpdateEditText.setText(billingQuantityRetrieve+"");

        billingUpdateButton = findViewById(R.id.billing_update_button);
        billingUpdateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setBillingUpdateResult(Integer.parseInt(idUpdateEditText.getText().toString()),
                                        clientNameUpdateEditText.getText().toString(),
                                        productNameUpdateEditText.getText().toString(),
                                        Double.parseDouble(priceUpdateEditText.getText().toString()),
                                        Integer.parseInt(quantityUpdateEditText.getText().toString()));
            }
        });

    }

    private void setBillingUpdateResult(int clientID,
                                        String clientName,
                                        String productName,
                                        double productPrice,
                                        int productQuantity) {
        Intent dataIntent = new Intent();
        dataIntent.putExtra(EXTRA_BILLING_ID, clientID);
        dataIntent.putExtra(EXTRA_BILLING_CLIENT_NAME, clientName);
        dataIntent.putExtra(EXTRA_BILLING_PRODUCT_NAME, productName);
        dataIntent.putExtra(EXTRA_BILLING_PRICE, productPrice);
        dataIntent.putExtra(EXTRA_BILLING_QUANTITY, productQuantity);
        setResult(RESULT_OK, dataIntent);
    }

    public static Billing decodedMessageBillingUpdate(Intent resultIntent) {
        Billing billingUpdateInfo = new Billing();

        billingUpdateInfo.setClient_ID(resultIntent.getIntExtra(EXTRA_BILLING_ID, 0));
        billingUpdateInfo.setClient_Name(resultIntent.getStringExtra(EXTRA_BILLING_CLIENT_NAME));
        billingUpdateInfo.setProduct_Name(resultIntent.getStringExtra(EXTRA_BILLING_PRODUCT_NAME));
        billingUpdateInfo.setPrd_Price(resultIntent.getDoubleExtra(EXTRA_BILLING_PRICE,0.0));
        billingUpdateInfo.setPrd_Qty(resultIntent.getIntExtra(EXTRA_BILLING_QUANTITY, 0));

        return billingUpdateInfo;
    }
}