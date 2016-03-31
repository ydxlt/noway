package com.ydx.noway.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ydx.noway.R;
import com.ydx.noway.annotation.ViewInject;
import com.ydx.noway.annotation.ViewUtils;
import com.ydx.noway.model.Crontab;
import com.ydx.noway.utils.UIutils;

import java.util.List;


/**
 * Created by LT on 2016/3/22.
 */
public class DataConnAdapter extends BaseCrontabAdapter{


    public DataConnAdapter(Context context, List<Crontab> datas) {
        super(context, datas);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if(getItemViewType(position) == TYPE_HEADER)
            return;
        holder.tv_type.setText(mDatas.get(position).getType()==0?"关闭连接":"打开连接");
        holder.iv_toggle.setImageResource(mDatas.get(position).getStatus() == 0 ? R.mipmap.icon_toggle_close : R.mipmap.icon_toggle_open);
        holder.iv_type.setImageResource(mDatas.get(position).getType()==0?R.mipmap.icon_data_toggle_close:R.mipmap.icon_data_toggle_open);
    }
}
