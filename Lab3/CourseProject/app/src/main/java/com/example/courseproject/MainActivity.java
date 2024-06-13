package com.example.courseproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
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
    private Course[] all_courses;
    private int currentIndex =  0;
    public static String TAG = "Course Project";
    public static String KEY_INDEX = "index";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        Course.credits = 3;

        //in real-world should be read from database
        Course c1 = new Course("MIS 101", "Intro. to Info. Systems", 140);
        Course c2 = new Course("MIS 301", "Systems Analysis", 35);
        Course c3 = new Course("MIS 441", "Database Management", 12);
        Course c4 = new Course("CS 155", "Programming in C++", 90);
        Course c5 = new Course("MIS 451", "Web-Based Systems", 30);
        Course c6 = new Course("MIS 551", "Advanced Web", 30);
        Course c7 = new Course("MIS 651", "Advanced Java", 30);

        all_courses = new Course[]{c1, c2, c3, c4, c5, c6, c7};

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

        courseTotalFeesTextView = findViewById(R.id.courseTotalFees_text_view);

        //get the view of the buttons
        courseTotalFeesButton = findViewById(R.id.courseTotalFees_button);
        courseTotalFeesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //get the view of courseTotalFees
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

                courseTextView.setText("Course: "+all_courses[currentIndex].getCourse_no()
                        +" "+all_courses[currentIndex].getCourse_name());
            }
        });

        courseDetailButton = findViewById(R.id.courseDetail_button);
        courseDetailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //start the CourseActivity (Controller)
                //specify the data to be sent as key-value pairs (from the parent activity to the child activity)
                String courseID = all_courses[currentIndex].getCourse_no();
                String courseName = all_courses[currentIndex].getCourse_name();
                int courseMaxEnrl = all_courses[currentIndex].getMax_enrl();
                int courseCredits = Course.credits;

                //call method newIntent() for sending over parameters
                //Intent specifies the child activity (CourseActivity) and the parent activity (MainActivity.this)
                //in other words, the beginning and endpoint of the intention (changing views)
                Intent intent = CourseActivity.newIntent(MainActivity.this, courseID, courseName, courseMaxEnrl, courseCredits);

                //used to start the child activity (ie CourseActivity)
                //the method startActivity() is used when you expect no result data from child activity
                //startActivity(intent);

                //create an object when sending data from parent MainActivity
                //the method startActivityIntent() is used when expecting result data from child activity
                startActivityIntent.launch(intent);
            }
        });

    } //end of onCreate()

    ActivityResultLauncher<Intent> startActivityIntent = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    //Decoding result data from child activity (ie CourseActivity)

                    //if there is no
                    if (result.getResultCode() != Activity.RESULT_OK) {
                        return;
                    }
                    else {
                        //calling decoded EXTRA parameters
                        //getData() in this case returns an object of intent
                        //because decodedMessageCourseUpdate is a static method, need to call it with the class name
                        Course courseUpdateInfo = CourseActivity.decodedMessageCourseUpdate(result.getData());
                        all_courses[currentIndex].setCourse_no(courseUpdateInfo.getCourse_no());
                        all_courses[currentIndex].setCourse_name(courseUpdateInfo.getCourse_name());
                        all_courses[currentIndex].setMax_enrl(courseUpdateInfo.getMax_enrl());
                        all_courses[currentIndex].credits = courseUpdateInfo.credits;

                        courseTextView.setText("Course: "+all_courses[currentIndex].getCourse_no()
                                +" "+all_courses[currentIndex].getCourse_name());
                        courseTotalFeesTextView.setText("Total Course Fees is: "+all_courses[currentIndex].calculateCourseTotalFees());

                        Toast.makeText(MainActivity.this,
                                all_courses[currentIndex].getCourse_no()+" "+
                                all_courses[currentIndex].getCourse_name(),
                                Toast.LENGTH_SHORT).show();
                    }
                }
            }
    );

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
