package com.jade.library.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.jade.library.mvp.BasePresenter;

/**
 * Created by jade on 16-8-3.
 */
public abstract class BaseActivity<V, T extends BasePresenter<V>> extends AppCompatActivity {
    protected T mPresenter;
    protected Context mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        mPresenter = initPresenter();
        if (mPresenter != null) {
            mPresenter.attach((V) this);
        }
    }

    /**
     * 生成Presenter
     *
     * @return T
     */
    protected abstract T initPresenter();

    @Override
    protected void onDestroy() {
        if (mPresenter != null) {
            mPresenter.detach();
        }
        super.onDestroy();
    }

    /**
     * 弹出Toast
     *
     * @param msg
     */
    public void showToast(String msg) {
        Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
    }


    // -------------------------------------------打开Activity-------------------------------------------
    protected void openActivity(Class<?> clazz) {
        openActivity(clazz, null);
    }

    protected void openActivity(String action) {
        openActivity(action, null);
    }

    protected void openActivity(Class<?> clazz, Bundle bundle) {
        Intent intent = new Intent(this, clazz);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    protected void openActivity(String action, Bundle bundle) {
        Intent intent = new Intent(action);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    protected void openActivityForResult(Class<?> clazz, int requestCode, Bundle bundle) {
        Intent intent = new Intent(this, clazz);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, requestCode);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    protected void openActivityForResult(Class<?> clazz, int requestCode) {
        openActivityForResult(clazz, requestCode, null);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    public void finish() {
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        super.finish();
    }
}
