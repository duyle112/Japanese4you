package com.example.hoangduy.japanese4you.fragments;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hoangduy.japanese4you.R;
import com.example.hoangduy.japanese4you.adapters.RankingAdapter;
import com.example.hoangduy.japanese4you.decorations.GridViewDecoration;
import com.example.hoangduy.japanese4you.models.Exercise;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;

@EFragment(R.layout.fragment_ranking)
public class RankingFragment extends Fragment {

    private OnFragmentInteractionListener mListener;
    @ViewById(R.id.rankingRecyclerView)
    RecyclerView mRankingRecyclerView;
    @FragmentArg
    ArrayList<Exercise> mExercises;
    private RecyclerView.Adapter mAdapter;
    public static final String TITLE = "Ranking";
    private ImageView mImgBack;

    @AfterViews
    void init() {
        ImageClicking();
        mAdapter = new RankingAdapter(mExercises);
        mRankingRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRankingRecyclerView.setAdapter(mAdapter);
        mRankingRecyclerView.addItemDecoration(new GridViewDecoration(20));
//        mRankingRecyclerView.addOnItemTouchListener(new ListLessonFragment.RecyclerTouchListener(getContext(), mRankingRecyclerView, new ListLessonFragment.ClickListener() {
//            @Override
//            public void onClick(View view, int position) {
//                mListener.onFragmentInteraction(mExercises.get(position).getId()
//                        , mExercises.get(position).getCategory()
//                        , mExercises.get(position).getGroup());
//            }
//        }));
    }

    public void ImageClicking() {
        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        mImgBack = (ImageView) toolbar.findViewById(R.id.imgBack);
        TextView textview = (TextView) toolbar.findViewById(R.id.tvTitle);
        textview.setText(TITLE);
        mImgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });
    }

    public RankingFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }


    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(int id, String category, int group);
    }
}
