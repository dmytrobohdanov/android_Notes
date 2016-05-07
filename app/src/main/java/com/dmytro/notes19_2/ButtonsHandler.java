package com.dmytro.notes19_2;


import android.app.Activity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

public class ButtonsHandler {
    private Button makeNewNote;
    private Activity activity;
    private SpeechHandler speechHandler;
    private PhotoButtonHandler photoHandler;

    private final int TEXT_BUTTON_ID = R.id.addNoteByTextButton;
    private final int VOICE_BUTTON_ID = R.id.addVoiceNoteButton;
    private final int PHOTO_BUTTON_ID = R.id.addPhotoNoteButton;

    NotesKeeper notesKeeper;

    ButtonsHandler(Activity activity, int buttonId) {
        this.activity = activity;

        switch (buttonId) {
            case TEXT_BUTTON_ID:
                setTextButtonHandler(activity, TEXT_BUTTON_ID);
                break;
            case VOICE_BUTTON_ID:
                setVoiceButtonHandler(activity, VOICE_BUTTON_ID);
                break;
            case PHOTO_BUTTON_ID:
                setPhotoButtonHandler(activity, PHOTO_BUTTON_ID);
                break;
            default:
                Log.d("ButtonsHandler", "wrong ID of button");
        }
    }

    private void setVoiceButtonHandler(Activity activity, int voice_button_id) {
        speechHandler = new SpeechHandler(activity);

        makeNewNote = (Button) activity.findViewById(voice_button_id);
        assert makeNewNote != null;
        makeNewNote.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    startSpeech();
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    stopSpeech();
                }
                return false;
            }
        });
    }

    /**
     * onClick method of the id.startButton
     * starting voice recognition
     */
    private void startSpeech() {
        speechHandler.startListening();
    }

    /**
     * onUp method of the id.startButton
     * stops listening
     */
    private void stopSpeech() {
        speechHandler.stopListening();
    }


    private void setPhotoButtonHandler(Activity activity, int photo_button_id) {
        photoHandler = new PhotoButtonHandler(activity, photo_button_id);

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

    public PhotoButtonHandler getPhotoButtonHandlerInstance() {
        return photoHandler;
    }
}
