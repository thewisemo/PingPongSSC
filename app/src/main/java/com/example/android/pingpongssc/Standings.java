package com.example.android.pingpongssc;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class Standings extends Activity {
    // Names TextViews Global Variables &
    TextView StandLeftName, StandRightName,
    // Recent Best Matches Scores TextViews Global Variables
    LeftFirstMatchTxt, RightFirstMatchTxt,
            LeftSecondMatchTxt, RightSecondMatchTxt,
            LeftThirdMatchTxt, RightThirdMatchTxt,
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
    Button swapBtn;

    int // Recent Matches Global Integer variables
            FirstLeftMatch, FirstRightMatch,
            SecondLeftMatch, SecondRightMatch,
            ThirdLeftMatch, ThirdRightMatch,
    // Total Bests Won
    LeftTotalWonBests, RightTotalWonBests,
    // First Best Matches Scores Integers Global Variables
    FrBFirstLeftMatch, FrBFirstRightMatch,
            FrBSecondLeftMatch, FrBSecondRightMatch,
            FrBThirdLeftMatch, FrBThirdRightMatch,
    // Second Best Matches Scores Integers Global Variables
    SeBFirstLeftMatch, SeBFirstRightMatch,
            SeBSecondLeftMatch, SeBSecondRightMatch,
            SeBThirdLeftMatch, SeBThirdRightMatch,
    // Third Best Matches Scores Integers Global Variables
    ThBFirstLeftMatch, ThBFirstRightMatch,
            ThBSecondLeftMatch, ThBSecondRightMatch,
            ThBThirdLeftMatch, ThBThirdRightMatch;

    // This @Override saves current data on screen mobile rotation and showing standings popup view
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        // Save Recent Matches scores in their keys
        savedInstanceState.putInt("leftMatchFirst", FirstLeftMatch);
        savedInstanceState.putInt("rightMatchFirst", FirstRightMatch);
        savedInstanceState.putInt("leftMatchSecond", SecondLeftMatch);
        savedInstanceState.putInt("rightMatchSecond", SecondRightMatch);
        savedInstanceState.putInt("leftMatchThird", ThirdLeftMatch);
        savedInstanceState.putInt("rightMatchThird", ThirdRightMatch);
        // Save 1st Best Matches Scores in keys
        savedInstanceState.putInt("leftFrBMatchFirst", FrBFirstLeftMatch);
        savedInstanceState.putInt("rightFrBMatchFirst", FrBFirstRightMatch);
        savedInstanceState.putInt("leftFrBMatchSecond", FrBSecondLeftMatch);
        savedInstanceState.putInt("rightFrBMatchSecond", FrBSecondRightMatch);
        savedInstanceState.putInt("leftFrBMatchThird", FrBThirdLeftMatch);
        savedInstanceState.putInt("rightFrBMatchThird", FrBThirdRightMatch);
        // Save 2nd Best Matches Scores in keys
        savedInstanceState.putInt("leftSeBMatchFirst", SeBFirstLeftMatch);
        savedInstanceState.putInt("rightSeBMatchFirst", SeBFirstRightMatch);
        savedInstanceState.putInt("leftSeBMatchSecond", SeBSecondLeftMatch);
        savedInstanceState.putInt("rightSeBMatchSecond", SeBSecondRightMatch);
        savedInstanceState.putInt("leftSeBMatchThird", SeBThirdLeftMatch);
        savedInstanceState.putInt("rightSeBMatchThird", SeBThirdRightMatch);
        // Save 3rd Best Matches Scores in keys
        savedInstanceState.putInt("leftThBMatchFirst", ThBFirstLeftMatch);
        savedInstanceState.putInt("rightThBMatchFirst", ThBFirstRightMatch);
        savedInstanceState.putInt("leftThBMatchSecond", ThBSecondLeftMatch);
        savedInstanceState.putInt("rightThBMatchSecond", ThBSecondRightMatch);
        savedInstanceState.putInt("leftThBMatchThird", ThBThirdLeftMatch);
        savedInstanceState.putInt("rightThBMatchThird", ThBThirdRightMatch);
        // Always call the superclass so it can save the view hierarchy state
        super.onSaveInstanceState(savedInstanceState);
    }

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

        //Assigns Left & Right "Recent Matches Scores textViews public variables to IDs in standings_popup.xml
        LeftFirstMatchTxt = findViewById(R.id.left_first_match_score);
        RightFirstMatchTxt = findViewById(R.id.rightFirstMatchScore);
        LeftSecondMatchTxt = findViewById(R.id.leftSecondMatchScore);
        RightSecondMatchTxt = findViewById(R.id.rightSecondMatchScore);
        LeftThirdMatchTxt = findViewById(R.id.leftThirdMatchScore);
        RightThirdMatchTxt = findViewById(R.id.rightThirdMatchScore);
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
            // Get passed Recent Matches scores Integers keys from CountingActivity
            Integer leftFirst = bundle.getInt("LeftFirstMatch");
            Integer rightFirst = bundle.getInt("RightFirstMatch");
            Integer leftSecond = bundle.getInt("LeftSecondMatch");
            Integer rightSecond = bundle.getInt("RightSecondMatch");
            Integer leftThird = bundle.getInt("LeftThirdMatch");
            Integer rightThird = bundle.getInt("RightThirdMatch");
            // Put Recent Matches scores as Strings in their TextViews
            LeftFirstMatchTxt.setText(String.valueOf(leftFirst));
            RightFirstMatchTxt.setText(String.valueOf(rightFirst));
            LeftSecondMatchTxt.setText(String.valueOf(leftSecond));
            RightSecondMatchTxt.setText(String.valueOf(rightSecond));
            LeftThirdMatchTxt.setText(String.valueOf(leftThird));
            RightThirdMatchTxt.setText(String.valueOf(rightThird));
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
    // Public Class to call saved data in onSaveInstanceState
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        // Always call the superclass so it can restore the view while screen oriantation or Showing standings popup
        super.onRestoreInstanceState(savedInstanceState);
        // get Recent Matches scores saved in onSavedInstanceState keys
        FirstLeftMatch = savedInstanceState.getInt("leftMatchFirst");
        FirstRightMatch = savedInstanceState.getInt("rightMatchFirst");
        SecondLeftMatch = savedInstanceState.getInt("leftMatchSecond");
        SecondRightMatch = savedInstanceState.getInt("rightMatchThird");
        ThirdLeftMatch = savedInstanceState.getInt("leftMatchThird");
        ThirdRightMatch = savedInstanceState.getInt("rightMatchThird");
        // get 1st Best Matches scores saved in onSavedInstanceState keys
        FrBFirstLeftMatch = savedInstanceState.getInt("leftFrBMatchFirst");
        FrBFirstRightMatch = savedInstanceState.getInt("rightFrBMatchFirst");
        FrBSecondLeftMatch = savedInstanceState.getInt("leftFrBMatchSecond");
        FrBSecondRightMatch = savedInstanceState.getInt("rightFrBMatchSecond");
        FrBThirdLeftMatch = savedInstanceState.getInt("leftFrBMatchThird");
        FrBThirdRightMatch = savedInstanceState.getInt("leftFrBMatchThird");
        // get 2nd Best Matches scores saved in onSavedInstanceState keys
        SeBFirstLeftMatch = savedInstanceState.getInt("leftSeBMatchFirst");
        SeBFirstRightMatch = savedInstanceState.getInt("rightSeBMatchFirst");
        SeBSecondLeftMatch = savedInstanceState.getInt("leftSeBMatchSecond");
        SeBSecondRightMatch = savedInstanceState.getInt("rightSeBMatchSecond");
        SeBThirdLeftMatch = savedInstanceState.getInt("leftSeBMatchThird");
        SeBThirdRightMatch = savedInstanceState.getInt("leftSeBMatchThird");
        // get 3rd Best Matches scores saved in onSavedInstanceState keys
        ThBFirstLeftMatch = savedInstanceState.getInt("leftThBMatchFirst");
        ThBFirstRightMatch = savedInstanceState.getInt("rightThBMatchFirst");
        ThBSecondLeftMatch = savedInstanceState.getInt("leftThBMatchSecond");
        ThBSecondRightMatch = savedInstanceState.getInt("rightThBMatchSecond");
        ThBThirdLeftMatch = savedInstanceState.getInt("leftThBMatchThird");
        ThBThirdRightMatch = savedInstanceState.getInt("leftThBMatchThird");
    }
}