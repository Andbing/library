package com.jade.library.utils;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * 获取mac地址
 * 
 * @author cyxod
 *
 */
public class MACUtil {
	public static String getWlanMacAddress(Context context, boolean withDivider) {
		WifiManager mWifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
		WifiInfo info = mWifiManager.getConnectionInfo();
		if (info != null) {
			String macAddress = info.getMacAddress();
			if (withDivider) {
				return macAddress;
			} else {
				if (macAddress != null) {
					String upperCase = macAddress.toUpperCase();
					String[] strs = upperCase.split(":");
					StringBuffer sb = new StringBuffer();
					for (String string : strs) {
						sb.append(string);
					}
					return sb.toString();
				}
			}
		}
		return null;
	}

	public static String loadFileAsString(String filePath) throws IOException {
		StringBuffer fileData = new StringBuffer(1000);
		BufferedReader reader = new BufferedReader(new FileReader(filePath));
		char[] buf = new char[1024];
		int numRead = 0;
		while ((numRead = reader.read(buf)) != -1) {
			String readData = String.valueOf(buf, 0, numRead);
			fileData.append(readData);
		}
		reader.close();
		return fileData.toString();
	}

	public static String getEth0MacAddress(boolean withDivider) {
		try {
			String eth0MacAddress = loadFileAsString("/sys/class/net/eth0/address").toUpperCase().substring(0, 17);
			if (withDivider) {
				return eth0MacAddress;
			} else {
				String[] strs = eth0MacAddress.split(":");
				StringBuffer sb = new StringBuffer();
				for (String string : strs) {
					sb.append(string);
				}
				return sb.toString();
			}
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
}
