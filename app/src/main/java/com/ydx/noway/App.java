package com.ydx.noway;

import android.app.Application;

/**
 * Created by LT on 2016/3/23.
 * 这个App代表我们这个应用，一个应用只有一个Application类
 */
public class App extends Application{

    private static App mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        this.mInstance = this;
    }

    public static App getInstance() {
        return mInstance;
    }

    /**
     * 退出应用
     */
    protected void kill(){
        android.os.Process.killProcess(android.os.Process.myPid());
    }
}
