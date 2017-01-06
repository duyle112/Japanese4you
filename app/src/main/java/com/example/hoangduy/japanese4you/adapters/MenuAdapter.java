package com.example.hoangduy.japanese4you.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hoangduy.japanese4you.R;
import com.example.hoangduy.japanese4you.models.Section;

import java.util.List;

/**
 * Created by HoangDuy on 03/01/2017.
 */
public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.ViewHolder> {

    private List<Section> mSections;

    public MenuAdapter(List<Section> sections) {
        mSections = sections;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_menu_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mImgicon.setImageResource(mSections.get(position).getIcon());
        holder.mTvContent.setText(mSections.get(position).getContent());
    }

    @Override
    public int getItemCount() {
        return mSections.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mTvContent;
        private ImageView mImgicon;

        public ViewHolder(View itemView) {
            super(itemView);
            mTvContent = (TextView) itemView.findViewById(R.id.tvContent);
            mImgicon = (ImageView) itemView.findViewById(R.id.imgIcon);
        }
    }
}
