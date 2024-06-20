package com.example.employeeproject;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.employeeproject.model.Employee;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private TextView empIDTextView;
    private TextView empNameTextView;
    private TextView empSalaryTextView;
    private TextView empTotalTaxTextView;
    private Button empPrevButton;
    private Button empNextButton;
    private Button calculateTaxButton;
    private int currentIndex = 0;
    ArrayList<Employee> employeeArrayList;

    Employee emp1 = new Employee(342, "Jon Snow", 50000.0);
    Employee emp2 = new Employee(124, "Tyrion Lannister", 80000.0);
    Employee emp3 = new Employee(758, "Brynden Tully", 65000.0);



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        employeeArrayList = new ArrayList<>();
        employeeArrayList.add(emp1);
        employeeArrayList.add(emp2);
        employeeArrayList.add(emp3);

        findViews();

        setTextView();

        empPrevButton.setOnClickListener(v -> {
            currentIndex = ((currentIndex - 1)+employeeArrayList.size())%employeeArrayList.size();
            setTextView();
        });

        empNextButton.setOnClickListener(v -> {
            currentIndex = (currentIndex + 1)%employeeArrayList.size();
            setTextView();
        });

        calculateTaxButton.setOnClickListener(v -> {
            double totalTax = employeeArrayList.get(currentIndex).calculateTotalTax();
            String totalTaxText = "Employee Total Tax: "+totalTax;
            empTotalTaxTextView.setText(totalTaxText);

            Toast.makeText(MainActivity.this, totalTaxText, Toast.LENGTH_SHORT).show();
        });
    }

    private void findViews() {
        empIDTextView = findViewById(R.id.emp_id_text_view);
        empNameTextView = findViewById(R.id.emp_name_text_view);
        empSalaryTextView = findViewById(R.id.emp_salary_text_view);
        empPrevButton = findViewById(R.id.emp_prev_button);
        empNextButton = findViewById(R.id.emp_next_button);
        calculateTaxButton = findViewById(R.id.calculate_tax_button);
        empTotalTaxTextView = findViewById(R.id.emp_total_tax_text_view);
    }

    private void setTextView() {
        String empID = "Employee ID: "+employeeArrayList.get(currentIndex).getEmp_id();
        String empName = "Employee Name: "+employeeArrayList.get(currentIndex).getEmp_name();
        String empSalary = "Employee Salary: "+employeeArrayList.get(currentIndex).getEmp_salary();

        empIDTextView.setText(empID);
        empNameTextView.setText(empName);
        empSalaryTextView.setText(empSalary);
    }
}