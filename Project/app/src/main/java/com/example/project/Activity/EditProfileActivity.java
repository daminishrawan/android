package com.example.project.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.project.Entity.classrooms;
import com.example.project.Entity.students;
import com.example.project.Entity.users;
import com.example.project.R;
import com.example.project.utils.RetrofitClient;
import com.google.gson.JsonObject;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditProfileActivity extends AppCompatActivity {

    EditText editName,editEmail,editAddress,editMobile;

    Button btnSaveChanges;

    users user ;
    students student;
    //classrooms classroom;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        //editAcademicClass= findViewById(R.id.editAcademicClass);
        editName= findViewById(R.id.editName);
        editEmail= findViewById(R.id.editEmail);
        //editPass= findViewById(R.id.editPass);
        editAddress= findViewById(R.id.editAddress);
        editMobile = findViewById(R.id.editMobile);

        user = (users) getIntent().getSerializableExtra("user1");
        //classroom = (classrooms) getIntent().getSerializableExtra("classroom");
        btnSaveChanges= findViewById(R.id.btnSaveChanges);

        editEmail.setText(user.getEmail());
        //editAcademicClass.setText(classroom.getClassroom_name());
//        editPassword.setText(user.getPassword());
        editName.setText(user.getName());
//        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
//        String formattedDate = dateFormat.format(user.getDob());
//        editDOB.setText(formattedDate);
        editAddress.setText(user.getAddress());
        editMobile.setText(user.getMobile());

        btnSaveChanges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                users user1 = new users();
                //classrooms class1 = new classrooms();
                user1.setName(editName.getText().toString());
                user1.setEmail(editEmail.getText().toString());
                user1.setAddress(editAddress.getText().toString());
                user1.setMobile(editMobile.getText().toString());

                RetrofitClient.getInstance().getApi().editUser(user.getUid(),user1).enqueue(new Callback<JsonObject>() {
                    @Override
                    public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                        if(response.body().getAsJsonObject().get("message").getAsString().equals("User information updated successfully."))
                        {
                            Toast.makeText(EditProfileActivity.this, "EDITED", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }

                    @Override
                    public void onFailure(Call<JsonObject> call, Throwable t) {

                    }
                });

                Intent intent = new Intent(getApplicationContext(),ProfileActivity.class);
                startActivity(intent);
            }
        });
    }
}