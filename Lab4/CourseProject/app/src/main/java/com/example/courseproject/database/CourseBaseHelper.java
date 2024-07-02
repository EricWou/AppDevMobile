package com.example.courseproject.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.courseproject.model.Course;

import java.util.ArrayList;

public class CourseBaseHelper extends SQLiteOpenHelper {

    private static final int VERSION=1;
    //VERSION keeps track of if the program is run for the first time and how many times since
    private static final String DATABASE_NAME="courseBase.db";
    //The created database "coursebase.db" can be found by going to
    //Device Manager -> ... -> Open in Device Explorer then looking for
    //data/data/com.example.courseproject/database/courseBase.db

    public CourseBaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    //The table is going to be created locally (in the mobile phone)
    //first time the program is run, will call onCreate() to create the database file
    //on subsequent runs, if the database is located in the mobile phone then onCreate()
    //won't be run again
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + CourseDBSchema.CourseTable.NAME + "(" +
                CourseDBSchema.CourseTable.Columns.COURSE_NO + ", " +
                CourseDBSchema.CourseTable.Columns.COURSE_NAME + ", " +
                CourseDBSchema.CourseTable.Columns.MAX_ENRL + ", " +
                CourseDBSchema.CourseTable.Columns.CREDITS + ") "
        );
    }

    //onUpgrade should only be called if there is need to add/remove/alter tables
    //from existing database, then write appropriate SQL logic in onUpgrade()
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    //helper method for addNewCourse
    private ContentValues getContentValues(Course course){

        ContentValues values = new ContentValues();
        //a built-in class that uses key-value pairs similar to map

        values.put(CourseDBSchema.CourseTable.Columns.COURSE_NO, course.getCourse_no());
        values.put(CourseDBSchema.CourseTable.Columns.COURSE_NAME, course.getCourse_name());
        values.put(CourseDBSchema.CourseTable.Columns.MAX_ENRL, course.getMax_enrl());
        values.put(CourseDBSchema.CourseTable.Columns.CREDITS, course.credits);

        return values;
    }

    public void addNewCourse(Course course){

        //writing data into database
        SQLiteDatabase db = this.getWritableDatabase();

        //creating values from ContentValues (method)
        ContentValues values = getContentValues(course);

        //Insert values into table row
        db.insert(CourseDBSchema.CourseTable.NAME, null, values);

        //Close the database
        db.close();
    }

    //Using Cursor object to store all table content and skip through cursor by moving cursor
    public ArrayList<Course> readCourses() {

        //reading data from database
        SQLiteDatabase db = this.getReadableDatabase();

        //create Cursor object and place database table information inside cursorCourse
        //database table information would be from hard disk
        //cursorCourse would be in Android phone memory
        Cursor cursorCourse = db.rawQuery("select * from "+ CourseDBSchema.CourseTable.NAME, null);
        //first parameter is a literal SQL query
        //second parameter specifies that it should return null if the query returns nothing

        //create ArrayList
        ArrayList<Course> courseModalArrayList = new ArrayList<>();

        //Using this if and do-while to iterate through each data entry stored inside cursorCourse
        //move the Cursor object to the first position
        if (cursorCourse.moveToFirst()) {
            do {
                //getString or getInt depending on the data types of the model class Course
                courseModalArrayList.add(new Course(cursorCourse.getString(0),
                                                    cursorCourse.getString(1),
                                                     cursorCourse.getInt(2)));

            }while (cursorCourse.moveToNext());
        }

        //close Cursor object to remove it from the memory (for performance)
        cursorCourse.close();

        return courseModalArrayList;
    }

    public void updateCourse(Course course) {

        String course_noString = course.getCourse_no();

        //creating values from ContentValues
        ContentValues values = getContentValues(course);

        SQLiteDatabase db = this.getWritableDatabase();

        db.update(CourseDBSchema.CourseTable.NAME,
                    values,
                    CourseDBSchema.CourseTable.Columns.COURSE_NO + "=?",
                    new String[]{course_noString});
        //specifying name of the database table
        //the updated values from the values from the Course object (through getContentValue)
        //specifying which column we are looking for (ie which key)
        //and which entry to update (ie which value)
        //the ? inside "=?" will be replaced by "new String[](course_noString})"
    }
}
