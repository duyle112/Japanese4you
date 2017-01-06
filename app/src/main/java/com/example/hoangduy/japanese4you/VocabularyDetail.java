package com.example.hoangduy.japanese4you;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.hoangduy.japanese4you.adapters.VocabularyAdapter;
import com.example.hoangduy.japanese4you.models.Sentences;
import com.example.hoangduy.japanese4you.models.Word;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;

import github.chenupt.springindicator.SpringIndicator;

@EActivity(R.layout.activity_vocabulary_detail)
public class VocabularyDetail extends AppCompatActivity {

    @ViewById(R.id.viewpager)
    ViewPager mViewPager;
    @ViewById(R.id.indicator)
    SpringIndicator mSpringIndicator;

    @AfterViews
    public void init() {
        ArrayList<Word> words = new ArrayList<>();
        words.add(new Word("上","ue","above, up, over"));
        words.add(new Word("上り","nobori","ascent, climbing"));
        words.add(new Word("上る","noboru","to ascend, to climb"));
        words.add(new Word("上司 ","joushi","boss, superior authorities"));
        words.add(new Word("上着","uwagi","coat, jacket"));
        words.add(new Word("以上","ijou","superiority, first-class"));
        words.add(new Word("上手","jouzu","skill, skillful"));
        words.add(new Word("向上","koujou","elevation, rise"));
        words.add(new Word("上位","joui","superior (in rank)"));
        words.add(new Word("上院","jouin","Senate, Upper House"));
        words.add(new Word("上","ue","above, up, over"));
        words.add(new Word("上り","nobori","ascent, climbing"));
        words.add(new Word("上る","noboru","to ascend, to climb"));
        words.add(new Word("上司 ","joushi","boss, superior authorities"));
        words.add(new Word("上着","uwagi","coat, jacket"));
        words.add(new Word("以上","ijou","superiority, first-class"));
        words.add(new Word("上手","jouzu","skill, skillful"));
        words.add(new Word("向上","koujou","elevation, rise"));
        words.add(new Word("上位","joui","superior (in rank)"));
        words.add(new Word("上院","jouin","Senate, Upper House"));
        ArrayList<Sentences> sentences = new ArrayList<>();
        sentences.add(new Sentences("山本は席から飛び上がった","yamamoto wa seki kara tobiagatta.","Yamamoto sprang up from his seat."));
        sentences.add(new Sentences("宿題は明日の夜仕上げよう。","shukudai wa ashita no yoru shiageyou","I’ll finish the homework tomorrow night."));
        sentences.add(new Sentences("山本は席から飛び上がった","yamamoto wa seki kara tobiagatta.","Yamamoto sprang up from his seat."));
        sentences.add(new Sentences("宿題は明日の夜仕上げよう。","shukudai wa ashita no yoru shiageyou","I’ll finish the homework tomorrow night."));
        sentences.add(new Sentences("宿題は明日の夜仕上げよう。","shukudai wa ashita no yoru shiageyou","I’ll finish the homework tomorrow night."));
        mViewPager.setAdapter(new VocabularyAdapter(getSupportFragmentManager(),words,sentences));
        mSpringIndicator.setViewPager(mViewPager);
    }

}
