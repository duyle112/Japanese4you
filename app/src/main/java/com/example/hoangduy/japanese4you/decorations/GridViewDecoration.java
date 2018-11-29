package com.example.hoangduy.japanese4you.decorations;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by HoangDuy on 03/01/2017.
 */
public class GridViewDecoration extends RecyclerView.ItemDecoration {
    private int mSpace;

    public GridViewDecoration(int space) {
        mSpace = space;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.left = mSpace;
        outRect.right = mSpace;
        outRect.bottom = mSpace;
        if (parent.getChildLayoutPosition(view) == 0 || parent.getChildLayoutPosition(view) == 1) {
            outRect.top = mSpace;
        } else {
            outRect.top = 0;
        }
    }

}
