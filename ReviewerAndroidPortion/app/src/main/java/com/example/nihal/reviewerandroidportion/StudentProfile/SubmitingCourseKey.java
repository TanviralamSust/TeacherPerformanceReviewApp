package com.example.nihal.reviewerandroidportion.StudentProfile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.nihal.reviewerandroidportion.R;

public class SubmitingCourseKey extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submiting_course_key);
    }

    public void keysubmit(View view) {
            Intent intent = new Intent(SubmitingCourseKey.this,CourseNewRating.class);
            startActivity(intent);
    }
}
