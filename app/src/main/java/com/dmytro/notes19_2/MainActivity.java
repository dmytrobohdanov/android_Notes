package com.dmytro.notes19_2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //    public static ArrayList<Note> notes = new ArrayList<>();
    public static ArrayList<Note> notes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        notes = DataSaver.loadSavedNotes(notes);

        setContentView(R.layout.activity_main);


        TextButtonHandler textButtonHandler = new TextButtonHandler(this, notes);

        SpeechHandler speechHandler = new SpeechHandler(this, notes);
        VoiceButtonHandler voiceButtonHandler = new VoiceButtonHandler(this, speechHandler);

        PhotoButtonHandler photoButtonHandler = new PhotoButtonHandler(this, notes);
    }



    @Override
    protected void onStart() {
        super.onStart();
        notes = DataSaver.loadSavedNotes(notes);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        notes = DataSaver.loadSavedNotes(notes);
    }

    @Override
    protected void onResume() {
        super.onResume();
        notes = DataSaver.loadSavedNotes(notes);
    }

    @Override
    protected void onPause() {
        DataSaver.saveNotes(notes);
        super.onPause();
    }

    @Override
    protected void onStop() {
        DataSaver.saveNotes(notes);
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        DataSaver.saveNotes(notes);
        super.onDestroy();
    }

}

