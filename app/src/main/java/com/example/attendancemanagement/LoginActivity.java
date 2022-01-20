package com.example.attendancemanagement;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.attendancemanagement.api.ApiInterface;
import com.example.attendancemanagement.utilities.Validation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

public class LoginActivity extends AppCompatActivity {

    EditText edtEmployeeID;

    EditText edtEmployeePassword;

    TextView lblLogin;

    AppCompatActivity thisActivity;

    Resources res;

    ApiInterface apiInterface;

    Typeface fontRegular,fontBold;

    String strEmployeeID = null;

    String strEmployeePassword = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        thisActivity = this;

        fontRegular = ResourcesCompat.getFont(thisActivity, R.font.regularsans);

        fontBold = ResourcesCompat.getFont(thisActivity, R.font.boldsans);


        edtEmployeeID = (EditText) findViewById(R.id.edtEmployeeID);

        edtEmployeeID.setTypeface(fontRegular);

        edtEmployeePassword = (EditText) findViewById(R.id.edtEmployeePassword);

        edtEmployeePassword.setTypeface(fontRegular);

        res = thisActivity.getResources();

        lblLogin = (TextView) findViewById(R.id.lblLogin);

        lblLogin.setTypeface(fontBold);

        lblLogin.setOnClickListener(new OnClick());

    }
    private class OnClick implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch(v.getId()){
                case R.id.lblLogin:

                    strEmployeeID = edtEmployeeID.getText().toString().trim();

                    strEmployeePassword = edtEmployeePassword.getText().toString().trim();

                    if(strEmployeeID.isEmpty()){
                        Validation.showToast(thisActivity, "Please Enter UserID");
                    }

                    else if(strEmployeePassword.isEmpty()){
                        Validation.showToast(thisActivity, "Please Enter Password");
                    }

                    else{

                        if(strEmployeeID.equalsIgnoreCase("ADMIN") && strEmployeePassword.equalsIgnoreCase("EMPL")){

                            Intent intent = new Intent(thisActivity,EmployeeList.class);
                            startActivity(intent);

                        }

                        else  if(strEmployeeID.equalsIgnoreCase("ADMIN") && strEmployeePassword.equalsIgnoreCase("ATTL")){

                            Intent intent = new Intent(thisActivity,AttendanceList.class);
                            startActivity(intent);

                        }

                        else if(strEmployeeID.equalsIgnoreCase("USER") && strEmployeePassword.equalsIgnoreCase("USER")){

                            Intent intent = new Intent(thisActivity,MainActivity.class);
                            startActivity(intent);

                        }

                        else{
                            Validation.showToast(thisActivity, "Invalid UserId or Password");
                        }


                    }

                    break;
            }
        }
    }
}