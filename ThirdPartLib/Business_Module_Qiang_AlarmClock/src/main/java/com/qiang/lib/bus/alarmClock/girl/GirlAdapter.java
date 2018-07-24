package com.qiang.lib.bus.alarmClock.girl;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.github.chrisbanes.photoview.PhotoView;
import com.qiang.lib.bus.alarmClock.R;
import com.qiang.lib.bus.alarmClock.data.bean.Girls;

import java.util.List;

/**
 * <pre>
 *      Date            ： 2018/7/4 17:26
 *      Author          ： Jackzhou
 *      Email           ： zhiqiang2008.happy@163.com
 *      blog            ： http://www.3927.group
 *      ModuleName      ：
 *      FunctionName    ：
 *      Deprecation     ：
 * </pre>
 */

public class GirlAdapter extends PagerAdapter {

    private Context mContext;
    private List<Girls> mData;
    private LayoutInflater layoutInflater;
    private View mCurrentView;

    public GirlAdapter(Context context, List<Girls> data) {
        mContext = context;
        mData = data;
        layoutInflater = LayoutInflater.from(this.mContext);
    }

    @Override
    public int getCount() {
        if (mData == null) {
            return 0;
        }
        return mData.size();
    }

    @Override
    public void setPrimaryItem(ViewGroup container, int position, Object object) {
        super.setPrimaryItem(container, position, object);
        mCurrentView = (View) object;
    }

    @Override
    public View instantiateItem(ViewGroup container, int position) {
        final String imageUrl = mData.get(position).getUrl();
        View view = layoutInflater.inflate(R.layout.item_girl_detail, container, false);
        PhotoView imageView = (PhotoView) view.findViewById(R.id.girl_image);
        Glide.with(mContext)
                .load(imageUrl)
                .thumbnail(0.2f)
                .into(imageView);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    public View getPrimaryItem() {
        return mCurrentView;
    }
}
