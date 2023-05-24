package com.example.pazig_projekt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class SettingsActivity extends AppCompatActivity {


    ImageButton ExitMainSButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);


        ExitMainSButton = (ImageButton) findViewById(R.id.ExitMainSButton);

        ExitMainSButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentLoadMain = new Intent(SettingsActivity.this, MainActivity.class);
                startActivity(intentLoadMain);
            }
        });
    }
}