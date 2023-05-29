package com.example.pazig_projekt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class ScoreActivity extends AppCompatActivity {

    ImageButton ExitStartButton;
    ImageButton SettingsButton;
    private LineChart lineChart;
    ImageButton checkName;
    EditText name;
    TextView tMean,tMin,tMax;
    String getName;
    FirebaseFirestore firestore;
    List<Object> valueList= new ArrayList<>();
    
   float max,mean;
    float min = 1000000;
    int iteracje = 0;
    float sum;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);


        lineChart = findViewById(R.id.lineChart);
        checkName=findViewById(R.id.checkButton);
        name= findViewById(R.id.editTextName);
        tMean=findViewById(R.id.tMean);
        tMin=findViewById(R.id.tMin);
        tMax=findViewById(R.id.tMax);
        tMax.setVisibility(View.INVISIBLE);
        tMin.setVisibility(View.INVISIBLE);
        tMean.setVisibility(View.INVISIBLE);

        firestore = FirebaseFirestore.getInstance();










        ExitStartButton = (ImageButton) findViewById(R.id.ExitMainSButton) ;
        SettingsButton = (ImageButton) findViewById(R.id.SettingsButton4);
        checkName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkName.setVisibility(View.INVISIBLE);
                name.setVisibility(View.INVISIBLE);

                getName = name.getText().toString();
                lineChart.setVisibility(View.VISIBLE);
                tMax.setVisibility(View.VISIBLE);
                tMin.setVisibility(View.VISIBLE);
                tMean.setVisibility(View.VISIBLE);
                PobierzDane(view);
                PobierzDane2(view);

            }
        });

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


    public void PobierzDane(View v)
    {

        firestore.collection("wyniki")
                .whereEqualTo("firstName", getName)
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    List<DocumentSnapshot> documentList = queryDocumentSnapshots.getDocuments();


                    for (DocumentSnapshot document : documentList) {
                        Object value = document.get("wynik");
                        valueList.add(value);
                    }
                    List<Entry> entries = new ArrayList<>();
                    for (int i = 0; i < valueList.size(); i++) {
                        float value = Float.parseFloat(valueList.get(i).toString());
                        entries.add(new Entry(i, value));
                    }

// Utwórz zestaw danych dla wykresu
                    LineDataSet dataSet = new LineDataSet(entries, "Wykres");
                    dataSet.setColor(Color.BLUE);
                    dataSet.setValueTextColor(Color.RED);

                    LineData lineData = new LineData(dataSet);

// Konfiguruj wykres
                    lineChart.setData(lineData);
                    lineChart.invalidate();

                    // Obsłuż pobrane wartości "wynik" tutaj
                    // valueList zawiera pobrane wartości pola "wynik" dla dokumentów pasujących do zapytania
                })
                .addOnFailureListener(e -> {
                    // Obsłuż błąd tutaj
                });

    }
    public void PobierzDane2(View v)
    {
        firestore.collection("wyniki")
                .whereEqualTo("firstName", getName)
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    List<DocumentSnapshot> documentList = queryDocumentSnapshots.getDocuments();


                    for (DocumentSnapshot document : documentList) {
                        Object value = document.get("wynik");
                        valueList.add(value);


                    }
                    for (int i = 0; i < valueList.size(); i++) {
                        iteracje = iteracje+1;

                        float value = Float.parseFloat(valueList.get(i).toString());
                        if(value<min)
                        {
                            min=value;

                        }
                        if (value>max)
                        {
                            max=value;
                        }
                        sum = sum +value;

                    }
                    mean=sum/iteracje;
                    String sMean = "Srednia: " + mean+"ms";
                    String sMin = "Najlepszy wynik: " + min+"ms";
                    String sMax = "Najgorszy wynik: " + max+"ms";


                    tMean.setText(sMean);
                    tMin.setText(sMin);
                    tMax.setText(sMax);


                    // Obsłuż pobrane wartości "wynik" tutaj
                    // valueList zawiera pobrane wartości pola "wynik" dla dokumentów pasujących do zapytania
                })
                .addOnFailureListener(e -> {
                    // Obsłuż błąd tutaj
                });
    }

}
