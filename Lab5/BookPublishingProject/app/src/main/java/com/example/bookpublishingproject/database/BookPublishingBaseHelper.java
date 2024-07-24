package com.example.bookpublishingproject.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class BookPublishingBaseHelper extends SQLiteOpenHelper  {

    private static final int VERSION=1;
    private static final String DATABASE_NAME = "bookpublishingBase.db";

    public BookPublishingBaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + BookPublishingDbSchema.BookTable.NAME + "(" +
                BookPublishingDbSchema.BookTable.Cols.B_ID + " INTEGER PRIMARY KEY , " +
                BookPublishingDbSchema.BookTable.Cols.B_AUTHOR + " TEXT , " +
                BookPublishingDbSchema.BookTable.Cols.B_TITLE + " TEXT , " +
                BookPublishingDbSchema.BookTable.Cols.B_ISBN + " INTEGER , " +
                BookPublishingDbSchema.BookTable.Cols.B_TYPE + " STRING , " +
                BookPublishingDbSchema.BookTable.Cols.B_PRICE + " REAL , " +
                BookPublishingDbSchema.BookTable.Cols.P_ID + " INTEGER , " +
                " FOREIGN KEY (" + BookPublishingDbSchema.BookTable.Cols.P_ID + ")" +
                " REFERENCES " + BookPublishingDbSchema.PublisherTable.NAME +
                "(" +BookPublishingDbSchema.PublisherTable.Cols.P_ID +"))"
        );

        db.execSQL("create table " + BookPublishingDbSchema.PublisherTable.NAME + "(" +
                BookPublishingDbSchema.PublisherTable.Cols.P_ID + " INTEGER PRIMARY KEY , " +
                BookPublishingDbSchema.PublisherTable.Cols.P_NAME + " TEXT , " +
                BookPublishingDbSchema.PublisherTable.Cols.P_ADDRESS + " TEXT) "
        );

        db.execSQL("create table " + BookPublishingDbSchema.ChapterTable.NAME + "(" +
                BookPublishingDbSchema.ChapterTable.Cols.B_ID + " INTEGER , " +
                BookPublishingDbSchema.ChapterTable.Cols.C_NO + " INTEGER , " +
                BookPublishingDbSchema.ChapterTable.Cols.C_TITLE + " TEXT , " +
                BookPublishingDbSchema.ChapterTable.Cols.C_PRICE + " REAL, " +
                "PRIMARY KEY (" + BookPublishingDbSchema.ChapterTable.Cols.B_ID + ", " +
                BookPublishingDbSchema.ChapterTable.Cols.C_NO + "))"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
