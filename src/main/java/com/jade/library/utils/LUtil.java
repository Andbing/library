package com.jade.library.utils;

import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;

import com.jade.library.LibManager;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

/**
 * 日志信息记录
 *
 * @author cyxod
 */
public class LUtil {
    /**
     * 开发阶段
     */
    private static final int DEVELOP = 0;
    /**
     * 内部测试阶段
     */
    private static final int DEBUG = 1;
    /**
     * 公开测试
     */
    private static final int BATE = 2;
    /**
     * 正式发布
     */
    private static final int RELEASE = 3;

    /**
     * 当前阶段标示
     */
    private static int currentStage = DEVELOP;

    private static String path;
    private static File file;
    private static FileOutputStream outputStream;
    private static String pattern = "yyyy-MM-dd HH:mm:ss";

    private static final String JADE_LOG_PATH = "/jade/log/";

    static {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            File externalStorageDirectory = Environment.getExternalStorageDirectory();
            if (TextUtils.isEmpty(LibManager.getInstance().getConfig().getLogPath())) {
                path = externalStorageDirectory.getAbsolutePath() + JADE_LOG_PATH;
            } else {
                path = externalStorageDirectory.getAbsolutePath() + "/" + LibManager.getInstance().getConfig().getLogPath();
            }
            File directory = new File(path);
            if (!directory.exists()) {
                directory.mkdirs();
            }
            file = new File(new File(path), "log.txt");
            Log.i("SDCAEDTAG", path);
            try {
                outputStream = new FileOutputStream(file, true);
            } catch (FileNotFoundException e) {

            }
        } else {

        }
    }

    public static void info(String msg) {
        info(LUtil.class, msg);
    }

    public static void debug(String msg) {
        debug(LUtil.class, msg);
    }

    public static void error(String msg) {
        error(LUtil.class, msg);
    }

    public static void info(Class<?> clazz, String msg) {
        switch (currentStage) {
            case DEVELOP:
                // 控制台输凿
                Log.i(clazz.getSimpleName(), msg);
                break;
            case DEBUG:
                // 在应用下面创建目录存放日忿
                Log.i(clazz.getSimpleName(), msg);
                break;
            case BATE:
                // 写日志到sdcard
                Date date = new Date();
                String time = DateUtil.format(date, pattern);
                if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                    if (outputStream != null) {
                        try {
                            outputStream.write(time.getBytes());
                            String className = "";
                            if (clazz != null) {
                                className = clazz.getSimpleName();
                            }
                            outputStream.write(("    " + className + "\r\n").getBytes());

                            outputStream.write(msg.getBytes());
                            outputStream.write("\r\n".getBytes());
                            outputStream.flush();
                        } catch (IOException e) {

                        }
                    } else {
                        Log.i("SDCAEDTAG", "file is null");
                    }
                }
                break;
            case RELEASE:
                // 一般不做日志记录
                break;
        }
    }

    public static void debug(Class<?> clazz, String msg) {
        switch (currentStage) {
            case DEVELOP:
                // 控制台输凿
                Log.d(clazz.getSimpleName(), msg);
                break;
            case DEBUG:
                // 在应用下面创建目录存放日忿

                break;
            case BATE:
                // 写日志到sdcard
                Date date = new Date();
                String time = DateUtil.format(date, pattern);
                if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                    if (outputStream != null) {
                        try {
                            outputStream.write(time.getBytes());
                            String className = "";
                            if (clazz != null) {
                                className = clazz.getSimpleName();
                            }
                            outputStream.write(("    " + className + "\r\n").getBytes());

                            outputStream.write(msg.getBytes());
                            outputStream.write("\r\n".getBytes());
                            outputStream.flush();
                        } catch (IOException e) {

                        }
                    } else {
                        Log.d("SDCAEDTAG", "file is null");
                    }
                }
                break;
            case RELEASE:
                // 一般不做日志记录
                break;
        }
    }

    public static void error(Class<?> clazz, String msg) {
        switch (currentStage) {
            case DEVELOP:
                // 控制台输凿
                Log.e(clazz.getSimpleName(), msg);
                break;
            case DEBUG:
                // 在应用下面创建目录存放日忿

                break;
            case BATE:
                // 写日志到sdcard
                Date date = new Date();
                String time = DateUtil.format(date, pattern);
                if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                    if (outputStream != null) {
                        try {
                            outputStream.write(time.getBytes());
                            String className = "";
                            if (clazz != null) {
                                className = clazz.getSimpleName();
                            }
                            outputStream.write(("    " + className + "\r\n").getBytes());

                            outputStream.write(msg.getBytes());
                            outputStream.write("\r\n".getBytes());
                            outputStream.flush();
                        } catch (IOException e) {

                        }
                    } else {
                        Log.d("SDCAEDTAG", "file is null");
                    }
                }
                break;
            case RELEASE:
                // 一般不做日志记录
                break;
        }
    }
}
