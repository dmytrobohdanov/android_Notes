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

    public Note(String text) {
        setID();
        setBackgroundColorOfNote();
        setText(text);
    }

    //public methods
    public int getID() {
        return id;
    }
    public String getText(){
        return textOfNote;
    }
    public int getColor(){
        return colorOfNote;
    }

    public static void setIdGenerator(int lastId) {
        idGenerator = lastId;
    }

    public void setText(String text) {
        this.textOfNote = text;
    }

    public void setColor(int color){
        this.colorOfNote = color;
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

}
