package com.example.android.pingpongssc;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {


    int leftTeamScore;
    int rightTeamScore;

    CheckBox leftFirst;
    CheckBox leftSecond;
    CheckBox leftThird;
    CheckBox rightFirst;
    CheckBox rightSecond;
    CheckBox rightThird;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**
         * To import data from the 2 EditText in the activity_teams_names
         */
        TextView rightTeamName = findViewById(R.id.rightTeamName);
        TextView leftTeamName = findViewById(R.id.leftTeamName);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {

            String rightTeam = bundle.getString("RIGHT");
            String leftTeam = bundle.getString("LEFT");

            rightTeamName.setText(rightTeam);
            leftTeamName.setText(leftTeam);
        }
        leftFirst = findViewById(R.id.leftTeamMOne);
        leftSecond = findViewById(R.id.leftTeamMTwo);
        leftThird = findViewById(R.id.leftTeamMThree);
        rightFirst = findViewById(R.id.rightTeamMOne);
        rightSecond = findViewById(R.id.rightTeamMTwo);
        rightThird = findViewById(R.id.rightTeamMThree);
    }
    /**
     * When the + for left team points is clicked
     */
    public void incLeftOne(View view) {

        leftTeamScore = leftTeamScore + 1;
        displayForLeft(leftTeamScore);

        if (leftTeamScore == 3) {
            leftTeamScore = 0;
            rightTeamScore = 0;
            displayForLeft(leftTeamScore);
            displayForRight(rightTeamScore);
            //make 1st Match left checkbox ischecked
            leftFirst.setChecked(!leftFirst.isChecked());
        } else if (leftFirst.isChecked() || leftTeamScore == 3) {
            leftTeamScore = 0;
            rightTeamScore = 0;
            displayForLeft(leftTeamScore);
            displayForRight(rightTeamScore);
            //make 1st Match left checkbox ischecked
            leftFirst.setChecked(!leftFirst.isChecked());
            //make 1st Match left checkbox ischecked
            leftSecond.setChecked(!leftSecond.isChecked());
        }
    }
    /**
     * When the -1 for left player/team score is clicked
     */
    public void decLeftOne(View view) {
        if (leftTeamScore == 0) {
            // Show an error message as a toast
            Toast.makeText(this, "You cannot have less than 0 point", Toast.LENGTH_SHORT).show();
            // Exit this method early because there's nothing left to do
            return;
        }
        leftTeamScore = leftTeamScore - 1;
        displayForLeft(leftTeamScore);
    }


    /**
     * When the + for right player/team score is clicked
     */
    public void incRightOne(View view) {
        rightTeamScore = rightTeamScore + 1;
        displayForRight(rightTeamScore);
        if (rightTeamScore == 3) {
            //make 1st match checkbox selected then reset
            rightFirst.setChecked(!rightFirst.isChecked());
            rightTeamScore = 0;
            leftTeamScore = 0;
            displayForLeft(rightTeamScore);
            displayForRight(leftTeamScore);
        }
    }

    /**
     * When the 2 points clicked
     */
    /*public void twoPointsB(View view) {
        rightTeamScore = rightTeamScore + 2;
        displayForRight(rightTeamScore);
    }

    /**
     * When the - for right player/team score is clicked
     */
    public void decRightOne(View view) {
        if (rightTeamScore == 0) {
            // Show an error message as a toast
            Toast.makeText(this, "You cannot have less than 0 point", Toast.LENGTH_SHORT).show();
            // Exit this method early because there's nothing left to do
            return;
        }
        rightTeamScore = rightTeamScore - 1;
        displayForRight(rightTeamScore);
    }

    /**
     * When the reset button clicked
     */
    public void reset(View view) {
        leftTeamScore = 0;
        rightTeamScore = 0;
        displayForLeft(leftTeamScore);
        displayForRight(rightTeamScore);
    }

    /**
     * Displays the given score for Left
     */
    public void displayForLeft(int score) {
        TextView scoreView = findViewById(R.id.left_score);
        scoreView.setText(String.valueOf(score));
    }

    /**
     * Displays the given score for Right
     */
    public void displayForRight(int score) {
        TextView scoreView = findViewById(R.id.right_score);
        scoreView.setText(String.valueOf(score));
    }
}
