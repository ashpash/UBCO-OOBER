package com.ubco_oober.ashleybernhardt.ubco;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Ashley on 4/4/2017.
 */

public class rideSearchRequest extends StringRequest{

    private static final String Search_Request_URL = "https://ubco-oober.000webhostapp.com/rideRequest.php";
    private Map<String, String> params;

    public rideSearchRequest(String Destination, String studentEmail, Response.Listener<String> listener) {
        super(Request.Method.POST, Search_Request_URL, listener, null);
        params = new HashMap<>() ;
        params.put("studentEmail", studentEmail);
        Log.i(studentEmail,"studentEmail");
        params.put("Destination", Destination);
        Log.i(Destination,"destination");

    }

    public  Map<String, String> getParams() {
        return params;
    }
}
