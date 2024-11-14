package com.example.votingapp;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ResultActivity extends AppCompatActivity {

    private TextView pollTopicTextView, resultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        pollTopicTextView = findViewById(R.id.pollTopicTextView);
        resultTextView = findViewById(R.id.resultTextView);

        // Retrieve data passed from VotePollActivity
        SharedPreferences sharedPreferences = getSharedPreferences("PollData", MODE_PRIVATE);

        String pollTopic = sharedPreferences.getString("pollTopic", "No Topic");
        int numberOfOptions = sharedPreferences.getInt("numberOfOptions", 0);

        // Display poll topic
        pollTopicTextView.setText(pollTopic);

        // Calculate and display voting results
        StringBuilder resultText = new StringBuilder();
        int totalVotes = 0;
        double[] optionPercentages = new double[numberOfOptions];

        // Sum up the total votes
        for (int i = 0; i < numberOfOptions; i++) {
            int votes = sharedPreferences.getInt("option" + (i + 1) + "Votes", 0);
            totalVotes += votes;
        }

        // Calculate percentages
        for (int i = 0; i < numberOfOptions; i++) {
            String option = sharedPreferences.getString("option" + (i + 1), "Option " + (i + 1));
            int votes = sharedPreferences.getInt("option" + (i + 1) + "Votes", 0);
            double percentage = totalVotes > 0 ? (votes * 100.0) / totalVotes : 0;
            resultText.append(option).append(": ").append(votes).append(" votes (").append(String.format("%.2f", percentage)).append("%)\n");
        }

        // Display the result
        resultTextView.setText(resultText.toString());
    }
}
