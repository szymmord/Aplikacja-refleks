package com.example.pazig_projekt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;


public class StartActivity extends AppCompatActivity {

    ImageButton SettingsButton2;
    ImageButton EasyButton;
    ImageButton HardButton;
    ImageButton ExitMainButton;
    ImageButton UltraButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        SettingsButton2 = (ImageButton) findViewById(R.id.SettingsButton2);

        SettingsButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentLoadSettings = new Intent(StartActivity.this, SettingsActivity.class);
                startActivity(intentLoadSettings);
            }
        });

        ExitMainButton = (ImageButton) findViewById(R.id.ExitMainButton);
        ExitMainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentLoadMain = new Intent(StartActivity.this, MainActivity.class);
                startActivity(intentLoadMain);
            }
        });

        EasyButton = (ImageButton) findViewById(R.id.EasyButton);

        EasyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentLoadEasy = new Intent(StartActivity.this, easy_mode.class);
                startActivity(intentLoadEasy);
            }
        });

        HardButton = (ImageButton) findViewById(R.id.HardButton);

        HardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentLoadHard = new Intent(StartActivity.this, hard_mode.class);
                startActivity(intentLoadHard);
            }
        });

        UltraButton = (ImageButton) findViewById(R.id.UltraButton);
        UltraButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentLoadUltraHard = new Intent(StartActivity.this, veryhard_mode.class);
                startActivity(intentLoadUltraHard);
            }
        });
    }
}