package com.example.nihal.reviewerandroidportion.TeacherProfile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.nihal.reviewerandroidportion.R;

public class TeacherHomeScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_select);
    }

    public void semisterChoose(View view) {
        Intent i = new Intent(TeacherHomeScreen.this, SemisterShow.class);
        startActivity(i);

    }

    public void statisticsShow(View view) {
    }
}
