package com.example.studentgradeproject;

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

import com.example.studentgradeproject.Model.Grade;

public class MainActivity extends AppCompatActivity {

    private TextView studentInfoTextView;
    private TextView gradeAverageTextView;
    private Button displayGradeButton;
    private Button prevStudentButton;
    private Button nextStudentButton;
    private int currentIndex = 0;
    public static String TAG = "Student Grade Project";
    public static String KEY_INDEX = "index";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        Grade s1 = new Grade(1, "Graham", "Bill", 69, 70, 98, 80, 90);
        Grade s2 = new Grade(2, "Sanchez", "Jim", 88, 72, 90, 83, 93);
        Grade s3 = new Grade(3, "White", "Peter", 85, 80, 45, 93, 70);
        Grade s4 = new Grade(4, "Phelp", "David", 70, 60, 60, 90, 70);
        Grade s5 = new Grade(5, "Lewis", "Sheila", 50, 76, 87, 59, 72);
        Grade s6 = new Grade(6, "James", "Thomas", 89, 99, 97, 98, 99);

        Grade[] studentArray = new Grade[]{s1, s2, s3, s4, s5, s6};

        if (savedInstanceState != null)
        {
           currentIndex = savedInstanceState.getInt(KEY_INDEX);
        }

        studentInfoTextView = findViewById(R.id.studentInfo_text_view);
        String studentText = "Student: "+studentArray[currentIndex].getStudent_lname() +
                                " "+studentArray[currentIndex].getStudent_fname();
        studentInfoTextView.setText(studentText);

        gradeAverageTextView = findViewById(R.id.gradeAverage_text_view);

        displayGradeButton = findViewById(R.id.displayGrade_button);
        displayGradeButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                double studentGrade = studentArray[currentIndex].calculate_GradeAverage();
                String gradeText = "Grade Average is: "+String.format("%.2f",studentGrade);

                gradeAverageTextView.setText(gradeText);

                Toast.makeText(MainActivity.this,
                        gradeText,
                        Toast.LENGTH_SHORT).show();
            }
        });

        prevStudentButton = findViewById(R.id.prevStudent_button);
        prevStudentButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (currentIndex == 0) {
                    currentIndex = studentArray.length - 1;
                }
                else {
                    currentIndex--;
                }
                String studentText = "Student: "+studentArray[currentIndex].getStudent_lname() +
                        " "+studentArray[currentIndex].getStudent_fname();
                studentInfoTextView.setText(studentText);
            }
        });

        nextStudentButton = findViewById(R.id.nextStudent_button);
        nextStudentButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (currentIndex == studentArray.length-1) {
                    currentIndex = 0;
                }
                else {
                    currentIndex++;
                }
                String studentText = "Student: "+studentArray[currentIndex].getStudent_lname() +
                        " "+studentArray[currentIndex].getStudent_fname();
                studentInfoTextView.setText(studentText);
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