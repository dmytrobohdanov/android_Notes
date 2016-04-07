package com.dmytro.notes19_2;
import android.content.Intent;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.dmytro.notes19_2.R;

public class MainActivity extends AppCompatActivity {

    SpeechRecognizer sr;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button makeNewNote = (Button) findViewById(R.id.startButton);

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

    private void stop() {
        sr.stopListening();
        TextView tv = (TextView) findViewById(R.id.recognizedText);

        assert tv != null;
        String text= SpeechRecognizer.RESULTS_RECOGNITION;
        tv.setText(text);
//        tv.setText(SpeechRecognizer.RESULTS_RECOGNITION + " " + counter);
    }

    private void start() {
        sr = SpeechRecognizer.createSpeechRecognizer(this);
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, "ru-RU");

        sr.startListening(intent);
    }
}
