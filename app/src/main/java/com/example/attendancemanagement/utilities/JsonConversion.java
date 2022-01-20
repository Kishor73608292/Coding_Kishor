package com.example.attendancemanagement.utilities;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JsonConversion {

    private JsonConversion(){

    }
    public  static  JsonConversion jsonConversion;
    public static JsonConversion getInstance(){
        if (jsonConversion==null){
            synchronized (JsonConversion.class){
                jsonConversion=new JsonConversion();
            }
        }
        return jsonConversion;
    }

    public  Object stringToObject(String jsonString, Type type) {

//        Gson gson;
//
//        Log.e("","===========>jsonString"+jsonString);
//        try
//        {
//            gson = new Gson().fromJson(jsonString, type);
//            return new Gson().fromJson(jsonString, type);
//        }catch(Exception e){
//            e.printStackTrace();
//        }
        return new Gson().fromJson(jsonString, type);
    }
    public  String objectToString(Object object) {
        return new Gson().toJson(object);
    }

    public String getJsonStrFromResult(String response){
        String result="";
        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONObject obj = jsonObject.getJSONObject("result");
            result=obj.toString();

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return result;
    }
    public JSONArray getJsonStrFromResultList(String response){
        String result="";
        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray jsonArray = jsonObject.getJSONArray("result");
            return jsonArray;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
    public String getStrFromResult(String response){
        String result="";
        try {
            JSONObject jsonObject = new JSONObject(response);
            String obj = String.valueOf(jsonObject.get("result"));
            result=obj.toString();

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return result;
    }
    public String getErrorFromResult(String response){
        String result="";
        try {
            JSONObject jsonObject = new JSONObject(response);
            String obj = String.valueOf(jsonObject.get("result"));
            result=obj.toString();

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return result;
    }

    public List<String> fromCommaSeperateStr(String CommaSeparated){
        List<String> items = new ArrayList<String>(Arrays.asList(CommaSeparated.split("\\s*,\\s*")));
        return items;
    }
}
