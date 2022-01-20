package com.example.attendancemanagement.utilities;

import android.app.Activity;
import android.app.ProgressDialog;

import androidx.appcompat.app.AppCompatActivity;

public class Progress {

    private static final Progress ourInstance = new Progress();

    public static Progress getInstance(){
        return ourInstance;
    }

    private Progress() {
    }

    ProgressDialog mProgressDialog;

    public void show(Activity activity, String msg){

        try {
            mProgressDialog = new ProgressDialog(activity);
            mProgressDialog.setMessage(msg);
            mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            mProgressDialog.setCanceledOnTouchOutside(false);
            mProgressDialog.setCancelable(false);

            mProgressDialog.show();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public boolean isShowing(){
        if (mProgressDialog!=null) {
            return mProgressDialog.isShowing();
        }
        return false;
    }

    public void hide(){
        if (mProgressDialog!=null && isShowing()){
            try {
                mProgressDialog.dismiss();
            }catch(Exception ex){
                ex.printStackTrace();
            }
        }
    }
}
