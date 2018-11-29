package com.example.hoangduy.japanese4you.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.hoangduy.japanese4you.database.DataHelper;
import com.example.hoangduy.japanese4you.fragments.VocabularyFragment;
import com.example.hoangduy.japanese4you.fragments.VocabularyFragment_;
import com.example.hoangduy.japanese4you.models.Example;
import com.example.hoangduy.japanese4you.models.Word;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HoangDuy on 04/01/2017.
 */
public class VocabularyAdapter extends FragmentStatePagerAdapter {
    private int mCount;
    private List<Example> mSentences;
    private ArrayList<Word> mWords;
    private DataHelper mDataHelper;

    public VocabularyAdapter(Context context, FragmentManager fm, ArrayList<Word> words, int count) {
        super(fm);
        mWords = words;
        mCount = count;
        mDataHelper = new DataHelper(context, "", null, 1);
    }

    @Override
    public Fragment getItem(int position) {
        mSentences = mDataHelper.getExamples(mWords.get(position).getIdword());
        VocabularyFragment vocabularyFragment = new VocabularyFragment_().builder()
                .mSentences((ArrayList<Example>) mSentences)
                .mWord(mWords.get(position))
                .build();
        return vocabularyFragment;
    }

    @Override
    public int getCount() {
        return mCount;
    }
}
