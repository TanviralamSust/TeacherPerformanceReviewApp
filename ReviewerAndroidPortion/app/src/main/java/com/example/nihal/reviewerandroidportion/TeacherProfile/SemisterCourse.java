package com.example.nihal.reviewerandroidportion.TeacherProfile;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.nihal.reviewerandroidportion.R;

public class SemisterCourse extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_semister_course);
    }

    public void mis(View view) {
        Intent intent = new Intent(SemisterCourse.this,TeacherNewCourseKey.class);
        startActivity(intent);
    }

    public void datacom(View view) {
        Intent intent = new Intent(SemisterCourse.this,TeacherNewCourseKey.class);
        startActivity(intent);
    }

    public void micro(View view) {
        Intent intent = new Intent(SemisterCourse.this,TeacherNewCourseKey.class);
        startActivity(intent);
    }

    public void operating(View view) {
        Intent intent = new Intent(SemisterCourse.this,TeacherNewCourseKey.class);
        startActivity(intent);
    }

    public void database(View view) {
        Intent intent = new Intent(SemisterCourse.this,TeacherNewCourseKey.class);
        startActivity(intent);
    }
}
