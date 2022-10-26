package com.example.fualapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    public static String BASE_URL2 = "http://10.0.2.2:3000";
    private Retrofit retrofit;
    private RetrofitInterface retrofitInterface;

    /***  Use your Emulator or Real Device Accordingly for TESTING Processes (Please keep Default when Comitting )***/

    // IF you are using the Emulator :-
        private String BASE_URL = "http://10.0.2.2:3000";

    //  If you using a Real Device :- (Connect to same network both Lap and mobile, add the IP here)
//      private String BASE_URL = "http://192.168.8.159:3000";

    /***  ------------------------------------------------------------------------------------------------------  ***/


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        retrofitInterface = retrofit.create(RetrofitInterface.class);

    }

    // Button Action for navigating to the Registering and navigating to the pages (ADD Your Page Path from Here as TESTING DEVELOPNMENT)-->>>
    public void GoRegAct(View view) {
        Toast.makeText(MainActivity.this, "You Are Warmly Welcomed !", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(MainActivity.this, FirstNavigation.class);
        startActivity(intent);
    }

    //Button action to navigate to the FuelUser
    public void move1(View view) {
        Toast.makeText(MainActivity.this, "Fual user", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(MainActivity.this, CheckMyVehicleInQ.class);
        startActivity(intent);
    }

}
