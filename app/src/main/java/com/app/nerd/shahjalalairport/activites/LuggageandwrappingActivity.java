package com.app.nerd.shahjalalairport.activites;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.app.nerd.shahjalalairport.R;

public class LuggageandwrappingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_luggageandwrapping);
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(LuggageandwrappingActivity.this, MainActivity.class));
        overridePendingTransition(R.anim.anim_enter, R.anim.anim_leave);
        finish();
    }
}