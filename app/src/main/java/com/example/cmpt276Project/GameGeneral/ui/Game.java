//Game starts. Gets information about user's options and display game correctly. Displays a chronometer showing time played. Uses GameLogic Class to run Game
package com.example.cmpt276project.GameGeneral.ui;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import com.example.cmpt276project.ExternalImages.Flikr.Model.ImageBitMapList;
import com.example.cmpt276project.R;
import com.example.cmpt276project.GameGeneral.model.Card;
import com.example.cmpt276project.GameGeneral.model.Deck;
import com.example.cmpt276project.GameGeneral.model.ExportCard;
import com.example.cmpt276project.GameGeneral.model.GameLogic;
import com.example.cmpt276project.GameGeneral.model.SaveTime;
import com.example.cmpt276project.GameGeneral.model.Settings;

import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

public class Game extends AppCompatActivity {
    static int DELAY_TIME = 5000;
    final SaveTime newTime = SaveTime.getInstance();
    int time;
    private SoundPool soundPool;

    Settings settings = Settings.getInstance();
    GameLogic gameLogic = new GameLogic();
    Chronometer chronometer;
    Boolean running = false;
    long PauseoffSet = 0;
    ArrayList<Bitmap> exportImageList = new ArrayList<>();
    private ExportCard exportCard = ExportCard.getInstance();
    int topCard;

    public static Intent makeIntent(Context c) {
        return new Intent(c, Game.class);
    }

    @Override
    public void onBackPressed() {
        exportCard.clear();
        Intent intent = MainActivity.makeIntent(Game.this);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("");

        //Sound effects
        MediaPlayer startS;
        startS = MediaPlayer.create(this, R.raw.start);
        startS.setAudioStreamType(AudioManager.STREAM_MUSIC);
        startS.setLooping(false);
        startS.start();

        AudioAttributes audioAttributes = new AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_ASSISTANCE_SONIFICATION)
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .build();
        soundPool = new SoundPool.Builder()
                .setMaxStreams(3)
                .setAudioAttributes(audioAttributes)
                .build();
        setVolumeControlStream(AudioManager.STREAM_MUSIC);

        timer();
        reset();
        ArrayList<Bitmap> image;
        ArrayList<Bitmap> word;
        ImageBitMapList imageBitMapList = new ImageBitMapList();
        ArrayList<Bitmap> flickr = ImageBitMapList.get().getBitmapArrayList();

        image = gameLogic.populateList(getResources());
        word = gameLogic.populate_Word_list(getResources());
        Deck deck = new Deck();
        deck.populateDeck(image);
        Card c1 = gameLogic.getRandomCard(deck);
        Card c2 = gameLogic.getRandomCard(deck);
        topCard = c1.getCardIndex();

        //Create buttons
        ConstraintLayout top = findViewById(R.id.top);
        ArrayList<ImageButton> top_btns = new ArrayList<>();
        for (int i = 0; i < settings.getImageNumPerCard(); i++) {
            ImageButton btn = new ImageButton(this);
            btn.setId(View.generateViewId());
            if(settings.getImageNumPerCard() == 3) {
                ConstraintLayout.LayoutParams params = new ConstraintLayout.LayoutParams(350, 350);
                btn.setLayoutParams(params);
            }
            else if(settings.getImageNumPerCard() == 4){
                ConstraintLayout.LayoutParams params = new ConstraintLayout.LayoutParams(350, 350);
                btn.setLayoutParams(params);
            }
            else if(settings.getImageNumPerCard() == 6){
                ConstraintLayout.LayoutParams params = new ConstraintLayout.LayoutParams(300, 300);
                btn.setLayoutParams(params);
            }
            top.addView(btn);
            top_btns.add(btn);
        }
        constraint_btns(top_btns,top);

        ConstraintLayout bottom = findViewById(R.id.bottom);
        ArrayList<ImageButton> bottom_btns = new ArrayList<>();
        for (int i = 0; i < settings.getImageNumPerCard(); i++) {
            ImageButton btn = new ImageButton(this);
            btn.setId(View.generateViewId());
            if(settings.getImageNumPerCard() == 3) {
                ConstraintLayout.LayoutParams params = new ConstraintLayout.LayoutParams(350, 350);
                btn.setLayoutParams(params);
            }
            else if(settings.getImageNumPerCard() == 4){
                ConstraintLayout.LayoutParams params = new ConstraintLayout.LayoutParams(350, 350);
                btn.setLayoutParams(params);
            }
            else if(settings.getImageNumPerCard() == 6){
                ConstraintLayout.LayoutParams params = new ConstraintLayout.LayoutParams(300, 300);
                btn.setLayoutParams(params);
            }
            bottom.addView(btn);
            bottom_btns.add(btn);
        }
        constraint_btns(bottom_btns,bottom);

