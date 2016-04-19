package com.dmytro.notes19_2;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
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
        Log.d("constructor", "start");
        photoButton = (Button) activity.findViewById(R.id.photoButton);
        this.mainActivity = activity;
        this.notes = notes;
        photoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("constructor", "onClick Start");
                dispatchTakePictureIntent();
                Log.d("constructor", "onClickFinished");
            }
        });
        Log.d("constructor", "finished");
    }

    private void dispatchTakePictureIntent() {
        Log.d("dispatch", "start");
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(mainActivity.getPackageManager()) != null) {
            Log.d("dispatch", "I'm in the IF");
            mainActivity.startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
            Log.d("dispatch", "startActivity for result");
        }
        Log.d("dispatch", "finished");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d("onActivityResult", "start");
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Log.d("onActivityResult", "in the IF");
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            notes.add(new Note(imageBitmap));
            Log.d("onActivityResult", "note Added");
        }
        Log.d("onActivityResult", "fin");
    }

}
