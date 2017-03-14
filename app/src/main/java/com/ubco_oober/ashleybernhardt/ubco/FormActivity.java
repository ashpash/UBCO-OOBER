package com.ubco_oober.ashleybernhardt.ubco;

import android.content.Intent;
import android.net.Uri;
import android.os.Parcelable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class FormActivity extends AppCompatActivity {
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;
    // public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        Button button = (Button) findViewById(R.id.bFinish);
        final EditText etDestination = (EditText) findViewById(R.id.etDestination);
        final EditText etDate = (EditText) findViewById(R.id.etDate);
        final EditText etTime = (EditText) findViewById(R.id.etTime);
        final EditText etSpace = (EditText) findViewById(R.id.etSpace);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

                String Date = etDate.getText().toString();
                Date myDate;
                myDate = dateFormat.parse(Date);
                Calendar cal = Calendar.getInstance();
                cal.setTime(myDate);
                cal.get(Calendar.DAY_OF_MONTH);

                final String Destination = etDestination.getText().toString();
                final String Time = etTime.getText().toString();
                final int Space = Integer.parseInt(etSpace.getText().toString());


                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");

                            if (success) {
                                Intent intent = new Intent(FormActivity.this, ScrollingActivity.class);
                                FormActivity.this.startActivity(intent);
                            } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(FormActivity.this);
                                builder.setMessage("Form filled out Incorrecty. ")
                                        .setNegativeButton("Retry", null)
                                        .create()
                                        .show();

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                };
                FormRequest formRequest = new FormRequest(Destination, Date, Time, Space, responseListener);
                RequestQueue queue = Volley.newRequestQueue(FormActivity.this);
                queue.add(formRequest);

            }

        });

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Form Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }
}


    //final Button bFinish = (Button) findViewById(R.id.bRegister);

   /* public void sendInfo(View view) {
        Intent intent = new Intent(this, ScrollingActivity.class);

        //place inputted text into variables
        final EditText etDestination = (EditText) findViewById(R.id.etDestination);
        final EditText etSpace = (EditText) findViewById(R.id.etSpace);
        final EditText etTime = (EditText) findViewById(R.id.etTime);
        String[] message = new String[3];

        Form exampleForm = new Form(etDestination.getText().toString(),etSpace.getText().toString(),etTime.getText().toString());

        message[0] = etDestination.getText().toString();
        message[1] = etSpace.getText().toString();
        message[2] = etTime.getText().toString();


        intent.putExtra(EXTRA_MESSAGE, (Parcelable) exampleForm);
        startActivity(intent);

    }
}

