package com.example.hoangduy.japanese4you;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.hoangduy.japanese4you.adapters.TestTutorial;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import me.relex.circleindicator.CircleIndicator;

@EActivity(R.layout.activity_tutorials)
public class Tutorials extends AppCompatActivity {

    @ViewById(R.id.viewpager)
    ViewPager mViewpager;
    @ViewById(R.id.indicator)
    CircleIndicator mIndicator;
    @Click(R.id.tvSkip)
    public void skip() {
        Intent intent = new Intent(Tutorials.this,MainActivity_.class);
        startActivity(intent);
        finish();
    }

    @AfterViews
    public void init() {
        mViewpager.setAdapter(new TestTutorial(getSupportFragmentManager()));
        mIndicator.setViewPager(mViewpager);
    }
}
