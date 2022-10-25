package com.example.fualapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CheckFuelStatusForMe extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_fuel_status_for_me);
    }


    public void sendToInitialLanding(View view) {
        Intent intent = new Intent(this,  FualUser.class);
        Button image = (Button) findViewById(R.id.textView4);
        startActivity(intent);
    }

}