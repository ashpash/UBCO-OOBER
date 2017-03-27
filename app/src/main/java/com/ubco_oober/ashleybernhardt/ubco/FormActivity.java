package com.ubco_oober.ashleybernhardt.ubco;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Parcelable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RemoteViews;
import android.widget.TimePicker;
import android.widget.Toast;

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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import static android.R.attr.id;

public class FormActivity extends AppCompatActivity implements
        View.OnClickListener {

    private GoogleApiClient client;
    public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";

    private NotificationCompat.Builder builder;
    private NotificationManager notificationManager;
    private int notification_id;
    private RemoteViews remoteViews;
    private Context context;

    Button btnDatePicker, btnTimePicker, btnFinish;
    EditText txtDate, txtTime, txtDestination,etSpace;
    private int mYear, mMonth, mDay, mHour, mMinute;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        btnDatePicker=(Button)findViewById(R.id.etButtonDate);
        btnTimePicker=(Button)findViewById(R.id.etButtonTime);
        txtDate=(EditText)findViewById(R.id.etDate);
        txtTime=(EditText)findViewById(R.id.etTime);
        etSpace = (EditText) findViewById(R.id.etSpace);
        txtDestination = (EditText) findViewById(R.id.etDestination);
        btnFinish =(Button)findViewById(R.id.bFinish);

        btnDatePicker.setOnClickListener(this);
        btnTimePicker.setOnClickListener(this);
        btnFinish.setOnClickListener(this);

        context = this;
        notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        remoteViews = new RemoteViews(getPackageName(),R.layout.ride_notification);

        notification_id = (int) Math.random()*100;
        Intent button_Intent = new Intent("button_clicked");
        button_Intent.putExtra("id",notification_id);



    } @Override
    public void onClick(View v) {

        if (v == btnDatePicker) {

            // Get Current Date
            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);


            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {

                            txtDate.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);

                        }
                    }, mYear, mMonth, mDay);datePickerDialog.show();
        }
        if (v == btnTimePicker) {

            // Get Current Time
            final Calendar c = Calendar.getInstance();
            mHour = c.get(Calendar.HOUR_OF_DAY);
            mMinute = c.get(Calendar.MINUTE);

            // Launch Time Picker Dialog
            TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                    new TimePickerDialog.OnTimeSetListener() {

                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay,
                                              int minute) {

                            txtTime.setText(hourOfDay + ":" + minute);
                        }
                    }, mHour, mMinute, false);
            timePickerDialog.show();
        }
        if (v== btnFinish) {

            final int Space = Integer.parseInt(etSpace.getText().toString());
            final String Destination = txtDestination.getText().toString();
            final String sDate = txtDate.getText().toString();
            final String sTime = txtTime.getText().toString();
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
                            //pendingIntent allows the notifcation to know where to go if you click the whitespace
//                            Intent notificationIntent = new Intent(context,RSS.class);
//                            PendingIntent pendingIntent = PendingIntent.getActivity(context,notification_id,notificationIntent,PendingIntent.FLAG_UPDATE_CURRENT);

                            remoteViews.setTextViewText(R.id.notif_destination,Destination);
                            remoteViews.setTextViewText(R.id.notif_time,sDate);
                            remoteViews.setTextViewText(R.id.notif_space,sTime);

                            builder = new NotificationCompat.Builder(context);
                            builder.setSmallIcon(R.mipmap.ic_launcher)
                                    .setAutoCancel(false)
                                    .setCustomContentView(remoteViews);
                            //setContentIntent(Intent) is what intent you want to go to when clicking whitespace
//                                    .setContentIntent(pendingIntent);

                            notificationManager.notify(notification_id,builder.build());

                            Intent intent = new Intent(FormActivity.this, RSS.class);
                            FormActivity.this.startActivity(intent);
                        } else {
                            AlertDialog.Builder builder = new AlertDialog.Builder(FormActivity.this);
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

            FormRequest formRequest = new FormRequest(Destination, sDate, sTime, Space, studentEmail, responseListener);
            RequestQueue queue = Volley.newRequestQueue(FormActivity.this);

            queue.add(formRequest);
        }
    }

}

/*
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
                FormRequest formRequest = new FormRequest(studentEmail, Destination, dateTime, Space, responseListener);
                RequestQueue queue = Volley.newRequestQueue(FormActivity.this);
                queue.add(formRequest);

            }

        });
    }

}
/*
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
   /* public Action getIndexApiAction() {
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

*/