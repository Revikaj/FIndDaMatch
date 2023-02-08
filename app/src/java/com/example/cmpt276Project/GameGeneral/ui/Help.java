//Class provides information about game and creators
package com.example.cmpt276project.GameGeneral.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.cmpt276project.R;

public class Help extends AppCompatActivity {

    public static Intent makeIntent(Context context){
        return new Intent(context,Help.class);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.help2);

    }

}