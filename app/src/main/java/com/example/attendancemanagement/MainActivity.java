package com.example.attendancemanagement;

import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

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

public class MainActivity extends AppCompatActivity {
    RelativeLayout rytRegisterId;
    TextView lblRegisterId;
    EditText edtEmployeeID;
    AppCompatActivity thisActivity;

    Resources res;

String strEmployeeID = null;

    ApiInterface apiInterface;


     Typeface fontRegular,fontBold;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        thisActivity = this;

        fontRegular = ResourcesCompat.getFont(thisActivity, R.font.regularsans);

        fontBold = ResourcesCompat.getFont(thisActivity, R.font.boldsans);


        res = thisActivity.getResources();

        rytRegisterId = (RelativeLayout)findViewById(R.id.rytRegisterId);

        lblRegisterId = (TextView) findViewById(R.id.lblRegisterId);

        lblRegisterId.setTypeface(fontBold);

        edtEmployeeID = (EditText) findViewById(R.id.edtEmployeeID);

        edtEmployeeID.setTypeface(fontRegular);

        rytRegisterId.setOnClickListener(new OnClick());

        lblRegisterId.setOnClickListener(new OnClick());
    }

    private class OnClick implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch(v.getId()){
                case R.id.lblRegisterId:
                case R.id.rytRegisterId:

strEmployeeID = edtEmployeeID.getText().toString().trim();

if(strEmployeeID.isEmpty()){
    Validation.showToast(thisActivity, "Please Enter EmployeeID");
}

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
        else {
            Toast.makeText(thisActivity, "Invalid Employee ID!!!", Toast.LENGTH_SHORT).show();
        }

    } else {
        Toast.makeText(thisActivity, getString(R.string.network_error), Toast.LENGTH_SHORT).show();
    }
}

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