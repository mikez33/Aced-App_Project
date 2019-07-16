package com.example.andre.aced;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

public class NotesDialog extends DialogFragment {

    //widgets
    private EditText notes;
    private TextView save;
    private TextView cancel;


    //@Nullable
    //@Override
   /* public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.note_dialog, container, false);
        //save = view.findViewById(R.id.save_action);
        cancel = view.findViewById(R.id.cancel_action);

        //Cancel Note
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().dismiss();
            }
        });*/

        //Save Note
        /*save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input = notes.getText().toString();
                //TODO: Save Note to RecylerView List Format

            }
        });*/
        //return view;
    }


