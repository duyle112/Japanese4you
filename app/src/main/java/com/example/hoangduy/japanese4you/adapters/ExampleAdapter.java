package com.example.hoangduy.japanese4you.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.hoangduy.japanese4you.R;
import com.example.hoangduy.japanese4you.models.Sentences;

import java.util.ArrayList;

/**
 * Created by HoangDuy on 04/01/2017.
 */
public class ExampleAdapter extends RecyclerView.Adapter<ExampleAdapter.ViewHolder> {
    private ArrayList<Sentences> mSentences;

    public ExampleAdapter(ArrayList<Sentences> sentences) {
        mSentences = sentences;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_sentences, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mTvJapSentences.setText(mSentences.get(position).getJapaneseSen());
        holder.mTvEngSentences.setText(mSentences.get(position).getEnglishSen());
        holder.mTvRomajiSentences.setText(mSentences.get(position).getRomanjiSen());
    }

    @Override
    public int getItemCount() {
        return mSentences.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView mTvJapSentences;
        private TextView mTvEngSentences;
        private TextView mTvRomajiSentences;

        public ViewHolder(View itemView) {
            super(itemView);
            mTvJapSentences = (TextView) itemView.findViewById(R.id.tvJapSentence);
            mTvRomajiSentences = (TextView) itemView.findViewById(R.id.tvRomajiSentence);
            mTvEngSentences = (TextView) itemView.findViewById(R.id.tvEngSentence);
        }
    }
}
