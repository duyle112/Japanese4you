package com.example.hoangduy.japanese4you.fragments;

import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.util.Log;

import com.astuetz.PagerSlidingTabStrip;
import com.example.hoangduy.japanese4you.R;
import com.example.hoangduy.japanese4you.adapters.JLPTLevelAdapter;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;


@EFragment(R.layout.fragment_list_vocabulary)
public class ListVocabularyFragment extends Fragment {

    private OnFragmentInteractionListener mListener;

    public ListVocabularyFragment() {
        // Required empty public constructor
    }

    @ViewById(R.id.viewpager)
    ViewPager mViewPager;

    @ViewById(R.id.tabs)
    PagerSlidingTabStrip mTabs;

    @AfterViews
    public void init() {
        mViewPager.setAdapter(new JLPTLevelAdapter(getFragmentManager()));
        mTabs.setViewPager(mViewPager);
        ListLessonFragment listLessonFragment = ListLessonFragment_.builder().mPos(0).build();
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.flContainerSub, listLessonFragment).commit();
        mTabs.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                Log.i("aaa", position + "");
                ListLessonFragment listLessonFragment = ListLessonFragment_.builder().mPos(position).build();
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.flContainerSub, listLessonFragment).commit();

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
//    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
