package com.example.cmpt276project.ExternalImages.Flikr.UI;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cmpt276project.ExternalImages.Flikr.Model.FlickrFetchr;
import com.example.cmpt276project.ExternalImages.Flikr.Model.GalleryItem;
import com.example.cmpt276project.ExternalImages.Flikr.Model.ImageBitMapList;
import com.example.cmpt276project.ExternalImages.Flikr.Model.QueryPreferences;
import com.example.cmpt276project.ExternalImages.Flikr.Model.ThumbnailDownloader;
import com.example.cmpt276project.ExternalImages.SelectedImagesView.Model.SelectedImages;
import com.example.cmpt276project.ExternalImages.SelectedImagesView.Model.SelectedItems;
import com.example.cmpt276project.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

//code highly influenced from The Big Nerd Ranch Guide (3rd ed)
//implements all the logic for the activity
public class PhotoGalleryFragment extends Fragment {
    private ArrayList<Bitmap> list = new ArrayList<>();
    private HashMap<Integer, SelectedItems> hashMap = new HashMap<>();
    private RecyclerView mPhotoRecyclerView;
    private static final String TAG = "PhotoGalleryFragment";
    private List<GalleryItem> mItems = new ArrayList<>();
    private ThumbnailDownloader<PhotoHolder> mThumbnailDownloader;

    public static PhotoGalleryFragment newInstance() {
        return new PhotoGalleryFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        setHasOptionsMenu(true);
        this.hashMap.clear();
        updateItems();
        Handler responseHandler = new Handler();
        mThumbnailDownloader = new ThumbnailDownloader<>(responseHandler);
        mThumbnailDownloader.setThumbnailDownloadListener(
                new ThumbnailDownloader.ThumbnailDownloadListener<PhotoHolder>() {
                    @Override
                    public void onThumbnailDownloaded(PhotoHolder photoHolder, Bitmap bitmap) {
                        photoHolder.bindDrawable(getResources(), bitmap);
                        list.add(bitmap);
                    }
                }
        );
        mThumbnailDownloader.start();
        mThumbnailDownloader.getLooper();
        Log.i(TAG, "Background thread started");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_photo_gallery, container, false);
        mPhotoRecyclerView = v.findViewById(R.id.photo_recycler_view);
        mPhotoRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        setupAdapter();
        return v;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        super.onCreateOptionsMenu(menu, menuInflater);
        menuInflater.inflate(R.menu.fragment_photo_gallery, menu);
        MenuItem searchItem = menu.findItem(R.id.menu_item_search);
        MenuItem AddItem = menu.findItem(R.id.btnAdd);

        final SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                Log.d(TAG, "QueryTextSubmit: " + s);
                QueryPreferences.setStoredQuery(getActivity(), s);
                updateItems();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                Log.d(TAG, "QueryTextChange: " + s);
                return false;
            }
        });

        searchView.setOnSearchClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String query = QueryPreferences.getStoredQuery(getActivity());
                searchView.setQuery(query, false);
            }
        });

        AddItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                for (SelectedItems i : hashMap.values()) {
                    if (i.isSelected()) {
                        Intent intent = SelectedImages.makeIntent(getContext());
                        startActivity(intent);
                        ImageBitMapList img1 = ImageBitMapList.get();
                        Bitmap image = i.getBitmap();
                        img1.getBitmapArrayList().add(image);
                        i.setSelected(false);

                        ContextWrapper cw = new ContextWrapper(getContext());
                        File directory = cw.getDir("FindDaMatch", Context.MODE_PRIVATE);
                        int id = image.getGenerationId();
                        File mypath = new File(directory, String.valueOf(id));
                        FileOutputStream fos = null;
                        try {
                            fos = new FileOutputStream(mypath);
                            image.compress(Bitmap.CompressFormat.PNG, 100, fos);
                        } catch (Exception e) {
                            e.printStackTrace();
                        } finally {
                            try {
                                assert fos != null;
                                fos.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
                return false;
            }
        });
    }

    private void updateItems() {
        String query = QueryPreferences.getStoredQuery(getActivity());
        new FetchItemsTask(query).execute();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_item_clear) {
            QueryPreferences.setStoredQuery(getActivity(), null);
            updateItems();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mThumbnailDownloader.quit();
        Log.i(TAG, "Background thread destroyed");
    }

    private void setupAdapter() {
        if (isAdded()) {
            mPhotoRecyclerView.setAdapter(new PhotoAdapter(mItems));
        }
    }

    @SuppressLint("StaticFieldLeak")
    private class FetchItemsTask extends AsyncTask<Void, Void, List<GalleryItem>> {
        private String mQuery;

        public FetchItemsTask(String query) {
            mQuery = query;
        }

        @Override
        protected List<GalleryItem> doInBackground(Void... params) {

            if (mQuery == null) {
                return new FlickrFetchr().fetchRecentPhotos();
            } else {
                return new FlickrFetchr().searchPhotos(mQuery);
            }
        }

        @Override
        protected void onPostExecute(List<GalleryItem> items) {
            mItems = items;
            setupAdapter();
        }
    }

    //using this function to create a bitmap array of the images
    private class PhotoHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView mItemImageView;
        private List<GalleryItem> mGalleryItems;

        public PhotoHolder(View itemView) {
            super(itemView);
            mItemImageView = itemView.findViewById(R.id.item_image_view);
            itemView.setOnClickListener(this);
        }

        public void bindDrawable(Resources resources, Bitmap bitmap) {
            Drawable drawable = new BitmapDrawable(resources, bitmap);
            SelectedItems item = new SelectedItems(bitmap, false);
            hashMap.put(getLayoutPosition(), item);
            mItemImageView.setImageDrawable(drawable);
        }

        @Override
        public void onClick(View view) {
            if (Objects.requireNonNull(hashMap.get(getLayoutPosition())).isSelected()) {
                Objects.requireNonNull(hashMap.get(getLayoutPosition())).setSelected(false);
                view.setSelected(false);
            } else {
                Objects.requireNonNull(hashMap.get(getLayoutPosition())).setSelected(true);
                view.setSelected(true);
            }
        }
    }

    private class PhotoAdapter extends RecyclerView.Adapter<PhotoHolder> {
        private List<GalleryItem> mGalleryItems;

        public PhotoAdapter(List<GalleryItem> galleryItems) {
            mGalleryItems = galleryItems;
        }

        @NonNull
        @Override
        public PhotoHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(getActivity());
            View view = inflater.inflate(R.layout.list_item_gallery, viewGroup, false);
            return new PhotoHolder(view);
        }

        @Override
        public void onBindViewHolder(PhotoHolder photoHolder, int position) {
            GalleryItem galleryItem = mGalleryItems.get(position);
             Bitmap b = BitmapFactory.decodeResource(getResources(),R.drawable.cupcake);
            photoHolder.bindDrawable(getResources(), b);
            mThumbnailDownloader.queueThumbnail(photoHolder, galleryItem.getUrl());
        }

        @Override
        public int getItemCount() {
            return mGalleryItems.size();
        }
    }
}