        //print cards
        int[] newrand1 =randomNumArr();
        int[] newrand2 =randomNumArr();
        int[] randResizeTopCard = random_resize();
        int[] randResizeBottomCard = random_resize();
        int[] randomAngle=randAngleArray(randResizeTopCard.length);

        printTopCard(top_btns, c1, image,word,flickr,newrand1,randResizeTopCard,top,randomAngle);
        printBottomCard(bottom_btns, c2, image,word,flickr,newrand2,randResizeBottomCard,bottom,randomAngle);

        int match = gameLogic.checkMatch(c1.getImageIndexArray(), c2.getImageIndexArray());
        ActivateButtons(match, c1, c2, deck, top_btns, bottom_btns, image,word,flickr,newrand2,randomNumArr(),randResizeBottomCard,random_resize(),top,bottom,randomAngle);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        soundPool.release();
        soundPool = null;
    }

  private void ActivateButtons(int match, final Card card1, final Card card2, final Deck deck,
                               final ArrayList<ImageButton> top_btns, final ArrayList<ImageButton> bottom_btns,
                               final ArrayList<Bitmap> image, final ArrayList<Bitmap>word, final ArrayList<Bitmap> flickr, final int[] rand1, final int[] rand2, final int[] randResizeTop, final int[]randResizeBottom, final ConstraintLayout top, final ConstraintLayout bottom, final int[] randomAngle) {

        //Sound effects
        //final int endS = soundPool.load(this, R.raw.end,1);
        //final int correctS = soundPool.load(this, R.raw.correct,1);
        //final int incorrectS = soundPool.load(this, R.raw.incorrect, 1);

      final MediaPlayer incorrectS;
      incorrectS = MediaPlayer.create(this, R.raw.incorrect);
      incorrectS.setAudioStreamType(AudioManager.STREAM_MUSIC);
      incorrectS.setLooping(false);

      final MediaPlayer correctS;
      correctS = MediaPlayer.create(this, R.raw.correct);
      correctS.setAudioStreamType(AudioManager.STREAM_MUSIC);
      correctS.setLooping(false);

        final ArrayList<Card> cards = deck.getCards();

        for(int i = 0; i < settings.getImageNumPerCard(); i++){
            if (i != match){
                bottom_btns.get(i).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //soundPool.play(incorrectS, 1,1,0,0,1);
                        incorrectS.start();
                    }
                });
            }
        }
        bottom_btns.get(match).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //take screenshot of cards to export
                if (card1.getCardIndex() == topCard) {
                    takePhoto(findViewById(R.id.top));
                }
                takePhoto(findViewById(R.id.bottom));

                card1.setCardIndex(card2.getCardIndex());

                if (cards.size() != 0) {
                    //soundPool.play(correctS, 1, 1, 0, 0, 1);
                    correctS.start();
                    Card temp = card2;
                    temp.setImageIndexArray(card2.getImageIndexArray());
                    card1.setImageIndexArray(card2.getImageIndexArray());
                    printTopCard(top_btns, card1, image,word,flickr,rand1,randResizeTop,top,randomAngle);
                    temp = gameLogic.getRandomCard(deck);
                    printBottomCard(bottom_btns, temp, image,word,flickr,rand2,randResizeBottom,bottom,randomAngle);
                    int[] randTemp =rand2;
                    int[] randResizeTemp = randResizeBottom;
                    int match = gameLogic.checkMatch(card1.getImageIndexArray(), temp.getImageIndexArray());
                    ActivateButtons(match, card1, temp, deck, top_btns, bottom_btns, image, word,flickr,randTemp,randomNumArr(),randResizeTemp,random_resize(),top,bottom,randomAngle);
                } else {
                    //soundPool.play(correctS, 1, 1, 0, 0, 1);
                    correctS.start();
                    //soundPool.play(endS,1,1,0,0,1);
                    chronometer.stop();
//                    final Handler handler = new Handler();
//                    handler.postDelayed(new Runnable() {
//                        @Override
//                        public void run() {
//                            callLogin();
//                        }
//                    },DELAY_TIME);
                    callLogin();

//                    //Cancel Login Box and restart game
//                    Button resetBtn = (Button)findViewById(R.id.reset_timer);
//                    resetBtn.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//                            handler.removeCallbacksAndMessages(null);
//                            startActivity(Game.makeIntent(Game.this));
//                        }
//                    });
                }
            }
        });
    }

    //creates an array of random variables to randomly decide which images appear as a word and which appear as images.
    private int[] randomNumArr(){
        int[] rand;
        int NumPerCard= settings.getImageNumPerCard();
        rand= new int[NumPerCard];
        Random r = new Random();
        int count=0;
        for(int i=0; i < NumPerCard-1;i++){
            rand[i]=r.nextInt(2);
            if(rand[i]==1){
                count++;
            }
        }
        if(count==NumPerCard-1){
            rand[NumPerCard-1]=2;
        }
        else{
            rand[NumPerCard-1]=1;
        }
        return rand;
    }

    private void printBottomCard(ArrayList<ImageButton> btns, Card c2, ArrayList<Bitmap> image, ArrayList<Bitmap> word,ArrayList<Bitmap>flickr, int[] rand, int[] randResize,ConstraintLayout bottom,int[] randomAngle) {
        if(settings.getLevel().equals("Hard")){

            resize_btn(randResize,btns,bottom);
            rotateButton(randResize,btns, randomAngle,bottom);

        }
        if(settings.getLevel().equals("Normal")) {
            rotateButton(randResize,btns,randomAngle,bottom);
        }

        int[] arr = c2.getImageIndexArray();
        Bitmap cardImage;

        for(int i = 0; i < settings.getImageNumPerCard(); i++) {
            if(settings.getMode().equals("Flickr")){
                cardImage = flickr.get(arr[i]);
            }
            else if (settings.getMode().equals("Image")) {
                cardImage = image.get(arr[i]);
            }
            else {
                if (rand[i]==1) {
                        cardImage = word.get(arr[i]);
                }
                else {
                    cardImage = image.get(arr[i]);
                }
            }
            BitmapDrawable drawable_bitmap = new BitmapDrawable(getResources(),cardImage);
            btns.get(i).setBackground(drawable_bitmap);
        }
    }

    private void printTopCard(ArrayList<ImageButton> btns, Card c1, ArrayList<Bitmap> image, ArrayList<Bitmap> word,ArrayList<Bitmap>flickr, int[] rand, int[] randResize,ConstraintLayout top,int[] randomAngle) {
        if(settings.getLevel().equals("Hard")){
            resize_btn(randResize,btns,top);
            rotateButton(randResize,btns, randomAngle,top);
        }
        if(settings.getLevel().equals("Normal")) {
            rotateButton(randResize,btns, randomAngle,top);
        }
        int[] arr = c1.getImageIndexArray();
        Bitmap cardImage;

        for(int i = 0; i < settings.getImageNumPerCard(); i++){
            if(settings.getMode().equals("Flickr")){

                cardImage=flickr.get(arr[i]);
            }
            else if(settings.getMode().equals("Image")) {

                cardImage = image.get(arr[i]);
            }
            else {
                if(rand[i]== 1){
                    cardImage = word.get(arr[i]);
                }
                else {
                    cardImage = image.get(arr[i]);
                }
            }
            BitmapDrawable drawable_bitmap = new BitmapDrawable(getResources(),cardImage);
            btns.get(i).setBackground(drawable_bitmap);
        }
    }

    private int[] randAngleArray(int len) {
        int[] arr=new int[len];
        for(int i=0;i<len;i++) {
            arr[i]=randAngle();
        }
        return arr;
    }

    private int randAngle() {
        Random rand= new Random();
        int randomAngle= rand.nextInt(90);
        return randomAngle;
    }

    private void rotateButton(int[] randResize, ArrayList<ImageButton> btns, int[] randomAngle, ConstraintLayout temp) {
        for(int i=0;i<randResize.length;i++) {
            switch(randResize[i]) {
                case 0:
                    break;
                case 1:
                case 2:
                    btns.get(i).setRotation((float) randomAngle[i]);
            }
        }
        constraint_btns(btns,temp);
    }

    //sets timer during the game
    public void timer() {
        chronometer = findViewById(R.id.chronometer);

        if (!running) {
            chronometer.setBase(SystemClock.elapsedRealtime() - PauseoffSet);//ensures the timer only starts after we click on start
            if (!running) {
                //chronometer.setBase(SystemClock.elapsedRealtime() - PauseoffSet);//ensures the timer only starts after we start activity
                chronometer.start();
                running = true;
            } else {
                chronometer.stop();
                PauseoffSet = SystemClock.elapsedRealtime() - chronometer.getBase();
                running = false;
                chronometer.stop();

                //PauseoffSet = SystemClock.elapsedRealtime() - chronometer.getBase();
                running = false;

            }
        }
    }

    //takes screenshot of game for cards
    private void takePhoto(View view) {
        Date date = new Date();
        android.text.format.DateFormat.format("yyyy-MM-dd-hh:mm:ss", date);

        try {
            //create bitmap
            view.setDrawingCacheEnabled(true);
            Bitmap bitmap = Bitmap.createBitmap(view.getDrawingCache());
            view.setDrawingCacheEnabled(false);

            Bitmap bgView = BitmapFactory.decodeResource(getResources(), R.drawable.export_pic_bg);
            Bitmap bgResized = Bitmap.createScaledBitmap(bgView, view.getWidth(), view.getHeight(), false);
            Bitmap exportCardMap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);

            Canvas canvas = new Canvas(exportCardMap);
            canvas.drawBitmap(bgResized, 0, 0, null);
            canvas.drawBitmap(bitmap, 0, 0, null);

            //add image to list of images to export
            exportCard.add(exportCardMap);
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    private int getSeconds(String time) {
        String[] parts = time.split(":");

        int minutes = Integer.parseInt(parts[0])*60;
        int seconds = Integer.parseInt(parts[1]);

        return minutes + seconds;
    }

    public void reset() {
        Button reset = findViewById(R.id.reset_timer);
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recreate();

            }
        });
    }

    private void callLogin() {
        finish();
        time = getSeconds((String) chronometer.getText());
        newTime.add(time);
        Intent intent = Login.makeIntent(Game.this);
         startActivity(intent);
    }
