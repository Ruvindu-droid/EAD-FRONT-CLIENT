/**
 ============================================================================================================================
 HEADER COMMENT BLOCK FOR THE .JAVA FILE

 App:- SMART FUEL APP
 For:- EAD Module Related Development - Sri Lankan Institute of Information Technology

 *** This Special .java file is for :-
 Initial page for fuel owner for navigating their activities.With necessary userfriendly designs.

 Author(s): Ruvindu Kaushalya(Leader)

 ============================================================================================================================
 **/

package com.example.fualapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class FualOwner extends AppCompatActivity {

    private TextView title,Account;
    private EditText sb,msg;
    String  messagerk ="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fual_owner);

        title = (TextView) findViewById(R.id.textView1);
        Account = (TextView) findViewById(R.id.textView2);

        Intent intent = getIntent();
        messagerk = intent.getStringExtra(Authentication.EXTRA_MESSAGE);

        title.setText("Welcome "+messagerk+" !");
        Account.setText(""+messagerk+" Authorized");

    }

    //Logging Out
    public void sendToInitialLanding(View view) {
        Intent intent = new Intent(this,  FirstNavigation.class);
        Button image = (Button) findViewById(R.id.textView4);
        startActivity(intent);
    }

    // Implimet Methordes From Here :-

    //Sending to Including Station :--
    public void sendToRegisterStation(View view) {
        Intent intent = new Intent(this,  StationOwner.class); // Rename with correct page
        Button image = (Button) findViewById(R.id.button);
        startActivity(intent);
    }

   //sending to updating station
    public void sendToUpdateStationStatus(View view) {
        Intent intent = new Intent(this,  Retrievestationdetails.class); // Rename with correct page
        Button image = (Button) findViewById(R.id.button2);
        startActivity(intent);
    }

}