package com.example.attendancemanagement.fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

public class MyApplicationFragment  extends Fragment {

    Activity superActivity, thisActivity;

    Context thisContext;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        //Thread.setDefaultUncaughtExceptionHandler(handleAppCrash);
    }
    public void logI(String contentStr) {
        thisActivity = getActivity();
        if (contentStr == null) {
            contentStr = "";
        }
        Log.i("PritisV2", contentStr);
    }

    //    private Thread.UncaughtExceptionHandler handleAppCrash = new Thread.UncaughtExceptionHandler() {
//        @Override
//        public void uncaughtException(Thread thread, Throwable exception) {
//            Log.e("","exception fragment =======" + exception.toString());
//            Log.e("","exception ======= line" + exception.getStackTrace()[0].getLineNumber());
//            android.os.Process.killProcess(android.os.Process.myPid());
//            System.exit(10);
//        }
//    };
    public void logV(String contentStr) {
        thisActivity = getActivity();
        if (contentStr == null) {
            contentStr = "";
        }
        Log.v("PritisV2", contentStr);
    }



    public void logD(String contentStr) {
        thisActivity = getActivity();
        if (contentStr == null) {
            contentStr = "";
        }
        Log.d("PritisV2", contentStr);
    }

    public void logE(String contentStr) {
        thisActivity = getActivity();
        if (contentStr == null) {
            contentStr = "";
        }
        Log.e("PritisV2", contentStr);
    }

    public class OnTouchEvent implements View.OnTouchListener {
        int normal;
        int select;

        public OnTouchEvent(int normal, int select) {
            this.normal = normal;
            this.select = select;
        }

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            int touchAction = event.getAction();
            if (touchAction == MotionEvent.ACTION_DOWN) {
                v.setBackgroundResource(select);
            } else if (touchAction == MotionEvent.ACTION_UP || touchAction == MotionEvent.ACTION_CANCEL) {
                v.setBackgroundResource(normal);
            }
            return false;
        }
    }

    public class OnImageTouchEvent implements View.OnTouchListener {
        int normal;
        int select;

        public OnImageTouchEvent(int normal, int select) {
            this.normal = normal;
            this.select = select;
        }




        @Override
        public boolean onTouch(View v, MotionEvent event) {
            ImageView img = (ImageView) v;
            int touchAction = event.getAction();
            if (touchAction == MotionEvent.ACTION_DOWN) {
                try {
                    img.setImageResource(select);
                } catch (OutOfMemoryError e) {
                    e.printStackTrace();
                }
            } else if (touchAction == MotionEvent.ACTION_UP || touchAction == MotionEvent.ACTION_CANCEL) {
                try {
                    img.setImageResource(normal);
                } catch (OutOfMemoryError e) {
                    e.printStackTrace();
                }
            }
            return false;
        }
    }
}
