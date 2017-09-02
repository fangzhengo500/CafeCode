package com.luuso.base.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * @project： CafeCode
 * @package： com.luuso.base.adapter
 * @class: BaseListAdapter
 * @author: 陆伟
 * @Date: 2017/9/2 14:52
 * @desc： 对ViewHolder的封装，以及更方便的控制ListView滑动过程中不加载图片
 */

public abstract class BaseListAdapter<T> extends BaseAdapter implements
        AbsListView.OnScrollListener {

    protected Collection<T> mDatas;
    protected int mItemLayoutId;
    protected AbsListView mListView;
    protected boolean isScrolling;
    protected Context mContext;
    protected LayoutInflater mInflater;

    private AbsListView.OnScrollListener listener;

    public BaseListAdapter(AbsListView listView, Collection<T> datas, int itemLayoutId) {
        if (datas == null) {
            datas = new ArrayList<>(0);
        }
        this.mDatas = datas;
        mItemLayoutId = itemLayoutId;
        mListView = listView;
        mContext = listView.getContext();
        this.mInflater = LayoutInflater.from(mContext);
        mListView.setOnScrollListener(this);
    }

    public void setDatas(Collection<T> datas) {
        if (datas == null) {
            datas = new ArrayList<>(0);
        }
        this.mDatas = datas;
        notifyDataSetChanged();
    }

    public void setListener(AbsListView.OnScrollListener listener) {
        this.listener = listener;
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public T getItem(int position) {
        if (mDatas instanceof List) {
            return ((List<T>) mDatas).get(position);
        } else if (mDatas instanceof Set) {
            return new ArrayList<T>(mDatas).get(position);
        } else {
            return null;
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        IViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext())
                    .inflate(mItemLayoutId, parent, false);
            viewHolder = ListHolder.newViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ListHolder) convertView.getTag();
        }
        convert(viewHolder, getItem(position), position, isScrolling);
        return convertView;
    }


    public abstract void convert(IViewHolder helper, T item, int position,
                                 boolean isScrolling);


    @Override
    public void onScrollStateChanged(AbsListView absListView, int scrollState) {
        if (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE) {
            isScrolling = false;
            this.notifyDataSetChanged();
        } else {
            isScrolling = true;
        }

        if (listener != null) {
            listener.onScrollStateChanged(absListView, scrollState);
        }
    }

    @Override
    public void onScroll(AbsListView absListView, int firstVisibleItem,
                         int visibleItemCount, int totalItemCount) {
        if (listener != null) {
            listener.onScroll(absListView, firstVisibleItem, visibleItemCount,
                    totalItemCount);
        }
    }
}
