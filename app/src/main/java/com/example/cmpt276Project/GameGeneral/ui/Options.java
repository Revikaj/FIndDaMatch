//Where user changes the game settings. Includes theme, order, number of cards, mode. User can start game directly from options screen after choosing
package com.example.cmpt276project.GameGeneral.ui;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cmpt276project.ExternalImages.Flikr.Model.ImageBitMapList;
import com.example.cmpt276project.ExternalImages.SelectedImagesView.Model.SelectedImages;
import com.example.cmpt276project.R;
import com.example.cmpt276project.GameGeneral.model.Settings;

import java.util.ArrayList;
import java.util.Objects;

public class Options extends AppCompatActivity {
    private Settings settings;
    ImageBitMapList imageBitMapList=new ImageBitMapList();
    public static Intent makeIntent(Context c) {
        return new Intent(c, Options.class);
    }

    @Override
    public void onBackPressed() {
        Intent intent = MainActivity.makeIntent(Options.this);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);
        settings = Settings.getInstance();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.ops);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.options);

        createRadioButtons();
        createRadioButtons_ORDER();
        //  int savedOrder=getOrder(this);
        createPileSize();
        // String savedPile=getPile(this);

        Button start = findViewById(R.id.start);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(settings.getMode().equals("Image") || settings.getMode().equals("Image-Word"))
                {
                    Intent intent = Game.makeIntent(Options.this);
                    startActivity(intent);
                }
                else {
                    ArrayList<Bitmap> flickr = ImageBitMapList.get().getBitmapArrayList();

                    Boolean check = settings.FlickImageCheck(flickr);
                    if (check) {
                        Intent intent = Game.makeIntent(Options.this);
                        startActivity(intent);
                    } else {
                        Toast toast = Toast.makeText(getApplicationContext(),
                                getString(R.string.toastt) + settings.getOrder() + getString(R.string.t),
                                Toast.LENGTH_LONG);

                        toast.show();
                    }
                }
            }
        });
    }

   // @RequiresApi(api = Build.VERSION_CODES.O)
   public void createRadioButtons_ORDER() {
       RadioGroup group = findViewById(R.id.radio_ORDER);

       final int[] order = getResources().getIntArray(R.array.order_cards);
       //Typeface font= Typeface.createFromAsset(getAssets(),"grandHotel-Regular.ttf");
       for (final int orderi : order) {
           RadioButton button = new RadioButton(this);
           button.setText("" + orderi);
           //button.setTypeface(font);
           //button.

           button.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   saveOrder(orderi);
               }
           });
           group.addView(button);

           //Select Default Button:
           if (orderi == getOrder(this)) {
               button.setChecked(true);
           }
       }
   }

    private void saveOrder(int orderi) {
        SharedPreferences prefs = this.getSharedPreferences("OrderPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt("OrderPrefs", orderi);
        editor.apply();
        settings.setOrder(orderi);
        deletePileSize();
        createPileSize();

    }
    private void deletePileSize() {
        RadioGroup rg=(RadioGroup)findViewById(R.id.radio_Deck);
        rg.clearCheck();
        rg.removeAllViews();
    }

    public static int getOrder(Context context) {
        SharedPreferences prefs = context.getSharedPreferences("OrderPrefs", MODE_PRIVATE);
        int defaultVal=context.getResources().getInteger(R.integer.Default_order_cards);
        return prefs.getInt("OrderPrefs", defaultVal);
    }

    private void createRadioButtons() {
        final Button flickr_image=findViewById(R.id.flickrimage);
        flickr_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = SelectedImages.makeIntent(Options.this);
                startActivity(intent);
            }
        });
        if(settings.getMode().equals("Flickr")){
            flickr_image.setEnabled(true);
        }
        else {
            flickr_image.setEnabled(false);
        }

        final RadioButton food = findViewById(R.id.food);
        if ("Food".equals(settings.getTheme())) {
            food.setChecked(true);
        }
        food.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveTheme("Food");
            }
        });


        final RadioButton dessert = findViewById(R.id.dessert);
        if ("Dessert".equals(settings.getTheme())) {
            dessert.setChecked(true);
        }
        dessert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveTheme("Dessert");
            }
        });

        final RadioButton image_word = findViewById(R.id.image_word);
        image_word.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveMode("Image-Word");
                flickr_image.setEnabled(false);
                food.setEnabled(true);
                dessert.setEnabled(true);
            }
        });
        if("Image-Word".equals(settings.getMode())){
            image_word.setChecked(true);
        }

        final RadioButton image= findViewById(R.id.image);
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveMode("Image");
                flickr_image.setEnabled(false);
                food.setEnabled(true);
                dessert.setEnabled(true);
            }
        });
        if("Image".equals(settings.getMode())){
            image.setChecked(true);

        }

        final RadioButton flickrRB = findViewById(R.id.flickr);
        flickrRB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveMode("Flickr");
                food.setEnabled(false);
                dessert.setEnabled(false);
                flickr_image.setEnabled(true);
            }
        });
        if("Flickr".equals(settings.getMode())){
            flickrRB.setChecked(true);
        }
        RadioButton easyRB = findViewById(R.id.easy);
        easyRB.setChecked(true);
        easyRB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveLevel("Easy");
            }
        });
        if("Easy".equals(settings.getLevel())){
            easyRB.setChecked(true);
        }
        RadioButton normalRB = findViewById(R.id.normal);
        normalRB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveLevel("Normal");
            }
        });
        if("Normal".equals(settings.getLevel())){
            normalRB.setChecked(true);
        }
        RadioButton hardRB = findViewById(R.id.hard);
        hardRB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveLevel("Hard");
            }
        });
        if("Hard".equals(settings.getLevel())){
            hardRB.setChecked(true);
        }
    }

    private void saveTheme(String theme) {
        SharedPreferences prefs = this.getSharedPreferences("ThemePrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("ThemePrefs", theme);
        editor.apply();
        settings.setTheme(theme);
    }

    private void saveMode(String mode){
        SharedPreferences modepref = this.getSharedPreferences("ModePrefs",MODE_PRIVATE);
        SharedPreferences.Editor editor = modepref.edit();
        editor.putString("ModePrefs",mode);
        editor.apply();
        settings.setMode(mode);
    }

    static public String getTheme(Context context) {
        SharedPreferences prefs = context.getSharedPreferences("ThemePrefs", MODE_PRIVATE);
        return prefs.getString("ThemePrefs", "");
    }

    static public String getMode(Context context){
        SharedPreferences modepref = context.getSharedPreferences("ModePrefs",MODE_PRIVATE);
        return modepref.getString("ModePrefs","");
    }

    public void createPileSize() {

        RadioGroup group = findViewById(R.id.radio_Deck);
        String[] pileCards = getResources().getStringArray((R.array.Pile_Size));
        int order=getOrder(this);

        for (int i = 0; i < pileCards.length; i++) {
            final String orderi = pileCards[i];
            RadioButton button = new RadioButton(this);
            button.setText(""+orderi);
            if(order==2)
            {
                if (i>0 && i!= pileCards.length-1)
               button.setEnabled(false);
            }
            if(order==3)
            {
                if (i>1 && i!= pileCards.length-1)
                    button.setEnabled(false);
            }

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    savePileCards(orderi);
                }
            });
            group.addView(button);

            if(orderi.equals(getPile(this))){
                button.setChecked(true);
            }
        }
    }

    public String getPile(Context context) {
        SharedPreferences prefs = context.getSharedPreferences("PilePrefs", MODE_PRIVATE);
        String defaultVal=context.getResources().getString(R.string.Default_pile);
        return prefs.getString("PilePrefs", defaultVal);
    }

    private void savePileCards(String orderi) {
        SharedPreferences prefs = this.getSharedPreferences("PilePrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("PilePrefs", orderi);
        editor.apply();
        settings.setPile(orderi);
    }
    private void saveLevel(String levels){
        SharedPreferences prefs = this.getSharedPreferences("LevelPref",MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("LevelPref", levels );
        editor.apply();
        settings.setlevel(levels);
    }
}
