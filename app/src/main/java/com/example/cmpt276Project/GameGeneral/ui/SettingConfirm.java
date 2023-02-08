//Dialog box confirms user's game setting, allows them to go to options before starting game or start game directly without changing anything
package com.example.cmpt276project.GameGeneral.ui;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.cmpt276project.ExternalImages.Flikr.Model.ImageBitMapList;
import com.example.cmpt276project.R;
import com.example.cmpt276project.GameGeneral.model.Settings;

import java.util.ArrayList;

public class SettingConfirm extends AppCompatDialogFragment {
    Settings settings = Settings.getInstance();
    ArrayList<Bitmap> images = ImageBitMapList.get().getBitmapArrayList();

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        final int order = settings.getOrder();
        final String mode = settings.getMode();

        View v = LayoutInflater.from(getActivity())
                .inflate(R.layout.setting_confirm,null);
        DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch(which){
                    case DialogInterface.BUTTON_NEGATIVE:
                        boolean check = settings.FlickImageCheck(images);
                        if(settings.getMode().equals("Flickr") && !check) {
                            Intent intentOpt = Options.makeIntent(getContext());
                            startActivity(intentOpt);
                            Toast toast = Toast.makeText(getContext(),
                                    getString(R.string.toastt) + settings.getOrder() + getString(R.string.t),
                                    Toast.LENGTH_LONG);
                            toast.show();
                        }
                        else if (check || !settings.getMode().equals("Flickr")) {
                            Intent intentGame = Game.makeIntent(getContext());
                            startActivity(intentGame);
                        }
                        break;
                    case DialogInterface.BUTTON_POSITIVE:
                        Intent intentOK = Options.makeIntent(getContext());
                        startActivity(intentOK);
                        break;
                }
            }
        };

        return new AlertDialog.Builder(getActivity())
                .setTitle("")
                .setView(v)
                .setPositiveButton(android.R.string.ok, listener)
                .setNegativeButton(android.R.string.cancel, listener)
                .create();
    }
}
