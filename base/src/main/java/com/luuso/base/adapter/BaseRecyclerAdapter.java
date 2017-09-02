package com.luuso.base.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @project： CafeCode
 * @package： com.luuso.base.adapter
 * @class: BaseRecyclerAdapter
 * @author: 陆伟
 * @Date: 2017/9/2 21:10
 * @desc： RecyclerView的万能适配器，适配任何一个RecyclerView
 */

public abstract class BaseRecyclerAdapter<T> extends RecyclerView.Adapter<RecyclerHolder> {

    protected List<T> realDatas;
    protected final int mItemLayoutId;
    protected Context mContext;
    private OnItemClickListener listener;

    public BaseRecyclerAdapter(RecyclerView recyclerView,
                               Collection<T> datas, int itemLayoutId) {
        if (datas == null) {
            realDatas = new ArrayList<>(0);
        } else if (datas instanceof List) {
            realDatas = (List<T>) datas;
        } else {
            realDatas = new ArrayList<>(datas);
        }
        mItemLayoutId = itemLayoutId;
        mContext = recyclerView.getContext();
    }

    public interface OnItemClickListener {
        void onItemClick(View view, Object data, int position);
    }

    public void setOnItemClickListener(OnItemClickListener l) {
        listener = l;
    }

    public View.OnClickListener getOnClickListener(final int position) {
        return new View.OnClickListener() {
            @Override
            public void onClick(@Nullable View v) {
                if (listener != null && v != null) {
                    listener.onItemClick(v, realDatas.get(position), position);
                }
            }
        };
    }

    @Override
    public RecyclerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(mContext)
                .inflate(mItemLayoutId, parent, false);
        return new RecyclerHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerHolder holder, int position) {
        convert(holder, realDatas.get(position), position);
        holder.itemView.setOnClickListener(getOnClickListener(position));
    }

    @Override
    public int getItemCount() {
        return realDatas.size();
    }

    /**
     * Recycler适配器填充方法
     *
     * @param holder viewholder
     * @param item   javabean
     */
    public abstract void convert(RecyclerHolder holder, T item, int position);
}
