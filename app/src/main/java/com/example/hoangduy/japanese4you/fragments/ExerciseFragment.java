package com.example.hoangduy.japanese4you.fragments;

import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hoangduy.japanese4you.R;
import com.example.hoangduy.japanese4you.adapters.ExerciseAdapter;
import com.example.hoangduy.japanese4you.decorations.GridViewDecoration;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;


@EFragment(R.layout.fragment_exercise)
public class ExerciseFragment extends Fragment {

    @ViewById(R.id.ExerciseRecyclerView)
    RecyclerView mRecyclerView;

    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayout;
    private OnFragmentInteractionListener mListener;

    @AfterViews
    public void init() {
        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        ImageView img = (ImageView) toolbar.findViewById(R.id.imgBack);
        TextView textview = (TextView) toolbar.findViewById(R.id.tvTitle);
        textview.setText("Exercise");
        img.setVisibility(View.VISIBLE);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });
        String[] exercises = {"Vocabulary Exercise 01", "Vocabulary Exercise 02",
                "Vocabulary Exercise 03", "Vocabulary Exercise 04",
                "Vocabulary Exercise 05", "Vocabulary Exercise 06", "Vocabulary Exercise 07",
                "Vocabulary Exercise 08", "Vocabulary Exercise 09", "Vocabulary Exercise 010", "Vocabulary Exercise 01", "Vocabulary Exercise 02",
                "Vocabulary Exercise 03", "Vocabulary Exercise 04",
                "Vocabulary Exercise 05", "Vocabulary Exercise 06", "Vocabulary Exercise 07",
                "Vocabulary Exercise 08", "Vocabulary Exercise 09", "Vocabulary Exercise 010"};
        mAdapter = new ExerciseAdapter(exercises);
        mLayout = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayout);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addItemDecoration(new GridViewDecoration(20));
    }

    public ExerciseFragment() {
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
