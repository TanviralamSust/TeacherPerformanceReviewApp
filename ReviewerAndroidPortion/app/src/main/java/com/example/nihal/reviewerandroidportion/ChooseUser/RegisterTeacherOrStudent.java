package com.example.nihal.reviewerandroidportion.ChooseUser;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.nihal.reviewerandroidportion.R;
import com.example.nihal.reviewerandroidportion.StudentProfile.StudentRegister;
import com.example.nihal.reviewerandroidportion.TeacherProfile.TeacherRegister;

public class  RegisterTeacherOrStudent extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_teacher_or_student);
        initializeClickListeners();
    }
    private void initializeClickListeners()
    {
        findViewById(R.id.teacher_registraion).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterTeacherOrStudent.this,TeacherRegister.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.student_registration).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterTeacherOrStudent.this,StudentRegister.class);
                startActivity(intent);
            }
        });

    }
}
