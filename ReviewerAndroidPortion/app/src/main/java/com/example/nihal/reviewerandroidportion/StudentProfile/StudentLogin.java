package com.example.nihal.reviewerandroidportion.StudentProfile;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.NoConnectionError;
import com.android.volley.VolleyError;
import com.example.nihal.reviewerandroidportion.R;
import com.example.nihal.reviewerandroidportion.httprequest.HttpListener;
import com.example.nihal.reviewerandroidportion.httprequest.HttpRequest;

public class StudentLogin extends AppCompatActivity implements HttpListener{

    private static final int REQUEST_LOGIN = 1212;
    private Button Submit;
    private EditText userField,passwordField;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        findAllViews();
        initClickListener();

    }
    private void initClickListener() {
        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String user = userField.getText().toString();
                String pass = passwordField.getText().toString();
              /*  if(!TeacherRegister.isValidEmailAddress(user))
                {
                    final Dialog dialog = new Dialog(TeacherLogin.this);
                    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    dialog.setContentView(R.layout.dailogeerror_login);
                    dialog.findViewById(R.id.ok).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();
                        }
                    });
                    dialog.show();

                    return;
                }*/

                if(userField.getText().toString().trim().equals("") || passwordField.getText().toString().trim().equals(""))
                {
                    final Dialog dialog = new Dialog(StudentLogin.this);
                    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    dialog.setContentView(R.layout.dailogeone);
                    dialog.findViewById(R.id.ok).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();
                        }
                    });
                    dialog.show();
                    return;
                }//tag=login&user=tanviralam&pass=nihal
                HttpRequest request = new HttpRequest("http://10.100.173.39:8084/DataBase-Hibernate/LoginServlet?"+"tag=login"+"&userType=student"+"&userName="+user+"&password="+pass+"&device=android",REQUEST_LOGIN,StudentLogin.this);
                request.execute("post");

            }
        });
    }
    private void findAllViews()
    {
        Submit = (Button) findViewById(R.id.login);
        userField = (EditText) findViewById(R.id.user_id_tea);
        passwordField = (EditText) findViewById(R.id.password_te);

    }

    @Override
    public void respond(String jsonRespond, int respondId, HttpRequest parent) {
        Toast.makeText(StudentLogin.this,"response recieved "+jsonRespond,Toast.LENGTH_LONG).show();
        if(jsonRespond.equals("Login successful"))
        {
            Intent intent = new Intent(StudentLogin.this,SubmitingCourseKey.class);
            //intent.putExtra("email",emailField.getText().toString());
            //intent.putExtra("password",passwordField.getText().toString());
            //intent.putExtra("dept",deptField.getText().toString());
            startActivity(intent);
            finish();
        }
        else
        {
            final Dialog dialog = new Dialog(StudentLogin.this);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(R.layout.dailogeerror);
            dialog.findViewById(R.id.ok).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });
            dialog.show();

        }
    }

    @Override
    public void errorRespond(VolleyError e, int respondId, HttpRequest parent) {
        Log.i("tanvir","loginout =");
        if(e instanceof NoConnectionError)
        {
            Toast.makeText(StudentLogin.this,"No Connection",Toast.LENGTH_LONG).show();
        }
        else
        {
            Toast.makeText(StudentLogin.this,"invalid",Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public Context getContext() {
        return StudentLogin.this;
    }
}
