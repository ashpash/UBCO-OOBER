package com.ubco_oober.ashleybernhardt.ubco;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Ashley Bernhardt on 3/14/2017.
 */

public class FormRequest extends StringRequest {

    private static final String Form_Request_URL = "https://ubco-oober.000webhostapp.com/Form.php";
    private Map<String, String> params;

    public FormRequest(String studentEmail, String destination, String date, String time, Integer space, Response.Listener<String> listener) {
        super(Request.Method.POST, Form_Request_URL, listener, null);
        params = new HashMap<>() ;
        params.put("studentEmail", studentEmail);
        params.put("Destination", destination);
        params.put("Date", date);
        params.put("Time", time);
        params.put("Space", String.valueOf(space));


    }

    public  Map<String, String> getParams() {
        return params;
    }
}
