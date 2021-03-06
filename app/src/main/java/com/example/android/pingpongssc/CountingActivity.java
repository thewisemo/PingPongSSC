package com.example.android.pingpongssc;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class CountingActivity extends AppCompatActivity {
    // For the Reset/Standings Popup views
    // Global Variables for the context & the background for both popups landing activity
    Context mContext;
    Activity landingActivity;
    // The reset & standings buttons and the main relative layout container Global Variables
    RelativeLayout popupsMainRelativeLayout;
    ImageButton resetBtn, incLeftOneBtn;
    Button standingsBtn;
    // PopupWindow variables
    PopupWindow resetPopupWindow;

    // Global integers ( primitive values )
    int winingScore = 5,
            wonMatchesForOneBest = 2,
            totalMatchesForOneBest = 3,
    // Player Scores
    leftScore, rightScore,
    // Best Number
    bestNumber,
    // Match Number
    matchNumber,
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

    // Names TextViews Global Variables
    TextView leftTeamName, rightTeamName;

    // This @Override saves current data on screen mobile rotation and showing standings popup view
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        // Save the players/teams current scores state
        savedInstanceState.putInt("leftScore", leftScore);
        savedInstanceState.putInt("rightScore", rightScore);
        // Always call the superclass so it can save the view hierarchy state
        super.onSaveInstanceState(savedInstanceState);
    }

    // The onCreate method which assigns the valuables to the Global variables
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Scores Array
        final ArrayList<GameScore> scores = new ArrayList<>(18);
        // Index 1
