package com.dmytro.notes19_2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static ArrayList<Note> notes = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SpeechHandler speechHandler = new SpeechHandler(this, notes);
        StartButtonHandler startButtonHandler = new StartButtonHandler(speechHandler, this);

    }


}
