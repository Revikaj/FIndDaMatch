package com.example.cmpt276project.ExternalImages.Flikr.UI;

import android.content.Context;
import android.content.Intent;
//code highly influenced from The Big Nerd Ranch Guide (3rd ed)

//Launches photoGalleryActivity
public class PhotoGalleryActivity extends SingleFragmentActivity {
    public static Intent makeIntent(Context c) {
        return new Intent(c, PhotoGalleryActivity.class);
    }

    @Override
    protected PhotoGalleryFragment createFragment() {
        return PhotoGalleryFragment.newInstance();
    }
}
