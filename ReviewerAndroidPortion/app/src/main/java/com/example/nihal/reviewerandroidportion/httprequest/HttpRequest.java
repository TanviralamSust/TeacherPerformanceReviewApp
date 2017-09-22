package com.example.nihal.reviewerandroidportion.httprequest;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.nihal.reviewerandroidportion.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Siyam on 26-Dec-15.
 */
public class HttpRequest {

    private KeyValue[] params,headers;
    private String url;
    private boolean urlencoded;
    private int reqId;
    private HttpListener activity;
    private Bundle bundle;
    private final int request_time_out=8000;
    private Dialog dialog;

    private JSONObject jsonData;



    public HttpRequest(String url, int requestId, HttpListener mActivity) {
        this.url =url;
        reqId =requestId;
        activity =mActivity;
        jsonData = new JSONObject();
        initRequestJSON();

    }
    private void initRequestJSON()
    {
        try {
            jsonData.put("apiKey","1df5ea94-64f8-11e6-8b77-86f30ca893d3");
            jsonData.put("domain","parentingkit.thewomens.org.au");
            jsonData.put("devicePlatform","Android");
            jsonData.put("appVersion","1.0");
            jsonData.put("deviceVersion",""+android.os.Build.VERSION.SDK_INT);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public void setParams(KeyValue... params){
        this.params = params;
    }

    private class CustomRequest extends StringRequest
    {

        public CustomRequest(int method, String url, Response.Listener<String> listener, Response.ErrorListener errorListener) {
            super(method, url, listener, errorListener);
        }
        @Override
        protected Map<String, String> getParams() throws AuthFailureError {
            Map<String,String> mParams = new HashMap<>();
            if(params!=null)
            for(int i=0;i<params.length;i++)
            {
                mParams.put(params[i].getKey(),params[i].getValue());
                Log.i("params","key = "+params[i].getKey()+" value= "+params[i].getValue());
            }
            Log.i("params","key = "+"sent");
            return mParams;
        }

        @Override
        public byte[] getBody() throws AuthFailureError {
            if(jsonData!=null)
            {
                Log.i("tanvy",jsonData.toString());
                return jsonData.toString().getBytes();

            }
            else
            {
                return super.getBody();
            }
        }

        @Override
        public Map<String, String> getHeaders() throws AuthFailureError {
            Map<String,String> params = new HashMap<>();
            if(headers!=null)
            for(int i=0;i<headers.length;i++)
            {
                params.put(headers[i].getKey(),headers[i].getValue());

            }

            //headers.put();
            return params;
        }

        @Override
        public String getBodyContentType() {
            if(urlencoded)
                return "application/x-www-form-urlencoded";
            else
                return super.getBodyContentType();
        }
    }
    public void setRawJson(JSONObject data)
    {
        jsonData =data;
    }
    public void setRawJson(String key, String value)
    {
        try {
            jsonData.put(key,value);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public void urlencoded(boolean encoded)
    {
        this.urlencoded = encoded;

    }


    public void setHeaders(KeyValue... headers)
    {
        this.headers = headers;
    }

    public void execute(String method)
    {
        dialog = new Dialog(activity.getContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.progressdailoge);
        dialog.show();
        Response.Listener<String> rlistener = new Response.Listener<String>(){
            @Override
            public void onResponse(String response) {
                // Display the first 500 characters of the response string.
                activity.respond(response,reqId,HttpRequest.this);
                dialog.dismiss();

            }
        };
        Response.ErrorListener elistener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                activity.errorRespond(error,reqId,HttpRequest.this);
                dialog.dismiss();
            }
        };

        CustomRequest stringRequest;
        if(method.equals("post"))
        {
            stringRequest = new CustomRequest(Request.Method.POST, url, rlistener , elistener);
            Log.i("requestedd","post");

        }
        else if (method.equals("put"))
        {
            stringRequest = new CustomRequest(Request.Method.PUT, url, rlistener , elistener);
            Log.i("requestedd","put");

        }
        else
        {
            stringRequest = new CustomRequest(Request.Method.GET, url, rlistener , elistener);
            Log.i("request","get");
        }

        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                request_time_out,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        RequestManager.getRequestQueue(activity.getContext()).add(stringRequest);


    }
    public void putExtras(Bundle bundle)
    {
        this.bundle = bundle;
    }
    public Bundle getExtras()
    {
        return bundle;
    }

}
