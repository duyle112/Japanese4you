package com.example.hoangduy.japanese4you.service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.RingtoneManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.example.hoangduy.japanese4you.MainActivity_;
import com.example.hoangduy.japanese4you.R;
import com.example.hoangduy.japanese4you.ScheduleActivity;
import com.example.hoangduy.japanese4you.database.DataAlarm;
import com.example.hoangduy.japanese4you.models.Time;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by HoangDuy on 18/01/2017.
 */
public class MyService extends Service {

    public static final String ACTION = "vn.asiantech.training";
    public static final String STOPACTION = "stopseft";
    private int mSecond = 0;
    private Notification notification;
    private Intent mIntent;
    private int arr[];
    private ArrayList<Time> mTimes;
    private ArrayList<Time> mDistances;
    private int mAlertSecond;
    private Handler mHandler;


    @Override
    public void onCreate() {
        super.onCreate();
        mIntent = new Intent(ACTION);
        registerReceiver(new MyBroadcastReceiver(), new IntentFilter(ACTION));
    }


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(final Intent intent, int flags, int startId) {
        restartService();
        mHandler = new Handler();
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mHandler.removeCallbacksAndMessages(null);
                mSecond++;
                Log.i("infosec", mSecond + "");
                if (mSecond == mAlertSecond) {
                    showNotification();
                    mAlertSecond = min(mTimes);
                    mSecond = 0;
                    Log.i("alert", mAlertSecond + "");
                }
                mHandler.postDelayed(this, 1000);
            }
        }, 1000);
        return START_STICKY;
    }

    public void restartService() {
        Intent in = new Intent(ACTION);
        ArrayList<Time> times = getAlarm();
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList(ScheduleActivity.KEY_TIME, times);
        in.putExtra(ScheduleActivity.KEY_BUNDLE, bundle);
        sendBroadcast(in);
    }

    public ArrayList<Time> getAlarm() {
        DataAlarm dbHelper = new DataAlarm(getApplicationContext());
        return dbHelper.getAlarm();
    }

    public void initArrayTime(ArrayList<Time> alarms) {
        mTimes = alarms;
        ArrayList<Time> times = new ArrayList<>();
        for (int i = 0; i < mTimes.size(); i++) {
            if (mTimes.get(i).getFlag() == 1) {
                times.add(mTimes.get(i));
            }
        }
        if (times.size() == 0) {
            ScheduleActivity.FLAG = 0;
        }
        mSecond = 0;
        try {
            mAlertSecond = min(times);
            Log.i("alertSecond", mAlertSecond + "");
        } catch (Exception e) {

        }
    }


    public int convertToS(int hour, int minute) {
        return hour * 3600 + minute * 60;
    }

    public int min(ArrayList<Time> times) {
        Calendar mCalendar = Calendar.getInstance();
        int hour = mCalendar.get(Calendar.HOUR_OF_DAY);
        int minute = mCalendar.get(Calendar.MINUTE);
        int dayofweek = mCalendar.get(Calendar.DAY_OF_WEEK);
        mDistances = new ArrayList<>();
        int min = 0;
        for (int i = 0; i < times.size(); i++) {
            String[] strings = times.get(i).getDayofweek().split(" ");
            for (int j = 0; j < strings.length; j++) {
                mDistances.add(new Time(times.get(i).getId(),times.get(i).getHour(), times.get(i).getMinute(), strings[j], times.get(i).getFlag()));
            }
        }
        arr = new int[mDistances.size()];
        Log.i("today", hour + " " + minute + " " + dayofweek);
        for (int i = 0; i < arr.length; i++) {
            Log.i("alarm", mDistances.get(i).getHour() + " " +
                    mDistances.get(i).getMinute() + " " + getDayofWeek(mDistances.get(i).getDayofweek()));
            if ((getDayofWeek(mDistances.get(i).getDayofweek()) > dayofweek)
                    || ((getDayofWeek(mDistances.get(i).getDayofweek()) == dayofweek)
                    && (convertToS(mDistances.get(i).getHour(), mDistances.get(i).getMinute()) - convertToS(hour, minute) > 0))) {
                arr[i] = convertToS(mDistances.get(i).getHour(), mDistances.get(i).getMinute()) - convertToS(hour, minute) +
                        convertToS((getDayofWeek(mDistances.get(i).getDayofweek()) - dayofweek) * 24, 0) - mCalendar.get(Calendar.SECOND);
            } else {
                arr[i] = convertToS(mDistances.get(i).getHour(), mDistances.get(i).getMinute()) - convertToS(hour, minute) +
                        convertToS((-dayofweek + getDayofWeek(mDistances.get(i).getDayofweek()) + 7) * 24, 0) - mCalendar.get(Calendar.SECOND);
            }
            Log.i("arr", arr[i] + "");
        }
        min = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < min && arr[i] != 0) {
                min = arr[i];
            }
        }
        return min;
    }

    public int getDayofWeek(String dayofweek) {
        switch (dayofweek) {
            case "Monday":
                return 2;
            case "Tuesday":
                return 3;
            case "Wednesday":
                return 4;
            case "Thursday":
                return 5;
            case "Friday":
                return 6;
            case "Satusday":
                return 7;
            case "Sunday":
                return 8;
        }
        return 9;
    }

    public void showNotification() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(getString(R.string.app_name))
                .setContentText("Time to learn!!!")
                .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                .setAutoCancel(true)
                .setOngoing(false);
        builder.setVibrate(new long[]{1000, 1000});
        Intent notifyIntent =
                new Intent(this, MainActivity_.class);
        PendingIntent notifyPendingIntent =
                PendingIntent.getActivity(
                        this,
                        0,
                        notifyIntent,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );

        builder.setContentIntent(notifyPendingIntent);
        NotificationManager mNotificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(0, builder.build());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    private void showForegroundNotification() {

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
            return;
        }
        Intent showTaskIntent = new Intent(getApplicationContext(), ScheduleActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(
                this,
                0,
                new Intent(),
                0);

        notification = new Notification.Builder(getApplicationContext())
                .setContentTitle(getString(R.string.app_name))
                .setContentText("Schedule is set")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setWhen(System.currentTimeMillis())
                .setAutoCancel(true)
                .setContentIntent(contentIntent)
                .setOngoing(true)
                .build();
        startForeground(1, notification);
    }

    public class MyBroadcastReceiver extends android.content.BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(ACTION)) {
                ArrayList<Time> times = intent.getBundleExtra(ScheduleActivity.KEY_BUNDLE).getParcelableArrayList(ScheduleActivity.KEY_TIME);
                initArrayTime(times);
            }
        }
    }
}

