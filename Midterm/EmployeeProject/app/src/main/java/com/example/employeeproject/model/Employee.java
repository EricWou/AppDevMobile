package com.example.employeeproject.model;

public class Employee {

    private int emp_id;
    private String emp_name;
    private double emp_salary;
    public static double Prv_Tax=0.09;
    public static double Fed_Tax=0.07;

    public Employee() {
        this.emp_id = 0;
        this.emp_name = "";
        this.emp_salary = 0.0;
    }

    public Employee(int emp_id, String emp_name, double emp_salary) {
        this.emp_id = emp_id;
        this.emp_name = emp_name;
        this.emp_salary = emp_salary;
    }

    public int getEmp_id() {
        return emp_id;
    }

    public void setEmp_id(int emp_id) {
        this.emp_id = emp_id;
    }

    public String getEmp_name() {
        return emp_name;
    }

    public void setEmp_name(String emp_name) {
        this.emp_name = emp_name;
    }

    public double getEmp_salary() {
        return emp_salary;
    }

    public void setEmp_salary(double emp_salary) {
        this.emp_salary = emp_salary;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "emp_id=" + emp_id +
                ", emp_name='" + emp_name + '\'' +
                ", emp_salary=" + emp_salary +
                '}';
    }

    public double calculateTotalTax() {
        return emp_salary*Fed_Tax + emp_salary*Prv_Tax;
    }
}
