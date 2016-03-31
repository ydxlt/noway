package com.ydx.noway.utils;

import android.content.Context;
import android.view.View;

import com.ydx.noway.App;

/**
 * Created by LT on 2016/3/24.
 */
public class UIutils {
    public static Context mContext = App.getInstance();
    public static View inflate(int id){
        return View.inflate(mContext,id,null);
    }
}
