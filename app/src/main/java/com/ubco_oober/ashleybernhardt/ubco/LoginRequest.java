package com.ubco_oober.ashleybernhardt.ubco;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Ashley Bernhardt on 3/13/2017.
 */

public class LoginRequest extends StringRequest {
    private static final String Login_Request_URL = "https://ubco-oober.000webhostapp.com/Login.php";
    private Map<String, String> params;

    public LoginRequest(String studentEmail, String password,Response.Listener<String> listener) {
        super(Request.Method.POST, Login_Request_URL, listener, null);
        params = new HashMap<>() ;
        params.put("studentEmail", studentEmail);
        params.put("password", password);
           }

    public  Map<String, String> getParams() {
        return params;
    }

}
