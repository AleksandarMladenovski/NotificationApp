package com.mydesing.notificationapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private final static String CHANNEL_ID="Channel id";
    public static final int Notification_id = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final NotificationManager notificationManager=(NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        findViewById(R.id.send_notification).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
                    NotificationChannel channel=new NotificationChannel(CHANNEL_ID,"Channel 1", NotificationManager.IMPORTANCE_DEFAULT);
                    if(notificationManager!=null) {
                        notificationManager.createNotificationChannel(channel);
                    }
                }

            Intent intent=new Intent(MainActivity.this,NotificationActivity.class);
            PendingIntent pendingIntentClose=PendingIntent.getActivity(MainActivity.this,
                    1,
                    intent,
                    PendingIntent.FLAG_UPDATE_CURRENT);

            Intent intentClose = new Intent(MainActivity.this,BroadcastClose.class);
            PendingIntent pendingintentClose=PendingIntent.getBroadcast(MainActivity.this,
                    2,
                    intentClose,
                    PendingIntent.FLAG_UPDATE_CURRENT);
            NotificationCompat.Action action = new NotificationCompat.Action(android.R.drawable.star_big_on,"close",pendingintentClose);
            Notification notification=new NotificationCompat.Builder(MainActivity.this,CHANNEL_ID)
                    .setContentTitle("Title")
                    .setContentText("Text")
                    .setSmallIcon(R.drawable.ic_launcher_background)
                    .setContentIntent(pendingIntentClose)
                    .setAutoCancel(true)
                    .setStyle(new NotificationCompat.BigTextStyle()
                            .bigText("BIG TEXT BIG TEXT BIG TEXT BIG TEXT BIG TEXT BIG TEXT BIG TEXT BIG TEXT BIG TEXT"))
                    .addAction(action)
                    .build();
            if(notificationManager!=null){
                notificationManager.notify(Notification_id,notification);
            }

        }
        });
        findViewById(R.id.close_notification).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(notificationManager!=null){
                    notificationManager.cancel(Notification_id);
                }
            }
        });
    }
}
