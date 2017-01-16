package com.example.hoangduy.japanese4you.fragments;

import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hoangduy.japanese4you.R;
import com.example.hoangduy.japanese4you.adapters.ExampleAdapter;
import com.example.hoangduy.japanese4you.database.DataHelper;
import com.example.hoangduy.japanese4you.decorations.GridViewDecoration;
import com.example.hoangduy.japanese4you.models.Example;
import com.example.hoangduy.japanese4you.models.Word;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
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

    @FragmentArg
    ArrayList<Example> mSentences;

    @ViewById(R.id.imgFav)
    ImageView mImgFav;

    @ViewById(R.id.tvKanji)
    TextView mTvKanji;

    @FragmentArg
    Word mWord;

    private RecyclerView.Adapter mAdapter;
    private DataHelper mDataHelper;

    @Click(R.id.imgFav)
    void doFavorite() {
        mWord.setFavorite(changeFav(mWord.getFavorite()));
        mDataHelper.setFavorite(mWord.getIdword(),mWord.getFavorite());
        mImgFav.setImageResource(setImageFav(mWord.getFavorite()));

    }

    @AfterViews
    public void init() {
        mDataHelper = new DataHelper(getContext(), "", null, 1);
        mTvKanji.setText(mWord.getKanji());
        mTvHira.setText(mWord.getKana());
        mTvRoman.setText(mWord.getRomanji());
        mTvType.setText(mWord.getType());
        mTvMeaning.setText(mWord.getMeaning());
        mImgFav.setImageResource(setImageFav(mWord.getFavorite()));
        mAdapter = new ExampleAdapter(mSentences);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addItemDecoration(new GridViewDecoration(20));
    }

    public int setImageFav(int favorite) {
        return favorite == 0 ? R.drawable.ic_favorite_border_green_a700_48dp : R.drawable.ic_favorite_green_a700_48dp;
    }

    public int changeFav(int fav) {
        return fav == 0 ? 1 : 0;
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
