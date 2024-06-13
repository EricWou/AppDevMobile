package com.example.facultymobileproject;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.facultymobileproject.model.Faculty;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private TextView facultyIDTextView;
    private TextView facultyLNameTextView;
    private TextView facultyFNameTextView;
    private TextView facultySalaryTextView;
    private TextView facultyBonusTextView;
    private TextView facultyBonusTotalTextView;
    private Button prevFacultyButton;
    private Button nextFacultyButton;
    private Button calculateBonusButton;
    private int currentIndex = 0;
    public static String TAG = "Faculty Project";
    public static String KEY_INDEX = "index";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        Faculty f1 = new Faculty(101,"Robertson","Myra",60000.00, 2.50);
        Faculty f2 = new Faculty(212,"Smith","Neal",40000.00, 3.00);
        Faculty f3 = new Faculty(315,"Arlec","Lisa",55000.00, 1.50);
        Faculty f4 = new Faculty(857,"Fillipo","Paul",30000.00, 5.00);
        Faculty f5 = new Faculty(370,"Denkan","Anais",95000.00, 1.50);

        Map<Integer,Faculty> facultyHashMap = new HashMap<>();
        facultyHashMap.put(101,f1);
        facultyHashMap.put(212,f2);
        facultyHashMap.put(315,f3);
        facultyHashMap.put(857,f4);
        facultyHashMap.put(370,f5);

        if (savedInstanceState != null)
        {
            currentIndex = savedInstanceState.getInt(KEY_INDEX);
        }

        facultyIDTextView = findViewById(R.id.f_id_text_view);
        facultyLNameTextView = findViewById(R.id.f_Lname_text_view);
        facultyFNameTextView = findViewById(R.id.f_Fname_text_view);
        facultySalaryTextView = findViewById(R.id.f_salary_text_view);
        facultyBonusTextView = findViewById(R.id.f_bonus_text_view);
        facultyBonusTotalTextView = findViewById(R.id.f_bonus_total_text_view);

        for (Map.Entry<Integer, Faculty> entry: facultyHashMap.entrySet()) {
            String facultyID = "Faculty No: "+entry.getKey();
            String facultyLName = "Last Name: "+entry.getValue().getF_lname();
            String facultyFName = "First Name: "+entry.getValue().getF_fname();
            String facultySalary = "Salary: "+entry.getValue().getF_salary();
            String facultyBonus = "Bonus Rate: "+entry.getValue().getF_bonus();

            //if the program is opened for the first time, then get the first entry
            if (currentIndex == 0) {
                facultyIDTextView.setText(facultyID);
                facultyLNameTextView.setText(facultyLName);
                facultyFNameTextView.setText(facultyFName);
                facultySalaryTextView.setText(facultySalary);
                facultyBonusTextView.setText(facultyBonus);

                currentIndex = entry.getKey();
                break;
            }
            //else look for a match for saved HashMap key in currentIndex
            else if (currentIndex == entry.getKey()) {
                facultyIDTextView.setText(facultyID);
                facultyLNameTextView.setText(facultyLName);
                facultyFNameTextView.setText(facultyFName);
                facultySalaryTextView.setText(facultySalary);
                facultyBonusTextView.setText(facultyBonus);

                break;
            }
        }

        calculateBonusButton = findViewById(R.id.calculateBonus_button);
        calculateBonusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (Map.Entry<Integer, Faculty> entry: facultyHashMap.entrySet()) {
                    String facultyBonusTotal = "Faculty Bonus Amount: "+entry.getValue().calcBonus()+"$";

                    if (currentIndex == 0) {
                        facultyBonusTotalTextView.setText(facultyBonusTotal);
                        currentIndex = entry.getKey();

                        Toast.makeText(MainActivity.this,
                                "Faculty Bonus Amount: "+entry.getValue().calcBonus()+"$",
                                Toast.LENGTH_SHORT).show();

                        break;
                    }
                    else if (currentIndex == entry.getKey()) {
                        facultyBonusTotalTextView.setText(facultyBonusTotal);

                        Toast.makeText(MainActivity.this,
                                "Faculty Bonus Amount: "+entry.getValue().calcBonus()+"$",
                                Toast.LENGTH_SHORT).show();

                        break;
                    }
                }
            }
        });

        prevFacultyButton = findViewById(R.id.prevFaculty_button);
        prevFacultyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<Integer> facultyArray = new ArrayList<>();

                //adding facultyHashMap entrys to an ArrayList so we can iterate properly
                for (Map.Entry<Integer, Faculty> entry: facultyHashMap.entrySet()) {
                    facultyArray.add(entry.getKey());
                }
                System.out.println("Done making ArrayList");

                int facultyArrayIndex = 0;
                int totalIndex = facultyHashMap.size();

                //looking for which index in ArrayList the HashMap key in currentIndex is located
                for (int i=0;i<facultyArray.size();i++) {
                    if (facultyArray.get(i) == currentIndex) {
                        facultyArrayIndex = i;
                    }
                }

                //need to perform this longer operation to ensure it always returns a non-negative integer
                int prevIndex = (((facultyArrayIndex - 1)%totalIndex)+totalIndex)%totalIndex;

                for (Map.Entry<Integer, Faculty> entry: facultyHashMap.entrySet()) {
                    String facultyID = "Faculty No: "+entry.getKey();
                    String facultyLName = "Last Name: "+entry.getValue().getF_lname();
                    String facultyFName = "First Name: "+entry.getValue().getF_fname();
                    String facultySalary = "Salary: "+entry.getValue().getF_salary();
                    String facultyBonus = "Bonus Rate: "+entry.getValue().getF_bonus();

                    if (facultyArray.get(prevIndex) == entry.getKey()) {
                        facultyIDTextView.setText(facultyID);
                        facultyLNameTextView.setText(facultyLName);
                        facultyFNameTextView.setText(facultyFName);
                        facultySalaryTextView.setText(facultySalary);
                        facultyBonusTextView.setText(facultyBonus);

                        currentIndex = entry.getKey();
                        break;
                    }
                }
            }
        });

        nextFacultyButton = findViewById(R.id.nextFaculty_button);
        nextFacultyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<Integer> facultyArray = new ArrayList<>();

                //adding facultyHashMap entrys to an ArrayList so we can iterate properly
                for (Map.Entry<Integer, Faculty> entry: facultyHashMap.entrySet()) {
                    facultyArray.add(entry.getKey());
                }
                System.out.println("Done making ArrayList");

                int facultyArrayIndex = 0;
                int totalIndex = facultyHashMap.size();

                //looking for which index in ArrayList the HashMap key in currentIndex is located
                for (int i=0;i<facultyArray.size();i++) {
                    if (facultyArray.get(i) == currentIndex) {
                        facultyArrayIndex = i;
                    }
                }

                int nextIndex = (facultyArrayIndex + 1)%totalIndex;

                for (Map.Entry<Integer, Faculty> entry: facultyHashMap.entrySet()) {
                    String facultyID = "Faculty No: "+entry.getKey();
                    String facultyLName = "Last Name: "+entry.getValue().getF_lname();
                    String facultyFName = "First Name: "+entry.getValue().getF_fname();
                    String facultySalary = "Salary: "+entry.getValue().getF_salary();
                    String facultyBonus = "Bonus Rate: "+entry.getValue().getF_bonus();

                    if (facultyArray.get(nextIndex) == entry.getKey()) {
                        facultyIDTextView.setText(facultyID);
                        facultyLNameTextView.setText(facultyLName);
                        facultyFNameTextView.setText(facultyFName);
                        facultySalaryTextView.setText(facultySalary);
                        facultyBonusTextView.setText(facultyBonus);

                        currentIndex = entry.getKey();
                        break;
                    }
                }
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