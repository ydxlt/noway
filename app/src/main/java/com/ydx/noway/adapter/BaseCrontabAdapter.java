package com.ydx.noway.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
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
 * Created by LT on 2016/3/24.
 */
public abstract class BaseCrontabAdapter extends RecyclerView.Adapter<BaseCrontabAdapter.ViewHolder>{

    protected List<Crontab> mDatas;
    protected Context context;
    protected View mHeadView;
    public static final int TYPE_HEADER = 0;
    public static final int TYPE_COMMON = 1;


    public BaseCrontabAdapter(Context context, List<Crontab> datas) {
        this.context = context;
        this.mDatas = datas;
    }

    public void setHeadView(View headView){
        mHeadView = headView;
        notifyItemInserted(0);
    }

    public View getHeadView(){
        return mHeadView;
    }

    @Override
    public int getItemViewType(int position) {
        if(mHeadView == null){
            return TYPE_COMMON;
        }
        return position == 0?TYPE_HEADER:TYPE_COMMON;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType == TYPE_HEADER)
            return new ViewHolder(mHeadView);
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_crontab, null);
        return new ViewHolder(layout);
    }

    /**
     * 获得真实的位置，因为加了position后位置会变化
     * @return
     */
    public int getRealPosition(RecyclerView.ViewHolder holder){
        int layoutPosition = holder.getLayoutPosition();
        return mHeadView == null?layoutPosition:(layoutPosition-1);
    }

    @Override
    public abstract void onBindViewHolder(ViewHolder holder, int position);

    @Override
    public int getItemCount() {
        return mHeadView == null?mDatas.size():(mDatas.size()+1);
    }

    /**
     * 处理LayoutManager为GridLayoutManager的情况
     * @param recyclerView
     */
    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
        if(manager instanceof GridLayoutManager) {
            final GridLayoutManager gridManager = ((GridLayoutManager) manager);
            gridManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    return getItemViewType(position) == TYPE_HEADER
                            ? gridManager.getSpanCount() : 1;
                }
            });
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        @ViewInject(R.id.tv_type)
        public TextView tv_type;
        @ViewInject(R.id.iv_type)
        public ImageView iv_type;
        @ViewInject(R.id.iv_toggle)
        public ImageView iv_toggle;

        public ViewHolder(View itemView) {
            super(itemView);
            if(itemView == mHeadView){
                return;
            }
            ViewUtils.inject(this, itemView);
        }
    }
}
