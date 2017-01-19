package com.example.hoangduy.japanese4you.fragments;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hoangduy.japanese4you.R;
import com.example.hoangduy.japanese4you.ScheduleActivity;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;

@EFragment(R.layout.fragment_settings)
public class SettingsFragment extends Fragment {

    private static final String TITLE ="Settings" ;

    @Click(R.id.imgCalendar)
    void setSchedule() {
        Intent intent = new Intent(getActivity(),ScheduleActivity.class);
        startActivity(intent);
    }

    @AfterViews
    void init() {
        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        ImageView img = (ImageView) toolbar.findViewById(R.id.imgBack);
        TextView textview = (TextView) toolbar.findViewById(R.id.tvTitle);
        textview.setText(TITLE);
        img.setVisibility(View.INVISIBLE);
    }

    private OnFragmentInteractionListener mListener;

    public SettingsFragment() {
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
