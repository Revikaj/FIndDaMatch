//Class holds methods that recognize the match image of 2 random cards from the deck. Also add images to be used into an ArrayList
package com.example.cmpt276project.GameGeneral.model;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.cmpt276project.R;

import java.util.ArrayList;
import java.util.Random;

//import com.example.cmpt276project.GameGeneral.ui.Options;

public class GameLogic {

    Settings settings = Settings.getInstance();

    Random rand = new Random();

    public ArrayList<Bitmap> populateList(Resources res){

        String savedTheme = settings.getTheme();
        int savedOrder = settings.getOrder();
        int SavedPileSize=settings.getPile();
        ArrayList<Bitmap> arrayList=new ArrayList<>();

        if(savedTheme.equals("Dessert")){
            if(savedOrder >= 2){
                if(savedOrder >= 3){
                    if(savedOrder == 5){
                        arrayList.add(BitmapFactory.decodeResource(res, R.drawable.applepie));
                        arrayList.add(BitmapFactory.decodeResource(res, R.drawable.bingsu));
                        arrayList.add(BitmapFactory.decodeResource(res, R.drawable.birthdaycake));
                        arrayList.add(BitmapFactory.decodeResource(res, R.drawable.biscuits));
                        arrayList.add(BitmapFactory.decodeResource(res, R.drawable.boba));
                        arrayList.add(BitmapFactory.decodeResource(res, R.drawable.cheesecake));
                        arrayList.add(BitmapFactory.decodeResource(res, R.drawable.chococake));
                        arrayList.add(BitmapFactory.decodeResource(res, R.drawable.chocolatebar));
                        arrayList.add(BitmapFactory.decodeResource(res, R.drawable.coffee));
                        arrayList.add(BitmapFactory.decodeResource(res, R.drawable.crepe));
                        arrayList.add(BitmapFactory.decodeResource(res, R.drawable.cupcake));
                        arrayList.add(BitmapFactory.decodeResource(res, R.drawable.donut));
                        arrayList.add(BitmapFactory.decodeResource(res, R.drawable.flan));
                        arrayList.add(BitmapFactory.decodeResource(res, R.drawable.gummybear));
                        arrayList.add(BitmapFactory.decodeResource(res, R.drawable.hotchocolate));
                        arrayList.add(BitmapFactory.decodeResource(res, R.drawable.icecreamcone));
                        arrayList.add(BitmapFactory.decodeResource(res, R.drawable.lollipop));
                        arrayList.add(BitmapFactory.decodeResource(res, R.drawable.macaron));
                    }
                    arrayList.add(BitmapFactory.decodeResource(res, R.drawable.matchaicecream));
                    arrayList.add(BitmapFactory.decodeResource(res, R.drawable.matchapudding));
                    arrayList.add(BitmapFactory.decodeResource(res, R.drawable.milkshake));
                    arrayList.add(BitmapFactory.decodeResource(res, R.drawable.mochi));
                    arrayList.add(BitmapFactory.decodeResource(res, R.drawable.mooncake));
                    arrayList.add(BitmapFactory.decodeResource(res, R.drawable.pancake));
                }
                arrayList.add(BitmapFactory.decodeResource(res, R.drawable.popsicle));
                arrayList.add(BitmapFactory.decodeResource(res, R.drawable.rollcake));
                arrayList.add(BitmapFactory.decodeResource(res, R.drawable.smoothies));
                arrayList.add(BitmapFactory.decodeResource(res, R.drawable.sundae));
                arrayList.add(BitmapFactory.decodeResource(res, R.drawable.tiramisu));
                arrayList.add(BitmapFactory.decodeResource(res, R.drawable.waffle));
                arrayList.add(BitmapFactory.decodeResource(res, R.drawable.watermelon));
            }
        }
        else{
            if(savedOrder >= 2){
                if(savedOrder >= 3){
                    if(savedOrder == 5){
                        arrayList.add(BitmapFactory.decodeResource(res, R.drawable.bacon));
                        arrayList.add(BitmapFactory.decodeResource(res, R.drawable.beef));
                        arrayList.add(BitmapFactory.decodeResource(res, R.drawable.bibimbap));
                        arrayList.add(BitmapFactory.decodeResource(res, R.drawable.burger));
                        arrayList.add(BitmapFactory.decodeResource(res, R.drawable.burrito));
                        arrayList.add(BitmapFactory.decodeResource(res, R.drawable.chicken));
                        arrayList.add(BitmapFactory.decodeResource(res, R.drawable.corndog));
                        arrayList.add(BitmapFactory.decodeResource(res, R.drawable.curry));
                        arrayList.add(BitmapFactory.decodeResource(res, R.drawable.dimsum));
                        arrayList.add(BitmapFactory.decodeResource(res, R.drawable.dumpling));
                        arrayList.add(BitmapFactory.decodeResource(res, R.drawable.fish));
                        arrayList.add(BitmapFactory.decodeResource(res, R.drawable.fries));
                        arrayList.add(BitmapFactory.decodeResource(res, R.drawable.gyoza));
                        arrayList.add(BitmapFactory.decodeResource(res, R.drawable.hotdog));
                        arrayList.add(BitmapFactory.decodeResource(res, R.drawable.kimbap));
                        arrayList.add(BitmapFactory.decodeResource(res, R.drawable.mixnoodle));
                        arrayList.add(BitmapFactory.decodeResource(res, R.drawable.oyster));
                        arrayList.add(BitmapFactory.decodeResource(res, R.drawable.padthai));
                    }
                    arrayList.add(BitmapFactory.decodeResource(res, R.drawable.pasta));
                    arrayList.add(BitmapFactory.decodeResource(res, R.drawable.pizza));
                    arrayList.add(BitmapFactory.decodeResource(res, R.drawable.salad));
                    arrayList.add(BitmapFactory.decodeResource(res, R.drawable.sandwich));
                    arrayList.add(BitmapFactory.decodeResource(res, R.drawable.skewed));
                    arrayList.add(BitmapFactory.decodeResource(res, R.drawable.soup));
                }
                arrayList.add(BitmapFactory.decodeResource(res, R.drawable.spaghetti));
                arrayList.add(BitmapFactory.decodeResource(res, R.drawable.springrolls));
                arrayList.add(BitmapFactory.decodeResource(res, R.drawable.salmon));
                arrayList.add(BitmapFactory.decodeResource(res, R.drawable.sushi));
                arrayList.add(BitmapFactory.decodeResource(res, R.drawable.taco));
                arrayList.add(BitmapFactory.decodeResource(res, R.drawable.tteokbokki));
                arrayList.add(BitmapFactory.decodeResource(res, R.drawable.wings));
            }
        }
        return arrayList;
    }

