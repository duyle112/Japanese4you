package com.example.hoangduy.japanese4you.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

import com.example.hoangduy.japanese4you.fragments.ListLessonFragment_;

/**
 * Created by HoangDuy on 05/01/2017.
 */
public class JLPTLevelAdapter extends FragmentStatePagerAdapter {

    private static final int COUNT = 5;
    private String tabTitles[] = new String[]{"N5", "N4", "N3", "N2", "N1"};

    public JLPTLevelAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Log.i("POS", position + "");
        switch (position) {
            case 0:
                return  ListLessonFragment_.builder().build();
            case 1:
                return ListLessonFragment_.builder().build();
            case 2:
                return ListLessonFragment_.builder().build();
            case 3:
                return ListLessonFragment_.builder().build();
            case 4:
                return ListLessonFragment_.builder().build();
        }
        return  ListLessonFragment_.builder().build();
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
