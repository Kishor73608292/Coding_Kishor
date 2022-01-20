package com.example.attendancemanagement.api;

import java.io.Serializable;

public class Attendance implements Serializable {


    private String employeeID;

    // @CreationTimestamp
    // @Column(updatable = false)
    // private LocalDate created_at;

    // @UpdateTimestamp
    // @Column(insertable = false)
    // private LocalDate updated_at;

    private String In_Time;

    private String Out_Time;

    private String Employee_Name;

    private String Date;


    private String Leave_Taken;

    private String Balance_Leave;

    private String Attendance;

    public String getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(String employeeID) {
        this.employeeID = employeeID;
    }

    public String getIn_Time() {
        return In_Time;
    }

    public void setIn_Time(String in_Time) {
        In_Time = in_Time;
    }

    public String getOut_Time() {
        return Out_Time;
    }

    public void setOut_Time(String out_Time) {
        Out_Time = out_Time;
    }

    public String getEmployee_Name() {
        return Employee_Name;
    }

    public void setEmployee_Name(String employee_Name) {
        Employee_Name = employee_Name;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getLeave_Taken() {
        return Leave_Taken;
    }

    public void setLeave_Taken(String leave_Taken) {
        Leave_Taken = leave_Taken;
    }

    public String getBalance_Leave() {
        return Balance_Leave;
    }

    public void setBalance_Leave(String balance_Leave) {
        Balance_Leave = balance_Leave;
    }

    public String getAttendance() {
        return Attendance;
    }

    public void setAttendance(String attendance) {
        Attendance = attendance;
    }

    @Override
    public String toString() {
        return "Attendance{" +
                "employeeID='" + employeeID + '\'' +
                ", In_Time='" + In_Time + '\'' +
                ", Out_Time='" + Out_Time + '\'' +
                ", Employee_Name='" + Employee_Name + '\'' +
                ", Date='" + Date + '\'' +
                ", Leave_Taken='" + Leave_Taken + '\'' +
                ", Balance_Leave='" + Balance_Leave + '\'' +
                ", Attendance='" + Attendance + '\'' +
                '}';
    }
}
