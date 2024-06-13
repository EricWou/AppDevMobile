package com.example.facultyproject.model;

import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.facultyproject.AllFacultiesActivity;
import com.example.facultyproject.R;

//represents each item of the recycler view
public class FacultyViewHolder extends RecyclerView.ViewHolder {

    private TextView fRateRecyclerTextView;
    private TextView fIDRecyclerTextView;
    private TextView fLNameRecyclerTextView;
    private TextView fFNameRecyclerTextView;
    private TextView fSalaryRecyclerTextView;
    private View view;
    //private View.OnClickListener onItemClickListener;

    public TextView getfRateRecyclerTextView() {
        return fRateRecyclerTextView;
    }

    public void setfRateRecyclerTextView(TextView fRateRecyclerTextView) {
        this.fRateRecyclerTextView = fRateRecyclerTextView;
    }

    public TextView getfIDRecyclerTextView() {
        return fIDRecyclerTextView;
    }

    public void setfIDRecyclerTextView(TextView fIDRecyclerTextView) {
        this.fIDRecyclerTextView = fIDRecyclerTextView;
    }

    public TextView getfLNameRecyclerTextView() {
        return fLNameRecyclerTextView;
    }

    public void setfLNameRecyclerTextView(TextView fLNameRecyclerTextView) {
        this.fLNameRecyclerTextView = fLNameRecyclerTextView;
    }

    public TextView getfFNameRecyclerTextView() {
        return fFNameRecyclerTextView;
    }

    public void setfFNameRecyclerTextView(TextView fFNameRecyclerTextView) {
        this.fFNameRecyclerTextView = fFNameRecyclerTextView;
    }

    public TextView getfSalaryRecyclerTextView() {
        return fSalaryRecyclerTextView;
    }

    public void setfSalaryRecyclerTextView(TextView fSalaryRecyclerTextView) {
        this.fSalaryRecyclerTextView = fSalaryRecyclerTextView;
    }

    public View getView() {
        return view;
    }

    public void setView(View view) {
        this.view = view;
    }

    /*
    public View.OnClickListener getOnItemClickListener() {
        return onItemClickListener;
    }

    public void setOnItemClickListener(View.OnClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
     */

    public FacultyViewHolder(@NonNull View itemView) {
        super(itemView);
        //itemView.setTag(this);
        //itemView.setOnClickListener(onItemClickListener);

        //RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder)view.getTag();

        //int position = viewHolder.getAdapterPosition();

        fRateRecyclerTextView = itemView.findViewById(R.id.f_rate_recycler_text_view);
        fIDRecyclerTextView = itemView.findViewById(R.id.f_id_recycler_text_view);
        fLNameRecyclerTextView = itemView.findViewById(R.id.f_lname_recycler_text_view);
        fFNameRecyclerTextView = itemView.findViewById(R.id.f_fname_recycler_text_view);
        fSalaryRecyclerTextView = itemView.findViewById(R.id.f_salary_recycler_text_view);

        view = itemView;

        //Can try to implement dynamic 
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(view.getContext(), ,
                        Toast.LENGTH_SHORT).show();
            }
        });

    }

}
