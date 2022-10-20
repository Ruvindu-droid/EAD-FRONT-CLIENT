package com.example.fualapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;


public class FirstNavigation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_navigation);
    }

    public void sendToRegistation(View view) {
        Intent intent = new Intent(this,  Register.class);
        Button image = (Button) findViewById(R.id.welcome_main_join_now_btn);
        startActivity(intent);
    }

    public void sendToInitialLogin(View view) {
        Intent intent = new Intent(this,  Authentication.class);
        Button image = (Button) findViewById(R.id.welcome_main_login_btn);
        startActivity(intent);
    }


}

