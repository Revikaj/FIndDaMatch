//Class holds information of the score, which are user's name and time played
package com.example.cmpt276project.GameGeneral.model;

public class ScoreInfo {
    private int time;
    private String name;

    public ScoreInfo(int time, String name) {
        this.time = time;
        this.name = name;
    }

    public int getTime() {
        return time;
    }

    public String getName() {
        return name;
    }

}