//
//        scores.add(new GameScore(ThBThirdRightMatch));
        // ToolBar menu
        Toolbar moToolBar = findViewById(R.id.ToolBar);
        setSupportActionBar(moToolBar);
        // Assign 2TextViews to the left & right IDs
        // Create bundle as Bundle to get data passed from UserInputNames class
        leftTeamName = findViewById(R.id.left_team_name);
        rightTeamName = findViewById(R.id.right_team_name);
        Bundle bundle = getIntent().getExtras();
        // To import data by their referred keys from 2 EditText views in the activity_teams_names
        // Put the texts saved in keys and set Names TextViews into the
        // Counting views & Standing pop TextView Global Variables
        if (bundle != null) {
            String rightInputS = bundle.getString("RIGHTINPUT");
            String leftInputS = bundle.getString("LEFTINPUT");
            rightTeamName.setText(rightInputS);
            leftTeamName.setText(leftInputS);
        }
        //To be sure that the Name will scroll horizontally repetitively
        findViewById(R.id.left_team_name).setSelected(true);
        //To be sure that the Name will scroll horizontally repetitively
        findViewById(R.id.right_team_name).setSelected(true);
        // For Reset Popup View :
        // Get the application context
        mContext = getApplicationContext();
        // Get the Background Activity
        landingActivity = CountingActivity.this;
        // Get the widgets reference from XML layout
        popupsMainRelativeLayout = findViewById(R.id.main_relative_layout);
        // Magic Reset button
        // Reset button onClickListner that Inflates resetpop.xml
        resetBtn = findViewById(R.id.reset);
        // Set a click listener for the text view
        resetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Initialize a new instance of LayoutInflater service
                LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(LAYOUT_INFLATER_SERVICE);
                // Inflate the custom layout/view
                assert inflater != null;
                @SuppressLint("InflateParams") View customView = inflater.inflate(R.layout.resetpop, null);
                setTheme(R.style.Theme_AppCompat_Dialog);
                /*
                public PopupWindow (View contentView, int width, int height)
                Create a new non focusable popup window which can display the contentView.
                The dimension of the window must be passed to this constructor.
                The popup does not provide any background. This should be handled by
                the content view.
                    Parameters
                        contentView : the popup's content
                        width : the popup's width
                        height : the popup's height
                */
                // Initialize a new instance of popup window
                resetPopupWindow = new PopupWindow(
                        customView,
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT);
                // Set an elevation value for popup window if android system less than 21V
                // Call requires API level 21
                if (Build.VERSION.SDK_INT >= 21) {
                    resetPopupWindow.setElevation(5.0f);
                }

                // Reset0 button
                Button reset0 = customView.findViewById(R.id.reset0);
                reset0.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // reset scores to Zeors
                        leftScore = 0;
                        rightScore = 0;
                        displayForLeft(leftScore);
                        displayForRight(rightScore);
                        // Dismiss the popup reset window
                        resetPopupWindow.dismiss();
                    }
                });
                // Reset15 button
                Button reset15 = customView.findViewById(R.id.reset15);
                reset15.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // reset scores to 15
                        leftScore = 15;
                        rightScore = 15;
                        displayForLeft(leftScore);
                        displayForRight(rightScore);
                        // Dismiss the popup reset window
                        resetPopupWindow.dismiss();
                    }
                });
                // Reset17 button
                Button reset17 = customView.findViewById(R.id.reset17);
                reset17.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // reset scores to 17
                        leftScore = 17;
                        rightScore = 17;
                        displayForLeft(leftScore);
                        displayForRight(rightScore);
                        // Dismiss the popup reset window
                        resetPopupWindow.dismiss();
                    }
                });
                // Back button
                Button back = customView.findViewById(R.id.back);
                back.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        resetPopupWindow.dismiss();
                    }
                });
                /*
                public void showAtLocation (View parent, int gravity, int x, int y)
                Display the content view in a popup window at the specified location. If the
                popup window cannot fit on screen, it will be clipped.
                I NEED TO Learn WindowManager.LayoutParams for more information on how gravity and the x
                and y parameters are related. Specifying a gravity of NO_GRAVITY is similar
                to specifying Gravity.LEFT | Gravity.TOP.
                    Parameters
                        parent : a parent view to get the getWindowToken() token from
                        gravity : the gravity which controls the placement of the popup window
                        x : the popup's x location offset
                        y : the popup's y location offset
                */
                // Finally, show the popup window at the center location of root relative layout
                resetPopupWindow.showAtLocation(popupsMainRelativeLayout, Gravity.CENTER, 0, 0);
            }
        });

        // Standings Button
        standingsBtn = findViewById(R.id.standingsBtn);
        //Standings Button clickListner to show popup view from StandPop class
        // By intent Standings pop window to be shown
        standingsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent standings = new Intent(CountingActivity.this, Standings.class);
                // Pass Names scores to Standings class
                standings.putExtra("LEFTNAMESTAND", leftTeamName.getText().toString());
                standings.putExtra("RIGHTNAMESTAND", rightTeamName.getText().toString());
                // Pass 1st Best Matches Scores
                standings.putExtra("LeftFirstMatchFrB", FrBFirstLeftMatch);
                standings.putExtra("RightFirstMatchFrB", FrBFirstRightMatch);
                standings.putExtra("LeftSecondMatchFrB", FrBSecondLeftMatch);
                standings.putExtra("RightSecondMatchFrB", FrBSecondRightMatch);
                standings.putExtra("LeftThirdMatchFrB", FrBThirdLeftMatch);
                standings.putExtra("RightThirdMatchFrB", FrBThirdRightMatch);
                // Pass 2nd Best Matches Scores
                standings.putExtra("LeftFirstMatchSeB", SeBFirstLeftMatch);
                standings.putExtra("RightFirstMatchSeB", SeBFirstRightMatch);
                standings.putExtra("LeftSecondMatchSeB", SeBSecondLeftMatch);
                standings.putExtra("RightSecondMatchSeB", SeBSecondRightMatch);
                standings.putExtra("LeftThirdMatchSeB", SeBThirdLeftMatch);
                standings.putExtra("RightThirdMatchSeB", SeBThirdRightMatch);
                // Pass 3rd Best Matches Scores
                standings.putExtra("LeftFirstMatchThB", ThBFirstLeftMatch);
                standings.putExtra("RightFirstMatchThB", ThBFirstRightMatch);
                standings.putExtra("LeftSecondMatchThB", ThBSecondLeftMatch);
                standings.putExtra("RightSecondMatchThB", ThBSecondRightMatch);
                standings.putExtra("LeftThirdMatchThB", ThBThirdLeftMatch);
                standings.putExtra("RightThirdMatchThB", ThBThirdRightMatch);
                startActivity(standings);
            }
        });

        incLeftOneBtn = findViewById(R.id.incLeftOne);
        incLeftOneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                }

        });
    }
    //ToolBar inflater
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater moMenuInflater = getMenuInflater();
        moMenuInflater.inflate(R.menu.wisemo_menu, menu);
        return true;
    }
    // This Class is to call saved data in onSaveInstanceState for the screen orientation and popup view show
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        // Always call the superclass so it can restore the view hierarchy
        super.onRestoreInstanceState(savedInstanceState);
        // get players/teams scores saved in onSavedInstanceState keys
        leftScore = savedInstanceState.getInt("leftScore");
        rightScore = savedInstanceState.getInt("rightScore");
        displayForLeft(savedInstanceState.getInt("leftScore"));
        displayForRight(savedInstanceState.getInt("rightScore"));
    }
    // When the Back Button is pressed :
    // If The popup Reset OR Standings view is showing then dismissed
    // If the popup Reset OR Standings view is not showing then finish (((( NEED TO BE EXIT APP WARNING screen ))))
    @Override
    public void onBackPressed() {
        if (resetPopupWindow.isShowing()) {
            resetPopupWindow.dismiss();
        } else super.onBackPressed();
    }
    // When the + for left Player/Team points is clicked


