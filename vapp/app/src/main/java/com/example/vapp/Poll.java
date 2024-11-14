// Poll.java
package com.example.vapp;

public class Poll {
    private String topic;
    private String option1;
    private String option2;
    private int option1Votes;
    private int option2Votes;

    public Poll(String topic, String option1, String option2) {
        this.topic = topic;
        this.option1 = option1;
        this.option2 = option2;
        this.option1Votes = 0;
        this.option2Votes = 0;
    }

    public String getTopic() {
        return topic;
    }

    public String getOption1() {
        return option1;
    }

    public String getOption2() {
        return option2;
    }

    public int getOption1Votes() {
        return option1Votes;
    }

    public int getOption2Votes() {
        return option2Votes;
    }

    public void incrementOption1Votes() {
        option1Votes++;
    }

    public void incrementOption2Votes() {
        option2Votes++;
    }
}
