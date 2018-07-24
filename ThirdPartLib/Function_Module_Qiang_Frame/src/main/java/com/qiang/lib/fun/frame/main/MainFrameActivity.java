package com.qiang.lib.fun.frame.main;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.qiang.lib.fun.common.arouter.BaseARouterPath;
import com.qiang.lib.fun.common.base.view.BaseActivityByID;
import com.qiang.lib.fun.common.base.BaseViewManager;
import com.qiang.lib.fun.common.utils.MyToastUtil;
import com.qiang.lib.fun.frame.R;

import java.util.List;

/**
 * <pre>
 *      Date            ： 2018/7/10 13:09
 *      Author          ： Jackzhou
 *      Email           ： zhiqiang2008.happy@163.com
 *      blog            ： http://www.3927.group
 *      ModuleName      ：
 *      FunctionName    ：
 *      Deprecation     ：
 * </pre>
 */

@Route(path = BaseARouterPath.path_fun_common_frame_main)
public class MainFrameActivity extends BaseActivityByID {

    private MainFramePageAdapter mainFramePageAdapter;
    private List<MainFrameItemEntity> mainFrameBottomListData;
    private String whereToWeb = "";

    private ViewPager mainframe_viewPager;
    private TabLayout mainframe_tabLayout;

    private long mExitTime = 0;


    @Override
    public int loadViewLayout() {
        return R.layout.function_common_frame_activity_main_layout;
    }

    @Override
    public boolean isLoadTopTab() {
        return false;
    }

    @Override
    public void findViewById() {
        mainframe_viewPager = findViewById(R.id.mainframe_viewPager);
        mainframe_tabLayout = findViewById(R.id.mainframe_tabLayout);
    }

    @Override
    public void viewParam() {
        Intent intent = getIntent();
        whereToWeb = intent.getStringExtra("webFrom");

        mainFrameBottomListData = MainFrameUtils.getMainFrameBottomListData(this);
        mainFramePageAdapter = new MainFramePageAdapter(getSupportFragmentManager(), mainFrameBottomListData);
        mainframe_viewPager.setAdapter(mainFramePageAdapter);
        mainframe_tabLayout.setupWithViewPager(mainframe_viewPager);
        mainframe_viewPager.addOnPageChangeListener(new MyPageChangeListener(this, mainframe_tabLayout));
        mainframe_tabLayout.addOnTabSelectedListener(new MyViewPagerOnTabSelectedListener(this, mainframe_viewPager));

        for (int i = 0; i < mainframe_tabLayout.getTabCount(); i++) {
            TabLayout.Tab tab = mainframe_tabLayout.getTabAt(i);
            MainFrameItemEntity mainFrameItemEntity = mainFrameBottomListData.get(i);
            //设置选中tab
            if ("".equals(whereToWeb) || null == whereToWeb) {
                if (mainFrameItemEntity.isCurrent()) {
                    tab.select();
                }
            } else if (whereToWeb.equals(mainFrameItemEntity.getMyTag())) {
                mainFrameItemEntity.setCurrent(true);
                tab.select();
            }
            View custom_tab_item = View.inflate(this, R.layout.item_mainframe_bottom_layout, null);
            tab.setCustomView(custom_tab_item);
            ImageView item_mainframe_bottom_image = custom_tab_item.findViewById(R.id.item_mainframe_bottom_image);
            TextView item_mainframe_bottom_num_text = custom_tab_item.findViewById(R.id.item_mainframe_bottom_num_text);
            TextView item_mainframe_bottom_name_text = custom_tab_item.findViewById(R.id.item_mainframe_bottom_name_text);
            item_mainframe_bottom_image.setImageResource(mainFrameItemEntity.getTitleIconSelector());
            if (mainFrameItemEntity.isShowNumText()) {
                item_mainframe_bottom_num_text.setVisibility(View.VISIBLE);
                item_mainframe_bottom_num_text.setText(mainFrameItemEntity.getNumTextString());
            } else {
                item_mainframe_bottom_num_text.setVisibility(View.INVISIBLE);
            }
            item_mainframe_bottom_name_text.setText(mainFrameItemEntity.getTitleText());
        }
    }


    @Override
    public void setListener() {

    }

    @Override
    public void onClickEvent(View paramView) {

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            //两秒之内按返回键就会退出
            if ((System.currentTimeMillis() - mExitTime) > 2000) {
                MyToastUtil.showInfoToast(R.string.app_exit_hint, false);
                mExitTime = System.currentTimeMillis();
            } else {
                BaseViewManager.getInstance().exitApp(this);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}
