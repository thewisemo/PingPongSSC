package com.example.android.pingpongssc;

/**
 * Created by wisemo on 1/30/2018.
 * * (Score) was written to keep score tracking for both Left & Right players
 * represents a single Match release.
 * Each object has 3 properties: player score, player Match score.
 */

public class MatchScore {
    // States
    // Player Match score
    private Integer mPlayerMatchScore;

    // Public constructor for the word
    public MatchScore(Integer playerMatchScore) {
        mPlayerMatchScore = playerMatchScore;
    }

    // Get the Player score method
    public Integer getScore() {
        return mPlayerMatchScore;
    }

    // public methods on Score
    // Set the Player score method
    public void setScore(int playerScore) {
        mPlayerMatchScore = playerScore;
    }

}
