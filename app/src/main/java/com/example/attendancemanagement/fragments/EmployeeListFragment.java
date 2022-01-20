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
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.attendancemanagement.AttendanceList;
import com.example.attendancemanagement.EmployeeList;
import com.example.attendancemanagement.LoginActivity;
import com.example.attendancemanagement.R;
import com.example.attendancemanagement.adapter.EmployeeListAdapter;
import com.example.attendancemanagement.api.APIErrorResponse;
import com.example.attendancemanagement.api.ApiClient;
import com.example.attendancemanagement.api.ApiInterface;
import com.example.attendancemanagement.api.Employee;
import com.example.attendancemanagement.utilities.JsonConversion;
import com.example.attendancemanagement.utilities.Progress;
import com.example.attendancemanagement.utilities.RecyclerTouchListener;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EmployeeListFragment extends MyApplicationFragment {
    View v;
    //ProgressDialog loading;
    Activity thisActivity;

    private ScrollView scrMain;

    Resources res;

    ImageView backImage,imgFilter;
    TextView lblBack,lblHome;


    RelativeLayout rytback;

    ApiInterface apiInterface;

    Typeface fontRegular,fontBold;


    RecyclerView empList;

    ArrayList<Employee> empArrayList = new ArrayList<Employee>();

    EmployeeListAdapter employeeListAdapter;
    TextView lblNoList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        thisActivity = getActivity();
        res = thisActivity.getResources();
        v = inflater.inflate(R.layout.activity_employee_list, null, false);
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
        lblHome.setVisibility(View.VISIBLE);
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

        lblHome.setText("Employee List");

        backImage = (ImageView) thisActivity.findViewById(R.id.backImage);

        lblBack = (TextView) thisActivity.findViewById(R.id.lblBack);

        rytback = (RelativeLayout) thisActivity .findViewById(R.id.rytback);

        if(empArrayList.size()>0){
            empArrayList.clear();
        }

        fontRegular = ResourcesCompat.getFont(thisActivity, R.font.regularsans);

        fontBold = ResourcesCompat.getFont(thisActivity, R.font.boldsans);


        empList = (RecyclerView) v.findViewById(R.id.empList);

        lblNoList = (TextView) v.findViewById(R.id.lblNoList);

        backImage.setOnClickListener(new OnClick());
        lblBack.setOnClickListener(new OnClick());
        rytback.setOnClickListener(new OnClick());

        lblNoList.setVisibility(View.VISIBLE);
        empList.setVisibility(View.GONE);

        makeListCall();
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(thisActivity.getApplicationContext());
        empList.setLayoutManager(mLayoutManager);
        empList.setItemAnimator(new DefaultItemAnimator());

        empList.addItemDecoration(new DividerItemDecoration(thisActivity, LinearLayoutManager.VERTICAL));

        empList.addOnItemTouchListener(new RecyclerTouchListener(thisActivity.getApplicationContext(), empList, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                //FileModel movie = FileList.get(position);
                //Toast.makeText(thisActivity.getApplicationContext(), movie.getFileName(), Toast.LENGTH_SHORT).show();

                Bundle bundle = new Bundle();
                bundle.putInt("listEmployeePosition", position);


            }

            @Override
            public void onLongClick(View view, int position) {


            }


        }));


    }

    private void doBackPress() {


        getFragmentManager().popBackStackImmediate();


        
    }

        public class OnClick implements  View.OnClickListener{

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
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
    private void makeListCall() {

        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Employee user = new Employee();
        Progress.getInstance().show(thisActivity,getString(R.string.loading));
        Call<List<Employee>> call=apiInterface.getFileDetails();
        call.enqueue(new Callback<List<Employee>>() {
            @Override
            public void onResponse(Call<List<Employee>> call, Response<List<Employee>> response) {
                if (response.isSuccessful()) {
                    Progress.getInstance().hide();



                    if(empArrayList.size()>0){
                        empArrayList.clear();
                    }


                    empArrayList = (ArrayList<Employee>) response.body();


                    System.out.println("======>"+empArrayList.size());

                    lblNoList.setVisibility(View.GONE);
                    empList.setVisibility(View.VISIBLE);


                    employeeListAdapter = new EmployeeListAdapter(empArrayList, thisActivity);
                    empList.setAdapter(employeeListAdapter);


                    //Toast.makeText(thisActivity, response.body().getMessage(), Toast.LENGTH_SHORT).show();


/*
                    Intent intent = new Intent(RegistrationActivity.this, LoginActivity.class);
                    startActivity(intent);
                    thisActivity.finish();
*/

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


                }

            }

            @Override
            public void onFailure(Call<List<Employee>> call, Throwable t) {

                Progress.getInstance().hide();
                Toast.makeText(thisActivity, getString(R.string.failed), Toast.LENGTH_SHORT).show();

                lblNoList.setVisibility(View.VISIBLE);
                empList.setVisibility(View.GONE);

            }
        });
    }
}
