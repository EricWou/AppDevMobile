package com.example.facultyproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.facultyproject.model.Faculty;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private TextView facultyIDTextView;
    private TextView facultyLNameTextView;
    private TextView facultyFNameTextView;
    private TextView facultySalaryTextView;
    private TextView facultyRateTextView;
    private TextView facultyBonusTextView;
    private Button prevFacultyButton;
    private Button nextFacultyButton;
    private Button facultyDetailsButton;
    private Button allFacultiesButton;
    ArrayList<Faculty> facultyArray = new ArrayList<>();;
    private int currentIndex = 0;
    private int updateIndex;
    public static String TAG = "Faculty Project";
    public static String KEY_INDEX = "index";
    public static String KEY_UPDATE_INDEX = "update index";
    public static String KEY_UPDATE_INFO = "update info array";

    Faculty f1 = new Faculty(101,"Robertson","Myra",60000.00, 2.50);
    Faculty f2 = new Faculty(212,"Smith","Neal",40000.00, 3.00);
    Faculty f3 = new Faculty(315,"Arlec","Lisa",55000.00, 1.50);
    Faculty f4 = new Faculty(857,"Fillipo","Paul",30000.00, 5.00);
    Faculty f5 = new Faculty(370,"Denkan","Anais",95000.00, 1.50);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        //Initializing "database"
        facultyArray.add(f1);
        facultyArray.add(f2);
        facultyArray.add(f3);
        facultyArray.add(f4);
        facultyArray.add(f5);

        //linking instance variables from Controller with their View counterpart
        facultyIDTextView = findViewById(R.id.f_id_text_view);
        facultyLNameTextView = findViewById(R.id.f_Lname_text_view);
        facultyFNameTextView = findViewById(R.id.f_Fname_text_view);
        facultySalaryTextView = findViewById(R.id.f_salary_text_view);
        facultyRateTextView = findViewById(R.id.f_rate_text_view);
        facultyBonusTextView = findViewById(R.id.f_bonus_text_view);
        prevFacultyButton = findViewById(R.id.prev_faculty_button);
        nextFacultyButton = findViewById(R.id.next_faculty_button);
        facultyDetailsButton = findViewById(R.id.faculty_details_button);
        allFacultiesButton = findViewById(R.id.all_faculties_button);

        //Retrieving currentIndex from previous instance
        if (savedInstanceState != null)
        {
            currentIndex = savedInstanceState.getInt(KEY_INDEX);
            updateIndex = savedInstanceState.getInt(KEY_UPDATE_INDEX);

            ArrayList<String> updateInfo = savedInstanceState.getStringArrayList(KEY_UPDATE_INFO);

            for (int i=0;i<updateInfo.size();i++) {
                facultyArray.get(currentIndex).setF_id(Integer.parseInt(updateInfo.get(i)));
                facultyArray.get(currentIndex).setF_lname(updateInfo.get(++i));
                facultyArray.get(currentIndex).setF_fname(updateInfo.get(++i));
                facultyArray.get(currentIndex).setF_salary(Double.parseDouble(updateInfo.get(++i)));
                facultyArray.get(currentIndex).setF_bonus(Double.parseDouble(updateInfo.get(++i)));
            }
        }

        //Setting strings for the text views for initial run
        setTextView(currentIndex);

        prevFacultyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //prevents the % from giving a negative value
                currentIndex = ((currentIndex - 1) + facultyArray.size())%facultyArray.size();

                setTextView(currentIndex);
            }
        });

        nextFacultyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentIndex = (currentIndex + 1)%facultyArray.size();

                setTextView(currentIndex);
            }
        });

        allFacultiesButton.setOnClickListener(new View.OnClickListener() {

             @Override
             public void onClick(View v) {

                 Intent intent = AllFacultiesActivity.newIntent(MainActivity.this, facultyArray);

                 startActivity(intent);
            }
        });

        facultyDetailsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int facultyID = facultyArray.get(currentIndex).getF_id();
                String facultyLName = facultyArray.get(currentIndex).getF_lname();
                String facultyFName = facultyArray.get(currentIndex).getF_fname();
                double facultySalary = facultyArray.get(currentIndex).getF_salary();
                double facultyRate = facultyArray.get(currentIndex).getF_bonus();

                updateIndex = currentIndex;

                //sends the data to be updated in FacultyActivity
                Intent intent = FacultyActivity.newIntent(MainActivity.this, facultyID,
                                                                                            facultyLName,
                                                                                            facultyFName,
                                                                                            facultySalary,
                                                                                            facultyRate);

                startActivityIntent.launch(intent);
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
                        //retrieves updated data from FacultyActivity
                        Faculty facultyUpdateInfo = FacultyActivity.decodedMessageFacultyUpdate(result.getData());
                        facultyArray.get(currentIndex).setF_id(facultyUpdateInfo.getF_id());
                        facultyArray.get(currentIndex).setF_lname(facultyUpdateInfo.getF_lname());
                        facultyArray.get(currentIndex).setF_fname(facultyUpdateInfo.getF_fname());
                        facultyArray.get(currentIndex).setF_salary(facultyUpdateInfo.getF_salary());
                        facultyArray.get(currentIndex).setF_bonus(facultyUpdateInfo.getF_bonus());

                        //sets the view to reflect automatically the update made in FacultyActivity
                        setTextView(currentIndex);
                    }
                }
            }
    );

    private void setTextView(int index){
        String facultyID = "Faculty No: "+facultyArray.get(index).getF_id();
        String facultyLName = "Faculty Last Name: "+facultyArray.get(index).getF_lname();
        String facultyFName = "Faculty First Name: "+facultyArray.get(index).getF_fname();
        String facultySalary = "Faculty Salary: "+facultyArray.get(index).getF_salary();
        String facultyRate = "Faculty Rate Bonus: "+facultyArray.get(index).getF_bonus();
        String facultyBonus = "Faculty Amount Bonus: "+String.format("%.2f", facultyArray.get(index).calcBonus())+"$";

        facultyIDTextView.setText(facultyID);
        facultyLNameTextView.setText(facultyLName);
        facultyFNameTextView.setText(facultyFName);
        facultySalaryTextView.setText(facultySalary);
        facultyRateTextView.setText(facultyRate);
        facultyBonusTextView.setText(facultyBonus);
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

        String facultyID = facultyArray.get(currentIndex).getF_id()+"";
        String facultyLName = facultyArray.get(currentIndex).getF_lname();
        String facultyFName = facultyArray.get(currentIndex).getF_fname();
        String facultySalary = facultyArray.get(currentIndex).getF_salary()+"";
        String facultyRate = facultyArray.get(currentIndex).getF_bonus()+"";

        ArrayList<String> updateInfo = new ArrayList<>();
        updateInfo.add(facultyID);
        updateInfo.add(facultyLName);
        updateInfo.add(facultyFName);
        updateInfo.add(facultySalary);
        updateInfo.add(facultyRate);

        onSavedInstanceState.putStringArrayList(KEY_UPDATE_INFO, updateInfo);
    }

}