package com.example.fualapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.fualapp.Database.DBHelper;



public class Register extends AppCompatActivity {

    EditText etusername, etpassword;
    String  typekk ="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etusername = findViewById(R.id.RegUninp);
        etpassword = findViewById(R.id.RegPwdinp);
    }


    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.radio_pirates:
                if (checked){
                    typekk = "normal_user";}
                break;
            case R.id.radio_ninjas:
                if (checked){
                    typekk = "fuel_owner";}
                break;
        }
    }


    //for register class
    public void addData(View view){
        DBHelper dbHelper = new DBHelper(this);

        long val = dbHelper.addInfo(etusername.getText().toString(),etpassword.getText().toString(),typekk);

        if(val>0){
            Toast.makeText(this, "Successfully Registered to the SMART FUEL APP !", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this, "Sorry! Autentication System Issue Occured, Data Not Inserted", Toast.LENGTH_SHORT).show();
        }
    }


    public void sendToInitialLogin(View view) {
        Intent intent = new Intent(this,  Authentication.class);
        Button image = (Button) findViewById(R.id.welcome_main_login_btn);
        startActivity(intent);
    }

}

