package com.example.billingproject;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.billingproject.database.BillingBaseHelper;
import com.example.billingproject.model.Billing;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;

public class BillingViewFragment extends Fragment {

    private TextView billingListTextView;
    private ArrayList<Billing> billingArray;
    private BillingBaseHelper baseHelper;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d("BillingViewFragment", "onCreate() is called");
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedIntanceState) {

        View v1 = inflater.inflate(R.layout.fragment_billing_view, container, false);

        billingListTextView = v1.findViewById(R.id.billing_list_text_view);

        billingArray = new ArrayList<>();

        //onCreateView() is called before onStart()
        baseHelper = new BillingBaseHelper(getContext().getApplicationContext());

        billingArray.addAll(baseHelper.readBillings());

        billingListTextView.setText(billingArray.toString());

        return v1;

    }

    public void onStart() {
        //could add the business logic inside of onCreate() into onStart() instead, since they
        //are all apart of the Life Cycle Activity
        super.onStart();

        //Here onCreateView() is called first and needs baseHelper before onStart() is called
        //baseHelper = new BillingBaseHelper(getContext().getApplicationContext());

        //to see output in Logcat (for debugging purposes)
        Log.d("BillingViewFragment", "onStart() is called");
    }

    @Override
    public void onPause() {

        super.onPause();

        Log.d("BillingViewFragment", "onPause() is called");
    }

    @Override
    public void onResume() {

        super.onResume();

        Log.d("BillingViewFragment", "onResume() is called");
    }

    @Override
    public void onStop() {

        super.onStop();

        Log.d("BillingViewFragment", "onStop() is called");
    }

    @Override
    public void onDestroy() {

        super.onDestroy();

        Log.d("BillingViewFragment", "onDestroy() is called");
    }
}
