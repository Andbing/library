package com.jade.library.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.jade.library.R;
import com.jade.library.mvp.BasePresenter;

/**
 * Created by jade on 16-8-3.
 */
public abstract class BaseFragment<V, T extends BasePresenter<V>> extends Fragment {
    protected T mPresenter;
    protected Context mContext;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
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
    public void onDetach() {
        if (mPresenter != null) {
            mPresenter.detach();
        }
        super.onDetach();
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
        Intent intent = new Intent(mContext, clazz);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
        getActivity().overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    protected void openActivity(String action, Bundle bundle) {
        Intent intent = new Intent(action);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
        getActivity().overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    protected void openActivityForResult(Class<?> clazz, int requestCode, Bundle bundle) {
        Intent intent = new Intent(mContext, clazz);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, requestCode);
        getActivity().overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    protected void openActivityForResult(Class<?> clazz, int requestCode) {
        openActivityForResult(clazz, requestCode, null);
        getActivity().overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }
}
