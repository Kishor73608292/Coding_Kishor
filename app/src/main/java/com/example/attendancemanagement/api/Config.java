package com.example.attendancemanagement.api;

import android.util.Log;

public class Config {


    private static final Config ourInstance = new Config();

    public static Config getInstance() {
        return ourInstance;
    }

    private Config() {
    }


    //demo url
    String BASE_URL="http://192.168.29.163:8080";




    //live url

    /*String SERVER_PATH="https://pritis-ecademy.com/api";
    String BASE_URL="/auth/login/";
    String VERSION="/v1/";*/

    public String getSERVER_PATH() {
        return BASE_URL;
    }

    public String getBASE_URL() {
        Log.d("BaseUrl",""+BASE_URL);
        return BASE_URL;
    }

}
