package com.qiang.lib.fun.common.widget.recyler.decorations;

import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;


public class LTRBSpaceItemDecoration extends RecyclerView.ItemDecoration {

    private int lSpace;
    private int tSpace;
    private int rSpace;
    private int bSpace;

    /**
     * @param lSpace  左侧空间
     * @param tSpace 顶部空间
     * @param rSpace 右侧空间
     * @param bSpace 底部空间
     */
    public LTRBSpaceItemDecoration(int lSpace,int tSpace,int rSpace,int bSpace) {
        this.lSpace = lSpace;
        this.tSpace = tSpace;
        this.rSpace = rSpace;
        this.bSpace = bSpace;
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.left = lSpace;
        outRect.right = rSpace;
        outRect.top = tSpace;
        outRect.bottom = bSpace;
    }
}
