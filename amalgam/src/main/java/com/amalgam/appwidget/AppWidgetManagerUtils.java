/*
 * Copyright (C) 2013 nohana, Inc. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.amalgam.appwidget;

import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;

/**
 * Utility for {@link android.appwidget.AppWidgetManager}.
 */

public final class AppWidgetManagerUtils {

    /**
     * 获取appwidgetids
     * 最近在做widget是使用AppWidgetManager的getAppWidgetIds(ComponentName provider)方法返回widget实例时发现总是返回空数组，
     * 最后发现问题在于ComponentName的构造方法需要使用：new ComponentName(context, com.android.appWidgetinstance.class)，
     * 而不是使用包名和类名的构造方法
     * Wrapper method of the {@link android.appwidget.AppWidgetManager#getAppWidgetIds(android.content.ComponentName)}.
     * @see android.appwidget.AppWidgetManager#getAppWidgetIds(android.content.ComponentName).
     */
    public static int[] getAppWidgetIds(AppWidgetManager appWidgetManager, Context context, Class<?> clazz) {
        return appWidgetManager.getAppWidgetIds(new ComponentName(context, clazz));
    }
}