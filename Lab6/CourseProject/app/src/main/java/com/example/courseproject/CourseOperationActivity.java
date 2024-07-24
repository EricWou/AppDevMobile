package com.example.courseproject;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.courseproject.model.Course;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class CourseOperationActivity extends AppCompatActivity {

    private EditText operationCourseNoEditText;
    private EditText operationCourseNameEditText;
    private EditText operationMaxEnrlEditText;
    private EditText operationCourseCreditsEditText;
    private TextView operationCourseListTextView;
    private Button operationAddCourseButton;
    private Button operationSearchCourseButton;
    DatabaseReference databaseRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_course_operation);

        databaseRef = FirebaseDatabase.getInstance().getReference();

        operationCourseNoEditText = findViewById(R.id.operation_courseNo_edit_text);
        operationCourseNameEditText = findViewById(R.id.operation_courseName_edit_text);
        operationMaxEnrlEditText = findViewById(R.id.operation_maxEnrl_edit_text);
        operationCourseCreditsEditText = findViewById(R.id.operation_courseCredits_edit_text);
        operationCourseListTextView = findViewById(R.id.operation_course_list_text_view);
        operationAddCourseButton = findViewById(R.id.operation_add_course_button);
        operationSearchCourseButton = findViewById(R.id.operation_search_course_button);

        operationAddCourseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String courseNo = operationCourseNoEditText.getText().toString();
                String courseName = operationCourseNameEditText.getText().toString();
                int maxEnrl = Integer.parseInt(operationMaxEnrlEditText.getText().toString());
                int courseCredits = Integer.parseInt(operationCourseCreditsEditText.getText().toString());

                Course addCourse = new Course(courseNo, courseName, maxEnrl);

                databaseRef.child("ExtraCourses").child(addCourse.getCourse_no()).setValue(addCourse);
                Toast.makeText(CourseOperationActivity.this, "Course added to Firebase", Toast.LENGTH_SHORT).show();

                operationCourseNoEditText.setText("");
                operationCourseNameEditText.setText("");
                operationMaxEnrlEditText.setText("");
                operationCourseCreditsEditText.setText("");

                databaseRef.child("ExtraCourses").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String allCourses = "";

                        for(DataSnapshot ss:snapshot.getChildren())
                        {
                            allCourses += ss.getValue(Course.class).toString() +"\n";
                        }

                        operationCourseListTextView.setText(allCourses);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });

        operationSearchCourseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                databaseRef.child("ExtraCourses").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        String courseNo = operationCourseNoEditText.getText().toString();

                        //iterating through the entries in the database
                        for (DataSnapshot ss: snapshot.getChildren()) {
                            //looking to see if the courseNo matches one of the keys of the entries
                            if (ss.getKey().equals(courseNo)) {
                                //how to retrieve the values of the entries
                                String courseName = ss.getValue(Course.class).getCourse_name();
                                int maxEnrl = ss.getValue(Course.class).getMax_enrl();

                                operationCourseNameEditText.setText(courseName);
                                operationMaxEnrlEditText.setText(maxEnrl+"");
                                operationCourseCreditsEditText.setText(Course.credits+"");

                                operationCourseListTextView.setText(ss.getValue(Course.class).toString());

                                Toast.makeText(CourseOperationActivity.this, "Course Found in Firebase", Toast.LENGTH_SHORT).show();

                                return;
                            }
                            else {
                                operationCourseNameEditText.setText("Course Not Found");
                                operationMaxEnrlEditText.setText("");
                                operationCourseCreditsEditText.setText("");
                                operationCourseListTextView.setText("");

                                Toast.makeText(CourseOperationActivity.this, "Course Not Found in Firebase", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }
        });

    }
}