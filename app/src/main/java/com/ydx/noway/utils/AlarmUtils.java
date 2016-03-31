package com.ydx.noway.utils;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.ydx.noway.receiver.AlarmReceiver;


/**
 * Created by LT on 2016/3/29.
 */
public class AlarmUtils {

    /*public static void invokeAlarmService(Context context,int startTime,int intervalTime){
        PendingIntent alarmSender = null;
        Intent startIntent = new Intent(AlarmReceiver.RECEIVER_ACTION);
        startIntent.putExtra(AlarmReceiver.FLAG, AlarmService.TASK_MUSIC_VOICE_AJUST);
        startIntent.setAction(AlarmService.SERVICE_ACTION);
        try {
            alarmSender = PendingIntent.getService(context, 0, startIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        } catch (Exception e) {
            Log.i("ServiceUtil-AlarmManager", "failed to start " + e.toString());
        }
        AlarmManager am = (AlarmManager) context.getSystemService(Activity.ALARM_SERVICE);
        // 使用绝对时间执行定时任务
        am.setInexactRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(),intervalTime, alarmSender);
    }*/

    public static void cancleAlarmManager(Context context){
        Intent intent = new Intent(AlarmReceiver.RECEIVER_ACTION);
        PendingIntent pendingIntent=PendingIntent.getService(context, 0, intent,PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarm=(AlarmManager)context.getSystemService(Activity.ALARM_SERVICE);
        alarm.cancel(pendingIntent);
    }
}
