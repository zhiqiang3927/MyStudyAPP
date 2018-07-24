package com.qiang.lib.bus.alarmClock.main;


import com.qiang.lib.bus.alarmClock.data.bean.Girls;
import com.qiang.lib.fun.common.base.BasePresenter;
import com.qiang.lib.fun.common.base.BaseView;

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

public interface GirlsContract {

    interface View extends BaseView<Presenter> {

        /**
         * View 的存活状态
         *
         * @return true or false
         */
        boolean isActive();

        void refresh(List<Girls> data);

        void load(List<Girls> data);

        void showError();

        void showNormal();

    }

    interface Presenter extends BasePresenter {

        void getGirls(int page, int size, boolean isRefresh);

    }


}
