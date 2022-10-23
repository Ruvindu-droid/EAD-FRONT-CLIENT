package com.example.fualapp;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RetrofitInterface {

    @POST("/login")
    Call<com.example.fualapp.LoginResult> executeLogin(@Body HashMap<String, String> map);

    @POST("/site/create")
    Call<Void> executeSignup (@Body HashMap<String, String> map);

    @POST("/normaluser/create-R-normal-user")
    Call<Void> executeAddNewVehicle (@Body HashMap<String, String> map);

}
