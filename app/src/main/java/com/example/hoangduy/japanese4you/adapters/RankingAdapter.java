package com.example.hoangduy.japanese4you.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.hoangduy.japanese4you.R;
import com.example.hoangduy.japanese4you.models.Exercise;

import java.util.List;

/**
 * Created by HoangDuy on 16/01/2017.
 */
public class RankingAdapter extends RecyclerView.Adapter<RankingAdapter.ViewHolder> {

    private List<Exercise> mExercises;

    public RankingAdapter(List<Exercise> exercises) {
        mExercises = exercises;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_ranking, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mTvTestTaken.setText(mExercises.get(position).getTesttaken() + "");
        holder.mTvExerciseName.setText(mExercises.get(position).getName());
        holder.mTvLastScore.setText(setProgress(position) + "%");
        holder.mProgressbar.setProgress(setProgress(position));
    }

    public int setProgress(int position) {
        return mExercises.get(position).getLastscore() == null ? 0 : Integer.parseInt(mExercises.get(position).getLastscore());
    }

    @Override
    public int getItemCount() {
        return mExercises.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mTvExerciseName;
        private TextView mTvTestTaken;
        private View mView;
        private TextView mTvLastScore;
        private ProgressBar mProgressbar;

        public ViewHolder(View itemView) {
            super(itemView);
            mTvExerciseName = (TextView) itemView.findViewById(R.id.tvExerciseName);
            mTvTestTaken = (TextView) itemView.findViewById(R.id.tvTestsTaken);
            mView = itemView.findViewById(R.id.scoreProgress);
            mTvLastScore = (TextView) mView.findViewById(R.id.tvPercent);
            mProgressbar = (ProgressBar) mView.findViewById(R.id.progressBar);
        }
    }
}
