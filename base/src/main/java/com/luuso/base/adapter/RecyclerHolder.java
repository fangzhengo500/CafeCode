package com.luuso.base.adapter;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * @project： CafeCode
 * @package： com.luuso.base.adapter
 * @class: RecyclerHolder
 * @author: 陆伟
 * @Date: 2017/9/2 21:03
 * @desc： TODO
 */

public class RecyclerHolder extends RecyclerView.ViewHolder implements IViewHolder {
    private final SparseArray<View> mViews;


    public RecyclerHolder(View itemView) {
        super(itemView);
        this.mViews = new SparseArray<>(8);
    }

    @Override
    public View getItemView() {
        return itemView;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T extends View> T getView(int id) {
        View view = mViews.get(id);
        if (view == null) {
            view = itemView.findViewById(id);
            mViews.put(id, view);
        }
        return (T) view;
    }


    @Override
    public IViewHolder setText(int id, CharSequence text) {
        View view = getView(id);
        if (view instanceof TextView) {
            ((TextView) view).setText(text);
        }
        return this;
    }

    @Override
    public IViewHolder setImageResource(int id, int resId) {
        View view = getView(id);
        if (view instanceof ImageView) {
            ((ImageView) view).setImageResource(resId);
        } else {
            throw new IllegalStateException(String.
                    format("view = %s is not ImageView can not setImageResource()", view));
        }
        return this;
    }

    @Override
    public IViewHolder setImageBitmap(int id, Bitmap bm) {
        View view = getView(id);
        if (view instanceof ImageView) {
            ((ImageView) view).setImageBitmap(bm);
        } else {
            throw new IllegalStateException(String.
                    format("view = %s is not ImageView can not setImageBitmap()", view));
        }
        return this;
    }

    @Override
    public IViewHolder setImageDrawable(int id, Drawable drawable) {
        View view = getView(id);
        if (view instanceof ImageView) {
            ((ImageView) view).setImageDrawable(drawable);
        } else {
            throw new IllegalStateException(String.
                    format("view = %s is not ImageView can not setImageDrawable()", view));
        }
        return this;
    }
}
