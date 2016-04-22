package com.dmytro.notes19_2;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;


/**
 * Saves data to the file
 * for now: using serialization
 * todo: someday - use DB instead of it
 */
public class DataSaver {

    //public methods

    /**
     * Public method for saving notes to disk
     *
     * @param notes array of notes
     */
    public static void saveNotes(ArrayList<Note> notes) {
        try {
            saveArrayOfNotes(notes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Returns  new array for notes if there wasn't saved notes
     * or array of saved notes
     *
     * @return array of notes
     */
    public static ArrayList<Note> loadSavedNotes() {
        try {
            //if there are saved notes
            return getArrayOfNotes();
        } catch (IOException e) {
            //if there is no this file - create new arraylist of notes
            return new ArrayList<>();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        //in case of error
        return null;
    }


    //private methods

    /**
     * Get array of Notes
     *
     * @return ArrayList<Note> object - the one that keeps all saved notes on hard drive
     * @throws IOException            if there is no such file
     * @throws ClassNotFoundException
     */
    private static ArrayList<Note> getArrayOfNotes() throws IOException, ClassNotFoundException {
        ArrayList<Note> notes = null;

        File notesFile = new File(android.os.Environment.getExternalStorageDirectory(), "notes.out");
        FileInputStream fileInputStream = new FileInputStream(notesFile);
//        FileInputStream fileInputStream = new FileInputStream("notes.out");
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

        try {
            notes = (ArrayList<Note>) objectInputStream.readObject();
        } catch (IOException e) {
            notes = new ArrayList<>();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return notes;
    }


    /**
     * Saving notes to hard drive
     *
     * @param notes - array of Notes has to be saved
     * @throws IOException
     */
    private static void saveArrayOfNotes(ArrayList<Note> notes) throws IOException {
        //serializing of our ArrayList of notes
        File notesFile = new File(android.os.Environment.getExternalStorageDirectory(), "notes.out");
        //TODO: W/System.err: java.io.FileNotFoundException: /notes.out: open failed: EROFS (Read-only file system)
        FileOutputStream fos = new FileOutputStream(notesFile);
        ObjectOutputStream oos = new ObjectOutputStream(fos);

        oos.writeObject(notes);
        oos.flush();
        oos.close();

    }

}
