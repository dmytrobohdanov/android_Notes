package com.dmytro.notes19_2;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class DataSaver {

      //public methods
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
