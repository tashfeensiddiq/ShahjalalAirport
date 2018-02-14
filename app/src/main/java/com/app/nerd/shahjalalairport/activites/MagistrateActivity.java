package com.app.nerd.shahjalalairport.activites;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.app.nerd.shahjalalairport.R;

public class MagistrateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_magistrate);
    }
    @Override
    public void onBackPressed() {
        startActivity(new Intent(MagistrateActivity.this, MainActivity.class));
        overridePendingTransition(R.anim.anim_enter, R.anim.anim_leave);
        finish();
    }
}
