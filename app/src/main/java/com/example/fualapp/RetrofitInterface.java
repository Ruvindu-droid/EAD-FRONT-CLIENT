package com.example.fualapp;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface RetrofitInterface {

    @POST("/login")
    Call<com.example.fualapp.LoginResult> executeLogin(@Body HashMap<String, String> map);

    @POST("/site/create")
    Call<Void> executeSignup (@Body HashMap<String, String> map);

    @POST("/normaluser/create-R-normal-user")
    Call<Void> executeAddNewVehicle (@Body HashMap<String, String> map);

    @POST("/normaluser/get-R-normal-user-by-vehicle-number")
    Call<com.example.fualapp.VehicleResult> loadvehicledata(@Body HashMap<String, String> map);

    @POST("/normaluser/Delete-R-normal-user-by-vehicle-number")
    Call<Void> executeRemoveVehicle (@Body HashMap<String, String> map);

    @POST("/station/decrment-r-queue-by-special-function")
    Call<Void> executeRemoveVehiclefromStationQueue (@Body HashMap<String, String> map);

    @POST("/station/increment-r-queue-by-special-function")
    Call<Void> executeAddVehicletoStationQueue (@Body HashMap<String, String> map);

    @POST("/station/find-r-station-by-name")
    Call<com.example.fualapp.StationResult> loadstationdata(@Body HashMap<String, String> map);

    @POST("/station/get-r-all-station")
    Call<com.example.fualapp.StationResult[]> loadallstations();


}
