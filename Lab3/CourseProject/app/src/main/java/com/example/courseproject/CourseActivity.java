package com.example.courseproject;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.courseproject.model.Course;

public class CourseActivity extends AppCompatActivity {

    //right click on the parent folder for the package -> new -> activity -> Empty Views Activity
    private EditText courseIDEditText;
    private EditText courseNameEditText;
    private EditText courseMaxEnrlEditText;
    private EditText courseCreditsEditText;
    private Button courseUpdateButton;
    private String courseIdRetrieve;
    private String courseNameRetrieve;
    private int courseMaxEnrlRetrieve;
    private int courseCreditsRetrieve;
    private static String EXTRA_COURSE_NO="com.example.courseproject.course_no";
    private static String EXTRA_COURSE_NAME="com.example.courseproject.course_name";
    private static String EXTRA_COURSE_MAX_ENRL="com.example.courseproject.max_enrl";
    private static String EXTRA_COURSE_CREDITS="com.example.courseproject.course_credits";

    //coding EXTRA parameters from parent MainActivity to child CourseActivity
    public static Intent newIntent(Context packageContext, String course_id, String course_name, int max_enrl, int course_credits)
    {
        //specifying key-value pairs for the Intent object (using EXTRA parameters)
        //in a way it's like creating instance variables but without having to create a class beforehand
        Intent intent = new Intent(packageContext, CourseActivity.class);
        intent.putExtra(EXTRA_COURSE_NO,course_id);
        intent.putExtra(EXTRA_COURSE_NAME,course_name);
        intent.putExtra(EXTRA_COURSE_MAX_ENRL,max_enrl);
        intent.putExtra(EXTRA_COURSE_CREDITS,course_credits);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_course);

        //Decoding EXTRA parameters (Retrieve coded parameters from the MainActivity)
        courseIdRetrieve = getIntent().getStringExtra(EXTRA_COURSE_NO);
        courseNameRetrieve = getIntent().getStringExtra(EXTRA_COURSE_NAME);
        //for getIntExtra(), the second argument is to denote the default value if the first argument has no value
        //not necessary for getStringExtra() because the automatic default value would be an empty string
        courseMaxEnrlRetrieve = getIntent().getIntExtra(EXTRA_COURSE_MAX_ENRL, 0);
        courseCreditsRetrieve = getIntent().getIntExtra(EXTRA_COURSE_CREDITS, 0);

        //get the view of courseIdEditText
        courseIDEditText = (EditText) findViewById(R.id.courseNo_edit_text);
        courseIDEditText.setText(courseIdRetrieve);

        //get the view of courseNameEditText
        courseNameEditText = (EditText) findViewById(R.id.courseName_edit_text);
        courseNameEditText.setText(courseNameRetrieve);

        //get the view of courseMaxEnrlEditText
        courseMaxEnrlEditText = (EditText) findViewById(R.id.courseMaxEnrl_edit_text);
        courseMaxEnrlEditText.setText(courseMaxEnrlRetrieve + ""); //Add "" to convert to string

        //get the view of courseCreditsEditText
        courseCreditsEditText = (EditText) findViewById(R.id.courseCredits_edit_text);
        courseCreditsEditText.setText(courseCreditsRetrieve + ""); //Add "" to convert to string

        courseUpdateButton = findViewById(R.id.courseUpdate_button);
        courseUpdateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //coding parameters to update course info to send to parent activity
                setCourseUpdateResult(courseIDEditText.getText().toString(),
                                        courseNameEditText.getText().toString(),
                                        Integer.parseInt(courseMaxEnrlEditText.getText().toString()),
                                        Integer.parseInt(courseCreditsEditText.getText().toString()));
            }
        });

    }//end of onCreate()

    //coding EXTRA parameters from child CourseActivity to parent MainActivity
    private void setCourseUpdateResult(String course_id, String course_name, int max_enrl, int course_credits)
    {
        //updating EXTRA parameters with the new user input
        Intent dataIntent = new Intent();
        dataIntent.putExtra(EXTRA_COURSE_NO,course_id);
        dataIntent.putExtra(EXTRA_COURSE_NAME,course_name);
        dataIntent.putExtra(EXTRA_COURSE_MAX_ENRL,max_enrl);
        dataIntent.putExtra(EXTRA_COURSE_CREDITS,course_credits);
        setResult(RESULT_OK, dataIntent);
    }

    //decoding EXTRA parameters in parent MainActivity
    public static Course decodedMessageCourseUpdate(Intent resultIntent)
    {
        Course courseUpdateInfo = new Course();

        courseUpdateInfo.setCourse_no(resultIntent.getStringExtra(EXTRA_COURSE_NO));
        courseUpdateInfo.setCourse_name(resultIntent.getStringExtra(EXTRA_COURSE_NAME));
        courseUpdateInfo.setMax_enrl(resultIntent.getIntExtra(EXTRA_COURSE_MAX_ENRL,0));
        courseUpdateInfo.credits = resultIntent.getIntExtra(EXTRA_COURSE_CREDITS, 0);

        return courseUpdateInfo;
    }

}

