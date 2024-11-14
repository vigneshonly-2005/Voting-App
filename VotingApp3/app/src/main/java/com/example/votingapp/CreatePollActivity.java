package com.example.votingapp;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CreatePollActivity extends AppCompatActivity {

    private EditText pollTopicEditText, numberOfOptionsEditText;
    private Button createPollButton;
    private LinearLayout optionsContainer;
    private List<EditText> optionEditTexts = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_poll);

        pollTopicEditText = findViewById(R.id.pollTopicEditText);
        numberOfOptionsEditText = findViewById(R.id.numberOfOptionsEditText);
        createPollButton = findViewById(R.id.createPollButton);
        optionsContainer = findViewById(R.id.optionsContainer);

        createPollButton.setOnClickListener(view -> createPoll());
    }

    private void createPoll() {
        String pollTopic = pollTopicEditText.getText().toString().trim();
        String numberOfOptionsStr = numberOfOptionsEditText.getText().toString().trim();
        if (pollTopic.isEmpty() || numberOfOptionsStr.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        int numberOfOptions = Integer.parseInt(numberOfOptionsStr);
        if (numberOfOptions <= 1) {
            Toast.makeText(this, "Please enter at least 2 options", Toast.LENGTH_SHORT).show();
            return;
        }

        // Generate options EditTexts
        optionsContainer.removeAllViews();
        optionEditTexts.clear();
        for (int i = 0; i < numberOfOptions; i++) {
            EditText optionEditText = new EditText(this);
            optionEditText.setHint("Option " + (i + 1));
            optionEditTexts.add(optionEditText);
            optionsContainer.addView(optionEditText);
        }

        // Save poll data
        createPollButton.setText("Save Poll");
        createPollButton.setOnClickListener(view -> savePoll(pollTopic, numberOfOptions));
    }

    private void savePoll(String pollTopic, int numberOfOptions) {
        // Generate a random poll code
        String pollCode = generateRandomCode();

        // Save poll data
        SharedPreferences sharedPreferences = getSharedPreferences("PollData", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("pollCode", pollCode);
        editor.putString("pollTopic", pollTopic);
        editor.putInt("numberOfOptions", numberOfOptions);

        // Save each option
        for (int i = 0; i < numberOfOptions; i++) {
            String option = optionEditTexts.get(i).getText().toString().trim();
            editor.putString("option" + (i + 1), option);
            editor.putInt("option" + (i + 1) + "Votes", 0);  // Initial vote count
        }

        editor.apply();
        Toast.makeText(this, "Poll Created! Code: " + pollCode, Toast.LENGTH_SHORT).show();
    }

    private String generateRandomCode() {
        Random random = new Random();
        int code = 10000 + random.nextInt(90000);
        return String.valueOf(code);
    }
}
