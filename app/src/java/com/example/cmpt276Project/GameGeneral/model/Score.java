//Class includes user's name, date and time played. Compares scores to places scores in order
package com.example.cmpt276project.GameGeneral.model;

public class Score implements Comparable<Score> {
    private int time;
    private String name;
    private String date;

    public Score(int time, String name, String date) {
        this.time = time;
        this.name = name;
        this.date = date;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String toString() {
        return time + " " + name + " " + date;
    }


    @Override
    public int compareTo(Score score) {
        int compareScore = score.getTime();
        return this.time - compareScore;
    }
}
