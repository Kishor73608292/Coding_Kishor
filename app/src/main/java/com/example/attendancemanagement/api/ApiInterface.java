package com.example.attendancemanagement.api;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiInterface {

    @POST("/AttendanceRegistry/{employeeID}")
    Call<APIResponse> getEmp(@Path("employeeID") String employeeID);

    @GET("/employeeDetails/")
    Call<List<Employee>> getFileDetails();

    @GET("/attendanceDetails/")
    Call<List<Attendance>> getAttDetails();

    @POST("/AttendanceListDateWise/{dDate}")
    Call<List<Attendance>> getAtList(@Path("dDate") String dDate);

}
