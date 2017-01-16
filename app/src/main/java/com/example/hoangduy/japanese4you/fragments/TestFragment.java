package com.example.hoangduy.japanese4you.fragments;

import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hoangduy.japanese4you.R;
import com.example.hoangduy.japanese4you.database.DataHelper;
import com.example.hoangduy.japanese4you.models.Exercise;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;

import java.util.ArrayList;
import java.util.List;


@EFragment(R.layout.fragment_test)
public class TestFragment extends Fragment {

    private FragmentManager mFragmentManager;
    private static final String TITLE = "Exercise";
    private DataHelper mDataHelper;

    @AfterViews
    public void init() {
        mDataHelper = new DataHelper(getContext(), "", null, 1);
        mDataHelper.openDataBase();
        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        ImageView img = (ImageView) toolbar.findViewById(R.id.imgBack);
        TextView textview = (TextView) toolbar.findViewById(R.id.tvTitle);
        textview.setText(TITLE);
        img.setVisibility(View.INVISIBLE);
        mFragmentManager = getFragmentManager();
    }

    @Click(R.id.imgBasicTest)
    void doBasicTest() {
        List<Exercise> exercises = mDataHelper.getAllExercises();
        ExerciseFragment exerciseFragment = ExerciseFragment_.builder().mExercises((ArrayList<Exercise>) exercises).build();
        mFragmentManager.beginTransaction().addToBackStack(null).replace(R.id.flContainer, exerciseFragment).commit();
    }

    @Click(R.id.imgKanjiTest)
    void doKanjiTest() {
        goExercise("kanji");
    }

    @Click(R.id.imgGrammarTest)
    void doGrammarTest() {
        goExercise("grammar");
    }

    @Click(R.id.imgReadingTest)
    void doReadingTest() {

    }

    @Click(R.id.imgVocaTest)
    void doVocaTest() {

    }

    public void goExercise(String category) {
        List<Exercise> exercises = mDataHelper.getExercises(category);
        ExerciseFragment exerciseFragment = ExerciseFragment_.builder().mExercises((ArrayList<Exercise>) exercises).build();
        mFragmentManager.beginTransaction().addToBackStack(null).replace(R.id.flContainer, exerciseFragment).commit();
    }


    private OnFragmentInteractionListener mListener;

    public TestFragment() {
        // Required empty public constructor
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
