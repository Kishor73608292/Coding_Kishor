package com.example.attendancemanagement;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.attendancemanagement.adapter.AttendanceListAdapter;
import com.example.attendancemanagement.adapter.EmployeeListAdapter;
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
import java.util.ArrayList;
import java.util.List;

public class AttendanceList extends AppCompatActivity {

    AppCompatActivity thisActivity;

    Resources res;

    ApiInterface apiInterface;

    Typeface fontRegular,fontBold;

    RecyclerView attRecyl;

    ArrayList<Attendance> AtList = new ArrayList<Attendance>();

AttendanceListAdapter attendanceListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance_list);

        thisActivity = this;

        if(AtList.size()>0){
            AtList.clear();
        }

        fontRegular = ResourcesCompat.getFont(thisActivity, R.font.regularsans);

        fontBold = ResourcesCompat.getFont(thisActivity, R.font.boldsans);

        res = thisActivity.getResources();

        attRecyl = (RecyclerView) findViewById(R.id.attRecyl);

        makeListCall();
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

    private void makeListCall() {

        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Employee user = new Employee();
        Progress.getInstance().show(thisActivity,getString(R.string.loading));
        Call<List<Attendance>> call=apiInterface.getAtList("20-Jan-22");
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
            }
        });
    }
}