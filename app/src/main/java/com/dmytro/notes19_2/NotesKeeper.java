package com.dmytro.notes19_2;

import java.util.ArrayList;

public class NotesKeeper {
    private static NotesKeeper instance;
    private static ArrayList<Note> notes;

    private NotesKeeper() {
        notes = DataSaver.loadSavedNotes();
    }


    //public methods

    /**
     * Returning instance of NotesKeeper
     *
     * @return instance of NotesKeeper
     */
    public static NotesKeeper getInstance() {
        if (instance == null) {
            instance = new NotesKeeper();
        }
        return instance;
    }

    /**
     * adds specified note to NotesKeeper
     *
     * @param note has to be added
     */
    public void add(Note note) {
        notes.add(note);
    }

    /**
     * Removes specified note from NotesKeeper
     *
     * @param note has to be removed
     */
    public static void remove(Note note) {
        notes.remove(note);
    }

    /**
     * Returning all notes
     *
     * @return array of notes
     */
    public ArrayList<Note> getAllNotes() {
        return notes;
    }

    /**
     * Saving notes to disk
     */
    public void saveAllNotesToDisk() {
        DataSaver.saveNotes(notes);
    }
}
