package com.dmytro.notes19_2;

import android.content.Intent;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class StartButtonHandler {
    SpeechRecognizer sr;
    //TODO spechRecognizer --> SpeechHandler
    public StartButtonHandler(Button makeNewNote, SpeechRecognizer sr) {
        this.sr = sr;
        assert makeNewNote != null;
        makeNewNote.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    Log.d("on action down", " down");
                    start();
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    Log.d("on action Up", " up");
                    stop();
                }
                return false;
            }
        });
    }

    //onClick method of the id.startButton
    //starting voice recognition
    private void start() {
        //todo handle this
        TextView tv = (TextView) MainActivity.findViewById(R.id.note1_text);
        assert tv != null;
        tv.setText(noteText);

        sr = SpeechRecognizer.createSpeechRecognizer(this);
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
//        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, "ru-RU");
        recognizeDirectly(intent);

        sr.startListening(intent);
    }

    //onUp method of the id.startButton
    //stops listening and
    //set to TextView id.recognizedText recognized text
    private void stop() {
        sr.stopListening();
    }

}
