package com.example.hoangduy.japanese4you.fragments;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hoangduy.japanese4you.R;
import com.example.hoangduy.japanese4you.adapters.WordsAdapter;
import com.example.hoangduy.japanese4you.decorations.GridViewDecoration;
import com.example.hoangduy.japanese4you.models.Word;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link WordsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link WordsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class WordsFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private RecyclerView mWordRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private ArrayList<Word> mWords;
    private OnFragmentInteractionListener mListener;

    public WordsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment WordsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static WordsFragment newInstance(ArrayList<Word> words) {
        WordsFragment fragment = new WordsFragment();
        Bundle args = new Bundle();
        args.putParcelableArrayList(ARG_PARAM1, words);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mWords = getArguments().getParcelableArrayList(ARG_PARAM1);
            Log.i("adad", mWords.size() + "");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_words, container, false);
        mWordRecyclerView = (RecyclerView) view.findViewById(R.id.wordsRecyclerView);
        mAdapter = new WordsAdapter(mWords);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        mWordRecyclerView.setLayoutManager(gridLayoutManager);
        mWordRecyclerView.setAdapter(mAdapter);
        mWordRecyclerView.addItemDecoration(new GridViewDecoration(10));
        return view;
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
