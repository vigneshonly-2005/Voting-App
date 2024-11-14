package com.example.votingapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button createPollButton, votePollButton, viewResultsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize the buttons
        createPollButton = findViewById(R.id.createPollButton);
        votePollButton = findViewById(R.id.votePollButton);
        viewResultsButton = findViewById(R.id.viewResultsButton);

        // Set click listeners for each button
        createPollButton.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, CreatePollActivity.class);
            startActivity(intent);
        });

        votePollButton.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, VotePollActivity.class);
            startActivity(intent);
        });

        viewResultsButton.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, ResultActivity.class);
            startActivity(intent);
        });
    }
}
