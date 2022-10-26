/**
 ============================================================================================================================
 HEADER COMMENT BLOCK FOR THE .JAVA FILE

 App:- SMART FUEL APP
 For:- EAD Module Related Development - Sri Lankan Institute of Information Technology

 *** This Special .java file is for :-
The Biggining of user journy of the user to ragistering his/her vehicle for the system with assigned station name existing
 several validation tecniques and deep data base queries have used from backend to generate this class.
 User have to select station that he have reached (from existing stations of the system/ validated) and can navigate to the next page
 with that data. All managed with tost messages and validatens for adding and generating/retriveing data.

 Author(s): Ruvindu Kaushalya(Leader)

 ============================================================================================================================
 **/

package com.example.fualapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CheckFuelStatusForMe extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    public static final String RUVIMESSAGETWO = "com.example.fualapp.RUVIMESSAGETWO";
    private Retrofit retrofit;
    private RetrofitInterface retrofitInterface;

    Spinner stationSpinner;
    EditText takeinputtext;
    TextView vehiclesWaiting, lineStartTime, fuelStatus, petrolArrivalAt, petrolEndAt, dieselArrivalAt, dieselEndAt,stationnamebyload;
    Button checkStationStatus, addVehicleToQ;



    String stationNameCatched;
    // Test Array Taken
    StationResult[] takefromfunction;
    public void setTakefromfunction(StationResult[] takefromfunction) {
        this.takefromfunction = takefromfunction;
    }
    // Test array to list converter
    List<String> namestofeedlist = new ArrayList<String>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_fuel_status_for_me);

        retrofit = new Retrofit.Builder()
                .baseUrl(MainActivity.BASE_URL2)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        retrofitInterface = retrofit.create(RetrofitInterface.class);


        // Data Fields Imported Correctly,
        stationnamebyload = findViewById(R.id.textView12);
        takeinputtext = findViewById(R.id.amountkk);
        stationSpinner = findViewById(R.id.amount7);
        vehiclesWaiting = findViewById(R.id.amount6);
        lineStartTime = findViewById(R.id.amount8);
        fuelStatus = findViewById(R.id.amount9);
        petrolArrivalAt = findViewById(R.id.textView19);
        petrolEndAt = findViewById(R.id.textView20);
        dieselArrivalAt = findViewById(R.id.textView21);
        dieselEndAt = findViewById(R.id.textView22);
        checkStationStatus = findViewById(R.id.textView4);
        addVehicleToQ = findViewById(R.id.AddtoQButton);

        stationnamebyload.setText("XXXX");
        //Calling Online Stations Data For Spinner :-
        LoadStations();

        //===================================================TEST 1 START =====================================================

        // Spinner click listener
           stationSpinner.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);

        // Spinner Drop down elements - Remove Later
        List<String> categories = new ArrayList<String>();
        categories.add("Item 1");
        categories.add("Item 2");
        categories.add("Item 3");
        categories.add("Item 4");
        categories.add("Badulla");
        categories.add("VV");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, namestofeedlist);
        //ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        stationSpinner.setAdapter(dataAdapter);

        //========================================================== TEST 1 END =================================================

    }


    //========================TEST 1.2 START ====================================================================================

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        String item = parent.getItemAtPosition(position).toString();

        // Showing selected spinner item
        Toast.makeText(parent.getContext(), "Selected Station from Dropdown is: " + item, Toast.LENGTH_LONG).show();

        stationNameCatched = item;

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }

    //===========================TEST 1.2 END =====================================================================================



//Loding data on the selected station by onclick on enter button

    public void LoadDetails(View view) {

        HashMap<String, String> map = new HashMap<>();

        //Enter Station Name to search here :---------------
        map.put("stationname", takeinputtext.getText().toString());

        Call<StationResult> call = retrofitInterface.loadstationdata(map);

        call.enqueue(new Callback<StationResult>() {
            @Override
            public void onResponse(Call<StationResult> call, Response<StationResult> response) {

                if (response.code() == 200) {

                    StationResult result = response.body();

                    stationnamebyload.setText(result.getStationname());
                    vehiclesWaiting.setText( result.getQueue().toString());
                    fuelStatus.setText(result.getStatus());

                    petrolArrivalAt.setText(result.getPetrolarrivaltime());
                    petrolEndAt.setText(result.getPetrolfinishtime());

                    dieselArrivalAt.setText(result.getDieselarrivaltime());
                    dieselEndAt.setText(result.getDieselfinishtime());

                    //Calling Time Taking Function :-
                    LoadEarliestVehicleforthestation();

                } else if (response.code() == 404) {
                    Toast.makeText(CheckFuelStatusForMe.this, "Station You Entered is not On Line! Please Enter other to Continue ! ",
                            Toast.LENGTH_LONG).show();

                    stationnamebyload.setText("Not Online!");
                    vehiclesWaiting.setText("");
                    fuelStatus.setText("");

                    petrolArrivalAt.setText("");
                    petrolEndAt.setText("");

                    dieselArrivalAt.setText("");
                    dieselEndAt.setText("");
                    lineStartTime.setText("");
                }
            }

            @Override
            public void onFailure(Call<StationResult> call, Throwable t) {
                Toast.makeText(CheckFuelStatusForMe.this, t.getMessage(),
                        Toast.LENGTH_LONG).show();
            }
        });

    }



