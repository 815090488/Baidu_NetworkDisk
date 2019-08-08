package com.luyao.util;

import java.io.*;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FileUtil {
    private static SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmssSSS");
    private static SimpleDateFormat formatDate = new SimpleDateFormat("yyyy年MM月dd日");

    public static String getUnqiueByName(String filename) {
        if (filename == null || filename.trim().length() == 0) {
            return "";
        }
        return format.format(new Date()) + filename.substring(filename.lastIndexOf("."));
    }

    /**
     * 计算文件总容量
     *
     * @param files
     * @return
     */
    public static long calSize(File[] files) {
        int total = 0;
        for (File file : files) {
            total += file.length();
        }
        return total;
    }

    /**
     * 计算MD5
     *
     * @param filename
     * @return
     * @throws Exception
     */

    private static byte[] createChecksum(String filename) throws Exception {
        InputStream fis = new FileInputStream(filename);
        BufferedInputStream bis = new BufferedInputStream(fis);

        MessageDigest complete = MessageDigest.getInstance("MD5");

        byte[] buffer;
        int size = fis.available();
        //如果文件小于20M
        if (size >= 0 && size < 20480) {
            buffer = new byte[size];
            fis.read(buffer);
            complete.update(buffer);//使用指定的字节数组更新摘要
        } else {
            buffer = new byte[10240];
            int numRead;
            do {
                numRead = bis.read(buffer);
                if (numRead > 0) {
                    complete.update(buffer, 0, numRead);//使用指定的字节数组更新摘要
                }
            } while (numRead != -1);
        }
        fis.close();
        return complete.digest();
    }

    public static String getMD5(File file) throws Exception {
        return getMD5Checksum(file.getAbsolutePath());
    }

    public static String getMD5Checksum(String filename) throws Exception {
        byte[] b = createChecksum(filename);
        String result = "";

        for (int i = 0; i < b.length; i++) {
            result += Integer.toString((b[i] & 0xff) + 0x100, 16).substring(1);
        }
        return result;
    }
}
