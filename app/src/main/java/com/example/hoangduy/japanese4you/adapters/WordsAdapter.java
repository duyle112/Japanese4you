package com.example.hoangduy.japanese4you.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.hoangduy.japanese4you.R;
import com.example.hoangduy.japanese4you.models.Word;

import java.util.List;

/**
 * Created by HoangDuy on 04/01/2017.
 */
public class WordsAdapter extends RecyclerView.Adapter<WordsAdapter.ViewHolder> {
    private List<Word> mWords;

    public WordsAdapter(List<Word> words) {
        mWords = words;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_word, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mTvKanji.setText(mWords.get(position).getKanji());
        holder.mTvRomanji.setText(mWords.get(position).getRomanji());
        holder.mTvMeaning.setText(mWords.get(position).getMeaning());
    }

    @Override
    public int getItemCount() {
        return mWords.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mTvKanji;
        private TextView mTvRomanji;
        private TextView mTvMeaning;

        public ViewHolder(View itemView) {
            super(itemView);
            mTvKanji = (TextView) itemView.findViewById(R.id.tvKanji);
            mTvRomanji = (TextView) itemView.findViewById(R.id.tvRomanji);
            mTvMeaning = (TextView) itemView.findViewById(R.id.tvMeaning);
        }
    }
}
