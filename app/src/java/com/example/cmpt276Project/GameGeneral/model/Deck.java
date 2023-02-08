//Class holds an ArrayList of Cards and populate Deck based on the chosen order
package com.example.cmpt276project.GameGeneral.model;

import android.graphics.Bitmap;

import java.util.ArrayList;

public class Deck {
   private ArrayList<Card> cards = new ArrayList<>();

   Settings settings=Settings.getInstance();

   ArrayList<Bitmap> cardDessert= new ArrayList<>();

    //populates deck with chosen order's number of cards that fit requirements for the game
    public void populateDeck(ArrayList<Bitmap> arrayList) {

        int savedOrder = settings.getOrder();
        int SavePile=settings.getPile();

        if (savedOrder == 2) {
            for (int i = 0; i < SavePile; i++) {
                int[] imageIndexArray = {0, 0, 0};
                //the deck still needs to be populated randomly

                //each card has 1 and only 1 identical number to any other card
                //number can be used to add image buttons to each card
                switch (i) {
                    case 0:
                        imageIndexArray[0] = 0;
                        imageIndexArray[1] = 1;
                        imageIndexArray[2] = 2;
                        cardDessert.add(arrayList.get(imageIndexArray[0]));
                        cardDessert.add(arrayList.get(imageIndexArray[1]));
                        cardDessert.add(arrayList.get(imageIndexArray[2]));
                        break;

                    case 1:
                        imageIndexArray[0] = 0;
                        imageIndexArray[1] = 3;
                        imageIndexArray[2] = 4;
                        cardDessert.add(arrayList.get(imageIndexArray[0]));
                        cardDessert.add(arrayList.get(imageIndexArray[1]));
                        cardDessert.add(arrayList.get(imageIndexArray[2]));
                        break;

                    case 2:
                        imageIndexArray[0] = 0;
                        imageIndexArray[1] = 5;
                        imageIndexArray[2] = 6;
                        cardDessert.add(arrayList.get(imageIndexArray[0]));
                        cardDessert.add(arrayList.get(imageIndexArray[1]));
                        cardDessert.add(arrayList.get(imageIndexArray[2]));
                        break;

                    case 3:
                        imageIndexArray[0] = 1;
                        imageIndexArray[1] = 3;
                        imageIndexArray[2] = 5;
                        cardDessert.add(arrayList.get(imageIndexArray[0]));
                        cardDessert.add(arrayList.get(imageIndexArray[1]));
                        cardDessert.add(arrayList.get(imageIndexArray[2]));
                        break;

                    case 4:
                        imageIndexArray[0] = 1;
                        imageIndexArray[1] = 4;
                        imageIndexArray[2] = 6;
                        cardDessert.add(arrayList.get(imageIndexArray[0]));
                        cardDessert.add(arrayList.get(imageIndexArray[1]));
                        cardDessert.add(arrayList.get(imageIndexArray[2]));
                        break;

                    case 5:
                        imageIndexArray[0] = 2;
                        imageIndexArray[1] = 3;
                        imageIndexArray[2] = 6;
                        cardDessert.add(arrayList.get(imageIndexArray[0]));
                        cardDessert.add(arrayList.get(imageIndexArray[1]));
                        cardDessert.add(arrayList.get(imageIndexArray[2]));
                        break;

                    case 6:
                        imageIndexArray[0] = 2;
                        imageIndexArray[1] = 4;
                        imageIndexArray[2] = 5;
                        cardDessert.add(arrayList.get(imageIndexArray[0]));
                        cardDessert.add(arrayList.get(imageIndexArray[1]));
                        cardDessert.add(arrayList.get(imageIndexArray[2]));
                        break;
                }
                cards.add(new Card(imageIndexArray, i, cardDessert));
            }
        }

        else if (savedOrder == 3) {
            for (int i = 0; i < SavePile; i++) {
                int[] imageIndexArray = {0, 0, 0, 0};

                switch (i) {
                    case 0:
                        imageIndexArray[0] = 0;
                        imageIndexArray[1] = 1;
                        imageIndexArray[2] = 2;
                        imageIndexArray[3] = 9;
                        cardDessert.add(arrayList.get(imageIndexArray[0]));
                        cardDessert.add(arrayList.get(imageIndexArray[1]));
                        cardDessert.add(arrayList.get(imageIndexArray[2]));
                        cardDessert.add(arrayList.get(imageIndexArray[3]));
                        break;

                    case 1:
                        imageIndexArray[0] = 9;
                        imageIndexArray[1] = 3;
                        imageIndexArray[2] = 4;
                        imageIndexArray[3] = 5;
                        cardDessert.add(arrayList.get(imageIndexArray[0]));
                        cardDessert.add(arrayList.get(imageIndexArray[1]));
                        cardDessert.add(arrayList.get(imageIndexArray[2]));
                        cardDessert.add(arrayList.get(imageIndexArray[3]));
                        break;

                    case 2:
                        imageIndexArray[0] = 8;
                        imageIndexArray[1] = 9;
                        imageIndexArray[2] = 6;
                        imageIndexArray[3] = 7;
                        cardDessert.add(arrayList.get(imageIndexArray[0]));
                        cardDessert.add(arrayList.get(imageIndexArray[1]));
                        cardDessert.add(arrayList.get(imageIndexArray[2]));
                        cardDessert.add(arrayList.get(imageIndexArray[3]));
                        break;

                    case 3:
                        imageIndexArray[0] = 0;
                        imageIndexArray[1] = 10;
                        imageIndexArray[2] = 3;
                        imageIndexArray[3] = 6;
                        cardDessert.add(arrayList.get(imageIndexArray[0]));
                        cardDessert.add(arrayList.get(imageIndexArray[1]));
                        cardDessert.add(arrayList.get(imageIndexArray[2]));
                        cardDessert.add(arrayList.get(imageIndexArray[3]));
                        break;

                    case 4:
                        imageIndexArray[0] = 1;
                        imageIndexArray[1] = 10;
                        imageIndexArray[2] = 4;
                        imageIndexArray[3] = 7;
                        cardDessert.add(arrayList.get(imageIndexArray[0]));
                        cardDessert.add(arrayList.get(imageIndexArray[1]));
                        cardDessert.add(arrayList.get(imageIndexArray[2]));
                        cardDessert.add(arrayList.get(imageIndexArray[3]));
                        break;

                    case 5:
                        imageIndexArray[0] = 8;
                        imageIndexArray[1] = 2;
                        imageIndexArray[2] = 10;
                        imageIndexArray[3] = 5;
                        cardDessert.add(arrayList.get(imageIndexArray[0]));
                        cardDessert.add(arrayList.get(imageIndexArray[1]));
                        cardDessert.add(arrayList.get(imageIndexArray[2]));
                        cardDessert.add(arrayList.get(imageIndexArray[3]));
                        break;

                    case 6:
                        imageIndexArray[0] = 0;
                        imageIndexArray[1] = 8;
                        imageIndexArray[2] = 11;
                        imageIndexArray[3] = 4;
                        cardDessert.add(arrayList.get(imageIndexArray[0]));
                        cardDessert.add(arrayList.get(imageIndexArray[1]));
                        cardDessert.add(arrayList.get(imageIndexArray[2]));
                        cardDessert.add(arrayList.get(imageIndexArray[3]));
                        break;

                    case 7:
                        imageIndexArray[0] = 1;
                        imageIndexArray[1] = 11;
                        imageIndexArray[2] = 5;
                        imageIndexArray[3] = 6;
                        cardDessert.add(arrayList.get(imageIndexArray[0]));
                        cardDessert.add(arrayList.get(imageIndexArray[1]));
                        cardDessert.add(arrayList.get(imageIndexArray[2]));
                        cardDessert.add(arrayList.get(imageIndexArray[3]));
                        break;

                    case 8:
                        imageIndexArray[0] = 11;
                        imageIndexArray[1] = 2;
                        imageIndexArray[2] = 3;
                        imageIndexArray[3] = 7;
                        cardDessert.add(arrayList.get(imageIndexArray[0]));
                        cardDessert.add(arrayList.get(imageIndexArray[1]));
                        cardDessert.add(arrayList.get(imageIndexArray[2]));
                        cardDessert.add(arrayList.get(imageIndexArray[3]));
                        break;

                    case 9:
                        imageIndexArray[0] = 0;
                        imageIndexArray[1] = 12;
                        imageIndexArray[2] = 5;
                        imageIndexArray[3] = 7;
                        cardDessert.add(arrayList.get(imageIndexArray[0]));
                        cardDessert.add(arrayList.get(imageIndexArray[1]));
                        cardDessert.add(arrayList.get(imageIndexArray[2]));
                        cardDessert.add(arrayList.get(imageIndexArray[3]));
                        break;

                    case 10:
                        imageIndexArray[0] = 8;
                        imageIndexArray[1] = 1;
                        imageIndexArray[2] = 3;
                        imageIndexArray[3] = 12;
                        cardDessert.add(arrayList.get(imageIndexArray[0]));
                        cardDessert.add(arrayList.get(imageIndexArray[1]));
                        cardDessert.add(arrayList.get(imageIndexArray[2]));
                        cardDessert.add(arrayList.get(imageIndexArray[3]));
                        break;

                    case 11:
                        imageIndexArray[0] = 12;
                        imageIndexArray[1] = 2;
                        imageIndexArray[2] = 4;
                        imageIndexArray[3] = 6;
                        cardDessert.add(arrayList.get(imageIndexArray[0]));
                        cardDessert.add(arrayList.get(imageIndexArray[1]));
                        cardDessert.add(arrayList.get(imageIndexArray[2]));
                        cardDessert.add(arrayList.get(imageIndexArray[3]));
                        break;

                    case 12:
                        imageIndexArray[0] = 9;
                        imageIndexArray[1] = 10;
                        imageIndexArray[2] = 11;
                        imageIndexArray[3] = 12;
                        cardDessert.add(arrayList.get(imageIndexArray[0]));
                        cardDessert.add(arrayList.get(imageIndexArray[1]));
                        cardDessert.add(arrayList.get(imageIndexArray[2]));
                        cardDessert.add(arrayList.get(imageIndexArray[3]));
                        break;
                }
                cards.add(new Card(imageIndexArray, i, cardDessert));
            }
        }

        else if (savedOrder == 5) {
            for (int i = 0; i < SavePile; i++) {
                int[] imageIndexArray = {0, 0, 0, 0, 0, 0};

                switch (i) {
                    case 0:
                        imageIndexArray[0] = 0;
                        imageIndexArray[1] = 1;
                        imageIndexArray[2] = 2;
                        imageIndexArray[3] = 3;
                        imageIndexArray[4] = 4;
                        imageIndexArray[5] = 25;
                        cardDessert.add(arrayList.get(imageIndexArray[0]));
                        cardDessert.add(arrayList.get(imageIndexArray[1]));
                        cardDessert.add(arrayList.get(imageIndexArray[2]));
                        cardDessert.add(arrayList.get(imageIndexArray[3]));
                        cardDessert.add(arrayList.get(imageIndexArray[4]));
                        cardDessert.add(arrayList.get(imageIndexArray[5]));
                        break;

                    case 1:
                        imageIndexArray[0] = 5;
                        imageIndexArray[1] = 6;
                        imageIndexArray[2] = 7;
                        imageIndexArray[3] = 8;
                        imageIndexArray[4] = 9;
                        imageIndexArray[5] = 25;
                        cardDessert.add(arrayList.get(imageIndexArray[0]));
                        cardDessert.add(arrayList.get(imageIndexArray[1]));
                        cardDessert.add(arrayList.get(imageIndexArray[2]));
                        cardDessert.add(arrayList.get(imageIndexArray[3]));
                        cardDessert.add(arrayList.get(imageIndexArray[4]));
                        cardDessert.add(arrayList.get(imageIndexArray[5]));
                        break;

                    case 2:
                        imageIndexArray[0] = 10;
                        imageIndexArray[1] = 11;
                        imageIndexArray[2] = 12;
                        imageIndexArray[3] = 13;
                        imageIndexArray[4] = 14;
                        imageIndexArray[5] = 25;
                        cardDessert.add(arrayList.get(imageIndexArray[0]));
                        cardDessert.add(arrayList.get(imageIndexArray[1]));
                        cardDessert.add(arrayList.get(imageIndexArray[2]));
                        cardDessert.add(arrayList.get(imageIndexArray[3]));
                        cardDessert.add(arrayList.get(imageIndexArray[4]));
                        cardDessert.add(arrayList.get(imageIndexArray[5]));
                        break;

                    case 3:
                        imageIndexArray[0] = 15;
                        imageIndexArray[1] = 16;
                        imageIndexArray[2] = 17;
                        imageIndexArray[3] = 18;
                        imageIndexArray[4] = 19;
                        imageIndexArray[5] = 25;
                        cardDessert.add(arrayList.get(imageIndexArray[0]));
                        cardDessert.add(arrayList.get(imageIndexArray[1]));
                        cardDessert.add(arrayList.get(imageIndexArray[2]));
                        cardDessert.add(arrayList.get(imageIndexArray[3]));
                        cardDessert.add(arrayList.get(imageIndexArray[4]));
                        cardDessert.add(arrayList.get(imageIndexArray[5]));
                        break;

                    case 4:
                        imageIndexArray[0] = 20;
                        imageIndexArray[1] = 21;
                        imageIndexArray[2] = 22;
                        imageIndexArray[3] = 23;
                        imageIndexArray[4] = 24;
                        imageIndexArray[5] = 25;
                        cardDessert.add(arrayList.get(imageIndexArray[0]));
                        cardDessert.add(arrayList.get(imageIndexArray[1]));
                        cardDessert.add(arrayList.get(imageIndexArray[2]));
                        cardDessert.add(arrayList.get(imageIndexArray[3]));
                        cardDessert.add(arrayList.get(imageIndexArray[4]));
                        cardDessert.add(arrayList.get(imageIndexArray[5]));
                        break;

                    case 5:
                        imageIndexArray[0] = 0;
                        imageIndexArray[1] = 5;
                        imageIndexArray[2] = 10;
                        imageIndexArray[3] = 15;
                        imageIndexArray[4] = 20;
                        imageIndexArray[5] = 26;
                        cardDessert.add(arrayList.get(imageIndexArray[0]));
                        cardDessert.add(arrayList.get(imageIndexArray[1]));
                        cardDessert.add(arrayList.get(imageIndexArray[2]));
                        cardDessert.add(arrayList.get(imageIndexArray[3]));
                        cardDessert.add(arrayList.get(imageIndexArray[4]));
                        cardDessert.add(arrayList.get(imageIndexArray[5]));
                        break;

                    case 6:
                        imageIndexArray[0] = 1;
                        imageIndexArray[1] = 6;
                        imageIndexArray[2] = 11;
                        imageIndexArray[3] = 16;
                        imageIndexArray[4] = 21;
                        imageIndexArray[5] = 26;
                        cardDessert.add(arrayList.get(imageIndexArray[0]));
                        cardDessert.add(arrayList.get(imageIndexArray[1]));
                        cardDessert.add(arrayList.get(imageIndexArray[2]));
                        cardDessert.add(arrayList.get(imageIndexArray[3]));
                        cardDessert.add(arrayList.get(imageIndexArray[4]));
                        cardDessert.add(arrayList.get(imageIndexArray[5]));
                        break;

                    case 7:
                        imageIndexArray[0] = 2;
                        imageIndexArray[1] = 7;
                        imageIndexArray[2] = 12;
                        imageIndexArray[3] = 17;
                        imageIndexArray[4] = 22;
                        imageIndexArray[5] = 26;
                        cardDessert.add(arrayList.get(imageIndexArray[0]));
                        cardDessert.add(arrayList.get(imageIndexArray[1]));
                        cardDessert.add(arrayList.get(imageIndexArray[2]));
                        cardDessert.add(arrayList.get(imageIndexArray[3]));
                        cardDessert.add(arrayList.get(imageIndexArray[4]));
                        cardDessert.add(arrayList.get(imageIndexArray[5]));
                        break;

                    case 8:
                        imageIndexArray[0] = 3;
                        imageIndexArray[1] = 8;
                        imageIndexArray[2] = 13;
                        imageIndexArray[3] = 18;
                        imageIndexArray[4] = 23;
                        imageIndexArray[5] = 26;
                        cardDessert.add(arrayList.get(imageIndexArray[0]));
                        cardDessert.add(arrayList.get(imageIndexArray[1]));
                        cardDessert.add(arrayList.get(imageIndexArray[2]));
                        cardDessert.add(arrayList.get(imageIndexArray[3]));
                        cardDessert.add(arrayList.get(imageIndexArray[4]));
                        cardDessert.add(arrayList.get(imageIndexArray[5]));
                        break;

                    case 9:
                        imageIndexArray[0] = 4;
                        imageIndexArray[1] = 9;
                        imageIndexArray[2] = 14;
                        imageIndexArray[3] = 19;
                        imageIndexArray[4] = 24;
                        imageIndexArray[5] = 26;
                        cardDessert.add(arrayList.get(imageIndexArray[0]));
                        cardDessert.add(arrayList.get(imageIndexArray[1]));
                        cardDessert.add(arrayList.get(imageIndexArray[2]));
                        cardDessert.add(arrayList.get(imageIndexArray[3]));
                        cardDessert.add(arrayList.get(imageIndexArray[4]));
                        cardDessert.add(arrayList.get(imageIndexArray[5]));
                        break;

                    case 10:
                        imageIndexArray[0] = 0;
                        imageIndexArray[1] = 6;
                        imageIndexArray[2] = 12;
                        imageIndexArray[3] = 18;
                        imageIndexArray[4] = 24;
                        imageIndexArray[5] = 27;
                        cardDessert.add(arrayList.get(imageIndexArray[0]));
                        cardDessert.add(arrayList.get(imageIndexArray[1]));
                        cardDessert.add(arrayList.get(imageIndexArray[2]));
                        cardDessert.add(arrayList.get(imageIndexArray[3]));
                        cardDessert.add(arrayList.get(imageIndexArray[4]));
                        cardDessert.add(arrayList.get(imageIndexArray[5]));
                        break;

                    case 11:
                        imageIndexArray[0] = 1;
                        imageIndexArray[1] = 7;
                        imageIndexArray[2] = 13;
                        imageIndexArray[3] = 19;
                        imageIndexArray[4] = 20;
                        imageIndexArray[5] = 27;
                        cardDessert.add(arrayList.get(imageIndexArray[0]));
                        cardDessert.add(arrayList.get(imageIndexArray[1]));
                        cardDessert.add(arrayList.get(imageIndexArray[2]));
                        cardDessert.add(arrayList.get(imageIndexArray[3]));
                        cardDessert.add(arrayList.get(imageIndexArray[4]));
                        cardDessert.add(arrayList.get(imageIndexArray[5]));
                        break;

                    case 12:
                        imageIndexArray[0] = 2;
                        imageIndexArray[1] = 8;
                        imageIndexArray[2] = 14;
                        imageIndexArray[3] = 15;
                        imageIndexArray[4] = 21;
                        imageIndexArray[5] = 27;
                        cardDessert.add(arrayList.get(imageIndexArray[0]));
                        cardDessert.add(arrayList.get(imageIndexArray[1]));
                        cardDessert.add(arrayList.get(imageIndexArray[2]));
                        cardDessert.add(arrayList.get(imageIndexArray[3]));
                        cardDessert.add(arrayList.get(imageIndexArray[4]));
                        cardDessert.add(arrayList.get(imageIndexArray[5]));
                        break;

                    case 13:
                        imageIndexArray[0] = 3;
                        imageIndexArray[1] = 9;
                        imageIndexArray[2] = 10;
                        imageIndexArray[3] = 16;
                        imageIndexArray[4] = 22;
                        imageIndexArray[5] = 27;
                        cardDessert.add(arrayList.get(imageIndexArray[0]));
                        cardDessert.add(arrayList.get(imageIndexArray[1]));
                        cardDessert.add(arrayList.get(imageIndexArray[2]));
                        cardDessert.add(arrayList.get(imageIndexArray[3]));
                        cardDessert.add(arrayList.get(imageIndexArray[4]));
                        cardDessert.add(arrayList.get(imageIndexArray[5]));
                        break;

                    case 14:
                        imageIndexArray[0] = 4;
                        imageIndexArray[1] = 5;
                        imageIndexArray[2] = 11;
                        imageIndexArray[3] = 17;
                        imageIndexArray[4] = 23;
                        imageIndexArray[5] = 27;
                        cardDessert.add(arrayList.get(imageIndexArray[0]));
                        cardDessert.add(arrayList.get(imageIndexArray[1]));
                        cardDessert.add(arrayList.get(imageIndexArray[2]));
                        cardDessert.add(arrayList.get(imageIndexArray[3]));
                        cardDessert.add(arrayList.get(imageIndexArray[4]));
                        cardDessert.add(arrayList.get(imageIndexArray[5]));
                        break;

                    case 15:
                        imageIndexArray[0] = 0;
                        imageIndexArray[1] = 7;
                        imageIndexArray[2] = 14;
                        imageIndexArray[3] = 16;
                        imageIndexArray[4] = 23;
                        imageIndexArray[5] = 28;
                        cardDessert.add(arrayList.get(imageIndexArray[0]));
                        cardDessert.add(arrayList.get(imageIndexArray[1]));
                        cardDessert.add(arrayList.get(imageIndexArray[2]));
                        cardDessert.add(arrayList.get(imageIndexArray[3]));
                        cardDessert.add(arrayList.get(imageIndexArray[4]));
                        cardDessert.add(arrayList.get(imageIndexArray[5]));
                        break;

                    case 16:
                        imageIndexArray[0] = 1;
                        imageIndexArray[1] = 8;
                        imageIndexArray[2] = 10;
                        imageIndexArray[3] = 17;
                        imageIndexArray[4] = 24;
                        imageIndexArray[5] = 28;
                        cardDessert.add(arrayList.get(imageIndexArray[0]));
                        cardDessert.add(arrayList.get(imageIndexArray[1]));
                        cardDessert.add(arrayList.get(imageIndexArray[2]));
                        cardDessert.add(arrayList.get(imageIndexArray[3]));
                        cardDessert.add(arrayList.get(imageIndexArray[4]));
                        cardDessert.add(arrayList.get(imageIndexArray[5]));
                        break;

                    case 17:
                        imageIndexArray[0] = 2;
                        imageIndexArray[1] = 9;
                        imageIndexArray[2] = 11;
                        imageIndexArray[3] = 18;
                        imageIndexArray[4] = 20;
                        imageIndexArray[5] = 28;
                        cardDessert.add(arrayList.get(imageIndexArray[0]));
                        cardDessert.add(arrayList.get(imageIndexArray[1]));
                        cardDessert.add(arrayList.get(imageIndexArray[2]));
                        cardDessert.add(arrayList.get(imageIndexArray[3]));
                        cardDessert.add(arrayList.get(imageIndexArray[4]));
                        cardDessert.add(arrayList.get(imageIndexArray[5]));
                        break;

                    case 18:
                        imageIndexArray[0] = 3;
                        imageIndexArray[1] = 5;
                        imageIndexArray[2] = 12;
                        imageIndexArray[3] = 19;
                        imageIndexArray[4] = 21;
                        imageIndexArray[5] = 28;
                        cardDessert.add(arrayList.get(imageIndexArray[0]));
                        cardDessert.add(arrayList.get(imageIndexArray[1]));
                        cardDessert.add(arrayList.get(imageIndexArray[2]));
                        cardDessert.add(arrayList.get(imageIndexArray[3]));
                        cardDessert.add(arrayList.get(imageIndexArray[4]));
                        cardDessert.add(arrayList.get(imageIndexArray[5]));
                        break;

                    case 19:
                        imageIndexArray[0] = 4;
                        imageIndexArray[1] = 6;
                        imageIndexArray[2] = 13;
                        imageIndexArray[3] = 15;
                        imageIndexArray[4] = 22;
                        imageIndexArray[5] = 28;
                        cardDessert.add(arrayList.get(imageIndexArray[0]));
                        cardDessert.add(arrayList.get(imageIndexArray[1]));
                        cardDessert.add(arrayList.get(imageIndexArray[2]));
                        cardDessert.add(arrayList.get(imageIndexArray[3]));
                        cardDessert.add(arrayList.get(imageIndexArray[4]));
                        cardDessert.add(arrayList.get(imageIndexArray[5]));
                        break;

                    case 20:
                        imageIndexArray[0] = 0;
                        imageIndexArray[1] = 8;
                        imageIndexArray[2] = 11;
                        imageIndexArray[3] = 19;
                        imageIndexArray[4] = 22;
                        imageIndexArray[5] = 29;
                        cardDessert.add(arrayList.get(imageIndexArray[0]));
                        cardDessert.add(arrayList.get(imageIndexArray[1]));
                        cardDessert.add(arrayList.get(imageIndexArray[2]));
                        cardDessert.add(arrayList.get(imageIndexArray[3]));
                        cardDessert.add(arrayList.get(imageIndexArray[4]));
                        cardDessert.add(arrayList.get(imageIndexArray[5]));
                        break;

                    case 21:
                        imageIndexArray[0] = 1;
                        imageIndexArray[1] = 9;
                        imageIndexArray[2] = 12;
                        imageIndexArray[3] = 15;
                        imageIndexArray[4] = 23;
                        imageIndexArray[5] = 29;
                        cardDessert.add(arrayList.get(imageIndexArray[0]));
                        cardDessert.add(arrayList.get(imageIndexArray[1]));
                        cardDessert.add(arrayList.get(imageIndexArray[2]));
                        cardDessert.add(arrayList.get(imageIndexArray[3]));
                        cardDessert.add(arrayList.get(imageIndexArray[4]));
                        cardDessert.add(arrayList.get(imageIndexArray[5]));
                        break;

                    case 22:
                        imageIndexArray[0] = 2;
                        imageIndexArray[1] = 5;
                        imageIndexArray[2] = 13;
                        imageIndexArray[3] = 16;
                        imageIndexArray[4] = 24;
                        imageIndexArray[5] = 29;
                        cardDessert.add(arrayList.get(imageIndexArray[0]));
                        cardDessert.add(arrayList.get(imageIndexArray[1]));
                        cardDessert.add(arrayList.get(imageIndexArray[2]));
                        cardDessert.add(arrayList.get(imageIndexArray[3]));
                        cardDessert.add(arrayList.get(imageIndexArray[4]));
                        cardDessert.add(arrayList.get(imageIndexArray[5]));
                        break;

                    case 23:
                        imageIndexArray[0] = 3;
                        imageIndexArray[1] = 6;
                        imageIndexArray[2] = 14;
                        imageIndexArray[3] = 17;
                        imageIndexArray[4] = 20;
                        imageIndexArray[5] = 29;
                        cardDessert.add(arrayList.get(imageIndexArray[0]));
                        cardDessert.add(arrayList.get(imageIndexArray[1]));
                        cardDessert.add(arrayList.get(imageIndexArray[2]));
                        cardDessert.add(arrayList.get(imageIndexArray[3]));
                        cardDessert.add(arrayList.get(imageIndexArray[4]));
                        cardDessert.add(arrayList.get(imageIndexArray[5]));
                        break;

                    case 24:
                        imageIndexArray[0] = 4;
                        imageIndexArray[1] = 7;
                        imageIndexArray[2] = 10;
                        imageIndexArray[3] = 18;
                        imageIndexArray[4] = 21;
                        imageIndexArray[5] = 29;
                        cardDessert.add(arrayList.get(imageIndexArray[0]));
                        cardDessert.add(arrayList.get(imageIndexArray[1]));
                        cardDessert.add(arrayList.get(imageIndexArray[2]));
                        cardDessert.add(arrayList.get(imageIndexArray[3]));
                        cardDessert.add(arrayList.get(imageIndexArray[4]));
                        cardDessert.add(arrayList.get(imageIndexArray[5]));
                        break;

                    case 25:
                        imageIndexArray[0] = 0;
                        imageIndexArray[1] = 9;
                        imageIndexArray[2] = 13;
                        imageIndexArray[3] = 17;
                        imageIndexArray[4] = 21;
                        imageIndexArray[5] = 30;
                        cardDessert.add(arrayList.get(imageIndexArray[0]));
                        cardDessert.add(arrayList.get(imageIndexArray[1]));
                        cardDessert.add(arrayList.get(imageIndexArray[2]));
                        cardDessert.add(arrayList.get(imageIndexArray[3]));
                        cardDessert.add(arrayList.get(imageIndexArray[4]));
                        cardDessert.add(arrayList.get(imageIndexArray[5]));
                        break;

                    case 26:
                        imageIndexArray[0] = 1;
                        imageIndexArray[1] = 5;
                        imageIndexArray[2] = 14;
                        imageIndexArray[3] = 18;
                        imageIndexArray[4] = 22;
                        imageIndexArray[5] = 30;
                        cardDessert.add(arrayList.get(imageIndexArray[0]));
                        cardDessert.add(arrayList.get(imageIndexArray[1]));
                        cardDessert.add(arrayList.get(imageIndexArray[2]));
                        cardDessert.add(arrayList.get(imageIndexArray[3]));
                        cardDessert.add(arrayList.get(imageIndexArray[4]));
                        cardDessert.add(arrayList.get(imageIndexArray[5]));
                        break;

                    case 27:
                        imageIndexArray[0] = 2;
                        imageIndexArray[1] = 6;
                        imageIndexArray[2] = 10;
                        imageIndexArray[3] = 19;
                        imageIndexArray[4] = 23;
                        imageIndexArray[5] = 30;
                        cardDessert.add(arrayList.get(imageIndexArray[0]));
                        cardDessert.add(arrayList.get(imageIndexArray[1]));
                        cardDessert.add(arrayList.get(imageIndexArray[2]));
                        cardDessert.add(arrayList.get(imageIndexArray[3]));
                        cardDessert.add(arrayList.get(imageIndexArray[4]));
                        cardDessert.add(arrayList.get(imageIndexArray[5]));
                        break;

                    case 28:
                        imageIndexArray[0] = 3;
                        imageIndexArray[1] = 7;
                        imageIndexArray[2] = 11;
                        imageIndexArray[3] = 15;
                        imageIndexArray[4] = 24;
                        imageIndexArray[5] = 30;
                        cardDessert.add(arrayList.get(imageIndexArray[0]));
                        cardDessert.add(arrayList.get(imageIndexArray[1]));
                        cardDessert.add(arrayList.get(imageIndexArray[2]));
                        cardDessert.add(arrayList.get(imageIndexArray[3]));
                        cardDessert.add(arrayList.get(imageIndexArray[4]));
                        cardDessert.add(arrayList.get(imageIndexArray[5]));
                        break;

                    case 29:
                        imageIndexArray[0] = 4;
                        imageIndexArray[1] = 8;
                        imageIndexArray[2] = 12;
                        imageIndexArray[3] = 16;
                        imageIndexArray[4] = 20;
                        imageIndexArray[5] = 30;
                        cardDessert.add(arrayList.get(imageIndexArray[0]));
                        cardDessert.add(arrayList.get(imageIndexArray[1]));
                        cardDessert.add(arrayList.get(imageIndexArray[2]));
                        cardDessert.add(arrayList.get(imageIndexArray[3]));
                        cardDessert.add(arrayList.get(imageIndexArray[4]));
                        cardDessert.add(arrayList.get(imageIndexArray[5]));
                        break;

                    case 30:
                        imageIndexArray[0] = 25;
                        imageIndexArray[1] = 26;
                        imageIndexArray[2] = 27;
                        imageIndexArray[3] = 28;
                        imageIndexArray[4] = 29;
                        imageIndexArray[5] = 30;
                        cardDessert.add(arrayList.get(imageIndexArray[0]));
                        cardDessert.add(arrayList.get(imageIndexArray[1]));
                        cardDessert.add(arrayList.get(imageIndexArray[2]));
                        cardDessert.add(arrayList.get(imageIndexArray[3]));
                        cardDessert.add(arrayList.get(imageIndexArray[4]));
                        cardDessert.add(arrayList.get(imageIndexArray[5]));
                        break;
                }
                cards.add(new Card(imageIndexArray, i, cardDessert));
            }
        }
    }

    public void remove(int x)
    {
        cards.remove(x);
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

}
