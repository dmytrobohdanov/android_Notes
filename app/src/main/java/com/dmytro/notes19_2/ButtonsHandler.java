package com.dmytro.notes19_2;


import android.app.Activity;
import android.view.View;
import android.widget.Button;

public class ButtonsHandler {
    private Button makeNewNote;
    private Activity activity;

    private final int TEXT_BUTTON_ID;

    NotesKeeper notesKeeper;

    ButtonsHandler (Activity activity, int textButtonId){
        TEXT_BUTTON_ID = textButtonId;
        setTextButtonHandler(activity, TEXT_BUTTON_ID);
    }

    private void setTextButtonHandler(Activity activity, int text_button_id) {

    }

//    TextButtonHandler(final Activity activity) {
//        notesKeeper = NotesKeeper.getInstance();
//        makeNewNote = (Button) activity.findViewById(R.id.addNoteByTextButton);
//
//        makeNewNote.setOnClickListener(new View.OnClickListener() {
//            /**
//             * Called when a view has been clicked.
//             * Create new empty Note,
//             * pass it to ViewCreator
//             * asks ViewCreator to show EditText
//             *
//             * @param v The view that was clicked.
//             */
//            @Override
//            public void onClick(View v) {
//                Note newNote = new Note("");
//                notesKeeper.add(newNote);
//                ViewCreator viewCreator = new ViewCreator(activity, newNote);
//                viewCreator.changeToEditText(activity);
//            }
//        });
//
//    }
}
