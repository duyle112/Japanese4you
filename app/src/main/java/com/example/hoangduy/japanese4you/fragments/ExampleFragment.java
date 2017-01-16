package com.example.hoangduy.japanese4you.fragments;

import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.hoangduy.japanese4you.R;
import com.example.hoangduy.japanese4you.decorations.GridViewDecoration;
import com.example.hoangduy.japanese4you.models.Sentences;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;

@EFragment(R.layout.fragment_example)
public class ExampleFragment extends Fragment {

    @ViewById(R.id.exampleRecyclerView)
    RecyclerView mExampleRecyclerView;
    @FragmentArg("sentences")
    ArrayList<Sentences> mSentences;

    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayout;


    private OnFragmentInteractionListener mListener;

    @AfterViews
    public void init() {
       // mAdapter = new ExampleAdapter(mSentences);
        mLayout = new LinearLayoutManager(getContext());
        mExampleRecyclerView.setLayoutManager(mLayout);
        mExampleRecyclerView.setAdapter(mAdapter);
        mExampleRecyclerView.addItemDecoration(new GridViewDecoration(10));
    }

    public ExampleFragment() {
        // Required empty public constructor
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
