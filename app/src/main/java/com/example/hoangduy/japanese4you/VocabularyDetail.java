package com.example.hoangduy.japanese4you;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.hoangduy.japanese4you.adapters.VocabularyAdapter;
import com.example.hoangduy.japanese4you.models.Sentences;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;

@EActivity(R.layout.activity_vocabulary_detail)
public class VocabularyDetail extends AppCompatActivity {

    @ViewById(R.id.viewpager)
    ViewPager mViewPager;

    @AfterViews
    public void init() {
        ArrayList<Sentences> sentences = new ArrayList<>();
        sentences.add(new Sentences("山本は席から飛び上がった","yamamoto wa seki kara tobiagatta.","Yamamoto sprang up from his seat."));
        sentences.add(new Sentences("宿題は明日の夜仕上げよう。","shukudai wa ashita no yoru shiageyou","I’ll finish the homework tomorrow night."));
        sentences.add(new Sentences("山本は席から飛び上がった","yamamoto wa seki kara tobiagatta.","Yamamoto sprang up from his seat."));
        sentences.add(new Sentences("宿題は明日の夜仕上げよう。","shukudai wa ashita no yoru shiageyou","I’ll finish the homework tomorrow night."));
        sentences.add(new Sentences("宿題は明日の夜仕上げよう。","shukudai wa ashita no yoru shiageyou","I’ll finish the homework tomorrow night."));
        mViewPager.setAdapter(new VocabularyAdapter(getSupportFragmentManager(),sentences));
    }

}
