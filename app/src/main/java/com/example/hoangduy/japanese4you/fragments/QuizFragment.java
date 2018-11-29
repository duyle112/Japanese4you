package com.example.hoangduy.japanese4you.fragments;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hoangduy.japanese4you.R;
import com.example.hoangduy.japanese4you.adapters.QuizAdapter;
import com.example.hoangduy.japanese4you.database.DataHelper;
import com.example.hoangduy.japanese4you.models.Quiz;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;


@EFragment(R.layout.fragment_quiz)
public class QuizFragment extends Fragment implements QuestionFragment.OnFragmentInteractionListener, ResultDialogFragment.DialogListener {

    @ViewById(R.id.viewpager)
    ViewPager mViewPager;

    @ViewById(R.id.tabLayout)
    TabLayout mTabs;

    @ViewById(R.id.tvTimeleft)
    TextView mTvTimeleft;

    private QuizAdapter mAdapter;
    @FragmentArg("quiz")
    ArrayList<Quiz> mQuizes;

    @FragmentArg("pos")
    int mPos;

    @FragmentArg("id")
    int mId;

    @FragmentArg("flag")
    boolean mIsSubmit;

    private SharedPreferences mSharePreference;
    public static final String KEY_PREFERENCE = "share";
    public static final String KEY_FLAG = "flag";
    private Handler mHandler;
    private Handler mHandlerCountdown;
    private int mMin = 4;
    private int mSecond = 60;
    private static final String mTime = "05:00";
    private DataHelper mDataHelper;

    @AfterViews
    public void init() {
        setFlag();
        mDataHelper = new DataHelper(getContext(), "", null, 1);
        mDataHelper.openDataBase();
        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        ImageView img = (ImageView) toolbar.findViewById(R.id.imgBack);
        img.setVisibility(View.VISIBLE);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });
        mAdapter = new QuizAdapter(getFragmentManager(), mQuizes, mIsSubmit, this);
        mViewPager.setAdapter(mAdapter);
        mViewPager.setCurrentItem(mPos);
        mTabs.setupWithViewPager(mViewPager);
        mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                mSecond = msg.arg1;
                if (mSecond == 0) {
                    if (mMin == 0) {
                        showResultDialog();
                    } else {
                        mSecond = 60;
                        mMin--;
                    }
                }
                try {
                    mTvTimeleft.setText(mMin + ":" + mSecond);
                } catch (Exception e) {

                }
            }
        };
        mHandlerCountdown = new Handler();
        mHandlerCountdown.postDelayed(new Runnable() {
            @Override
            public void run() {
                Message message = new Message();
                message.arg1 = mSecond - 1;
                mHandler.sendMessage(message);
                mHandlerCountdown.postDelayed(this, 1000);
            }
        }, 1000);
        disableHandler();
    }

    public void disableHandler() {
        if (mIsSubmit) {
            mHandlerCountdown.removeCallbacksAndMessages(null);
            //mTvTimeleft.setText(mTime);
        }
    }

    public void setFlag() {
        mSharePreference = getContext().getSharedPreferences(KEY_PREFERENCE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = mSharePreference.edit();
        editor.putBoolean(KEY_FLAG, true);
        editor.commit();
    }

    public View update(String title) {
        TextView tvTitle = (TextView) LayoutInflater.from(getContext()).inflate(R.layout.custom_tab_layout, null);
        tvTitle.setTextColor(getResources().getColor(R.color.colorIndicator));
        tvTitle.setText(title);
        return tvTitle;
    }

    public QuizFragment() {
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

    public void setFlagFalse() {
        mSharePreference = getContext().getSharedPreferences(QuizFragment.KEY_PREFERENCE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = mSharePreference.edit();
        editor.putBoolean(QuizFragment.KEY_FLAG, false);
        editor.commit();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mHandlerCountdown.removeCallbacksAndMessages(null);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mHandlerCountdown.removeCallbacksAndMessages(null);
    }

    @Override
    public void onFragmentInteraction(int position, int choice) {
        mQuizes.get(position).setChoosenQuestion(choice);
        mAdapter = new QuizAdapter(getFragmentManager(), mQuizes, mIsSubmit, this);
        mViewPager.setAdapter(mAdapter);
        mViewPager.setCurrentItem(position);
        if (mQuizes.get(position).getChoosenQuestion() != 5) {
            mTabs.getTabAt(position).setCustomView(update(position + 1 + ""));
        }
    }

    @Override
    public void onFragmentSubmit() {
        boolean isComplete = false;
        int count = 0;
        for (Quiz quiz : mQuizes) {
            if (quiz.getChoosenQuestion() != 5) {
                count++;
            }
        }
        if (count == mQuizes.size()) {
            isComplete = true;
        }
        if (isComplete) {
            showResultDialog();
        } else {
            showMessage();
        }
    }

    public void showMessage() {
        final Dialog dialog = new Dialog(getContext());
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_alert);
        dialog.setCanceledOnTouchOutside(false);
        Button btnPositive = (Button) dialog.findViewById(R.id.btnPositive);
        Button btnNegative = (Button) dialog.findViewById(R.id.btnNegative);
        btnPositive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                showResultDialog();
            }
        });

        btnNegative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    public void showResultDialog() {
        mHandlerCountdown.removeCallbacksAndMessages(null);
        mDataHelper.updateProgress(mId,(int)calculateAccurateQuestion()+"");
        ResultDialogFragment dialogFragment = ResultDialogFragment.newInstance(calculateAccurateQuestion(), getRightQuestionCount());
        dialogFragment.setCallback(this);
        dialogFragment.setCancelable(false);
        dialogFragment.show(getActivity().getFragmentManager(), "");
    }

    public double calculateAccurateQuestion() {
        int count = 0;
        for (Quiz quiz : mQuizes) {
            if (quiz.getChoosenQuestion() == quiz.getRightAnswer()) {
                count++;
            }
        }
        return ((double) count / mQuizes.size()) * 100;
    }

    public int getRightQuestionCount() {
        int count = 0;
        for (Quiz quiz : mQuizes) {
            if (quiz.getChoosenQuestion() == quiz.getRightAnswer()) {
                count++;
            }
        }
        return count;
    }

    @Override
    public void onAddDialogPositiveClick(DialogFragment dialog) {
        mAdapter = new QuizAdapter(getFragmentManager(), mQuizes, true, this);
        mViewPager.setAdapter(mAdapter);
        mViewPager.setCurrentItem(0);
    }

    @Override
    public void onAddDialogNegativeClick(DialogFragment dialog) {
        setFlagFalse();
        getActivity().onBackPressed();
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
