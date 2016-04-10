package com.dmytro.notes19_2;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import java.util.Random;

public class Note implements View.OnFocusChangeListener, RecognitionListener {
    private static int idGenerator = 0; //keeps last added note's id
    private static final int [] colors = {Color.rgb(64,249,138), Color.rgb(255,255,102)};

    private int id; //note's id
    private String textOfNote;
    boolean onFocus;

    private ViewSwitcher vs;
    private EditText editText;
    private TextView textView;
    int colorOfNote;

    public Note(Activity activity){
        setID();
        setBackgroundColorOfNote();
        createViewSwitcher(activity);
        setText();
    }

    private void setBackgroundColorOfNote() {
        //temporary method
        //TODO: rewrite this method. Should set colors in turn, not random
        Random rnd = new Random();
        //getting random color from array of colors
        colorOfNote = colors[rnd.nextInt() % colors.length];
    }

    public int getID(){
        return id;
    }
    private void setID(){
        id = 1 + idGenerator++; //i'm not sure :)
    }

    private void createViewSwitcher(Activity activity) {
        vs = new ViewSwitcher(activity);
        vs.setId(id);
        vs.setBackgroundColor(colorOfNote);

        //TODO: how set layout params?
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams();
        vs.setLayoutParams(layoutParams);

        createTextView();
        createEditText();
        vs.addView(textView);
        vs.addView(editText);
    }

    private void createTextView() {
        //todo this method
        textView = new TextView();

    }

    private void createEditText (){
        //todo this method
        editText = new EditText();
    }

    public void setText(){

    }




    @Override
    public void onFocusChange(View v, boolean hasFocus) {

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
    public void onResults(Bundle results) {

    }

    @Override
    public void onPartialResults(Bundle partialResults) {

    }

    @Override
    public void onEvent(int eventType, Bundle params) {

    }
}
