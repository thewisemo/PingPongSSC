package com.example.android.pingpongssc;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

public class WelcomeSplashActivity extends AppCompatActivity {
    // This code to put a splash screen before the WelcomeSplashActivity screen with 3 seconds

    int SPLASH_TIME_OUT = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        //This is the handler for the splash screen from activity_home to activity_teams_names
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run(){
                Intent homeIntent = new Intent(WelcomeSplashActivity.this, UserInputNames.class);
                startActivity(homeIntent);
                finish();
            }
        } ,SPLASH_TIME_OUT);

    }
}
