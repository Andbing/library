package com.jade.library.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;

public class NetWorkTypeUtils {

    public static final int NETTYPE_NO = 0;
    public static final int NETTYPE_WIFI = 1;
    public static final int NETTYPE_2G = 2;
    public static final int NETTYPE_3G = 3;
    public static final int NETTYPE_4G = 4;
    private static final int NETTYPE_ETHERNET = 5;

    public static boolean isNetAvailable(Context context) {
        return NetWorkTypeUtils.getAvailableNetWorkInfo(context) != null;
    }

    public static boolean isThirdGeneration(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context
                .getSystemService(Context.TELEPHONY_SERVICE);
        int netWorkType = telephonyManager.getNetworkType();
        switch (netWorkType) {
            case TelephonyManager.NETWORK_TYPE_GPRS:
            case TelephonyManager.NETWORK_TYPE_CDMA:
            case TelephonyManager.NETWORK_TYPE_EDGE:

                return false;
            default:
                return true;
        }
    }

    public static boolean isWifi(Context context) {
        NetworkInfo networkInfo = getAvailableNetWorkInfo(context);
        if (networkInfo != null) {
            if (networkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
                return true;
            }
        }

        return false;
    }

    public static NetworkInfo getAvailableNetWorkInfo(Context context) {
        NetworkInfo activeNetInfo;
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            activeNetInfo = connectivityManager.getActiveNetworkInfo();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        if (activeNetInfo != null && activeNetInfo.isAvailable()
                && activeNetInfo.isConnected()) {
            return activeNetInfo;
        } else {
            return null;
        }
    }

    public static int getNetType(Context context) {

        ConnectivityManager connectivityManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isAvailable()
                && networkInfo.isConnected()) {
            if (ConnectivityManager.TYPE_WIFI == networkInfo.getType()) {
                return NETTYPE_WIFI;
            } else if (ConnectivityManager.TYPE_ETHERNET == networkInfo.getType()) {
                return NETTYPE_ETHERNET;
            } else {
                TelephonyManager telephonyManager = (TelephonyManager) context
                        .getSystemService(Context.TELEPHONY_SERVICE);

                switch (telephonyManager.getNetworkType()) {
                    case TelephonyManager.NETWORK_TYPE_GPRS:
                    case TelephonyManager.NETWORK_TYPE_CDMA:
                    case TelephonyManager.NETWORK_TYPE_EDGE:
                        return NETTYPE_2G;
                    case TelephonyManager.NETWORK_TYPE_LTE:
                        return NETTYPE_4G;
                    default:
                        return NETTYPE_3G;

                }
            }
        } else {
            return NETTYPE_NO;
        }
    }
}
