package com.dmytro.notes19_2;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;


public class PhotoButtonHandler extends Activity{
    static final int REQUEST_IMAGE_CAPTURE = 1;
    private Button photoButton;
    Activity mainActivity;

    //pointer to arraylist of notes
    private ArrayList<Note> notes;

    PhotoButtonHandler(Activity activity, ArrayList<Note> notes) {
        photoButton = (Button) activity.findViewById(R.id.photoButton);
        this.mainActivity = activity;
        this.notes = notes;
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            Note newNote = new Note(imageBitmap);
            notes.add(newNote);
            ViewCreator viewCreator = new ViewCreator(this, newNote);
        }
    }

}
