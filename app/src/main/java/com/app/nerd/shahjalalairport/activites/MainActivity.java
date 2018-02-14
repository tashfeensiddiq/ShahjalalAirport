package com.app.nerd.shahjalalairport.activites;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.app.nerd.shahjalalairport.R;

public class MainActivity extends AppCompatActivity{

    Button mapButton,
        flightButton,
        airButton,
        restButton,
        policeButton,
        hotelButton,
        bankButton,
        ambButton,
        aboutButton,
    directorButton,
            luggageButton,
    magistrateButton
    ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initialize Button

        magistrateButton = (Button) findViewById(R.id.btn_magistrate);
        luggageButton = (Button) findViewById(R.id.btn_luggage);
        mapButton = (Button) findViewById(R.id.btn_map);
        flightButton = (Button) findViewById(R.id.btn_flight);
        airButton = (Button) findViewById(R.id.btn_airlines);
        restButton = (Button) findViewById(R.id.btn_restaurant);
        policeButton = (Button) findViewById(R.id.btn_police);
        hotelButton = (Button) findViewById(R.id.btn_hotel);
        bankButton = (Button) findViewById(R.id.btn_bank);
        ambButton = (Button) findViewById(R.id.btn_ambulance);
        directorButton = (Button) findViewById(R.id.btn_director);
        aboutButton = (Button) findViewById(R.id.btn_about);

        mapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, MapsActivity.class));
                overridePendingTransition(R.anim.anim_enter,R.anim.anim_leave);
                finish();
            }
        });

        flightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, ScheduleActivity.class));
                overridePendingTransition(R.anim.anim_enter,R.anim.anim_leave);
                finish();
            }
        });

        airButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, AirlinesActivity.class));
                overridePendingTransition(R.anim.anim_enter,R.anim.anim_leave);
                finish();
            }
        });

        restButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, FoodActivity.class));
                overridePendingTransition(R.anim.anim_enter,R.anim.anim_leave);
                finish();
            }
        });

        policeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, PoliceActivity.class));
                overridePendingTransition(R.anim.anim_enter,R.anim.anim_leave);
                finish();
            }
        });

        hotelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, HotelsActivity.class));
                overridePendingTransition(R.anim.anim_enter,R.anim.anim_leave);
                finish();
            }
        });

        bankButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, MoneyExchangeActivity.class));
                overridePendingTransition(R.anim.anim_enter,R.anim.anim_leave);
                finish();

            }
        });

        ambButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, AmbuActivity.class));
                overridePendingTransition(R.anim.anim_enter,R.anim.anim_leave);
                finish();
            }
        });

        directorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, DirectorActivity.class));
                overridePendingTransition(R.anim.anim_enter,R.anim.anim_leave);
                finish();
            }
        });

        luggageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, LuggageandwrappingActivity.class));
                overridePendingTransition(R.anim.anim_enter,R.anim.anim_leave);
                finish();
            }
        });


        magistrateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, MagistrateActivity.class));
                overridePendingTransition(R.anim.anim_enter,R.anim.anim_leave);
                finish();
            }
        });

        aboutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, AboutActivity.class));
                overridePendingTransition(R.anim.anim_enter,R.anim.anim_leave);
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
