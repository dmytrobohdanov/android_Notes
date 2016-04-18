package com.dmytro.notes19_2;

import android.graphics.Bitmap;
import android.graphics.Color;

import java.util.Random;


/**
 * Note is class for keeping notes
 * each note has id, color, text
 * todo: add date and time for note
 */

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

    //for photo note
    Bitmap bitmap = null;

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
     * constructor
     * for photo notes
     *
     * @param bitmap - image from camera
     */
    public Note(Bitmap bitmap) {
        setID();
        this.bitmap = bitmap;
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
     * setting background color
     */
    private void setBackgroundColorOfNote() {
        //temporary method
        //TODO: rewrite this method. Should set colors in turn, not random
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
