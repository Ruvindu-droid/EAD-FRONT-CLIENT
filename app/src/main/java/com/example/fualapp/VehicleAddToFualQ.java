package com.example.fualapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class VehicleAddToFualQ extends AppCompatActivity {

    EditText vehiclenumber, vehicletype, arrivaltime, station;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle_add_to_fual_q);

        // Data Fields Imported Correctly
        vehiclenumber = findViewById(R.id.amount);
        vehicletype = findViewById(R.id.amount2);
        arrivaltime = findViewById(R.id.amount3);
        station = findViewById(R.id.amount4);
    }

    public void sendToInitialLanding(View view) {
        Intent intent = new Intent(this,  FualUser.class);
        Button image = (Button) findViewById(R.id.textView4);
        startActivity(intent);
    }


    //Implimet Your Methords Here :-

    public void AddtoQueue(View view) {

        //Add the Submit Button Function


    }



}