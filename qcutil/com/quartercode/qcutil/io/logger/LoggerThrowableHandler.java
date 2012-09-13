
package com.quartercode.qcutil.io.logger;

import com.quartercode.qcutil.ThrowableHandler;
import com.quartercode.qcutil.utility.ObjectUtil;

public class LoggerThrowableHandler implements ThrowableHandler {

    protected Logger logger;

    public LoggerThrowableHandler(Logger logger) {

        setLogger(logger);
    }

    public Logger getLogger() {

        return logger;
    }

    public void setLogger(Logger logger) {

        this.logger = logger;
    }

    @Override
    public void handleThrowable(Throwable throwable) {

        logger.handleThrowable(throwable);
    }

    @Override
    public int hashCode() {

        final int prime = 31;
        int result = 1;
        result = prime * result + (logger == null ? 0 : logger.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        LoggerThrowableHandler other = (LoggerThrowableHandler) obj;
        if (logger == null) {
            if (other.logger != null) {
                return false;
            }
        } else if (!logger.equals(other.logger)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {

        return ObjectUtil.generateObjectStringWithNames(this, "logger");
    }

}
