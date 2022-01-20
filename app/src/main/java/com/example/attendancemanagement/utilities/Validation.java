package com.example.attendancemanagement.utilities;

import android.content.Context;
import android.util.Patterns;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Validation {

    private static  Validation ourInstance = new Validation();

    public static  Validation getInstance(){
        return ourInstance;
    }


    public boolean isEmailEmpty(AppCompatActivity activity, String email){

        if(email.toString().trim().equalsIgnoreCase("")){
            return  true;
        }else{
            return false;
        }


    }
    public boolean isEmail(AppCompatActivity activity, String email){

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            return  true;
        }else{
            return false;
        }


    }
    public boolean isPasswordEmpty(AppCompatActivity activity, String password){

        if(password.toString().trim().equalsIgnoreCase("")){
            return  true;
        }else{
            return false;
        }


    }

    public boolean isFirstNameEmpty(AppCompatActivity activity, String firstname){

        if(firstname.toString().trim().equalsIgnoreCase("")){
            return  true;
        }else{
            return false;
        }


    }


    public boolean isLastNameEmpty(AppCompatActivity activity, String lastname){

        if(lastname.toString().trim().equalsIgnoreCase("")){
            return  true;
        }else{
            return false;
        }


    }


    public boolean isGenderEmpty(AppCompatActivity activity, String gender){

        if(gender.toString().trim().equalsIgnoreCase("")){
            return  true;
        }else{
            return false;
        }


    }

    public boolean isBirthdayEmpty(AppCompatActivity activity, String birthday){

        if(birthday.toString().trim().equalsIgnoreCase("")){
            return  true;
        }else{
            return false;
        }


    }

    public boolean isMobileNoEmpty(AppCompatActivity activity, String mobile){

        if(mobile.toString().trim().equalsIgnoreCase("")){
            return  true;
        }else{
            return false;
        }


    }


    public boolean isPasswordMatches(AppCompatActivity activity, String password, String confimPassword){

        if(!password.equals(confimPassword)){
            return  true;
        }else{
            return false;
        }


    }

    public static void showToast(Context screenObj, String alertMsg) {
        Toast.makeText(screenObj, alertMsg, Toast.LENGTH_SHORT).show();
    }
}
