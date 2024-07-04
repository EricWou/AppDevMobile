package com.example.courseproject;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.example.courseproject.database.CourseBaseHelper;
import com.example.courseproject.model.Course;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;

public class CourseFragment extends Fragment {

    //All widgest will be managed by the fragment
    private TextView courseTextView;
    private TextView courseTotalFeesTextView;
    private TextView courseListTextView;
    private Button courseTotalFeesButton;
    private Button courseNextButton;
    private Button courseDetailButton;
    private Button courseStartServiceButton;
    private Button courseStopServiceButton;
    private Button courseLinkButton;
    private int currentIndex = 0;

    private Course[] all_courses;
    private Context context;
    private ArrayList<Course> courseModalArrayList;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //fragment will receive a callback from Fragment manager
        setHasOptionsMenu(true);

        Course.credits = 3;

        Course c1 = new Course("MIS 101", "Intro. to Info. Systems", 140);
        Course c2 = new Course("MIS 301", "Systems Analysis", 35);
        Course c3 = new Course("MIS 441", "Database Management", 12);
        Course c4 = new Course("CS 155", "Programming in C++", 90);
        Course c5 = new Course("MIS 451", "Web-Based Systems", 30);
        Course c6 = new Course("MIS 551", "Advanced Web", 30);
        Course c7 = new Course("MIS 651", "Advanced Java", 30);

        all_courses = new Course[]{c1, c2, c3, c4, c5, c6, c7};

        context = getContext().getApplicationContext();

        //Instantiate an object from CourseBaseHelper
        CourseBaseHelper courseBaseHelper = new CourseBaseHelper(context);
        courseBaseHelper.addNewCourse(c1);
        courseBaseHelper.addNewCourse(c2);
        courseBaseHelper.addNewCourse(c3);
        courseBaseHelper.addNewCourse(c4);
        courseBaseHelper.addNewCourse(c5);
        courseBaseHelper.addNewCourse(c6);
        courseBaseHelper.addNewCourse(c7);

        c3.setCourse_name("Android Database System");
        courseBaseHelper.updateCourse(c3);
    }


    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedIntanceState) {

        View v = inflater.inflate(R.layout.fragment_course, container, false);

        //get the view of course_toolbar
        //needs to be this: import androidx.appcompat.widget.Toolbar;
        Toolbar courseToolbar = v.findViewById(R.id.course_toolbar);

        //inflate course_menu
        courseToolbar.inflateMenu(R.menu.menu_course);
        courseToolbar.setTitle(R.string.app_name);
        courseToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.course_option_1) {
                    Toast.makeText(getActivity(), "Item 1", Toast.LENGTH_SHORT).show();
                }
                else if (item.getItemId() == R.id.course_option_2) {
                    Toast.makeText(getActivity(), "Item 2", Toast.LENGTH_SHORT).show();
                }
                else if (item.getItemId() == R.id.course_option_3) {
                    Toast.makeText(getActivity(), "Item 3", Toast.LENGTH_SHORT).show();
                }
                else if (item.getItemId() == R.id.course_option_5) {
                    Toast.makeText(getActivity(), "Item 4", Toast.LENGTH_SHORT).show();
                }
                else if (item.getItemId() == R.id.course_option_5) {
                    Toast.makeText(getActivity(), "Item 5", Toast.LENGTH_SHORT).show();
                }

                return false;
            }
        });

        /*
        //adds the toolbar to the activity (or fragment?)
        AppCompatActivity activity = (AppCompatActivity)getActivity();
        activity.setSupportActionBar(courseToolbar);
        */

        //to be able to see the first course
        courseTextView = (TextView) v.findViewById(R.id.course_text_view);
        courseTextView.setText("Course:" + all_courses[currentIndex].getCourse_no() +
                " " + all_courses[currentIndex].getCourse_name());

        courseTotalFeesTextView = (TextView) v.findViewById(R.id.course_total_fees_text_view);

        //get the view of the courseTotalFeesButton
        courseTotalFeesButton = (Button) v.findViewById(R.id.total_fees_button);
        courseTotalFeesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                courseTotalFeesTextView.setText("Total Course Fees is " +
                                all_courses[currentIndex].calculateTotalFees());

                //Use Toast class
                Toast.makeText(getActivity(), "Total Course Fees is " +
                                all_courses[currentIndex].calculateTotalFees(), Toast.LENGTH_SHORT).show();
            }
        });

        //get the view of the courseNextButton
        courseNextButton = (Button) v.findViewById(R.id.course_next_button);
        courseNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                currentIndex = (currentIndex + 1) % all_courses.length;

                courseTextView.setText("Course:" + all_courses[currentIndex].getCourse_no() +
                        " " + all_courses[currentIndex].getCourse_name());
            }
        });

        //get the view of the courseDetailButton
        courseDetailButton = (Button) v.findViewById(R.id.course_detail_button);
        courseDetailButton.setOnClickListener((new View.OnClickListener() {
            public void onClick(View view) {
                //here needs to be "view" instead of default "v" because there will be a clash
                //with the View v that we declared earlier

                //get view of the textview inside of the scrollview
                courseListTextView = v.findViewById(R.id.course_list_text_view);

                //read the table rows by calling method readCourses() from CourseBaseHelper
                courseModalArrayList = new CourseBaseHelper(context).readCourses();

                String allCourses = "";

                //read the content of courseModalArrayList and load into courseListTextView
                for (Course course:courseModalArrayList) {
                    allCourses += course.toString();
                }

                courseListTextView.setText(allCourses);
            }
        }));

        courseStartServiceButton = v.findViewById(R.id.course_start_service_button);
        courseStartServiceButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //will call the onStartCommand() method of CourseService
                getActivity().startService(new Intent(getActivity(), CourseService.class));
            }
        });

        courseStopServiceButton = v.findViewById(R.id.course_stop_service_button);
        courseStopServiceButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //will call the onDestroy() method of CourseService
                getActivity().stopService(new Intent(getActivity(), CourseService.class));
            }
        });

        courseLinkButton = v.findViewById(R.id.course_link_button);
        courseLinkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //uses implicit intent (OS will manage which activity will handle the intent)
                //versus explicit intent where you specify which activity (in previous examples user defined activity)
                //that the intent will switch to

                //start internal Browsing activity
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.vaniercollege.qc.ca"));
                startActivity(intent);
            }
        });

        return v;
    }

    /*
    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        //inflate course_menu
        inflater.inflate(R.menu.menu_course, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        //gets the id of the different menu options
        if (item.getItemId() == R.id.course_option_1) {
            Toast.makeText(getActivity(), "Item 1", Toast.LENGTH_SHORT).show();
        }
        else if (item.getItemId() == R.id.course_option_2) {
            Toast.makeText(getActivity(), "Item 2", Toast.LENGTH_SHORT).show();
        }
        else if (item.getItemId() == R.id.course_option_3) {
            Toast.makeText(getActivity(), "Item 3", Toast.LENGTH_SHORT).show();
        }
        else if (item.getItemId() == R.id.course_option_5) {
            Toast.makeText(getActivity(), "Item 4", Toast.LENGTH_SHORT).show();
        }
        else if (item.getItemId() == R.id.course_option_5) {
            Toast.makeText(getActivity(), "Item 5", Toast.LENGTH_SHORT).show();
        }

        //use implicit intent to call internal Activities, such as:
        //  browser
        //  mail
        //  phone call
        //  camera
        //  sensors

        return super.onOptionsItemSelected(item);
    }
    */


}
