package com.luuso.base.adapter;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.View;

/**
 * @project： CafeCode
 * @package： com.luuso.base.adapter
 * @class: IViewHolder
 * @author: 陆伟
 * @Date: 2017/9/2 19:26
 * @desc： TODO
 */

interface IViewHolder {
    <T extends View> T getView(int id);

    View getItemView();

    IViewHolder setText(int id, CharSequence text);

    IViewHolder setImageResource(int viewId, int drawableId);

    IViewHolder setImageBitmap(int viewId, Bitmap bm);

    IViewHolder setImageDrawable(int id, Drawable drawable);
}
