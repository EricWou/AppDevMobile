package com.example.billingproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.billingproject.model.Billing;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText idEditText;
    private EditText clientNameEditText;
    private EditText productNameEditText;
    private EditText priceEditText;
    private EditText quantityEditText;
    private TextView billingInfoTextView;
    private Button totalInputButton;
    private Button totalRecordButton;
    private Button billingDetailsButton;
    private Button prevBillingButton;
    private Button nextBillingButton;
    private ArrayList<Billing> billingArray = new ArrayList<>();
    private int currentIndex = 0;
    private int updateIndex;
    public static String TAG = "Billing Project";
    public static String KEY_INDEX = "index";
    public static String KEY_UPDATE_INDEX = "update index";
    public static String KEY_UPDATE_INFO = "updated info array";

    Billing b1 = new Billing(105,"Johnston Jane", "Chair", 99.99, 2);
    Billing b2 = new Billing(108,"Fikhali Samuel", "Table", 139.99, 1);
    Billing b3 = new Billing(113,"Samson Amina", "KeyUSB", 14.99, 2);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        billingArray.add(b1);
        billingArray.add(b2);
        billingArray.add(b3);

        if (savedInstanceState != null)
        {
            currentIndex = savedInstanceState.getInt(KEY_INDEX);
            updateIndex = savedInstanceState.getInt(KEY_UPDATE_INDEX);

            ArrayList<String> updateInfo = savedInstanceState.getStringArrayList(KEY_UPDATE_INFO);

            for (int i=0;i<updateInfo.size();i++) {
                billingArray.get(updateIndex).setClient_ID(Integer.parseInt(updateInfo.get(i)));
                billingArray.get(updateIndex).setClient_Name(updateInfo.get(++i));
                billingArray.get(updateIndex).setProduct_Name(updateInfo.get(++i));
                billingArray.get(updateIndex).setPrd_Price(Double.parseDouble(updateInfo.get(++i)));
                billingArray.get(updateIndex).setPrd_Qty(Integer.parseInt(updateInfo.get(++i)));
            }
        }

        billingInfoTextView = findViewById(R.id.billingInfo_text_view);

        billingInfoTextView.setText(displayInfo(currentIndex));

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

                billingArray.add(userBilling);

                billingInfoTextView = findViewById(R.id.billingInfo_text_view);

                String displayInputInfo = "Client: "+clientID+", "+
                        clientName+", Product: "+
                        productName+" is "+
                        String.format("%.2f",userBilling.calculateBilling())+" $";

                billingInfoTextView.setText(displayInputInfo);

                Toast.makeText(MainActivity.this, displayInputInfo,
                        Toast.LENGTH_SHORT).show();
            }
        });

        totalRecordButton = findViewById(R.id.totalRecord_button);
        totalRecordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                billingInfoTextView.setText(displayInfo(currentIndex));

                Toast.makeText(MainActivity.this, (displayInfo(currentIndex)),
                        Toast.LENGTH_SHORT).show();
            }
        });

        billingDetailsButton = findViewById(R.id.billingDetails_button);
        billingDetailsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int clientID = billingArray.get(currentIndex).getClient_ID();
                String clientName = billingArray.get(currentIndex).getClient_Name();
                String productName = billingArray.get(currentIndex).getProduct_Name();
                double productPrice = billingArray.get(currentIndex).getPrd_Price();
                int productQuantity = billingArray.get(currentIndex).getPrd_Qty();

                Intent intent = BillingActivity.newIntent(MainActivity.this, clientID, clientName, productName, productPrice, productQuantity);

                startActivityIntent.launch(intent);
            }
        });

        prevBillingButton = findViewById(R.id.prevBilling_button);
        prevBillingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentIndex = ((currentIndex-1)+billingArray.size())%billingArray.size();

                billingInfoTextView = findViewById(R.id.billingInfo_text_view);

                billingInfoTextView.setText(displayInfo(currentIndex));
            }
        });

        nextBillingButton = findViewById(R.id.nextBilling_button);
        nextBillingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentIndex = (currentIndex+1)%billingArray.size();

                billingInfoTextView = findViewById(R.id.billingInfo_text_view);

                billingInfoTextView.setText(displayInfo(currentIndex));
            }
        });

    }

    ActivityResultLauncher<Intent> startActivityIntent = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {

                    if (result.getResultCode() != Activity.RESULT_OK) {
                        return;
                    }
                    else {
                        Billing billingUpdateInfo = BillingActivity.decodedMessageBillingUpdate(result.getData());
                        billingArray.get(currentIndex).setClient_ID(billingUpdateInfo.getClient_ID());
                        billingArray.get(currentIndex).setClient_Name(billingUpdateInfo.getClient_Name());
                        billingArray.get(currentIndex).setProduct_Name(billingUpdateInfo.getProduct_Name());
                        billingArray.get(currentIndex).setPrd_Price(billingUpdateInfo.getPrd_Price());
                        billingArray.get(currentIndex).setPrd_Qty(billingUpdateInfo.getPrd_Qty());

                        updateIndex = currentIndex;

                        billingInfoTextView.setText(displayInfo(currentIndex));

                        Toast.makeText(MainActivity.this, displayInfo(currentIndex),
                                Toast.LENGTH_SHORT).show();
                    }

                }
            }
    );

    private String displayInfo(int index) {
        return "Client: "+billingArray.get(index).getClient_ID()+", "+
                billingArray.get(index).getClient_Name()+", Product: "+
                billingArray.get(index).getProduct_Name()+" is "+
                String.format("%.2f",billingArray.get(index).calculateBilling())+" $";
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
        onSavedInstanceState.putInt(KEY_UPDATE_INDEX, updateIndex);

        String clientIDUpdate = billingArray.get(updateIndex).getClient_ID()+"";
        String clientNameUpdate = billingArray.get(updateIndex).getClient_Name();
        String productNameUpdate = billingArray.get(updateIndex).getProduct_Name();
        String priceUpdate = billingArray.get(updateIndex).getPrd_Price()+"";
        String quantityUpdate = billingArray.get(updateIndex).getPrd_Qty()+"";

        ArrayList<String> updateInfo = new ArrayList<>();
        updateInfo.add(clientIDUpdate);
        updateInfo.add(clientNameUpdate);
        updateInfo.add(productNameUpdate);
        updateInfo.add(priceUpdate);
        updateInfo.add(quantityUpdate);

        onSavedInstanceState.putStringArrayList(KEY_UPDATE_INFO, updateInfo);
    }
}