// creates a 1-dimensional int array of random variables to randomly decide which buttons will be resized and which will not.
    private int[] random_resize(){
        int NumPerCard= settings.getImageNumPerCard();
        int[] rand = new int[NumPerCard] ;
        Random r = new Random();
        for(int j=0; j<NumPerCard;j++){
            rand[j]=r.nextInt(2);
        }
         return rand;
    }


    private void resize_btn(int[] rand,ArrayList<ImageButton> btns,ConstraintLayout temp){
        ConstraintLayout.LayoutParams params;
        for(int j=0;j<settings.getImageNumPerCard();j++){
            switch (rand[j]){
                    case 0:
                        if(settings.getOrder()==5){
                            params= new ConstraintLayout.LayoutParams(270,270);
                            btns.get(j).setLayoutParams(params);
                        }
                        else {
                            params = new ConstraintLayout.LayoutParams(350, 350);
                            btns.get(j).setLayoutParams(params);
                        }
                        break;
                    case 1:
                        params = new ConstraintLayout.LayoutParams(180, 180);
                        btns.get(j).setLayoutParams(params);
                        break;
                    case 2:
                        params = new ConstraintLayout.LayoutParams(450, 450);
                        btns.get(j).setLayoutParams(params);
                        break;
            }
        }
        constraint_btns(btns,temp);
    }

    private void constraint_btns(ArrayList<ImageButton> btns, ConstraintLayout temp) {
        ConstraintSet constraintTop = new ConstraintSet();
        constraintTop.clone(temp);
        if (settings.getOrder() == 2) {
            //Top card
            constraintTop.center(btns.get(0).getId(), ConstraintSet.PARENT_ID, ConstraintSet.LEFT, 0,
                    ConstraintSet.PARENT_ID, ConstraintSet.RIGHT, 0, 0.4f);
            constraintTop.center(btns.get(0).getId(), ConstraintSet.PARENT_ID, ConstraintSet.TOP, 0,
                    ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM, 0, 0.8f);

            constraintTop.center(btns.get(1).getId(), ConstraintSet.PARENT_ID, ConstraintSet.LEFT, 0,
                    ConstraintSet.PARENT_ID, ConstraintSet.RIGHT, 0, 0.9f);
            constraintTop.center(btns.get(1).getId(), ConstraintSet.PARENT_ID, ConstraintSet.TOP, 0,
                    ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM, 0, 0.1f);

            constraintTop.center(btns.get(2).getId(), ConstraintSet.PARENT_ID, ConstraintSet.LEFT, 0,
                    ConstraintSet.PARENT_ID, ConstraintSet.RIGHT, 0, 0.01f);
            constraintTop.center(btns.get(2).getId(), ConstraintSet.PARENT_ID, ConstraintSet.TOP, 0,
                    ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM, 0, 0.05f);
            constraintTop.applyTo(temp);
        } else if (settings.getOrder() == 3) {
            //Top card
            constraintTop.center(btns.get(0).getId(), ConstraintSet.PARENT_ID, ConstraintSet.LEFT, 0,
                    ConstraintSet.PARENT_ID, ConstraintSet.RIGHT, 0, 0.02f);
            constraintTop.center(btns.get(0).getId(), ConstraintSet.PARENT_ID, ConstraintSet.TOP, 0,
                    ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM, 0, 0.7f);

            constraintTop.center(btns.get(1).getId(), ConstraintSet.PARENT_ID, ConstraintSet.LEFT, 0,
                    ConstraintSet.PARENT_ID, ConstraintSet.RIGHT, 0, 0.9f);
            constraintTop.center(btns.get(1).getId(), ConstraintSet.PARENT_ID, ConstraintSet.TOP, 0,
                    ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM, 0, 0.001f);

            constraintTop.center(btns.get(2).getId(), ConstraintSet.PARENT_ID, ConstraintSet.LEFT, 0,
                    ConstraintSet.PARENT_ID, ConstraintSet.RIGHT, 0, 0.02f);
            constraintTop.center(btns.get(2).getId(), ConstraintSet.PARENT_ID, ConstraintSet.TOP, 0,
                    ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM, 0, 0.001f);

            constraintTop.center(btns.get(3).getId(), ConstraintSet.PARENT_ID, ConstraintSet.LEFT, 0,
                    ConstraintSet.PARENT_ID, ConstraintSet.RIGHT, 0, 0.9f);
            constraintTop.center(btns.get(3).getId(), ConstraintSet.PARENT_ID, ConstraintSet.TOP, 0,
                    ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM, 0, 0.7f);

            constraintTop.applyTo(temp);

        }
        else if (settings.getOrder() == 5) {
            //Top card
            constraintTop.center(btns.get(0).getId(), ConstraintSet.PARENT_ID, ConstraintSet.LEFT, 0,
                    ConstraintSet.PARENT_ID, ConstraintSet.RIGHT, 0, 0.5f);
            constraintTop.center(btns.get(0).getId(), ConstraintSet.PARENT_ID, ConstraintSet.TOP, 0,
                    ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM, 0, 0.4f);
            constraintTop.center(btns.get(1).getId(), ConstraintSet.PARENT_ID, ConstraintSet.LEFT, 0,
                    ConstraintSet.PARENT_ID, ConstraintSet.RIGHT, 0, 1f);
            constraintTop.center(btns.get(1).getId(), ConstraintSet.PARENT_ID, ConstraintSet.TOP, 0,
                    ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM, 0, 0.2f);
            constraintTop.center(btns.get(2).getId(), ConstraintSet.PARENT_ID, ConstraintSet.LEFT, 0,
                    ConstraintSet.PARENT_ID, ConstraintSet.RIGHT, 0, 0.01f);
            constraintTop.center(btns.get(2).getId(), ConstraintSet.PARENT_ID, ConstraintSet.TOP, 0,
                    ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM, 0, 0.2f);
            constraintTop.center(btns.get(3).getId(), ConstraintSet.PARENT_ID, ConstraintSet.LEFT, 0,
                    ConstraintSet.PARENT_ID, ConstraintSet.RIGHT, 0, 0.5f);
            constraintTop.center(btns.get(3).getId(), ConstraintSet.PARENT_ID, ConstraintSet.TOP, 0,
                    ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM, 0, 0.01f);
            constraintTop.center(btns.get(4).getId(), ConstraintSet.PARENT_ID, ConstraintSet.LEFT, 0,
                    ConstraintSet.PARENT_ID, ConstraintSet.RIGHT, 0, 0.2f);
            constraintTop.center(btns.get(4).getId(), ConstraintSet.PARENT_ID, ConstraintSet.TOP, 0,
                    ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM, 0, 0.7f);
            constraintTop.center(btns.get(5).getId(), ConstraintSet.PARENT_ID, ConstraintSet.LEFT, 0,
                    ConstraintSet.PARENT_ID, ConstraintSet.RIGHT, 0, 0.8f);
            constraintTop.center(btns.get(5).getId(), ConstraintSet.PARENT_ID, ConstraintSet.TOP, 0,
                    ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM, 0, 0.7f);


            constraintTop.applyTo(temp);
        }


        }

}

