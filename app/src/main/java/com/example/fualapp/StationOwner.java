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

public class StationOwner extends AppCompatActivity {

    private Retrofit retrofit;
    private RetrofitInterface retrofitInterface;

    EditText stationname, petrolarrivaltime, petrolfinishtime, dieselarrivaltime, dieselfinishtime, status;
    Button insertbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_station_owner);

        retrofit = new Retrofit.Builder()
                .baseUrl(MainActivity.BASE_URL2)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        retrofitInterface = retrofit.create(RetrofitInterface.class);

        // Data Fields Imported Correctly,
        stationname = findViewById(R.id.stationname);
        petrolarrivaltime = findViewById(R.id.petrolarrivaltime);
        petrolfinishtime = findViewById(R.id.petrolfinishtime);
        dieselarrivaltime = findViewById(R.id.dieselarrivaltime);
        dieselfinishtime = findViewById(R.id.dieselfinishtime);
        status = findViewById(R.id.status);
        insertbutton = findViewById(R.id.insertbutton);
    }

    public void AddDetails(View view) {

        HashMap<String, String> map = new HashMap<>();

        map.put("stationname", stationname.getText().toString());
        map.put("petrolarrivaltime", petrolarrivaltime.getText().toString());
        map.put("petrolfinishtime", petrolfinishtime.getText().toString());
        map.put("dieselarrivaltime", dieselarrivaltime.getText().toString());
        map.put("dieselfinishtime", dieselfinishtime.getText().toString());
        map.put("status", status.getText().toString());
        map.put("queue", String.valueOf(0));

        Call<Void> call = retrofitInterface.executeStationownerdetails(map);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {

                if (response.code() == 200) {
                    Toast.makeText(StationOwner.this,
                            "Added the Details successfully", Toast.LENGTH_LONG).show();
                } else if (response.code() == 400) {
                    Toast.makeText(StationOwner.this,
                            "Error", Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(StationOwner.this, t.getMessage(),
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