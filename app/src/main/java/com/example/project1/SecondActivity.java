package com.example.project1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {
    TextView resultSummary;
    ProgressBar progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTitle("Results");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        resultSummary = findViewById(R.id.textViewResults);
        progress = findViewById(R.id.progressBar);
        String requestResults = getIntent().getExtras().getString("results");
        float accuracy = getIntent().getExtras().getFloat("accuracy");
        resultSummary.setText(requestResults);
        progress.setProgress((int) accuracy);
    }
}
