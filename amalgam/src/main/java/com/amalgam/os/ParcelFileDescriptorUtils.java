package com.amalgam.os;

import android.os.ParcelFileDescriptor;
import android.util.Log;

import java.io.IOException;

/**
 * Utility for the {@link android.os.ParcelFileDescriptor}.
 */

public final class ParcelFileDescriptorUtils {
    public static final String TAG = ParcelFileDescriptorUtils.class.getSimpleName();

    private ParcelFileDescriptorUtils() {
        throw new AssertionError();
    }

    /**
     * Close {@link android.os.ParcelFileDescriptor} with null checks.
     * Any exception during close is just logged.
     * FileDescriptor对象代表了原始的Linux文件描述符，它可以被写入Parcel并在读取时返回一个ParcelFileDescriptor对象
     * 用于操作原始的文件描述符。ParcelFileDescriptor是原始描述符的一个复制：
     * 对象和fd不同，但是都操作于同一文件流，使用同一个文件位置指针，等等。
     * 可以使用的方法是：writeFileDescriptor(FileDescriptor), readFileDescriptor()。
     * @param descriptor to close.
     */
    public static void close(ParcelFileDescriptor descriptor) {
        if (descriptor == null) {
            return;
        }
        try {
            descriptor.close();
        } catch (IOException e) {
            Log.e(TAG, "something went wrong on close descriptor: ", e);
        }
    }
}
