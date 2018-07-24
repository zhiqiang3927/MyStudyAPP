package com.qiang.lib.fun.frame.main;


import com.qiang.lib.fun.frame.base.BaseItemEntity;
import com.qiang.lib.fun.frame.base.BaseMainActivityFragment;

/**
 * 模块：主界面单个页面实体类
 * 功能：
 * 作者：周志强
 * 时间：2018年05月31日09时34分
 */
public class MainFrameItemEntity extends BaseItemEntity {
    /**
     * viewPager 嵌套的 Fragment
     */
    private BaseMainActivityFragment baseMainActivityFragment;
    /**
     * 当前是否被选中
     */
    private boolean isCurrent = false;

    /**
     * 页面Tag
     */
    private String myTag;

    private MainFrameItemEntity(Builder builder) {
        setTitleText(builder.titleText);
        setTitleColorSelector(builder.titleColorSelector);
        setTitleIconSelector(builder.titleIconSelector);
        setShowNumText(builder.showNumText);
        setBaseMainActivityFragment(builder.baseMainActivityFragment);
        setNumIconID(builder.numIconID);
        setCurrent(builder.isCurrent);
        setNumTextString(builder.numTextString);
        setMyTag(builder.myTag);
    }

    public BaseMainActivityFragment getBaseMainActivityFragment() {
        return baseMainActivityFragment;
    }

    public void setBaseMainActivityFragment(BaseMainActivityFragment baseMainActivityFragment) {
        this.baseMainActivityFragment = baseMainActivityFragment;
    }

    public boolean isCurrent() {
        return isCurrent;
    }

    public void setCurrent(boolean current) {
        isCurrent = current;
    }

    public String getMyTag() {
        return myTag;
    }

    public void setMyTag(String myTag) {
        this.myTag = myTag;
    }


    public static final class Builder {
        private String titleText;
        private int titleColorSelector;
        private int titleIconSelector;
        private boolean showNumText;
        private BaseMainActivityFragment baseMainActivityFragment;
        private int numIconID;
        private boolean isCurrent;
        private String numTextString;
        private String myTag;

        public Builder(BaseMainActivityFragment baseMainActivityFragment,String titleText,int titleIconSelector,boolean isCurrent,String myTag) {
            this.baseMainActivityFragment = baseMainActivityFragment;
            this.titleText = titleText;
            this.titleIconSelector = titleIconSelector;
            this.isCurrent = isCurrent;
            this.myTag = myTag;
        }

        public Builder titleColorSelector(int val) {
            titleColorSelector = val;
            return this;
        }

        public Builder showNumText(boolean val) {
            showNumText = val;
            return this;
        }

        public Builder numIconID(int val) {
            numIconID = val;
            return this;
        }

        public Builder numTextString(String val) {
            numTextString = val;
            return this;
        }

        public MainFrameItemEntity build() {
            return new MainFrameItemEntity(this);
        }
    }
}
