package com.example.project.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project.Adapter.ResultAdapter;
import com.example.project.Entity.exams;
import com.example.project.R;
import com.example.project.utils.RetrofitClient;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ResultActivity extends AppCompatActivity {

    TextView textPercentage;
    ImageView backButton;

    TextView tvEng, tvSci, tvMaths, tvHistory, tvGeography, tvSanskrit;

    EditText editEnglish, editScience, editMaths, editHistory, editGeography, editSanskrit;

    RecyclerView recyclerView;
    ResultAdapter resultAdapter;
    List<exams> examList;

    int y, m, d;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        backButton = findViewById(R.id.backButton);
        textPercentage = findViewById(R.id.textPercentage);

        tvEng = findViewById(R.id.tvEng);
        tvSci = findViewById(R.id.tvSci);
        tvMaths = findViewById(R.id.tvMaths);
        tvHistory = findViewById(R.id.tvHistory);
        tvGeography = findViewById(R.id.tvGeography);
        
        
        
        
        
        tvSanskrit = findViewById(R.id.tvSanskrit);

        editEnglish = findViewById(R.id.editEnglish);
        editScience = findViewById(R.id.editScience);
        editMaths = findViewById(R.id.editMaths);
        editHistory = findViewById(R.id.editHistory);
        editGeography = findViewById(R.id.editGeography);
        editSanskrit = findViewById(R.id.editSanskrit);

        recyclerView = findViewById(R.id.recyclerView);
        examList = new ArrayList<>();
        resultAdapter = new ResultAdapter(getApplicationContext(), examList);
        Log.d("ExamList", "Size: " + examList.size());

        recyclerView.setAdapter(resultAdapter);
        resultAdapter.notifyDataSetChanged();
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        getExamById();



        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), DashboardActivity.class);
                startActivity(intent);

            }
        });


    }

    public void getExamById() {
        int id=getApplicationContext().getSharedPreferences("schoolDiary", Context.MODE_PRIVATE).getInt("uid",0);
        String token = getSharedPreferences("schoolDiary", MODE_PRIVATE).getString("token","token");

        RetrofitClient.getInstance().getApi().getExamById(id).enqueue(new Callback<JsonObject>() {

            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                Log.d("ResponseResult", "Error" + response.body() + " " + response.isSuccessful() + " " + response.code());
                if (response.isSuccessful()) {
                    JsonObject responseObject = response.body();
                    if (responseObject.has("data") && responseObject.get("data").isJsonArray()) {
                        JsonArray jsonArray = responseObject.getAsJsonArray("data");
                        for (JsonElement element : jsonArray) {
                            exams exam = new exams();
                            exam.setExam_id(element.getAsJsonObject().get("exam_id").getAsInt());
                            exam.setStudent_id(element.getAsJsonObject().get("student_id").getAsInt());
                            exam.setMarks(element.getAsJsonObject().get("marks").getAsInt());
                            exam.setSubjects(element.getAsJsonObject().get("subjects").getAsString());

                            String dateString = element.getAsJsonObject().get("exam_date").getAsString();

                            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
                            Date examDate = null;
                            try {
                                examDate = dateFormat.parse(dateString);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            exam.setExam_date(examDate);

                            examList.add(exam);

//                            TableLayout tableLayout = findViewById(R.id.tableLayout);
//
//                            for (exams exam1 : examList) {
//                                TableRow row = new TableRow(ResultActivity.this);
//
//                                // Create TextViews for Date, Subject, and Marks
//                               TextView tvDate = new TextView(ResultActivity.this);
//                               TextView tvSubject = new TextView(ResultActivity.this);
//                                TextView tvMarks = new TextView(ResultActivity.this);
//
//                                // Set text for Date, Subject, and Marks
//                                DateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
//                                String formattedDate = dateFormat1.format(exam.getExam_date());
//                                tvDate.setText(formattedDate);
//                                tvSubject.setText(exam.getSubjects());
//                                tvMarks.setText(String.valueOf(exam.getMarks()));
//
//
//                                // Add TextViews to the TableRow
//                                row.addView(tvDate);
//                               row.addView(tvSubject);
//                               row.addView(tvMarks);
//
//                                // Add the TableRow to the TableLayout
//                                tableLayout.addView(row);
//                            }
//
//
//

                            Log.d("FistExamList","size"+examList.size());
                            //resultAdapter.notifyDataSetChanged();

                        }
                      resultAdapter.notifyDataSetChanged();
                    } else {
                        Toast.makeText(getApplicationContext(), "No data found in the response", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Response unsuccessful", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        });


    }
}