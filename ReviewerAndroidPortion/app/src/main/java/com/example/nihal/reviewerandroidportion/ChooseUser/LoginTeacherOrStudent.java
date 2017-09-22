package com.example.nihal.reviewerandroidportion.ChooseUser;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.nihal.reviewerandroidportion.R;
import com.example.nihal.reviewerandroidportion.StudentProfile.StudentLogin;
import com.example.nihal.reviewerandroidportion.TeacherProfile.TeacherLogin;

public class LoginTeacherOrStudent extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_teacher_or_student);
        initializeClickListeners();
    }
    private void initializeClickListeners()
    {
        findViewById(R.id.teacher_login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginTeacherOrStudent.this,TeacherLogin.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.student_login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginTeacherOrStudent.this,StudentLogin.class);
                startActivity(intent);
            }
        });

    }
}
