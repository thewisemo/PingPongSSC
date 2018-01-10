package com.example.android.pingpongssc;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

public class UserInputNames extends AppCompatActivity {

    EditText UserInputRightName, UserInputLeftName;
    LinearLayout logo, start;
    Animation upToDown, downToUp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teams_names);
        // Assigns the EditText objects to their places in the layout
        UserInputRightName = findViewById(R.id.right_team_name);
        UserInputLeftName = findViewById(R.id.left_team_name);
        // The Linear views to be animated
        logo = findViewById(R.id.welcome_logo);
        start = findViewById(R.id.user_start);
        // Load animations from anim floder in resources
        upToDown = AnimationUtils.loadAnimation(this, R.anim.uptodown);
        downToUp = AnimationUtils.loadAnimation(this, R.anim.downtoup);
        // Assign animation to each view
        logo.setAnimation(upToDown);
        start.setAnimation(downToUp);

        // To Save user teams names and casting them to the score counter activity which is CountingActivity now
        // Start Button
        // StartCount onClickListener for the START Button
        Button startCount = findViewById(R.id.startBtn);
        startCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // If the EditText Views empty then
                if ((UserInputRightName.getText().toString().trim().equals(""))
                        || UserInputLeftName.getText().toString().trim().equals("")) {
                    // Show warning Toast
                    Toast.makeText(getApplicationContext(), "One OR Both Names Are Empty!", Toast.LENGTH_SHORT).show();
                } else {
                    // implicit intent to pass user teams names to CountingActivity
                    Intent intent = new Intent(getApplicationContext(), CountingActivity.class);
                    intent.putExtra("RIGHTINPUT", UserInputRightName.getText().toString());
                    intent.putExtra("LEFTINPUT", UserInputLeftName.getText().toString());
                    startActivity(intent);
                }
            }
        });
    }
}