// Taking Earliest Added Time of Vehicle ----------------------------------------------------------------------------------------

    public void LoadEarliestVehicleforthestation() {

        HashMap<String, String> map = new HashMap<>();

        //Enter Station Name to search here :---------------
        map.put("stationName", stationnamebyload.getText().toString());

        Call<VehicleResult> call = retrofitInterface.loadealiestaddedvehiclefortheq(map);

        call.enqueue(new Callback<VehicleResult>() {
            @Override
            public void onResponse(Call<VehicleResult> call, Response<VehicleResult> response) {
                if (response.code() == 200) {

                    VehicleResult result = response.body();
                    lineStartTime.setText(result.getCreatedAt().toString());

                } else if (response.code() == 404) {
                    Toast.makeText(CheckFuelStatusForMe.this, "No Vehicles in this Queue Yet!",
                            Toast.LENGTH_LONG).show();
                    lineStartTime.setText("No Vehicles In Queue");
                }
            }
            @Override
            public void onFailure(Call<VehicleResult> call, Throwable t) {
                Toast.makeText(CheckFuelStatusForMe.this, t.getMessage(),
                        Toast.LENGTH_LONG).show();
            }
        });

    }


    //---------------------------------------------------------Special Spnier for showing stations at online --------------------

    public void LoadStations() {

        Call<StationResult[]> call = retrofitInterface.loadallstations();

        call.enqueue(new Callback<StationResult[]>() {
            @Override
            public void onResponse(Call<StationResult[]> call, Response<StationResult[]> response) {

                if (response.code() == 200) {

                    Toast.makeText(CheckFuelStatusForMe.this, "Check The Stations Online NOW!",
                            Toast.LENGTH_LONG).show();

                    Toast.makeText(CheckFuelStatusForMe.this, "Please Enter the Station You Reached to Continue !",
                            Toast.LENGTH_LONG).show();

                    takefromfunction =response.body();

                    for(int i=0;i<takefromfunction.length; i++){
                      //  Toast.makeText(CheckFuelStatusForMe.this, "Check Loaded Stations > "+ takefromfunction[i].getStationname() + "Check Length of Station Arraylist > " +  takefromfunction.length, Toast.LENGTH_LONG).show();
                        namestofeedlist.add(takefromfunction[i].getStationname().toString());
                    }


                } else if (response.code() == 404) {
                    Toast.makeText(CheckFuelStatusForMe.this, "Sorry! No Stations Avilable Online!",
                            Toast.LENGTH_LONG).show();

                }

            }

            @Override
            public void onFailure(Call<StationResult[]> call, Throwable t) {
                Toast.makeText(CheckFuelStatusForMe.this, t.getMessage(),
                        Toast.LENGTH_LONG).show();
            }
        });

    }

    //----------------------------------------------------------------------------------------------------------------------

//Sending user back to home page
    public void sendToInitialLanding(View view) {
        Intent intent = new Intent(this,  FualUser.class);
        startActivity(intent);
    }

    //user sending next page with station name if it is only validated from the system logics
    public void sendToAddfuelQueueWithDetails(View view) {

        if(stationnamebyload.getText().toString().equals("Not Online!") || stationnamebyload.getText().toString().equals("XXXX")){
            Toast.makeText(this, "Please Enter Valid Station to Continue!", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(this, "Please Enter More Details of you to Continue!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(CheckFuelStatusForMe.this,VehicleAddToFualQ.class);
            intent.putExtra(RUVIMESSAGETWO, takeinputtext.getText().toString());
            startActivity(intent);
        }

    }

} //Class Ended