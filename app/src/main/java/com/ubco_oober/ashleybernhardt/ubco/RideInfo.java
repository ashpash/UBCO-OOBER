package com.ubco_oober.ashleybernhardt.ubco;

import android.app.NotificationManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.view.View;
import android.widget.Button;
import android.widget.RemoteViews;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.common.api.GoogleApiClient;

import org.json.JSONException;
import org.json.JSONObject;

import static com.ubco_oober.ashleybernhardt.ubco.R.styleable.AlertDialog;

/**
 * Created by Ashley on 3/26/2017.
 */

public class RideInfo extends AppCompatActivity implements View.OnClickListener {

    private GoogleApiClient client;
    Button btnJoinRide, btnLeaveRide;
    String driverStudentEmail, driveDestination, driveTime,driveDate,studentEmail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rideinfo);

        TextView destination = (TextView) findViewById(R.id.destination);
        TextView driver = (TextView) findViewById(R.id.driver);
        TextView time = (TextView) findViewById(R.id.time);
        TextView date = (TextView) findViewById(R.id.date);
        btnJoinRide = (Button) findViewById(R.id.joinRideButton);
        btnLeaveRide = (Button) findViewById(R.id.btnRemove);


        Intent intent = getIntent();
        Bundle b = intent.getExtras();
        driverStudentEmail = (String) b.get("driverStudentEmail");
        driver.setText(driverStudentEmail);
        driveDate = (String) b.get("driveDate");
        date.setText(driveDate);
        driveTime = (String) b.get("driveTime");
        time.setText(driveTime);
        driveDestination = (String) b.get("driveDestination");
        destination.setText(driveDestination);

        btnJoinRide.setOnClickListener(this);
        btnLeaveRide.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        if (v == btnLeaveRide) {

            Intent intent = getIntent();
            Bundle b = intent.getExtras();
            studentEmail = (String) b.get("studentEmail");
            Response.Listener<String> responseListener = new Response.Listener<String>() {

                @Override
                public void onResponse(String response) {
                    try {
                        JSONObject jsonResponse = new JSONObject(response);
                        boolean success = jsonResponse.getBoolean("success");
                        boolean notInRide = jsonResponse.getBoolean("notInRide");

                        if (!notInRide) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(RideInfo.this);
                            builder.setMessage("Ride Removal Successful!")
                                    .setNegativeButton("Retry", null)
                                    .create()
                                    .show() ;

                            Intent intent = new Intent(RideInfo.this, RSS.class);
                            intent.putExtra("studentEmail", studentEmail);
                            RideInfo.this.startActivity(intent);

                        } else if (notInRide){
                            AlertDialog.Builder builder = new AlertDialog.Builder(RideInfo.this);
                            builder.setMessage("Ride Removal Failed, You are not a Passenger in this Ride")
                                    .setNegativeButton("Retry", null)
                                    .create()
                                    .show() ;

                        }else {
                            AlertDialog.Builder builder = new AlertDialog.Builder(RideInfo.this);
                            builder.setMessage("FAILLLLLL")
                                    .setNegativeButton("Retry", null)
                                    .create()
                                    .show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            };

            rideRemoveRequest rideRemoveRequest = new rideRemoveRequest(driveDestination, driverStudentEmail, driveTime, driveDate, studentEmail, responseListener);
            RequestQueue queue = Volley.newRequestQueue(RideInfo.this);

            queue.add(rideRemoveRequest);

        }

        if (v == btnJoinRide) {

            Intent intent = getIntent();
            Bundle b = intent.getExtras();
            studentEmail = (String) b.get("studentEmail");
            Response.Listener<String> responseListener = new Response.Listener<String>() {

                @Override
                public void onResponse(String response) {
                    try {
                        JSONObject jsonResponse = new JSONObject(response);
                        boolean success = jsonResponse.getBoolean("success");

                        if (success) {

                            AlertDialog.Builder builder = new AlertDialog.Builder(RideInfo.this);
                            builder.setMessage("Welcome Aboard!")
                                    .setNegativeButton("Retry", null)
                                    .create()
                                    .show() ;
                            Intent intent = new Intent(RideInfo.this, RSS.class);
                            intent.putExtra("studentEmail", studentEmail);
                            RideInfo.this.startActivity(intent);

                        } else {
                            AlertDialog.Builder builder = new AlertDialog.Builder(RideInfo.this);
                            builder.setMessage("FAILLLLLL")
                                    .setNegativeButton("Retry", null)
                                    .create()
                                    .show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            };

            AddRideRequest addRideRequest = new AddRideRequest(driveDestination, driverStudentEmail, driveTime, driveDate, studentEmail, responseListener);
            RequestQueue queue = Volley.newRequestQueue(RideInfo.this);

            queue.add(addRideRequest);
        }
    }

}

