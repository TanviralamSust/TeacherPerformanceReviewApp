package com.example.nihal.reviewerandroidportion.TeacherProfile;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
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

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static android.R.id.list;

public class SemisterList extends AppCompatActivity implements Response.ErrorListener, Response.Listener<String>{

   private String semisterNum;
    public ListView list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_semister_list);
        semisterNum = getIntent().getExtras().getString("semister");
        String url = "http://10.100.1.122:8084/TeachersReview/FirstServlet";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, this, this){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String>  params = new HashMap<String, String>();
                params.put("tag","course");
                params.put("dept","cse");
                params.put("semester",semisterNum);
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
        list = (ListView)findViewById(R.id.course_list);
        myadapter adapter =new myadapter(getApplicationContext(), pj.courses);
        //new CustomList(this, ParseJSON.ids,ParseJSON.names,ParseJSON.emails);
        list.setAdapter(adapter);
    }
}
class myadapter extends ArrayAdapter {
    ArrayList<GsonPerser> journeyArray;
    Context context;
    public myadapter(Context c, ArrayList<GsonPerser> journeyArray) {
        super(c, R.layout.activity_semister_list);
        this.context = c;
        this.journeyArray = journeyArray;
        Log.i("tanvir99","on adapter constructor");
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

        courseTitel.setText(journeyArray.get(position).getCourseName());
        Log.i("tanvir99","on adapter getview");
        Log.i("tanvir9977","Json print Titel"+journeyArray.get(position).getCourseName());
        //imageLoader.displayImage(journeyArray.get(position).getImage(), journeyImage);
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.v("nihal", "ROW PRESSED");
                context.startActivity(new Intent(context, TeacherCourseKey.class));
            }
        });


        return convertView;
    }

}