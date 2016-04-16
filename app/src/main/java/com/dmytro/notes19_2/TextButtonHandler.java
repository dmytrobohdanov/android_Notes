package com.dmytro.notes19_2;

import android.app.Activity;
import android.view.View;
import android.widget.Button;


public class TextButtonHandler implements View.OnClickListener{
    Button makeNewNote;

    TextButtonHandler(Activity activity) {
        makeNewNote = (Button) activity.findViewById(R.id.addNoteByTextButton);
        Note newNote = new Note("");
        ViewCreator viewCreator = new ViewCreator(activity, newNote);

    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {

    }
}
