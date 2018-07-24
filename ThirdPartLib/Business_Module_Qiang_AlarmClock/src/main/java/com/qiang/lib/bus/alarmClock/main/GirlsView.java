package com.qiang.lib.bus.alarmClock.main;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewStub;
import android.widget.FrameLayout;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.qiang.lib.bus.alarmClock.Constants;
import com.qiang.lib.bus.alarmClock.R;
import com.qiang.lib.bus.alarmClock.data.bean.Girls;
import com.qiang.lib.bus.alarmClock.girl.GirlActivity;
import com.qiang.lib.fun.common.base.BaseContextUtils;

import java.util.ArrayList;
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

public class GirlsView extends FrameLayout implements GirlsContract.View, SwipeRefreshLayout.OnRefreshListener, RecyclerArrayAdapter.OnLoadMoreListener {

    private GirlsContract.Presenter mPresenter;
    private boolean mActive;

    private EasyRecyclerView mGirlsRecyclerView;
    private ViewStub mNetworkErrorLayout;
    private View networkErrorView;
    private GirlsAdapter mAdapter;
    private ArrayList<Girls> mData;
    private int page = 1;
    private int size = 20;

    public GirlsView(Context context) {
        super(context);
        initView();
    }

    public GirlsView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }


    private void initView() {
        inflate(getContext(), R.layout.view_girls_content, this);
        mNetworkErrorLayout = (ViewStub) findViewById(R.id.network_error_layout);
        mGirlsRecyclerView = (EasyRecyclerView) findViewById(R.id.girls_recycler_view);
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        mGirlsRecyclerView.setLayoutManager(staggeredGridLayoutManager);
        mAdapter = new GirlsAdapter(getContext());

        mGirlsRecyclerView.setAdapterWithProgress(mAdapter);
        mGirlsRecyclerView.setRefreshingColor(
                ContextCompat.getColor(getContext(), R.color.colorPrimary),
                ContextCompat.getColor(getContext(), android.R.color.holo_blue_light),
                ContextCompat.getColor(getContext(), android.R.color.holo_green_light)
        );
        mAdapter.setMore(R.layout.layout_load_more, this);
        mAdapter.setNoMore(R.layout.layout_load_no_more);
        mAdapter.setError(R.layout.layout_load_error);
        mAdapter.setOnMyItemClickListener(new GirlsAdapter.OnMyItemClickListener() {
            @Override
            public void onItemClick(int position, BaseViewHolder holder) {
                Intent intent = new Intent(BaseContextUtils.getActivity(GirlsView.this), GirlActivity.class);
                intent.putParcelableArrayListExtra(Constants.INTENT_GIRLS, mData);
                intent.putExtra(Constants.INTENT_INDEX, position);
                ActivityOptionsCompat options = ActivityOptionsCompat.makeScaleUpAnimation(holder.itemView, holder.itemView.getWidth() / 2, holder.itemView.getHeight() / 2, 0, 0);
                BaseContextUtils.getActivity(GirlsView.this).startActivity(intent, options.toBundle());
            }
        });

        mGirlsRecyclerView.setRefreshListener(this);

        mData = new ArrayList<>();
        mActive = true;
    }


    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        mActive = true;
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        mActive = false;
    }

    @Override
    public void setPresenter(GirlsContract.Presenter presenter) {
        this.mPresenter = presenter;
    }

    @Override
    public boolean isActive() {
        return mActive;
    }


    @Override
    public void refresh(List<Girls> data) {
        mData.clear();
        mData.addAll(data);
        mAdapter.clear();
        mAdapter.addAll(data);
    }

    @Override
    public void load(List<Girls> data) {
        mData.addAll(data);
        mAdapter.addAll(data);
    }

    @Override
    public void showError() {
        mGirlsRecyclerView.showError();

        if (networkErrorView != null) {
            networkErrorView.setVisibility(View.VISIBLE);
            return;
        }

        networkErrorView = mNetworkErrorLayout.inflate();
    }

    @Override
    public void showNormal() {
        if (networkErrorView != null) {
            networkErrorView.setVisibility(View.GONE);
        }
    }


    @Override
    public void onLoadMore() {
        if (mData.size() % size == 0) {
            page++;
            mPresenter.getGirls(size, page, false);
        }
    }

    @Override
    public void onRefresh() {
        mPresenter.getGirls(size, page, true);
        page = 1;
    }
}
