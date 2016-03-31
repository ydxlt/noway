package com.ydx.noway.model;

/**
 * Created by LT on 2016/3/24.
 * 定时任务
 */
public class Crontab {

    public static final int TYPE_CLOSE = 0;
    public static final int TYPE_OPEN = 1;
    public static final int STATUS_CLOSE = 0;
    public static final int STATUS_OPEN = 1;

    private int type;
    private int status;
    private long time;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
