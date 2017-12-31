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
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class CountingActivity extends AppCompatActivity {
    // For the Reset/Standings Popup views
    // Global Variables for the context & the background for both popups landing activity
    Context mContext;
    Activity landingActivity;
    // The reset & standings buttons and the main relative layout container Global Variables
    RelativeLayout popupsMainRelativeLayout;
    ImageButton resetBtn;
    Button standingsBtn;
    // PopupWindow variables
    PopupWindow resetPopupWindow;
    // RIGHT & LEFT Scores &
    int leftTeamScore, rightTeamScore,
    // Recent Matches Global Integer variables
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

    // Names TextViews Global Variables
    TextView leftTeamName, rightTeamName;

    // This @Override saves current data on screen mobile rotation and showing standings popup view
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        // Save the players/teams current scores state
        savedInstanceState.putInt("leftScore", leftTeamScore);
        savedInstanceState.putInt("rightScore", rightTeamScore);
        // Always call the superclass so it can save the view hierarchy state
        super.onSaveInstanceState(savedInstanceState);
    }

    // The onCreate method which assigns the valuables to the Global variables
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_ACTION_BAR);
        // Remove notifications bar (( Makes Activity full screen ))
        this.getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        // Assign 2TextViews to the left & right IDs
        // Create bundle as Bundle to get data passed from UserInputNames class
        leftTeamName = findViewById(R.id.left_team_name);
        rightTeamName = findViewById(R.id.right_team_name);
        Bundle bundle = getIntent().getExtras();
        // Tool Bar menu
        Toolbar wisemoTBar = findViewById(R.id.wisemoToolBar);
        setSupportActionBar(wisemoTBar);

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
                        leftTeamScore = 0;
                        rightTeamScore = 0;
                        displayForLeft(leftTeamScore);
                        displayForRight(rightTeamScore);
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
                        leftTeamScore = 15;
                        rightTeamScore = 15;
                        displayForLeft(leftTeamScore);
                        displayForRight(rightTeamScore);
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
                        leftTeamScore = 17;
                        rightTeamScore = 17;
                        displayForLeft(leftTeamScore);
                        displayForRight(rightTeamScore);
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

        /////////////////////////////////////////////////////////////////////////////
        /////////////////////////////////////////////////////////////////////////////
        /////////////////////////////////////////////////////////////////////////////
        // How Update Total Left & Right Integers to be shown in th Standings class?
        // For Example:
        /*
        // If Left Won 1st Best
        if (FrBFirstLeftMatch == 3 && FrBSecondLeftMatch == 3){
            LeftTotalWonBests = LeftTotalWonBests + 1;
            //Reset Left & Right scores to 0 then display both scores
            leftTeamScore = 0;
            rightTeamScore = 0;
            displayForLeft(leftTeamScore);
            displayForRight(rightTeamScore);
            // Reset recent matches AND END THE COUNTING
            FirstLeftMatch = 0;
            FirstRightMatch = 0;
            SecondLeftMatch = 0;
            SecondRightMatch = 0;
            ThirdLeftMatch = 0;
            ThirdRightMatch = 0;
            // Congratulations dialog toast
            Toast.makeText(getApplicationContext(),
                    "Congratulations! New Best To Count", Toast.LENGTH_SHORT)
                    .show();
        }
        */
        /////////////////////////////////////////////////////////////////////////////

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
                // Pass Recent Matches scores to StandPop class
                standings.putExtra("LeftFirstMatch", FirstLeftMatch);
                standings.putExtra("RightFirstMatch", FirstRightMatch);
                standings.putExtra("LeftSecondMatch", SecondLeftMatch);
                standings.putExtra("RightSecondMatch", SecondRightMatch);
                standings.putExtra("LeftThirdMatch", ThirdLeftMatch);
                standings.putExtra("RightThirdMatch", ThirdRightMatch);
                // Pass Total Won Bests
                standings.putExtra("TotalLeftBests", LeftTotalWonBests);
                standings.putExtra("TotalRightBests", RightTotalWonBests);
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
                // Start Activity standings Intent
                startActivity(standings);
            }
        });
    }

    // Wisemo Tool Bar inflater
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater wisemoMenuInflater = getMenuInflater();
        wisemoMenuInflater.inflate(R.menu.wisemo_menu, menu);
        return true;
    }

    // This Class is to call saved data in onSaveInstanceState for the screen orientation and popup view show
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        // Always call the superclass so it can restore the view hierarchy
        super.onRestoreInstanceState(savedInstanceState);
        // get players/teams scores saved in onSavedInstanceState keys
        leftTeamScore = savedInstanceState.getInt("leftScore");
        rightTeamScore = savedInstanceState.getInt("rightScore");
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
    public void incLeftOne(View view) {
        // Increase score by 1 then display new score
        leftTeamScore = leftTeamScore + 1;
        displayForLeft(leftTeamScore);
/*
//// This is the logic I want to see, but it is wrong as it is now for sure

        // If Left Won 1st Best
        if ((FrBFirstLeftMatch == 3 && FrBSecondLeftMatch == 3) ||
                (FrBSecondLeftMatch == 3 && FrBThirdLeftMatch == 3) ||
                (FrBFirstLeftMatch == 3 && FrBThirdLeftMatch == 3)){
            // Increase total won bests by left
            LeftTotalWonBests = LeftTotalWonBests + 1;
            //Reset Left & Right scores to 0 then display both scores
            leftTeamScore = 0;
            rightTeamScore = 0;
            displayForLeft(leftTeamScore);
            displayForRight(rightTeamScore);
            // Reset recent matches AND END THE COUNTING
            FirstLeftMatch = 0;
            FirstRightMatch = 0;
            SecondLeftMatch = 0;
            SecondRightMatch = 0;
            ThirdLeftMatch = 0;
            ThirdRightMatch = 0;
            // Congratulations dialog toast
            Toast.makeText(getApplicationContext(),
                    "Congratulations!", Toast.LENGTH_SHORT)
                    .show();
        }
        // If Left Won 2nd Best
        else if ((SeBFirstLeftMatch == 3 && SeBSecondLeftMatch == 3) ||
                (SeBSecondLeftMatch == 3 && SeBThirdLeftMatch == 3) ||
                (SeBFirstLeftMatch == 3 && SeBThirdLeftMatch == 3)){
            // Increase total won bests by left
            LeftTotalWonBests = LeftTotalWonBests + 1;
            //Reset Left & Right scores to 0 then display both scores
            leftTeamScore = 0;
            rightTeamScore = 0;
            displayForLeft(leftTeamScore);
            displayForRight(rightTeamScore);
            // Reset recent matches AND END THE COUNTING
            FirstLeftMatch = 0;
            FirstRightMatch = 0;
            SecondLeftMatch = 0;
            SecondRightMatch = 0;
            ThirdLeftMatch = 0;
            ThirdRightMatch = 0;
            // Congratulations dialog toast
            Toast.makeText(getApplicationContext(),
                    "Congratulations!", Toast.LENGTH_SHORT)
                    .show();
        }
        // If Left Won 3rd Best
        else if ((ThBFirstLeftMatch == 3 && ThBSecondLeftMatch == 3) ||
                (ThBSecondLeftMatch == 3 && ThBThirdLeftMatch == 3 ) ||
                (ThBFirstLeftMatch == 3 && ThBThirdLeftMatch == 3)) {
            // Increase total won bests by left
            LeftTotalWonBests = LeftTotalWonBests + 1;
            //Reset Left & Right scores to 0 then display both scores
            leftTeamScore = 0;
            rightTeamScore = 0;
            displayForLeft(leftTeamScore);
            displayForRight(rightTeamScore);
            // Reset recent matches AND END THE COUNTING
            FirstLeftMatch = 0;
            FirstRightMatch = 0;
            SecondLeftMatch = 0;
            SecondRightMatch = 0;
            ThirdLeftMatch = 0;
            ThirdRightMatch = 0;
            // Congratulations dialog toast
            Toast.makeText(getApplicationContext(),
                    "Congratulations!", Toast.LENGTH_SHORT)
                    .show();
        }
*/
        // If Left won the 3rd best 3rd Match
        if (((leftTeamScore == 3) &&
                (FirstLeftMatch == 3 | FirstRightMatch == 3) &&
                (SecondLeftMatch == 3 | SecondRightMatch == 3)) &&
                (FrBFirstLeftMatch == 3 | FrBFirstRightMatch == 3) &&
                (FrBSecondLeftMatch == 3 | FrBSecondRightMatch == 3) &&
                (FrBThirdLeftMatch == 3 | FrBThirdRightMatch == 3) &&
                (SeBFirstLeftMatch == 3 | SeBFirstRightMatch == 3) &&
                (SeBSecondLeftMatch == 3 | SeBSecondRightMatch == 3) &&
                (SeBThirdLeftMatch == 3 | SeBThirdRightMatch == 3) &&
                (ThBFirstLeftMatch == 3 | ThBFirstRightMatch == 3) &&
                (ThBSecondLeftMatch == 3 | ThBSecondRightMatch == 3)) {
            // Left won 3rd Recent match
            ThirdLeftMatch = 3;
            // Right 3rd Match score
            ThirdRightMatch = rightTeamScore;
            // Left is the Winner of 3rd Best 3rd Match
            ThBThirdLeftMatch = 3;
            // Right 3rd Best 3rd Match
            ThBThirdRightMatch = rightTeamScore;
            //Reset Left & Right scores to 0 then display both scores
            leftTeamScore = 0;
            rightTeamScore = 0;
            displayForLeft(leftTeamScore);
            displayForRight(rightTeamScore);
            // Reset recent matches AND END THE COUNTING
            FirstLeftMatch = 0;
            FirstRightMatch = 0;
            SecondLeftMatch = 0;
            SecondRightMatch = 0;
            ThirdLeftMatch = 0;
            ThirdRightMatch = 0;
            // Congratulations dialog toast
            Toast.makeText(getApplicationContext(),
                    "Congratulations!", Toast.LENGTH_SHORT)
                    .show();
        }
        // If Left Won the 3rd best 2nd Match
        else if (((leftTeamScore == 3) &&
                (FirstLeftMatch == 3 | FirstRightMatch == 3)) &&
                (FrBFirstLeftMatch == 3 | FrBFirstRightMatch == 3) &&
                (FrBSecondLeftMatch == 3 | FrBSecondRightMatch == 3) &&
                (FrBThirdLeftMatch == 3 | FrBThirdRightMatch == 3) &&
                (SeBFirstLeftMatch == 3 | SeBFirstRightMatch == 3) &&
                (SeBSecondLeftMatch == 3 | SeBSecondRightMatch == 3) &&
                (SeBThirdLeftMatch == 3 | SeBThirdRightMatch == 3)) {
            // Left won 2nd Recent match
            SecondLeftMatch = 3;
            // Right 2nd Match score
            SecondRightMatch = rightTeamScore;
            // Left is the Winner of 3rd Best 2nd Match
            ThBSecondLeftMatch = 3;
            // Right 3rd Best 2nd Match
            ThBSecondRightMatch = rightTeamScore;
            //Reset Left & Right scores to 0 then display both scores
            leftTeamScore = 0;
            rightTeamScore = 0;
            displayForLeft(leftTeamScore);
            displayForRight(rightTeamScore);
            // Congratulations dialog toast
            Toast.makeText(getApplicationContext(),
                    "Congratulations!", Toast.LENGTH_SHORT)
                    .show();
        }
        // If Left Won the 3rd best 1st Match
        else if (((leftTeamScore == 3) &&
                (FrBFirstLeftMatch == 3 | FrBFirstRightMatch == 3) &&
                (FrBSecondLeftMatch == 3 | FrBSecondRightMatch == 3) &&
                (FrBThirdLeftMatch == 3 | FrBThirdRightMatch == 3) &&
                (SeBFirstLeftMatch == 3 | SeBFirstRightMatch == 3) &&
                (SeBSecondLeftMatch == 3 | SeBSecondRightMatch == 3) &&
                (SeBThirdLeftMatch == 3 | SeBThirdRightMatch == 3))) {
            // Left won 1st Recent match
            FirstLeftMatch = 3;
            // Right 1st Match score
            FirstRightMatch = rightTeamScore;
            // Left is the Winner of 3rd Best 1st Match
            ThBFirstLeftMatch = 3;
            // Right 3rd Best 1st Match score
            ThBFirstRightMatch = rightTeamScore;
            //Reset Left & Right scores to 0 then display both scores
            leftTeamScore = 0;
            rightTeamScore = 0;
            displayForLeft(leftTeamScore);
            displayForRight(rightTeamScore);
            // Congratulations dialog toast
            Toast.makeText(getApplicationContext(),
                    "Congratulations!", Toast.LENGTH_SHORT)
                    .show();
        }
        // If Left won the 2nd best 3rd Match
        // When all 1st & 2nd recent Matches && 3rd Best 1st & 2nd Matches finished
        else if (((leftTeamScore == 3) &&
                (FirstLeftMatch == 3 | FirstRightMatch == 3) &&
                (SecondLeftMatch == 3 | SecondRightMatch == 3)) &&
                (FrBFirstLeftMatch == 3 | FrBFirstRightMatch == 3) &&
                (FrBSecondLeftMatch == 3 | FrBSecondRightMatch == 3) &&
                (FrBThirdLeftMatch == 3 | FrBThirdRightMatch == 3) &&
                (SeBFirstLeftMatch == 3 | SeBFirstRightMatch == 3) &&
                (SeBSecondLeftMatch == 3 | SeBSecondRightMatch == 3)) {
            // Left won 3rd Recent match
            ThirdLeftMatch = 3;
            // Right 3rd Match score
            ThirdRightMatch = rightTeamScore;
            // Left is the Winner of 2nd Best 3rd Match
            SeBThirdLeftMatch = 3;
            // Right 2nd Best 3rd Match
            SeBThirdRightMatch = rightTeamScore;
            //Reset Left & Right scores to 0 then display both scores
            leftTeamScore = 0;
            rightTeamScore = 0;
            displayForLeft(leftTeamScore);
            displayForRight(rightTeamScore);
            // Reset recent matches AND END THE COUNTING
            FirstLeftMatch = 0;
            FirstRightMatch = 0;
            SecondLeftMatch = 0;
            SecondRightMatch = 0;
            ThirdLeftMatch = 0;
            ThirdRightMatch = 0;
            // Congratulations dialog toast
            Toast.makeText(getApplicationContext(),
                    "Congratulations!", Toast.LENGTH_SHORT)
                    .show();
        }
        // If Left Won the 2nd best 2nd Match
        else if (((leftTeamScore == 3) &&
                (FirstLeftMatch == 3 | FirstRightMatch == 3)) &&
                (FrBFirstLeftMatch == 3 | FrBFirstRightMatch == 3) &&
                (FrBSecondLeftMatch == 3 | FrBSecondRightMatch == 3) &&
                (FrBThirdLeftMatch == 3 | FrBThirdRightMatch == 3) &&
                (SeBFirstLeftMatch == 3 | SeBFirstRightMatch == 3)) {
            // Left won 2nd Recent match
            SecondLeftMatch = 3;
            // Right 2nd Match score
            SecondRightMatch = rightTeamScore;
            // Left is the Winner of 2nd Best 2nd Match
            SeBSecondLeftMatch = 3;
            // Right 2nd Best 2nd Match
            SeBSecondRightMatch = rightTeamScore;
            //Reset Left & Right scores to 0 then display both scores
            leftTeamScore = 0;
            rightTeamScore = 0;
            displayForLeft(leftTeamScore);
            displayForRight(rightTeamScore);
            // Congratulations dialog toast
            Toast.makeText(getApplicationContext(),
                    "Congratulations!", Toast.LENGTH_SHORT)
                    .show();
        }
        // If Left Won the 2nd best 1st Match
        else if (((leftTeamScore == 3) &&
                (FrBFirstLeftMatch == 3 | FrBFirstRightMatch == 3) &&
                (FrBSecondLeftMatch == 3 | FrBSecondRightMatch == 3) &&
                (FrBThirdLeftMatch == 3 | FrBThirdRightMatch == 3))) {
            // Left won 1st Recent match
            FirstLeftMatch = 3;
            // Right 1st Match score
            FirstRightMatch = rightTeamScore;
            // Left is the Winner of 2nd Best 1st Match
            SeBFirstLeftMatch = 3;
            // Right 2nd Best 1st Match score
            SeBFirstRightMatch = rightTeamScore;
            //Reset Left & Right scores to 0 then display both scores
            leftTeamScore = 0;
            rightTeamScore = 0;
            displayForLeft(leftTeamScore);
            displayForRight(rightTeamScore);
            // Congratulations dialog toast
            Toast.makeText(getApplicationContext(),
                    "Congratulations!", Toast.LENGTH_SHORT)
                    .show();
        }
        // If Left won the 1st best 3rd Match
        else if (((leftTeamScore == 3) &&
                (FirstLeftMatch == 3 | FirstRightMatch == 3) &&
                (SecondLeftMatch == 3 | SecondRightMatch == 3)) &&
                (FrBFirstLeftMatch == 3 | FrBFirstRightMatch == 3) &&
                (FrBSecondLeftMatch == 3 | FrBSecondRightMatch == 3)) {
            // Left won 3rd Recent match
            ThirdLeftMatch = 3;
            // Right 3rd Match score
            ThirdRightMatch = rightTeamScore;
            // Left is the Winner of 1st Best 3rd Match
            FrBThirdLeftMatch = 3;
            // Right 1st Best 3rd Match
            FrBThirdRightMatch = rightTeamScore;
            //Reset Left & Right scores to 0 then display both scores
            leftTeamScore = 0;
            rightTeamScore = 0;
            displayForLeft(leftTeamScore);
            displayForRight(rightTeamScore);
            // Reset recent matches AND END THE COUNTING
            FirstLeftMatch = 0;
            FirstRightMatch = 0;
            SecondLeftMatch = 0;
            SecondRightMatch = 0;
            ThirdLeftMatch = 0;
            ThirdRightMatch = 0;
            // Congratulations dialog toast
            Toast.makeText(getApplicationContext(),
                    "Congratulations!", Toast.LENGTH_SHORT)
                    .show();
        }
        // If Left Won the 1st best 2nd Match
        else if (((leftTeamScore == 3) &&
                (FirstLeftMatch == 3 | FirstRightMatch == 3)) &&
                (FrBFirstLeftMatch == 3 | FrBFirstRightMatch == 3)) {
            // Left won 2nd Recent match
            SecondLeftMatch = 3;
            // Right 2nd Match score
            SecondRightMatch = rightTeamScore;
            // Left is the Winner of 1st Best 2nd Match
            FrBSecondLeftMatch = 3;
            // Right 1st Best 2nd Match
            FrBSecondRightMatch = rightTeamScore;
            //Reset Left & Right scores to 0 then display both scores
            leftTeamScore = 0;
            rightTeamScore = 0;
            displayForLeft(leftTeamScore);
            displayForRight(rightTeamScore);
            // Congratulations dialog toast
            Toast.makeText(getApplicationContext(),
                    "Congratulations!", Toast.LENGTH_SHORT)
                    .show();
        }
        // If Left Won the 1st best 1st Match
        else if (leftTeamScore == 3) {
            // Left won 1st Recent match
            FirstLeftMatch = 3;
            // Right 1st Match score
            FirstRightMatch = rightTeamScore;
            // Left is the Winner of 1st Best 1st Match
            FrBFirstLeftMatch = 3;
            // Right 1st Best 1st Match score
            FrBFirstRightMatch = rightTeamScore;
            //Reset Left & Right scores to 0 then display both scores
            leftTeamScore = 0;
            rightTeamScore = 0;
            displayForLeft(leftTeamScore);
            displayForRight(rightTeamScore);
            // Congratulations dialog toast
            Toast.makeText(getApplicationContext(),
                    "Congratulations!", Toast.LENGTH_SHORT)
                    .show();
        }
    }

    // When the -1 for left player/team score is clicked
    public void decLeftOne(View view) {
        if (leftTeamScore == 0) {
            // Show an error message as a toast
            Toast.makeText(this, "You cannot have less than 0 point", Toast.LENGTH_SHORT).show();
            // Exit this method early because there's nothing left to do
            return;
        }
        leftTeamScore = leftTeamScore - 1;
        displayForLeft(leftTeamScore);
        FirstLeftMatch = leftTeamScore;
    }

    // When the + for right Player/Team points is clicked
    public void incRightOne(View view) {
        rightTeamScore = rightTeamScore + 1;
        displayForRight(rightTeamScore);
        // If Right won the 3rd best 3rd Match
        if (((rightTeamScore == 3) &&
                (FirstLeftMatch == 3 | FirstRightMatch == 3) &&
                (SecondLeftMatch == 3 | SecondRightMatch == 3)) &&
                (FrBFirstLeftMatch == 3 | FrBFirstRightMatch == 3) &&
                (FrBSecondLeftMatch == 3 | FrBSecondRightMatch == 3) &&
                (FrBThirdLeftMatch == 3 | FrBThirdRightMatch == 3) &&
                (SeBFirstLeftMatch == 3 | SeBFirstRightMatch == 3) &&
                (SeBSecondLeftMatch == 3 | SeBSecondRightMatch == 3) &&
                (SeBThirdLeftMatch == 3 | SeBThirdRightMatch == 3) &&
                (ThBFirstLeftMatch == 3 | ThBFirstRightMatch == 3) &&
                (ThBSecondLeftMatch == 3 | ThBSecondRightMatch == 3)) {
            // Right won 3rd Recent match
            ThirdRightMatch = 3;
            // Left 3rd Match score
            ThirdLeftMatch = leftTeamScore;
            // Right is the Winner of 3rd Best 3rd Match
            ThBThirdRightMatch = 3;
            // Left 3rd Best 3rd Match
            ThBThirdLeftMatch = leftTeamScore;
            //Reset Left & Right scores to 0 then display both scores
            leftTeamScore = 0;
            rightTeamScore = 0;
            displayForLeft(leftTeamScore);
            displayForRight(rightTeamScore);
            // Reset recent matches AND END THE COUNTING
            FirstLeftMatch = 0;
            FirstRightMatch = 0;
            SecondLeftMatch = 0;
            SecondRightMatch = 0;
            ThirdLeftMatch = 0;
            ThirdRightMatch = 0;
            // Congratulations dialog toast
            Toast.makeText(getApplicationContext(),
                    "Congratulations!", Toast.LENGTH_SHORT)
                    .show();
        }
        // If Right Won the 3rd best 2nd Match
        else if (((rightTeamScore == 3) &&
                (FirstLeftMatch == 3 | FirstRightMatch == 3)) &&
                (FrBFirstLeftMatch == 3 | FrBFirstRightMatch == 3) &&
                (FrBSecondLeftMatch == 3 | FrBSecondRightMatch == 3) &&
                (FrBThirdLeftMatch == 3 | FrBThirdRightMatch == 3) &&
                (SeBFirstLeftMatch == 3 | SeBFirstRightMatch == 3) &&
                (SeBSecondLeftMatch == 3 | SeBSecondRightMatch == 3) &&
                (SeBThirdLeftMatch == 3 | SeBThirdRightMatch == 3)) {
            // Right won 2nd Recent match
            SecondRightMatch = 3;
            // Left 2nd Match score
            SecondLeftMatch = leftTeamScore;
            // Right is the Winner of 3rd Best 2nd Match
            ThBSecondRightMatch = 3;
            // Left 3rd Best 2nd Match
            ThBSecondLeftMatch = leftTeamScore;
            //Reset Left & Right scores to 0 then display both scores
            leftTeamScore = 0;
            rightTeamScore = 0;
            displayForLeft(leftTeamScore);
            displayForRight(rightTeamScore);
            // Congratulations dialog toast
            Toast.makeText(getApplicationContext(),
                    "Congratulations!", Toast.LENGTH_SHORT)
                    .show();
        }
        // If Right Won the 3rd best 1st Match
        else if (((rightTeamScore == 3) &&
                (FrBFirstLeftMatch == 3 | FrBFirstRightMatch == 3) &&
                (FrBSecondLeftMatch == 3 | FrBSecondRightMatch == 3) &&
                (FrBThirdLeftMatch == 3 | FrBThirdRightMatch == 3) &&
                (SeBFirstLeftMatch == 3 | SeBFirstRightMatch == 3) &&
                (SeBSecondLeftMatch == 3 | SeBSecondRightMatch == 3) &&
                (SeBThirdLeftMatch == 3 | SeBThirdRightMatch == 3))) {
            // Right won 1st Recent match
            FirstRightMatch = 3;
            // Left 1st Match score
            FirstLeftMatch = leftTeamScore;
            // Right is the Winner of 3rd Best 1st Match
            ThBFirstRightMatch = 3;
            // Left 3rd Best 1st Match score
            ThBFirstLeftMatch = leftTeamScore;
            //Reset Left & Right scores to 0 then display both scores
            leftTeamScore = 0;
            rightTeamScore = 0;
            displayForLeft(leftTeamScore);
            displayForRight(rightTeamScore);
            // Congratulations dialog toast
            Toast.makeText(getApplicationContext(),
                    "Congratulations!", Toast.LENGTH_SHORT)
                    .show();
        }
        // If Right won the 2nd best 3rd Match
        else if (((rightTeamScore == 3) &&
                (FirstLeftMatch == 3 | FirstRightMatch == 3) &&
                (SecondLeftMatch == 3 | SecondRightMatch == 3)) &&
                (FrBFirstLeftMatch == 3 | FrBFirstRightMatch == 3) &&
                (FrBSecondLeftMatch == 3 | FrBSecondRightMatch == 3) &&
                (FrBThirdLeftMatch == 3 | FrBThirdRightMatch == 3) &&
                (SeBFirstLeftMatch == 3 | SeBFirstRightMatch == 3) &&
                (SeBSecondLeftMatch == 3 | SeBSecondRightMatch == 3)) {
            // Right won 3rd Recent match
            ThirdRightMatch = 3;
            // Left 3rd Match score
            ThirdLeftMatch = leftTeamScore;
            // Right is the Winner of 2nd Best 3rd Match
            SeBThirdRightMatch = 3;
            // Left 2nd Best 3rd Match
            SeBThirdLeftMatch = leftTeamScore;
            //Reset Left & Right scores to 0 then display both scores
            leftTeamScore = 0;
            rightTeamScore = 0;
            displayForLeft(leftTeamScore);
            displayForRight(rightTeamScore);
            // Reset recent matches AND END THE COUNTING
            FirstLeftMatch = 0;
            FirstRightMatch = 0;
            SecondLeftMatch = 0;
            SecondRightMatch = 0;
            ThirdLeftMatch = 0;
            ThirdRightMatch = 0;
            // Congratulations dialog toast
            Toast.makeText(getApplicationContext(),
                    "Congratulations!", Toast.LENGTH_SHORT)
                    .show();
        }
        // If Right Won the 2nd best 2nd Match
        else if (((rightTeamScore == 3) &&
                (FirstLeftMatch == 3 | FirstRightMatch == 3)) &&
                (FrBFirstLeftMatch == 3 | FrBFirstRightMatch == 3) &&
                (FrBSecondLeftMatch == 3 | FrBSecondRightMatch == 3) &&
                (FrBThirdLeftMatch == 3 | FrBThirdRightMatch == 3) &&
                (SeBFirstLeftMatch == 3 | SeBFirstRightMatch == 3)) {
            // Right won 2nd Recent match
            SecondRightMatch = 3;
            // Left 2nd Match score
            SecondLeftMatch = leftTeamScore;
            // Right is the Winner of 2nd Best 2nd Match
            SeBSecondRightMatch = 3;
            // Left 2nd Best 2nd Match
            SeBSecondLeftMatch = leftTeamScore;
            //Reset Left & Right scores to 0 then display both scores
            leftTeamScore = 0;
            rightTeamScore = 0;
            displayForLeft(leftTeamScore);
            displayForRight(rightTeamScore);
            // Congratulations dialog toast
            Toast.makeText(getApplicationContext(),
                    "Congratulations!", Toast.LENGTH_SHORT)
                    .show();
        }
        // If Right Won the 2nd best 1st Match
        else if (((rightTeamScore == 3) &&
                (FrBFirstLeftMatch == 3 | FrBFirstRightMatch == 3) &&
                (FrBSecondLeftMatch == 3 | FrBSecondRightMatch == 3) &&
                (FrBThirdLeftMatch == 3 | FrBThirdRightMatch == 3))) {
            // Right won 1st Recent match
            FirstRightMatch = 3;
            // Left 1st Match score
            FirstLeftMatch = leftTeamScore;
            // Right is the Winner of 2nd Best 1st Match
            SeBFirstRightMatch = 3;
            // Left 2nd Best 1st Match score
            SeBFirstLeftMatch = leftTeamScore;
            //Reset Left & Right scores to 0 then display both scores
            leftTeamScore = 0;
            rightTeamScore = 0;
            displayForLeft(leftTeamScore);
            displayForRight(rightTeamScore);
            // Congratulations dialog toast
            Toast.makeText(getApplicationContext(),
                    "Congratulations!", Toast.LENGTH_SHORT)
                    .show();
        }
        // If Right won the 1st best 3rd Match
        else if (((rightTeamScore == 3) &&
                (FirstLeftMatch == 3 | FirstRightMatch == 3) &&
                (SecondLeftMatch == 3 | SecondRightMatch == 3)) &&
                (FrBFirstLeftMatch == 3 | FrBFirstRightMatch == 3) &&
                (FrBSecondLeftMatch == 3 | FrBSecondRightMatch == 3)) {
            // Right won 3rd Recent match
            ThirdRightMatch = 3;
            // Left 3rd Match score
            ThirdLeftMatch = leftTeamScore;
            // Right is the Winner of 1st Best 3rd Match
            FrBThirdRightMatch = 3;
            // Left 1st Best 3rd Match
            FrBThirdLeftMatch = leftTeamScore;
            //Reset Left & Right scores to 0 then display both scores
            leftTeamScore = 0;
            rightTeamScore = 0;
            displayForLeft(leftTeamScore);
            displayForRight(rightTeamScore);
            // Reset recent matches AND END THE COUNTING
            FirstLeftMatch = 0;
            FirstRightMatch = 0;
            SecondLeftMatch = 0;
            SecondRightMatch = 0;
            ThirdLeftMatch = 0;
            ThirdRightMatch = 0;
            // Congratulations dialog toast
            Toast.makeText(getApplicationContext(),
                    "Congratulations! New Best To Count", Toast.LENGTH_SHORT)
                    .show();
        }
        // If Right Won the 1st best 2nd Match
        else if (((rightTeamScore == 3) &&
                (FirstLeftMatch == 3 | FirstRightMatch == 3)) &&
                (FrBFirstLeftMatch == 3 | FrBFirstRightMatch == 3)) {
            // Right won 2nd Recent match
            SecondRightMatch = 3;
            // Left 2nd Match score
            SecondLeftMatch = leftTeamScore;
            // Right is the Winner of 1st Best 2nd Match
            FrBSecondRightMatch = 3;
            // Left 1st Best 2nd Match
            FrBSecondLeftMatch = leftTeamScore;
            //Reset Left & Right scores to 0 then display both scores
            leftTeamScore = 0;
            rightTeamScore = 0;
            displayForLeft(leftTeamScore);
            displayForRight(rightTeamScore);
            // Congratulations dialog toast
            Toast.makeText(getApplicationContext(),
                    "Congratulations!", Toast.LENGTH_SHORT)
                    .show();
        }
        // If Right Won the 1st best 1st Match
        else if (rightTeamScore == 3) {
            // Right won 1st Recent match
            FirstRightMatch = 3;
            // Left 1st Match score
            FirstLeftMatch = leftTeamScore;
            // Right is the Winner of 1st Best 1st Match
            FrBFirstRightMatch = 3;
            // Left 1st Best 1st Match score
            FrBFirstLeftMatch = leftTeamScore;
            //Reset Left & Right scores to 0 then display both scores
            leftTeamScore = 0;
            rightTeamScore = 0;
            displayForLeft(leftTeamScore);
            displayForRight(rightTeamScore);
            // Congratulations dialog toast
            Toast.makeText(getApplicationContext(),
                    "Congratulations!", Toast.LENGTH_SHORT)
                    .show();
        }

    }

    // When the - for right player/team score is clicked
    public void decRightOne(View view) {
        if (rightTeamScore == 0) {
            // Show an error message as a toast
            Toast.makeText(this, "You cannot have less than 0 point", Toast.LENGTH_SHORT).show();
            // Exit this method early because there's nothing left to do
            return;
        }
        rightTeamScore = rightTeamScore - 1;
        displayForRight(rightTeamScore);
        FirstRightMatch = rightTeamScore;
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