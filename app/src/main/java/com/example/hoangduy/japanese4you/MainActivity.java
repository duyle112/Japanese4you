package com.example.hoangduy.japanese4you;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;

import com.astuetz.PagerSlidingTabStrip;
import com.example.hoangduy.japanese4you.adapters.ContentAdapter;
import com.example.hoangduy.japanese4you.fragments.FavFragment;
import com.example.hoangduy.japanese4you.fragments.ListVocabularyFragment;
import com.example.hoangduy.japanese4you.fragments.ListVocabularyFragment_;
import com.example.hoangduy.japanese4you.fragments.QuestionFragment;
import com.example.hoangduy.japanese4you.fragments.SettingsFragment;
import com.example.hoangduy.japanese4you.models.Section;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

@EActivity(R.layout.activity_main)
public class MainActivity extends AppCompatActivity {

    private List<Section> mSections;

    @ViewById(R.id.toolbar)
    Toolbar mToolbar;


    @ViewById(R.id.viewpager)
    ViewPager mViewpager;

    @ViewById(R.id.tabs)
    PagerSlidingTabStrip mTabs;

    @AfterViews
    public void init() {
        final FragmentManager fragmentManager = getSupportFragmentManager();
        mSections = new ArrayList<>();
        mSections.add(new Section(R.drawable.voca, "Vocabulary"));
        mSections.add(new Section(R.drawable.voca, "N5 Basic test"));
        mSections.add(new Section(R.drawable.kanji, "N5 Kanji test"));
        mSections.add(new Section(R.drawable.voca, "N5 Grammar test"));
        mSections.add(new Section(R.drawable.voca, "N5 Vocabulary test"));
        mSections.add(new Section(R.drawable.reading, "N5 Reading test"));
        mSections.add(new Section(R.drawable.about, "About App"));
        mSections.add(new Section(R.drawable.contact, "Contact Us"));
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        mViewpager.setAdapter(new ContentAdapter(getSupportFragmentManager()));
        mTabs.setViewPager(mViewpager);
        mTabs.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                Log.i("position", position + "");
                switch (position) {
                    case 0:
                        ListVocabularyFragment listVocabularyFragment = ListVocabularyFragment_.builder().build();
                        fragmentManager.beginTransaction().replace(R.id.flContainer,listVocabularyFragment).commit();
                        break;
                    case 1:
                        QuestionFragment questionFragment = new QuestionFragment();
                        fragmentManager.beginTransaction().replace(R.id.flContainer,questionFragment).commit();
                        break;
                    case 2:
                        FavFragment favFragment = new FavFragment();
                        fragmentManager.beginTransaction().replace(R.id.flContainer,favFragment).commit();
                        break;
                    case 3:
                        SettingsFragment settingsFragment= new SettingsFragment();
                        fragmentManager.beginTransaction().replace(R.id.flContainer,settingsFragment).commit();
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    interface ClickListener {
        void onClick(View view, int position);
    }

    static class RecyclerTouchListener implements RecyclerView.OnItemTouchListener {

        private GestureDetector gestureDetector;
        private ClickListener clickListener;

        public RecyclerTouchListener(Context context, final RecyclerView recyclerView, final ClickListener clickListener) {
            this.clickListener = clickListener;
            gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }
            });
        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {

            View child = rv.findChildViewUnder(e.getX(), e.getY());
            if (child != null && clickListener != null && gestureDetector.onTouchEvent(e)) {
                clickListener.onClick(child, rv.getChildPosition(child));
            }
            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {
        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

        }

    }

}
