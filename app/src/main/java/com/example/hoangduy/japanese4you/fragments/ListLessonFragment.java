package com.example.hoangduy.japanese4you.fragments;

import android.content.Context;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import com.example.hoangduy.japanese4you.R;
import com.example.hoangduy.japanese4you.adapters.WordsAdapter;
import com.example.hoangduy.japanese4you.database.DataHelper;
import com.example.hoangduy.japanese4you.decorations.GridViewDecoration;
import com.example.hoangduy.japanese4you.models.Example;
import com.example.hoangduy.japanese4you.models.Word;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;


@EFragment(R.layout.fragment_list_lesson)
public class ListLessonFragment extends Fragment {

    @ViewById(R.id.lessonRecyclerView)
    RecyclerView mLessonRecycler;

    @FragmentArg("pos")
    int mPos;

    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayout;
    private List<Word> mWords;
    private DataHelper mDataHelper;
    private List<Word> mGroupWord;
    private List<String> mTitles;
    private OnFragmentInteractionListener mListener;

    public ListLessonFragment() {
        // Required empty public constructor
    }

    @AfterViews
    public void init() {
        openDb();
        mWords = mDataHelper.getWords();
        mTitles = new ArrayList<>();
        mGroupWord = new ArrayList<>();
        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        ImageView img = (ImageView) toolbar.findViewById(R.id.imgBack);
        img.setVisibility(View.INVISIBLE);
        final FragmentManager fragmentManager = getFragmentManager();
        mGroupWord = filter(mPos);
        addTitle();
        mAdapter = new WordsAdapter(mTitles);
        mLessonRecycler.setLayoutManager(new GridLayoutManager(getContext(), 2));
        mLessonRecycler.setAdapter(mAdapter);
        mLessonRecycler.addItemDecoration(new GridViewDecoration(20));
        mLessonRecycler.addOnItemTouchListener(new RecyclerTouchListener(getContext(), mLessonRecycler, new ClickListener() {
            @Override
            public void onClick(View view, int position) {
                WordDetailFragment wordDetailFragment = WordDetailFragment_.builder()
                        .mWords((ArrayList<Word>) mGroupWord)
                        .mCount(mGroupWord.size())
                        .mPos(position)
                        .build();
                fragmentManager.beginTransaction().replace(R.id.flContainerSub, wordDetailFragment).addToBackStack(null).commit();
            }
        }));
    }

    public List<Example> getExamples(int id) {
        return mDataHelper.getExamples(id);
    }

    public void addTitle() {
        for (int i = 0; i < mGroupWord.size(); i++) {
            mTitles.add(mGroupWord.get(i).getKanji());
        }
    }

    public List<Word> filter(int level) {
        List<Word> words = new ArrayList<>();
        switch (level) {
            case 0:
                for (int i = 0; i < mWords.size(); i++) {
                    if (mWords.get(i).getCategory().equals("5")) {
                        words.add(mWords.get(i));
                    }
                }
                break;

            case 1:
                for (int i = 0; i < mWords.size(); i++) {
                    if (mWords.get(i).getCategory().equals("4")) {
                        words.add(mWords.get(i));
                    }
                }
                break;

            case 2:
                for (int i = 0; i < mWords.size(); i++) {
                    if (mWords.get(i).getCategory().equals("3")) {
                        words.add(mWords.get(i));
                    }
                }
                break;

            case 3:
                for (int i = 0; i < mWords.size(); i++) {
                    if (mWords.get(i).getCategory().equals("2")) {
                        words.add(mWords.get(i));
                    }
                }
                break;

            case 4:
                for (int i = 0; i < mWords.size(); i++) {
                    if (mWords.get(i).getCategory().equals("1")) {
                        words.add(mWords.get(i));
                    }
                }
                break;
        }
        return words;
    }

    public void openDb() {
        mDataHelper = new DataHelper(getContext(), "", null, 1);
        mDataHelper.openDataBase();
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

    static class RecyclerTouchListener implements RecyclerView.OnItemTouchListener {

        private GestureDetector gestureDetector;
        private ClickListener clickListener;

        public RecyclerTouchListener(Context context, final RecyclerView recyclerView, final ClickListener clickListener) {
            this.clickListener = clickListener;
            gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }
            });
        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {

            View child = rv.findChildViewUnder(e.getX(), e.getY());
            if (child != null && clickListener != null && gestureDetector.onTouchEvent(e)) {
                clickListener.onClick(child, rv.getChildPosition(child));
            }
            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {
        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

        }

    }

    interface ClickListener {
        void onClick(View view, int position);
    }

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
