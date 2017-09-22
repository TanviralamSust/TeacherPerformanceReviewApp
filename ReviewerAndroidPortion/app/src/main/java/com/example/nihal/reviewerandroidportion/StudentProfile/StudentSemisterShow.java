package com.example.nihal.reviewerandroidportion.StudentProfile;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.nihal.reviewerandroidportion.R;

public class StudentSemisterShow extends AppCompatActivity {
    public String semisterNum;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_semister_show);
    }
    public void semisterFourTwo(View view) {
        semisterNum = "4-2";
        Intent i = new Intent(StudentSemisterShow.this, StuidentSemisterList.class);
        i.putExtra("semister",semisterNum.toString());
        startActivity(i);
    }

    public void semisterOneOne(View view) {
        semisterNum = "1-1";
        Intent i = new Intent(StudentSemisterShow.this, StuidentSemisterList.class);
        i.putExtra("semister",semisterNum.toString());
        startActivity(i);
    }

    public void semisterOneTwo(View view) {
        semisterNum = "1-2";
        Intent i = new Intent(StudentSemisterShow.this, StuidentSemisterList.class);
        i.putExtra("semister",semisterNum.toString());
        startActivity(i);
    }

    public void semisterTwoOne(View view) {
        semisterNum = "2-1";
        Intent i = new Intent(StudentSemisterShow.this, StuidentSemisterList.class);
        i.putExtra("semister",semisterNum.toString());
        startActivity(i);
    }

    public void semisterTwoTwo(View view) {
        semisterNum = "2-2";
        Intent i = new Intent(StudentSemisterShow.this, StuidentSemisterList.class);
        i.putExtra("semister",semisterNum.toString());
        startActivity(i);
    }

    public void semisterThreeOne(View view) {
        semisterNum = "3-1";
        Intent i = new Intent(StudentSemisterShow.this, StuidentSemisterList.class);
        i.putExtra("semister",semisterNum.toString());
        startActivity(i);
    }

    public void semisterThreeTwo(View view) {
        semisterNum = "3-2";
        Intent i = new Intent(StudentSemisterShow.this, StuidentSemisterList.class);
        i.putExtra("semister",semisterNum.toString());
        startActivity(i);
    }

    public void semisterFourOne(View view) {
        semisterNum = "4-1";
        Intent i = new Intent(StudentSemisterShow.this, StuidentSemisterList.class);
        i.putExtra("semister",semisterNum.toString());
        startActivity(i);

    }

    public void mis(View view) {
    }
}