//    // declare & initialize a boolean value for 1st match scores
//    // The code meaning : any player wins Match (True) when
//    // boolean his score = winning score
////    public boolean wonMatch = (leftScore | rightScore) == winingScore;
//
//    boolean wonPoint, wonMatch, wonBest;
//
//    public void isWonPoint (int playerScore){
//        playerScore = playerScore + 1;
//        displayForLeft(playerScore);
//    }

    // isLeftWonMatch method called to track left score " When the boolean is false " AND
    // to check if Left is the winner for the all 9 matches - as a test for my knowledge to use switch -
//    public void isWonMatch(int theScore) {
//        switch (theScore){
//            case 1:
//                if (!wonMatch) {
//                    FrBFirstLeftMatch = leftScore;
//
//                } else {
//                    FrBFirstLeftMatch = winingScore;
//                    FrBFirstRightMatch = rightScore;
//
//                }
//
//        }
//    }





    // When the -1 for left player/team score is clicked
    public void decLeftOne(View view) {
        if (leftScore == 0) {
            // Show an error message as a toast
            Toast.makeText(this, "You cannot have less than 0 point", Toast.LENGTH_SHORT).show();
            // Exit this method early because there's nothing left to do
            return;
        }
        leftScore = leftScore - 1;
        displayForLeft(leftScore);
    }


    // When the + for right Player/Team points is clicked
    public void incRightOne(View view) {
        rightScore = rightScore + 1;
        displayForRight(rightScore);

    }
    // When the - for right player/team score is clicked
    public void decRightOne(View view) {
        if (rightScore == 0) {
            // Show an error message as a toast
            Toast.makeText(this, "You cannot have less than 0 point", Toast.LENGTH_SHORT).show();
            // Exit this method early because there's nothing left to do
            return;
        }
        rightScore = rightScore - 1;
        displayForRight(rightScore);
    }
    // Displays the given score for Left
    public void displayForLeft(int score) {
        TextView scoreView = findViewById(R.id.left_score);
        scoreView.setText(String.valueOf(score));
    }
    // Displays the given score for Right
    public void displayForRight(int score) {
        TextView scoreView = findViewById(R.id.right_score);
        scoreView.setText(String.valueOf(score));
    }
}