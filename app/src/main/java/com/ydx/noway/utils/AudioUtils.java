package com.ydx.noway.utils;

import android.content.Context;
import android.media.AudioManager;

/**
 * Created by LT on 2016/3/29.
 */
public class AudioUtils {

    /**
     * 获取各种音量
     * @param context
     * @param streamType
     *          1. AudioManager.STREAM_VOICE_CALL 通话音量
     *          2. AudioManager.STREAM_SYSTEM 系统音量
     *          3. AudioManager.STREAM_RING 铃声音量
     *          4. AudioManager.STREAM_MUSIC  音乐音量
     *          5. AudioManager.STREAM_ALARM  提示声音音量
     * @return
     */
    public static int getStreamVolume(Context context,int streamType){
        AudioManager audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        return audioManager.getStreamVolume(streamType);
    }


    /**
     * 设置各种音量
     * @param context
     * @param streamType
     *          1. AudioManager.STREAM_VOICE_CALL 通话音量
     *          2. AudioManager.STREAM_SYSTEM 系统音量
     *          3. AudioManager.STREAM_RING 铃声音量
     *          4. AudioManager.STREAM_MUSIC  音乐音量
     *          5. AudioManager.STREAM_ALARM  提示声音音量
     * @return
     */
    public static int getStreamMaxVolume(Context context,int streamType){
        AudioManager audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        return audioManager.getStreamMaxVolume(streamType);
    }

    /**
     * 以步长调节手机音量大小
     * @param context
     * @param streamType
     *          1. AudioManager.STREAM_VOICE_CALL 通话音量
     *          2. AudioManager.STREAM_SYSTEM 系统音量
     *          3. AudioManager.STREAM_RING 铃声音量
     *          4. AudioManager.STREAM_MUSIC  音乐音量
     *          5. AudioManager.STREAM_ALARM  提示声音音量
     * @param direction 调节方向：ADJUST_LOWER（降低）、ADJUST_RAISE（升高）、ADJUST_SAME
     * @param flags 可选的标志位
     * @return
     */
    public static void adjustStreamVolume(Context context,int streamType,int direction,int flags){
        AudioManager audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        audioManager.adjustStreamVolume(streamType, direction, flags);
    }

    /**
     * 直接根据数值设置音量
     * @param context
     * @param streamType
     *          1. AudioManager.STREAM_VOICE_CALL 通话音量
     *          2. AudioManager.STREAM_SYSTEM 系统音量
     *          3. AudioManager.STREAM_RING 铃声音量
     *          4. AudioManager.STREAM_MUSIC  音乐音量
     *          5. AudioManager.STREAM_ALARM  提示声音音量
     * @param index 音量的值
     * @param flags 可选的标志位
     * @return
     */
    public static void setStreamVolume(Context context,int streamType,int index,int flags){
        AudioManager audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        audioManager.setStreamVolume(streamType,index,flags);
    }

    /**
     * 设置静音
     * @param context
     * @param streamType
     * @param state
     */
    public static void setStreamMute(Context context,int streamType,boolean state){
        AudioManager audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        audioManager.setStreamMute(streamType,state);
    }
}
