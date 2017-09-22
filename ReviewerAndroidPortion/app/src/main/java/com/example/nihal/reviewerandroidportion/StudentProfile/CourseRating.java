package com.example.nihal.reviewerandroidportion.StudentProfile;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.example.nihal.reviewerandroidportion.R;
import com.example.nihal.reviewerandroidportion.httprequest.HttpListener;
import com.example.nihal.reviewerandroidportion.httprequest.HttpRequest;

public class CourseRating extends AppCompatActivity implements HttpListener {
    Button submitButton;
    private static final int REQUEST_EMAIL_VALIDATION = 1212;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_rating);
        findAllViews();
        initClickListener();
    }

    private void findAllViews() {
        submitButton = (Button) findViewById(R.id.submit_from);
    }


    private void initClickListener() {

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HttpRequest request = new HttpRequest("http://10.100.4.221:8084/DataBase-Hibernate/ReviewServlet?tag=review&courseCode=cse3331&userName=a&semester=3-1&q1=1&q2=2&q3=4&device=android", REQUEST_EMAIL_VALIDATION, CourseRating.this);
                request.execute("post");
                Toast.makeText(CourseRating.this, "wait...., thank you.", Toast.LENGTH_SHORT).show();

            }

        });
    }

    @Override
    public void respond(String jsonRespond, int respondId, HttpRequest parent) {
        Toast.makeText(CourseRating.this,"Request Recieve"+jsonRespond,Toast.LENGTH_LONG).show();
    }

    @Override
    public void errorRespond(VolleyError e, int respondId, HttpRequest parent) {
        Toast.makeText(CourseRating.this,"no req",Toast.LENGTH_LONG).show();
    }

    @Override
    public Context getContext() {
        return null;
    }
}
