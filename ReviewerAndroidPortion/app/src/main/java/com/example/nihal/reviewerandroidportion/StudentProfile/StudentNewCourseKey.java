package com.example.nihal.reviewerandroidportion.StudentProfile;

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

public class StudentNewCourseKey extends AppCompatActivity implements Response.ErrorListener, Response.Listener<String> {
    private EditText stukeyField;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_new_course_key);
        stukeyField = (EditText) findViewById(R.id.stu_unique_key);
    }

    public void sut_put_courseKey(View view) {

        String stuCourseUniqueKey = stukeyField.getText().toString();
        String url1 ="http://10.100.173.39:8084/DataBase-Hibernate/ReviewServlet?tag=check_key&device=android&semester=1-2&courseCode=CSE331"+"&key="+stuCourseUniqueKey;
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
        Toast.makeText(StudentNewCourseKey.this," req send wait",Toast.LENGTH_LONG).show();
        Log.i("request",stringRequest.toString());


    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(StudentNewCourseKey.this,"no req",Toast.LENGTH_LONG).show();
    }

    @Override
    public void onResponse(String response) {
        Toast.makeText(StudentNewCourseKey.this, "Request Recieve" + response, Toast.LENGTH_LONG).show();
        if (response.equals("inserted")) {
            Intent intent = new Intent(StudentNewCourseKey.this, StudentNewHomeScreen.class);
            //intent.putExtra("email",emailField.getText().toString());
            //intent.putExtra("password",passwordField.getText().toString());
            //intent.putExtra("dept",deptField.getText().toString());
            startActivity(intent);
            finish();
        }
    }
}
