package com.example.fualapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class FualOwner extends AppCompatActivity {

    private TextView title;
    private EditText sb,msg;
    String  messagerk ="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fual_owner);

        title = (TextView) findViewById(R.id.Welcometae);

        Intent intent = getIntent();
        messagerk = intent.getStringExtra(Authentication.EXTRA_MESSAGE);

        title.setText("Welcome "+messagerk+" !");
    }

    public void sendToInitialLanding(View view) {
        Intent intent = new Intent(this,  FirstNavigation.class);
        ImageButton image = (ImageButton) findViewById(R.id.back);
        startActivity(intent);
    }

    // Implimet Methordes From Here :-


}