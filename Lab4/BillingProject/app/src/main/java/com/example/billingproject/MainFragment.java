package com.example.billingproject;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.billingproject.database.BillingBaseHelper;
import com.example.billingproject.model.Billing;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;

public class MainFragment extends Fragment {

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
    private ArrayList<Billing> billingArray;
    private BillingBaseHelper baseHelper;
    private int currentIndex = 0;
    private int updateIndex = 0;
    public static String KEY_INDEX = "index";
    public static String KEY_UPDATE_INDEX = "update index";
    //public static String KEY_UPDATE_INFO = "updated info array";

    Billing b1 = new Billing(105,"Johnston Jane", "Chair", 99.99, 2);
    Billing b2 = new Billing(108,"Fikhali Samuel", "Table", 139.99, 1);
    Billing b3 = new Billing(113,"Samson Amina", "KeyUSB", 14.99, 2);

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d("MainFragment", "onCreate() is called");

        baseHelper = new BillingBaseHelper(this.getContext());

        //in case need to redo table
        //baseHelper.dropTable();

        /*
        //initiating database if database is empty
        if (baseHelper.readBillings().isEmpty()) {
            baseHelper.createBilling(b1);
            baseHelper.createBilling(b2);
            baseHelper.createBilling(b3);

            billingArray.add(b1);
            billingArray.add(b2);
            billingArray.add(b3);
        }
        //otherwise retrieve data from database
        else {
            //Log.d("ERIC", "reached else statement");
            billingArray.addAll(baseHelper.readBillings());
            //Log.d("ERIC1", billingArray.get(1).getClient_Name());
        }

         */

    }

    private String displayInfo(int index) {
        return "Client: "+billingArray.get(index).getClient_id()+", "+
                billingArray.get(index).getClient_name()+", Product: "+
                billingArray.get(index).getProduct_name()+" is "+
                String.format("%.2f",billingArray.get(index).calculateBilling())+" $";
    }

    private void findViews(View v) {
        idEditText = v.findViewById(R.id.id_edit_text);
        clientNameEditText = v.findViewById(R.id.client_name_edit_text);
        productNameEditText = v.findViewById(R.id.product_name_edit_text);
        priceEditText = v.findViewById(R.id.price_edit_text);
        quantityEditText = v.findViewById(R.id.quantity_edit_text);
        billingInfoTextView = v.findViewById(R.id.billingInfo_text_view);
        totalInputButton = v.findViewById(R.id.totalInput_button);
        totalRecordButton = v.findViewById(R.id.totalRecord_button);
        billingDetailsButton = v.findViewById(R.id.billingDetails_button);
        prevBillingButton = v.findViewById(R.id.prevBilling_button);
        nextBillingButton = v.findViewById(R.id.nextBilling_button);
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedIntanceState) {

        //changes view to fragment_main
        View v = inflater.inflate(R.layout.fragment_main, container, false);

        findViews(v);

        totalInputButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int clientID = Integer.parseInt(idEditText.getText().toString());
                String clientName = clientNameEditText.getText().toString();
                String productName = productNameEditText.getText().toString();
                double price = Double.parseDouble(priceEditText.getText().toString());
                int quantity = Integer.parseInt(quantityEditText.getText().toString());

                Billing newBilling = new Billing(clientID, clientName, productName, price, quantity);

                billingArray.add(newBilling);
                baseHelper.createBilling(newBilling);

                String displayInputInfo = "Client: "+clientID+", "+
                        clientName+", Product: "+
                        productName+" is "+
                        String.format("%.2f",newBilling.calculateBilling())+" $";

                billingInfoTextView.setText(displayInputInfo);

                Toast.makeText(getActivity(),
                        displayInputInfo,
                        Toast.LENGTH_SHORT).show();
            }
        });

        totalRecordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                billingInfoTextView.setText(displayInfo(currentIndex));

                Toast.makeText(getActivity(),
                        (displayInfo(currentIndex)),
                        Toast.LENGTH_SHORT).show();
            }
        });

        billingDetailsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int clientID = billingArray.get(currentIndex).getClient_id();
                String clientName = billingArray.get(currentIndex).getClient_name();
                String productName = billingArray.get(currentIndex).getProduct_name();
                double productPrice = billingArray.get(currentIndex).getPrd_price();
                int productQuantity = billingArray.get(currentIndex).getPrd_qty();

                updateIndex = currentIndex;

                Intent intent = BillingActivity.newIntent(getActivity(), clientID, clientName, productName, productPrice, productQuantity);

                startActivity(intent);
            }
        });

        prevBillingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentIndex = ((currentIndex-1)+billingArray.size())%billingArray.size();

                billingInfoTextView.setText(displayInfo(currentIndex));
            }
        });

        nextBillingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentIndex = (currentIndex+1)%billingArray.size();

                billingInfoTextView.setText(displayInfo(currentIndex));
            }
        });

        return v;
    }

    @Override
    public void onStart() {
        //could add the business logic inside of onCreate() into onStart() instead, since they
        //are all apart of the Life Cycle Activity
        super.onStart();

        baseHelper = new BillingBaseHelper(this.getContext());

        //this code is in onStart() so that it will run if returning to this activity
        //onCreate() will only be called the first time the app is created
        //initiating database if database is empty
        if (baseHelper.readBillings().isEmpty()) {
            baseHelper.createBilling(b1);
            baseHelper.createBilling(b2);
            baseHelper.createBilling(b3);

            billingArray = new ArrayList<>();

            billingArray.add(b1);
            billingArray.add(b2);
            billingArray.add(b3);
        }
        //otherwise retrieve data from database
        else {
            billingArray = new ArrayList<>();

            billingArray.addAll(baseHelper.readBillings());
        }

        billingInfoTextView.setText(displayInfo(updateIndex));

        //to see output in Logcat (for debugging purposes)
        Log.d("MainFragment", "onStart() is called");
    }

    @Override
    public void onPause() {

        super.onPause();

        Log.d("MainFragment", "onPause() is called");
    }

    @Override
    public void onResume() {

        super.onResume();

        Log.d("MainFragment", "onResume() is called");
    }

    @Override
    public void onStop() {

        super.onStop();

        Log.d("MainFragment", "onStop() is called");
    }

    @Override
    public void onDestroy() {

        super.onDestroy();

        Log.d("MainFragment", "onDestroy() is called");
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle onSavedInstanceState) {

        super.onSaveInstanceState(onSavedInstanceState);

        onSavedInstanceState.putInt(KEY_INDEX, currentIndex);
        onSavedInstanceState.putInt(KEY_UPDATE_INDEX, updateIndex);

        /*
        String clientIDUpdate = billingArray.get(updateIndex).getClient_id()+"";
        String clientNameUpdate = billingArray.get(updateIndex).getClient_name();
        String productNameUpdate = billingArray.get(updateIndex).getProduct_name();
        String priceUpdate = billingArray.get(updateIndex).getPrd_price()+"";
        String quantityUpdate = billingArray.get(updateIndex).getPrd_qty()+"";

        ArrayList<String> updateInfo = new ArrayList<>();
        updateInfo.add(clientIDUpdate);
        updateInfo.add(clientNameUpdate);
        updateInfo.add(productNameUpdate);
        updateInfo.add(priceUpdate);
        updateInfo.add(quantityUpdate);

        onSavedInstanceState.putStringArrayList(KEY_UPDATE_INFO, updateInfo);
         */
    }


}
