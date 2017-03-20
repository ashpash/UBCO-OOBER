package com.ubco_oober.ashleybernhardt.ubco;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RemoteViews;

public class FormActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";

    private NotificationCompat.Builder builder;
    private NotificationManager notificationManager;
    private int notification_id;
    private RemoteViews remoteViews;
    private Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
        Button button = (Button) findViewById(R.id.bFinish);

        context = this;
        notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        remoteViews = new RemoteViews(getPackageName(),R.layout.ride_notification);
//        remoteViews.setImageViewResource(R.id.notif_icon,R.drawable.ic_menu_send);
        remoteViews.setTextViewText(R.id.notif_destination,"Sample destination");
        remoteViews.setTextViewText(R.id.notif_time,"Sample departure time");
        remoteViews.setTextViewText(R.id.notif_space,"Sample space");


        notification_id = (int) Math.random()*100;
        Intent button_Intent = new Intent("button_clicked");
        button_Intent.putExtra("id",notification_id);

        PendingIntent p_button_intent = PendingIntent.getBroadcast(context,123,button_Intent,0);
        remoteViews.setOnClickPendingIntent(R.id.notif_button, p_button_intent);

    }

    public void sendInfo(View view) {

        Intent intent = new Intent(this, congratsActivity.class);

        Intent notificationIntent = new Intent(context,FormActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context,notification_id,notificationIntent,PendingIntent.FLAG_UPDATE_CURRENT);



        //place inputted text into variables
        final EditText etDestination = (EditText) findViewById(R.id.etDestination);
        final EditText etSpace = (EditText) findViewById(R.id.etSpace);
        final EditText etTime = (EditText) findViewById(R.id.etTime);
        String[] message = new String[3];

        Form exampleForm = new Form(etDestination.getText().toString(),etSpace.getText().toString(),etTime.getText().toString());

        remoteViews.setTextViewText(R.id.notif_destination,exampleForm.getDestination());
        remoteViews.setTextViewText(R.id.notif_time,exampleForm.getTime());
        remoteViews.setTextViewText(R.id.notif_space,exampleForm.getSpace());

        builder = new NotificationCompat.Builder(context);
        builder.setSmallIcon(R.mipmap.ic_launcher)
                .setAutoCancel(true)
                .setCustomContentView(remoteViews)
                .setContentIntent(pendingIntent);

        notificationManager.notify(notification_id,builder.build());



        intent.putExtra(EXTRA_MESSAGE, (Parcelable) exampleForm);

        startActivity(intent);

    }
}

