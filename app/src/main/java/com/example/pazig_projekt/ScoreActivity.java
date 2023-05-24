package com.example.pazig_projekt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class ScoreActivity extends AppCompatActivity {

    ImageButton ExitStartButton;
    ImageButton SettingsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        ExitStartButton = (ImageButton) findViewById(R.id.ExitMainSButton) ;
        SettingsButton = (ImageButton) findViewById(R.id.SettingsButton4);

        ExitStartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentLoadStart = new Intent(ScoreActivity.this, StartActivity.class);
                startActivity(intentLoadStart);
            }
        });

        SettingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentLoadSettings = new Intent (ScoreActivity.this, SettingsActivity.class);
                startActivity(intentLoadSettings);
            }
        });
    }
}