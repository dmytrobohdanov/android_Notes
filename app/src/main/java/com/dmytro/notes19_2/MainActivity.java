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
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements RecognitionListener {
    String noteText= "";
    SpeechRecognizer sr;


    //array of recognized text
    List<String> recognizedText = new LinkedList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button makeNewNote = (Button) findViewById(R.id.startButton);

        StartButtonHandler startButtonHandler = new StartButtonHandler(makeNewNote, sr);

    }



    //todo: move all below to speach handler and starButtonhandler
 
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
        recognizedText.clear();
        for(String str: heard){
            recognizedText.add(str);
        }
    }

    @Override
    public void onResults(Bundle results) {
        receiveResults(results);
        TextView tv = (TextView) findViewById(R.id.note1_text);
        assert tv != null;
        noteText = noteText + recognizedText.get(0);
        tv.setText(noteText);

    }

    @Override
    public void onPartialResults(Bundle partialResults) {
        receiveResults(partialResults);
        TextView tv = (TextView) findViewById(R.id.note1_text);
        assert tv != null;
        noteText = noteText + recognizedText.get(0);
        tv.setText(noteText);

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
