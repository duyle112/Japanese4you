package com.example.hoangduy.japanese4you.fragments;

import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hoangduy.japanese4you.MainActivity;
import com.example.hoangduy.japanese4you.R;
import com.example.hoangduy.japanese4you.models.Quiz;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;
import org.androidannotations.annotations.ViewById;


@EFragment(R.layout.fragment_question)
public class QuestionFragment extends Fragment {

    @ViewById(R.id.tvQuestion)
    TextView mTvQuestion;
    @ViewById(R.id.tvAnswerA)
    TextView mTvAnswerA;
    @ViewById(R.id.tvAnswerB)
    TextView mTvAnswerB;
    @ViewById(R.id.tvAnswerC)
    TextView mTvAnswerC;
    @ViewById(R.id.tvAnswerD)
    TextView mTvAnswerD;
    @FragmentArg("quiz")
    Quiz mQuiz;
    @FragmentArg("pos")
    int mPos;

    @Click(R.id.tvAnswerA)
    void choiceAnswerA() {
        mTvAnswerA.setBackgroundResource(android.R.color.holo_green_light);
        mListener.onFragmentInteraction(mPos,0);
    }

    @Click(R.id.tvAnswerB)
    void choiceAnswerB() {
        mTvAnswerB.setBackgroundResource(android.R.color.holo_green_light);
        mListener.onFragmentInteraction(mPos,1);
    }

    @Click(R.id.tvAnswerC)
    void choiceAnswerC() {
        mTvAnswerC.setBackgroundResource(android.R.color.holo_green_light);
        mListener.onFragmentInteraction(mPos,2);
    }

    @Click(R.id.tvAnswerD)
    void choiceAnswerD() {
        mTvAnswerD.setBackgroundResource(android.R.color.holo_green_light);
        mListener.onFragmentInteraction(mPos,3);
    }

    @AfterViews
    void init() {
        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        ImageView img = (ImageView) toolbar.findViewById(R.id.imgBack);
        TextView textview = (TextView) toolbar.findViewById(R.id.tvTitle);
        // img.setVisibility(View.VISIBLE);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
                Log.i("question","question");
            }
        });
        mListener = (MainActivity) getContext();
        mTvQuestion.setText(mQuiz.getQuestion());
        mTvAnswerA.setText(mQuiz.getAnswerA());
        mTvAnswerB.setText(mQuiz.getAnswerB());
        mTvAnswerC.setText(mQuiz.getAnswerC());
        mTvAnswerD.setText(mQuiz.getAnswerD());
        switch (mQuiz.getChoosenQuestion()) {
            case 0:
                mTvAnswerA.setBackgroundResource(android.R.color.holo_green_light);
                break;
            case 1:
                mTvAnswerB.setBackgroundResource(android.R.color.holo_green_light);
                break;
            case 2:
                mTvAnswerC.setBackgroundResource(android.R.color.holo_green_light);
                break;
            case 3:
                mTvAnswerD.setBackgroundResource(android.R.color.holo_green_light);
                break;
        }
    }

    private OnFragmentInteractionListener mListener;

    public QuestionFragment() {
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
        void onFragmentInteraction(int position, int choice);
    }
}
