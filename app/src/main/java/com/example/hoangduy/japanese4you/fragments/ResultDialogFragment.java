package com.example.hoangduy.japanese4you.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.hoangduy.japanese4you.R;

import java.text.DecimalFormat;

/**
 * Created by HoangDuy on 10/01/2017.
 */
public class ResultDialogFragment extends DialogFragment {
    public static final String NAME_POSITIVE_BTN = "Review Test";
    public static final String NAME_NEGATIVE_BTN = "Exit";
    private double mAccurateQuestionCount;
    private static final String KEY_ARG = "accurate";
    private static final String KEY_COUNT = "count";
    private int mCount;
    public DialogListener mListener;

    public static ResultDialogFragment newInstance(double accurateQuestionCount, int count) {
        ResultDialogFragment resultDialogFragment = new ResultDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putDouble(KEY_ARG, accurateQuestionCount);
        bundle.putInt(KEY_COUNT, count);
        resultDialogFragment.setArguments(bundle);
        return resultDialogFragment;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        mAccurateQuestionCount = getArguments().getDouble(KEY_ARG);
        mCount = getArguments().getInt(KEY_COUNT);
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
        LayoutInflater layoutInflater = getActivity().getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.dialog_result, null);
        ProgressBar progressBar = (ProgressBar) view.findViewById(R.id.progressBar);
        progressBar.setProgress((int)mAccurateQuestionCount);
        TextView tvPercent = (TextView) view.findViewById(R.id.tvPercent);
        TextView tvCount = (TextView) view.findViewById(R.id.tvRightQuestionCount);
        DecimalFormat df = new DecimalFormat("###.#");
        tvPercent.setText(df.format(mAccurateQuestionCount) + "%");
        tvCount.setText(mCount + " of " + df.format(mAccurateQuestionCount / mCount) + " answers");
        alertDialog.setView(view)
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

    public void setCallback(DialogListener listener) {
        mListener = listener;
    }

//    @Override
//    public void onAttach(Activity activity) {
//        super.onAttach(activity);
//        // Verify that the host activity implements the callback interface
//        try {
//            // Instantiate the NoticeDialogListener so we can send events to the host
//            mListener = (DialogListener) activity;
//        } catch (ClassCastException e) {
//            // The activity doesn't implement the interface, throw exception
//            throw new ClassCastException(activity.toString()
//                    + " must implement NoticeDialogListener");
//        }
//    }

    public interface DialogListener {
        void onAddDialogPositiveClick(DialogFragment dialog);

        void onAddDialogNegativeClick(DialogFragment dialog);
    }
}
