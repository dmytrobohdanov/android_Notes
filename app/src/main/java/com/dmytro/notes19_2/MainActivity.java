package com.dmytro.notes19_2;
import android.content.Intent;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.dmytro.notes19_2.R;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements RecognitionListener {
    String noteText= "ping, i'm going to ";
    SpeechRecognizer sr;

    //array of recognized text
    List<String> recognizedText = new LinkedList<>();

    //some float array
//  TODO: find out what the hell is this array is about :-)


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("CREATION", " creating");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button makeNewNote = (Button) findViewById(R.id.startButton);

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
        TextView tv = (TextView) findViewById(R.id.recognizedText);
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
        String resultString = "debug ";
//        if(recognizedText == null){
////           recognizedText.add("Something wrong. I have no text");
//            Log.d("Null pointer fixing", "shit");
//            //return;
//        }

//        ArrayList <String> strings = new ArrayList<>(recognizedText);
//        for(String str: strings){
//            Log.d("array ", str);
//            resultString = resultString + "\n" + str;
//        }
        for(String str: recognizedText){
            Log.d("array ", "ping");
            Log.d("array ", resultString);
            resultString = resultString + "\n" + str;
        }
        TextView tv = (TextView) findViewById(R.id.recognizedText);
        assert tv != null;
        noteText = noteText + resultString;
        Log.d("stop", " finishing forming string");
        Log.d("stop", " The string is " + resultString);

        tv.setText(noteText);
    }


    //initialization of SpeechRecognizer
    public void recognizeDirectly(Intent recognizerIntent)
    {
        // SpeechRecognizer requires EXTRA_CALLING_PACKAGE, so add if it's not here
        if (!recognizerIntent.hasExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE))
        {
//            recognizerIntent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE, "com.dummy");
            recognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, "ru-RU");
            //maybe try sometime:
            //intent.putExtra(RecognizerIntent.EXTRA_PARTIAL_RESULTS, true);
        }

        SpeechRecognizer recognizer = getSpeechRecognizer();
        recognizer.startListening(recognizerIntent);
    }

    private SpeechRecognizer getSpeechRecognizer()
    {
        SpeechRecognizer recognizer;
        recognizer = SpeechRecognizer.createSpeechRecognizer(this);
        recognizer.setRecognitionListener(this);
        return recognizer;
    }


    //get results of recognition
    private void receiveResults(Bundle results)
    {
        if ((results != null)
                && results.containsKey(SpeechRecognizer.RESULTS_RECOGNITION))
        {
            List<String> heard =
                    results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
            float[] scores =
                    results.getFloatArray(SpeechRecognizer.CONFIDENCE_SCORES);

            receiveRecognizedText(heard, scores);
        }
    }

    //fill the textView id.recognizedText within received text
    private void receiveRecognizedText(List<String> heard, float[] scores) {
        //List recognizedText = recognizedText + heard;
        for(String str: heard){
            recognizedText.add(str);
        }
    }

    @Override
    public void onResults(Bundle results) {
        receiveResults(results);
    }

    @Override
    public void onPartialResults(Bundle partialResults) {
        receiveResults(partialResults);
    }

    @Override
    public void onReadyForSpeech(Bundle params) {

    }

    @Override
    public void onBeginningOfSpeech() {

    }

    @Override
    public void onRmsChanged(float rmsdB) {

    }

    @Override
    public void onBufferReceived(byte[] buffer) {

    }

    @Override
    public void onEndOfSpeech() {

    }

    @Override
    public void onError(int error) {
    }

    @Override
    public void onEvent(int eventType, Bundle params) {

    }
}
