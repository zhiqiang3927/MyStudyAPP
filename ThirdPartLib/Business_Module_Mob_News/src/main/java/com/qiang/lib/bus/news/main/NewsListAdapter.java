package com.qiang.lib.bus.news.main;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.qiang.lib.bus.news.R;
import com.qiang.lib.bus.news.data.bean.Story;
import com.qiang.lib.bus.news.detail.NewsDetailActivity;

/**
 * <pre>
 *      Date            ： 2018/7/4 17:23
 *      Author          ： Jackzhou
 *      Email           ： zhiqiang2008.happy@163.com
 *      blog            ： http://www.3927.group
 *      ModuleName      ：
 *      FunctionName    ：
 *      Deprecation     ：
 * </pre>
 */

public class NewsListAdapter extends RecyclerArrayAdapter<Story> {


    public NewsListAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new NewsListHolder(parent);
    }

    private class NewsListHolder extends BaseViewHolder<Story> {

        private TextView mTextView;
        private ImageView mImageView;

        NewsListHolder(ViewGroup parent) {
            super(parent, R.layout.item_news_list);
            mTextView = $(R.id.news_title);
            mImageView = $(R.id.news_image);
        }

        @Override
        public void setData(final Story data) {
            super.setData(data);
            mTextView.setText(data.getTitle());
            Glide.with(getContext())
                    .load(data.getImages()[0])
                    .centerCrop()
                    .crossFade()
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .into(mImageView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getContext(), NewsDetailActivity.class);
                    intent.putExtra("id", data.getId());
                    getContext().startActivity(intent);
                }
            });
        }
    }
}
