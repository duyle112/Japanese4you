package com.example.hoangduy.japanese4you.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.hoangduy.japanese4you.fragments.QuestionFragment;
import com.example.hoangduy.japanese4you.fragments.QuestionFragment_;
import com.example.hoangduy.japanese4you.models.Quiz;

import java.util.List;

/**
 * Created by HoangDuy on 04/01/2017.
 */
public class QuizAdapter extends FragmentStatePagerAdapter {
    private static final int COUNT = 10;
    private List<Quiz> mQuizes;
    private boolean mIsSubmit;
    private String[] mTitles = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
    private QuestionFragment.OnFragmentInteractionListener mCallBack;

    public QuizAdapter(FragmentManager fm, List<Quiz> quizes, boolean isSubmit, QuestionFragment.OnFragmentInteractionListener callBack) {
        super(fm);
        mQuizes = quizes;
        mIsSubmit = isSubmit;
        mCallBack = callBack;
    }

    @Override
    public Fragment getItem(int position) {
        QuestionFragment questionFragment = QuestionFragment_.builder().mQuiz(mQuizes.get(position)).mPos(position).mFlag(mIsSubmit).build();
        questionFragment.setCallback(mCallBack);
        return questionFragment;
    }

    @Override
    public int getCount() {
        return COUNT;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles[position];
    }
}
