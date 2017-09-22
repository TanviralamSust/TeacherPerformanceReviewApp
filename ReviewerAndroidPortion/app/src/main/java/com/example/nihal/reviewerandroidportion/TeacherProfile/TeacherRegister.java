package com.example.nihal.reviewerandroidportion.TeacherProfile;

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

public class TeacherRegister extends AppCompatActivity implements HttpListener {
    private static final int REQUEST_EMAIL_VALIDATION = 1212;
    private EditText emailField,passwordField,deptField,firstField,lastField,userField;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_register);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        findAllViews();
        initClickListeners();
    }

    private void initClickListeners() {
        findViewById(R.id.teacher_register).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(emailField.getText().toString().trim().equals("") && passwordField.getText().toString().trim().equals("") && deptField.getText().toString().trim().equals("") && firstField.getText().toString().trim().equals("") && lastField.getText().toString().trim().equals("") && userField.getText().toString().trim().equals(""))
                        {
                            final Dialog dialog = new Dialog(TeacherRegister.this);
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

                        final String email = emailField.getText().toString();
                        final String pass = passwordField.getText().toString();
                        final  String username = userField.getText().toString();
                        final  String firstname = firstField.getText().toString();
                        final  String lastname = lastField.getText().toString();
                        final  String deptname = deptField.getText().toString();
                        Log.i("nihal9977","msg  ="+email);
                        Log.i("nihal9977","msg pass  ="+pass);
                        Log.i("nihal997","msg user ="+username);
                        Log.i("nihal997","msg  first="+firstname);
                        Log.i("nihal997","msg  last="+lastname);
                        Log.i("nihal997","msg  dept="+deptname);
                        final Dialog dialog = new Dialog(TeacherRegister.this);
                        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                        dialog.setContentView(R.layout.dailogeone);
                        dialog.findViewById(R.id.ok).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.dismiss();
                            }
                        });
                if(!TeacherRegister.isValidEmailAddress(email))
                {
                    final Dialog dialog2 = new Dialog(TeacherRegister.this);
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
                    final Dialog confDialog = new Dialog(TeacherRegister.this);
                    confDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    confDialog.setContentView(R.layout.dailogeregistration);
                    TextView emailField = (TextView) confDialog.findViewById(R.id.email);
                    emailField.setText(email+" is this correct ?");
                    confDialog.findViewById(R.id.ok).setOnClickListener( new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            HttpRequest request = new HttpRequest("http://10.100.173.39:8084/DataBase-Hibernate/LoginServlet?"+"tag=register"+"&userType=teacher"+"&userName="+username+"&email="+email+"&password="+pass+"&department="+deptname+"&firstName="+firstname+"&lastName="+lastname+"&device=android",REQUEST_EMAIL_VALIDATION,TeacherRegister.this);
                            request.execute("post");
                            confDialog.dismiss();
                            // request.setRawJson("email",email);
                            // request.setRawJson("tag", "register");
                           /* request.setParams(
                                    new KeyValue("tag","register"),
                                    new KeyValue("device","android"),
                                    new KeyValue("firstName",firstname),
                                    new KeyValue("lastName",lastname),
                                    new KeyValue("department",deptname),
                                    new KeyValue("email",email),
                                    new KeyValue("userName",username),
                                    new KeyValue("password",pass),
                                    new KeyValue("userType","teacher")
                                    );*/
                            /*request.setParams(

                                    new KeyValue("tag","register"),
                                    new KeyValue("device","android"),
                                    //new KeyValue("firstName",firstname),
                                    new KeyValue("firstName","tss"),
                                    new KeyValue("lastName","tkk"),
                                    new KeyValue("department","cse"),
                                    new KeyValue("email","t@gmail.com"),
                                    new KeyValue("userName","tdh997"),
                                    new KeyValue("password","shah"),
                                    new KeyValue("userType","teacher"));
                            request.urlencoded(true);*/
                           // request.execute("post");
                            //confDialog.dismiss();
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
    private void findAllViews()
    {
        userField = (EditText)findViewById(R.id.user_name);
        emailField = (EditText) findViewById(R.id.email_te);
        passwordField = (EditText) findViewById(R.id.password_te);
        deptField = (EditText)findViewById(R.id.department_te);
        firstField =(EditText)findViewById(R.id.first_name);
        lastField = (EditText)findViewById(R.id.last_name);

    }

    @Override
    public void respond(String jsonRespond, int respondId, HttpRequest parent) {
        Log.i("nihal","url  ="+ urls.domainAddress);
        Log.i("tanvir","msg  ="+ jsonRespond);
        Toast.makeText(TeacherRegister.this,"response recieved "+jsonRespond,Toast.LENGTH_LONG).show();

            if(jsonRespond.equals("Register Successful"))
            {
                Intent intent = new Intent(TeacherRegister.this,TeacherHomeScreen.class);
                intent.putExtra("email",emailField.getText().toString());
                intent.putExtra("password",passwordField.getText().toString());
                intent.putExtra("dept",deptField.getText().toString());
                startActivity(intent);
                finish();
            }
            else
            {
                final Dialog dialog = new Dialog(TeacherRegister.this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.dailogeerror_registration);
                dialog.findViewById(R.id.ok).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {dialog.dismiss();
                    }
                });
                dialog.show();

            }

    }

    @Override
    public void errorRespond(VolleyError e, int respondId, HttpRequest parent) {
        Log.i("nihal","timeout =");
        if(e instanceof NoConnectionError)
        {
            Toast.makeText(TeacherRegister.this,"No internet connection",Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public Context getContext() {
        return TeacherRegister.this;
    }
    public static boolean isValidEmailAddress(String email) {
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(email);
        return m.matches();
    }
}
