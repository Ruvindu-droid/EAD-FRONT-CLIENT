package com.example.fualapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class FualUser extends AppCompatActivity {

    private TextView title,Account;
    String  messagerk ="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fual_user);

        title = (TextView) findViewById(R.id.textView1);
        Account = (TextView) findViewById(R.id.textView2);

        Intent intent = getIntent();
        messagerk = intent.getStringExtra(Authentication.EXTRA_MESSAGE);

        title.setText("Welcome "+messagerk+" !");
        Account.setText(""+messagerk+" Authorized");
    }

    public void sendToInitialLanding(View view) {
        Intent intent = new Intent(this,  FirstNavigation.class);
        Button image = (Button) findViewById(R.id.textView4);
        startActivity(intent);
    }

    // Implimet Methordes From Here :-

    public void sendToAddToQ(View view) {
        Intent intent = new Intent(this,  VehicleAddToFualQ.class); // Rename with correct page
        Button image = (Button) findViewById(R.id.button);
        startActivity(intent);
    }

    public void sendToViewmyVehicleStatus(View view) {
        Intent intent = new Intent(this,  MainActivity.class); // Rename with correct page
        Button image = (Button) findViewById(R.id.button2);
        startActivity(intent);
    }

    public void sendToViewStationStatus(View view) {
        Intent intent = new Intent(this,  MainActivity.class); // Rename with correct page
        Button image = (Button) findViewById(R.id.button3);
        startActivity(intent);
    }


}

