package com.dmytro.notes19_2;

import android.app.Activity;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;


/**
 * Text Button Handler
 * listening onClick of button
 * create new empty Note and displays it
 */
public class TextButtonHandler {
    private Button makeNewNote;
    private Activity activity;

    //pointer to arraylist of notes
    private ArrayList<Note> notes;

    TextButtonHandler(final Activity activity, final ArrayList<Note> notes) {
        this.notes = notes;
        makeNewNote = (Button) activity.findViewById(R.id.addNoteByTextButton);

        makeNewNote.setOnClickListener(new View.OnClickListener() {
            /**
             * Called when a view has been clicked.
             * Create new empty Note,
             * pass it to ViewCreator
             * asks ViewCreator to show EditText
             *
             * @param v The view that was clicked.
             */
            @Override
            public void onClick(View v) {
                Note newNote = new Note("");
                notes.add(newNote);
                ViewCreator viewCreator = new ViewCreator(activity, newNote);
                viewCreator.changeToEditText(activity);
            }
        });

    }


}
