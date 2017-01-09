package com.example.hoangduy.japanese4you.fragments;

import android.net.Uri;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hoangduy.japanese4you.R;
import com.example.hoangduy.japanese4you.adapters.QuizAdapter;
import com.example.hoangduy.japanese4you.models.Quiz;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;


@EFragment(R.layout.fragment_quiz)
public class QuizFragment extends Fragment {

    @ViewById(R.id.viewpager)
    ViewPager mViewPager;

    @ViewById(R.id.tabLayout)
    TabLayout mTabs;

    private QuizAdapter mAdapter;
    @FragmentArg("quiz")
    ArrayList<Quiz> mQuizes;

    @FragmentArg("pos")
    int mPos;

    @AfterViews
    public void init() {
        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        ImageView img = (ImageView) toolbar.findViewById(R.id.imgBack);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });
        mAdapter = new QuizAdapter(getFragmentManager(), mQuizes);
        mViewPager.setAdapter(mAdapter);
        mViewPager.setCurrentItem(mPos);
        mTabs.setupWithViewPager(mViewPager);
        for (int i = 0; i < 10; i++) {
            if (mQuizes.get(i).getChoosenQuestion() != 5) {
                mTabs.getTabAt(i).setCustomView(update((i + 1) + ""));
            } else {
                mTabs.getTabAt(i).setCustomView(getTabView((i + 1) + ""));
            }
        }
    }

    public View update(String title) {
        TextView tvTitle = (TextView) LayoutInflater.from(getContext()).inflate(R.layout.custom_tab_layout, null);
        tvTitle.setText(title);
        tvTitle.setTextColor(getResources().getColor(R.color.colorIndicator));
        return tvTitle;
    }

    public QuizFragment() {
        // Required empty public constructor
    }

    public View getTabView(String title) {
        TextView tvTitle = (TextView) LayoutInflater.from(getContext()).inflate(R.layout.custom_tab_layout, null);
        tvTitle.setBackgroundResource(R.color.colorToolbarBg);
        tvTitle.setText(title);
        return tvTitle;
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
