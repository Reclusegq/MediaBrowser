package com.imagebrowser.recluseguo.mediabrowser.decorators;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by recluseguo on 2016/7/11.
 */
public class DividerGridItemDecoration extends RecyclerView.ItemDecoration {

    private static final int[] ATTRS = new int[]{android.R.attr.listDivider};
    private Drawable divider;

    public DividerGridItemDecoration(Context context) {
        final TypedArray typedArray = context.obtainStyledAttributes(ATTRS);
        divider = typedArray.getDrawable(0);
        typedArray.recycle();
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
        drawHorizontal(c, parent);
        drawVertical(c, parent);
    }


    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.set(0, 0, divider.getIntrinsicWidth(), divider.getIntrinsicHeight());
    }

    public void drawHorizontal(Canvas c, RecyclerView view){
        final int childCount = view.getChildCount();

        for(int i = 0; i < childCount; i++){
            View child = view.getChildAt(i);
            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();

            final int top = child.getBottom() + params.bottomMargin;
            final int bottom = top + divider.getIntrinsicHeight();
            final int left = child.getLeft() - params.leftMargin;
            final int right = child.getRight() + params.rightMargin + divider.getIntrinsicWidth();

            divider.setBounds(left, top, right, bottom);
            divider.draw(c);
        }
    }

    public void drawVertical(Canvas c, RecyclerView view){
        final int childCount = view.getChildCount();

        for(int i = 0; i < childCount; i++){
            View child = view.getChildAt(i);
            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();

            final int left = child.getRight() + params.leftMargin;
            final int right = left + divider.getIntrinsicWidth();
            final int top = child.getTop() - params.topMargin;
            final int bottom = child.getBottom() + params.bottomMargin;

            divider.setBounds(left, top, right, bottom);
            divider.draw(c);
        }
    }


}
