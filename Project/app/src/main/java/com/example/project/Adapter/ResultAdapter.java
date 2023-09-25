package com.example.project.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project.Entity.exams;
import com.example.project.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class ResultAdapter extends RecyclerView.Adapter<ResultAdapter.ViewHolder> {
    Context context;
    List<exams> examList;

    public ResultAdapter(Context context, List<exams> examList) {
        this.context = context;
        this.examList = examList;
    }


    @NonNull
    @Override
    public ResultAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.result_list,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ResultAdapter.ViewHolder holder, int position) {

        Log.d("Adapter", "onBindViewHolder called for position: " + position);


        exams exam=examList.get(position);
        //holder.txtDate.setText(String.valueOf( exam.getExam_date()));
        holder.txtSubject.setText(exam.getSubjects());

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        String formattedDate = dateFormat.format(exam.getExam_date());
        holder.txtDate.setText(formattedDate);
        holder.txtMarks.setText(""+exam.getMarks());

        Log.d("Marks",String.valueOf(exam.getMarks()));

    }

    @Override
    public int getItemCount() {
        return examList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtDate,txtSubject,txtMarks;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtDate = itemView.findViewById(R.id.txtDate);
            txtSubject = itemView.findViewById(R.id.txtSubject);
            txtMarks = itemView.findViewById(R.id.txtMarks);

        }
    }
}

