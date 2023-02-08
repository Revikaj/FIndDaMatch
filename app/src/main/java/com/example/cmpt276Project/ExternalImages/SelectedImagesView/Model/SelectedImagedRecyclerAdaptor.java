package com.example.cmpt276project.ExternalImages.SelectedImagesView.Model;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.example.cmpt276project.ExternalImages.SelectedImagesView.UI.ImageViewHolder;
import com.example.cmpt276project.R;

import java.util.ArrayList;
import java.util.HashMap;
/*
Adaptor class for selected images and created and binds the images to put in the recycler
 */


public class SelectedImagedRecyclerAdaptor extends RecyclerView.Adapter<ImageViewHolder> {
    private ArrayList<Bitmap> bitmapArrayList;
    private Context context;
    private HashMap<Integer, SelectedItems> RemoveHashMap = new HashMap<>();

    private Resources resources;


    public SelectedImagedRecyclerAdaptor(ArrayList<Bitmap> bitmapArrayList, Context context, HashMap<Integer, SelectedItems> RemoveHashMap, Resources resources) {
        this.bitmapArrayList = bitmapArrayList;
        this.context = context;
        this.RemoveHashMap = RemoveHashMap;
        this.resources = resources;
    }

    @Override
    public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item_gallery, parent, false);
        ImageViewHolder imageViewHolder = new ImageViewHolder(view,RemoveHashMap);
        return imageViewHolder;
    }

    @Override
    public void onBindViewHolder(ImageViewHolder holder, int position) {
        Bitmap b = bitmapArrayList.get(position);
        Drawable d = new BitmapDrawable();
        holder.bindDrawable(b, resources);

    }

    @Override
    public int getItemCount() {
        return bitmapArrayList.size();
    }

}

