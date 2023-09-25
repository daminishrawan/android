package com.example.project.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.project.Entity.classrooms;
import com.example.project.Entity.users;

import com.example.project.R;

public class DashboardActivity extends AppCompatActivity {

    EditText editStudentname,editClass;



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        CardView cardProfile=findViewById(R.id.cardProfile);
        CardView cardFees=findViewById(R.id.cardFees);
        CardView cardPlayquiz=findViewById(R.id.cardPlayquiz);
        CardView cardAssignments=findViewById(R.id.cardAssignments);
        CardView cardHoliday=findViewById(R.id.cardHoliday);
        CardView cardTimetable=findViewById(R.id.cardTimetable);
        CardView cardResult=findViewById(R.id.cardResult);
        CardView cardDatesheet=findViewById(R.id.cardDatesheet);
        CardView cardAskdoubts=findViewById(R.id.cardAskdoubts);
        CardView cardSchoolgalary=findViewById(R.id.cardSchoolgalary);
        CardView cardEvents=findViewById(R.id.cardEvents);
        CardView cardChangepassword=findViewById(R.id.cardChangepassword);
        CardView cardLeaveapplication=findViewById(R.id.cardLeaveapplication);
        CardView cardLogout=findViewById(R.id.cardLogout);
        editStudentname = findViewById(R.id.editStudentname);
        editClass = findViewById(R.id.editClass);
//        users user = new users();
//        classrooms classroom = new classrooms();
//        editStudentname.setText(user.getName());
//        editClass.setText(classroom.getClassroom_name());

        String name =getSharedPreferences("schoolDiary",MODE_PRIVATE).getString("name","");
        String classroom_name=getSharedPreferences("schoolDiary",MODE_PRIVATE).getString("classroom_name","");

        editStudentname.setText(name);
       editClass.setText(classroom_name);

        cardProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), ProfileActivity.class);
                startActivity(intent);
            }
        });

        cardTimetable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), TimetableActivity.class);
                startActivity(intent);
            }
        });

        cardLogout.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        }));

        cardResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ResultActivity.class);
                startActivity(intent);
            }
        });

        cardEvents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardActivity.this,EventActivity.class);
                startActivity(intent);
            }
        });

        cardSchoolgalary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getApplicationContext(),SchoolGalleryActivity.class);
                startActivity(intent);
            }
        });
    }



}