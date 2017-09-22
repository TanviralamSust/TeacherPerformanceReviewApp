package com.example.nihal.reviewerandroidportion.TeacherProfile;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.nihal.reviewerandroidportion.R;

public class TeacherNewCourseKey extends AppCompatActivity implements Response.Listener<String>, Response.ErrorListener {
    private EditText keyField;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_new_course_key);
        keyField = (EditText) findViewById(R.id.unique_key);
    }

    public void setcourseKey(View view) {
        String courseUniqueKey = keyField.getText().toString();

        //String url = "http://27.147.231.8:7228/DataBase-Hibernate/CourseComponent?"+"tag=setKey"+"&department=ICT"+"&semester:1-2+"+"courseCode:CSE1001t"+"courseName:Project+Work"+"&key=123&device=android";
        String url1 ="http://10.100.173.39:8084/DataBase-Hibernate/CourseComponent?tag=setKey&device=android&department=ICT&semester=1-2&courseCode=CSE331&courseName=DatabaseManagement"+"&key="+courseUniqueKey;
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url1, this, this){

            /*
         //for form data sending//
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String>  params = new HashMap<String, String>();
                params.put("domain",article.getDomain());
                params.put("apiKey",article.getApiKey());
                params.put("userType", article.getUserType());
                params.put("isStory","0");
                params.put("limit","4");
                return params();
            }*/
        };

        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());//problem//
        queue.add(stringRequest);
        Toast.makeText(TeacherNewCourseKey.this," req send wait",Toast.LENGTH_LONG).show();
        Log.i("request",stringRequest.toString());

    }

    @Override
    public void onResponse(String response) {
        Toast.makeText(TeacherNewCourseKey.this, "Request Recieve" + response, Toast.LENGTH_LONG).show();
        if (response.equals("inserted")) {
            Intent intent = new Intent(TeacherNewCourseKey.this, TeacherHomeScreen.class);
            //intent.putExtra("email",emailField.getText().toString());
            //intent.putExtra("password",passwordField.getText().toString());
            //intent.putExtra("dept",deptField.getText().toString());
            startActivity(intent);
            finish();
        }
    }
    @Override
    public void onErrorResponse(VolleyError error) {

        Toast.makeText(TeacherNewCourseKey.this,"no req",Toast.LENGTH_LONG).show();
    }
}
