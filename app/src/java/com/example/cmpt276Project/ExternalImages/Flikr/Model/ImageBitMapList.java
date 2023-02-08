package com.example.cmpt276project.ExternalImages.Flikr.Model;

import android.graphics.Bitmap;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.Iterator;
//code highly influenced from The Big Nerd Ranch Guide (3rd ed)
//list of bitmap images of selected images

public class ImageBitMapList implements Iterable {
    private ArrayList<Bitmap> bitmapArrayList;
    private static ImageBitMapList imageBitMapList;

    public ImageBitMapList() {
        this.bitmapArrayList = new ArrayList<>();
    }

    public ArrayList<Bitmap> getBitmapArrayList() {
        return bitmapArrayList;
    }
    public static ImageBitMapList get(){
        if(imageBitMapList ==null){
            imageBitMapList = new ImageBitMapList();
        }
        return imageBitMapList;

    }
    public void add(Bitmap d)
    {
        bitmapArrayList.add(d);
    }
    public void remove(ArrayList<Bitmap> d){
        bitmapArrayList.removeAll(d);
    }

    @NonNull
    @Override
    public Iterator iterator() {
        return imageBitMapList.iterator();
    }
}

