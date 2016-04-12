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

public class Note implements View.OnFocusChangeListener {
    private static int idGenerator = 0; //keeps last added note's id
    private final int [] colors = {Color.rgb(64,249,138), Color.rgb(255,255,102)};

    private int id; //note's id
    private String textOfNote;
    boolean onFocus;

    private ViewSwitcher vs;
    private EditText editText;
    private TextView textView;
    int colorOfNote;

    public Note(Activity activity, String text){
        setID();
        setBackgroundColorOfNote();
        createViewSwitcher(activity);
        setText(text);
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
        id = 1 + idGenerator++;
    }

    private void createViewSwitcher(Activity activity) {
        vs = new ViewSwitcher(activity);
        vs.setId(id);
        vs.setBackgroundColor(colorOfNote);

        //TODO: how set layout params?
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams();
        vs.setLayoutParams(layoutParams);

        createTextView(activity);
        createEditText(activity);
        vs.addView(textView);
        vs.addView(editText);
    }

    private void createTextView(Activity activity) {
        textView = new TextView(activity);
        textView.setText(textOfNote);
        //todo: params
        textView.setLayoutParams();

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //change text view to edit text
                editText.setText(textOfNote);
                vs.showNext();
            }
        });

    }

    private void createEditText (Activity activity){
        //todo this method
        editText = new EditText(activity);
        editText.setLayoutParams();
    }

    public void setText(String text){
        this.textOfNote = text;
    }


    public static void setIdGenerator (int lastId) {
        idGenerator = lastId;
    }


    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        //if unfocus edit text, editText become textView again
        if(!hasFocus){
            textView.setText(textOfNote);
            vs.showPrevious();
        }
    }

}
