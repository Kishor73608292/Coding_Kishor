package com.example.attendancemanagement.api;

import android.util.Log;

public class APIErrorResponse {

    String message;
    private boolean status;

    public String getMessage() {
        Log.e("","===========>jsonString message"+message);
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
