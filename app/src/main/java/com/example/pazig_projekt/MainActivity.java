package com.example.pazig_projekt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    ImageButton SettingsButton;
    ImageButton StartButton;
    ImageButton ScoreButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SettingsButton = (ImageButton) findViewById(R.id.SettingsButton4);
        StartButton = (ImageButton) findViewById(R.id.StartButton);
        ScoreButton = (ImageButton) findViewById(R.id.ScoreButton);

        SettingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentLoadSettings = new Intent (MainActivity.this, SettingsActivity.class);
                startActivity(intentLoadSettings);
            }
        });

        StartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentLoadStart = new Intent (MainActivity.this, StartActivity.class);
                startActivity(intentLoadStart);
            }
        });

        ScoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentLoadScore = new Intent(MainActivity.this, ScoreActivity.class);
                startActivity(intentLoadScore);
            }
        });

    }
}