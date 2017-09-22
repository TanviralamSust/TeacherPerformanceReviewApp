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
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.NoConnectionError;
import com.android.volley.VolleyError;
import com.example.nihal.reviewerandroidportion.R;
import com.example.nihal.reviewerandroidportion.httprequest.HttpListener;
import com.example.nihal.reviewerandroidportion.httprequest.HttpRequest;
import com.example.nihal.reviewerandroidportion.httprequest.urls;

public class StudentRegister extends AppCompatActivity implements HttpListener {
    EditText emailField,passwordField ,deptField,regField,userNameField;
    private static final int REQUEST_EMAIL_VALIDATION = 1212;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_register);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        findAllViews();
        initClickListeners();

    }



    private void findAllViews() {
        emailField = (EditText) findViewById(R.id.email_stu);
        passwordField = (EditText) findViewById(R.id.password_stu);
        deptField= (EditText) findViewById(R.id.department_stu);
        regField = (EditText) findViewById(R.id.registration_num_stu);
        userNameField=(EditText) findViewById(R.id.user_id_stu);

    }
    private void initClickListeners() {
        findViewById(R.id.student_account_reg).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(emailField.getText().toString().trim().equals("") && passwordField.getText().toString().trim().equals("") && deptField.getText().toString().trim().equals("") && regField.getText().toString().trim().equals(""))
                {
                    final Dialog dialog = new Dialog(StudentRegister.this);
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
                }

                final String userEmail = emailField.getText().toString();
                Log.i("nihal997","msg  ="+ userEmail);
                final String pass = passwordField.getText().toString();
                Log.i("nihal997","msg  ="+ pass);
                final  String userName = userNameField.getText().toString();
                final  String dept = deptField.getText().toString();
                final  String reg = regField.getText().toString();
                final Dialog dialog = new Dialog(StudentRegister.this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.dailogeone);
                dialog.findViewById(R.id.ok).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                if(!StudentRegister.isValidEmailAddress(userEmail))
                {
                    final Dialog dialog2 = new Dialog(StudentRegister.this);
                    dialog2.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    dialog2.setContentView(R.layout.dailogeerror_login);
                    dialog2.findViewById(R.id.ok).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog2.dismiss();
                        }
                    });
                    dialog2.show();

                    return;
                }
                else
                {
                    final Dialog confDialog = new Dialog(StudentRegister.this);
                    confDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    confDialog.setContentView(R.layout.dailogeregistration);
                    TextView emailField = (TextView) confDialog.findViewById(R.id.email);
                    emailField.setText(userEmail+" is this correct ?");
                    confDialog.findViewById(R.id.ok).setOnClickListener( new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            HttpRequest request = new HttpRequest("http://10.100.173.39:8084/DataBase-Hibernate/LoginServlet?"+"tag=register"+"&userType=student"+"&userName="+userName+"&email="+userEmail+"&password="+pass+"&department="+dept+"&reg="+reg+"&device=android",REQUEST_EMAIL_VALIDATION,StudentRegister.this);
                            // request.setRawJson("email",email);
                            // request.setRawJson("tag", "register");
                            request.execute("post");
                            confDialog.dismiss();
                        }
                    });
                    confDialog.findViewById(R.id.cancel).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            confDialog.dismiss();
                        }
                    });
                    confDialog.show();


                }
            }
        });

    }

    @Override
    public void respond(String jsonRespond, int respondId, HttpRequest parent) {
        Toast.makeText(StudentRegister.this,"response recieved ",Toast.LENGTH_LONG).show();
        Log.i("nihal","url  ="+ urls.domainAddress);
        Log.i("nihal","msg  ="+ jsonRespond);
        try {

           // Log.i("nihal","msg  ="+ isValidEmail);
            if(jsonRespond.equals("Register Successful"))
            {
                Intent intent = new Intent(StudentRegister.this,SubmitingCourseKey.class);
                intent.putExtra("email",emailField.getText().toString());
                intent.putExtra("password",passwordField.getText().toString());
                intent.putExtra("dept",deptField.getText().toString());
                intent.putExtra("reg",regField.getText().toString());
                startActivity(intent);
                finish();
            }
            else
            {
                final Dialog dialog = new Dialog(StudentRegister.this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.dailogeerror_registration);
                dialog.findViewById(R.id.ok).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                dialog.show();

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void errorRespond(VolleyError e, int respondId, HttpRequest parent) {
        Log.i("nihal","timeout =");
        if(e instanceof NoConnectionError)
        {
            Toast.makeText(StudentRegister.this,"No internet connection",Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public Context getContext() {
        return StudentRegister.this;
    }
    public static boolean isValidEmailAddress(String email) {
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(email);
        return m.matches();
    }
}
