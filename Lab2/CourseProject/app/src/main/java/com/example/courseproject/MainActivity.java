package com.example.courseproject;

import static android.content.ContentValues.TAG;

import android.content.Intent;
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
import com.example.courseproject.model.Course;

public class MainActivity extends AppCompatActivity {

    private TextView courseTextView;
    private TextView courseTotalFeesTextView;
    private Button courseTotalFeesButton;
    private Button courseNextButton;
    private Button courseDetailButton;
    private int currentIndex = 0;
    public static String TAG = "Course Project";
    public static String KEY_INDEX = "index";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        // Need to remove this section in order to avoid NullPointerError when rotating to landscape
        /*
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
        Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
        v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
        return insets;
        });
        */

        Course.credits = 3;

        //in real-world should be read from database
        Course c1 = new Course("MIS 101", "Intro. to Info. Systems", 140);
        Course c2 = new Course("MIS 301", "Systems Analysis", 35);
        Course c3 = new Course("MIS 441", "Database Management", 12);
        Course c4 = new Course("CS 155", "Programming in C++", 90);
        Course c5 = new Course("MIS 451", "Web-Based Systems", 30);
        Course c6 = new Course("MIS 551", "Advanced Web", 30);
        Course c7 = new Course("MIS 651", "Advanced Java", 30);

        Course[] all_courses = new Course[]{c1, c2, c3, c4, c5, c6, c7};

        //Retrieve currentIndex from the
        if (savedInstanceState != null)
        {
            //retrieving the value inside Bundle class object and retrieving
            //the value through getInt() method using the KEY_INDEX argument
            currentIndex = savedInstanceState.getInt(KEY_INDEX);
        }

        //get the view of courseTextView
        courseTextView = findViewById(R.id.course_text_view);
        courseTextView.setText(("Course: "+all_courses[currentIndex].getCourse_no()
                +" "+all_courses[currentIndex].getCourse_name()));

        //get the view of the buttons
        courseTotalFeesButton = findViewById(R.id.courseTotalFees_button);
        courseTotalFeesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //get the view of courseTotalFees
                courseTotalFeesTextView = findViewById(R.id.courseTotalFees_text_view);
                courseTotalFeesTextView.setText("Total Course Fees is: "+all_courses[currentIndex].calculateCourseTotalFees());

                //use Toast class to show popup message
                Toast.makeText(MainActivity.this,
                        "Total Course Fees is: "+all_courses[currentIndex].calculateCourseTotalFees(),
                        Toast.LENGTH_SHORT).show();

            }
        });

        courseNextButton = findViewById(R.id.courseNext_button);
        courseNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //using incrementing and modulo in order to cycle through all elements of the array
                //and allow the index to go back to the start instead of going out of bounds
                currentIndex = (currentIndex + 1)%all_courses.length;

                courseTextView.setText(("Course: "+all_courses[currentIndex].getCourse_no()
                        +" "+all_courses[currentIndex].getCourse_name()));
            }
        });


    } //end of onCreate()

    @Override
    public void onStart() {
        //could add the business logic inside of onCreate() into onStart() instead, since they
        //are all apart of the Life Cycle Activity
        super.onStart();

        //to see output in Logcat (for debugging purposes)
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

        Log.d(TAG, "onDestroy() is called");

        //Will save the value of currentIndex into the object onSavedInstanceState inside
        //class Bundle and it will save the value inside of the key KEY_INDEX
        onSavedInstanceState.putInt(KEY_INDEX, currentIndex);
    }
}