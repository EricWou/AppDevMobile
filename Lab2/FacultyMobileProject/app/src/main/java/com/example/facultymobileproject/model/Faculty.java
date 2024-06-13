package com.example.facultymobileproject.model;

public class Faculty {

    private int f_id;
    private String f_lname;
    private String f_fname;
    private double f_salary;
    private double f_bonus;

    public Faculty() {
        this.f_id = 0;
        this.f_lname = "";
        this.f_fname = "";
        this.f_salary = 0.0;
        this.f_bonus = 0.0;
    }

    public Faculty(int f_id, String f_lname, String f_fname, double f_salary, double f_bonus) {
        this.f_id = f_id;
        this.f_lname = f_lname;
        this.f_fname = f_fname;
        this.f_salary = f_salary;
        this.f_bonus = f_bonus;
    }

    public int getF_id() {
        return f_id;
    }

    public void setF_id(int f_id) {
        this.f_id = f_id;
    }

    public String getF_lname() {
        return f_lname;
    }

    public void setF_lname(String f_lname) {
        this.f_lname = f_lname;
    }

    public String getF_fname() {
        return f_fname;
    }

    public void setF_fname(String f_fname) {
        this.f_fname = f_fname;
    }

    public double getF_salary() {
        return f_salary;
    }

    public void setF_salary(double f_salary) {
        this.f_salary = f_salary;
    }

    public double getF_bonus() {
        return f_bonus;
    }

    public void setF_bonus(double f_bonus) {
        this.f_bonus = f_bonus;
    }

    @Override
    public String toString() {
        return "Faculty{" +
                "f_id=" + f_id +
                ", f_lname='" + f_lname + '\'' +
                ", f_fname='" + f_fname + '\'' +
                ", f_salary=" + f_salary +
                ", f_bonus=" + f_bonus +
                '}';
    }

    public double calcBonus() {
        return f_salary*f_bonus/100;
    }
}
