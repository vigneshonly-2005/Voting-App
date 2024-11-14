package com.example.votingapp;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class VotePollActivity extends AppCompatActivity {

    private EditText pollCodeEditText;
    private Button submitCodeButton, voteButton;
    private TextView pollTopicTextView;
    private RadioGroup optionsRadioGroup;
    private List<RadioButton> optionRadioButtons = new ArrayList<>();

    private int[] optionVotes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vote_poll);

        pollCodeEditText = findViewById(R.id.pollCodeEditText);
        submitCodeButton = findViewById(R.id.submitCodeButton);
        voteButton = findViewById(R.id.voteButton);
        pollTopicTextView = findViewById(R.id.pollTopicTextView);
        optionsRadioGroup = findViewById(R.id.optionsRadioGroup);

        submitCodeButton.setOnClickListener(view -> checkPollCode());
        voteButton.setOnClickListener(view -> submitVote());
    }

    private void checkPollCode() {
        String enteredCode = pollCodeEditText.getText().toString().trim();
        SharedPreferences sharedPreferences = getSharedPreferences("PollData", MODE_PRIVATE);

        String savedCode = sharedPreferences.getString("pollCode", "");
        if (enteredCode.equals(savedCode)) {
            String topic = sharedPreferences.getString("pollTopic", "Topic not found");
            int numberOfOptions = sharedPreferences.getInt("numberOfOptions", 0);

            pollTopicTextView.setText(topic);
            pollTopicTextView.setVisibility(View.VISIBLE);

            optionsRadioGroup.removeAllViews();
            optionRadioButtons.clear();
            optionVotes = new int[numberOfOptions];

            for (int i = 0; i < numberOfOptions; i++) {
                String option = sharedPreferences.getString("option" + (i + 1), "Option " + (i + 1));
                RadioButton radioButton = new RadioButton(this);
                radioButton.setText(option);
                optionRadioButtons.add(radioButton);
                optionsRadioGroup.addView(radioButton);

                optionVotes[i] = sharedPreferences.getInt("option" + (i + 1) + "Votes", 0);
            }

            optionsRadioGroup.setVisibility(View.VISIBLE);
            voteButton.setVisibility(View.VISIBLE);
        } else {
            Toast.makeText(this, "Invalid Poll Code", Toast.LENGTH_SHORT).show();
        }
    }

    private void submitVote() {
        int selectedOptionId = optionsRadioGroup.getCheckedRadioButtonId();
        if (selectedOptionId == -1) {
            Toast.makeText(this, "Please select an option to vote", Toast.LENGTH_SHORT).show();
            return;
        }

        SharedPreferences sharedPreferences = getSharedPreferences("PollData", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        for (int i = 0; i < optionRadioButtons.size(); i++) {
            if (optionRadioButtons.get(i).getId() == selectedOptionId) {
                optionVotes[i]++;
                editor.putInt("option" + (i + 1) + "Votes", optionVotes[i]);
                break;
            }
        }
        editor.apply();
        Toast.makeText(this, "Vote submitted successfully!", Toast.LENGTH_SHORT).show();

        showResults();
    }

    private void showResults() {
        // Display the results in a new activity or update UI
    }
}
