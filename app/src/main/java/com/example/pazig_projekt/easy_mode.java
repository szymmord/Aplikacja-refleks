package com.example.pazig_projekt;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.media.Image;
import android.widget.Chronometer;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.os.SystemClock;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class easy_mode extends AppCompatActivity {

    ImageButton ExitButton;
    private Chronometer chronometer;

    ImageButton bNiebieski, bZolty, bCzerwony, bZielony, StartButtonEasy, checkButton;
    ImageButton tNiebieski, tZielony, tZolty, tCzerwony, tCzarny;
    TextView textStart3, textImie, text1,text2 ,text3,text4,podsumowanie,tMean,tMin,tMax;
    private long pauseOffset;
    private boolean running;
    int gowno;
    int jd = 1;
    FirebaseFirestore firestore,firestore2;
    EditText editTextName;
    String getName;
    long maxValue, sum ;
    long meanValue;
    long minValue=100000;
    LineChart lineChart;
    List<Entry> entries = new ArrayList<>();

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
        editTextName = (EditText) findViewById(R.id.editTextName);
        checkButton = (ImageButton) findViewById(R.id.checkButton);
        textImie = (TextView) findViewById(R.id.textImie);



        tMean=findViewById(R.id.tMean);
        tMin=findViewById(R.id.tMin);
        tMax=findViewById(R.id.tMax);
        podsumowanie = findViewById(R.id.podusmowanie);
        text1=findViewById(R.id.textView3);
        text2=findViewById(R.id.textView4);
        text3=findViewById(R.id.textView7);
        text4=findViewById(R.id.textView8);
        lineChart = findViewById(R.id.lineChart);
        lineChart.setVisibility(View.INVISIBLE);
        podsumowanie.setVisibility(View.INVISIBLE);
        tMin.setVisibility(View.INVISIBLE);
        tMax.setVisibility(View.INVISIBLE);
        tMean.setVisibility(View.INVISIBLE);





        firestore = FirebaseFirestore.getInstance();
        firestore2 = FirebaseFirestore.getInstance();

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

        checkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkButton.setVisibility(View.INVISIBLE);
                editTextName.setVisibility(View.INVISIBLE);
                textImie.setVisibility(View.INVISIBLE);
                getName = editTextName.getText().toString();
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
                firestore = FirebaseFirestore.getInstance();


                entries.add(new Entry(jd, elapsedMillis));



                Map<String, Object> wyniki = new HashMap<>();
                wyniki.put("firstName", getName);
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
                sum = elapsedMillis + sum;

                if(elapsedMillis>maxValue)
                {
                    maxValue=elapsedMillis;
                }
                if(elapsedMillis < minValue)
                {
                    minValue=elapsedMillis;
                }
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
                meanValue=sum/10;



                stworzWykres(v);



                firestore2 = FirebaseFirestore.getInstance();
                Map<String, Object> summary = new HashMap<>();
                summary.put("firstName", getName);
                summary.put("level", "1");
                summary.put("minValue", minValue);
                summary.put("maxValue", maxValue);
                summary.put("meanValue", meanValue);
                firestore2.collection("summary").add(summary).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
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


                entries.add(new Entry(jd, elapsedMillis));



                Map<String, Object> wyniki = new HashMap<>();
                wyniki.put("firstName", getName);
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

                sum = elapsedMillis + sum;
                if(elapsedMillis>maxValue)
                {
                    maxValue=elapsedMillis;
                }
                if(elapsedMillis < minValue)
                {
                    minValue=elapsedMillis;
                }

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
                meanValue=sum/10;
                firestore2 = FirebaseFirestore.getInstance();

                stworzWykres(v);


                Map<String, Object> summary = new HashMap<>();
                summary.put("firstName", getName);
                summary.put("level", "1");
                summary.put("minValue", minValue);
                summary.put("maxValue", maxValue);
                summary.put("meanValue", meanValue);
                firestore2.collection("summary").add(summary).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
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



                entries.add(new Entry(jd, elapsedMillis));




                Map<String, Object> wyniki = new HashMap<>();
                wyniki.put("firstName", getName);
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
                sum = elapsedMillis + sum;

                if(elapsedMillis>maxValue)
                {
                    maxValue=elapsedMillis;
                }
                if(elapsedMillis < minValue)
                {
                    minValue=elapsedMillis;
                }

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
                meanValue=sum/10;

                stworzWykres(v);



                firestore2 = FirebaseFirestore.getInstance();
                Map<String, Object> summary = new HashMap<>();
                summary.put("firstName", getName);
                summary.put("level", "1");
                summary.put("minValue", minValue);
                summary.put("maxValue", maxValue);
                summary.put("meanValue", meanValue);
                firestore2.collection("summary").add(summary).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
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



                entries.add(new Entry(jd, elapsedMillis));





                Map<String, Object> wyniki = new HashMap<>();
                wyniki.put("firstName", getName);
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
                sum = elapsedMillis + sum;

                if(elapsedMillis>maxValue)
                {
                    maxValue=elapsedMillis;
                }
                if(elapsedMillis < minValue)
                {
                    minValue=elapsedMillis;
                }

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
                meanValue=sum/10;

                stworzWykres(v);


                firestore2 = FirebaseFirestore.getInstance();
                Map<String, Object> summary = new HashMap<>();
                summary.put("firstName", getName);
                summary.put("level", "1");
                summary.put("minValue", minValue);
                summary.put("maxValue", maxValue);
                summary.put("meanValue", meanValue);
                firestore2.collection("summary").add(summary).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
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
            }

        }
    }
    public void stworzWykres(View v)
    {
        tNiebieski.setVisibility(View.INVISIBLE);
        tZolty.setVisibility(View.INVISIBLE);
        tCzerwony.setVisibility(View.INVISIBLE);
        tZielony.setVisibility(View.INVISIBLE);
        tCzarny.setVisibility(View.INVISIBLE);
        bNiebieski.setVisibility(View.INVISIBLE);
        bZolty.setVisibility(View.INVISIBLE);
        bCzerwony.setVisibility(View.INVISIBLE);
        bZielony.setVisibility(View.INVISIBLE);
        text1.setVisibility(View.INVISIBLE);
        text2.setVisibility(View.INVISIBLE);
        text3.setVisibility(View.INVISIBLE);
        text4.setVisibility(View.INVISIBLE);
        chronometer.setVisibility(View.INVISIBLE);

        podsumowanie.setVisibility(View.VISIBLE);
        lineChart.setVisibility(View.VISIBLE);
        tMin.setVisibility(View.VISIBLE);
        tMax.setVisibility(View.VISIBLE);
        tMean.setVisibility(View.VISIBLE);
        String srednia = "Srednia: " + meanValue+"ms";
        String min = "Najlepszy wynik: " + minValue+"ms";
        String max = "Najgorszy wynik: " + maxValue+"ms";

        tMean.setText(srednia);
        tMin.setText(min);
        tMax.setText(max);

        LineDataSet dataSet = new LineDataSet(entries, "Wykres liniowy");
        dataSet.setColor(Color.BLUE);
        dataSet.setValueTextColor(Color.RED);

        LineData lineData = new LineData(dataSet);

        lineChart.setData(lineData);
        lineChart.invalidate(); // Odświeżenie wykresu
    }

}