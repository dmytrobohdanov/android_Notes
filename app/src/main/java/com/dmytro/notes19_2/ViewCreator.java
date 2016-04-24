package com.dmytro.notes19_2;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ViewSwitcher;
import java.io.File;

/**
 * ViewCreator
 * ask for note should be shown
 * and adds it to specified layout
 */
public class ViewCreator {
    private int layoutId = R.id.layoutNotesKeeper;
    private Activity activity;
    private Note note;
    private String textOfNote;
    private int colorOfNote;
    private int id;

    //Note's text keepers
    private ViewSwitcher vs;
    private EditText editText;
    private TextView textView;

    //Note's photo keeper
    ImageView imageNote;

    /**
     * Constructor of class
     * for text and voice notes
     *
     * @param activity - current activity
     * @param note     - the note has to be shown
     */
    ViewCreator(Activity activity, Note note) {
        this.id = note.getID();
        this.activity = activity;
        this.note = note;

        LinearLayout layout = (LinearLayout) activity.findViewById(layoutId);

        /**
         * checking is this note text/from voice or a photo note
         * if bitmap is null so it is text/voice note and we create TextView(ViewSwitcher)
         * else it's photo note and we creating ImageView
         */
        if (note.photoFile == null) {
            this.colorOfNote = note.getColor();
            this.textOfNote = note.getText();

            createViewSwitcher(activity);
            layout.addView(vs, 0);
        } else {
            imageNote = createImageNote(activity, note.photoFile);
            layout.addView(imageNote, 0);
        }
    }

    /**
     * creating ImageNote
     * and adds it to layout
     *
     * @param activity current
     * @param photofile image of note
     */
    private ImageView createImageNote(final Activity activity, final File photofile) {
        ImageView imageNote = new ImageView(activity);

        //todo: do something with width and height
        int width = 350;
        int height = 280;

        //get preview from URI
        Bitmap bitmap = ThumbnailUtils.extractThumbnail(
                BitmapFactory.decodeFile(photofile.getPath()),width, height);

        //set image to ImageView
        imageNote.setImageBitmap(bitmap);

        //set parameters to view
        imageNote.setPadding(0, 10, 0, 1);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(0, 10, 0, 10);

        imageNote.setLayoutParams(layoutParams);

        //set onCLick listener
        //onClick open photo to full screen
        imageNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setDataAndType(Uri.fromFile(photofile), "image/*");
                activity.startActivity(intent);
            }
        });

//        imageNote.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                return false;
//            }
//        });
        setSwipeListener(imageNote, activity);
        return imageNote;
    }


    /**
     * asking current ViewText change TextView To EditText
     */
    public void changeToEditText(Activity activity) {
        editText.setText(textOfNote);
        vs.showNext();
        editText.requestFocus();
        showKeyboard(activity);
        //todo: rewrite it: handle situation when vs.showNext() is null, i.e. vs is already shows EditText
    }

    /**
     * ViewSwitcher changing displaying views between
     * EditText and TextView
     */
    private void createViewSwitcher(Activity activity) {
        vs = new ViewSwitcher(activity);
        vs.setId(id);
        vs.setBackgroundColor(colorOfNote);
        vs.setPadding(0, 10, 0, 1);

        //set params: width: MATCH_PARENT, height - WRAP_CONTENT
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams
                (LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(0, 10, 0, 10);
        vs.setLayoutParams(layoutParams);

        //add TextView to VS
        createTextView(activity);
        vs.addView(textView);
        //add EditText to VS
        createEditText(activity);
        vs.addView(editText);

        //set swipe listener
        setSwipeListener(vs, activity);
    }

    /**
     * creating TextView
     */
    private void createTextView(final Activity activity) {
        textView = new TextView(activity);
        textView.setText(textOfNote);
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        textView.setLayoutParams(layoutParams);

        textView.setTextSize(17);

        //onClick listener. Changing TextView to EditText when clicked
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeToEditText(activity);
            }
        });
        setSwipeListener(textView, activity);
    }

    /**
     * creating EditText
     */
    private void createEditText(Activity activity) {
        editText = new EditText(activity);
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        editText.setLayoutParams(layoutParams);
        editText.setFocusableInTouchMode(true);
        View.OnFocusChangeListener focusChangeListener = new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                //if unfocus EditText field, editText becomes textView again
                if (!hasFocus) {
                    //todo write showTextView, handling situation when TextView is already shown
                    textOfNote = editText.getText().toString();
                    textView.setText(textOfNote);
                    note.setText(textOfNote); //todo not good, need to rewrite
                    vs.showPrevious();
                }
            }
        };
        editText.setOnFocusChangeListener(focusChangeListener);
        setSwipeListener(editText, activity);
    }


    /**
     * Shows keyboard to edit and add text
     *
     * @param activity current activity
     */
    private void showKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(editText, InputMethodManager.SHOW_FORCED);
    }

    public void setSwipeListener (View view, Activity activity){
        view.setOnTouchListener(new OnSwipeTouchListener(activity) {
            @Override
            public void onSwipeRight() {
                deleteNote(note);
            }
        });
    }

    private void deleteNote (Note note){
        NotesKeeper.remove(note);
        Note.drawNotes(activity, NotesKeeper.getInstance().getAllNotes());
    }
}
