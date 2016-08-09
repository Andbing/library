package com.jade.library.data;

/**
 * Created by jade on 16-8-4.
 */
public interface OnResponseListener {
    void onSuccess(String data);

    void onError(Exception e);
}
