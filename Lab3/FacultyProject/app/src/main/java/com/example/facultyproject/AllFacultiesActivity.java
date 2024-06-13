package com.example.facultyproject;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.facultyproject.model.Faculty;
import com.example.facultyproject.adapter.FacultyAdapter;

import java.util.ArrayList;

public class AllFacultiesActivity extends AppCompatActivity {
    private FacultyAdapter adapter;
    private RecyclerView recyclerView;
    private ArrayList<Faculty> facultyArrayListRetrieve;
    private static String EXTRA_FACULTY_ARRAYLIST = "Faculty ArrayList";

    public static Intent newIntent(Context packageContext, ArrayList<Faculty> importList) {

        Intent intent = new Intent(packageContext, AllFacultiesActivity.class);
        //model class Faculty needs to implement Parcelable in order to send ArrayList of objects
        intent.putParcelableArrayListExtra(EXTRA_FACULTY_ARRAYLIST, importList);

        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_all_faculties);

        facultyArrayListRetrieve = getIntent().getParcelableArrayListExtra(EXTRA_FACULTY_ARRAYLIST);

        facultyArrayListRetrieve.addAll(facultyArrayListRetrieve);

        //Looking for the recycler view (like looking for TextEdit)
        recyclerView = findViewById(R.id.faculty_recycler_view);

        //passing the information from ArrayList to the adapter object
        //then passing the adapter object to recyclerView
        //similar
        adapter = new FacultyAdapter(facultyArrayListRetrieve, getApplication());
        recyclerView.setAdapter(adapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(AllFacultiesActivity.this));

    }

}