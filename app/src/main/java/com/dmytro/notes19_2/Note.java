package com.dmytro.notes19_2;

import android.app.Activity;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import java.util.Random;


//Note is class of notes
//
public class Note implements View.OnFocusChangeListener {
    //keeps last added note's id
    private static int idGenerator = 0;

    //array of note's colors
    private final int[] colors = {Color.rgb(64, 249, 138), Color.rgb(255, 255, 102)};

    //note's id
    private int id;
    private int colorOfNote;
    private String textOfNote;

    //Note's text keepers
    private ViewSwitcher vs;
    private EditText editText;
    private TextView textView;

    public Note(Activity activity, String text) {
        setID();
        setBackgroundColorOfNote();
        createViewSwitcher(activity);
        setText(text);
    }

    //public methods
    public int getID() {
        return id;
    }

    public static void setIdGenerator(int lastId) {
        idGenerator = lastId;
    }


    //private methods
    private void setBackgroundColorOfNote() {
        //temporary method
        //TODO: rewrite this method. Should set colors in turn, not random
        Random rnd = new Random();
        //getting random color from array of colors
        colorOfNote = colors[rnd.nextInt() % colors.length];
    }


    private void setID() {
        id = 1 + idGenerator++;
    }

    private void createViewSwitcher(Activity activity) {
        vs = new ViewSwitcher(activity);
        vs.setId(id);
        vs.setBackgroundColor(colorOfNote);

        //set params: width: MATCH_PARENT, height - WRAP_CONTENT
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        vs.setLayoutParams(layoutParams);

        createTextView(activity);
        createEditText(activity);
        vs.addView(textView);
        vs.addView(editText);
    }

    private void createTextView(Activity activity) {
        textView = new TextView(activity);
        textView.setText(textOfNote);
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        textView.setLayoutParams(layoutParams);


        //onClick listener. Changing TextView to EditText when clicked
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setText(textOfNote);
                vs.showNext();
            }
        });

    }

    private void createEditText(Activity activity) {
        editText = new EditText(activity);
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        editText.setLayoutParams(layoutParams);
    }

    private void setText(String text) {
        this.textOfNote = text;
    }


    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        //if unfocus EditText field, editText becomes textView again
        if (!hasFocus) {
            textView.setText(textOfNote);
            vs.showPrevious();
        }
    }


}
