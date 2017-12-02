package com.example.android.pingpongssc;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.pingpongssc.R;

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


        leftFirst = (CheckBox) findViewById(R.id.leftTeamMOne);
        leftSecond = (CheckBox) findViewById(R.id.leftTeamMTwo);
        leftThird = (CheckBox) findViewById(R.id.leftTeamMThree);

        rightFirst = (CheckBox) findViewById(R.id.rightTeamMOne);
        rightSecond = (CheckBox) findViewById(R.id.rightTeamMTwo);
        rightThird = (CheckBox) findViewById(R.id.rightTeamMThree);

    }



    /**
     * When the + for left team points is clicked
     */
    public void incLeftOne(View view) {
        leftTeamScore = leftTeamScore + 1;
        displayForLeft(leftTeamScore);
        if (leftTeamScore == 3) {

            //make 1st Match checkbox selected and reset
            leftFirst.setChecked(!leftFirst.isChecked());
            leftTeamScore = 0;
            rightTeamScore = 0;
            displayForLeft(leftTeamScore);
            displayForRight(rightTeamScore);

            /**
             * What should i do to keep score tracking then set 2nd and 3rd match to select statment !!!
             */
        }
        else {
            if (leftTeamScore == 3 || leftFirst.isChecked()){
                //make 2nd match checkbox checked and then reset
                leftSecond.setChecked(!leftSecond.isChecked());
                leftTeamScore = 0;
                rightTeamScore = 0;
                displayForLeft(leftTeamScore);
                displayForRight(rightTeamScore);
            }
            else {
                if (leftTeamScore == 3 || leftFirst.isChecked() || leftSecond.isChecked()){
                    //make 3rd match checkbox checked and then reset
                    leftThird.setChecked(!leftThird.isChecked());
                    leftTeamScore = 0;
                    rightTeamScore = 0;
                    displayForLeft(leftTeamScore);
                    displayForRight(rightTeamScore);
                }
            }
        }


    }

    /**
     * When the 2 points clicked
     */
    /*public void twoPoints(View view) {
        teamAScore = teamAScore + 2;
        displayForLeft(teamAScore);
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