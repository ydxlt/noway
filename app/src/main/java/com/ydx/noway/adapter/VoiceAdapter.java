package com.ydx.noway.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ydx.noway.R;
import com.ydx.noway.annotation.ViewInject;
import com.ydx.noway.annotation.ViewUtils;
import com.ydx.noway.base.BaseRecyclerAdapter;
import com.ydx.noway.model.Crontab;
import com.ydx.noway.utils.UIutils;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by LT on 2016/3/22.
 */
public class VoiceAdapter extends BaseRecyclerAdapter<Crontab>{

    public VoiceAdapter(List<Crontab> mDatas) {
        super(mDatas);
    }

    @Override
    public RecyclerView.ViewHolder onCreate(ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_crontab, null);
        return new MyHolder(layout);
    }

    @Override
    public void onBind(RecyclerView.ViewHolder viewHolder, int RealPosition, Crontab data) {
        ((MyHolder)viewHolder).tv_type.setText(mDatas.get(RealPosition).getType()==0?"媒体音量":"通话音量");
        ((MyHolder)viewHolder).iv_toggle.setImageResource(mDatas.get(RealPosition).getStatus() == 0 ? R.mipmap.icon_toggle_close : R.mipmap.icon_toggle_open);
        ((MyHolder)viewHolder).iv_type.setImageResource(mDatas.get(RealPosition).getType() == 0 ? R.mipmap.icon_voice_close : R.mipmap.icon_voice_open);
    }

    public class MyHolder extends RecyclerView.ViewHolder{

        @ViewInject(R.id.tv_type)
        public TextView tv_type;
        @ViewInject(R.id.iv_type)
        public ImageView iv_type;
        @ViewInject(R.id.iv_toggle)
        public ImageView iv_toggle;

        public MyHolder(View itemView) {
            super(itemView);
            if(itemView == mHeaderView){
                return;
            }
            ViewUtils.inject(this, itemView);
        }
    }
}
