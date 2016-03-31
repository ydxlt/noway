package com.ydx.noway.ui;

import android.graphics.drawable.Drawable;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;


import com.ydx.noway.R;
import com.ydx.noway.adapter.TabPageAdapter;
import com.ydx.noway.annotation.ViewInject;
import com.ydx.noway.annotation.ViewUtils;
import com.ydx.noway.base.BaseActivity;
import com.ydx.noway.fragment.ClockFragment;
import com.ydx.noway.fragment.DataConnFragment;
import com.ydx.noway.fragment.ModulationFragment;
import com.ydx.noway.utils.Constants;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends BaseActivity implements
        ViewPager.OnPageChangeListener, OnCheckedChangeListener {

    private final int PAGE_DATA_CONNECT = 0;
    private final int PAGE_MODULATION = 1;
    private final int PAGE_CLOCK = 2;

    @ViewInject(R.id.viewPager)
    private ViewPager mViewPager;
    @ViewInject(R.id.radioGroup)
    private RadioGroup mRadioGroup;

    private List<Fragment> fragments = new ArrayList<Fragment>();

    @Override
    protected void initData() {
        TabPageAdapter tabPageAdapter = new TabPageAdapter(
                getSupportFragmentManager(), fragments);
        mViewPager.setAdapter(tabPageAdapter);
        selectPage(PAGE_DATA_CONNECT);
    }

    @Override
    protected void init() {
        Fragment crowdFundingFragment = new DataConnFragment();
        Fragment exchangeFragment = new ModulationFragment();
        Fragment meFragment = new ClockFragment();
        fragments.add(crowdFundingFragment);
        fragments.add(exchangeFragment);
        fragments.add(meFragment);
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_main);
        ViewUtils.inject(this);
        mRadioGroup.setOnCheckedChangeListener(this);
        mViewPager.setOnPageChangeListener(this);
    }

    /**
     * 选择某页
     * @param position 页面的位置
     */
    private void selectPage(int position) {
        // 将所有的tab的icon变成灰色的
        for (int i = 0; i < mRadioGroup.getChildCount(); i++) {
            Drawable gray = getResources().getDrawable(Constants.unselectedIconIds[i]);
            // 不能少，少了不会显示图片
            gray.setBounds(0, 0, gray.getMinimumWidth(),
                    gray.getMinimumHeight());
            RadioButton child = (RadioButton) mRadioGroup.getChildAt(i);
            child.setCompoundDrawables(null, gray, null, null);
            child.setTextColor(getResources().getColor(
                    R.color.dark_gray));
        }
        // 切换页面
        mViewPager.setCurrentItem(position, false);
        // 改变图标
        Drawable yellow = getResources().getDrawable(Constants.selectedIconIds[position]);
        yellow.setBounds(0, 0, yellow.getMinimumWidth(),
                yellow.getMinimumHeight());
        RadioButton select = (RadioButton) mRadioGroup.getChildAt(position);
        select.setCompoundDrawables(null, yellow, null, null);
        select.setTextColor(getResources().getColor(
                R.color.yellow));
    }

    @Override
    public void onPageScrolled(int position, float positionOffset,
                               int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        selectPage(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.btn_data_connection: // 数据连接
                selectPage(PAGE_DATA_CONNECT);
                break;
            case R.id.btn_modulation: // 音量
                selectPage(PAGE_MODULATION);
                break;
            case R.id.btn_clock: // 整点
                selectPage(PAGE_CLOCK);
                break;
        }
    }
}

