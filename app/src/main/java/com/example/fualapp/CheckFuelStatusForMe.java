package com.example.fualapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CheckFuelStatusForMe extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Retrofit retrofit;
    private RetrofitInterface retrofitInterface;


    Spinner stationSpinner;
    TextView vehiclesWaiting, lineStartTime, fuelStatus, petrolArrivalAt, petrolEndAt, dieselArrivalAt, dieselEndAt;
    Button checkStationStatus, addVehicleToQ;
    String stationNameCatched;


    // Test Array Taken
    StationResult[] takefromfunction;

    public void setTakefromfunction(StationResult[] takefromfunction) {
        this.takefromfunction = takefromfunction;
    }

    // Test array to list converter
    List<String> namestofeedlist = new ArrayList<String>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_fuel_status_for_me);

        retrofit = new Retrofit.Builder()
                .baseUrl(MainActivity.BASE_URL2)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        retrofitInterface = retrofit.create(RetrofitInterface.class);


        // Data Fields Imported Correctly,
        stationSpinner = findViewById(R.id.amount7);
        vehiclesWaiting = findViewById(R.id.amount6);
        lineStartTime = findViewById(R.id.amount8);
        fuelStatus = findViewById(R.id.amount9);
        petrolArrivalAt = findViewById(R.id.textView19);
        petrolEndAt = findViewById(R.id.textView20);
        dieselArrivalAt = findViewById(R.id.textView21);
        dieselEndAt = findViewById(R.id.textView22);
        checkStationStatus = findViewById(R.id.textView4);
        addVehicleToQ = findViewById(R.id.AddtoQButton);

        LoadStations();

        //===================================================TEST 1 START

        // Spinner click listener
           stationSpinner.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);

        // Spinner Drop down elements
        List<String> categories = new ArrayList<String>();
        categories.add("Item 1");
        categories.add("Item 2");
        categories.add("Item 3");
        categories.add("Item 4");
        categories.add("Badulla");
        categories.add("Item 6");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        stationSpinner.setAdapter(dataAdapter);

        //========================================================== TEST 1 END

    }


    //========================TEST 1.2 START

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        String item = parent.getItemAtPosition(position).toString();

        // Showing selected spinner item
        Toast.makeText(parent.getContext(), "Selected Station is : " + item, Toast.LENGTH_LONG).show();

        stationNameCatched = item;
        LoadDetails(item);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }

    //===========================TEST 1.2 END






    public void LoadDetails(String nametoserch) {

        // Button image = (Button) findViewById(R.id.textView4);

        HashMap<String, String> map = new HashMap<>();

        //Enter Station Name to search here :---------------
        map.put("stationname", stationNameCatched);

        Call<StationResult> call = retrofitInterface.loadstationdata(map);

        call.enqueue(new Callback<StationResult>() {
            @Override
            public void onResponse(Call<StationResult> call, Response<StationResult> response) {

                if (response.code() == 200) {

                    StationResult result = response.body();

                    //stationSpinner.setText(result.getStationname());
                    vehiclesWaiting.setText( result.getQueue().toString());
                    fuelStatus.setText(result.getStatus());

                    petrolArrivalAt.setText(result.getPetrolarrivaltime());
                    petrolEndAt.setText(result.getPetrolfinishtime());

                    dieselArrivalAt.setText(result.getDieselarrivaltime());
                    dieselEndAt.setText(result.getDieselfinishtime());

                    lineStartTime.setText("Line Start Success");


                } else if (response.code() == 404) {
                    Toast.makeText(CheckFuelStatusForMe.this, "Station Entered is not Exist!",
                            Toast.LENGTH_LONG).show();

                    //stationSpinner.setText("");;
                    vehiclesWaiting.setText("");
                    fuelStatus.setText("");

                    petrolArrivalAt.setText("");
                    petrolEndAt.setText("");

                    dieselArrivalAt.setText("");
                    dieselEndAt.setText("");

                    lineStartTime.setText("");

                }

            }

            @Override
            public void onFailure(Call<StationResult> call, Throwable t) {
                Toast.makeText(CheckFuelStatusForMe.this, t.getMessage(),
                        Toast.LENGTH_LONG).show();
            }
        });

    }




    //---------------------------------------------------------Special Test--------------------

    public void LoadStations() {

        Call<StationResult[]> call = retrofitInterface.loadallstations();

        call.enqueue(new Callback<StationResult[]>() {
            @Override
            public void onResponse(Call<StationResult[]> call, Response<StationResult[]> response) {

                if (response.code() == 200) {

                    Toast.makeText(CheckFuelStatusForMe.this, "Stations Catched!",
                            Toast.LENGTH_LONG).show();

                    takefromfunction =response.body();

                    for(int i=0;i<takefromfunction.length; i++){
                     //   Toast.makeText(CheckFuelStatusForMe.this, "Checkk>>>>>>>>>>"+ takefromfunction[i].getStationname(), Toast.LENGTH_LONG).show();
                        namestofeedlist.add(takefromfunction[i].getStationname().toString());
                    }


                } else if (response.code() == 404) {
                    Toast.makeText(CheckFuelStatusForMe.this, "Sorry! Stations not catched!",
                            Toast.LENGTH_LONG).show();

                }

            }

            @Override
            public void onFailure(Call<StationResult[]> call, Throwable t) {
                Toast.makeText(CheckFuelStatusForMe.this, t.getMessage(),
                        Toast.LENGTH_LONG).show();
            }
        });

    }

    //----------------------------------------------------------------------------------------------------------------------



    public void sendToInitialLanding(View view) {
        Intent intent = new Intent(this,  FualUser.class);
        Button image = (Button) findViewById(R.id.textView4);
        startActivity(intent);
    }


} //Class Ended