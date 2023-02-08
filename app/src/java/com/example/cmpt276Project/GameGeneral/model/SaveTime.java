//Saves time played of the user, data to be used in displaying High scores
package com.example.cmpt276project.GameGeneral.model;

import java.util.ArrayList;

public class SaveTime {
    ArrayList<Integer> newTime = new ArrayList<>();

    //Singleton support
    private static SaveTime instance;
    private SaveTime() {
        //prevent other instancing
    }

    public static SaveTime getInstance() {
        if (instance == null) {
            instance = new SaveTime();
        }

        return instance;
    }

    public void add(int time) {
        newTime.add(time);
    }

    public int get(int index) {
        return newTime.get(index);
    }

    public void remove(int index) {
        newTime.remove(index);
    }
}
