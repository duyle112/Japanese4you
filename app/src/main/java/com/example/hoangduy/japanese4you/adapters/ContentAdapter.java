package com.example.hoangduy.japanese4you.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

import com.example.hoangduy.japanese4you.fragments.ListLessonFragment;

/**
 * Created by HoangDuy on 05/01/2017.
 */
public class ContentAdapter extends FragmentStatePagerAdapter {
    private static final int COUNT = 4;
    private String tabTitles[] = new String[]{"Voca", "Test", "Fav", "Settings"};
    private FragmentManager mFragmentManager;

    public ContentAdapter(FragmentManager fm) {
        super(fm);
        mFragmentManager=fm;
    }


    @Override
    public Fragment getItem(int position) {
        Log.i("pos", position + "");
        switch (position) {
            case 0:
                ListLessonFragment listLessonFragment = ListLessonFragment.newInstance("1","1");
                return listLessonFragment;
            case 1:
                return new ListLessonFragment();
            case 2:
                return new ListLessonFragment();
            case 3:
                return new ListLessonFragment();
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
