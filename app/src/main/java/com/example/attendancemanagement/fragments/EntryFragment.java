package com.example.attendancemanagement.fragments;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.attendancemanagement.AttendanceList;
import com.example.attendancemanagement.EmployeeList;
import com.example.attendancemanagement.LoginActivity;
import com.example.attendancemanagement.MainActivity;
import com.example.attendancemanagement.R;
import com.example.attendancemanagement.api.APIErrorResponse;
import com.example.attendancemanagement.api.APIResponse;
import com.example.attendancemanagement.api.ApiClient;
import com.example.attendancemanagement.api.ApiInterface;
import com.example.attendancemanagement.api.Attendance;
import com.example.attendancemanagement.utilities.JsonConversion;
import com.example.attendancemanagement.utilities.Network;
import com.example.attendancemanagement.utilities.Progress;
import com.example.attendancemanagement.utilities.Validation;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EntryFragment extends MyApplicationFragment {

    View v;
    //ProgressDialog loading;
    Activity thisActivity;


    Resources res;

    ImageView backImage,imgFilter;
    TextView lblBack,lblHome;

    TextView lblEmployee,lblAttendance,lblAboutUs;

    RelativeLayout rytback;

        RelativeLayout rytLogin;

    EditText edtEmployeeID;

    EditText edtEmployeePassword;

    TextView lblLogin;

    ApiInterface apiInterface;


    Typeface fontRegular,fontBold;

    String strEmployeeID = null;

    String strEmployeePassword = null;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        thisActivity = getActivity();
        res = thisActivity.getResources();
        v = inflater.inflate(R.layout.activity_login, null, false);
        init();
        return v;

    }

    @Override
    public void onPause() {
        lblHome.setVisibility(View.GONE);
        rytback.setVisibility(View.GONE);
        backImage.setVisibility(View.GONE);
        lblBack.setVisibility(View.GONE);
        imgFilter.setVisibility(View.GONE);
        super.onPause();
    }

    @Override
    public void onResume() {
        lblHome.setVisibility(View.GONE);
        rytback.setVisibility(View.VISIBLE);
        backImage.setVisibility(View.VISIBLE);
        lblBack.setVisibility(View.VISIBLE);
        imgFilter.setVisibility(View.GONE);
        super.onResume();
    }

    private void init() {

        imgFilter = (ImageView) thisActivity.findViewById(R.id.imgFilter);

        imgFilter.setVisibility(View.GONE);


        lblHome = (TextView) thisActivity.findViewById(R.id.lblHome);

        lblHome.setText("HOME");

        backImage = (ImageView) thisActivity.findViewById(R.id.backImage);

        lblBack = (TextView) thisActivity.findViewById(R.id.lblBack);

        rytback = (RelativeLayout) thisActivity .findViewById(R.id.rytback);

        rytLogin = (RelativeLayout) v .findViewById(R.id.rytLogin);


        fontRegular = ResourcesCompat.getFont(thisActivity, R.font.regularsans);

        fontBold = ResourcesCompat.getFont(thisActivity, R.font.boldsans);


        edtEmployeeID = (EditText) v.findViewById(R.id.edtEmployeeID);

        edtEmployeeID.setTypeface(fontRegular);

        edtEmployeePassword = (EditText) v.findViewById(R.id.edtEmployeePassword);

        edtEmployeePassword.setTypeface(fontRegular);

        res = thisActivity.getResources();

        lblLogin = (TextView) v.findViewById(R.id.lblLogin);

        backImage.setOnClickListener(new OnClick());
        lblBack.setOnClickListener(new OnClick());
        rytLogin.setOnClickListener(new OnClick());
        rytback.setOnClickListener(new OnClick());


        lblLogin.setTypeface(fontBold);

        lblLogin.setOnClickListener(new OnClick());



    }

    private void doBackPress() {


        getFragmentManager().popBackStackImmediate();
    }



    private class OnClick implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch(v.getId()){
                case R.id.lblLogin:
                case R.id.rytLogin:

                    strEmployeeID = edtEmployeeID.getText().toString().trim();

                    //strEmployeePassword = edtEmployeePassword.getText().toString().trim();

                    if(strEmployeeID.isEmpty()){
                        Validation.showToast(thisActivity, "Please Enter UserID");
                    }

                   /* else if(strEmployeePassword.isEmpty()){
                        Validation.showToast(thisActivity, "Please Enter Password");
                    }
*/
                    else{

                        if (Network.getInstance().isNetwork(thisActivity)) {

                            if(strEmployeeID.equalsIgnoreCase("BT0001")) {
                                make_rotrofit_call(strEmployeeID);
                            }
                            else if(strEmployeeID.equalsIgnoreCase("BT0002")) {
                                make_rotrofit_call(strEmployeeID);
                            }
                            else if(strEmployeeID.equalsIgnoreCase("BT0003")) {
                                make_rotrofit_call(strEmployeeID);
                            }
                            else if(strEmployeeID.equalsIgnoreCase("BT0004")) {
                                make_rotrofit_call(strEmployeeID);
                            }
                            else if(strEmployeeID.equalsIgnoreCase("BT0005")) {
                                make_rotrofit_call(strEmployeeID);
                            }
                            else if(strEmployeeID.equalsIgnoreCase("BT0006")) {
                                make_rotrofit_call(strEmployeeID);
                            }
                            else if(strEmployeeID.equalsIgnoreCase("BT0007")) {
                                make_rotrofit_call(strEmployeeID);
                            }
                            else if(strEmployeeID.equalsIgnoreCase("BT0008")) {
                                make_rotrofit_call(strEmployeeID);
                            }
                            else if(strEmployeeID.equalsIgnoreCase("BT0009")) {
                                make_rotrofit_call(strEmployeeID);
                            }
                            else if(strEmployeeID.equalsIgnoreCase("BT0010")) {
                                make_rotrofit_call(strEmployeeID);
                            }
                            else if(strEmployeeID.equalsIgnoreCase("BT0011")) {
                                make_rotrofit_call(strEmployeeID);
                            }
                            else if(strEmployeeID.equalsIgnoreCase("BT0012")) {
                                make_rotrofit_call(strEmployeeID);
                            }
                            else {
                                Toast.makeText(thisActivity, "Invalid Employee ID!!!", Toast.LENGTH_SHORT).show();
                            }

                        } else {
                            Toast.makeText(thisActivity, getString(R.string.network_error), Toast.LENGTH_SHORT).show();
                        }


                    }

                    break;


                case R.id.backImage:
                case R.id.lblBack:
                case R.id.rytback:

                    final Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            //Do something after 100ms

                            doBackPress();
                        }
                    }, 100);


                    break;

            }
        }
    }


    private void make_rotrofit_call(String strEmployeeID) {
        Attendance user = new Attendance();
        user.setEmployeeID(strEmployeeID);
        apiInterface= ApiClient.getClient().create(ApiInterface.class);
        Call<APIResponse> call=apiInterface.getEmp(user.getEmployeeID());
        call.enqueue(new Callback<APIResponse>() {
            @Override
            public void onResponse(Call<APIResponse> call, Response<APIResponse> response) {
                if (response.isSuccessful()) {
                    Progress.getInstance().hide();
                    System.out.println("++++++++++++>>>>>>>>>>>>>>>>>>>>>>>>>>"+response.body().getMessage());
                    Toast.makeText(thisActivity, response.body().getMessage(), Toast.LENGTH_SHORT).show();


                    edtEmployeeID.setText("");


                }

                else{

                    APIErrorResponse apiErrorResponse=new APIErrorResponse();
                    try {
                        apiErrorResponse= (APIErrorResponse) JsonConversion.getInstance().stringToObject(String.valueOf(response.errorBody().string()),new TypeToken<APIErrorResponse>(){}.getType());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Progress.getInstance().hide();


                    Toast.makeText(thisActivity, getString(R.string.failed), Toast.LENGTH_SHORT).show();
/*
                    name.setText("");
                    email.setText("");
                    password.setText("");
                    confirmPassword.setText("");

*/

                }

            }

            @Override
            public void onFailure(Call<APIResponse> call, Throwable t) {

                Progress.getInstance().hide();
                Toast.makeText(thisActivity, getString(R.string.failed), Toast.LENGTH_SHORT).show();



/*
                name.setText("");
                email.setText("");
                password.setText("");
                confirmPassword.setText("");

*/
            }
        });
    }

}
