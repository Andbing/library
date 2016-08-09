package com.jade.library.mvp;

/**
 * Created by jade on 16-8-3.
 */
public interface BasePresenter<V> {
    void attach(V v);

    void detach();
}