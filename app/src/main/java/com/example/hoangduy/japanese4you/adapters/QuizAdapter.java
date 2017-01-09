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

    public QuizAdapter(FragmentManager fm, List<Quiz> quizes) {
        super(fm);
        mQuizes = quizes;
    }

    @Override
    public Fragment getItem(int position) {
        QuestionFragment questionFragment = QuestionFragment_.builder().mQuiz(mQuizes.get(position)).mPos(position).build();
        return questionFragment;
    }

    @Override
    public int getCount() {
        return COUNT;
    }

}
