package com.dmytro.notes19_2;

import android.app.Activity;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import java.util.Random;


//Note is class of notes
//
public class Note {
    //keeps last added note's id
    private static int idGenerator = 0;

    //array of note's colors
    private final int[] colors = {Color.rgb(64, 249, 138), Color.rgb(255, 255, 102),
            Color.rgb(255, 110, 110), Color.rgb(249, 136, 238), Color.rgb(168, 163, 242),
            Color.rgb(255, 255, 153)};


    //note's id
    private int id;
    private int colorOfNote;
    private String textOfNote;

    //Note's text keepers
    private ViewSwitcher vs;
    private EditText editText;
    private TextView textView;

    public Note(Activity activity, String text) {
        Log.d("Note", "creation of Note started");
        setID();
        setBackgroundColorOfNote();
        setText(text);
        createViewSwitcher(activity);
//        RelativeLayout layout = (RelativeLayout) activity.findViewById(R.id.layout);
        LinearLayout layout = (LinearLayout) activity.findViewById(R.id.layout1);
        layout.addView(vs);
        Log.d("Note", "Note creation done");
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
//        getting random color from array of colors
        colorOfNote = colors[Math.abs(rnd.nextInt()) % colors.length];
    }


    private void setID() {
        id = 1 + idGenerator++;
    }

    private void createViewSwitcher(Activity activity) {
        Log.d("Create ViewSwitcher", " start");
        vs = new ViewSwitcher(activity);
        vs.setId(id);
        vs.setBackgroundColor(colorOfNote);
        vs.setPadding(0, 10, 0, 1);

        //set params: width: MATCH_PARENT, height - WRAP_CONTENT
//        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
//                ViewGroup.LayoutParams.WRAP_CONTENT);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams
                (LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(0, 10, 0, 10);
        vs.setLayoutParams(layoutParams);


        createTextView(activity);
        createEditText(activity);
        vs.addView(textView);
        vs.addView(editText);


        Log.d("Create ViewSwitcher", " finish");

    }

    private void createTextView(Activity activity) {
        Log.d("Create textView", " start");
        textView = new TextView(activity);
        textView.setText(textOfNote);
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        textView.setLayoutParams(layoutParams);

        textView.setTextSize(17);//todo remove this hell


        //onClick listener. Changing TextView to EditText when clicked
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setText(textOfNote);
                vs.showNext();
            }
        });
        Log.d("Create textView", "finish");
    }

    private void createEditText(Activity activity) {
        Log.d("Create editText", " start");
        editText = new EditText(activity);
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        editText.setLayoutParams(layoutParams);
        View.OnFocusChangeListener focusChangeListener = new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                Log.d("onfocuschange", "run");
                //if unfocus EditText field, editText becomes textView again
                if (!hasFocus) {
                    textView.setText(textOfNote);
                    vs.showPrevious();
                }
            }
        };
        editText.setOnFocusChangeListener(focusChangeListener);
        Log.d("Create editText", " finish");
    }

    private void setText(String text) {
        this.textOfNote = text;
    }
}
