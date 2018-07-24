package com.qiang.lib.fun.frame.main;

import android.content.Context;

import com.qiang.lib.fun.frame.R;
import com.qiang.lib.fun.frame.module.MyFocusFragment;
import com.qiang.lib.fun.frame.module.MyInfoFragment;
import com.qiang.lib.fun.frame.module.MyTestFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * 模块：
 * 功能：
 * 作者：周志强
 * 时间：2018年05月31日13时08分
 */
public class MainFrameUtils {
    //TODO 整合成集合
    public static List<MainFrameItemEntity> getMainFrameBottomListData(Context context) {
        List<MainFrameItemEntity> mainFrameItemEntities = new ArrayList<>();

        mainFrameItemEntities.add(0,
                new MainFrameItemEntity.Builder(
                        new MyInfoFragment(),
                        "我的",
                        R.drawable.selector_mainframe_bottom_tab_app,
                        true,
                        "app")
                        .showNumText(false)
                        .numTextString("")
                        .build());
        mainFrameItemEntities.add(0,
                new MainFrameItemEntity.Builder(
                        new MyTestFragment(),
                        "测试",
                        R.drawable.selector_mainframe_bottom_tab_news,
                        true,
                        "app")
                        .showNumText(false)
                        .numTextString("")
                        .build());
        mainFrameItemEntities.add(0,
                new MainFrameItemEntity.Builder(
                        new MyFocusFragment(),
                        "关注",
                        R.drawable.selector_mainframe_bottom_tab_news,
                        false,
                        "news")
                        .showNumText(false)
                        .numTextString("0")
                        .build());
        return mainFrameItemEntities;
    }
}
