package com.example.attendancemanagement.utilities;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.widget.Toast;

import com.example.attendancemanagement.R;

import androidx.appcompat.app.AppCompatActivity;


public class Network {

    private static final Network ourInstance = new Network();

    public static Network getInstance() {

        return ourInstance;
    }

    public boolean isNetwork(Activity activity){


        ConnectivityManager cm = (ConnectivityManager) activity.getSystemService(Context.CONNECTIVITY_SERVICE);
        // test for connection
        if (cm.getActiveNetworkInfo() != null
                && cm.getActiveNetworkInfo().isAvailable()
                && cm.getActiveNetworkInfo().isConnected()) {
            return true;
        } else {
            Toast.makeText(activity,activity.getString(R.string.network_error), Toast.LENGTH_SHORT).show();
            return false;
        }
    }

   }
