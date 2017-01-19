package com.example.hoangduy.japanese4you.fragments;

import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hoangduy.japanese4you.R;
import com.example.hoangduy.japanese4you.adapters.WordsAdapter;
import com.example.hoangduy.japanese4you.decorations.GridViewDecoration;
import com.example.hoangduy.japanese4you.models.Word;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

@EFragment(R.layout.fragment_fav)
public class FavFragment extends Fragment {

    private OnFragmentInteractionListener mListener;
    private static final String TITLE = "Favorite";

    @FragmentArg
    ArrayList<Word> mWords;

    @ViewById(R.id.favRecyclerView)
    RecyclerView mFavRecyclerView;

    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayout;
    private List<String> mKanjis;

    public FavFragment() {
        // Required empty public constructor
    }

    @AfterViews
    void init() {
        Log.i("wordsize",mWords.size()+"");
        mKanjis = new ArrayList<>();
        initWord();
        mAdapter = new WordsAdapter(mKanjis);
        mLayout = new GridLayoutManager(getContext(),2);
        mFavRecyclerView.setLayoutManager(mLayout);
        mFavRecyclerView.addItemDecoration(new GridViewDecoration(20));
        mFavRecyclerView.setAdapter(mAdapter);
        itemClick();
        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        ImageView img = (ImageView) toolbar.findViewById(R.id.imgBack);
        TextView textview = (TextView) toolbar.findViewById(R.id.tvTitle);
        textview.setText(TITLE);
        img.setVisibility(View.INVISIBLE);
    }

    public void itemClick() {
        mFavRecyclerView.addOnItemTouchListener(new ListLessonFragment.RecyclerTouchListener(getContext(), mFavRecyclerView, new ListLessonFragment.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                WordDetailFragment wordDetailFragment = WordDetailFragment_.builder()
                        .mWords((ArrayList<Word>) mWords)
                        .mCount(mWords.size())
                        .mPos(position)
                        .build();
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.flContainer, wordDetailFragment).addToBackStack(null).commit();
            }
        }));
    }

    public void initWord() {
        for (Word word : mWords) {
            mKanjis.add(word.getKanji());
        }
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
