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
package com.amalgam.os.exception;

/**
 * Thrown when a method is invoked from wrong thread.
 * Generally, this exception is thrown when calling blocking call on main thread, or causes ANR.
 * @author KeithYokoma
 *
 */

public class CalledFromWrongThreadException extends RuntimeException {
    private static final long serialVersionUID = 491900897762569060L;

    public CalledFromWrongThreadException() {
        super();
    }

    public CalledFromWrongThreadException(String detailMessage) {
        super(detailMessage);
    }

    public CalledFromWrongThreadException(Throwable throwable) {
        super(throwable);
    }

    public CalledFromWrongThreadException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }
}