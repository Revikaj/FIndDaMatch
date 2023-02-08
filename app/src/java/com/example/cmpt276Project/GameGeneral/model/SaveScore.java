//Saves score (includes name, date and time played) of the user when the game ends, data to be used in displaying High scores
package com.example.cmpt276project.GameGeneral.model;

import java.util.ArrayList;

public class SaveScore {
    ArrayList<ScoreInfo> newScore = new ArrayList<>();

    //Singleton support
    private static SaveScore instance;
    private SaveScore() {
        //prevent other instancing
    }


    public static SaveScore getInstance() {
        if (instance == null) {
            instance = new SaveScore();
        }
        return instance;
    }

    public void add(ScoreInfo score) {
        newScore.add(score);
    }

    public ScoreInfo get(int index) {
        return newScore.get(index);
    }

    public void remove(int index) {
        newScore.remove(index);
    }

    public int size() {
        return newScore.size();
    }
}
