package com.example.hoangduy.japanese4you.fragments;

import android.net.Uri;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hoangduy.japanese4you.R;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;


@EFragment(R.layout.fragment_list_vocabulary)
public class ListVocabularyFragment extends Fragment {

    private OnFragmentInteractionListener mListener;
    private static final String TITLE = "Vocabulary";

    public ListVocabularyFragment() {
        // Required empty public constructor
    }

    @ViewById(R.id.viewpager)
    ViewPager mViewPager;

    @ViewById(R.id.tabLayout)
    TabLayout mTabLayout;


    @AfterViews
    public void init() {
        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        ImageView img = (ImageView) toolbar.findViewById(R.id.imgBack);
        TextView textview = (TextView) toolbar.findViewById(R.id.tvTitle);
        textview.setText(TITLE);
        img.setVisibility(View.INVISIBLE);
        onCreateTabLayout();
        ListLessonFragment listLessonFragment = ListLessonFragment_.builder().mPos(0).build();
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.flContainerSub, listLessonFragment).commit();
        mTabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                ListLessonFragment listLessonFragment = ListLessonFragment_.builder().mPos(tab.getPosition()).build();
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.flContainerSub, listLessonFragment).commit();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    private void onCreateTabLayout() {
        mTabLayout.addTab(mTabLayout.newTab().setText("N5"));
        mTabLayout.addTab(mTabLayout.newTab().setText("N4"));
        mTabLayout.addTab(mTabLayout.newTab().setText("N3"));
        mTabLayout.addTab(mTabLayout.newTab().setText("N2"));
        mTabLayout.addTab(mTabLayout.newTab().setText("N1"));
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
