/*
 * Copyright (C) 2016 venshine.cn@gmail.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.wx.android.common.util;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.ColorStateList;
import android.content.res.XmlResourceParser;
import android.graphics.drawable.Drawable;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * 资源操作
 *
 * @author venshine
 */
public class ResourceUtils {

    /**
     * 获取资源id
     * Return a resource identifier for the given resource name. A fully qualified resource name is of the form
     * "package:type/entry". The first two components (package and type) are optional if defType and defPackage,
     * respectively, are specified here.
     *
     * @param context
     * @param drawableName
     * @param defType
     * @return
     */
    public static int getResourceByName(Context context, String drawableName, String defType) {
        return context.getResources().getIdentifier(drawableName, defType, AppUtils.getPackageName(context));
    }

    /**
     * 读取raw资源
     * Get raw file, ui/raw/file
     *
     * @param context
     * @param id
     * @return
     */
    public static InputStream getRaw(Context context, int id) {
        return context.getResources().openRawResource(id);
    }

    /**
     * 获取asset文件描述符
     * Get raw file descriptor, ui/raw/file. This function only works for resources that are stored in the package as
     * uncompressed data, which typically includes things like mp3 files and png images.
     *
     * @param context
     * @param id
     * @return
     */
    public static AssetFileDescriptor getRawFd(Context context, int id) {
        return context.getResources().openRawResourceFd(id);
    }

    /**
     * 读取raw文件文本
     * Get raw text file, ui/raw/text
     *
     * @param context
     * @param id
     * @return
     */
    public String getRawText(Context context, int id) {
        try {
            InputStreamReader inputReader = new InputStreamReader(getRaw(context, id));
            BufferedReader bufReader = new BufferedReader(inputReader);
            String line = "";
            StringBuilder result = new StringBuilder();
            while ((line = bufReader.readLine()) != null) {
                result.append(line);
            }
            return result.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Get xml file, ui/xml/file
     *
     * @param context
     * @param id
     * @return
     */
    public static XmlResourceParser getXml(Context context, int id) {
        return context.getResources().getXml(id);
    }

    /**
     * 获取drawable
     * Get drawable, ui/drawable/file
     *
     * @param context
     * @param id
     * @return
     */
    public static Drawable getDrawable(Context context, int id) {
        return context.getResources().getDrawable(id);
    }

    /**
     * 获取string
     * Get string, ui/values/strings.xml
     *
     * @param context
     * @param id
     * @return
     */
    public static String getString(Context context, int id) {
        return context.getResources().getString(id);
    }

    /**
     * 获取字符串数组
     * Get string array, ui/values/strings.xml
     *
     * @param context
     * @param id
     * @return
     */
    public static String[] getStringArray(Context context, int id) {
        return context.getResources().getStringArray(id);
    }

    /**
     * Get color, ui/values/colors.xml
     *
     * @param context
     * @param id
     * @return
     */
    public static int getColor(Context context, int id) {
        return context.getResources().getColor(id);
    }

    /**
     * Get color state list, ui/values/colors.xml
     *
     * @param context
     * @param id
     * @return
     */
    public static ColorStateList getColorStateList(Context context, int id) {
        return context.getResources().getColorStateList(id);
    }

    /**
     * Get dimension, ui/values/dimens.xml
     * 原文网址：http://www.eoeandroid.com/thread-322627-1-1.html
     * getDimension()是基于当前DisplayMetrics进行转换，获取指定资源id对应的尺寸。
     * 文档里并没说这里返回的就是像素，要注意这个函数的返回值是float，像素肯定是int。
     getDimensionPixelSize()与getDimension()功能类似，不同的是将结果转换为int，并且小数部分四舍五入。
     getDimensionPixelOffset()与getDimension()功能类似，不同的是将结果转换为int，并且偏移转换（offset conversion，
     函数命名中的offset是这个意思）是直接截断小数位，即取整（其实就是把float强制转化为int，注意不是四舍五入哦）。
     由此可见，这三个函数返回的都是绝对尺寸，而不是相对尺寸（dp/sp等）。如果getDimension()返回结果是20.5f，
     那么getDimensionPixelSize()返回结果就是21，getDimensionPixelOffset()返回结果就是20。
     *
     * @param context
     * @param id
     * @return View dimension value multiplied by the appropriate metric.
     */
    public static float getDimension(Context context, int id) {
        return context.getResources().getDimension(id);
    }

    /**
     * Get dimension, ui/values/dimens.xml
     *
     * @param context
     * @param id
     * @return View dimension value multiplied by the appropriate metric and truncated to integer pixels.
     */
    public static int getDimensionPixelOffset(Context context, int id) {
        return context.getResources().getDimensionPixelOffset(id);
    }

    /**
     * Get dimension, ui/values/dimens.xml
     *
     * @param context
     * @param id
     * @return View dimension value multiplied by the appropriate metric and truncated to integer pixels.
     */
    public static int getDimensionPixelSize(Context context, int id) {
        return context.getResources().getDimensionPixelSize(id);
    }

}
