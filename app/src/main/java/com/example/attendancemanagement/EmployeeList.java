package com.example.attendancemanagement;

import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

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

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EmployeeList extends AppCompatActivity {

    AppCompatActivity thisActivity;

    Resources res;

    ApiInterface apiInterface;

    Typeface fontRegular,fontBold;


    RecyclerView empList;

    ArrayList<Employee> empArrayList = new ArrayList<Employee>();

    EmployeeListAdapter employeeListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_list);

        thisActivity = this;

        if(empArrayList.size()>0){
            empArrayList.clear();
        }

        fontRegular = ResourcesCompat.getFont(thisActivity, R.font.regularsans);

        fontBold = ResourcesCompat.getFont(thisActivity, R.font.boldsans);

res = thisActivity.getResources();

        empList = (RecyclerView) findViewById(R.id.empList);


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
            }
        });
    }
}