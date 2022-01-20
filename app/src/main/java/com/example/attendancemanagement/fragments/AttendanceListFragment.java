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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.attendancemanagement.AttendanceList;
import com.example.attendancemanagement.EmployeeList;
import com.example.attendancemanagement.LoginActivity;
import com.example.attendancemanagement.R;
import com.example.attendancemanagement.adapter.AttendanceListAdapter;
import com.example.attendancemanagement.api.APIErrorResponse;
import com.example.attendancemanagement.api.ApiClient;
import com.example.attendancemanagement.api.ApiInterface;
import com.example.attendancemanagement.api.Attendance;
import com.example.attendancemanagement.api.Employee;
import com.example.attendancemanagement.utilities.JsonConversion;
import com.example.attendancemanagement.utilities.Progress;
import com.example.attendancemanagement.utilities.RecyclerTouchListener;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AttendanceListFragment extends MyApplicationFragment implements AdapterView.OnItemSelectedListener {
    View v;
    //ProgressDialog loading;
    Activity thisActivity;

    private ScrollView scrMain;

    Resources res;

    ImageView backImage;
    TextView lblBack,lblHome;


    RelativeLayout rytback;

    ApiInterface apiInterface;

    Typeface fontRegular,fontBold;

    RecyclerView attRecyl;

    ArrayList<Attendance> AtList = new ArrayList<Attendance>();

    AttendanceListAdapter attendanceListAdapter;

    TextView lblNoList;

    //logic
    SimpleDateFormat dateFormat = null;

    String formattedDate = null;


    // Millseconds in a day
    private static final long ONE_DAY_MILLI_SECONDS = 24 * 60 * 60 * 1000;

    Date date = null;

    long nextDayMilliSeconds;

    Date previousDate = null;

    String previousDateStr = null;

    ImageView imgFilter;

    Spinner spinner;

    String[] when = { "Today", "Yesterday", "All"};

    Boolean spinTrue = true;

    int spinCheck=0;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        thisActivity = getActivity();
        res = thisActivity.getResources();
        v = inflater.inflate(R.layout.activity_attendance_list, null, false);
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
        imgFilter.setVisibility(View.VISIBLE);
        super.onResume();
    }
    private void init() {


        spinner = (Spinner) v.findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);

        //Creating the ArrayAdapter instance having the country list
        ArrayAdapter aa = new ArrayAdapter(thisActivity,android.R.layout.simple_spinner_item,when);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        spinner.setAdapter(aa);


        dateFormat = new SimpleDateFormat("dd-MMM-yy");
        formattedDate = dateFormat.format(new Date()).toString();
        System.out.println("==============>formattedDate" + formattedDate);

        try {
            date = dateFormat.parse(formattedDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        // Getting the next day and formatting into 'YYYY-MM-DD'
        nextDayMilliSeconds = date.getTime() - ONE_DAY_MILLI_SECONDS;
        previousDate = new Date(nextDayMilliSeconds);
        previousDateStr = dateFormat.format(previousDate);

        System.out.println("==============>previousDateStr" + previousDateStr);

        imgFilter = (ImageView) thisActivity.findViewById(R.id.imgFilter);

        imgFilter.setVisibility(View.VISIBLE);


        lblHome = (TextView) thisActivity.findViewById(R.id.lblHome);

        lblHome.setText("Attendance List");

        backImage = (ImageView) thisActivity.findViewById(R.id.backImage);

        lblBack = (TextView) thisActivity.findViewById(R.id.lblBack);

        rytback = (RelativeLayout) thisActivity .findViewById(R.id.rytback);
        if(AtList.size()>0){
            AtList.clear();
        }
        fontRegular = ResourcesCompat.getFont(thisActivity, R.font.regularsans);

        fontBold = ResourcesCompat.getFont(thisActivity, R.font.boldsans);

        res = thisActivity.getResources();

        attRecyl = (RecyclerView) v.findViewById(R.id.attRecyl);

        lblNoList = (TextView) v.findViewById(R.id.lblNoList);

        lblNoList.setVisibility(View.VISIBLE);
        attRecyl.setVisibility(View.GONE);


        backImage.setOnClickListener(new OnClick());
        lblBack.setOnClickListener(new OnClick());
        rytback.setOnClickListener(new OnClick());

        imgFilter.setOnClickListener(new OnClick());


        makeListCall(formattedDate);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(thisActivity.getApplicationContext());
        attRecyl.setLayoutManager(mLayoutManager);
        attRecyl.setItemAnimator(new DefaultItemAnimator());

        attRecyl.addItemDecoration(new DividerItemDecoration(thisActivity, LinearLayoutManager.VERTICAL));

        attRecyl.addOnItemTouchListener(new RecyclerTouchListener(thisActivity.getApplicationContext(), attRecyl, new RecyclerTouchListener.ClickListener() {
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

    private void makeListCall(String formattedDate) {
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Employee user = new Employee();
        Progress.getInstance().show(thisActivity,getString(R.string.loading));
        if(formattedDate!=null) {
            Call<List<Attendance>> call = apiInterface.getAtList(formattedDate);
            call.enqueue(new Callback<List<Attendance>>() {
                @Override
                public void onResponse(Call<List<Attendance>> call, Response<List<Attendance>> response) {
                    if (response.isSuccessful()) {
                        Progress.getInstance().hide();



                        if(AtList.size()>0){
                            AtList.clear();
                        }


                        AtList = (ArrayList<Attendance>) response.body();


                        System.out.println("======>"+AtList.size());

                        lblNoList.setVisibility(View.GONE);
                        attRecyl.setVisibility(View.VISIBLE);


                        System.out.println("======>"+AtList.get(AtList.size()-1).getEmployeeID());
                        attendanceListAdapter = new AttendanceListAdapter(AtList, thisActivity);
                        attRecyl.setAdapter(attendanceListAdapter);



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
                public void onFailure(Call<List<Attendance>> call, Throwable t) {

                    Progress.getInstance().hide();
                    Toast.makeText(thisActivity, getString(R.string.failed), Toast.LENGTH_SHORT).show();
                    lblNoList.setVisibility(View.VISIBLE);
                    attRecyl.setVisibility(View.GONE);

                }
            });
        }

        else{
           //--------------------------------------------------------------------------------


            Call<List<Attendance>> call = apiInterface.getAttDetails();
            call.enqueue(new Callback<List<Attendance>>() {
                @Override
                public void onResponse(Call<List<Attendance>> call, Response<List<Attendance>> response) {
                    if (response.isSuccessful()) {
                        Progress.getInstance().hide();



                        if(AtList.size()>0){
                            AtList.clear();
                        }


                        AtList = (ArrayList<Attendance>) response.body();


                        System.out.println("======>"+AtList.size());

                        lblNoList.setVisibility(View.GONE);
                        attRecyl.setVisibility(View.VISIBLE);


                        System.out.println("======>"+AtList.get(AtList.size()-1).getEmployeeID());
                        attendanceListAdapter = new AttendanceListAdapter(AtList, thisActivity);
                        attRecyl.setAdapter(attendanceListAdapter);



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
                public void onFailure(Call<List<Attendance>> call, Throwable t) {

                    Progress.getInstance().hide();
                    Toast.makeText(thisActivity, getString(R.string.failed), Toast.LENGTH_SHORT).show();
                    lblNoList.setVisibility(View.VISIBLE);
                    attRecyl.setVisibility(View.GONE);

                }
            });


            //--------------------------------------------------------------------------
        }

    }
    private void doBackPress() {


        getFragmentManager().popBackStackImmediate();
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        //Toast.makeText(thisActivity,when[i] , Toast.LENGTH_LONG).show();
        if(AtList.size()>0){
            AtList.clear();
        }
        if(when[i].equalsIgnoreCase("Today")){
            if(spinCheck==1)
            makeListCall(formattedDate);
        }else if(when[i].equalsIgnoreCase("Yesterday")){
            if(spinCheck==1)
            makeListCall(previousDateStr);
        }
        else {
            if(spinCheck==1)
            makeListCall(null);
        }
spinCheck=1;
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

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

                case R.id.imgFilter:
                    if(spinTrue) {
                        spinner.setVisibility(View.VISIBLE);
                        spinTrue=false;
                    }else{
                        spinner.setVisibility(View.GONE);
                        spinTrue=true;

                    }

                    break;

                          }
        }
    }
}
