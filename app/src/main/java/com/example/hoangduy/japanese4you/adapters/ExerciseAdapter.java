package com.example.hoangduy.japanese4you.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.hoangduy.japanese4you.MainActivity;
import com.example.hoangduy.japanese4you.R;
import com.example.hoangduy.japanese4you.models.Exercise;

import java.util.List;

/**
 * Created by HoangDuy on 08/01/2017.
 */
public class ExerciseAdapter extends RecyclerView.Adapter<ExerciseAdapter.ViewHolder> {
    private List<Exercise> mExercises;
    public onFragmentTransaction mListener;

    public ExerciseAdapter(Context context, List<Exercise> exercises) {
        mExercises = exercises;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mListener = (MainActivity) parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_exercise, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.mTvExercise.setText(mExercises.get(position).getName());
        holder.mTvReadmore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onReadmore(mExercises.get(position).getCategory(),mExercises.get(position).getGroup());
            }
        });
    }

    @Override
    public int getItemCount() {
        return mExercises.size();
    }

    public interface onFragmentTransaction {
        void onReadmore(String category,int group);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mTvExercise;
        private TextView mTvReadmore;

        public ViewHolder(View itemView) {
            super(itemView);
            mTvExercise = (TextView) itemView.findViewById(R.id.tvExercise);
            mTvReadmore = (TextView) itemView.findViewById(R.id.tvReadmore);
        }
    }

}
