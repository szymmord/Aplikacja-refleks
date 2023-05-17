package com.example.pazig_projekt;

import androidx.appcompat.app.AppCompatActivity;

import android.widget.Chronometer;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.os.SystemClock;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Random;

public class easy_mode extends AppCompatActivity {

    ImageButton ExitButton;
    private Chronometer chronometer;
    Button bNiebieski,bZolty,bCzerwony,bZielony;
    TextView tNiebieski,tZielony,tZolty,tCzerwony,wynik;
    private long pauseOffset;
    private boolean running;
    int gowno;
    int jd=1;
    FirebaseFirestore firestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_easy_mode);

    }
}