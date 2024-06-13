package com.example.facultyproject.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.facultyproject.R;
import com.example.facultyproject.model.Faculty;

import java.util.ArrayList;

//holds the items of the FacultyViewHolder
//Would also make sense to implement where the FacultyAdapter is the outer class for
//FacultyViewHolder which would be the inner class
public class FacultyAdapter extends RecyclerView.Adapter<FacultyAdapter.FacultyViewHolder> {
    private ArrayList<Faculty> facultyArrayList;
    private Context context;

    public ArrayList<Faculty> getFacultyArrayList() {
        return facultyArrayList;
    }

    public void setFacultyArrayList(ArrayList<Faculty> facultyArrayList) {
        this.facultyArrayList = facultyArrayList;
    }

    public FacultyAdapter(ArrayList<Faculty> list, Context context) {
        this.facultyArrayList = list;
        this.context = context;
    }

    @NonNull
    @Override
    public FacultyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        Context context = parent.getContext();

        //inflate() method is to set the appropriate view, which takes the R.layout.faculty_card
        //which is a fragment or part of the entire activity screen
        //like in the MainActivity where we have setContentView(), but which takes the entire activity screen
        //these two methods have similar purposes
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.faculty_card, parent, false);

        FacultyViewHolder viewHolder = new FacultyViewHolder(view);

        return viewHolder;
    }

    //to bind the data from the FacultyViewHolder
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

    class FacultyViewHolder extends RecyclerView.ViewHolder {

        private TextView fRateRecyclerTextView;
        private TextView fIDRecyclerTextView;
        private TextView fLNameRecyclerTextView;
        private TextView fFNameRecyclerTextView;
        private TextView fSalaryRecyclerTextView;
        private View view;

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

        public FacultyViewHolder(@NonNull View itemView) {
            super(itemView);

            fRateRecyclerTextView = itemView.findViewById(R.id.f_rate_recycler_text_view);
            fIDRecyclerTextView = itemView.findViewById(R.id.f_id_recycler_text_view);
            fLNameRecyclerTextView = itemView.findViewById(R.id.f_lname_recycler_text_view);
            fFNameRecyclerTextView = itemView.findViewById(R.id.f_fname_recycler_text_view);
            fSalaryRecyclerTextView = itemView.findViewById(R.id.f_salary_recycler_text_view);

            view = itemView;

            //can potentially implement with Intent to dynamically retrieve data
            view.setOnClickListener(v -> {

                Faculty faculty = facultyArrayList.get(getAdapterPosition());

                Toast.makeText(view.getContext(), "Faculty Bonus: "+faculty.calcBonus() ,
                        Toast.LENGTH_SHORT).show();
            });

        }

    }

}


