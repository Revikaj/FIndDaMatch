//class to hold data of each image in the view

package com.example.cmpt276project.ExternalImages.SelectedImagesView.Model;

import android.graphics.Bitmap;

public class SelectedItems {
    Bitmap bitmap;
    boolean selected;

    public SelectedItems(Bitmap bitmap, boolean selected) {
        this.bitmap = bitmap;
        this.selected = selected;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
