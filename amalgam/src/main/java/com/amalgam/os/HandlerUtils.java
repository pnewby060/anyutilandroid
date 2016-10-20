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
package com.amalgam.os;

import android.os.Handler;
import android.os.Looper;


public final class HandlerUtils {
    private HandlerUtils() {}

    /**
     * 获取主线程上的handler
     * @return
     */
    public static Handler getMainHandler() {
        return new Handler(Looper.getMainLooper());
    }

    /**
     * 主线程上post消息
     * @param message
     */
    public static void postOnMain(Runnable message) {
        getMainHandler().post(message);
    }

    /**
     * 主线程上延迟发送消息
     * @param message
     * @param delayMillis
     */
    public static void postOnMainWithDelay(Runnable message, long delayMillis) {
        getMainHandler().postDelayed(message, delayMillis);
    }
}