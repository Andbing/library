package com.jade.library.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Jade on 2016/4/1.
 */
public class StringUtil {
    public static String readFromStream(InputStream is) throws IOException {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = is.read(buffer)) != -1) {
                baos.write(buffer, 0, len);
            }
            is.close();
            String result = baos.toString();
            baos.close();
            return result;
        } catch (Exception e) {
            return null;
        }
    }
}
