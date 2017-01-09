package com.example.hoangduy.japanese4you.fragments;

import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.example.hoangduy.japanese4you.R;
import com.example.hoangduy.japanese4you.adapters.VocabularyAdapter;
import com.example.hoangduy.japanese4you.models.Sentences;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;


@EFragment(R.layout.fragment_word_detail)
public class WordDetailFragment extends Fragment {

    @ViewById(R.id.WordViewpager)
    ViewPager mViewpager;

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
        img.setVisibility(View.VISIBLE);
        ArrayList<Sentences> sentences = new ArrayList<>();
        sentences.add(new Sentences("山本は席から飛び上がった", "yamamoto wa seki kara tobiagatta.", "Yamamoto sprang up from his seat."));
        sentences.add(new Sentences("宿題は明日の夜仕上げよう。", "shukudai wa ashita no yoru shiageyou", "I’ll finish the homework tomorrow night."));
        sentences.add(new Sentences("山本は席から飛び上がった", "yamamoto wa seki kara tobiagatta.", "Yamamoto sprang up from his seat."));
        sentences.add(new Sentences("宿題は明日の夜仕上げよう。", "shukudai wa ashita no yoru shiageyou", "I’ll finish the homework tomorrow night."));
        sentences.add(new Sentences("宿題は明日の夜仕上げよう。", "shukudai wa ashita no yoru shiageyou", "I’ll finish the homework tomorrow night."));
        mViewpager.setAdapter(new VocabularyAdapter(getFragmentManager(), sentences));
    }



    private OnFragmentInteractionListener mListener;

    public WordDetailFragment() {
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
