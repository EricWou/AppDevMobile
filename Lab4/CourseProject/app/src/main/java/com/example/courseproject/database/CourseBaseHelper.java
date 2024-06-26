package com.example.courseproject.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.courseproject.model.Course;

public class CourseBaseHelper extends SQLiteOpenHelper {

    private static final int VERSION=1;
    private static final String DATABASE_NAME="courseBase.db";

    public CourseBaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    //The table is going to be created locally (in the mobile phone)
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + CourseDBSchema.CourseTable.NAME + "(" +
                CourseDBSchema.CourseTable.Cols.COURSE_NO + ", " +
                CourseDBSchema.CourseTable.Cols.COURSE_NAME + ", " +
                CourseDBSchema.CourseTable.Cols.MAX_ENRL + ", " +
                CourseDBSchema.CourseTable.Cols.CREDITS + ") "
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    private ContentValues getContentValues(Course course){

        ContentValues values = new ContentValues();

        values.put(CourseDBSchema.CourseTable.Cols.COURSE_NO, course.getCourse_no());
        values.put(CourseDBSchema.CourseTable.Cols.COURSE_NAME, course.getCourse_name());
        values.put(CourseDBSchema.CourseTable.Cols.MAX_ENRL, course.getMax_enrl());
        values.put(CourseDBSchema.CourseTable.Cols.CREDITS, course.credits);

        return values;
    }

    public void addNewCourse(Course course){

        //writing data into database
        SQLiteDatabase db = this.getReadableDatabase();

        //creating values from ContentValues (method)
        ContentValues values = getContentValues(course);

        //Insert values into table row
        db.insert(CourseDBSchema.CourseTable.NAME, null, values);

        //Close the database
        db.close();
    }
}
