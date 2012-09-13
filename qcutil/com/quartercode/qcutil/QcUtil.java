
package com.quartercode.qcutil;

import com.quartercode.qcutil.io.PrintStreamThrowableHandler;

public class QcUtil {

    protected static ThrowableHandler defaultThrowableHandler = new PrintStreamThrowableHandler(System.err);

    public static ThrowableHandler getDefaultThrowableHandler() {

        return defaultThrowableHandler;
    }

    public static void setDefaultThrowableHandler(ThrowableHandler defaultThrowableHandler) {

        QcUtil.defaultThrowableHandler = defaultThrowableHandler;
    }

    public static void handleThrowable(Throwable throwable) {

        if (defaultThrowableHandler != null) {
            defaultThrowableHandler.handleThrowable(throwable);
        } else {
            throwable.printStackTrace(System.err);
        }
    }

    private QcUtil() {

    }

}
