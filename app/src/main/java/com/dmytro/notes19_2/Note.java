package com.dmytro.notes19_2;


import android.app.Activity;
import android.graphics.Color;
import android.widget.LinearLayout;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;


/**
 * Note is class for keeping notes
 * each note has either id, color, text
 * or id, photo
 */

public class Note implements Serializable {
    //keeps last added note's id
    private static int idGenerator = 0;

    //array of note's colors
    private final int[] colors = {Color.rgb(64, 249, 138), Color.rgb(255, 255, 102)};

    //note's id
    private int id;
    private int colorOfNote;
    private String textOfNote;

    //for photo note
    File photoFile = null;
//    Bitmap bitmap = null;

    /**
     * Constructor
     * for text notes
     *
     * @param text of the note
     */
    public Note(String text) {
        setID();
        setBackgroundColorOfNote();
        setText(text);
    }


    /**
     * Constructor of photo note
     *
     * @param photoFile keeps file with photo
     */
    public Note(File photoFile) {
        setID();
        this.photoFile = photoFile;
        setText(null);
    }


    //public methods

    /**
     * getID()
     *
     * @return ID of note
     */
    public int getID() {
        return id;
    }

    /**
     * getText()
     *
     * @return text of the note
     */
    public String getText() {
        return textOfNote;
    }

    /**
     * getColor()
     *
     * @return color of the note
     */
    public int getColor() {
        return colorOfNote;
    }

    //public static methods

    /**
     * Draw all notes in array,
     * if they haven't drawn yet
     *
     * @param activity we are working with
     * @param notes array of notes need to be drawn
     */
    public static void drawNotes(Activity activity, ArrayList<Note> notes){
        LinearLayout layout = (LinearLayout) activity.findViewById(R.id.layoutNotesKeeper);
        layout.removeAllViews();

        //null-array handler
        if(notes == null) { return; }

        //check are there any notes on the layout
        //if no - draw all notes
        if (activity.findViewById(notes.get(0).getID()) == null){
            for(Note note: notes){
                ViewCreator viewCreator = new ViewCreator(activity, note);
            }
//            int notesSize = notes.size();
//            for(int i = notesSize - 1; i >= 0; i--){
//                ViewCreator viewCreator = new ViewCreator(activity, notes.get(i));
//            }
        }
   }


    /**
     * Set idGenerator - the variable thats sets our id initialization
     *
     * @param lastId - the id of the last note in our list
     */
    //todo here could be problem: should find out how works id adding after app reboot
    public static void setIdGenerator(int lastId) {
        idGenerator = lastId;
    }

    /**
     * needs fo manual (outside of constructor) changing of the text of note
     *
     * @param text of the note
     */
    public void setText(String text) {
        this.textOfNote = text;
    }


    /**
     * Needs for manual (outside of constructor) changing of color of note
     *
     * @param color color we want to set for note
     */
    public void setColor(int color) {
        this.colorOfNote = color;
    }

    //private methods

    /**
     * sets random background color (from array) for note
     */
    private void setBackgroundColorOfNote() {
        Random rnd = new Random();
//        getting random color from array of colors
        colorOfNote = colors[Math.abs(rnd.nextInt()) % colors.length];
    }

    /**
     * setting id of note
     */
    private void setID() {
        id = 1 + idGenerator++;
    }

}
