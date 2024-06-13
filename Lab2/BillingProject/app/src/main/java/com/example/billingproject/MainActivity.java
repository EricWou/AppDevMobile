package com.example.billingproject;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.billingproject.model.Billing;

public class MainActivity extends AppCompatActivity {

    private EditText idEditText;
    private EditText clientNameEditText;
    private EditText productNameEditText;
    private EditText priceEditText;
    private EditText quantityEditText;
    private TextView billingInfoTextView;
    private Button totalInputButton;
    private Button totalRecordButton;
    private Button prevBillingButton;
    private Button nextBillingButton;
    private int currentIndex = 0;
    public static String TAG = "Billing Project";
    public static String KEY_INDEX = "index";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        if (savedInstanceState != null)
        {
            currentIndex = savedInstanceState.getInt(KEY_INDEX);
        }

        Billing b1 = new Billing(105,"Johnston Jane", "Chair", 99.99, 2);
        Billing b2 = new Billing(108,"Fikhali Samuel", "Table", 139.99, 1);
        Billing b3 = new Billing(113,"Samson Amina", "KeyUSB", 14.99, 2);

        Billing[] billingArray = new Billing[]{b1,b2,b3};

        billingInfoTextView = findViewById(R.id.billingInfo_text_view);

        String displayInfo = "Client: "+billingArray[currentIndex].getClient_ID()+", "+
                billingArray[currentIndex].getClient_Name()+", Product: "+
                billingArray[currentIndex].getProduct_Name()+" is "+
                String.format("%.2f",billingArray[currentIndex].calculateBilling())+" $";

        billingInfoTextView.setText(displayInfo);

        totalInputButton = findViewById(R.id.totalInput_button);
        totalInputButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                idEditText = findViewById(R.id.id_edit_text);
                clientNameEditText = findViewById(R.id.client_name_edit_text);
                productNameEditText = findViewById(R.id.product_name_edit_text);
                priceEditText = findViewById(R.id.price_edit_text);
                quantityEditText = findViewById(R.id.quantity_edit_text);

                int clientID = Integer.parseInt(idEditText.getText().toString());
                String clientName = clientNameEditText.getText().toString();
                String productName = productNameEditText.getText().toString();
                double price = Double.parseDouble(priceEditText.getText().toString());
                int quantity = Integer.parseInt(quantityEditText.getText().toString());

                Billing userBilling = new Billing(clientID, clientName, productName, price, quantity);

                billingInfoTextView = findViewById(R.id.billingInfo_text_view);

                String displayInfo = "Client: "+clientID+", "+
                                    clientName+", Product: "+
                                    productName+" is "+
                                    String.format("%.2f",userBilling.calculateBilling())+" $";

                billingInfoTextView.setText(displayInfo);

                Toast.makeText(MainActivity.this,
                        "Client: "+clientID+", "+
                                clientName+", Product: "+
                                productName+" is "+
                                String.format("%.2f",userBilling.calculateBilling())+" $",
                        Toast.LENGTH_SHORT).show();
            }
        });

        totalRecordButton = findViewById(R.id.totalRecord_button);
        totalRecordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                billingInfoTextView = findViewById(R.id.billingInfo_text_view);

                String displayInfo = "Client: "+billingArray[currentIndex].getClient_ID()+", "+
                                        billingArray[currentIndex].getClient_Name()+", Product: "+
                                        billingArray[currentIndex].getProduct_Name()+" is "+
                                        String.format("%.2f",billingArray[currentIndex].calculateBilling())+" $";

                billingInfoTextView.setText(displayInfo);

                Toast.makeText(MainActivity.this,
                        "Client: "+billingArray[currentIndex].getClient_ID()+", "+
                                billingArray[currentIndex].getClient_Name()+", Product: "+
                                billingArray[currentIndex].getProduct_Name()+" is "+
                                String.format("%.2f",billingArray[currentIndex].calculateBilling())+" $",
                        Toast.LENGTH_SHORT).show();
            }
        });

        prevBillingButton = findViewById(R.id.prevBilling_button);
        prevBillingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentIndex = (((currentIndex-1)%billingArray.length)+billingArray.length)%billingArray.length;

                billingInfoTextView = findViewById(R.id.billingInfo_text_view);

                String displayInfo = "Client: "+billingArray[currentIndex].getClient_ID()+", "+
                        billingArray[currentIndex].getClient_Name()+", Product: "+
                        billingArray[currentIndex].getProduct_Name()+" is "+
                        String.format("%.2f",billingArray[currentIndex].calculateBilling())+" $";

                billingInfoTextView.setText(displayInfo);
            }
        });

        nextBillingButton = findViewById(R.id.nextBilling_button);
        nextBillingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentIndex = (currentIndex+1)%billingArray.length;

                billingInfoTextView = findViewById(R.id.billingInfo_text_view);

                String displayInfo = "Client: "+billingArray[currentIndex].getClient_ID()+", "+
                        billingArray[currentIndex].getClient_Name()+", Product: "+
                        billingArray[currentIndex].getProduct_Name()+" is "+
                        String.format("%.2f",billingArray[currentIndex].calculateBilling())+" $";

                billingInfoTextView.setText(displayInfo);
            }
        });

    }

    @Override
    public void onStart() {

        super.onStart();

        Log.d(TAG, "onStart() is called");
    }

    @Override
    public void onPause() {

        super.onPause();

        Log.d(TAG, "onPause() is called");
    }

    @Override
    public void onResume() {

        super.onResume();

        Log.d(TAG, "onResume() is called");
    }

    @Override
    public void onStop() {

        super.onStop();

        Log.d(TAG, "onStop() is called");
    }

    @Override
    public void onDestroy() {

        super.onDestroy();

        Log.d(TAG, "onDestroy() is called");
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle onSavedInstanceState) {

        super.onSaveInstanceState(onSavedInstanceState);

        onSavedInstanceState.putInt(KEY_INDEX, currentIndex);
    }
}