//Class used to implement actions in the activity.
package com.example.cmpt276project.ExternalImages.SelectedImagesView.Model;

import android.content.ClipData;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cmpt276project.ExternalImages.Flikr.Model.ImageBitMapList;
import com.example.cmpt276project.ExternalImages.Flikr.UI.PhotoGalleryActivity;
import com.example.cmpt276project.R;
import com.example.cmpt276project.GameGeneral.ui.Options;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class SelectedImages extends AppCompatActivity {
    private static final int PICK_IMAGE = 1;
    private static final String TAG = "Save chosen images";

    RecyclerView recyclerView;
    private SelectedImagedRecyclerAdaptor adaptor;
    private ArrayList<Bitmap> images;
    private File directory;
    private HashMap<Integer, SelectedItems> RemovehashMap = new HashMap<>();

    public static Intent makeIntent(Context c) {
        return new Intent(c, SelectedImages.class);
    }

    @Override
    public void onBackPressed() {
        Intent intent = Options.makeIntent(SelectedImages.this);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_images);

        ContextWrapper cw = new ContextWrapper(getApplicationContext());
        directory = cw.getDir("FindDaMatch", Context.MODE_PRIVATE);

        images = ImageBitMapList.get().getBitmapArrayList();
        loadImageFromStorage(images);
        setupRecyclerAdapter();
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateAdapter();
    }

    private void updateAdapter() {
        this.images = ImageBitMapList.get().getBitmapArrayList();
        adaptor = new SelectedImagedRecyclerAdaptor(images, SelectedImages.this,RemovehashMap,getResources());
        recyclerView.setAdapter(adaptor);
    }

    private void setupRecyclerAdapter() {
        this.recyclerView = findViewById(R.id.photo_recycler_view1);
        adaptor = new SelectedImagedRecyclerAdaptor(images, SelectedImages.this, RemovehashMap, getResources());
        recyclerView.setAdapter(adaptor);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.selected_images_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.btnAdd2:
                Intent intent = PhotoGalleryActivity.makeIntent(SelectedImages.this);
                startActivity(intent);
                break;
            case R.id.btnAdd:
                // Code learnt from Desarrollador Creativo Youtube Video
                Intent intent1 = new Intent();
                intent1.setType("image/*");
                intent1.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                intent1.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent1, "Select images"), PICK_IMAGE);
                Toast.makeText(this, "Press and hold the image(s) to add to your set", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btnRemove:
                ArrayList<Bitmap> bitmaps = new ArrayList<>();
                for(SelectedItems i:RemovehashMap.values()) {
                    if(i.isSelected()) {
                        deleteImage(i.getBitmap().getGenerationId());
                        bitmaps.add(i.bitmap);
                        i.setSelected(false);
                    }
                }
                ImageBitMapList.get().remove(bitmaps);
                RemovehashMap.clear();
                updateAdapter();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            ClipData clipData = data.getClipData();
            if (clipData != null) {
                for (int i = 0; i < clipData.getItemCount(); i++) {
                    ClipData.Item item = clipData.getItemAt(i);
                    Uri uri = item.getUri();
                    Log.e("Image: ", uri.toString());
                    try {
                        Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
                        images.add(bitmap);
                        saveToInternalStorage(bitmap);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            else {
                Uri uri = data.getData();
                if(uri != null){
                    Bitmap bitmap = null;
                    try {
                        bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    images.add(bitmap);
                    assert bitmap != null;
                    saveToInternalStorage(bitmap);
                }
            }
        }
    }

    private void saveToInternalStorage(Bitmap image){
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

    private void loadImageFromStorage(ArrayList<Bitmap> images) {
        File[] files = directory.listFiles();
        assert files != null;
        for (File file : files) {
            if (images.size() < files.length) {
                Bitmap image = BitmapFactory.decodeFile(file.getAbsolutePath());
                images.add(image);
                System.out.println(file.delete() + "----------------Deleted");
                saveToInternalStorage(image);
            }
        }
    }

    public void deleteImage(int id) {
        if (directory.isDirectory()) {
            for (int i = 0; i < Objects.requireNonNull(directory.listFiles()).length; i++) {
                File file = Objects.requireNonNull(directory.listFiles())[i];
                if (file.getName().equals(String.valueOf(id))) {
                    System.out.println(file.delete() + "----------------Deleted " + id);
                }
            }
        }
    }

//    private static String encodeTobase64(Bitmap image) {
//        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        image.compress(Bitmap.CompressFormat.PNG, 100, baos);
//        byte[] b = baos.toByteArray();
//        String imageEncoded = Base64.encodeToString(b, Base64.DEFAULT);
//        Log.d("Image Log:", imageEncoded);
//        return imageEncoded;
//    }


}



