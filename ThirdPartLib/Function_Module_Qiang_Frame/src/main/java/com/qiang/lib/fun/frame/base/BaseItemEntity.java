package com.qiang.lib.fun.frame.base;

/**
 * 模块：
 * 功能：模块信息基础类
 * 作者：周志强
 * 时间：2018年05月31日13时50分
 */
public class BaseItemEntity {
    /**
     * 标题
     */
    private String titleText;
    /**
     * 标题颜色
     */
    private int titleColorSelector;
    /**
     * 图标
     */
    private int titleIconSelector;
    /**
     * 是否显示消息数量
     */
    private boolean showNumText = false;
    /**
     * 消息提示背景图
     */
    private int numIconID;
    /**
     * 消息数量或提示文字
     */
    private String numTextString;

    public String getTitleText() {
        return titleText;
    }

    public void setTitleText(String titleText) {
        this.titleText = titleText;
    }

    public int getTitleColorSelector() {
        return titleColorSelector;
    }

    public void setTitleColorSelector(int titleColorSelector) {
        this.titleColorSelector = titleColorSelector;
    }

    public int getTitleIconSelector() {
        return titleIconSelector;
    }

    public void setTitleIconSelector(int titleIconSelector) {
        this.titleIconSelector = titleIconSelector;
    }

    public boolean isShowNumText() {
        return showNumText;
    }

    public void setShowNumText(boolean showNumText) {
        this.showNumText = showNumText;
    }

    public int getNumIconID() {
        return numIconID;
    }

    public void setNumIconID(int numIconID) {
        this.numIconID = numIconID;
    }

    public String getNumTextString() {
        return numTextString;
    }

    public void setNumTextString(String numTextString) {
        this.numTextString = numTextString;
    }
}
