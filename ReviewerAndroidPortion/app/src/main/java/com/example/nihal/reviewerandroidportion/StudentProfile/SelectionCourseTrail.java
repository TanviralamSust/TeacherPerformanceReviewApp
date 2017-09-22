package com.example.nihal.reviewerandroidportion.StudentProfile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.nihal.reviewerandroidportion.R;


public class SelectionCourseTrail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection_course_trail);
    }

    public void databaseclicked(View view) {
        Intent intent = new Intent(SelectionCourseTrail.this,SubmitingCourseKey.class);
        startActivity(intent);
    }

    public void datacomclicked(View view) {
        Intent intent = new Intent(SelectionCourseTrail.this,SubmitingCourseKey.class);
        startActivity(intent);
    }

    public void microclicked(View view) {
        Intent intent = new Intent(SelectionCourseTrail.this,SubmitingCourseKey.class);
        startActivity(intent);
    }


    public void operatingSystem(View view) {

        Intent intent = new Intent(SelectionCourseTrail.this,SubmitingCourseKey.class);
        startActivity(intent);
    }
}