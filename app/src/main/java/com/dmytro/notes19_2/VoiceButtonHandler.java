package com.dmytro.notes19_2;

import android.app.Activity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

/**
 * Describes behavior of button
 * which makes notes by voice
 * set onTouchListener:
 * onDown - start listening speech by SpeechHandler class
 * onUp - stops and ask SpeechHandler to do his job
 */
public class VoiceButtonHandler {
    SpeechHandler speechHandler;
    Button makeNewNote;

    public VoiceButtonHandler(SpeechHandler speechHandler, Activity activity) {
        makeNewNote = (Button) activity.findViewById(R.id.startButton);
        this.speechHandler = speechHandler;
        assert makeNewNote != null;
        makeNewNote.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    start();
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    stop();
                }
                return false;
            }
        });
    }

    /**
     * onClick method of the id.startButton
     * starting voice recognition
     */
    private void start() {
        speechHandler.startListening();
    }

    /**
     * onUp method of the id.startButton
     * stops listening
     */
    private void stop() {
        speechHandler.stopListening();
    }
}
