package com.example.attendancemanagement.utilities;

import android.content.Context;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

public class RecyclerTouchListener implements RecyclerView.OnItemTouchListener {

    private GestureDetector gestureDetector;
    private ClickListener clickListener;


    public RecyclerTouchListener(Context context, final RecyclerView recyclerView, final ClickListener clickListener) {
        this.clickListener = clickListener;
        //System.out.println("======>RecyclerTouchListener RecyclerTouchListener");
        gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                //System.out.println("======>RecyclerTouchListener onSingleTapUp");
                return true;
            }

            @Override
            public void onLongPress(MotionEvent e) {
                //System.out.println("======>RecyclerTouchListener onLongPress");
                View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
                if (child != null && clickListener != null) {
                    clickListener.onLongClick(child, recyclerView.getChildPosition(child));
                }
            }
        });
    }




    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
        //System.out.println("======>RecyclerTouchListener onInterceptTouchEvent");
        View child = rv.findChildViewUnder(e.getX(), e.getY());
        if (child != null && clickListener != null && gestureDetector.onTouchEvent(e)) {
            clickListener.onClick(child, rv.getChildPosition(child));
        }
        return false;
    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {
        //System.out.println("======>RecyclerTouchListener onTouchEvent");

    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
        //System.out.println("======>RecyclerTouchListener onRequestDisallowInterceptTouchEvent");

    }

    public interface ClickListener {
        void onClick(View view, int position);

        void onLongClick(View view, int position);
    }



}