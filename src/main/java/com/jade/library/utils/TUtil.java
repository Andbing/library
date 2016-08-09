package com.jade.library.utils;

import android.content.Context;
import android.widget.Toast;

import com.jade.library.LibManager;

/**
 * Toast 工具类
 *
 * @author cyxod
 */
public class TUtil {
    /**
     * 短时Toast
     *
     * @param msg
     */
    public static void shortToast(Object msg) {
        Toast.makeText(LibManager.getInstance().getConfig().getContext(), msg.toString(), Toast.LENGTH_SHORT).show();
    }

    /**
     * 长时Toast
     *
     * @param msg
     */
    public static void longToast(Object msg) {
        Toast.makeText(LibManager.getInstance().getConfig().getContext(), msg.toString(), Toast.LENGTH_LONG).show();
    }
}
