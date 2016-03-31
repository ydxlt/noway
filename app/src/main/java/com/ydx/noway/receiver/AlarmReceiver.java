package com.ydx.noway.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;

import com.ydx.noway.utils.AudioUtils;

/**
 * Created by LT on 2016/3/29.
 */
public class AlarmReceiver extends BroadcastReceiver{

    public static final int TASK_MUSIC_VOICE_OPEN = 1;
    public static final int TASK_MUSIC_VOICE_CLOSE = 2;
    public static final int TASK_RING_VOICE_OPEN = 3;
    public static final int TASK_RING_VOICE_CLOSE = 4;
    public static final String RECEIVER_ACTION = "com.ydx.noway.service.AlarmService";
    public static final String FLAG = "TaskType";


    @Override
    public void onReceive(Context context, Intent intent) {
        int taskType = intent.getIntExtra(FLAG, 0);
        switch (taskType){
            case TASK_MUSIC_VOICE_OPEN:
                AudioUtils.setStreamMute(context,AudioManager.STREAM_MUSIC,false);
                break;
            case TASK_MUSIC_VOICE_CLOSE:
                AudioUtils.setStreamMute(context,AudioManager.STREAM_MUSIC,true);
                break;
            case TASK_RING_VOICE_OPEN:
                AudioUtils.setStreamMute(context,AudioManager.STREAM_RING,false);
                break;
            case TASK_RING_VOICE_CLOSE:
                AudioUtils.setStreamMute(context,AudioManager.STREAM_RING,true);
                break;
        }
    }
}
