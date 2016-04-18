package com.dmytro.notes19_2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //    public static ArrayList<Note> notes = new ArrayList<>();
    public static ArrayList<Note> notes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadSavedNotes();

        setContentView(R.layout.activity_main);

        SpeechHandler speechHandler = new SpeechHandler(this, notes);

        VoiceButtonHandler voiceButtonHandler = new VoiceButtonHandler(speechHandler, this);
        TextButtonHandler textButtonHandler = new TextButtonHandler(this, notes);
    }


    @Override
    protected void onStart() {
        super.onStart();
        loadSavedNotes();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        loadSavedNotes();
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadSavedNotes();
    }

    @Override
    protected void onPause() {
        saveNotes();
        super.onPause();
    }

    @Override
    protected void onStop() {
        saveNotes();
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        saveNotes();
        super.onDestroy();
    }

    private void saveNotes() {
        try {
            DataSaver.saveArrayOfNotes(notes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadSavedNotes() {
        try {
            //if there are saved notes
            notes = DataSaver.getArrayOfNotes();
        } catch (IOException e) {
            //if there is no this file - create new arraylist of notes
            notes = new ArrayList<>();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

