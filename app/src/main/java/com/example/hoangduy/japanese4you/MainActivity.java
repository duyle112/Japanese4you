package com.example.hoangduy.japanese4you;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.SharedPreferences;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.example.hoangduy.japanese4you.adapters.ExerciseAdapter;
import com.example.hoangduy.japanese4you.fragments.FavFragment;
import com.example.hoangduy.japanese4you.fragments.ListVocabularyFragment;
import com.example.hoangduy.japanese4you.fragments.ListVocabularyFragment_;
import com.example.hoangduy.japanese4you.fragments.QuestionFragment;
import com.example.hoangduy.japanese4you.fragments.QuizFragment;
import com.example.hoangduy.japanese4you.fragments.QuizFragment_;
import com.example.hoangduy.japanese4you.fragments.ResultDialogFragment;
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
public class MainActivity extends AppCompatActivity implements ExerciseAdapter.onFragmentTransaction
        , QuestionFragment.OnFragmentInteractionListener, ResultDialogFragment.DialogListener {

    private List<Section> mSections;
    ArrayList<Quiz> quizes;
    @ViewById(R.id.toolbar)
    Toolbar mToolbar;


    @ViewById(R.id.viewpager)
    ViewPager mViewpager;

    @ViewById(R.id.tabLayout)
    TabLayout mTabLayout;

    private FragmentManager mFragmentManager;
    private SharedPreferences mSharePreference;
    private boolean isCancel;

    @AfterViews
    public void init() {
        isCancel = false;
        setFlag();
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
                FavFragment favFragment = new FavFragment();
                loadFragment(favFragment, checkFlag());
                break;
            case 3:
                SettingsFragment settingsFragment = new SettingsFragment();
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
            quizes.add(new Quiz(questions[i], answerA[i], answerB[i], answerC[i], answerD[i], 5, i % 4));
        }
        QuizFragment quizFragment = QuizFragment_.builder().mQuizes(quizes).mPos(0).build();
        mFragmentManager.beginTransaction().replace(R.id.flContainer, quizFragment).addToBackStack(null).commit();
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
    public void onFragmentInteraction(int position, int choice) {
        quizes.get(position).setChoosenQuestion(choice);
        QuizFragment quizFragment = QuizFragment_.builder().mQuizes(quizes).mPos(position).build();
        mFragmentManager.beginTransaction().replace(R.id.flContainer, quizFragment).commit();
        Log.i("fragment", mFragmentManager.getBackStackEntryCount() + "");
    }

    @Override
    public void onAddDialogPositiveClick(DialogFragment dialog) {
        QuizFragment quizFragment = QuizFragment_.builder().mQuizes(quizes).mPos(0).mIsSubmit(true).build();
        mFragmentManager.beginTransaction().replace(R.id.flContainer, quizFragment).commit();
    }

    @Override
    public void onAddDialogNegativeClick(DialogFragment dialog) {
        setFlag();
        getFragmentManager().popBackStack();
    }
}
