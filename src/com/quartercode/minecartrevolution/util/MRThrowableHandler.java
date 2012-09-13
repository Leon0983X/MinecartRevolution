
package com.quartercode.minecartrevolution.util;

import com.quartercode.qcutil.ThrowableHandler;

public class MRThrowableHandler implements ThrowableHandler {

    public MRThrowableHandler() {

    }

    @Override
    public void handleThrowable(Throwable throwable) {

        throwable.printStackTrace();
    }

}
