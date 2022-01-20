package com.example.attendancemanagement.api;

import java.io.Serializable;

public class Employee implements Serializable {

    private String employeeID;

    private String employeeName;

    private String employeeEmail;

    private String bloodGroup;

    private String address;


    private String contactNumber;

    private String designation;


    private String dateOfJoining;

    private String dateOfReleaving;

    private String salaryFixed;



    private String bankName;

    private String branch;

    private String ifscCode;

    private String emergencyContactNumber;

    private String accountNumber;

    public String getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(String employeeID) {
        this.employeeID = employeeID;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getEmployeeEmail() {
        return employeeEmail;
    }

    public void setEmployeeEmail(String employeeEmail) {
        this.employeeEmail = employeeEmail;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getDateOfJoining() {
        return dateOfJoining;
    }

    public void setDateOfJoining(String dateOfJoining) {
        this.dateOfJoining = dateOfJoining;
    }

    public String getDateOfReleaving() {
        return dateOfReleaving;
    }

    public void setDateOfReleaving(String dateOfReleaving) {
        this.dateOfReleaving = dateOfReleaving;
    }

    public String getSalaryFixed() {
        return salaryFixed;
    }

    public void setSalaryFixed(String salaryFixed) {
        this.salaryFixed = salaryFixed;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getIfscCode() {
        return ifscCode;
    }

    public void setIfscCode(String ifscCode) {
        this.ifscCode = ifscCode;
    }

    public String getEmergencyContactNumber() {
        return emergencyContactNumber;
    }

    public void setEmergencyContactNumber(String emergencyContactNumber) {
        this.emergencyContactNumber = emergencyContactNumber;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    @Override
    public String toString() {
        return "Employee [employeeID=" + employeeID + ", employeeName=" + employeeName + ", employeeEmail="
                + employeeEmail + ", bloodGroup=" + bloodGroup + ", address=" + address + ", contactNumber="
                + contactNumber + ", designation=" + designation + ", dateOfJoining=" + dateOfJoining
                + ", dateOfReleaving=" + dateOfReleaving + ", salaryFixed=" + salaryFixed + ", bankName=" + bankName
                + ", branch=" + branch + ", ifscCode=" + ifscCode + ", emergencyContactNumber=" + emergencyContactNumber
                + ", accountNumber=" + accountNumber + "]";
    }


}
