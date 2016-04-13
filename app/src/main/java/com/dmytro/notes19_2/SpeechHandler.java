package com.dmytro.notes19_2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.util.Log;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


//SpeechHandler is a class which listening speech and create text from it
//constructor receive 2 params: Activity and ArrayList of notes
//it has 2 public methods:
//startListening()
//stopListening()
//after speech recognition class call method (onResult() or onPartialResults())
//which add new Note with recognized text

public class SpeechHandler implements RecognitionListener {

    //array of recognized variants of text
    private List<String> recognizedText = new LinkedList<>();
    //pointer to array of notes in MainActivity
    private ArrayList<Note> notes;
    //pointer to current activity
    private Activity activity;

    private SpeechRecognizer sr;

    //constructor
    public SpeechHandler(Activity activity, ArrayList<Note> notes) {
        Log.d("SpeechHandler", "creation");
        this.activity = activity;
        this.notes = notes;
        Log.d("SpeechHandler", "creation done");
    }

    //public methods:

    public void startListening() {
        Log.d("SpeechHandler", "startListening");
        sr = SpeechRecognizer.createSpeechRecognizer(activity);
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        recognizeDirectly(intent);

        sr.startListening(intent);
        Log.d("SpeechHandler", "startListening finished");
    }

    public void stopListening() {
        Log.d("SpeechHandler", "stop listening");
        sr.stopListening();
    }


    //private methods:

    //initialization of SpeechRecognizer
    private void recognizeDirectly(Intent recognizerIntent) {
        Log.d("SpeechHandler", "recognize");
        // SpeechRecognizer requires EXTRA_CALLING_PACKAGE, so add if it's not here
        if (!recognizerIntent.hasExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE)) {
            recognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, "ru-RU");
            //maybe try sometime:
            //intent.putExtra(RecognizerIntent.EXTRA_PARTIAL_RESULTS, true);
            //recognizerIntent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE, "com.dummy");
        }

        SpeechRecognizer recognizer = getSpeechRecognizer();
        recognizer.startListening(recognizerIntent);
    }

    private SpeechRecognizer getSpeechRecognizer() {
        SpeechRecognizer recognizer;
        recognizer = SpeechRecognizer.createSpeechRecognizer(activity);
        recognizer.setRecognitionListener(this);
        return recognizer;
    }

    //get list of variants of recognized text
    //todo: sometime change void method with global variable to List-returned method
    private void receiveRecognizedText(List<String> heard, float[] scores) {
        recognizedText.clear();
        for (String str : heard) {
            recognizedText.add(str);
        }
    }

    //get results of recognition
    private void receiveResults(Bundle results) {
        Log.d("SpeechHandler", "receive result");
        if ((results != null)
                && results.containsKey(SpeechRecognizer.RESULTS_RECOGNITION)) {
            List<String> heard =
                    results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
            float[] scores =
                    results.getFloatArray(SpeechRecognizer.CONFIDENCE_SCORES);

            receiveRecognizedText(heard, scores);
        }
    }


    private String getRecognizedText() {
        //return first variant of recognized text, the most probably one
        return recognizedText.get(0);
    }

    //adds new Note to array of notes
    private void handleResult() {
        notes.add(new Note(activity, getRecognizedText()));
    }

    @Override
    public void onResults(Bundle results) {
        Log.d("SpeechHandler", "on result");
        receiveResults(results);
        handleResult();
    }

    @Override
    public void onPartialResults(Bundle partialResults) {
        Log.d("SpeechHandler", "on partical result");
        receiveResults(partialResults);
        handleResult();
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
