package com.example.hoangduy.japanese4you.fragments;

import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hoangduy.japanese4you.R;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;


@EFragment(R.layout.fragment_test)
public class TestFragment extends Fragment {

    private FragmentManager mFragmentManager;

    @AfterViews
    public void init() {
        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        ImageView img = (ImageView) toolbar.findViewById(R.id.imgBack);
        TextView textview = (TextView) toolbar.findViewById(R.id.tvTitle);
        textview.setText("Exercise");
        img.setVisibility(View.INVISIBLE);
        mFragmentManager = getFragmentManager();
    }

    @Click(R.id.imgBasicTest)
    void doBasicTest() {
        ExerciseFragment exerciseFragment = ExerciseFragment_.builder().build();
        mFragmentManager.beginTransaction().replace(R.id.flContainer, exerciseFragment).addToBackStack(null).commit();
    }

    @Click(R.id.imgKanjiTest)
    void doKanjiTest() {

    }

    @Click(R.id.imgGrammarTest)
    void doGrammarTest() {

    }

    @Click(R.id.imgReadingTest)
    void doReadingTest() {

    }

    @Click(R.id.imgVocaTest)
    void doVocaTest() {

    }

    private OnFragmentInteractionListener mListener;

    public TestFragment() {
        // Required empty public constructor
    }

//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
//    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
