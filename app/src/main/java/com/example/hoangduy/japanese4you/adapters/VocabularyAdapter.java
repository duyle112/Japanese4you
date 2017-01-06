package com.example.hoangduy.japanese4you.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.hoangduy.japanese4you.fragments.VocabularyFragment;
import com.example.hoangduy.japanese4you.fragments.VocabularyFragment_;
import com.example.hoangduy.japanese4you.models.Sentences;

import java.util.ArrayList;

/**
 * Created by HoangDuy on 04/01/2017.
 */
public class VocabularyAdapter extends FragmentStatePagerAdapter {
    private static final int COUNT = 3;
    private ArrayList<Sentences> mSentences;

    public VocabularyAdapter(FragmentManager fm, ArrayList<Sentences> sentences) {
        super(fm);
        mSentences = sentences;
    }

    @Override
    public Fragment getItem(int position) {
        VocabularyFragment vocabularyFragment = new VocabularyFragment_().builder().mSentences(mSentences).build();
        return vocabularyFragment;
    }

    @Override
    public int getCount() {
        return COUNT;
    }
}
