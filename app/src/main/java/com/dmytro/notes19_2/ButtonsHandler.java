package com.dmytro.notes19_2;


import android.app.Activity;
import android.view.View;
import android.widget.Button;

public class ButtonsHandler {
    private Button makeNewNote;
    private Activity activity;

    private final int TEXT_BUTTON_ID = R.id.addNoteByTextButton;
    private final int VOICE_BUTTON_ID = R.id.addVoiceNoteButton;
    private final int PHOTO_BUTTON_ID = R.id.addPhotoNoteButton;

    NotesKeeper notesKeeper;

    ButtonsHandler (Activity activity, int ButtonId){
        setTextButtonHandler(activity, TEXT_BUTTON_ID);
        setVoiceButtonHandler(activity, VOICE_BUTTON_ID);
        setPhotoButtonHandler(activity, PHOTO_BUTTON_ID);
    }

    private void setPhotoButtonHandler(Activity activity, int photo_button_id) {
        PhotoHandler photoHandler = new PhotoHandler(activity, notesKeeper);
        Button photoButton = (Button) activity.findViewById(photo_button_id);
        photoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                photoHandler.dispatchTakePictureIntent();
            }
        });
    }

    private void setTextButtonHandler(final Activity activity, int text_button_id) {
        notesKeeper = NotesKeeper.getInstance();
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
                notesKeeper.add(newNote);
                ViewCreator viewCreator = new ViewCreator(activity, newNote);
                viewCreator.changeToEditText(activity);
            }
        });

    }

}
