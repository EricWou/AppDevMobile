package com.example.billingproject;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.billingproject.database.BillingBaseHelper;
import com.example.billingproject.model.Billing;

import org.jetbrains.annotations.Nullable;

public class BillingFragment extends Fragment {

    private EditText idUpdateEditText;
    private EditText clientNameUpdateEditText;
    private EditText productNameUpdateEditText;
    private EditText priceUpdateEditText;
    private EditText quantityUpdateEditText;
    private Button billingUpdateButton;
    private Button billingDeleteButton;
    private Button billingSearchButton;
    private Button billingViewButton;
    private BillingBaseHelper baseHelper;

    private void findViews(View v) {
        idUpdateEditText = v.findViewById(R.id.id_update_edit_text);
        clientNameUpdateEditText = v.findViewById(R.id.client_name_update_edit_text);
        productNameUpdateEditText = v.findViewById(R.id.product_name_update_edit_text);
        priceUpdateEditText = v.findViewById(R.id.price_update_edit_text);
        quantityUpdateEditText = v.findViewById(R.id.quantity_update_edit_text);

        billingUpdateButton = v.findViewById(R.id.billing_update_button);
        billingDeleteButton = v.findViewById(R.id.billing_delete_button);
        billingSearchButton = v.findViewById(R.id.billing_search_button);
        billingViewButton = v.findViewById(R.id.billing_view_button);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d("BillingFragment", "onCreate() is called");

        //baseHelper = new BillingBaseHelper(getContext().getApplicationContext());
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedIntanceState) {

        //baseHelper = new BillingBaseHelper(getContext().getApplicationContext());

        View v1 = inflater.inflate(R.layout.fragment_billing, container, false);

        findViews(v1);

        int client_id = requireArguments().getInt("client_id");
        String client_name = requireArguments().getString("client_name");
        String product_name = requireArguments().getString("product_name");
        double prd_price = requireArguments().getDouble("prd_price");
        int prd_qty = requireArguments().getInt("prd_qty");

        idUpdateEditText.setText(client_id+"");
        clientNameUpdateEditText.setText(client_name);
        productNameUpdateEditText.setText(product_name);
        priceUpdateEditText.setText(prd_price+"");
        quantityUpdateEditText.setText(prd_qty+"");

        billingUpdateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int client_id = Integer.parseInt(idUpdateEditText.getText().toString());
                String client_name = clientNameUpdateEditText.getText().toString();
                String product_name = productNameUpdateEditText.getText().toString();
                double prd_price = Double.parseDouble(priceUpdateEditText.getText().toString());
                int prd_qty = Integer.parseInt(quantityUpdateEditText.getText().toString());

                Log.d("client_id", client_id+"");
                Log.d("client_name", client_name);
                Log.d("product_name", product_name);
                Log.d("prd_price", prd_price+"");
                Log.d("prd_qty", prd_qty+"");

                Billing updatedBilling = new Billing(client_id,
                                                    client_name,
                                                    product_name,
                                                    prd_price,
                                                    prd_qty);

                baseHelper.updateBilling(updatedBilling);

                Log.d("updateBilling(1)", updatedBilling.toString());

                Toast.makeText(getActivity(),
                        "Client: "+client_id+" updated",
                        Toast.LENGTH_SHORT).show();
            }
        });

        billingDeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int client_id = Integer.parseInt(idUpdateEditText.getText().toString());

                baseHelper.deleteBilling(client_id);

                Toast.makeText(getActivity(),
                        "Client: "+client_id+" deleted",
                        Toast.LENGTH_SHORT).show();
            }
        });

        return v1;
    }

    @Override
    public void onStart() {
        //could add the business logic inside of onCreate() into onStart() instead, since they
        //are all apart of the Life Cycle Activity
        super.onStart();

        baseHelper = new BillingBaseHelper(getContext().getApplicationContext());

        //to see output in Logcat (for debugging purposes)
        Log.d("BillingFragment", "onStart() is called");
    }

    @Override
    public void onPause() {

        super.onPause();

        Log.d("BillingFragment", "onPause() is called");
    }

    @Override
    public void onResume() {

        super.onResume();

        Log.d("BillingFragment", "onResume() is called");
    }

    @Override
    public void onStop() {

        super.onStop();

        Log.d("BillingFragment", "onStop() is called");
    }

    @Override
    public void onDestroy() {

        super.onDestroy();

        Log.d("BillingFragment", "onDestroy() is called");
    }

}
