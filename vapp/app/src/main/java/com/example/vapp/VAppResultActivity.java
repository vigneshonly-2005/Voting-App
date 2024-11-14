package com.example.vapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class VAppResultActivity extends AppCompatActivity {

    private TextView resultTopicTextView, option1ResultTextView, option2ResultTextView;
    private Button backToMainButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        resultTopicTextView = findViewById(R.id.resultTopicTextView);
        option1ResultTextView = findViewById(R.id.option1ResultTextView);
        option2ResultTextView = findViewById(R.id.option2ResultTextView);
        backToMainButton = findViewById(R.id.backToMainButton);

        // Retrieve the poll code from the Intent
        String pollCode = getIntent().getStringExtra("pollCode");

        // Access the PollManager instance and retrieve the poll
        Poll poll = PollManager.getInstance().getPoll(pollCode);

        if (poll != null) {
            // Display the poll topic and votes for each option
            resultTopicTextView.setText("Results for: " + poll.getTopic());
            option1ResultTextView.setText(poll.getOption1() + ": " + poll.getOption1Votes() + " votes");
            option2ResultTextView.setText(poll.getOption2() + ": " + poll.getOption2Votes() + " votes");
        } else {
            // If poll is null, display a message
            resultTopicTextView.setText("Poll not found.");
        }

        // Set up button to go back to the main activity
        backToMainButton.setOnClickListener(v -> {
            Intent mainIntent = new Intent(VAppResultActivity.this, VAppMainActivity.class);
            startActivity(mainIntent);
            finish();
        });
    }
}
