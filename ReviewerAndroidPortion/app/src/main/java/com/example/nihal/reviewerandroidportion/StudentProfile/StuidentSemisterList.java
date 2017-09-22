package com.example.nihal.reviewerandroidportion.StudentProfile;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.nihal.reviewerandroidportion.PerserClass.CoursePerser;
import com.example.nihal.reviewerandroidportion.PerserClass.GsonPerser;
import com.example.nihal.reviewerandroidportion.R;
import com.example.nihal.reviewerandroidportion.TeacherProfile.TeacherCourseKey;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class StuidentSemisterList extends AppCompatActivity implements Response.ErrorListener, Response.Listener<String>{

    private String semisterNum;
    public ListView listview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stuident_semister_list);
        semisterNum = getIntent().getExtras().getString("semister");
        String url = "http://10.100.1.122:8084/TeachersReview/FirstServlet";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, this, this){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String>  params = new HashMap<String, String>();
                params.put("tag","course");
                params.put("dept","cse");
                params.put("semester",semisterNum);
                params.put("device","android");
                return params;
                //return param();
            }
        };
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());//problem//
        queue.add(stringRequest);
        Log.i("request",stringRequest.toString());
        //return SemisterList;
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Log.i("nihal","Jsonfragmenterror" +error);
    }

    @Override
    public void onResponse(String response) {
        Log.i("nihal","Json fragment response"+response);
        showJSON(response);
    }
    public void showJSON(String json) {
        //journeyperserjson class er object banay journeyperserjson method ke call korte hbe//
        Log.i("tanvirn","Json fragment method response"+json);
        CoursePerser pj = new CoursePerser (json);
        pj.courseSemister(json);
        listview = (ListView)findViewById(R.id.course_list_student);
        myadapterstu adapter =new myadapterstu(getApplicationContext(), pj.courses);
        //new CustomList(this, ParseJSON.ids,ParseJSON.names,ParseJSON.emails);
        listview.setAdapter(adapter);
    }
    }
class myadapterstu extends ArrayAdapter {
    ArrayList<GsonPerser> journeyArray1;
    Context context;

    public myadapterstu(Context c, ArrayList<GsonPerser> journeyArray1) {
        super(c, R.layout.activity_stuident_semister_list);
        this.context = c;
        this.journeyArray1 = journeyArray1;
        Log.i("tanvir99", "on adapter constructor");
    }

    @Override
    public int getCount() {
        return 2;
    }
    public View getView(int position, View convertView, ViewGroup parent) {
        //ImageLoader imageLoader = ImageLoader.getInstance();
        if (convertView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.single_list, parent, false);
        }
        TextView courseTitel = (TextView) convertView.findViewById(R.id.subject_name);

        courseTitel.setText(journeyArray1.get(position).getCourseName());
        Log.i("tanvir99","on adapter getview");
        Log.i("tanvir9977","Json print Titel"+journeyArray1.get(position).getCourseName());
        //imageLoader.displayImage(journeyArray.get(position).getImage(), journeyImage);
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.v("nihal", "ROW PRESSED");
                context.startActivity(new Intent(context, StudentPutKey.class));
            }
        });


        return convertView;
    }
}