package com.dmytro.notes19_2;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;


public class PhotoButtonHandler extends Activity {
    static final int REQUEST_IMAGE_CAPTURE = 1;
    private Button photoButton;
    Activity mainActivity;

    NotesKeeper notesKeeper;

    PhotoButtonHandler(Activity activity) {
        photoButton = (Button) activity.findViewById(R.id.photoButton);
        this.mainActivity = activity;
        notesKeeper = NotesKeeper.getInstance();
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


    // for now - useless
    //copy from MainActivity
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            Note newNote = new Note(imageBitmap);
            notesKeeper.add(newNote);
            ViewCreator viewCreator = new ViewCreator(this, newNote);
        }
    }

}
