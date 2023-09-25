package com.example.project.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project.Entity.classrooms;
import com.example.project.Entity.students;
import com.example.project.Entity.users;
import com.example.project.R;
import com.example.project.utils.RetrofitClient;
import com.google.gson.JsonObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileActivity extends AppCompatActivity {

    TextView btnMyprofile;

    TextView textEdit;

    students student;
    students student1;

    users user;
    users user1;

    classrooms classroom;
    classrooms classroom1;

    TextView txtStudentName, txtClass, txtDOB, txtEmail, txtPass, txtAddress, txtMobile;
    @SuppressLint({"MissingInflatedId", "WrongViewCast"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_acticity);

        txtStudentName = findViewById(R.id.txtStudentName);
        txtClass = findViewById(R.id.txtClass);
        txtDOB = findViewById(R.id.txtDOB);
        txtEmail = findViewById(R.id.txtEmail);
        txtMobile = findViewById(R.id.txtMobile);
        txtAddress = findViewById(R.id.txtAddress);
        textEdit = findViewById(R.id.textEdit);
        btnMyprofile = findViewById(R.id.btnMyprofile);





        btnMyprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), DashboardActivity.class);
                startActivity(intent);
            }
        });

        textEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), EditProfileActivity.class);
                intent.putExtra("user1",user1);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        int id = getSharedPreferences("schoolDiary",MODE_PRIVATE).getInt("uid",0);
        String token = getSharedPreferences("schoolDiary", MODE_PRIVATE).getString("token","token");

        RetrofitClient.getInstance().getApi().getUserById(id).enqueue(new Callback<JsonObject>() {
//

                @Override
                public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                    Log.d("ResponseProfile", "ErrorAtProfile" + " " +response.code()+" "+ response.body() + " " + response.isSuccessful());

                    if (response.isSuccessful() && response.body() != null) {
                        JsonObject jsonObject = response.body();
                        //String status = jsonObject.get("status").getAsString();

                        if (jsonObject!=null && jsonObject.has("data")) {
                            JsonObject data = jsonObject.getAsJsonObject("data");
                            if (data != null) {

                                //String token = data.get("token").getAsString();
                                //int uid = data.get("uid").getAsInt();

//                                getSharedPreferences("schoolDiary", MODE_PRIVATE).edit()
//                                        .putString("token", token)
//                                        .apply();

//                               getSharedPreferences("schoolDiary", MODE_PRIVATE).edit()
//                                       .putInt("uid", uid)
//                                        .apply();
//
                                user = new users();
                                String name = data.get("name").getAsString();
                                String classroomName = data.get("classroom_name").getAsString();
                                String dobString = data.get("dob").getAsString();
                                String email = data.get("email").getAsString();
                                //String password = data.get("password").getAsString();
                                String address = data.get("address").getAsString();
                                String mobile = data.get("mobile").getAsString();

                                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                                Date dob = null;
                                try {
                                    dob = dateFormat.parse(dobString);
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }

                                user.setName(name);
                                user.setEmail(email);
                                user.setAddress(address);
                                user.setMobile(mobile);


                                user1 = new users();

                                user1.setName(user.getName());
                                user1.setEmail(user.getEmail());
                                user1.setAddress(user.getAddress());
                                user1.setMobile(user.getMobile());

                                txtStudentName.setText(name);
                                txtClass.setText(classroomName);
                                txtDOB.setText(dob.toString()); // Adjust how you want to display the date
                                txtEmail.setText(email);
                                //txtPass.setText(password);
                                txtAddress.setText(address);
                                txtMobile.setText(mobile);





                            } else {
                                Toast.makeText(ProfileActivity.this, "Response status is not success", Toast.LENGTH_SHORT).show();
                            }

                        }
                 }
              else {
                       Toast.makeText(ProfileActivity.this, "Response is not successful", Toast.LENGTH_SHORT).show();
                   }
                }

// ...




            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {

                Log.e("RetrofitError", "Error: " + t.getMessage());
                Toast.makeText(ProfileActivity.this, "Something Went Wrong", Toast.LENGTH_SHORT).show();

            }
        });



    }
}