package com.example.hoangduy.japanese4you.fragments;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;

import com.example.hoangduy.japanese4you.R;

/**
 * Created by HoangDuy on 10/01/2017.
 */
public class ResultDialogFragment  extends DialogFragment{
    public static final String NAME_POSITIVE_BTN = "Review Test";
    public static final String NAME_NEGATIVE_BTN = "Exit";
    public DialogListener mListener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
        LayoutInflater layoutInflater = getActivity().getLayoutInflater();
        alertDialog.setView(layoutInflater.inflate(R.layout.dialog_result, null))
                .setNegativeButton(NAME_NEGATIVE_BTN, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mListener.onAddDialogNegativeClick(ResultDialogFragment.this);
                        getActivity().onBackPressed();
                    }
                })
                .setPositiveButton(NAME_POSITIVE_BTN, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mListener.onAddDialogPositiveClick(ResultDialogFragment.this);
                    }
                });
        return alertDialog.create();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            mListener = (DialogListener) activity;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(activity.toString()
                    + " must implement NoticeDialogListener");
        }
    }

    public interface DialogListener {
        void onAddDialogPositiveClick(DialogFragment dialog);

        void onAddDialogNegativeClick(DialogFragment dialog);
    }
}
