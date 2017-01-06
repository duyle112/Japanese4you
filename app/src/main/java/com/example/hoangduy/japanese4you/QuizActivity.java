package com.example.hoangduy.japanese4you;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.hoangduy.japanese4you.adapters.QuizAdapter;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import me.relex.circleindicator.CircleIndicator;

@EActivity(R.layout.activity_quiz)
public class QuizActivity extends AppCompatActivity {
    @ViewById(R.id.indicator)
    CircleIndicator mIndicator;
    @ViewById(R.id.viewpager)
    ViewPager mViewPager;

    @AfterViews
    public void init() {
        mViewPager.setAdapter(new QuizAdapter(getSupportFragmentManager()));
        mIndicator.setViewPager(mViewPager);
    }

}
