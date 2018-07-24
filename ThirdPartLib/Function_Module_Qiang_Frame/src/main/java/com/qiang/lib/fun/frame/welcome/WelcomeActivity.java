package com.qiang.lib.fun.frame.welcome;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.qiang.lib.fun.common.base.view.BaseActivityByID;
import com.qiang.lib.fun.frame.R;
import com.qiang.lib.fun.frame.main.MainFrameActivity;

public class WelcomeActivity extends BaseActivityByID {
    /**
     * 启动模式：0：全屏slogan；1：广告+slogan；
     */
    private int welcomeMode = 0;
    private int count = 6;
    private boolean isSkip = false;
    private Animation function_common_frame_welcome_countdown_anim;
    private FrameLayout function_common_frame_welcome_business_layout;
    private FrameLayout function_common_frame_welcome_slogan_layout;
    private TextView function_common_frame_welcome_countdown_text;

    @Override
    public int loadViewLayout() {
        return R.layout.function_common_frame_activity_welcome_layout;
    }

    @Override
    public boolean isLoadTopTab() {
        return false;
    }

    @Override
    public void findViewById() {
        function_common_frame_welcome_business_layout = findViewById(R.id.function_common_frame_welcome_business_layout);
        function_common_frame_welcome_slogan_layout = findViewById(R.id.function_common_frame_welcome_slogan_layout);
        function_common_frame_welcome_countdown_text = findViewById(R.id.function_common_frame_welcome_countdown_text);

    }

    @Override
    public void setListener() {
        function_common_frame_welcome_countdown_text.setOnClickListener(this);
    }

    @Override
    public void viewParam() {
        initWelcomeMode();
        initData();

    }

    /**
     * 初始化数据
     */
    private void initData() {

    }

    /**
     * 初始化启动页的模式
     */
    private void initWelcomeMode() {
        if (getWelcomeMode() == 0) {
            function_common_frame_welcome_business_layout.setVisibility(View.GONE);
            function_common_frame_welcome_slogan_layout.setVisibility(View.VISIBLE);
        } else if (getWelcomeMode() == 1) {
            function_common_frame_welcome_business_layout.setVisibility(View.VISIBLE);
            function_common_frame_welcome_slogan_layout.setVisibility(View.GONE);
        }
        function_common_frame_welcome_countdown_anim = AnimationUtils.loadAnimation(this, R.anim.function_common_frame_welcome_countdown_anim);

        handler.sendEmptyMessageDelayed(0, 0);
    }

    @Override
    public void onClickEvent(View view) {
        if (view.getId() == R.id.function_common_frame_welcome_countdown_text) {
            isSkip = true;
            gotoNextActivity();
        }

    }

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            if (msg.what == 0) {
                if (!isSkip) {
                    function_common_frame_welcome_countdown_text.setText("倒计时" + getCount() + "s,点击跳过");
                    handler.sendEmptyMessageDelayed(0, 1000);
                    function_common_frame_welcome_countdown_anim.reset();
                    function_common_frame_welcome_countdown_text.startAnimation(function_common_frame_welcome_countdown_anim);
                }
            }

        }
    };

    private int getCount() {
        count--;
        if (count == 0) {
            gotoNextActivity();
        }
        return count;
    }

    /**
     * 跳转到下个页面
     */
    private void gotoNextActivity() {
        Intent intent = new Intent(this, MainFrameActivity.class);
        startActivity(intent);
        finish();
    }


    public int getWelcomeMode() {
        return welcomeMode;
    }

    public void setWelcomeMode(int welcomeMode) {
        this.welcomeMode = welcomeMode;
    }
}
