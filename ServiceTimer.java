package teityan.com.appkn;


import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.ActivityManager;
import android.app.AppOpsManager;
import android.app.KeyguardManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.app.usage.UsageEvents;
import android.app.usage.UsageStats;
import android.app.usage.UsageStatsManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.media.AudioManager;
import android.os.BatteryManager;
import android.os.Build;
import android.os.IBinder;
import android.os.PowerManager;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.SortedMap;
import java.util.Timer;
import java.util.TimerTask;
import java.util.TreeMap;

import static android.support.constraint.Constraints.TAG;
import static android.view.View.resolveSizeAndState;


/**
 * Created by tenko_w8othx0 on 2017/08/29.
 */

public class ServiceTimer extends Service {
    //int
    int Data1;
    int Data2;
    int Nocount = 0;
    int Audios;
    private int count = 0;
    String strPreviousDate;
    String month;
    String m;
    String tasks;
    private Timer timer = null;
    private Timer timer1 = null;
    SharedPreferences dataStore;
    CharSequence dateText;
    boolean Audio=false;


    @Override
    public void onCreate() {
        super.onCreate();
        dataStore = getSharedPreferences("DataStore1", MODE_PRIVATE);
        Date nowDate = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(nowDate);
        strPreviousDate = new SimpleDateFormat("yyyyMMdd", Locale.JAPAN).format(cal.getTime());
        month = new SimpleDateFormat("yyyyMM", Locale.JAPAN).format(cal.getTime());

        SharedPreferences.Editor editor = dataStore.edit();
        editor.putInt((String) dateText, 0);
        editor.apply();
        registerReceiver(broadcastReceiver, new IntentFilter(Intent.ACTION_HEADSET_PLUG));
        registerReceiver(broadcastReceiver, new IntentFilter(AudioManager.ACTION_AUDIO_BECOMING_NOISY));
        showNotification();


    }
    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            final String action = intent.getAction();
            if (action == null) {
                return;
            }

