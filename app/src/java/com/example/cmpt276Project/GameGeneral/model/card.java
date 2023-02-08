//Class holds index of images display on a card, index of the card
package com.example.cmpt276project.GameGeneral.model;

import android.graphics.Bitmap;

import java.util.ArrayList;

public class Card {

    private int[] imageIndexArray;
    private int cardIndex;
    ArrayList<Bitmap> CardImages;
    public Card(int[] imageIndexArray, int cardIndex,ArrayList<Bitmap> CardImages) {
        this.imageIndexArray = imageIndexArray;
        this.cardIndex = cardIndex;
        this.CardImages=CardImages;
    }
    public ArrayList<Bitmap> getCardImages() { return CardImages; }

    public void setImageIndexArray(int[] imageIndexArray) {
        this.imageIndexArray = imageIndexArray;
    }

    public void setCardIndex(int cardIndex) {
        this.cardIndex = cardIndex;
    }

    public void setCardImages(ArrayList<Bitmap> cardImages) {
        CardImages = cardImages;
    }

    public int[] getImageIndexArray() {
        return imageIndexArray;
    }

    public int getCardIndex() {
        return cardIndex;
    }

}
