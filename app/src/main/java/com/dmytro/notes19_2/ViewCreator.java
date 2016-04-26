package com.dmytro.notes19_2;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ThumbnailUtils;
import android.net.Uri;
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
    //layout holds notes
    private int layoutId = R.id.layoutNotesKeeper;
    //pointer to MainActivity
    private Activity activity;

    //flags for setting onTouch listeners
    private final String FLAG_TEXTVIEW = "text";
    private final String FLAG_IMAGEVIEW = "image";
    private final String FLAG_VIEWSWITCHER = "viewSwitcher";
    private final String FLAG_EDITTEXT = "editText";

    //pointer to Note ViewCreator working with
    private Note note;
//    private String textOfNote;
    private int colorOfNote;
    private int id;

    //Note's text keepers
    private ViewSwitcher vs;
    private EditText editText;
    private TextView textView;

    //Note's photo keeper
    ImageView imageNote;
    protected Uri IMAGE_URI;


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

            createViewSwitcher(activity);
            layout.addView(vs, 0);
        } else {
            IMAGE_URI = Uri.fromFile(note.photoFile);
            imageNote = createImageNote(activity, note.photoFile);
            layout.addView(imageNote, 0);
        }
    }

    /**
     * creating ImageNote
     * and adds it to layout
     *
     * @param activity  current
     * @param photofile image of note
     */
    private ImageView createImageNote(final Activity activity, final File photofile) {
        ImageView imageNote = new ImageView(activity);

        //todo: do something with width and height
        int width = 350;
        int height = 280;

        //get preview from URI
        Bitmap bitmap = ThumbnailUtils.extractThumbnail(
                BitmapFactory.decodeFile(photofile.getPath()), width, height);

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
//        imageNote.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(Intent.ACTION_VIEW);
//                intent.setDataAndType(Uri.fromFile(photofile), "image/*");
//                activity.startActivity(intent);
//            }
//        });

        setTouchListener(imageNote, activity, FLAG_IMAGEVIEW);
        return imageNote;
    }


    /**
     * asking current ViewText change TextView To EditText
     */
    public void changeToEditText(Activity activity) {
        editText.setText(note.getText());
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
        setTouchListener(vs, activity, FLAG_VIEWSWITCHER);
    }

    /**
     * creating TextView
     */
    private void createTextView(final Activity activity) {
        textView = new TextView(activity);
        textView.setText(note.getText());
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        textView.setLayoutParams(layoutParams);

        textView.setTextSize(17);

        setTouchListener(textView, activity, FLAG_TEXTVIEW);
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
                    note.setText(editText.getText().toString());
                    textView.setText(note.getText());
                    vs.showPrevious();
                }
            }
        };
        editText.setOnFocusChangeListener(focusChangeListener);
        setTouchListener(editText, activity, FLAG_EDITTEXT);
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


    /**
     * sets onTouchListener to view depending on view type
     * imageView: onClick show full image
     * textView: onClick shows editText view
     * all views: deleting onSwipeRight
     *
     * @param view we are working with
     * @param activity we are working with
     * @param flag of View type
     */
    public void setTouchListener(View view, final Activity activity, String flag) {
        //on swipe deletes note
        //on click show full image
        if (flag.equals(FLAG_IMAGEVIEW)) {
            view.setOnTouchListener(new OnTouchListener(activity) {
                @Override
                public void onSwipeRight() {
                    deleteNote(note);
                }

                @Override
                public void onDownEvent() {
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setDataAndType(IMAGE_URI, "image/*");
                    activity.startActivity(intent);
                }
            });
        }

        if (flag.equals(FLAG_EDITTEXT) || flag.equals(FLAG_VIEWSWITCHER)) {
            view.setOnTouchListener(new OnTouchListener(activity) {
                @Override
                public void onSwipeRight() {
                    deleteNote(note);
                }
            });
        }

        if (flag.equals(FLAG_TEXTVIEW)) {
            view.setOnTouchListener(new OnTouchListener(activity) {
                @Override
                public void onSwipeRight() {
                    deleteNote(note);
                }

                @Override
                public void onDownEvent() {
                    changeToEditText(activity);
                }
            });
        }
    }

    /**
     * deletes specified note
     *
     * @param note has to be delete
     */
    private void deleteNote(Note note) {
        NotesKeeper.remove(note);
        Note.drawNotes(activity, NotesKeeper.getInstance().getAllNotes());
    }
}