    public ArrayList<Bitmap> populate_Word_list(Resources res) {
        String savedTheme = settings.getTheme();
        int savedOrder = settings.getOrder();
        String savedMode = settings.getMode();
        ArrayList<Bitmap> arrayList_word = new ArrayList<>();
        if (savedTheme.equals("Dessert")) {
            if (savedMode.equals("Image-Word")) {
                if (savedOrder >= 2) {
                    if (savedOrder >= 3) {
                        if (savedOrder == 5) {
                            arrayList_word.add(BitmapFactory.decodeResource(res, R.drawable.apple_pie_word));
                            arrayList_word.add(BitmapFactory.decodeResource(res, R.drawable.bingsu_word));
                            arrayList_word.add(BitmapFactory.decodeResource(res, R.drawable.birthdaycake_word));
                            arrayList_word.add(BitmapFactory.decodeResource(res, R.drawable.biscuits_word));
                            arrayList_word.add(BitmapFactory.decodeResource(res, R.drawable.boba_word));
                            arrayList_word.add(BitmapFactory.decodeResource(res, R.drawable.cheesecake_word));
                            arrayList_word.add(BitmapFactory.decodeResource(res, R.drawable.chocolatecake_word));
                            arrayList_word.add(BitmapFactory.decodeResource(res, R.drawable.chocolatebas_word));
                            arrayList_word.add(BitmapFactory.decodeResource(res, R.drawable.coffee_word));
                            arrayList_word.add(BitmapFactory.decodeResource(res, R.drawable.crepe_word));
                            arrayList_word.add(BitmapFactory.decodeResource(res, R.drawable.cupcake_word));
                            arrayList_word.add(BitmapFactory.decodeResource(res, R.drawable.donut_word));
                            arrayList_word.add(BitmapFactory.decodeResource(res, R.drawable.caramelflan_word));
                            arrayList_word.add(BitmapFactory.decodeResource(res, R.drawable.gummy_bear_word));
                            arrayList_word.add(BitmapFactory.decodeResource(res, R.drawable.hot_choco_word));
                            arrayList_word.add(BitmapFactory.decodeResource(res, R.drawable.ice_cream_cone_word));
                            arrayList_word.add(BitmapFactory.decodeResource(res, R.drawable.lollipop_word));
                            arrayList_word.add(BitmapFactory.decodeResource(res, R.drawable.macaron_word));
                        }
                        arrayList_word.add(BitmapFactory.decodeResource(res, R.drawable.matcha_ice_cream_word));
                        arrayList_word.add(BitmapFactory.decodeResource(res, R.drawable.matcha_pudding_word));
                        arrayList_word.add(BitmapFactory.decodeResource(res, R.drawable.milkshake_word));
                        arrayList_word.add(BitmapFactory.decodeResource(res, R.drawable.mochi_word));
                        arrayList_word.add(BitmapFactory.decodeResource(res, R.drawable.moon_cake_word));
                        arrayList_word.add(BitmapFactory.decodeResource(res, R.drawable.pancakes_word));
                    }
                    arrayList_word.add(BitmapFactory.decodeResource(res, R.drawable.popsicle_word));
                    arrayList_word.add(BitmapFactory.decodeResource(res, R.drawable.swiss_roll_word));
                    arrayList_word.add(BitmapFactory.decodeResource(res, R.drawable.smoothie_word));
                    arrayList_word.add(BitmapFactory.decodeResource(res, R.drawable.sundae_word));
                    arrayList_word.add(BitmapFactory.decodeResource(res, R.drawable.tiramisu_word));
                    arrayList_word.add(BitmapFactory.decodeResource(res, R.drawable.waffles_word));
                    arrayList_word.add(BitmapFactory.decodeResource(res, R.drawable.watermelon_word));
                }
            }
        }
        else {
            if (savedMode.equals("Image-Word")) {
                if (savedOrder >= 2) {
                    if (savedOrder >= 3) {
                        if (savedOrder == 5) {
                            arrayList_word.add(BitmapFactory.decodeResource(res, R.drawable.bacon_word));
                            arrayList_word.add(BitmapFactory.decodeResource(res, R.drawable.steak_word));
                            arrayList_word.add(BitmapFactory.decodeResource(res, R.drawable.bibimbap_word));
                            arrayList_word.add(BitmapFactory.decodeResource(res, R.drawable.burger_word));
                            arrayList_word.add(BitmapFactory.decodeResource(res, R.drawable.burrito_word));
                            arrayList_word.add(BitmapFactory.decodeResource(res, R.drawable.friedchicken_word));
                            arrayList_word.add(BitmapFactory.decodeResource(res, R.drawable.corndog_word));
                            arrayList_word.add(BitmapFactory.decodeResource(res, R.drawable.curry_word));
                            arrayList_word.add(BitmapFactory.decodeResource(res, R.drawable.dimsum_word));
                            arrayList_word.add(BitmapFactory.decodeResource(res, R.drawable.dumplings_word));
                            arrayList_word.add(BitmapFactory.decodeResource(res, R.drawable.fish_word));
                            arrayList_word.add(BitmapFactory.decodeResource(res, R.drawable.fries_word));
                            arrayList_word.add(BitmapFactory.decodeResource(res, R.drawable.gyoza_word));
                            arrayList_word.add(BitmapFactory.decodeResource(res, R.drawable.hotdog_word));
                            arrayList_word.add(BitmapFactory.decodeResource(res, R.drawable.kimba_word));
                            arrayList_word.add(BitmapFactory.decodeResource(res, R.drawable.mixed_noodles_word));
                            arrayList_word.add(BitmapFactory.decodeResource(res, R.drawable.oyster_word));
                            arrayList_word.add(BitmapFactory.decodeResource(res, R.drawable.pad_thai_word));
                        }
                        arrayList_word.add(BitmapFactory.decodeResource(res, R.drawable.pasta_word));
                        arrayList_word.add(BitmapFactory.decodeResource(res, R.drawable.pizza_word));
                        arrayList_word.add(BitmapFactory.decodeResource(res, R.drawable.salad_word));
                        arrayList_word.add(BitmapFactory.decodeResource(res, R.drawable.sandwich_word));
                        arrayList_word.add(BitmapFactory.decodeResource(res, R.drawable.skewers_word));
                        arrayList_word.add(BitmapFactory.decodeResource(res, R.drawable.soup_word));
                    }
                    arrayList_word.add(BitmapFactory.decodeResource(res, R.drawable.spaghetti_word));
                    arrayList_word.add(BitmapFactory.decodeResource(res, R.drawable.springrolls_word));
                    arrayList_word.add(BitmapFactory.decodeResource(res, R.drawable.salmon_word));
                    arrayList_word.add(BitmapFactory.decodeResource(res, R.drawable.sushi_word));
                    arrayList_word.add(BitmapFactory.decodeResource(res, R.drawable.taco_word));
                    arrayList_word.add(BitmapFactory.decodeResource(res, R.drawable.tteokbokki_word));
                    arrayList_word.add(BitmapFactory.decodeResource(res, R.drawable.wings_word));
                }
            }
        }
        return arrayList_word;
    }

    public int checkMatch(int[] arr, int[] arr2) {
        int imageNum = settings.getImageNumPerCard();
        int SameButton = 0;
        for(int i = 0; i < imageNum; i++)
        {
            for(int j = 0; j < imageNum; j++)
            {
                if(arr[i] == arr2[j])
                {
                    SameButton=j;
                    break;
                }
            }
        }
        return SameButton;

    }

    public Card getRandomCard(Deck deck) {
        ArrayList<Card> cards = deck.getCards();
        int size = cards.size();
        int r = 0;
        if(size != 0) {
            r = rand.nextInt(size);
        }
        Card card = cards.get(r);
        cards.remove(r);
        return card;
    }
}




