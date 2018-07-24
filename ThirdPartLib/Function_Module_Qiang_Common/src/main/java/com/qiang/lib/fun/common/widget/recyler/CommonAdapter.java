package com.qiang.lib.fun.common.widget.recyler;

import android.content.Context;
import android.view.LayoutInflater;

import com.qiang.lib.fun.common.widget.recyler.base.ItemViewDelegate;
import com.qiang.lib.fun.common.widget.recyler.base.ViewHolder;

import java.util.List;


/**
 * Created by zhy on 16/4/9.
 */
public abstract class CommonAdapter<T> extends MultiItemTypeAdapter<T>
{
    protected Context mContext;
    protected int mLayoutId;
    protected List<T> mDatas;
    protected LayoutInflater mInflater;

    public CommonAdapter(final Context context, final int layoutId, List<T> datas)
    {
        super(context, datas);
        mContext = context;
        mInflater = LayoutInflater.from(context);
        mLayoutId = layoutId;
        mDatas = datas;

        addItemViewDelegate(new ItemViewDelegate<T>()
        {
            @Override
            public int getItemViewLayoutId()
            {
                return layoutId;
            }

            @Override
            public boolean isForViewType( T item, int position)
            {
                return true;
            }

            @Override
            public void convert(ViewHolder holder, T t, int position)
            {
                CommonAdapter.this.convert(holder, t, position);
            }
        });
    }

    public List<T> getDatas(){
        return mDatas;
    }

    protected abstract void convert(ViewHolder holder, T t, int position);


}
