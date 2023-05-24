package com.example.pazig_projekt;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.widget.Chronometer;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.os.SystemClock;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class easy_mode extends AppCompatActivity {

    ImageButton ExitButton;
    private Chronometer chronometer;

    ImageButton bNiebieski, bZolty, bCzerwony, bZielony, StartButtonEasy;
    ImageButton tNiebieski, tZielony, tZolty, tCzerwony, tCzarny;
    TextView textStart3;
    private long pauseOffset;
    private boolean running;
    int gowno;
    int jd = 1;
    FirebaseFirestore firestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_easy_mode);
        bNiebieski = (ImageButton) findViewById(R.id.bZielony);
        bCzerwony = (ImageButton) findViewById(R.id.bZolty);
        bZielony = (ImageButton) findViewById(R.id.bCzerwony);
        bZolty = (ImageButton) findViewById(R.id.bNiebieski);
        tNiebieski = (ImageButton) findViewById(R.id.tNiebieski);
        tCzerwony = (ImageButton) findViewById(R.id.tCzerwony);
        tZielony = (ImageButton) findViewById(R.id.tZielony);
        tZolty = (ImageButton) findViewById(R.id.tZolty);
        tCzarny = (ImageButton) findViewById(R.id.tCzarny);
        StartButtonEasy = (ImageButton) findViewById(R.id.StartButtonHard);
        ExitButton = (ImageButton) findViewById(R.id.ExitButton);
        textStart3 = (TextView) findViewById(R.id.textStart3) ;
        //wynik= (TextView) findViewById(R.id.Wynik);


        firestore = FirebaseFirestore.getInstance();

        chronometer = findViewById(R.id.chronometer);
        chronometer.setFormat("Time: %s");
        chronometer.setBase(SystemClock.elapsedRealtime());

        ExitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentLoadStart = new Intent(easy_mode.this, StartActivity.class);
                startActivity(intentLoadStart);
            }
        });

        chronometer.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            @Override
            public void onChronometerTick(Chronometer chronometer) {
                long elapsedMillis = SystemClock.elapsedRealtime() - chronometer.getBase();
                String time = String.format(Locale.getDefault(), "Time: %02d:%02d:%03d",
                        TimeUnit.MILLISECONDS.toMinutes(elapsedMillis),
                        TimeUnit.MILLISECONDS.toSeconds(elapsedMillis) % 60,
                        elapsedMillis % 1000);
                chronometer.setText(time);
            }
        });
    }


    public void startChronometer(View v) {
        if (!running) {
            try {
                Thread.sleep(1000); // Opoźnienie 1000 milisekund = 1 sekunda
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            tNiebieski.setVisibility(View.VISIBLE);
            tZolty.setVisibility(View.INVISIBLE);
            tCzerwony.setVisibility(View.INVISIBLE);
            tZielony.setVisibility(View.INVISIBLE);
            tCzarny.setVisibility(View.VISIBLE);

            StartButtonEasy.setVisibility(View.INVISIBLE);
            textStart3.setVisibility(View.INVISIBLE);
            chronometer.setBase(SystemClock.elapsedRealtime() - pauseOffset);
            chronometer.start();
            running = true;
            Random random = new Random();
            int randomNumber = random.nextInt(4) + 1;


            if (randomNumber == 1) {

                tNiebieski.setVisibility(View.VISIBLE);
                tZolty.setVisibility(View.INVISIBLE);
                tCzerwony.setVisibility(View.INVISIBLE);
                tZielony.setVisibility(View.INVISIBLE);
                tCzarny.setVisibility(View.INVISIBLE);
                gowno = randomNumber;
            }
            if (randomNumber == 2) {

                tCzerwony.setVisibility(View.VISIBLE);
                tZielony.setVisibility(View.INVISIBLE);
                tZolty.setVisibility(View.INVISIBLE);
                tNiebieski.setVisibility(View.INVISIBLE);
                tCzarny.setVisibility(View.INVISIBLE);
                gowno = randomNumber;
            }
            if (randomNumber == 3) {

                tZielony.setVisibility(View.VISIBLE);
                tZolty.setVisibility(View.INVISIBLE);
                tCzerwony.setVisibility(View.INVISIBLE);
                tNiebieski.setVisibility(View.INVISIBLE);
                tCzarny.setVisibility(View.INVISIBLE);
                gowno = randomNumber;
            }
            if (randomNumber == 4) {

                tZolty.setVisibility(View.VISIBLE);
                tZielony.setVisibility(View.INVISIBLE);
                tCzerwony.setVisibility(View.INVISIBLE);
                tNiebieski.setVisibility(View.INVISIBLE);
                tCzarny.setVisibility(View.INVISIBLE);
                gowno = randomNumber;
            }


        }
    }

    public void pauseChronometer(View v) {
        if (running) {
            chronometer.stop();
            pauseOffset = SystemClock.elapsedRealtime() - chronometer.getBase();
            running = false;
            long elapsedMillis = SystemClock.elapsedRealtime() - chronometer.getBase();
            //wynik.setVisibility(View.VISIBLE);
            //wynik.setText(String.valueOf(elapsedMillis));
        }
    }

    public void resetChronometer(View v) {
        chronometer.setBase(SystemClock.elapsedRealtime());
        pauseOffset = 0;
    }

    public void kolorCzerwony(View v) {
        if (gowno == 2) {
            if (jd < 10) {
                long elapsedMillis = SystemClock.elapsedRealtime() - chronometer.getBase();
                //wynik.setVisibility(View.VISIBLE);
                //wynik.setText(String.valueOf(elapsedMillis));
                chronometer.setBase(SystemClock.elapsedRealtime());
                pauseOffset = 0;
                chronometer.stop();
                running = false;
                gowno = 0;
                jd = jd + 1;
                tNiebieski.setVisibility(View.INVISIBLE);
                tZolty.setVisibility(View.INVISIBLE);
                tCzerwony.setVisibility(View.INVISIBLE);
                tZielony.setVisibility(View.INVISIBLE);
                tCzarny.setVisibility(View.VISIBLE);

                Map<String, Object> wyniki = new HashMap<>();
                wyniki.put("firstName", "Test");
                wyniki.put("pomiar", jd);
                wyniki.put("wynik", elapsedMillis);
                firestore.collection("wyniki").add(wyniki).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_LONG).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(), "Failure", Toast.LENGTH_LONG).show();

                    }
                });
                try {
                    Thread.sleep(1000); // Opoźnienie 1000 milisekund = 1 sekunda
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


                startChronometer(v);
            } else {
                chronometer.setBase(SystemClock.elapsedRealtime());
                pauseOffset = 0;
                chronometer.stop();
                running = false;
            }

        }
    }

    public void kolorZielony(View v) {
        if (gowno == 3) {
            if (jd < 10) {
                long elapsedMillis = SystemClock.elapsedRealtime() - chronometer.getBase();
                // wynik.setVisibility(View.VISIBLE);
                //wynik.setText(String.valueOf(elapsedMillis));
                chronometer.setBase(SystemClock.elapsedRealtime());
                pauseOffset = 0;
                chronometer.stop();
                running = false;
                gowno = 0;
                jd = jd + 1;
                tNiebieski.setVisibility(View.INVISIBLE);
                tZolty.setVisibility(View.INVISIBLE);
                tCzerwony.setVisibility(View.INVISIBLE);
                tZielony.setVisibility(View.INVISIBLE);
                tCzarny.setVisibility(View.VISIBLE);
                firestore = FirebaseFirestore.getInstance();

                Map<String, Object> wyniki = new HashMap<>();
                wyniki.put("firstName", "Test");
                wyniki.put("pomiar", jd);
                wyniki.put("wynik", elapsedMillis);
                firestore.collection("wyniki").add(wyniki).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_LONG).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(), "Failure", Toast.LENGTH_LONG).show();

                    }
                });
                try {
                    Thread.sleep(1000); // Opoźnienie 1000 milisekund = 1 sekunda
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


                startChronometer(v);
            } else {
                chronometer.setBase(SystemClock.elapsedRealtime());
                pauseOffset = 0;
                chronometer.stop();
                running = false;
            }

        }
    }

    public void kolorNiebieski(View v) {
        if (gowno == 1) {
            if (jd < 10) {
                long elapsedMillis = SystemClock.elapsedRealtime() - chronometer.getBase();
                // wynik.setVisibility(View.VISIBLE);
                //wynik.setText(String.valueOf(elapsedMillis));
                chronometer.setBase(SystemClock.elapsedRealtime());
                pauseOffset = 0;
                chronometer.stop();
                running = false;
                gowno = 0;
                jd = jd + 1;
                tNiebieski.setVisibility(View.INVISIBLE);
                tZolty.setVisibility(View.INVISIBLE);
                tCzerwony.setVisibility(View.INVISIBLE);
                tZielony.setVisibility(View.INVISIBLE);
                tCzarny.setVisibility(View.VISIBLE);
                firestore = FirebaseFirestore.getInstance();

                Map<String, Object> wyniki = new HashMap<>();
                wyniki.put("firstName", "Test");
                wyniki.put("pomiar", jd);
                wyniki.put("wynik", elapsedMillis);
                firestore.collection("wyniki").add(wyniki).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_LONG).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(), "Failure", Toast.LENGTH_LONG).show();

                    }
                });
                try {
                    Thread.sleep(1000); // Opoźnienie 1000 milisekund = 1 sekunda
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


                startChronometer(v);

            } else {
                chronometer.setBase(SystemClock.elapsedRealtime());
                pauseOffset = 0;
                chronometer.stop();
                running = false;
            }
        }
    }

    public void kolorZolty(View v) {
        if (gowno == 4) {
            if (jd < 10) {
                long elapsedMillis = SystemClock.elapsedRealtime() - chronometer.getBase();
                //wynik.setVisibility(View.VISIBLE);
                //wynik.setText(String.valueOf(elapsedMillis));
                chronometer.setBase(SystemClock.elapsedRealtime());
                pauseOffset = 0;
                chronometer.stop();
                running = false;
                gowno = 0;
                jd = jd + 1;
                tNiebieski.setVisibility(View.INVISIBLE);
                tZolty.setVisibility(View.INVISIBLE);
                tCzerwony.setVisibility(View.INVISIBLE);
                tZielony.setVisibility(View.INVISIBLE);
                tCzarny.setVisibility(View.VISIBLE);
                firestore = FirebaseFirestore.getInstance();

                Map<String, Object> wyniki = new HashMap<>();
                wyniki.put("firstName", "Test");
                wyniki.put("pomiar", jd);
                wyniki.put("wynik", elapsedMillis);
                firestore.collection("wyniki").add(wyniki).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_LONG).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(), "Failure", Toast.LENGTH_LONG).show();

                    }
                });
                try {
                    Thread.sleep(1000); // Opoźnienie 1000 milisekund = 1 sekunda
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


                startChronometer(v);
            } else {
                chronometer.setBase(SystemClock.elapsedRealtime());
                pauseOffset = 0;
                chronometer.stop();
                running = false;
            }

        }
    }
}