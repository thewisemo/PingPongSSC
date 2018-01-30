package com.example.android.pingpongssc;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class Standings extends Activity {
    // Names TextViews Global Variables &
    TextView StandLeftName, StandRightName,
    // Total won bests TextViews Global Variables
    TotalLeftBestsTxt, TotalRightBestsTxt,
    // First Best Matches Scores TextViews Global Variables
    LeftBestOneMatchOne, RightBestOneMatchOne,
            LeftBestOneMatchTwo, RightBestOneMatchTwo,
            LeftBestOneMatchThree, RightBestOneMatchThree,
    // Second Best Matches Scores TextViews Global Variables
    LeftBestTwoMatchOne, RightBestTwoMatchOne,
            LeftBestTwoMatchTwo, RightBestTwoMatchTwo,
            LeftBestTwoMatchThree, RightBestTwoMatchThree,
    // Third Best Matches Scores TextViews Global Variables
    LeftBestThreeMatchOne, RightBestThreeMatchOne,
            LeftBestThreeMatchTwo, RightBestThreeMatchTwo,
            LeftBestThreeMatchThree, RightBestThreeMatchThree;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.standings_popup);
        //Assigns textViews public variables to IDs in standings_popup.xml
        StandLeftName = findViewById(R.id.stand_left_team_name);
        //To be sure that the Name will scroll horizontally repetitively
        findViewById(R.id.stand_left_team_name).setSelected(true);
        StandRightName = findViewById(R.id.stand_right_team_name);
        //To be sure that the Name will scroll horizontally repetitively
        findViewById(R.id.stand_right_team_name).setSelected(true);

        // Assigns Total won bests textViews
        TotalLeftBestsTxt = findViewById(R.id.left_bests_won);
        TotalRightBestsTxt = findViewById(R.id.right_bests_won);
        //Assigns Left & Right "1st Best Matches Scores textViews public variables to IDs in standings_popup.xml
        LeftBestOneMatchOne = findViewById(R.id.left_best_one_first_txt);
        RightBestOneMatchOne = findViewById(R.id.right_best_one_first_txt);
        LeftBestOneMatchTwo = findViewById(R.id.left_best_one_second_txt);
        RightBestOneMatchTwo = findViewById(R.id.right_best_one_second_txt);
        LeftBestOneMatchThree = findViewById(R.id.left_best_one_third_txt);
        RightBestOneMatchThree = findViewById(R.id.right_best_one_third_txt);
        //Assigns Left & Right "2nd Best Matches Scores textViews public variables to IDs in standings_popup.xml
        LeftBestTwoMatchOne = findViewById(R.id.left_best_two_first_txt);
        RightBestTwoMatchOne = findViewById(R.id.right_best_two_first_txt);
        LeftBestTwoMatchTwo = findViewById(R.id.left_best_two_second_txt);
        RightBestTwoMatchTwo = findViewById(R.id.right_best_two_second_txt);
        LeftBestTwoMatchThree = findViewById(R.id.left_best_two_third_txt);
        RightBestTwoMatchThree = findViewById(R.id.right_best_two_third_txt);
        //Assigns Left & Right "3rd Best Matches Scores textViews public variables to IDs in standings_popup.xml
        LeftBestThreeMatchOne = findViewById(R.id.left_best_three_first_txt);
        RightBestThreeMatchOne = findViewById(R.id.right_best_three_first_txt);
        LeftBestThreeMatchTwo = findViewById(R.id.left_best_three_second_txt);
        RightBestThreeMatchTwo = findViewById(R.id.right_best_three_second_txt);
        LeftBestThreeMatchThree = findViewById(R.id.left_best_three_third_txt);
        RightBestThreeMatchThree = findViewById(R.id.right_best_three_third_txt);

        //Get passed data from UserInputNames class & CountingActvity class
        Bundle bundle = getIntent().getExtras();
        //To import data from the 2 EditText in the activity_teams_names
        if (bundle != null) {
            String leftTeamS = bundle.getString("LEFTNAMESTAND");
            String rightTeamS = bundle.getString("RIGHTNAMESTAND");
            StandLeftName.setText(leftTeamS);
            StandRightName.setText(rightTeamS);
            // Get passed Total Won Bests keys from CountingActivity
            Integer totalLeftBests = bundle.getInt("TotalLeftBests");
            Integer totalRightBests = bundle.getInt("TotalRightBests");
            // Put passed Total Won Bests values from keys came from CountingActivity
            TotalLeftBestsTxt.setText(String.valueOf(totalLeftBests));
            TotalRightBestsTxt.setText(String.valueOf(totalRightBests));
            // Get passed 1st Best Matches scores Integers keys from CountingActivity
            Integer leftFrBOne = bundle.getInt("LeftFirstMatchFrB");
            Integer rightFrBOne = bundle.getInt("RightFirstMatchFrB");
            Integer leftFrBTwo = bundle.getInt("LeftSecondMatchFrB");
            Integer rightFrBTwo = bundle.getInt("RightSecondMatchFrB");
            Integer leftFrBThree = bundle.getInt("LeftThirdMatchFrB");
            Integer rightFrBThree = bundle.getInt("RightThirdMatchFrB");
            // Put 1st Best Matches scores as Strings in their TextViews
            LeftBestOneMatchOne.setText(String.valueOf(leftFrBOne));
            RightBestOneMatchOne.setText(String.valueOf(rightFrBOne));
            LeftBestOneMatchTwo.setText(String.valueOf(leftFrBTwo));
            RightBestOneMatchTwo.setText(String.valueOf(rightFrBTwo));
            LeftBestOneMatchThree.setText(String.valueOf(leftFrBThree));
            RightBestOneMatchThree.setText(String.valueOf(rightFrBThree));
            // Get passed 2nd Best Matches scores Integers keys from CountingActivity
            Integer leftSeBOne = bundle.getInt("LeftFirstMatchSeB");
            Integer rightSeBOne = bundle.getInt("RightFirstMatchSeB");
            Integer leftSeBTwo = bundle.getInt("LeftSecondMatchSeB");
            Integer rightSeBTwo = bundle.getInt("RightSecondMatchSeB");
            Integer leftSeBThree = bundle.getInt("LeftThirdMatchSeB");
            Integer rightSeBThree = bundle.getInt("RightThirdMatchSeB");
            // Put 2nd Best Matches scores as Strings in their TextViews
            LeftBestTwoMatchOne.setText(String.valueOf(leftSeBOne));
            RightBestTwoMatchOne.setText(String.valueOf(rightSeBOne));
            LeftBestTwoMatchTwo.setText(String.valueOf(leftSeBTwo));
            RightBestTwoMatchTwo.setText(String.valueOf(rightSeBTwo));
            LeftBestTwoMatchThree.setText(String.valueOf(leftSeBThree));
            RightBestTwoMatchThree.setText(String.valueOf(rightSeBThree));
            // Get passed 3rd Best Matches scores Integers keys from CountingActivity
            Integer leftThBOne = bundle.getInt("LeftFirstMatchThB");
            Integer rightThBOne = bundle.getInt("RightFirstMatchThB");
            Integer leftThBTwo = bundle.getInt("LeftSecondMatchThB");
            Integer rightThBTwo = bundle.getInt("RightSecondMatchThB");
            Integer leftThBThree = bundle.getInt("LeftThirdMatchThB");
            Integer rightThBThree = bundle.getInt("RightThirdMatchThB");
            // Put 3rd Best Matches scores as Strings in their TextViews
            LeftBestThreeMatchOne.setText(String.valueOf(leftThBOne));
            RightBestThreeMatchOne.setText(String.valueOf(rightThBOne));
            LeftBestThreeMatchTwo.setText(String.valueOf(leftThBTwo));
            RightBestThreeMatchTwo.setText(String.valueOf(rightThBTwo));
            LeftBestThreeMatchThree.setText(String.valueOf(leftThBThree));
            RightBestThreeMatchThree.setText(String.valueOf(rightThBThree));
        }
    }
}