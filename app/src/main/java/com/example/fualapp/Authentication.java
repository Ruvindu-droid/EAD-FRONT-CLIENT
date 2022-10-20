package com.example.fualapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.fualapp.Database.DBHelper;

import java.util.List;




public class Authentication extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "com.example.fualapp.MESSAGE";
    EditText etusername, etpassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentication);

        etusername = findViewById(R.id.Sbjinp);
        etpassword = findViewById(R.id.msginp);

    }



    public void signin(View view){

        DBHelper dbHelper = new DBHelper(this);
        List usernames = dbHelper.readAllInfo("user");
        List passwords = dbHelper.readAllInfo("password");
        List types = dbHelper.readAllInfo("type");

        String user = etusername.getText().toString();
        String password = etpassword.getText().toString();


        if(usernames.indexOf(user) >= 0){
            if (passwords.get(usernames.indexOf(user)).equals(password)) {
                if(types.get(usernames.indexOf(user)).equals("fuel_owner")){

                    Toast.makeText(this, "Log In Successfully for your Fuel Owner Account !", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Authentication.this,FualOwner.class);
                    intent.putExtra(EXTRA_MESSAGE, user);
                    startActivity(intent);

                }
                else if(types.get(usernames.indexOf(user)).equals("normal_user")){

                    Toast.makeText(this, "Log In Successfully for your Fuel User Account !", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Authentication.this,FualUser.class);
                    intent.putExtra(EXTRA_MESSAGE, user);
                    startActivity(intent);
                }

            }
            else{
                Toast.makeText(this, "Log In Failed", Toast.LENGTH_SHORT).show();
            }

        }
    }


    public void GoRegAct(View view) {

        Toast.makeText(Authentication.this, "You Are Navigating Back!", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(Authentication.this, FirstNavigation.class);
        startActivity(intent);
    }


}

