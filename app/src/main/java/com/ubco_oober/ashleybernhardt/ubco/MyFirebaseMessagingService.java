package com.ubco_oober.ashleybernhardt.ubco;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompatBase;
import android.support.v7.app.NotificationCompat;
import android.widget.RemoteViews;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

/**
 * Created by Nick on 2017-03-26.
 */

public class MyFirebaseMessagingService extends FirebaseMessagingService{

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage){
        Intent intent = new Intent(this,RSS.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_ONE_SHOT);

        RemoteViews remoteViews = new RemoteViews(getPackageName(),R.layout.ride_notification);

        //the 2nd parameter is what we set the notification textspaces to, right now it's set to the whole body.
        remoteViews.setTextViewText(R.id.notif_destination,remoteMessage.getNotification().getBody());  //Set destination text
        remoteViews.setTextViewText(R.id.notif_time,remoteMessage.getNotification().getBody());         //Set time text
        remoteViews.setTextViewText(R.id.notif_space,remoteMessage.getNotification().getBody());        //Set space text

        NotificationCompat.Builder notficationBuilder = new NotificationCompat.Builder(this);
        notficationBuilder.setContentTitle("FCM Notification")
                .setAutoCancel(true)
                .setSmallIcon(R.mipmap.login_plain)
                .setContentIntent(pendingIntent)
                .setCustomContentView(remoteViews);

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0,notficationBuilder.build());

    }
}
