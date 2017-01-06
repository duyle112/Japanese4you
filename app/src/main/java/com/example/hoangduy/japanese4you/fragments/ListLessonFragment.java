package com.example.hoangduy.japanese4you.fragments;

import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.hoangduy.japanese4you.R;
import com.example.hoangduy.japanese4you.adapters.WordsAdapter;
import com.example.hoangduy.japanese4you.decorations.GridViewDecoration;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;
import org.androidannotations.annotations.ViewById;


@EFragment(R.layout.fragment_list_lesson)
public class ListLessonFragment extends Fragment {

    @ViewById(R.id.lessonRecyclerView)
    RecyclerView mLessonRecycler;

    @FragmentArg("pos")
    int mPos;

    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayout;

    private OnFragmentInteractionListener mListener;

    public ListLessonFragment() {
        // Required empty public constructor
    }

    @AfterViews
    public void init() {
        String[] strings = new String[]{""};
        switch (mPos) {
            case 0:
                strings = getResources().getStringArray(R.array.lessonsVoca);
                break;
            case 1:
                strings = getResources().getStringArray(R.array.lessonsVoca1);
                break;
            case 2:
                strings = getResources().getStringArray(R.array.lessonsVoca2);
                break;
            case 3:
                strings = getResources().getStringArray(R.array.lessonsVoca3);
                break;
            case 4:
                strings = getResources().getStringArray(R.array.lessonsVoca4);
                break;
        }
        mAdapter = new WordsAdapter(strings);
        mLayout = new LinearLayoutManager(getContext());
        mLessonRecycler.setLayoutManager(mLayout);
        mLessonRecycler.setAdapter(mAdapter);
        mLessonRecycler.addItemDecoration(new GridViewDecoration(20));
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
