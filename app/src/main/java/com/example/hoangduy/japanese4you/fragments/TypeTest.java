package com.example.hoangduy.japanese4you.fragments;

import android.net.Uri;
import android.support.v4.app.Fragment;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hoangduy.japanese4you.R;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;
import org.androidannotations.annotations.ViewById;


@EFragment(R.layout.fragment_type_test)
public class TypeTest extends Fragment {
    @ViewById(R.id.imgIcon)
    ImageView mImgIcon;

    @ViewById(R.id.tvDescription)
    TextView mTvDescription;

    @FragmentArg("pos")
    int mPos;

    @AfterViews
    public void init() {
        String[] res=getResources().getStringArray(R.array.categories);
        mTvDescription.setText(res[mPos]);
        switch (mPos) {
            case 0:mImgIcon.setImageResource(R.drawable.reading);
                break;
            case 1:mImgIcon.setImageResource(R.drawable.kanji);
                break;
            case 2:mImgIcon.setImageResource(R.drawable.gra);
                break;
            case 3:mImgIcon.setImageResource(R.drawable.reading);
                break;
        }
    }

    private OnFragmentInteractionListener mListener;

    public TypeTest() {
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
