package com.dmytro.notes19_2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    NotesKeeper notesKeeper;
    PhotoButtonHandler photoButtonHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        notesKeeper = NotesKeeper.getInstance();

        TextButtonHandler textButtonHandler = new TextButtonHandler(this);

        SpeechHandler speechHandler = new SpeechHandler(this);
        VoiceButtonHandler voiceButtonHandler = new VoiceButtonHandler(this, speechHandler);

        photoButtonHandler = new PhotoButtonHandler(this);
    }


    @Override
    protected void onStart() {
        super.onStart();
        notesKeeper = NotesKeeper.getInstance();
        Note.drawNotes(this, notesKeeper.getAllNotes());
    }


    @Override
    protected void onResume() {
        super.onResume();
        notesKeeper = NotesKeeper.getInstance();
        Note.drawNotes(this, notesKeeper.getAllNotes());
    }

    @Override
    protected void onStop() {
        notesKeeper.saveAllNotesToDisk();
        super.onStop();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        photoButtonHandler.handleResult(requestCode, resultCode, data);

    }

}

