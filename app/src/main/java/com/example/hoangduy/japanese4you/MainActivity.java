package com.example.hoangduy.japanese4you;

import android.app.Dialog;
import android.content.SharedPreferences;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.example.hoangduy.japanese4you.adapters.ExerciseAdapter;
import com.example.hoangduy.japanese4you.database.DataHelper;
import com.example.hoangduy.japanese4you.fragments.FavFragment;
import com.example.hoangduy.japanese4you.fragments.FavFragment_;
import com.example.hoangduy.japanese4you.fragments.ListVocabularyFragment;
import com.example.hoangduy.japanese4you.fragments.ListVocabularyFragment_;
import com.example.hoangduy.japanese4you.fragments.QuizFragment;
import com.example.hoangduy.japanese4you.fragments.QuizFragment_;
import com.example.hoangduy.japanese4you.fragments.RankingFragment;
import com.example.hoangduy.japanese4you.fragments.RankingFragment_;
import com.example.hoangduy.japanese4you.fragments.SettingsFragment;
import com.example.hoangduy.japanese4you.fragments.SettingsFragment_;
import com.example.hoangduy.japanese4you.fragments.TestFragment;
import com.example.hoangduy.japanese4you.fragments.TestFragment_;
import com.example.hoangduy.japanese4you.models.Exercise;
import com.example.hoangduy.japanese4you.models.Quiz;
import com.example.hoangduy.japanese4you.models.Word;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

@EActivity(R.layout.activity_main)
@OptionsMenu(R.menu.menu_main)
public class MainActivity extends AppCompatActivity implements ExerciseAdapter.onFragmentTransaction,RankingFragment.OnFragmentInteractionListener{

    ArrayList<Quiz> mQuizes;
    @ViewById(R.id.toolbar)
    Toolbar mToolbar;


    @ViewById(R.id.viewpager)
    ViewPager mViewpager;

    @ViewById(R.id.tabLayout)
    TabLayout mTabLayout;

    private FragmentManager mFragmentManager;
    private SharedPreferences mSharePreference;
    private boolean isCancel;
    private DataHelper mDataHelper;

    @AfterViews
    public void init() {
        mDataHelper = new DataHelper(this, "", null, 1);
        mDataHelper.openDataBase();
        isCancel = false;
        setFlag();
        mFragmentManager = getSupportFragmentManager();
        ListVocabularyFragment listVocabularyFragment = ListVocabularyFragment_.builder().build();
        mFragmentManager.beginTransaction().replace(R.id.flContainer, listVocabularyFragment).commit();
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        TextView tvTitle = (TextView) mToolbar.findViewById(R.id.tvTitle);
        tvTitle.setText("Japanese4you");
        onCreateTabLayout();
        mTabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                setTabView(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    public void setTabView(int position) {
        switch (position) {
            case 0:
                ListVocabularyFragment listVocabularyFragment = ListVocabularyFragment_.builder().build();
                loadFragment(listVocabularyFragment, checkFlag());
                break;
            case 1:
                TestFragment testFragment = TestFragment_.builder().build();
                loadFragment(testFragment, checkFlag());
                break;
            case 2:
                List<Word> words = mDataHelper.getWordFavorited();
                FavFragment favFragment = FavFragment_.builder().mWords((ArrayList<Word>) words).build();
                loadFragment(favFragment, checkFlag());
                break;
            case 3:
                List<Exercise> exercises = mDataHelper.getTakenExercise();
                RankingFragment rankingFragment = RankingFragment_.builder().mExercises((ArrayList<Exercise>) exercises).build();
                loadFragment(rankingFragment, checkFlag());
                break;
            case 4:
                SettingsFragment settingsFragment = SettingsFragment_.builder().build();
                loadFragment(settingsFragment, checkFlag());
                break;
        }
    }

    public boolean checkFlag() {
        mSharePreference = getSharedPreferences(QuizFragment.KEY_PREFERENCE, MODE_PRIVATE);
        return mSharePreference.getBoolean(QuizFragment.KEY_FLAG, false);
    }

    public void loadFragment(Fragment fragment, boolean isCheck) {
        if (isCheck) {
            if (!isCancel) {
                showMessage(mTabLayout.getSelectedTabPosition());
            } else {
                isCancel = false;
            }
            // mTabLayout.getTabAt(1).select();
        } else {
            mFragmentManager.beginTransaction().replace(R.id.flContainer, fragment).commit();
        }
    }

    public void showMessage(final int tab) {
        final Dialog dialog = new Dialog(MainActivity.this);
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_exit);
        dialog.setCanceledOnTouchOutside(false);
        Button btnPositive = (Button) dialog.findViewById(R.id.btnPositive);
        Button btnNegative = (Button) dialog.findViewById(R.id.btnNegative);
        btnPositive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFlag();
                dialog.dismiss();
                setTabView(tab);
            }
        });

        btnNegative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
                isCancel = true;
                mTabLayout.getTabAt(1).select();
            }
        });
        if (!dialog.isShowing()) {
            dialog.show();
        }
    }

    public void setFlag() {
        mSharePreference = getSharedPreferences(QuizFragment.KEY_PREFERENCE, MODE_PRIVATE);
        SharedPreferences.Editor editor = mSharePreference.edit();
        editor.putBoolean(QuizFragment.KEY_FLAG, false);
        editor.commit();
    }

    private void onCreateTabLayout() {
        mTabLayout.addTab(mTabLayout.newTab().setText("Voc").setIcon(R.drawable.ic_book_green_a700_36dp));
        mTabLayout.addTab(mTabLayout.newTab().setText("Test").setIcon(R.drawable.ic_assignment_green_a700_36dp));
        mTabLayout.addTab(mTabLayout.newTab().setText("Fav").setIcon(R.drawable.ic_favorite_border_green_a700_36dp));
        mTabLayout.addTab(mTabLayout.newTab().setText("Ranking").setIcon(R.drawable.progress100));
        mTabLayout.addTab(mTabLayout.newTab().setText("Settings").setIcon(R.drawable.ic_settings_applications_green_a700_36dp));
    }

    @Override
    public void onReadmore(int id,String category, int group) {
        mQuizes = (ArrayList<Quiz>) mDataHelper.getQuizes(category, group);
        if (mQuizes.size() != 0) {
            QuizFragment quizFragment = QuizFragment_.builder().mQuizes(mQuizes).mPos(0).mId(id).build();
            mFragmentManager.beginTransaction().addToBackStack(null).replace(R.id.flContainer, quizFragment).commit();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        getFragmentManager().popBackStack();
        SharedPreferences.Editor editor = mSharePreference.edit();
        editor.putBoolean(QuizFragment.KEY_FLAG, false);
        editor.commit();
    }

    @Override
    public void onFragmentInteraction(int id, String category, int group) {
        mQuizes = (ArrayList<Quiz>) mDataHelper.getQuizes(category, group);
        if (mQuizes.size() != 0) {
            QuizFragment quizFragment = QuizFragment_.builder().mQuizes(mQuizes).mPos(0).mId(id).build();
            mFragmentManager.beginTransaction().addToBackStack(null).replace(R.id.flContainer, quizFragment).commit();
        }
    }
}
