package com.bigpumpkin.app.ddng_android.weight;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class SpacesItemDecoration extends RecyclerView.ItemDecoration {

    private int spaceLeft;
    private int spaceRight;
    private int spaceBottom;
    private int spaceTop;

    public SpacesItemDecoration(int spaceLeft) {
        this.spaceLeft = spaceLeft;
        this.spaceRight = spaceRight;
        this.spaceBottom = spaceBottom;
        this.spaceTop = spaceTop;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view,
                               RecyclerView parent, RecyclerView.State state) {
        //给recyclerView的item设置边距
        outRect.left = spaceLeft;
        outRect.right = spaceRight;
        outRect.bottom = spaceBottom;
        outRect.top = spaceTop;

        // Add top margin only for the first item to avoid double space between items
        if (parent.getChildPosition(view) == 0){

        }

    }
}
