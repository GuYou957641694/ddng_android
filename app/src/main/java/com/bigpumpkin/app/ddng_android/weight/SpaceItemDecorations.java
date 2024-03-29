package com.bigpumpkin.app.ddng_android.weight;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class SpaceItemDecorations extends RecyclerView.ItemDecoration {
    private int space;
    public SpaceItemDecorations(int space) {
        this.space = space;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        //不是第一个的格子都设一个左边和底部的间距
        outRect.left = space;
        outRect.bottom = space;
        //由于每行都只有2个，所以第一个都是2的倍数，把左边距设为0
        if (parent.getChildLayoutPosition(view) %2==0) {
            outRect.left = 0;
        }
    }
}
