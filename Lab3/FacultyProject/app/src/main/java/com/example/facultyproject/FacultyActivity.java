package com.example.facultyproject;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.facultyproject.model.Faculty;

public class FacultyActivity extends AppCompatActivity {

    private EditText facultyIDUpdateEditText;
    private EditText facultyLNameUpdateEditText;
    private EditText facultyFNameUpdateEditText;
    private EditText facultySalaryUpdateEditText;
    private EditText facultyRateUpdateEditText;
    private TextView facultyBonusUpdateTextView;
    private Button facultyUpdateButton;
    private int facultyIDRetrieve;
    private String facultyLNameRetrieve;
    private String facultyFNameRetrieve;
    private double facultySalaryRetrieve;
    private double facultyRateRetrieve;
    private static String EXTRA_FACULTY_ID="com.example.facultyProject.f_id";
    private static String EXTRA_FACULTY_LNAME="com.example.facultyProject.f_lname";
    private static String EXTRA_FACULTY_FNAME="com.example.facultyProject.f_fname";
    private static String EXTRA_FACULTY_SALARY="com.example.facultyProject.f_salary";
    private static String EXTRA_FACULTY_RATE="com.example.facultyProject.f_bonus";

    public static Intent newIntent(Context packageContext, int facultyID,
                                                            String facultyLName,
                                                            String facultyFName,
                                                            double facultySalary,
                                                            double facultyRate) {
        Intent intent = new Intent(packageContext, FacultyActivity.class);

        //codes the data into EXTRA parameters
        intent.putExtra(EXTRA_FACULTY_ID, facultyID);
        intent.putExtra(EXTRA_FACULTY_LNAME, facultyLName);
        intent.putExtra(EXTRA_FACULTY_FNAME, facultyFName);
        intent.putExtra(EXTRA_FACULTY_SALARY, facultySalary);
        intent.putExtra(EXTRA_FACULTY_RATE, facultyRate);

        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_faculty);

        //placing data from EXTRA parameters into instance variables
        facultyIDRetrieve = getIntent().getIntExtra(EXTRA_FACULTY_ID, 0);
        facultyLNameRetrieve = getIntent().getStringExtra(EXTRA_FACULTY_LNAME);
        facultyFNameRetrieve = getIntent().getStringExtra(EXTRA_FACULTY_FNAME);
        facultySalaryRetrieve = getIntent().getDoubleExtra(EXTRA_FACULTY_SALARY, 0.0);
        facultyRateRetrieve = getIntent().getDoubleExtra(EXTRA_FACULTY_RATE, 0.0);

        //linking instance variables from Controller with their View counterpart
        facultyIDUpdateEditText = findViewById(R.id.f_id_update_edit_text);
        facultyLNameUpdateEditText = findViewById(R.id.f_Lname_update_edit_text);
        facultyFNameUpdateEditText = findViewById(R.id.f_Fname_update_edit_text);
        facultySalaryUpdateEditText = findViewById(R.id.f_salary_update_edit_text);
        facultyRateUpdateEditText = findViewById(R.id.f_rate_update_edit_text);
        facultyBonusUpdateTextView = findViewById(R.id.f_bonus_update_text_view);
        facultyUpdateButton = findViewById(R.id.faculty_update_button);

        facultyIDUpdateEditText.setText(facultyIDRetrieve+"");
        facultyLNameUpdateEditText.setText(facultyLNameRetrieve);
        facultyFNameUpdateEditText.setText(facultyFNameRetrieve);
        facultySalaryUpdateEditText.setText(facultySalaryRetrieve+"");
        facultyRateUpdateEditText.setText(facultyRateRetrieve+"");

        facultyUpdateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Faculty tempFaculty = new Faculty(Integer.parseInt(facultyIDUpdateEditText.getText().toString()),
                                                facultyLNameUpdateEditText.getText().toString(),
                                                facultyFNameUpdateEditText.getText().toString(),
                                                Double.parseDouble(facultySalaryUpdateEditText.getText().toString()),
                                                Double.parseDouble(facultyRateUpdateEditText.getText().toString()));

                setFacultyUpdateResult(Integer.parseInt(facultyIDUpdateEditText.getText().toString()),
                                        facultyLNameUpdateEditText.getText().toString(),
                                        facultyFNameUpdateEditText.getText().toString(),
                                        Double.parseDouble(facultySalaryUpdateEditText.getText().toString()),
                                        Double.parseDouble(facultyRateUpdateEditText.getText().toString()));

                facultyBonusUpdateTextView.setText("Your bonus is: "+String.format("%.2f", tempFaculty.calcBonus())+"$");
            }
        });
    }

    //coding
    private void setFacultyUpdateResult(int facultyID,
                                        String facultyLName,
                                        String facultyFName,
                                        double facultySalary,
                                        double facultyRate) {
        Intent dataIntent = new Intent();

        dataIntent.putExtra(EXTRA_FACULTY_ID, facultyID);
        dataIntent.putExtra(EXTRA_FACULTY_LNAME, facultyLName);
        dataIntent.putExtra(EXTRA_FACULTY_FNAME, facultyFName);
        dataIntent.putExtra(EXTRA_FACULTY_SALARY, facultySalary);
        dataIntent.putExtra(EXTRA_FACULTY_RATE, facultyRate);

        setResult(RESULT_OK, dataIntent);
    }

    public static Faculty decodedMessageFacultyUpdate(Intent resultIntent) {
        Faculty facultyUpdateInfo = new Faculty();

        facultyUpdateInfo.setF_id(resultIntent.getIntExtra(EXTRA_FACULTY_ID, 0));
        facultyUpdateInfo.setF_lname(resultIntent.getStringExtra(EXTRA_FACULTY_LNAME));
        facultyUpdateInfo.setF_fname(resultIntent.getStringExtra(EXTRA_FACULTY_FNAME));
        facultyUpdateInfo.setF_salary(resultIntent.getDoubleExtra(EXTRA_FACULTY_SALARY, 0.0));
        facultyUpdateInfo.setF_bonus(resultIntent.getDoubleExtra(EXTRA_FACULTY_RATE, 0.0));

        return facultyUpdateInfo;
    }
}