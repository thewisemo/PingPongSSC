package com.example.android.pingpongssc;

import android.widget.TextView;

/**
 * Created by wisemo on 1/30/2018.
 * * (Score) was written to keep score tracking for both Left & Right players
 * represents a single Match release.
 * Each object has 3 properties: player score, player Match score.
 */

public class GameScore {
    // Static global int values
    private static int winingGameScore = 2;
    private static int winingBestScore = 2;
    private static int winingScore = 5;

    private int bestNumberOne, bestNumberTwo, bestNumberThree;

    public class NewBest {
        int mMatchNumberOne, mMatchNumberTwo, mMatchNumberThree;
        TextView mMatchNumberOneTV, mMatchNumberTwoTV, mMatchNumberThreeTV;

        // The constructor for the best objects in the BestsArrayList
        public NewBest(int playerMatchNumberOne, int playerMatchNumberTwo, int playerMatchNumberThree) {
            mMatchNumberOne = playerMatchNumberOne;
            mMatchNumberTwo = playerMatchNumberTwo;
            mMatchNumberThree = playerMatchNumberThree;
        }

        public void isWonBest(int playerMatchOneScore, int playerMatchTwoScore, int playerMatchThreeScore) {
            for (int M = 0; M == winingBestScore; M++) {
//                    MatchScore m1 = new MatchScore();

            }
        }
    }

    public class NewMatch {
        int mPlayerCurrentScore, mPlayerMatchScore;
        TextView mTxtOfCurrentScore, mTxtOfMatchScore;

        // The constructor for the match objects in the MatchesArrayList
        public NewMatch(int playerSideScore, int playerMatchNumberScore, TextView sideCurrentScore, TextView playerMatchScoreView) {
            mPlayerCurrentScore = playerSideScore;
            mPlayerMatchScore = playerMatchNumberScore;
            mTxtOfCurrentScore = sideCurrentScore;
            mTxtOfMatchScore = playerMatchScoreView;
        }

        // The increment "with one point" method to update player score AND display it on the right places.
        public void incSore(int playerCurrentScore, int playerMatchNumberScore, TextView txtOfSideScore, TextView txtOfMatchScore) {
            for (int p = 0; p < winingScore; p++) {
                playerCurrentScore = ++playerCurrentScore;
                playerMatchNumberScore = ++playerMatchNumberScore;
                txtOfSideScore.setText(String.valueOf(playerCurrentScore));
                txtOfMatchScore.setText(String.valueOf(playerMatchNumberScore));
            }
        }

        // Get match score
        public int getPlayerMatchScore(int playerMatchScore) {
            return mPlayerMatchScore;
        }

        // Set Match score
        public void setPlayerMatchScore(int matchNumber) {
            mPlayerMatchScore = matchNumber;
        }
    }
}
