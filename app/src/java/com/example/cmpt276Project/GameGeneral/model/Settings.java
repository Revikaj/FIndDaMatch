//Class holds all information about user's options before playing, used throughout the app's classes to display correctly based on user's choices
//Includes theme (main dish, dessert), order (2,3,5), mode (image, image-word), pile (number of cards user want to play in 1 game, can only be smaller or equals the maximum cards possible of the order)
package com.example.cmpt276project.GameGeneral.model;

import android.graphics.Bitmap;

import java.util.ArrayList;

public class Settings {
    private String theme;
    private int orderi;
    private String pile;
    private String mode;
    private String levels;

    //Singleton Support
    private static Settings instance;
    public Settings(){}
    public static Settings getInstance(){
        if(instance == null){
            instance = new Settings();
            instance.theme = "Food";
            instance.orderi = 2;
            instance.mode = "Image";
            instance.levels="Easy";
        }
        return instance;
    }

    public String getTheme() {
        return theme;
    }

    public void setOrder(int order) {
        this.orderi = order;
   }

    public int getOrder() {
        return orderi;
    }

    public String getMode(){
        return mode;
    }

    public void setMode(String mode){
        this.mode=mode;
    }

    public int getImageNumPerCard() {
        int imageNum = 0;
        if(orderi == 2){
            imageNum = 3;
        }
        else if(orderi == 3){
            imageNum = 4;
        }
        else if(orderi == 5){
            imageNum = 6;
        }
        return imageNum;
    }


    public void setTheme(String theme) {
        this.theme = theme;
    }

    public int getPile() {
        int pn=0;
        if(pile.equals("5"))
            pn=5;
        if(pile.equals("10"))
            pn=10;
        if(pile.equals("15"))
            pn=15;
        if(pile.equals("20"))
            pn=20;
        if(pile.equals("All"))
        {
            if(getOrder()==2)
                pn=7;
            if(getOrder()==3)
                pn=13;
            if(getOrder()==5)
                pn=31;
        }

        return pn;
    }

    public void setPile(String pile) {
        this.pile = pile;


    }
//checks if the number of flickr images chosen is enough for the order chosen
    public Boolean FlickImageCheck(ArrayList<Bitmap> flickr){
        if(getOrder()==2) {
            return flickr.size() >= 7;
        }
        else if(getOrder()==3) {
            return flickr.size() >= 13;
        }
       else if (getOrder() == 5) {
            return flickr.size() >= 31;
       }
       return false;
    }

    public String getLevel(){
        return levels;
    }

    public void setlevel(String levels){
        this.levels=levels;
    }
}
