
package com.example.cmpt276project.ExternalImages.Flikr.Model;

import android.graphics.drawable.Drawable;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.Iterator;

public class ImageDrawableList implements Iterable {
    private ArrayList<Drawable> drawableArray;
    private static ImageDrawableList imageDrawableList;

    public ImageDrawableList() {
        this.drawableArray = new ArrayList<>();
    }

    public ArrayList<Drawable> getDrawableArray() {
        return drawableArray;
    }
    public static ImageDrawableList get(){
        if(imageDrawableList==null){
            imageDrawableList =new ImageDrawableList();
        }
        return imageDrawableList;

    }
    public void add(Drawable d)
    {
        drawableArray.add(d);
    }
    public void remove(int x){
        drawableArray.remove(x);}

    @NonNull
    @Override
    public Iterator iterator() {
        return imageDrawableList.iterator();
    }
}
