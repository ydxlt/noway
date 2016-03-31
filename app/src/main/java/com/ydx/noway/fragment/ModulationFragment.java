package com.ydx.noway.fragment;

import android.app.ActionBar;
import android.graphics.drawable.Drawable;
import android.media.AudioManager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.TimePicker;

import com.ydx.noway.R;
import com.ydx.noway.adapter.DataConnAdapter;
import com.ydx.noway.adapter.VoiceAdapter;
import com.ydx.noway.annotation.ViewInject;
import com.ydx.noway.annotation.ViewUtils;
import com.ydx.noway.base.BaseFragment;
import com.ydx.noway.model.Crontab;
import com.ydx.noway.utils.AudioUtils;
import com.ydx.noway.utils.MobileDataUtils;
import com.ydx.noway.utils.UIutils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by LT on 2016/3/23.
 * 音量调节页面
 */
public class ModulationFragment extends BaseFragment implements View.OnClickListener, SeekBar.OnSeekBarChangeListener {

    private List<Crontab> datas = new ArrayList<Crontab>();

    @ViewInject(R.id.iv_left)
    private ImageView iv_left;
    @ViewInject(R.id.tv_title)
    private TextView tv_title;
    @ViewInject(R.id.iv_right)
    private ImageView iv_right;
    @ViewInject(R.id.recyclerView)
    private RecyclerView mRecyclerView;
    private SeekBar sb_musicVoice;
    private SeekBar sb_ringVoice;

    @Override
    protected View initView() {
        View view = UIutils.inflate(R.layout.fragment_modulation);
        ViewUtils.inject(this, view);
        configTitleBar();
        return view;
    }

    private void configTitleBar() {
        iv_right.setOnClickListener(this);
        tv_title.setText(R.string.tab_modulation);
    }

    @Override
    protected void initData() {
        super.initData();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        Random random = new Random();
        for (int i=0;i<20;i++){
            Crontab crontab = new Crontab();
            crontab.setType(random.nextInt(2));
            crontab.setStatus(random.nextInt(2));
            datas.add(crontab);
        }
        VoiceAdapter adapter = new VoiceAdapter(datas);
        mRecyclerView.setAdapter(adapter);
        View header = LayoutInflater.from(mContext).inflate(R.layout.header_modulation, null);
        adapter.setHeaderView(header);

        initSeekbar(header);
    }

    private void initSeekbar(View header) {
        sb_musicVoice = (SeekBar) header.findViewById(R.id.sb_musicVoice);
        //设置该进度条的最大值,默认情况下为O
        sb_musicVoice.setMax(AudioUtils.getStreamMaxVolume(mContext, AudioManager.STREAM_MUSIC));
        // 设置进度条的初始值
        sb_musicVoice.setProgress(AudioUtils.getStreamVolume(mContext, AudioManager.STREAM_MUSIC));
        sb_ringVoice = (SeekBar) header.findViewById(R.id.sb_ringVoice);
        //设置该进度条的最大值,默认情况下为O
        sb_ringVoice.setMax(AudioUtils.getStreamMaxVolume(mContext, AudioManager.STREAM_RING));
        // 设置进度条的初始值
        sb_ringVoice.setProgress(AudioUtils.getStreamVolume(mContext, AudioManager.STREAM_RING));
        sb_musicVoice.setOnSeekBarChangeListener(this);
        sb_ringVoice.setOnSeekBarChangeListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_left:

                break;
            case R.id.iv_right:
                showPopWindow();
                break;
        }
    }

    private void showPopWindow() {
        // 弹出对话框
        View contentView = UIutils.inflate(R.layout.dialog_add_modulation);
        TimePicker timePicker = (TimePicker) contentView.findViewById(R.id.timePicker);
        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                System.out.println("hourOfDay:"+hourOfDay+"  minute："+minute);
            }
        });
        PopupWindow pw = new PopupWindow(mContext);
        pw.setContentView(contentView);
        pw.setWidth(-2);
        pw.setHeight(-2);
        // 设置可聚焦，这样点击外部后窗口自动消失
        pw.setFocusable(true);
        pw.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                iv_right.setEnabled(true);
            }
        });
        pw.showAsDropDown(iv_right, -100, (int) iv_right.getY() + 10);
        iv_right.setEnabled(false);
    }

    /**
     * 进度改变时调用
     * @param seekBar
     * @param progress
     * @param fromUser
     */
    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        switch (seekBar.getId()){
            case R.id.sb_musicVoice:
                AudioUtils.setStreamVolume(mContext,AudioManager.STREAM_MUSIC,progress,AudioManager.FLAG_VIBRATE);
                sb_musicVoice.setProgress(progress);
                break;
            case R.id.sb_ringVoice:
                AudioUtils.setStreamVolume(mContext,AudioManager.STREAM_RING,progress,AudioManager.FLAG_VIBRATE);
                sb_ringVoice.setProgress(progress);
                break;
        }
    }

    /**
     * 拖动中调用
     * @param seekBar
     */
    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    /**
     * 停止拖动的时候调用
     * @param seekBar
     */
    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}
