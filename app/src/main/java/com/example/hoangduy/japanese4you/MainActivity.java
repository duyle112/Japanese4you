package com.example.hoangduy.japanese4you;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.example.hoangduy.japanese4you.adapters.ExerciseAdapter;
import com.example.hoangduy.japanese4you.fragments.FavFragment;
import com.example.hoangduy.japanese4you.fragments.ListVocabularyFragment;
import com.example.hoangduy.japanese4you.fragments.ListVocabularyFragment_;
import com.example.hoangduy.japanese4you.fragments.QuestionFragment;
import com.example.hoangduy.japanese4you.fragments.QuizFragment;
import com.example.hoangduy.japanese4you.fragments.QuizFragment_;
import com.example.hoangduy.japanese4you.fragments.SettingsFragment;
import com.example.hoangduy.japanese4you.fragments.TestFragment;
import com.example.hoangduy.japanese4you.fragments.TestFragment_;
import com.example.hoangduy.japanese4you.models.Quiz;
import com.example.hoangduy.japanese4you.models.Section;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

@EActivity(R.layout.activity_main)
@OptionsMenu(R.menu.menu_main)
public class MainActivity extends AppCompatActivity implements ExerciseAdapter.onFragmentTransaction, QuestionFragment.OnFragmentInteractionListener {

    private List<Section> mSections;
    ArrayList<Quiz> quizes;
    @ViewById(R.id.toolbar)
    Toolbar mToolbar;


    @ViewById(R.id.viewpager)
    ViewPager mViewpager;

    @ViewById(R.id.tabLayout)
    TabLayout mTabLayout;

    private FragmentManager mFragmentManager;

    @AfterViews
    public void init() {
        mFragmentManager = getSupportFragmentManager();
        ListVocabularyFragment listVocabularyFragment = ListVocabularyFragment_.builder().build();
        mFragmentManager.beginTransaction().replace(R.id.flContainer, listVocabularyFragment).commit();
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
        TextView tvTitle = (TextView) mToolbar.findViewById(R.id.tvTitle);
        tvTitle.setText("Japanese4you");
        onCreateTabLayout();
        mTabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 0:
                        ListVocabularyFragment listVocabularyFragment = ListVocabularyFragment_.builder().build();
                        loadFragment(listVocabularyFragment);
                        break;
                    case 1:
                        TestFragment testFragment = TestFragment_.builder().build();
                        loadFragment(testFragment);
                        break;
                    case 2:
                        FavFragment favFragment = new FavFragment();
                        loadFragment(favFragment);
                        break;
                    case 3:
                        SettingsFragment settingsFragment = new SettingsFragment();
                        loadFragment(settingsFragment);
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    public void loadFragment(Fragment fragment) {
        mFragmentManager.beginTransaction().replace(R.id.flContainer, fragment).commit();
    }

    private void onCreateTabLayout() {
        mTabLayout.addTab(mTabLayout.newTab().setText("Voc").setIcon(R.drawable.ic_book_green_a700_36dp));
        mTabLayout.addTab(mTabLayout.newTab().setText("Test").setIcon(R.drawable.ic_assignment_green_a700_36dp));
        mTabLayout.addTab(mTabLayout.newTab().setText("Fav").setIcon(R.drawable.ic_favorite_border_green_a700_36dp));
        mTabLayout.addTab(mTabLayout.newTab().setText("Settings").setIcon(R.drawable.ic_settings_applications_green_a700_36dp));
    }

    @Override
    public void onReadmore(int id) {
        quizes = new ArrayList<>();
        String[] questions = getResources().getStringArray(R.array.questions);
        String[] answerA = getResources().getStringArray(R.array.answerA);
        String[] answerB = getResources().getStringArray(R.array.answerB);
        String[] answerC = getResources().getStringArray(R.array.answerC);
        String[] answerD = getResources().getStringArray(R.array.answerD);
        for (int i = 0; i < 10; i++) {
            quizes.add(new Quiz(questions[i], answerA[i], answerB[i], answerC[i], answerD[i], 5));
        }
        QuizFragment quizFragment = QuizFragment_.builder().mQuizes(quizes).mPos(0).build();
        mFragmentManager.beginTransaction().replace(R.id.flContainer, quizFragment).addToBackStack(null).commit();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        getFragmentManager().popBackStack();
    }

    @Override
    public void onFragmentInteraction(int position, int choice) {
        quizes.get(position).setChoosenQuestion(choice);
        QuizFragment quizFragment = QuizFragment_.builder().mQuizes(quizes).mPos(position).build();
        mFragmentManager.beginTransaction().replace(R.id.flContainer, quizFragment).commit();
    }
}
