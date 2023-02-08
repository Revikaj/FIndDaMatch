package com.example.cmpt276project.ExternalImages.SelectedImagesView.UI;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;

import com.example.cmpt276project.ExternalImages.SelectedImagesView.Model.SelectedItems;
import com.example.cmpt276project.R;
import java.util.HashMap;
import androidx.recyclerview.widget.RecyclerView;
/*
This class holds the recycler view and implements the onClicklLstener
 */
public class ImageViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private ImageView imageView;
    private HashMap<Integer, SelectedItems> RemoveHashMap = new HashMap<>();

    public ImageViewHolder(View itemView, HashMap<Integer, SelectedItems> RemoveHashMap) {
        super(itemView);
        imageView = itemView.findViewById(R.id.item_image_view);
        this.RemoveHashMap = RemoveHashMap;
        itemView.setOnClickListener(this);
    }

    public void bindDrawable(Bitmap bitmap, Resources resources) {
        Drawable drawable = new BitmapDrawable(resources, bitmap);
        imageView.setImageDrawable(drawable);
        SelectedItems selectedItems = new SelectedItems(bitmap, false);
        RemoveHashMap.put(getLayoutPosition(), selectedItems);
    }

    @Override
    public void onClick(View view) {
        if (RemoveHashMap.get(getLayoutPosition()).isSelected()) {
            RemoveHashMap.get(getLayoutPosition()).setSelected(false);
            view.setSelected(false);

        } else {
            RemoveHashMap.get(getLayoutPosition()).setSelected(true);
            view.setSelected(true);
        }

    }
}