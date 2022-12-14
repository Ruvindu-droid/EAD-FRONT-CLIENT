/**
 ============================================================================================================================
 HEADER COMMENT BLOCK FOR THE .JAVA FILE

 App:- SMART FUEL APP
 For:- EAD Module Related Development - Sri Lankan Institute of Information Technology

 *** This Special .java file is for :-
For Calling Server accordingly

 Author(s): Ruvindu Kaushalya(Leader), praveena tawarajah, Netmi Hansika

 ============================================================================================================================
 **/

package com.example.fualapp;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface RetrofitInterface {

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

    @POST("/station/find-r-station-by-name")
    Call<StationResult> executeRetrieveDetails (@Body HashMap<String, String> map);

    @POST("/station/create-r-station")
    Call<Void> executeStationownerdetails (@Body HashMap<String, String> map);

    @POST("/station/update-station-by-name/:id")
    Call<Void> updateStationownerdetails (@Body HashMap<String, String> map);

    @POST("/normaluser/get-last-R-date-By-Station-Name")
    Call<com.example.fualapp.VehicleResult> loadealiestaddedvehiclefortheq(@Body HashMap<String, String> map);


}
