package com.example.nihal.reviewerandroidportion.StudentProfile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.nihal.reviewerandroidportion.R;
import com.example.nihal.reviewerandroidportion.TeacherProfile.SemisterShow;
import com.example.nihal.reviewerandroidportion.TeacherProfile.TeacherHomeScreen;

public class StudentNewHomeScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_new_home_screen);
    }

    public void semisterChooseStu(View view) {
        Intent i = new Intent(StudentNewHomeScreen.this, StudentSemisterShow.class);
        startActivity(i);
    }

    public void statisticsShowStu(View view) {
    }
}
