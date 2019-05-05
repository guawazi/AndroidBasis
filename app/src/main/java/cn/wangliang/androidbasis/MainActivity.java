package cn.wangliang.androidbasis;

import android.widget.Button;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.github.guawazi.common.ui.base.BaseActivity;

@Route(path = "/app/main")
public class MainActivity extends BaseActivity {

    Button mButton1;

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initEventAndData() {
        mButton1 = findViewById(R.id.btn_1);
        mButton1.setOnClickListener(v -> ARouter.getInstance()
                .build("/web/main")
                .navigation());
    }
}
