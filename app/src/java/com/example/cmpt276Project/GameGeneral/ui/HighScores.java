//Displays High scores of users based on user's input name, date and time played. Displayed High scores based on game settings. User can reset High score
package com.example.cmpt276project.GameGeneral.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.cmpt276project.R;
import com.example.cmpt276project.GameGeneral.model.SaveScore;
import com.example.cmpt276project.GameGeneral.model.Score;
import com.example.cmpt276project.GameGeneral.model.Settings;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

//High score activity that displays top 5 high scores
public class HighScores extends AppCompatActivity {
    private SharedPreferences gamePrefs;
    private SaveScore newScoreList = SaveScore.getInstance();
    Settings settings = Settings.getInstance();
    public final String GAME_PREFS = String.valueOf(settings.getPile()) + settings.getOrder();

    public static Intent makeIntent(Context c){
        return new Intent(c, HighScores.class);
    }

    @Override
    public void onBackPressed() {
        Intent intent = MainActivity.makeIntent(HighScores.this);
        startActivity(intent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_scores);
        TextView modeText = findViewById(R.id.txtScoreMode);
        modeText.setText(getString(R.string.Pile) + settings.getPile() + getString(R.string.order2) + settings.getOrder());
        populateScoreView(getSharedPreferences(GAME_PREFS, 0)
                .getString(GAME_PREFS,"").split("\\|").length);


        if (newScoreList.size() > 0) {
            setHighScore(newScoreList.get(0).getTime(), newScoreList.get(0).getName());
            newScoreList.remove(0);
        }

        gamePrefs = getSharedPreferences(GAME_PREFS, 0);

        TextView scoreView = findViewById(R.id.listHighScores);
        SharedPreferences scorePrefs = getSharedPreferences(GAME_PREFS, 0);

        //get saved scores and convert to strings
        String[] savedScores = scorePrefs.getString(GAME_PREFS, "").split("\\|");
        populateScoreView(savedScores.length);
        StringBuilder buildScores = new StringBuilder();
        for(String score : savedScores) {
            buildScores.append(score).append("\n");
        }
        scoreView.setText(buildScores.toString());

        Button close = findViewById(R.id.btnCloseScores);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent intent = MainActivity.makeIntent(HighScores.this);
                startActivity(intent);
            }
        });

        Button reset = findViewById(R.id.btnResetScores);
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor scoreClear = gamePrefs.edit();
                scoreClear.clear();
                scoreClear.apply();
                populateScoreView(1);
                finish();
                Intent intent = MainActivity.makeIntent(HighScores.this);
                startActivity(intent);
            }
        });
    }

    //prepopulate high score list
    public void populateScoreView(int lengthCheck) {

        if (lengthCheck == 1) {
            setHighScore(60, getString(R.string.Logan));
            setHighScore(50, getString(R.string.Kelvin));
            setHighScore(35, getString(R.string.Sara));
            setHighScore(20, getString(R.string.Aaron));
            setHighScore(12, getString(R.string.Jessy));
        }
    }

    //code taken from
    //https://code.tutsplus.com/tutorials/android-sdk-create-an-arithmetic-game-high-scores-and-state-data--mobile-18825
    public void setHighScore(int time, String name) {
        SharedPreferences prefs = this.getSharedPreferences(GAME_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        DateFormat dateForm = new SimpleDateFormat("dd MMMM yyyy");
        String dateOutput = dateForm.format(new Date());

        String scores = prefs.getString(GAME_PREFS, "");

        if (scores.length() > 0) {
            List<Score> scoresList = new ArrayList<>();
            String[] scoresArray = scores.split("\\|");

            for (String str : scoresArray) {
                String[] part = str.split(" ", 3);
                scoresList.add(new Score(Integer.parseInt(part[0]), part[1], part[2]));
            }

            Score newScore = new Score(time, name, dateOutput);
            scoresList.add(newScore);

            //sorts high scores list
            Collections.sort(scoresList);

            StringBuilder buildScores = new StringBuilder();
            for(int i = 0; i < scoresList.size(); i++) {
                if (i >= 5) {
                    break; //no more than 5 high scores
                }
                if (i > 0) {
                    buildScores.append("|");
                }
                buildScores.append(scoresList.get(i).toString());
            }
            //write to prefs
            editor.putString(GAME_PREFS, buildScores.toString());
        }
        else {
            //if no scores on list simply add score
            editor.putString(GAME_PREFS, time + " " + name + " " + dateOutput);
        }
        editor.apply();
    }
}
