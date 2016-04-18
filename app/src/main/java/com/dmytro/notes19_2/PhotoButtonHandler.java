package com.dmytro.notes19_2;

import android.app.Activity;
import android.content.Intent;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;


public class PhotoButtonHandler {
    static final int REQUEST_IMAGE_CAPTURE = 1;
    private Button photoButton;
    Activity mainActivity;

    PhotoButtonHandler(Activity activity){
        photoButton = (Button) activity.findViewById(R.id.photoButton);
        this.mainActivity = activity;

        photoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dispatchTakePictureIntent();
            }
        });

    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(mainActivity.getPackageManager()) != null) {
            mainActivity.startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }


}
