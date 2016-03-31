package com.ydx.noway.fragment;

import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ydx.noway.base.BaseFragment;

/**
 * Created by LT on 2016/3/23.
 * 整点报时
 */
public class ClockFragment extends BaseFragment{
    @Override
    protected View initView() {
        TextView textView = new TextView(getActivity());
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        textView.setGravity(Gravity.CENTER);
        textView.setLayoutParams(params);
        textView.setText("整点报时");
        return textView;
    }
}
