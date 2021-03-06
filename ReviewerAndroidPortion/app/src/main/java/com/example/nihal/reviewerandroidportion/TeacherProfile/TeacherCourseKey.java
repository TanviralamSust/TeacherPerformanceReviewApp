package com.example.nihal.reviewerandroidportion.TeacherProfile;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.NoConnectionError;
import com.android.volley.VolleyError;
import com.example.nihal.reviewerandroidportion.R;
import com.example.nihal.reviewerandroidportion.httprequest.HttpListener;
import com.example.nihal.reviewerandroidportion.httprequest.HttpRequest;
import com.example.nihal.reviewerandroidportion.httprequest.urls;

import org.json.JSONException;
import org.json.JSONObject;

public class TeacherCourseKey extends AppCompatActivity implements HttpListener{
    private static final int REQUEST_KEY = 1212;
    private Button Submitkey;
    private EditText teacherKeyfield;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_course_key);
        findAllViews();
        initClickListener();
    }

    private void initClickListener() {
        findViewById(R.id.teacher_key_submit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(teacherKeyfield.getText().toString().trim().equals("") )
                {
                    final Dialog dialog = new Dialog(TeacherCourseKey.this);
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

                final String coursekey = teacherKeyfield.getText().toString();
                final Dialog dialog = new Dialog(TeacherCourseKey.this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.dailogeone);
                dialog.findViewById(R.id.ok).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                if(!TeacherCourseKey.isValidCourseKey(coursekey))
                {
                    final Dialog dialog2 = new Dialog(TeacherCourseKey.this);
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
                    final Dialog confDialog = new Dialog(TeacherCourseKey.this);
                    confDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    confDialog.setContentView(R.layout.dailogeregistration);
                    TextView teacherKeyfield = (TextView) confDialog.findViewById(R.id.teacher_course_key);
                    teacherKeyfield.setText(coursekey+" is this correct ?");
                    confDialog.findViewById(R.id.ok).setOnClickListener( new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            HttpRequest request = new HttpRequest(urls.domainAddress+""+"?"+"tag=uniqueKey"+"&type=teacher"+"&courseKey="+coursekey+"&device=android",REQUEST_KEY ,TeacherCourseKey.this);
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
    private void findAllViews()
    {
        teacherKeyfield = (EditText) findViewById(R.id.teacher_course_key);

    }


    @Override
    public void respond(String jsonRespond, int respondId, HttpRequest parent) {
        Log.i("nihal","url  ="+ urls.domainAddress);
        Log.i("nihal","msg  ="+ jsonRespond);
        try {
            String isValidUniqueKey = new JSONObject(jsonRespond).getString("status");
            Log.i("nihal","msg  ="+ isValidUniqueKey);
            if(isValidUniqueKey.equals("keytaken sucessful"))
            {
                Intent intent = new Intent(TeacherCourseKey.this,TeacherHomeScreen.class);
                intent.putExtra("uniqueKey",teacherKeyfield.getText().toString());
                Toast.makeText(TeacherCourseKey.this,"thank you... key save",Toast.LENGTH_LONG).show();
                startActivity(intent);
                finish();
            }
            else
            {
                final Dialog dialog = new Dialog(TeacherCourseKey.this);
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

        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void errorRespond(VolleyError e, int respondId, HttpRequest parent) {
        Log.i("nihal","timeout =");
        if(e instanceof NoConnectionError)
        {
            Toast.makeText(TeacherCourseKey.this,"No internet connection",Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public Context getContext() {
        return TeacherCourseKey.this;
    }
    public static boolean isValidCourseKey(String courseKey) {
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(courseKey);
        return m.matches();
    }
}
