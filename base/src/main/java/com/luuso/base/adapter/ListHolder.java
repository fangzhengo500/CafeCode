package com.luuso.base.adapter;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.util.SparseArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * @project： CafeCode
 * @package： com.luuso.base.adapter
 * @class: ListHolder
 * @author: 陆伟
 * @Date: 2017/9/2 18:40
 * @desc： TODO
 */

public class ListHolder implements IViewHolder {
    private final SparseArray<View> mViews;
    private final View itemVie;

    private ListHolder(View itemView) {
        mViews = new SparseArray<View>(8);
        itemVie = itemView;
        itemVie.setTag(this);
    }


    public static ListHolder newViewHolder(View itemView) {
        return new ListHolder(itemView);
    }

    @Override
    public <T extends View> T getView(int id) {
        View view = mViews.get(id);
        if (view == null) {
            view = itemVie.findViewById(id);
            mViews.put(id, view);
        }
        return (T) view;
    }

    @Override
    public View getItemView() {
        return itemVie;
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

