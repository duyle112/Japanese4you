package com.example.hoangduy.japanese4you.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.hoangduy.japanese4you.fragments.QuestionFragment;

/**
 * Created by HoangDuy on 04/01/2017.
 */
public class QuizAdapter extends FragmentPagerAdapter {
    private static final int COUNT = 10;
    public QuizAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return new QuestionFragment();
    }

    @Override
    public int getCount() {
        return COUNT;
    }
}
