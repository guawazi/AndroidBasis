package com.github.guawazi.common.ui.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.github.guawazi.common.AppManager;

import butterknife.ButterKnife;
import butterknife.Unbinder;


public abstract class BaseActivity extends AppCompatActivity {
    private Unbinder mUnBinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        mUnBinder = ButterKnife.bind(this);
        onViewCreated(savedInstanceState);
        initEventAndData();
        AppManager.getAppManager().addActivity(this);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        AppManager.getAppManager().removeActivity(this);
        mUnBinder.unbind();
    }

    protected void onViewCreated(Bundle savedInstanceState) {

    }

    protected abstract int getLayout();

    protected abstract void initEventAndData();


}
