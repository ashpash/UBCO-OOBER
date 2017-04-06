package com.ubco_oober.ashleybernhardt.ubco;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.NotificationCompat;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Ashley on 4/4/2017.
 */

public class rideSearch extends Activity {


    Button btnR;
    EditText etDestination;

    @Override
    protected void onCreate(Bundle savedInstancesState) {
        super.onCreate(savedInstancesState);
        setContentView(R.layout.activity_ridesearch);

        etDestination = (EditText)findViewById(R.id.etDestination);
        btnR = (Button)findViewById(R.id.btnReq);

        btnR.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){

                final String Destination = etDestination.getText().toString();
                Intent intent = getIntent();
                Bundle b = intent.getExtras();
                final String studentEmail = (String) b.get("studentEmail");

                Response.Listener<String> responseListener = new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");

                            if (success) {
                                Intent intent = new Intent(rideSearch.this, RSS.class);
                                intent.putExtra("studentEmail", studentEmail);
                                rideSearch.this.startActivity(intent);
                            } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(rideSearch.this);
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

                rideSearchRequest searchRequest = new rideSearchRequest(Destination, studentEmail, responseListener);
                RequestQueue queue = Volley.newRequestQueue(rideSearch.this);

                queue.add(searchRequest);


            }
        });







    }
}
