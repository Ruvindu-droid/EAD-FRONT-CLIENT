package com.example.fualapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Retrievestationdetails extends AppCompatActivity {

    private Retrofit retrofit;
    private RetrofitInterface retrofitInterface;

    EditText stationname_1, petrolarrivaltime_1, petrolfinishtime_1, dieselarrivaltime_1, dieselfinishtime_1, status_1;
    Button updatebutton, textView4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrievestationdetails);

        retrofit = new Retrofit.Builder()
                .baseUrl(MainActivity.BASE_URL2)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        retrofitInterface = retrofit.create(RetrofitInterface.class);

        // Data Fields Imported Correctly,
        stationname_1 = findViewById(R.id.stationname_1);
        petrolarrivaltime_1 = findViewById(R.id.petrolarrivaltime_1);
        petrolfinishtime_1 = findViewById(R.id.petrolfinishtime_1);
        dieselarrivaltime_1 = findViewById(R.id.dieselarrivaltime_1);
        dieselfinishtime_1 = findViewById(R.id.dieselfinishtime_1);
        status_1 = findViewById(R.id.status_1);
        textView4 = findViewById(R.id.textView4);
        updatebutton = findViewById(R.id.updatebutton);


    }
    public void LoadDetails(View view) {

        //Button image = (Button) findViewById(R.id.textView4);

        HashMap<String, String> map = new HashMap<>();

        map.put("stationname", stationname_1.getText().toString());

        Call<StationResult> call = retrofitInterface.executeRetrieveDetails(map);

        call.enqueue(new Callback<StationResult>() {
            @Override
            public void onResponse(Call<StationResult> call, Response<StationResult> response) {

                if (response.code() == 200) {

                    StationResult result = response.body();

                    stationname_1.setText(result.getStationname());
                    petrolarrivaltime_1.setText(result.getPetrolarrivaltime());
                    petrolfinishtime_1.setText(result.getPetrolfinishtime());
                    dieselarrivaltime_1.setText(result.getDieselarrivaltime());
                    dieselfinishtime_1.setText(result.getDieselfinishtime());
                    status_1.setText(result.getStatus());

                } else if (response.code() == 404) {
                    Toast.makeText(Retrievestationdetails.this, "Station is not in here!",
                            Toast.LENGTH_LONG).show();

                    stationname_1.setText("");
                    petrolarrivaltime_1.setText("");
                    petrolfinishtime_1.setText("");
                    dieselarrivaltime_1.setText("");
                    dieselfinishtime_1.setText("");
                    status_1.setText("");
                }

            }

            @Override
            public void onFailure(Call<StationResult> call, Throwable t) {
                Toast.makeText(Retrievestationdetails.this, t.getMessage(),
                        Toast.LENGTH_LONG).show();
            }
        });

    }
    public void UpdateDetails(View view) {

        HashMap<String, String> map = new HashMap<>();

        map.put("stationname", stationname_1.getText().toString());
        map.put("petrolarrivaltime", petrolarrivaltime_1.getText().toString());
        map.put("petrolfinishtime", petrolfinishtime_1.getText().toString());
        map.put("dieselarrivaltime", dieselarrivaltime_1.getText().toString());
        map.put("dieselfinishtime", dieselfinishtime_1.getText().toString());
        map.put("status", status_1.getText().toString());


        Call<Void> call = retrofitInterface.updateStationownerdetails(map);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {

                if (response.code() == 200) {
                    Toast.makeText(Retrievestationdetails.this,
                            "Updated the Details successfully", Toast.LENGTH_LONG).show();
                } else if (response.code() == 400) {
                    Toast.makeText(Retrievestationdetails.this,
                            "Error", Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(Retrievestationdetails.this, t.getMessage(),
                        Toast.LENGTH_LONG).show();
            }
        });

    }
    public void sendToInitialLanding(View view) {
        Intent intent = new Intent(this,  FualOwner.class);
        Button image = (Button) findViewById(R.id.textView4);
        startActivity(intent);
    }
}