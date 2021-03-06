package com.example.alscon.chance_o_matic;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

public class RecyclerItemClickListener implements RecyclerView.OnItemTouchListener {

    GestureDetector mGestureDetector;
    private OnItemClickListener mListener;

    public RecyclerItemClickListener(Context c, OnItemClickListener onItemClickListener) {
        mListener = onItemClickListener;
        mGestureDetector = new GestureDetector(c, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }
        });
    }

    @Override
    public boolean onInterceptTouchEvent(RecyclerView v, MotionEvent e) {
        View mChildView = v.findChildViewUnder(e.getX(), e.getY());
        if ((mChildView != null && mListener != null && mGestureDetector.onTouchEvent(e))) {
            mListener.onItemClick(mChildView, v.getChildPosition(mChildView));
            return true;
        }
        return false;
    }

    @Override
    public void onTouchEvent(RecyclerView view, MotionEvent event) {
    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }

    public interface OnItemClickListener {
        public void onItemClick(View view, int i);
    }
}
