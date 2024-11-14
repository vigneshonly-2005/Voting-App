package com.example.vapp;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

public class PollManager {
    private static PollManager instance;
    private Map<String, Poll> polls;

    private PollManager() {
        polls = new HashMap<>();
    }

    public static synchronized PollManager getInstance() {
        if (instance == null) {
            instance = new PollManager();
        }
        return instance;
    }

    public String createPoll(String topic, String option1, String option2) {
        String code = generateUniqueCode();
        polls.put(code, new Poll(topic, option1, option2));
        return code;
    }

    public Poll getPoll(String code) {
        return polls.get(code);
    }

    public boolean deletePoll(String code) {
        if (polls.containsKey(code)) {
            polls.remove(code);
            return true;
        }
        return false;
    }

    public boolean addVote(String code, int optionIndex) {
        Poll poll = polls.get(code);
        if (poll != null) {
            if (optionIndex == 1) {
                poll.incrementOption1Votes();
            } else if (optionIndex == 2) {
                poll.incrementOption2Votes();
            }
            return true;
        }
        return false;
    }

    public Set<String> getAllPollCodes() {
        return polls.keySet();
    }

    private String generateUniqueCode() {
        return UUID.randomUUID().toString().substring(0, 5).toUpperCase();
    }
}
