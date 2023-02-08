package com.example.cmpt276project.ExternalImages.Flikr.Model;

import android.content.Context;
import android.preference.PreferenceManager;

public class QueryPreferences {
    //Singleton view to store query
    //code highly influenced from The Big Nerd Ranch Guide (3rd ed)

    private static final String PREF_SEARCH_QUERY = "searchQuery";
    private static final String PREF_IMAGES="ImagesArrayList";
    public static String getStoredQuery(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context)
                .getString(PREF_SEARCH_QUERY, null);

    }
    public static void setStoredQuery(Context context, String query) {
        PreferenceManager.getDefaultSharedPreferences(context)
                .edit()
                .putString(PREF_SEARCH_QUERY, query)
                .apply();
    }

//    public static ArrayList<Bitmap> getStoredArray(Context context){
//        ArrayList<Bitmap> array=new ArrayList<>();
//        //return PreferenceManager.getDefaultSharedPreferences(context).getString(PREF_IMAGES,null);
//        return array;
//    }
//
//

}
