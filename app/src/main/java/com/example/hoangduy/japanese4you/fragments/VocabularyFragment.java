package com.example.hoangduy.japanese4you.fragments;

import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.example.hoangduy.japanese4you.R;
import com.example.hoangduy.japanese4you.adapters.ExampleAdapter;
import com.example.hoangduy.japanese4you.decorations.GridViewDecoration;
import com.example.hoangduy.japanese4you.models.Sentences;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;

@EFragment(R.layout.fragment_vocabulary)
public class VocabularyFragment extends Fragment {


    @ViewById(R.id.tvRoman)
    TextView mTvRoman;

    @ViewById(R.id.tvHira)
    TextView mTvHira;

    @ViewById(R.id.tvType)
    TextView mTvType;

    @ViewById(R.id.tvMeaning)
    TextView mTvMeaning;

    @ViewById(R.id.recyclerView)
    RecyclerView mRecyclerView;

    @FragmentArg("sentences")
    ArrayList<Sentences> mSentences;

    private RecyclerView.Adapter mAdapter;

    @AfterViews
    public void init() {
        mAdapter = new ExampleAdapter(mSentences);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addItemDecoration(new GridViewDecoration(20));
    }

    private OnFragmentInteractionListener mListener;

    public VocabularyFragment() {
        // Required empty public constructor
    }







    // TODO: Rename method, update argument and hook method into UI event


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
