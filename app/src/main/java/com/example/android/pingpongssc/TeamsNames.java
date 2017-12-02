package com.example.android.pingpongssc;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android.pingpongssc.R;

public class TeamsNames extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teams_names);

        final EditText RightTeamName = findViewById(R.id.rightTeamName);
        final EditText LeftTeamName = findViewById(R.id.leftTeamName);

        /**
         * Save user teams names and casting them to the score counter activity which is activity_main now
         */

        Button startCount = findViewById(R.id.startBtn);
        startCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if ( ( RightTeamName.getText().toString().trim().equals("")) || LeftTeamName.getText().toString().trim().equals("") )
                {
                    Toast.makeText(getApplicationContext(), "Names are empty!", Toast.LENGTH_SHORT).show();
                }
                else
                {

                /**
                 * implicit intent to pass user teams names to HomeActivity
                 */
                Intent intent = new Intent(getApplicationContext(), com.example.android.pingpongssc.HomeActivity.class);
                intent.putExtra("RIGHT", RightTeamName.getText().toString());
                intent.putExtra("LEFT", LeftTeamName.getText().toString());
                startActivity(intent);

            }}
        });

    }

}
