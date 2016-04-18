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
 * has 2 static methods
 */
public class DataSaver {

    //public methods

    /**
     * Get array of Notes
     *
     * @return ArrayList<Note> object - the one that keeps all saved notes on hard drive
     * @throws IOException            if there is no such file
     * @throws ClassNotFoundException
     */
    public static ArrayList<Note> getArrayOfNotes() throws IOException, ClassNotFoundException {
        ArrayList<Note> notes = null;

        FileInputStream fileInputStream = new FileInputStream("notes.out");
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

        try {
            notes = (ArrayList<Note>) objectInputStream.readObject();
        } catch (IOException e) {
//            e.printStackTrace();
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
    public static void saveArrayOfNotes(ArrayList<Note> notes) throws IOException {
        //serializing of our ArrayList of notes
        File notesFile = new File("notes.out");
        FileOutputStream fos = new FileOutputStream(notesFile);
        ObjectOutputStream oos = new ObjectOutputStream(fos);

        oos.writeObject(notes);
        oos.flush();
        oos.close();

    }
}
