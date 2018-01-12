package teityan.com.appkn;


import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.usage.UsageEvents;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.view.MenuItem;
import android.view.View;
import android.widget.RemoteViews;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import static android.R.color.black;
import static java.lang.Boolean.TRUE;


public class ServiceActivity extends AppCompatActivity {
    int Data2;
    SharedPreferences dataStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service2);
        android.support.v7.app.ActionBar actionbar = getSupportActionBar();
        actionbar.setHomeButtonEnabled(true);
        actionbar.setDisplayHomeAsUpEnabled(true);
        setTitle( "設定" );
        Date nowDate = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(nowDate);
        String strDate = new SimpleDateFormat("yyyyMMdd", Locale.JAPAN).format(nowDate);
        dataStore = getSharedPreferences("DataStore", MODE_PRIVATE);
        Data2 = dataStore.getInt(strDate, 0);


    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){

        int itemId = item.getItemId();
        if(itemId == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);

    }
    public void start(View v){
        Intent intent = new Intent(ServiceActivity.this,MainActivity.class);
        //アプリ通知を表示
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext());
        builder.setSmallIcon(R.drawable.img);

        RemoteViews customView = new RemoteViews(getPackageName(), R.layout.notification);
        customView.setTextViewText(R.id.textView8, Data2+"分");
        customView.setOnClickFillInIntent(R.id.button7,intent);
        builder.setContent(customView);
       /* Intent is = new Intent(ServiceActivity.this, MainActivity.class);

        PendingIntent pendingIntent
                = PendingIntent.getActivity(
                this,
                0,
                is,
                0);

        Notification notifications= new Notification.Builder(this)
                .setContentTitle("使いすぎてな")
                .setContentText("もう30分使ってますよ！使い過ぎに注意です！")
                .setContentIntent(pendingIntent)
                .setSmallIcon(R.drawable.img)
                .setAutoCancel(true)
                .setOngoing(true)
                .build();

        NotificationManager nms = (NotificationManager)
                getSystemService(Context.NOTIFICATION_SERVICE);
       nms.notify(1, notifications);

*/
        // Serviceを呼び出す
        Intent intents = new Intent(getApplication(), ServiceTimer.class);
        startService(intents);


    }
    public void stop(View v){
        Intent intent = new Intent(ServiceActivity.this, ServiceTimer.class);
        stopService(intent);


       /* //アプリ通知を表示
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext());
        builder.setSmallIcon(R.drawable.img);

        RemoteViews customView = new RemoteViews(getPackageName(), R.layout.notification);
        customView.setTextViewText(R.id.textView8, Data2+"分");
        customView.setOnClickFillInIntent(R.id.button7,intent);
        builder.setContent(customView);
/*
        NotificationManagerCompat manager = NotificationManagerCompat.from(getApplicationContext());
        manager.notify(1, builder.build());
        Notification notification = new Notification();
        notification.contentView = new RemoteViews(getPackageName(), R.layout.notification);

        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(NotificationManager.IMPORTANCE_DEFAULT,notification);*/
    }
}
