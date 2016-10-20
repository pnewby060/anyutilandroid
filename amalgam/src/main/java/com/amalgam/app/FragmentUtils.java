package com.amalgam.app;

import android.annotation.TargetApi;
import android.content.ContentResolver;
import android.os.Build;

/**
 * @author KeithYokoma
 */

public final class FragmentUtils {
    private FragmentUtils() {
        throw new AssertionError();
    }

    /**
     * 重绘菜单
     * 运行时改变Menu ItemonCreateOptionsMenu()只有在menu刚被创建时才会执行，
     * 因此要想随时动态改变OptionMenu就要实现onPrepareOptionsMenu()方法，该方法会传给你Menu对象，供使用
     * Android2.3或更低的版本会在每次Menu打开的时候调用一次onPrepareOptionsMenu().
     * Android3.0及以上版本默认menu是打开的，所以必须调用invalidateOptionsMenu()方法，
     * 然后系统将调用onPrepareOptionsMenu()执行update操作
     * @param fragment
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static void invalidateOptionsMenu(android.app.Fragment fragment) {
        fragment.getActivity().invalidateOptionsMenu();
    }

    public static void supportInvalidateOptionsMenu(android.support.v4.app.Fragment fragment) {
        fragment.getActivity().supportInvalidateOptionsMenu();
    }

    /**
     * ContentResolver是通过ContentProvider来获取其他与应用程序共享的数据
     * @param fragment
     * @return
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
	public static ContentResolver getContentResolver(android.app.Fragment fragment) {
        return fragment.getActivity().getContentResolver();
    }

    public static ContentResolver getContentResolver(android.support.v4.app.Fragment fragment) {
        return fragment.getActivity().getContentResolver();
    }
}
