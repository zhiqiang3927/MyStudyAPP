package com.qiang.lib.fun.frame.main;

import android.content.Context;
import android.support.design.widget.TabLayout;

/**
 * 模块：
 * 功能：
 * 作者：周志强
 * 时间：2018年05月31日16时30分
 */
public class MyPageChangeListener extends TabLayout.TabLayoutOnPageChangeListener {
    private Context context;
    private boolean isCall = false;
    public MyPageChangeListener(Context context,TabLayout tabLayout) {
        super(tabLayout);
        this.context = context;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        super.onPageScrolled(position, positionOffset, positionOffsetPixels);
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        super.onPageScrollStateChanged(state);
//        if(!MyiPoleApplication.getInstance().isLoginState() && !isCall){
//            isCall = true;
//            Intent intent = new Intent(context, LoginActivity.class);
//            intent.putExtra("webFrom", "app");
//            context.startActivity(intent);
//        }
    }

    @Override
    public void onPageSelected(int position) {
        super.onPageSelected(position);
    }
}
