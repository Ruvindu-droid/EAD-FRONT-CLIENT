package com.example.fualapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CheckMyVehicleInQ extends AppCompatActivity {

    private Retrofit retrofit;
    private RetrofitInterface retrofitInterface;

    EditText vehicleNo;
    TextView vehicleType, arrivalTime, stationName;
    Button SerchQButton,UnableTakingOffButton,FilledTakingOffButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_my_vehicle_in_q);

        retrofit = new Retrofit.Builder()
                .baseUrl(MainActivity.BASE_URL2)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        retrofitInterface = retrofit.create(RetrofitInterface.class);

        // Data Fields Imported Correctly,
        vehicleNo = findViewById(R.id.amount7);
        vehicleType = findViewById(R.id.amount8);
        arrivalTime = findViewById(R.id.amount9);
        stationName = findViewById(R.id.amount6);
        SerchQButton = findViewById(R.id.textView4);
        UnableTakingOffButton = findViewById(R.id.addtoQButton);
        FilledTakingOffButton = findViewById(R.id.AddtoQButton);

    }


    public void LoadDetails(View view) {

        Button image = (Button) findViewById(R.id.textView4);

            HashMap<String, String> map = new HashMap<>();

            map.put("vehicleID", vehicleNo.getText().toString());

            Call<VehicleResult> call = retrofitInterface.loadvehicledata(map);

            call.enqueue(new Callback<VehicleResult>() {
                @Override
                public void onResponse(Call<VehicleResult> call, Response<VehicleResult> response) {

                    if (response.code() == 200) {

                        VehicleResult result = response.body();

                        //vehicleNo.setText(result.getVehicleID());
                        arrivalTime.setText(result.getArrivalTime());
                        vehicleType.setText(result.getVehicleType());
                        stationName.setText(result.getStationName());

                    } else if (response.code() == 404) {
                        Toast.makeText(CheckMyVehicleInQ.this, "Vehicle is not in Any Queues!",
                                Toast.LENGTH_LONG).show();

                        vehicleNo.setText("");
                        arrivalTime.setText("");
                        vehicleType.setText("");
                        stationName.setText("");
                    }

                }

                @Override
                public void onFailure(Call<VehicleResult> call, Throwable t) {
                    Toast.makeText(CheckMyVehicleInQ.this, t.getMessage(),
                            Toast.LENGTH_LONG).show();
                }
            });

    }


    public void RemoveMyVehicle(View view) {

        //Calling Station Queue Reducing Methord :-
        RemoveMyVehiclefromStationQueue();

        // Extra Button Identification :-
        Button image = (Button) findViewById(R.id.addtoQButton);
        Button image2 = (Button) findViewById(R.id.AddtoQButton);

        HashMap<String, String> map = new HashMap<>();

        map.put("vehicleID", vehicleNo.getText().toString());

        Call<Void> call = retrofitInterface.executeRemoveVehicle(map);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {

                if (response.code() == 200) {
                    Toast.makeText(CheckMyVehicleInQ.this,
                            "Your Vehicle Removed from Fuel Queue Successfully!", Toast.LENGTH_LONG).show();

                    vehicleNo.setText("");
                    arrivalTime.setText("");
                    vehicleType.setText("");
                    stationName.setText("");


                } else if (response.code() == 404) {
                    Toast.makeText(CheckMyVehicleInQ.this,
                            "Invalid Removing Detected !", Toast.LENGTH_LONG).show();

                    vehicleNo.setText("");
                    arrivalTime.setText("");
                    vehicleType.setText("");
                    stationName.setText("");
                }

            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(CheckMyVehicleInQ.this, t.getMessage(),
                        Toast.LENGTH_LONG).show();
            }
        });

    }


    // New Adding For Removing Vehicle from Station Queue :-

    public void RemoveMyVehiclefromStationQueue() {

        HashMap<String, String> map = new HashMap<>();

        map.put("stationname", stationName.getText().toString());

        Call<Void> call = retrofitInterface.executeRemoveVehiclefromStationQueue(map);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {

                if (response.code() == 200) {
                    Toast.makeText(CheckMyVehicleInQ.this,
                            "Station Notified that you are Removed !", Toast.LENGTH_LONG).show();

                } else if (response.code() == 404) {
                    Toast.makeText(CheckMyVehicleInQ.this,
                            "Invalid Removing from Station Detected !", Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(CheckMyVehicleInQ.this, t.getMessage(),
                        Toast.LENGTH_LONG).show();
            }
        });

    }

    public void sendToInitialLanding(View view) {
        Intent intent = new Intent(this,  FualUser.class);
        Button image = (Button) findViewById(R.id.textView4);
        startActivity(intent);
    }


}