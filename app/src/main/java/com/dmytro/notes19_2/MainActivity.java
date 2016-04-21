package com.dmytro.notes19_2;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

//    public static ArrayList<Note> notes;
    NotesKeeper notesKeeper;
    PhotoButtonHandler photoButtonHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        notesKeeper = NotesKeeper.getInstance();
//        notes = DataSaver.loadSavedNotes(notes);

        setContentView(R.layout.activity_main);


        TextButtonHandler textButtonHandler = new TextButtonHandler(this);

        SpeechHandler speechHandler = new SpeechHandler(this);
        VoiceButtonHandler voiceButtonHandler = new VoiceButtonHandler(this, speechHandler);

        photoButtonHandler = new PhotoButtonHandler(this);
    }



    @Override
    protected void onStart() {
        super.onStart();
//        notes = DataSaver.loadSavedNotes(notes);
        notesKeeper = NotesKeeper.getInstance();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
//        notes = DataSaver.loadSavedNotes(notes);
        notesKeeper = NotesKeeper.getInstance();
    }

    @Override
    protected void onResume() {
        super.onResume();
//        notes = DataSaver.loadSavedNotes(notes);
        notesKeeper = NotesKeeper.getInstance();
    }

    @Override
    protected void onPause() {
        notesKeeper.saveAllNotesToDisk();
        super.onPause();
    }

    @Override
    protected void onStop() {
        notesKeeper.saveAllNotesToDisk();
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        notesKeeper.saveAllNotesToDisk();
        super.onDestroy();
    }




    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        photoButtonHandler.handleResult(requestCode, resultCode, data);

    }

}

