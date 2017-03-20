package com.ubco_oober.ashleybernhardt.ubco;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.NotificationCompat;
import android.widget.Toast;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

/**
 * Created by Nick on 2017-03-19.
 * When the button on the notification bar is pressed, the button leads to the activity containing more info on the ride.
 * I mean it is a, View more details button.
 */

public class Button_listener extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationManager manager = (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);
        manager.cancel(intent.getExtras().getInt("id"));

        //I CAN SHOW YOU THE WOOORLD
        //Change the 2nd parameter of myIntent to choose where pressing notif leads you.
        Intent myIntent = new Intent(context,FormActivity.class);
        myIntent.addFlags(FLAG_ACTIVITY_NEW_TASK);
//        PendingIntent pendingIntent = PendingIntent.getActivity(context,0,myIntent,0);

        context.startActivity(myIntent);



        Toast.makeText(context, "let's go see some details :D", Toast.LENGTH_SHORT).show();



    }
            }
