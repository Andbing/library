package com.jade.library.utils;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Environment;
import android.text.TextUtils;
import android.widget.Toast;

import java.util.List;

public class Utils {

    private static final String DEFAULT_COLOR = "#00FFFFFF";

    public static final String EXTRA_PORT = "extraPort";

    /**
     * 将十六进制的颜色值转换为可设置的颜色值
     * 默认格式是 #FF FF FF FF
     */
    public static int getColorByString(String color) {
        if (!TextUtils.isEmpty(color) && color.startsWith("#")) {
            try {
                return Color.parseColor(color);
            } catch (Exception e) {
                return Color.parseColor(DEFAULT_COLOR);
            }
        } else {
            return Color.parseColor(DEFAULT_COLOR);
        }
    }

    /**
     * C to Java Boolean
     *
     * @param i
     * @return
     */
    public static boolean is(int i) {
        return i == 1 ? true : false;
    }

    /**
     * 字符串非空判断
     *
     * @param str
     * @return
     */
    public static boolean isNotEmpty(String str) {
        return str != null && !TextUtils.isEmpty(str) && !"".equals(str);
    }

    /**
     * 是否安装了指定应用
     *
     * @param context
     * @param packageName
     * @return
     */
    public static boolean isInstalledApp(Context context, String packageName) {
        PackageManager pm = context.getPackageManager();
        List<PackageInfo> installedPackages = pm.getInstalledPackages(0);

        for (PackageInfo packageInfo : installedPackages) {
            if (TextUtils.equals(packageInfo.packageName, packageName)) {
                return true;
            }
        }

        return false;
    }

    /**
     * 根据应用包名启动应用
     *
     * @param context
     * @param appPackageName
     */
    public static void startApp(Context context, String appPackageName) {
        try {
            Intent intent = context.getPackageManager().getLaunchIntentForPackage(appPackageName);
            context.startActivity(intent);
        } catch (Exception e) {
            Toast.makeText(context, "没有安装", Toast.LENGTH_LONG).show();
        }
    }


    /**
     * 进入信号源
     *
     * @param context
     * @param action
     */
    public static void enterDesktopSignal(Context context, String action) {
        try {
            Intent intent = new Intent();
            intent.setClassName("com.stv.signalsourcemanager", "com.stv.signalsourcemanager.MainActivity");
            intent.putExtra(EXTRA_PORT, action);
            context.startActivity(intent);
        } catch (Exception e) {
            LUtil.error("启动信号源失败..");
        }
    }

    /**
     * 打开浏览器
     *
     * @param context
     * @param url
     */
    public static void openBrowser(Context context, String url) {
        try {
            final Uri uri = Uri.parse(url);
            final Intent it = new Intent(Intent.ACTION_VIEW, uri);
            context.startActivity(it);
            // 20160614 18:06 打开网页bug
        } catch (ActivityNotFoundException e) {
            return;
        }
    }


    //判断是否有SD卡
    public static boolean avaiableMedia() {
        String status = Environment.getExternalStorageState();
        if (status.equals(Environment.MEDIA_MOUNTED)) {
            return true;
        } else {
            return false;
        }
    }
}
