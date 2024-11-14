package com.example.vapp; // Replace with your package name

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class VAppCreatePollActivity extends AppCompatActivity {

    private EditText topicEditText, option1EditText, option2EditText;
    private Button createPollButton;
    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_poll);

        // Initialize UI components
        topicEditText = findViewById(R.id.topicEditText);
        option1EditText = findViewById(R.id.option1EditText);
        option2EditText = findViewById(R.id.option2EditText);
        createPollButton = findViewById(R.id.createPollButton);

        // Get the username passed from MainActivity
        username = getIntent().getStringExtra("username");

        // Button click listener to create the poll
        createPollButton.setOnClickListener(v -> {
            String topic = topicEditText.getText().toString().trim();
            String option1 = option1EditText.getText().toString().trim();
            String option2 = option2EditText.getText().toString().trim();

            // Check if all fields are filled
            if (!topic.isEmpty() && !option1.isEmpty() && !option2.isEmpty()) {
                // Generate a unique 5-digit poll code
                String pollCode = generatePollCode();

                // Create a new Poll object
                Poll newPoll = new Poll(pollCode, topic, option1, option2);

                // Store the poll (for now, we are just showing a Toast message)
                // You can store this poll in SharedPreferences, a file, or an in-memory list if needed
                Toast.makeText(this, "Poll Created! Code: " + pollCode, Toast.LENGTH_SHORT).show();

                // Optionally, you can send the poll code to another activity to show poll results or allow voting
                Intent resultIntent = new Intent(VAppCreatePollActivity.this, VAppResultActivity.class);
                resultIntent.putExtra("pollCode", pollCode); // Pass the poll code to ResultActivity
                startActivity(resultIntent);
            } else {
                // Show a message if any field is empty
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Method to generate a random 5-digit poll code
    private String generatePollCode() {
        return String.format("%05d", new Random().nextInt(100000));
    }

    // Poll class to hold poll details
    public static class Poll {
        private String pollCode;
        private String topic;
        private String option1;
        private String option2;

        // Constructor
        public Poll(String pollCode, String topic, String option1, String option2) {
            this.pollCode = pollCode;
            this.topic = topic;
            this.option1 = option1;
            this.option2 = option2;
        }

        // Getters and setters
        public String getPollCode() {
            return pollCode;
        }

        public void setPollCode(String pollCode) {
            this.pollCode = pollCode;
        }

        public String getTopic() {
            return topic;
        }

        public void setTopic(String topic) {
            this.topic = topic;
        }

        public String getOption1() {
            return option1;
        }

        public void setOption1(String option1) {
            this.option1 = option1;
        }

        public String getOption2() {
            return option2;
        }

        public void setOption2(String option2) {
            this.option2 = option2;
        }
    }
}
