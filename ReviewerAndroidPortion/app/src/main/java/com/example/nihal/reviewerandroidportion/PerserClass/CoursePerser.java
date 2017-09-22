package com.example.nihal.reviewerandroidportion.PerserClass;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Nihal on 2/5/2017.
 */

public class CoursePerser {
    public  ArrayList<GsonPerser> courses = new ArrayList<>();
    public static final String JSON_ARRAY = "Semistercourse";
    public static final String KEY_COURSE = "course";
    private JSONArray users = null;

    public String json;

    public CoursePerser(String json) {
        //json recieved for persing here
        this.json = json;
        Log.i("nihal", "Json ahakjprint" + this.json);
        Log.i("nihal", "Json print" + json);
    }

    public void courseSemister(String json) {
        JSONObject jsonObject = null;
        try {
            //asigning the json object to it//
            JSONObject recievedJson = new JSONObject(json);
            JSONObject responseJson = recievedJson.getJSONObject("jsonResponse");
            users = responseJson.getJSONArray(JSON_ARRAY);
            for (int i = 0; i < users.length(); i++) {
                JSONObject jo = users.getJSONObject(i);
                //json objectof the perserclass and setting values for each field of that class//
                GsonPerser journey = new GsonPerser();
                journey.setCourseName(jo.getString(KEY_COURSE));
                courses.add(journey);
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}