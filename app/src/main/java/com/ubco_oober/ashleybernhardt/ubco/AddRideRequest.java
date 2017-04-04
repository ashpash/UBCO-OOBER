package com.ubco_oober.ashleybernhardt.ubco;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Ashley on 3/28/2017.
 */

public class AddRideRequest extends StringRequest{
    private static final String Ride_Request_URL = "https://ubco-oober.000webhostapp.com/AddToRide.php";
    private Map<String, String> params;

    public AddRideRequest(String driveDestination, String driverStudentEmail, String driveTime, String driveDate,String studentEmail, Response.Listener<String> listener) {
        super(Request.Method.POST, Ride_Request_URL, listener, null);
        params = new HashMap<>() ;
        params.put("studentEmail", studentEmail);
        Log.i(studentEmail,"studentEmail");
        params.put("driveDestination", driveDestination);
        Log.i(driveDestination,"destination");
        params.put("driveDate", driveDate);
        Log.i(driveDate,"driveDate");
        params.put("driveTime", driveTime);
        Log.i(driveTime,"time");
        params.put("driverStudentEmail", driverStudentEmail);
        Log.i(driverStudentEmail,"driverEmail");
    }

    public  Map<String, String> getParams() {
        return params;
    }

}
