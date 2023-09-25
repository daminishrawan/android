package com.example.project.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.project.Activity.DashboardActivity;
import com.example.project.Entity.users;
import com.example.project.R;
import com.example.project.utils.RetrofitClient;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    EditText editEmail,editPassword;
    CheckBox checkBoxRememberMe;
    Button login;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editEmail = findViewById(R.id.editEmail);
        editPassword = findViewById(R.id.editPassword);
        checkBoxRememberMe = findViewById(R.id.checkBoxRememberMe);
        login = findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                users user = new users();
                user.setEmail(editEmail.getText().toString());
                user.setPassword(editPassword.getText().toString());


                if (checkBoxRememberMe.isChecked())
                    getSharedPreferences("schoolDiary", MODE_PRIVATE).edit().putBoolean("login_status", true).apply();

                RetrofitClient.getInstance().getApi().loginUser(user).enqueue(new Callback<JsonObject>() {

                    @Override
                    public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                        Log.d("Response","Successful"+response.body()+"CheckResposnse"+response.isSuccessful());
                        if (response.isSuccessful()) {
                            JsonObject responseObject = response.body();


                            if (responseObject != null && responseObject.has("data")) {
                                JsonObject data = responseObject.getAsJsonObject("data");
                                if (data!=null) {

                                    //if (data.has("token") && data.has("uid") && data.has("name") && data.has("classroom_name")) {

                                        String token = data.get("token").getAsString();


                                        int uid = data.get("uid").getAsInt();
                                        String name = data.get("name").getAsString();
                                        //String classroom_name = data.get("classroom_name").getAsString();

                                        getSharedPreferences("schoolDiary", MODE_PRIVATE).edit()
                                                .putString("token", token)
                                                .apply();

                                        getSharedPreferences("schoolDiary", MODE_PRIVATE).edit()
                                                .putInt("uid", uid)
                                                .apply();

                                        getSharedPreferences("schoolDiary", MODE_PRIVATE).edit()
                                                .putString("name", name)
                                                .apply();
//
//                                        getSharedPreferences("schoolDiary", MODE_PRIVATE).edit()
//                                                .putString("classroom_name", classroom_name)
//                                                .apply();


                                        startActivity(new Intent(LoginActivity.this, DashboardActivity.class));
                                        finish();
                                    //}
                                }else {
                                    Toast.makeText(LoginActivity.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(LoginActivity.this, "Response data is null or doesn't have expected structure", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(LoginActivity.this, "Response was not successful", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<JsonObject> call, Throwable t) {
                        Log.e("RetrofitError", "Error: " + t.getMessage());
                        Toast.makeText(LoginActivity.this, "Something Went Wrong", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });
    }

    }



