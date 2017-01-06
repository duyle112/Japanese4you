package com.example.hoangduy.japanese4you.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.hoangduy.japanese4you.fragments.ExampleFragment;
import com.example.hoangduy.japanese4you.fragments.ExampleFragment_;
import com.example.hoangduy.japanese4you.fragments.VocabularyFragment;
import com.example.hoangduy.japanese4you.fragments.WordsFragment;
import com.example.hoangduy.japanese4you.models.Sentences;
import com.example.hoangduy.japanese4you.models.Word;

import java.util.ArrayList;

/**
 * Created by HoangDuy on 04/01/2017.
 */
public class VocabularyAdapter extends FragmentPagerAdapter {
    private static final int COUNT = 3;
    private String tabTitles[] = new String[]{"1", "2", "3"};
    private ArrayList<Word> mWords;
    private ArrayList<Sentences> mSentences;

    public VocabularyAdapter(FragmentManager fm, ArrayList<Word> words, ArrayList<Sentences> sentences) {
        super(fm);
        mWords = words;
        mSentences = sentences;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                VocabularyFragment vocabularyFragment = new VocabularyFragment();
                return vocabularyFragment;
            case 1:
                WordsFragment wordsFragment = WordsFragment.newInstance(mWords);
                return wordsFragment;
            case 2:
                ExampleFragment exampleFragment = new ExampleFragment_().builder().mSentences(mSentences).build();
                return exampleFragment;
        }
        return null;
    }

    @Override
    public int getCount() {
        return COUNT;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        return tabTitles[position];
    }
}