            switch (action) {
                case Intent.ACTION_HEADSET_PLUG:
                    Log.e(TAG, "Intent.ACTION_HEADSET_PLUG");
                    int state = intent.getIntExtra("state", -1);
                    if (state == 0) {
                        Audio=false;
                        // ヘッドセットが装着されていない・外された
                        Log.d("Audio","wd");
                    } else if (state > 0) {
                        Audio=true;

                        // イヤホン・ヘッドセット(マイク付き)が装着された

                    }
            }
        }
    };

    @Override
    public IBinder onBind(Intent intent) {
        // エラーになるので、とりあえず入れてありますが使いません
        return null;
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("service", "onStartCommand");


        timer1 = new Timer();
        timer1.schedule(new TimerTask() {
            @Override
            public void run() {
                Log.d("ServiceCount", "count = " + count);
                count++;
                TimerCount();

            }
        }, 0, 60000);


        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Log.d("service", "onDestroy");
        super.onDestroy();
        // timer cancel

        if (timer1 != null) {
            timer1.cancel();
            timer1 = null;
        }
        unregisterReceiver(broadcastReceiver);

        stopSelf();

    }
    @TargetApi(Build.VERSION_CODES.LOLLIPOP_MR1)
    private String getTopActivityPackageName() {
        String packageName = "";
        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {  // 【1】
            ActivityManager am = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
            List<ActivityManager.RunningAppProcessInfo> list = am.getRunningAppProcesses();
            packageName = list.get(0).processName;
        } else { // 【2】
            UsageStatsManager usm = (UsageStatsManager) getSystemService(Context.USAGE_STATS_SERVICE);  // 【3】
            long endTime = System.currentTimeMillis();
            long beginTime = endTime - 7 * 24 * 60 * 60 * 1000;
            List<UsageStats> list = usm.queryUsageStats(UsageStatsManager.INTERVAL_BEST, beginTime, endTime);
            // 【4】
            if (list != null && list.size() > 0) {
                SortedMap<Long, UsageStats> map = new TreeMap<>();
                for (UsageStats usageStats : list) {
                    map.put(usageStats.getLastTimeUsed(), usageStats);
                }
                if (!map.isEmpty()) {
                    packageName = map.get(map.lastKey()).getPackageName();  // 【5】
                }
            }
        }

        return packageName;
    }

    public void TimerCount() {
        //パッケージ名の取得
       String names= getTopActivityPackageName();
       Log.d("test" ,names);

        int aaa=0;
        //バッテリー残量の確認
        IntentFilter intentfilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        Intent batteryStatus = this.registerReceiver(null, intentfilter);

        int batteryLevel = batteryStatus.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
        Log.d("b",batteryLevel+"");

        if (batteryLevel<30) {
            Intent i = new Intent(ServiceTimer.this, ServiceActivity.class);
            PendingIntent pendingIntent
                    = PendingIntent.getActivity(
                    this,
                    0,
                    i,
                    0);
            if(aaa==5) {
                aaa=0;
            }
            if (aaa == 0) {

                Notification notification = new Notification.Builder(this)
                        .setContentTitle("電池残量の警告！！")
                        .setContentText("電池残量が30%になりました。サービスを終了するにはここをタップ！")
                        .setContentIntent(pendingIntent)
                        .setSmallIcon(R.drawable.img)
                        .setAutoCancel(true)
                        .build();
                NotificationManager nm = (NotificationManager)
                        getSystemService(Context.NOTIFICATION_SERVICE);

                nm.notify(1000, notification);
            }
            aaa++;
        }
        //画面のON　OFF判定
        PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
        boolean isScreenOn = pm.isScreenOn();
        String name = Context.KEYGUARD_SERVICE;
        //スマホのロック状態の確認
        KeyguardManager keyguard = (KeyguardManager) getSystemService(name);
        boolean isScreenLock = keyguard.inKeyguardRestrictedInputMode();
        if (isScreenOn) {
            if (isScreenLock == false) {
                dataStore = getSharedPreferences("DataStore", MODE_PRIVATE);
                Data2 = dataStore.getInt(strPreviousDate, 0);
                SharedPreferences dataStore1 = getSharedPreferences("months", MODE_PRIVATE);
                SharedPreferences date =getSharedPreferences(strPreviousDate,0);
                int Datax = date.getInt(getTopActivityPackageName(), 0);

                int time = dataStore1.getInt(month, 0);
                if(Audio=true){
                    Audios++;
                }
                if(Audios==30){
                    Intent i = new Intent(ServiceTimer.this, StopActivity.class);

                    PendingIntent pendingIntent
                            = PendingIntent.getActivity(
                            this,
                            0,
                            i,
                            0);

                    Notification notification = new Notification.Builder(this)
                            .setContentTitle("ここらで耳の休憩をしない？")
                            .setContentText("ヘッドフォンが刺さっています。適度に耳の休憩をしましょう。")
                            .setContentIntent(pendingIntent)
                            .setSmallIcon(R.drawable.img)
                            .setAutoCancel(true)
                            .build();

                    NotificationManager nm = (NotificationManager)
                            getSystemService(Context.NOTIFICATION_SERVICE);

                    nm.notify(1000, notification);
                }
                Data1 = Data2 + 1;
                time++;
                Nocount++;
                Datax++;
                if (Nocount == 30) {
                    Nocount = 0;
                    showNotification();
                }


                SharedPreferences.Editor editor = dataStore.edit();
                editor.putInt(strPreviousDate, Data1);
                editor.apply();
                SharedPreferences.Editor editor1 = date.edit();
                editor1.putInt( getTopActivityPackageName(),Datax );
                editor1.apply();
                editor = dataStore1.edit();
                editor.putInt(month, time);
                editor.apply();
                Log.d("test", "経過時間は" + Data1);
                Log.d("boolean", Nocount + "");
            }
        } else {
            Log.d("test", "テスト");
        }


    }



    //通知の送信
    public void showNotification() {

        Intent i = new Intent(ServiceTimer.this, ExActivity.class);



        PendingIntent pendingIntent
                = PendingIntent.getActivity(
                this,
                0,
                i,
                0);

        Notification notification = new Notification.Builder(this)
                .setContentTitle("使いすぎてない？")
                .setContentText("もう30分使ってますよ！ストレッチをしよう！タップで" +
                        "ストレッチスタート！")
                .setContentIntent(pendingIntent)
                .setSmallIcon(R.drawable.img)
                .setAutoCancel(true)
                .build();

        NotificationManager nm = (NotificationManager)
                getSystemService(Context.NOTIFICATION_SERVICE);

        nm.notify(1000, notification);
    }




}
