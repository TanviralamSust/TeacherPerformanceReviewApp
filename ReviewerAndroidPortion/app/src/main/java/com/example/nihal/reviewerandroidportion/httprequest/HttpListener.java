package com.example.nihal.reviewerandroidportion.httprequest;

import android.content.Context;

import com.android.volley.VolleyError;

/**
 * Created by root on 12/27/15.
 */
public interface HttpListener {
    public void respond(String jsonRespond, int respondId, HttpRequest parent);
    public void errorRespond(VolleyError e, int respondId, HttpRequest parent);
    public Context getContext();

}
