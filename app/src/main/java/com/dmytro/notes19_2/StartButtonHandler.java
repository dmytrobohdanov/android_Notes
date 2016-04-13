package com.dmytro.notes19_2;

import android.app.Activity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

public class StartButtonHandler {
    SpeechHandler speechHandler;
    Button makeNewNote;

    public StartButtonHandler(SpeechHandler speechHandler, Activity activity) {
        makeNewNote = (Button) activity.findViewById(R.id.startButton);
        Log.d("Start button", " counstructor");
        this.speechHandler = speechHandler;
        assert makeNewNote != null;
        makeNewNote.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.d("Start button", " eventListener set");
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    start();
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    stop();
                }
                return false;
            }
        });
    }

    //onClick method of the id.startButton
    //starting voice recognition
    private void start() {
        Log.d("Start button", " start");
        speechHandler.startListening();
    }

    //onUp method of the id.startButton
    //stops listening
    private void stop() {
        Log.d("start button", " stop");
        speechHandler.stopListening();
    }
}
