package com.dmytro.notes19_2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.RelativeLayout;


import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static ArrayList<Note> notes = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        RelativeLayout rlayout = (RelativeLayout) findViewById();
        setContentView(R.layout.activity_main);
//        Button
        Log.d("mainActivity", "set content view");
        SpeechHandler speechHandler = new SpeechHandler(this, notes);
        Log.d("MainActivity", "speechHandler initialized");
        StartButtonHandler startButtonHandler = new StartButtonHandler(speechHandler, this);
        Log.d("Main activity", "Start button");

    }


}
