package com.ydx.noway.fragment;

import android.graphics.drawable.Drawable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.ydx.noway.R;
import com.ydx.noway.adapter.DataConnAdapter;
import com.ydx.noway.annotation.ViewInject;
import com.ydx.noway.annotation.ViewUtils;
import com.ydx.noway.base.BaseFragment;
import com.ydx.noway.model.Crontab;
import com.ydx.noway.utils.MobileDataUtils;
import com.ydx.noway.utils.UIutils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by LT on 2016/3/23.
 */
public class DataConnFragment extends BaseFragment implements View.OnClickListener {

    private List<Crontab> datas = new ArrayList<Crontab>();

    @ViewInject(R.id.iv_left)
    private ImageView iv_left;
    @ViewInject(R.id.tv_title)
    private TextView tv_title;
    @ViewInject(R.id.iv_right)
    private ImageView iv_right;
    @ViewInject(R.id.recyclerView)
    private RecyclerView mRecyclerView;
    @ViewInject(R.id.btn_data_connect)
    private Button btn_data_connect;
    @ViewInject(R.id.btn_disable_all)
    private Button btn_disable_all;

    @Override
    protected View initView() {
        View view = UIutils.inflate(R.layout.fragment_data_connect);
        ViewUtils.inject(this, view);
        configTitleBar();
        return view;
    }

    private void configTitleBar() {
        tv_title.setText(R.string.tab_data_connection);
    }

    @Override
    protected void initData() {
        super.initData();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        Random random = new Random();
        for (int i=0;i<20;i++){
            Crontab dataConn = new Crontab();
            dataConn.setType(random.nextInt(2));
            dataConn.setStatus(random.nextInt(2));
            datas.add(dataConn);
        }
        setBtnDataConnectState();
        mRecyclerView.setAdapter(new DataConnAdapter(mContext, datas));

        btn_data_connect.setOnClickListener(this);
        btn_disable_all.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_data_connect:
                setBtnDataConnectState();
                break;
            case R.id.btn_disable_all:

                break;
        }
    }

    private void setBtnDataConnectState() {
        boolean isEnable = MobileDataUtils.getMobileDataState(mContext, null);
        Drawable image = null;
        if(isEnable){
            image = getResources().getDrawable(R.mipmap.icon_network_close);
        }else{
            image = getResources().getDrawable(R.mipmap.icon_network_open);
        }
        // 不能少，少了不会显示图片
        image.setBounds(0, 0, image.getMinimumWidth(),
                image.getMinimumHeight());
        btn_data_connect.setCompoundDrawables(null, null, image, null);
        MobileDataUtils.setMobileData(mContext,!isEnable);
    }
}
