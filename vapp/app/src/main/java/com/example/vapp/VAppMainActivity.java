package com.example.vapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class VAppMainActivity extends AppCompatActivity {

    private EditText usernameEditText;
    private Button createPollButton, votePollButton, adminPortalButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usernameEditText = findViewById(R.id.usernameEditText);
        createPollButton = findViewById(R.id.createPollButton);
        votePollButton = findViewById(R.id.votePollButton);
        adminPortalButton = findViewById(R.id.adminPortalButton);

        createPollButton.setOnClickListener(view -> {
            String username = usernameEditText.getText().toString().trim();
            if (!username.isEmpty()) {
                Intent intent = new Intent(VAppMainActivity.this, VAppCreatePollActivity.class);
                intent.putExtra("username", username);
                startActivity(intent);
            } else {
                Toast.makeText(this, "Please enter a username", Toast.LENGTH_SHORT).show();
            }
        });

        votePollButton.setOnClickListener(view -> {
            Intent intent = new Intent(VAppMainActivity.this, VAppVotePollActivity.class);
            startActivity(intent);
        });

        adminPortalButton.setOnClickListener(view -> {
            Intent intent = new Intent(VAppMainActivity.this, VAppAdminActivity.class);
            startActivity(intent);
        });
    }
}
