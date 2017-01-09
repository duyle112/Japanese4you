package com.example.hoangduy.japanese4you.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.hoangduy.japanese4you.R;

/**
 * Created by HoangDuy on 04/01/2017.
 */
public class WordsAdapter extends RecyclerView.Adapter<WordsAdapter.ViewHolder> {
    private String[] mStrs;

    public WordsAdapter(String[] strs) {
        mStrs = strs;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_vocabulary_title, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mTvLesson.setText(mStrs[position]);

    }

    @Override
    public int getItemCount() {
        return mStrs.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mTvLesson;

        public ViewHolder(View itemView) {
            super(itemView);
            mTvLesson = (TextView) itemView.findViewById(R.id.tvTitle);
        }
    }
}
