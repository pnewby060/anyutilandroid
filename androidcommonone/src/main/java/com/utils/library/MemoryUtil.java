package com.utils.library;

import android.annotation.TargetApi;
import android.app.ActivityManager;
import android.content.Context;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import android.text.format.Formatter;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;

/**
 *  内存信息
 * Created by dell on 2016/6/18.
 */
public class MemoryUtil {

    private static final int ERROR = -1;
    private static final int AVALIABLE_EXTERNAL_MEMORY_SIZE = 50 * 1024 * 1024; // 50MB

    /**
     * 判断外部sd卡是否可用
     * Judge whether external momory is available
     *
     * @return
     */
    public static boolean isExternalMemoryAvailable() {
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }

    /**
     * 判断外部存储空间是否已满
     * Judge whether external memory is full
     *
     * @return
     */
    public static boolean isExternalMemoryFull() {
        return getAvailableExternalMemorySize() - AVALIABLE_EXTERNAL_MEMORY_SIZE < 0;
    }

    /**
     * 获取可用的内部数据空间
     * Get available internal memory size
     *
     * @return
     */
    public static long getAvailableInternalMemorySize() {
        File path = Environment.getDataDirectory();
        StatFs stat = new StatFs(path.getPath());
        long blockSize = stat.getBlockSize();
        long availableBlocks = stat.getAvailableBlocks();
        return availableBlocks * blockSize;
    }

    /**
     * 获取内部数据目录空间大小
     * Get internal memory size
     *
     * @return
     */
    public static long getTotalInternalMemorySize() {
        File path = Environment.getDataDirectory();
        StatFs stat = new StatFs(path.getPath());
        long blockSize = stat.getBlockSize();
        long totalBlocks = stat.getBlockCount();
        return totalBlocks * blockSize;
    }

    /**
     * 获取可用的外部存储空间大小
     * Get available external memory size
     *
     * @return
     */
    public static long getAvailableExternalMemorySize() {
        if (isExternalMemoryAvailable()) {
            File path = Environment.getExternalStorageDirectory();
            StatFs stat = new StatFs(path.getPath());
            long blockSize = stat.getBlockSize();
            long availableBlocks = stat.getAvailableBlocks();
            return availableBlocks * blockSize;
        } else {
            return ERROR;
        }
    }

    /**
     * 获取外部sd存储空间大小
     * Get external memory size
     *
     * @return
     */
    public static long getTotalExternalMemorySize() {
        if (isExternalMemoryAvailable()) {
            File path = Environment.getExternalStorageDirectory();
            StatFs stat = new StatFs(path.getPath());
            long blockSize = stat.getBlockSize();
            long totalBlocks = stat.getBlockCount();
            return totalBlocks * blockSize;
        } else {
            return ERROR;
        }
    }


    /**
     * 获取总的物理内存大小
     * Get total memory
     *
     * @param context
     * @return
     */
    public static String getTotalMemory(Context context) {
        String str1 = "/proc/meminfo";
        String str2;
        String[] arrayOfString;
        long initial_memory = 0;
        try {
            FileReader localFileReader = new FileReader(str1);
            BufferedReader localBufferedReader = new BufferedReader(localFileReader, 8192);
            str2 = localBufferedReader.readLine();
            arrayOfString = str2.split("\\s+");
            for (String num : arrayOfString) {
                Log.i(str2, num + "\t");
            }
            initial_memory = Integer.valueOf(arrayOfString[1]).intValue() * 1024;
            localBufferedReader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return Formatter.formatFileSize(context, initial_memory);// Byte转换为KB或者MB，内存大小规格化
    }

    /**
     * 获取可用内存
     * Get available memory
     *
     * @param context
     * @return
     */
    public static String getAvailableMemory(Context context) {
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        ActivityManager.MemoryInfo mi = new ActivityManager.MemoryInfo();
        am.getMemoryInfo(mi);
        return Formatter.formatFileSize(context, mi.availMem);
    }

    /**
     * 获取系统总内存
     * 方式二，使用ActivityManager的getMemoryInfo(ActivityManager.MemoryInfo outInfo)
     ActivityManager.getMemoryInfo()主要是用于得到当前系统剩余内存的及判断是否处于低内存运行。
     实例1：
     private void displayBriefMemory() {
     final ActivityManager activityManager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
     ActivityManager.MemoryInfo info = new ActivityManager.MemoryInfo();
     activityManager.getMemoryInfo(info);
     Log.i(tag,"系统剩余内存:"+(info.availMem >> 10)+"k");
     Log.i(tag,"系统是否处于低内存运行："+info.lowMemory);
     Log.i(tag,"当系统剩余内存低于"+info.threshold+"时就看成低内存运行");
     }
     ActivityManager.getMemoryInfo()是用ActivityManager.MemoryInfo返回结果，而不是Debug.MemoryInfo，他们不一样的。
     ActivityManager.MemoryInfo只有三个Field:
     availMem:表示系统剩余内存
     lowMemory：它是boolean值，表示系统是否处于低内存运行
     hreshold：它表示当系统剩余内存低于好多时就看成低内存运行
     * Get all memory
     *
     * @param context
     * @return
     */
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public static String getAllMemory(Context context) {
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        ActivityManager.MemoryInfo mi = new ActivityManager.MemoryInfo();
        am.getMemoryInfo(mi);
        return Formatter.formatFileSize(context, mi.totalMem);
    }

    /**
     * Get format size
     *
     * @param size
     * @return
     */
    public static String getFormatSize(double size) {
        double kiloByte = size / 1024;
        if (kiloByte < 1) {
            return size + "Byte";
        }

        double megaByte = kiloByte / 1024;
        if (megaByte < 1) {
            BigDecimal result1 = new BigDecimal(Double.toString(kiloByte));
            return result1.setScale(2, BigDecimal.ROUND_HALF_UP)
                    .toPlainString() + "KB";
        }

        double gigaByte = megaByte / 1024;
        if (gigaByte < 1) {
            BigDecimal result2 = new BigDecimal(Double.toString(megaByte));
            return result2.setScale(2, BigDecimal.ROUND_HALF_UP)
                    .toPlainString() + "MB";
        }

        double teraBytes = gigaByte / 1024;
        if (teraBytes < 1) {
            BigDecimal result3 = new BigDecimal(Double.toString(gigaByte));
            return result3.setScale(2, BigDecimal.ROUND_HALF_UP)
                    .toPlainString() + "GB";
        }
        BigDecimal result4 = new BigDecimal(teraBytes);
        return result4.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString()
                + "TB";
    }

}
