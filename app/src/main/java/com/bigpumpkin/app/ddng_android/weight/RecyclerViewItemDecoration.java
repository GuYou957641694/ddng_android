package com.bigpumpkin.app.ddng_android.weight;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class RecyclerViewItemDecoration extends RecyclerView.ItemDecoration {
    private int space = 0;
    private int pos;

    public RecyclerViewItemDecoration(int space) {
        this.space = space;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {

        outRect.top = space;

        //该View在整个RecyclerView中位置。
        pos = parent.getChildAdapterPosition(view);

        //取模

        //两列的左边一列
        if (pos % 3 == 0) {
            outRect.left = space / 3;
            outRect.right = space / 3;
        }

        //两列的右边一列
        if (pos % 3 == 1) {
            outRect.left = space ;
            outRect.right = space/ 3;
        }

        if (pos % 3 == 2) {
            outRect.left = space ;
            outRect.right = space/ 3;
        }

    }
}
