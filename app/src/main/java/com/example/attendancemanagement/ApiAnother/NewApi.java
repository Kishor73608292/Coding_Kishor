package com.example.attendancemanagement.ApiAnother;

public class NewApi {

    //private static final String ROOT_URL = "http://192.168.1.187/user_details/v1/Api.php?apicall=";

    private static final String ROOT_URL = "http://192.168.1.179:8080/AddUsers";

    //private static final String ROOT_URL = "http://localhost/user_details/v1/Api.php?apicall=";


    //private static final String ROOT_URL = "http://10.0.2.2/user_details/v1/Api.php?apicall=";

    //public static final String URL_CREATE_USER = ROOT_URL + "createuser";


    public static final String URL_CREATE_USER = ROOT_URL;


    public static final String URL_SELECT_USER = ROOT_URL + "getsingleuser";

    public static final String URL_CREATE_COLOR = ROOT_URL + "createcolor";

    public static final String URL_CREATE_PROPERTY = ROOT_URL + "createproperty";


    public static final String URL_DELETE_COLOR = ROOT_URL + "deletecolor";

    public static final String URL_DELETE_PROPERTY = ROOT_URL + "deleteproperty";


    public static final String URL_GET_PROPERTY = ROOT_URL + "getproperties";

    public static final String URL_GET_COLORS = ROOT_URL + "getcolors";

}
