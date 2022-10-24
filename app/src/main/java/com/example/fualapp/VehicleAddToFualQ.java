package com.example.fualapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class VehicleAddToFualQ extends AppCompatActivity {

    private Retrofit retrofit;
    private RetrofitInterface retrofitInterface;

    EditText vehicleNo, vehicleType, arrivalTime, stationName;
    Button AddtoQButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle_add_to_fual_q);

        retrofit = new Retrofit.Builder()
                .baseUrl(MainActivity.BASE_URL2)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        retrofitInterface = retrofit.create(RetrofitInterface.class);

        // Data Fields Imported Correctly,
        vehicleNo = findViewById(R.id.vehicleNo);
        vehicleType = findViewById(R.id.vehicleType);
        arrivalTime = findViewById(R.id.arrivalTime);
        stationName = findViewById(R.id.stationName);
        AddtoQButton = findViewById(R.id.AddtoQButton);
    }

    public void sendToInitialLanding(View view) {
        Intent intent = new Intent(this,  FualUser.class);
        Button image = (Button) findViewById(R.id.textView4);
        startActivity(intent);
    }


    //Implimet Your Methords Here :-

    public void AddtoQueue(View view) {

        //Calling Station Queue Increasing Methord :-
        AddMyVehicletoStationQueue();

        // Extra Button Identification :-
        Button image = (Button) findViewById(R.id.AddtoQButton);
        HashMap<String, String> map = new HashMap<>();

        map.put("vehicleID", vehicleNo.getText().toString());
        map.put("arrivalTime", arrivalTime.getText().toString());
        map.put("depatutrTime", null);
        map.put("vehicleType", vehicleType.getText().toString());
        map.put("stationName", stationName.getText().toString());

        Call<Void> call = retrofitInterface.executeAddNewVehicle(map);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {

                if (response.code() == 200) {
                    Toast.makeText(VehicleAddToFualQ.this,
                            "Added the Vehicle successfully", Toast.LENGTH_LONG).show();
                } else if (response.code() == 400) {
                    Toast.makeText(VehicleAddToFualQ.this,
                            "Already Added to the Queue", Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(VehicleAddToFualQ.this, t.getMessage(),
                        Toast.LENGTH_LONG).show();
            }
        });

    }



    // New Adding For Removing Vehicle from Station Queue :-

    public void AddMyVehicletoStationQueue() {

        HashMap<String, String> map = new HashMap<>();

        map.put("stationname", stationName.getText().toString());

        Call<Void> call = retrofitInterface.executeAddVehicletoStationQueue(map);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {

                if (response.code() == 200) {
                    Toast.makeText(VehicleAddToFualQ.this,
                            "Station Notified that you are IN !", Toast.LENGTH_LONG).show();

                } else if (response.code() == 404) {
                    Toast.makeText(VehicleAddToFualQ.this,
                            "Sorry! Station Unable to know that you are IN !", Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(VehicleAddToFualQ.this, t.getMessage(),
                        Toast.LENGTH_LONG).show();
            }
        });

    }




}