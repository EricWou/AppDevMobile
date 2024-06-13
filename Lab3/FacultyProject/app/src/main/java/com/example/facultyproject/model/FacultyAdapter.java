package com.example.facultyproject.model;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.facultyproject.R;

import java.util.ArrayList;

//holds the items of the view holder
public class FacultyAdapter extends RecyclerView.Adapter<FacultyViewHolder>
                            implements AdapterView.OnItemClickListener {

    static ArrayList<Faculty> facultyArrayList;
    private Context context;

    public ArrayList<Faculty> getFacultyArrayList() {
        return facultyArrayList;
    }

    public void setFacultyArrayList(ArrayList<Faculty> facultyArrayList) {
        FacultyAdapter.facultyArrayList = facultyArrayList;
    }

    public FacultyAdapter(ArrayList<Faculty> list, Context context) {
        FacultyAdapter.facultyArrayList = list;
        this.context = context;
    }

    @NonNull
    @Override
    public FacultyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        Context context = parent.getContext();

        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(R.layout.faculty_card, parent, false);

        FacultyViewHolder viewHolder = new FacultyViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull FacultyViewHolder viewHolder, int position) {

        viewHolder.getfRateRecyclerTextView().setText(facultyArrayList.get(position).getF_bonus()+"");
        viewHolder.getfIDRecyclerTextView().setText(facultyArrayList.get(position).getF_id()+"");
        viewHolder.getfLNameRecyclerTextView().setText(facultyArrayList.get(position).getF_lname());
        viewHolder.getfFNameRecyclerTextView().setText(facultyArrayList.get(position).getF_fname());
        viewHolder.getfSalaryRecyclerTextView().setText(facultyArrayList.get(position).getF_salary()+"");

    }

    @Override
    public int getItemCount() {
        return facultyArrayList.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Log.i("Item clicked","tushar:itemclicked") ;
    }
}
