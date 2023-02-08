//Main menu allows user start game, choose different game settings in Options, view High score, learn how to play game and information about game and creators for Help
package com.example.cmpt276project.GameGeneral.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.example.cmpt276project.R;
import com.example.cmpt276project.GameGeneral.model.Settings;


//Class for Main menu activity, has buttons for options, help, and to play the game
public class MainActivity extends AppCompatActivity {

    public static Intent makeIntent(Context c) {
        return new Intent(c, MainActivity.class);
    }

    @Override
    public void onBackPressed() {
        finishAffinity();
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadSettings();

        //options button to start option menu activity
        Settings settings=Settings.getInstance();

        Button options = findViewById(R.id.btnOptions);
        options.setOnClickListener(new View.OnClickListener() {
            //on press go to options menu
            @Override
            public void onClick(View v) {
                Intent intent = Options.makeIntent(MainActivity.this);
                startActivity(intent);
            }
        });

        //help button to start help activity
        Button help = findViewById(R.id.btnHelp);
        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = Help.makeIntent(MainActivity.this);

                startActivity(intent);
            }
        });

        //play button to start game activity
        Button game = findViewById(R.id.btnPlay);
        game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = getSupportFragmentManager();
                SettingConfirm dialog = new SettingConfirm();
                dialog.show(manager, "Setting Confirm");

                Log.i("TAG", "Dialog");
            }
        });

        Button highScores = findViewById(R.id.btnHighScores);
        highScores.setGravity(Gravity.BOTTOM);
        highScores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = HighScores.makeIntent(MainActivity.this);
                startActivity(intent);
            }
        });
    }

    public void loadSettings()
    {
        SharedPreferences prefs1 = this.getSharedPreferences("PilePrefs", MODE_PRIVATE);
        SharedPreferences prefs2 = this.getSharedPreferences("ThemePrefs", MODE_PRIVATE);
        SharedPreferences prefs3 = this.getSharedPreferences("OrderPrefs", MODE_PRIVATE);
        SharedPreferences prefs4 = this.getSharedPreferences("ModePrefs",MODE_PRIVATE);
        SharedPreferences prefs5 = this.getSharedPreferences("LevelPref",MODE_PRIVATE);
        Settings settings=Settings.getInstance();
        String defaultVal=this.getResources().getString(R.string.Default_pile);
        settings.setPile(prefs1.getString("PilePrefs",defaultVal));

        int defaultVal1=this.getResources().getInteger(R.integer.Default_order_cards);
        settings.setOrder( prefs3.getInt("OrderPrefs", defaultVal1));

        String defaultVal3=this.getResources().getString(R.string.Default_theme);
        settings.setTheme(prefs2.getString("ThemePrefs",defaultVal3));

        String defaultVal4=getString(R.string.img);
        settings.setMode(prefs4.getString("ModePrefs",defaultVal4));

        String defvalue5=this.getResources().getString(R.string.Default_level);
        settings.setlevel(prefs5.getString("LevelPref",defvalue5));

    }
}