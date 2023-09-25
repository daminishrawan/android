package com.example.project.utils;

import com.example.project.Entity.users;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface API {
    String BASE_URL = "http://192.168.43.219:3000";

    @POST("/login")
    Call<JsonObject> loginUser(@Body users user);

    @GET("/users/{id}")
    Call<JsonObject> getUserById(@Path("id") int userId);

    @GET("/users/exams/{id}")
    Call<JsonObject> getExamById(@Path("id") int userId);

    @PUT("/users/{id}")
    Call<JsonObject> editUser(@Path("id") int userId ,@Body users user1 );


}
