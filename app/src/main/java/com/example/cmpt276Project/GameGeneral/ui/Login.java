//Allow users save their highscore or start new game without saving their score or go back to main menu. User can type in any name they want
package com.example.cmpt276project.GameGeneral.ui;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cmpt276project.R;
import com.example.cmpt276project.GameGeneral.model.ExportCard;
import com.example.cmpt276project.GameGeneral.model.SaveScore;
import com.example.cmpt276project.GameGeneral.model.SaveTime;
import com.example.cmpt276project.GameGeneral.model.Score;
import com.example.cmpt276project.GameGeneral.model.ScoreInfo;

public class Login extends AppCompatActivity {
    private Score user;
    private SaveTime newTime = SaveTime.getInstance();
    private SaveScore newScoreList = SaveScore.getInstance();
    private ExportCard exportCard = ExportCard.getInstance();

    public static Intent makeIntent(Context c){
        return new Intent(c, Login.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout(width, height);

        //Sound effects
        MediaPlayer endS;
        endS = MediaPlayer.create(this, R.raw.end);
        endS.setAudioStreamType(AudioManager.STREAM_MUSIC);
        endS.setLooping(false);
        endS.start();

        Button save = findViewById(R.id.save_btn);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText newScore = findViewById(R.id.editTextTextPersonName);
                String newScoreName = newScore.getText().toString();

                newScoreList.add(new ScoreInfo(newTime.get(0), newScoreName));
                newTime.remove(0);

                Intent intent = HighScores.makeIntent(Login.this);
                startActivity(intent);
                finish();
            }
        });

        Button newG = findViewById(R.id.new_game);
        newG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = Game.makeIntent(Login.this);
                startActivity(intent);            }
        });

        Button cancel = findViewById(R.id.cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = MainActivity.makeIntent(Login.this);
                startActivity(intent);
            }
        });

        Button export = findViewById(R.id.btnExport);
        export.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ExportCard.exportCanvas(getContentResolver());

                Toast exportMessage = Toast.makeText(getApplicationContext(),
                        "Cards exported to photos application!",
                        Toast.LENGTH_SHORT);

                exportMessage.show();

                exportCard.clear();
            }
        });
    }
}