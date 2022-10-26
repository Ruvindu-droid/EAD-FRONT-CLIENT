/**
 ============================================================================================================================
 HEADER COMMENT BLOCK FOR THE .JAVA FILE

 App:- SMART FUEL APP
 For:- EAD Module Related Development - Sri Lankan Institute of Information Technology

 *** This Special .java file is for :-
 Initial page for fuel User for navigating their activities.With necessary userfriendly designs.

 Author(s): Ruvindu Kaushalya(Leader)

 ============================================================================================================================
 **/

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
//Log out
    public void sendToInitialLanding(View view) {
        Intent intent = new Intent(this,  FirstNavigation.class);
        Button image = (Button) findViewById(R.id.textView4);
        startActivity(intent);
    }

    // Send to Begining of adding vehicle to Q

    public void sendToAddToQ(View view) {
        Intent intent = new Intent(this,  CheckFuelStatusForMe.class); // Rename with correct page
        Button image = (Button) findViewById(R.id.button);
        startActivity(intent);
    }

// Send to checking vehicle status
    public void sendToViewmyVehicleStatus(View view) {
        Intent intent = new Intent(this,  CheckMyVehicleInQ.class); // Rename with correct page
        Button image = (Button) findViewById(R.id.button2);
        startActivity(intent);
    }

}

