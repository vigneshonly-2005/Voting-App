package com.example.vapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class VAppVotePollActivity extends AppCompatActivity {

    private EditText pollCodeEditText;
    private RadioButton option1RadioButton, option2RadioButton;
    private Button voteButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vote_poll);

        pollCodeEditText = findViewById(R.id.pollCodeEditText);
        option1RadioButton = findViewById(R.id.option1RadioButton);
        option2RadioButton = findViewById(R.id.option2RadioButton);
        voteButton = findViewById(R.id.voteButton);

        voteButton.setOnClickListener(view -> {
            String pollCode = pollCodeEditText.getText().toString().trim();

            // Retrieve the PollManager instance and get the poll using the pollCode
            Poll poll = PollManager.getInstance().getPoll(pollCode);

            if (poll != null) {
                // Check which radio button is selected and add a vote accordingly
                if (option1RadioButton.isChecked()) {
                    PollManager.getInstance().addVote(pollCode, 1);
                } else if (option2RadioButton.isChecked()) {
                    PollManager.getInstance().addVote(pollCode, 2);
                } else {
                    Toast.makeText(this, "Please select an option", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Proceed to the result activity to show poll results
                Intent intent = new Intent(VAppVotePollActivity.this, VAppResultActivity.class);
                intent.putExtra("pollCode", pollCode);
                startActivity(intent);
            } else {
                Toast.makeText(this, "Invalid poll code", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
