package com.example.vapp;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;

public class VAppAdminActivity extends AppCompatActivity {

    private ListView pollListView;
    private Button deletePollButton;
    private ArrayAdapter<String> pollAdapter;
    private List<String> pollCodes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        pollListView = findViewById(R.id.pollListView);
        deletePollButton = findViewById(R.id.deletePollButton);

        // Retrieve poll codes from PollManager instance
        pollCodes = new ArrayList<>(PollManager.getInstance().getAllPollCodes());

        pollAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_single_choice, pollCodes);
        pollListView.setAdapter(pollAdapter);
        pollListView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        deletePollButton.setOnClickListener(v -> {
            int selectedPosition = pollListView.getCheckedItemPosition();
            if (selectedPosition != ListView.INVALID_POSITION) {
                String pollCode = pollCodes.get(selectedPosition);
                if (PollManager.getInstance().deletePoll(pollCode)) {
                    pollCodes.remove(selectedPosition);
                    pollAdapter.notifyDataSetChanged();
                    Toast.makeText(VAppAdminActivity.this, "Poll with code " + pollCode + " deleted", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(VAppAdminActivity.this, "Failed to delete poll", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(VAppAdminActivity.this, "Select a poll to delete", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
