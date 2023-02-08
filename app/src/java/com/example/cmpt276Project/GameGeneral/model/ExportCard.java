package com.example.cmpt276project.GameGeneral.model;

import android.content.ContentResolver;
import android.graphics.Bitmap;
import android.provider.MediaStore;

import java.util.ArrayList;
import java.util.Date;


//Class for exported images of cards to the photos app
public class ExportCard {
    static ArrayList<Bitmap> exportCardList = new ArrayList<>();

    //Singleton Support
    private static ExportCard instance;
    public ExportCard(){}
    public static ExportCard getInstance(){
        if(instance == null){
            instance = new ExportCard();
        }
        return instance;
    }

    public void add(Bitmap bitmap) {
        exportCardList.add(bitmap);
    }

    //export list of cards to photo gallery
    public static void exportCanvas(ContentResolver contentResolver) {
        for (int i = 0; i < exportCardList.size(); i++) {
            Date date = new Date();
            android.text.format.DateFormat.format("yyyy-MM-dd-hh:mm:ss", date);

            MediaStore.Images.Media.insertImage(contentResolver,
                    exportCardList.get(i),
                    "export card " + date,
                    "exported card");
        }
    }

    public void clear() {
        exportCardList.removeAll(exportCardList);
    }
}
