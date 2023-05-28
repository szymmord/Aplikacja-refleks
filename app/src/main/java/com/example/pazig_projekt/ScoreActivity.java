package com.example.pazig_projekt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.graphics.Color;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;
import java.util.List;

public class ScoreActivity extends AppCompatActivity {

    ImageButton ExitStartButton;
    ImageButton SettingsButton;
    private LineChart lineChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        lineChart = findViewById(R.id.lineChart);

        List<Entry> entries = new ArrayList<>();
        entries.add(new Entry(0, 4));
        entries.add(new Entry(1, 6));
        entries.add(new Entry(2, 3));
        entries.add(new Entry(3, 8));
        entries.add(new Entry(4, 5));

        LineDataSet dataSet = new LineDataSet(entries, "Wykres liniowy");
        dataSet.setColor(Color.BLUE);
        dataSet.setValueTextColor(Color.RED);

        LineData lineData = new LineData(dataSet);

        lineChart.setData(lineData);
        lineChart.invalidate(); // Odświeżenie wykresu



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