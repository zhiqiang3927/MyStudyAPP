package debug;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.qiang.lib.bus.news.detail.NewsDetailActivity;


/**
 * @Date on 2018/6/29 14:32
 * @Author Jackzhou
 * @Email zhiqiang2008.happy@163.com
 * @OrgURL http://www.3927.group
 * @ModuleName
 * @FunctionName
 * @Deprecation 组件开发模式下，用于传递数据的启动Activity，集成模式下无效
 */
public class LauncherActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //在这里传值给需要调试的Activity
        Intent intent = new Intent(this, NewsDetailActivity.class);
        intent.putExtra("id", "9500116");
        startActivity(intent);
        finish();
    }
}
