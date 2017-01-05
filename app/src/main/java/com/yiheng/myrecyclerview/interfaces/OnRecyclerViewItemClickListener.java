package com.yiheng.myrecyclerview.interfaces;

import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Yiheng on 1/4/2017.
 */

public abstract class OnRecyclerViewItemClickListener implements RecyclerView.OnItemTouchListener {

    private RecyclerView mRecyclerView;
    private final GestureDetectorCompat mGestureDetectorCompat;

    protected OnRecyclerViewItemClickListener(RecyclerView recyclerView) {
        this.mRecyclerView = recyclerView;
        MyOnGestureDetector myOnGestureDetector = new MyOnGestureDetector();
        mGestureDetectorCompat =
                new GestureDetectorCompat(recyclerView.getContext(),myOnGestureDetector);
    }

    public abstract void onItemClick(RecyclerView.ViewHolder vh);

    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
        mGestureDetectorCompat.onTouchEvent(e);
        return false;
    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {
        mGestureDetectorCompat.onTouchEvent(e);
    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }

    private class MyOnGestureDetector extends GestureDetector.SimpleOnGestureListener{
        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            View itemView = mRecyclerView.findChildViewUnder(e.getX(), e.getY());
            if (itemView != null) {
                RecyclerView.ViewHolder VH = mRecyclerView.getChildViewHolder(itemView);
                onItemClick(VH);
            }
            return true;
        }
    }
}
