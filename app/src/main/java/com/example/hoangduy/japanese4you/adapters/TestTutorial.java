package com.example.hoangduy.japanese4you.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.hoangduy.japanese4you.fragments.TypeTest_;

/**
 * Created by HoangDuy on 05/01/2017.
 */
public class TestTutorial extends FragmentPagerAdapter{

    public static final int COUNT=4;
    public TestTutorial(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return new TypeTest_().builder().mPos(position).build();
    }

    @Override
    public int getCount() {
        return COUNT;
    }
}
