package com.ydx.noway.base;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.FragmentActivity;


/**
 * Created by LT on 2016/3/23.
 */
public abstract class BaseActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
        initView();
        initData();
    }

    protected void initData() {

    }

    protected abstract void initView();

    protected void init() {

    }
}
