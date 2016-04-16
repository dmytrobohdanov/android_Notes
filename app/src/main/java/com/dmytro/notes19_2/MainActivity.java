package com.dmytro.notes19_2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.RelativeLayout;


import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

//    public static ArrayList<Note> notes = new ArrayList<>();
    public static ArrayList<Note> notes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        try {
            notes = DataSaver.getArrayOfNotes();
        } catch (IOException e) {
            notes = new ArrayList<>();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        setContentView(R.layout.activity_main);

        SpeechHandler speechHandler = new SpeechHandler(this, notes);

        StartButtonHandler startButtonHandler = new StartButtonHandler(speechHandler, this);
    }

    @Override
    protected void onStart(){
        super.onStart();
        try {
            notes = DataSaver.getArrayOfNotes();
        } catch (IOException e) {
            notes = new ArrayList<>();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void onRestart(){
        super.onRestart();
        try {
            notes = DataSaver.getArrayOfNotes();
        } catch (IOException e) {
            notes = new ArrayList<>();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void onResume(){
        super.onResume();
        try {
            notes = DataSaver.getArrayOfNotes();
        } catch (IOException e) {
            notes = new ArrayList<>();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void onPause(){
        try {
            DataSaver.saveArrayOfNotes(notes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        super.onPause();
    }

    @Override
    protected void onStop(){
        try {
            DataSaver.saveArrayOfNotes(notes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        super.onStop();
    }

    @Override
    protected void onDestroy(){
        try {
            DataSaver.saveArrayOfNotes(notes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        super.onDestroy();
    }

}
